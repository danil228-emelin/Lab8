package itmo.p3108.model;


import itmo.p3108.orm.Column;
import itmo.p3108.orm.Id;
import itmo.p3108.orm.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Table(tableName = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 498787001L;
    @Id
    private Long personId;
    @Column(columnName = "person_name", typeName = "varchar(31)")
    private String personName;

    private Coordinates coordinates;
    @Column(columnName = "person_creation_date", typeName = "varchar(63)")

    private java.time.ZonedDateTime personCreationDate;

    @Column(columnName = "person_height", typeName = "real")

    private Double personHeight;
    @Column(columnName = "person_birthday", typeName = "varchar(63)")

    private java.time.LocalDate personBirthday;
    @Column(columnName = "person_eye_color", typeName = "smallint")

    private Color personEyeColor;
    @Column(columnName = "person_birthday", typeName = "smallint")

    private Country personNationality;
    private Location location;
    private Place resp;
    private Place targetPlace;
    private String token;

    @Override
    public int hashCode() {
        int result = personId.hashCode();
        result = 31 * result + personName.hashCode();
        result = 31 * result + coordinates.hashCode();
        result = 31 * result + personCreationDate.hashCode();
        result = 31 * result + personHeight.hashCode();
        result = 31 * result + personBirthday.hashCode();
        result = 31 * result + personEyeColor.hashCode();
        result = 31 * result + personNationality.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", coordinates=" + coordinates +
                ", personCreationDate=" + personCreationDate +
                ", personHeight=" + personHeight +
                ", personBirthday=" + personBirthday +
                ", personEyeColor=" + personEyeColor +
                ", personNationality=" + personNationality +
                ", location=" + location +
                '}';
    }
}
