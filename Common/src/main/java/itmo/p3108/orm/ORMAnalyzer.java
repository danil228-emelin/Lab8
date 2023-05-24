package itmo.p3108.orm;

import itmo.p3108.bd.PsqlStorage;
import itmo.p3108.exception.FileException;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.util.Reflection;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class ORMAnalyzer {
    private static final PsqlStorage PSQL = PsqlStorage.getInstance();
    private static String query = "select create_table(?,?);";

    private static String addColumn = "select add_column(?, ?, ?);";

    public static void analyze(String packageSource) {
        Optional<Set<Class<?>>> optional = Reflection.findAllAnnotatedClasses(packageSource, Table.class);
        if (optional.isEmpty() || optional.get().size() == 0) {
            throw new FileException("wrong packageSource,can't find classes implements Table annotation");
        }
        Set<Class<?>> classes = optional.get();
        for (Class<?> classSource : classes) {
            Optional<Annotation> optionalAnnotation = Reflection.getAnnotation(classSource, Table.class);
            if (optionalAnnotation.isEmpty()) {
                throw new FileException("can't find  Table annotation for " + classSource.getName());
            }
            Table tableAnnotation = (Table) optionalAnnotation.get();
            Optional<Set<Field>> optionalFields = Reflection.getFieldsAnnotatedWith(classSource, Id.class);
            if (optionalFields.isEmpty() || optionalFields.get().size() != 1) {
                throw new ValidationException("Only one field must be annotated with Id annotation");
            }
            Set<Field> fields = optionalFields.get();
            Optional<Set<Annotation>> columnFields = Reflection.getAnnotationsFromFields(classSource, Column.class);
            if (columnFields.isEmpty() || columnFields.get().size() == 0) {
                throw new ValidationException("At least one field must be annotated with column");
            }
            boolean result = createIfNotExist(tableAnnotation.tableName(), fields.iterator().next().getName(), columnFields.get());
            if (!result) {
                throw new ValidationException("error occur during PSQL.CreateIfNotExist for " + tableAnnotation.tableName());
            } else {
                log.info("table " + tableAnnotation.tableName() + " created");
            }
        }
    }


    private static boolean createIfNotExist(String tableName, String idName, Set<Annotation> columnFields) {
        PSQL.changeMod(false);
        try (PreparedStatement preparedStatement = PSQL.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, idName);
            boolean createResult = preparedStatement.execute();
            if (!createResult) {
                throw new ValidationException("Can't create table " + tableName);
            }
            for (Annotation columnAnnotation : columnFields) {
                boolean result = addColumnInTable((Column) columnAnnotation, tableName);
                if (!result) {
                    throw new ValidationException(String.format("Column %s wasn't added in table %s", ((Column) columnAnnotation).columnName(), tableName));
                }
            }
            PSQL.commit();
            return true;
        } catch (SQLException | ValidationException e) {
            PSQL.rollback();
            log.error(e.getMessage());
        } finally {
            PSQL.changeMod(true);
        }
        return false;
    }

    public static boolean addColumnInTable(Column annotation, String tableName) throws SQLException {

        try (PreparedStatement preparedStatement = PSQL.getConnection().prepareStatement(addColumn)) {
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, annotation.columnName());
            preparedStatement.setString(3, annotation.typeName());
            return preparedStatement.execute();
        }
    }
}

