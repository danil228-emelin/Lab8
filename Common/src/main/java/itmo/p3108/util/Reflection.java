package itmo.p3108.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class Reflection,provide Reflections class functionality
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reflection {
    /**
     * @param packageName source package
     * @return find all commands in certain package
     */
    public static Optional<Set<Class<?>>> findAllClasses(String packageName, Class<?> classExtended) {
        try {
            Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
            return Optional.of(reflections.getSubTypesOf(classExtended).stream().parallel().collect(Collectors.toSet()));
        } catch (ReflectionsException exception) {
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param pathToCheckedClass source package
     * @param annotation         certain annotation to find
     * @return commands which has certain annotation
     */
    public static Optional<Set<Method>> findAllMethodsWithAnnotation(@NonNull String pathToCheckedClass, @NonNull Class<? extends Annotation> annotation) {
        try {


            Reflections reflections = new Reflections(pathToCheckedClass, new MethodAnnotationsScanner());


            return Optional.ofNullable(reflections.getMethodsAnnotatedWith(annotation));

        } catch (ReflectionsException exception) {
            System.err.println(exception.getMessage());
        }
        return Optional.empty();
    }

    public static <T> boolean hasAnnotation(@NonNull T command, @NonNull Class<? extends Annotation> annotation) {
        return command.getClass().isAnnotationPresent(annotation);

    }

    public static <T extends Annotation> Optional<Set<Class<?>>> findAllAnnotatedClasses(String packageName, Class<T> annotation) {
        try {
            Reflections reflections = new Reflections(packageName);
            return Optional.ofNullable(reflections.getTypesAnnotatedWith(annotation));
        } catch (ReflectionsException exception) {
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }

    public static <T extends Annotation> Optional<Annotation> getAnnotation(Class<?> classSource, Class<T> annotationToSearch) {
        return Optional.ofNullable(classSource.getAnnotation(annotationToSearch));
    }

    public static <T extends Annotation> Optional<Set<Field>> getFieldsAnnotatedWith(Class<?> classSource, Class<T> annotationToSearch) {
        try {
            Reflections reflections = new Reflections(classSource, new FieldAnnotationsScanner());
            return Optional.ofNullable(reflections.getFieldsAnnotatedWith(annotationToSearch));
        } catch (ReflectionsException exception) {
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }

    public static <T extends Annotation> Optional<Set<Annotation>> getAnnotationsFromFields(Class<?> classSource, Class<T> annotationToSearch) {
        try {
            Reflections reflections = new Reflections(classSource, new FieldAnnotationsScanner());
            return Optional.of(
                    reflections
                            .getFieldsAnnotatedWith(annotationToSearch)
                            .stream()
                            .map(x -> x.getAnnotation(annotationToSearch))
                            .collect(Collectors.toSet()));
        } catch (ReflectionsException exception) {
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }

}
