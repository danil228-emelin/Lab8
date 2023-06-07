package itmo.p3108.swing;

import itmo.p3108.PersonReadingBuilder;
import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.command.Add;
import itmo.p3108.command.AddIfMax;
import itmo.p3108.command.Update;
import itmo.p3108.command.type.Command;
import itmo.p3108.model.Coordinates;
import itmo.p3108.model.Country;
import itmo.p3108.model.Location;
import itmo.p3108.model.Person;
import itmo.p3108.util.CheckData;
import itmo.p3108.util.Checking;
import itmo.p3108.util.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.ZonedDateTime;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddFrame {
    private static final String[] ADD_PROPER_ARGUMENTS_ORDER = {"personName", "personHeight", "personBirthday", "locationX", "locationY", "locationZ", "locationName", "coordinatesX", "coordinatesY"};

    private static final AddFrame ADD_FRAME = new AddFrame();
    public static volatile boolean wasClicked = false;
    public static volatile boolean wasAdded = false;

    private JFrame jFrame;
    @Getter
    private JPanel jPanel;
    @Setter
    private Command command;

    public static AddFrame getInstance() {
        return ADD_FRAME;
    }

    public static void main(String[] args) {
        AddFrame.getInstance().createFrame();
    }

    public JFrame createFrame() {

        log.info("buttonAction try to perform");
        if (jPanel == null) {
            jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.setBounds(AbstractFrame.DIMENSION.width / 2 - 450, AbstractFrame.DIMENSION.height / 2 - 200, 500, 700);
        }
        if (jFrame != null) {
            return jFrame;
        }
        jFrame = new JFrame() {
        };

        {
            LabText labText = new LabText(jPanel);

            JTextField name = labText.create("Name:", 50, 40, 40, 30);
            JTextField height = labText.create("Height:", 40, 75, 50, 30);
            JTextField birthday = labText.create("Birthday:", 40, 110, 50, 30, "FORMAT:DD-MM-YYYY");

            JComboBox<itmo.p3108.model.Color> color = new JComboBox<>();
            JLabel colorLabel = new JLabel("Choose color");
            colorLabel.setBounds(0, 145, 95, 30);
            color.setBounds(95, 145, 100, 30);
            color.addItem(itmo.p3108.model.Color.BROWN);
            color.addItem(itmo.p3108.model.Color.GREEN);
            color.addItem(itmo.p3108.model.Color.BLUE);
            color.addItem(itmo.p3108.model.Color.WHITE);
            color.addItem(itmo.p3108.model.Color.YELLOW);
            jPanel.add(colorLabel);
            jPanel.add(color);
            JComboBox<Country> country = new JComboBox<>();
            JLabel countryLabel = new JLabel("Choose country");
            countryLabel.setBounds(0, 180, 95, 30);
            country.setBounds(95, 180, 100, 30);
            country.addItem(Country.FRANCE);
            country.addItem(Country.RUSSIA);
            country.addItem(Country.NORTH_KOREA);
            country.addItem(Country.SPAIN);
            jPanel.add(country);
            jPanel.add(countryLabel);
            JTextField locationX = labText.create("Location.x", 30, 215, 60, 30, "Use fractional number");

            JTextField locationY = labText.create("Location.y", 30, 250, 60, 30, "Use fractional number");

            JTextField locationZ = labText.create("Location.z", 30, 285, 60, 30, "Use fractional number");

            JTextField locationName = labText.create("Location.name", 0, 320, 90, 30);
            JTextField coordinatesX = labText.create("Coordinates.x", 0, 355, 90, 30, "Use integer");

            JTextField coordinatesY = labText.create("Coordinates.y", 0, 390, 90, 30, "Use fractional number");

            JButton ok = new JButton("CREATE");
            CheckData checkData = new CheckData();
            jFrame.revalidate();

            ok.addActionListener(e -> {
                wasAdded = false;
                wasClicked = false;
                log.info("Try to create person");
                String[] array = {name.getText(), height.getText(), birthday.getText(), locationX.getText(), locationY.getText(), locationZ.getText(), locationName.getText(), coordinatesX.getText(), coordinatesY.getText()};
                if (checkData.wrapperCheckArguments(array, Checking.class, ADD_PROPER_ARGUMENTS_ORDER)) {
                    Person person = Person.builder()
                            .personId(PersonReadingBuilder.getInstance().createId())
                            .personName(name.getText())
                            .personHeight(Double.parseDouble(height.getText()))
                            .personBirthday(LocalDateAdapter.getInstance().unmarshal(birthday.getText()))
                            .personEyeColor((itmo.p3108.model.Color) color.getSelectedItem())
                            .personNationality((Country) country.getSelectedItem())
                            .location(Location.builder().locationX(Double.parseDouble(locationX.getText())).locationY(Float.parseFloat(locationY.getText())).locationZ(Float.parseFloat(locationZ.getText())).locationName(locationName.getText()).build())
                            .coordinates(Coordinates.builder().coordinatesX(Integer.parseInt(coordinatesX.getText())).coordinatesY(Float.parseFloat(coordinatesY.getText())).build())
                            .personCreationDate(ZonedDateTime.now())
                            .token(Users.getUser().getToken())
                            .build();
                    if (command instanceof Update) {
                        person.setPersonId(Long.valueOf(UpdateButton.idText.getText()));
                        ((Update) command).setParameter(person);
                    }
                    if (command instanceof Add) {
                        ((Add) command).setPerson(person);
                    }
                    if (command instanceof AddIfMax) {
                        ((AddIfMax) command).setParameter(person);
                    }
                    wasAdded = true;
                } else {
                    log.error("Can't create person");
                    wasAdded = false;
                }
                LabText.clear();
                jFrame.dispose();
                wasClicked = true;
            });
            ok.setBounds(180, 440, 100, 30);
            jFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    wasClicked = true;
                    wasAdded = false;
                }
            });
            jPanel.add(ok);
            jPanel.setBackground(Color.GRAY);
            jFrame.setResizable(false);
            jFrame.setLocationRelativeTo(null);
            jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jFrame.setBounds(AbstractFrame.DIMENSION.width / 2 - 450, AbstractFrame.DIMENSION.height / 2 - 200, 500, 550);
            jFrame.add(jPanel);
            jFrame.setVisible(true);
        }
        return jFrame;
    }
}

