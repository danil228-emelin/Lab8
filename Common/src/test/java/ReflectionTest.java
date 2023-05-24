import itmo.p3108.model.Person;
import itmo.p3108.orm.Id;
import itmo.p3108.orm.Table;
import itmo.p3108.util.Reflection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReflectionTest {


    @Test
    void check_findAllAnnotatedClasses() {
        Assertions.assertThat(Reflection.findAllAnnotatedClasses("itmo.p3108.model", Table.class))
                .as("Can't find classes implements Table annotation")
                .isPresent();
    }

    @Test
    void check_getAnnotation() {
        Assertions.assertThat(Reflection.getAnnotation(Person.class, Table.class))
                .as("Can't find Table annotation")
                .isPresent();
    }

    @Test
    void check_getFieldsAnnotatedWith() {
        Assertions.assertThat(Reflection.getFieldsAnnotatedWith(Person.class, Id.class))
                .as("Can't find Id annotation")
                .isPresent();

    }

}
