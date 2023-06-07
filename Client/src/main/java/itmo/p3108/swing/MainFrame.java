package itmo.p3108.swing;

import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.command.Exit;
import itmo.p3108.model.Person;
import itmo.p3108.util.ServerChanel;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class MainFrame extends AbstractFrame {
    public static final DefaultTableModel model = new DefaultTableModel();
    volatile public static itmo.p3108.swing.Button button;
    private final List<itmo.p3108.swing.Button> buttonCreators = new LinkedList<>();


    public MainFrame() {
        super.createPanel();
        super.createFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(jPanel);
        jFrame.setBounds(DIMENSION.width / 2 - 550, DIMENSION.height / 2 - 350, 1200, 700);

    }


    public static void main(String[] args) {
        new MainFrame().createMainFrame(null, "d");
    }

    public static void clear() {
        if (MainFrame.model.getRowCount() > 0) {
            for (int i = MainFrame.model.getRowCount() - 1; i > -1; i--) {
                MainFrame.model.removeRow(i);
            }
        }
    }

    public static void addElements() {
        for (Person p : ServerChanel.list) {
            MainFrame.model.addRow(new Object[]{p.getPersonId(), p.getPersonName(), p.getPersonHeight(), LocalDateAdapter.getInstance().marshal(p.getPersonBirthday()), p.getPersonEyeColor().getName().substring(2), p.getPersonNationality().getName().substring(2), p.getCoordinates().getCoordinatesX(), p.getCoordinates().getCoordinatesY(), p.getLocation().getLocationX(), p.getLocation().getLocationY(), p.getLocation().getLocationZ(), p.getLocation().getLocationName()});

        }
    }

    public void createMainFrame(List<Person> list, String userName) {

        log.info("try to create MainFrame");
        JTable upperTable = new JTable();
        JScrollPane paneUpperTable = new JScrollPane(upperTable);
        upperTable.setFillsViewportHeight(true);

        JLabel user = new JLabel(userName);
        user.setForeground(Color.CYAN);
        user.setBounds(40, 1, 70, 31);
        upperTable.add(user);

        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("D:\\IntelliJ IDEA 2022.2.3\\Lab7\\Client\\src\\main\\resources\\img.jpg"));
        picture.setBounds(1, 1, 30, 30);
        picture.setBackground(Color.GRAY);
        jPanel.add(picture);


        itmo.p3108.swing.Button buttonCreator1 = new itmo.p3108.swing.Button("Exit", "Выход");
        buttonCreator1.makeInvisible();
        JButton exit = buttonCreator1.getButton();
        exit.setForeground(Color.CYAN);
        exit.setBounds(1100, 1, 80, 31);
        exit.addActionListener(e -> new Exit().prepare());
        upperTable.add(exit);

        paneUpperTable.setBounds(1, 1, 1200, 30);
        upperTable.setBackground(Color.GRAY);
        jPanel.add(paneUpperTable);

        jPanel.setLayout(null);
        AddButton buttonCreator2 = new AddButton();
        JButton add = buttonCreator2.getButton();
        add.addActionListener(e -> {
                    buttonCreator2.buttonAction();
                    button = buttonCreator2;
                }
        );
        add.setBounds(520, 130, 108, 30);

        InfoButton buttonCreator3 = new InfoButton();
        JButton info = buttonCreator3.getButton();
        info.addActionListener(e -> button = buttonCreator3
        );
        info.setBounds(638, 130, 108, 30);

        UpdateButton buttonCreator4 = new UpdateButton();

        JButton update = buttonCreator4.getButton();
        update.addActionListener(l -> {
            buttonCreator4.buttonAction();
            button = buttonCreator4;
        });
        update.setBounds(756, 130, 108, 30);

        ClearButton buttonCreator5 = new ClearButton();
        JButton clear = buttonCreator5.getButton();
        clear.setBounds(874, 130, 108, 30);
        clear.addActionListener(e -> button = buttonCreator5);
        AddIFMaxButton buttonCreator6 = new AddIFMaxButton();

        JButton addIfMax = buttonCreator6.getButton();
        addIfMax.addActionListener(e -> {
            buttonCreator6.buttonAction();
            button = buttonCreator6;
        });
        addIfMax.setBounds(992, 130, 125, 30);
        Button next = new NextButton();
        next.makeInvisible();
        JButton button1 = next.getButton();
        button1.setForeground(Color.RED);
        button1.setBounds(1110, 620, 80, 30);
        jPanel.add(button1);
        jPanel.add(addIfMax);
        jPanel.add(update);
        jPanel.add(clear);
        jPanel.add(info);
        jPanel.add(add);

        JTable table = new JTable(model);
        table.setBackground(Color.BLACK);

        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Height");
        model.addColumn("Birthday");
        model.addColumn("EyeColor");
        model.addColumn("Nationality");
        model.addColumn("coordinateX");
        model.addColumn("coordinateY");
        model.addColumn("locationX");
        model.addColumn("locationY");
        model.addColumn("locationZ");
        model.addColumn("locationName");
        table.setForeground(Color.RED);
        for (Person p : list) {
            model.addRow(new Object[]{p.getPersonId(), p.getPersonName(), p.getPersonHeight(), LocalDateAdapter.getInstance().marshal(p.getPersonBirthday()), p.getPersonEyeColor().getName().substring(2), p.getPersonNationality().getName().substring(2), p.getCoordinates().getCoordinatesX(), p.getCoordinates().getCoordinatesY(), p.getLocation().getLocationX(), p.getLocation().getLocationY(), p.getLocation().getLocationZ(), p.getLocation().getLocationName()});
        }
        buttonCreators.add(buttonCreator1);
        buttonCreators.add(buttonCreator2);
        buttonCreators.add(buttonCreator3);
        buttonCreators.add(buttonCreator4);
        buttonCreators.add(buttonCreator5);
        buttonCreators.add(buttonCreator6);
        JMenu language = new JMenu("Language");
        language.setForeground(Color.CYAN);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(language);
        JMenuItem english = language.add(new JMenuItem("English"));
        JMenuItem russian = language.add(new JMenuItem("Russian"));

        english.addActionListener(e -> {
            buttonCreators.forEach(x -> x.getButton().setText(x.getEnglish()));
            language.setText("Language");
            english.setText("English");
            russian.setText("Russian");
            jFrame.revalidate();

        });
        russian.addActionListener(e -> {
            buttonCreators.forEach(x -> x.getButton().setText(x.getRussian()));
            language.setText("Язык");
            english.setText("Англ");
            russian.setText("Рус");
            jFrame.revalidate();
        });

        jMenuBar.setBounds(120, 1, 70, 30);
        jMenuBar.setOpaque(false);
        jMenuBar.setBorderPainted(false);
        upperTable.add(jMenuBar);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setBounds(100, 200, 920, 300);

        jPanel.setBackground(Color.lightGray);
        jPanel.add(scrollPane);
        jFrame.add(jPanel);
        jFrame.revalidate();
        jFrame.setVisible(true);
        log.info("create MainFrame successfully");
        JLabel car = new JLabel(new ImageIcon("D:\\IntelliJ IDEA 2022.2.3\\Lab7\\Client\\src\\main\\resources\\MainFrame.jpg"));
        car.setBounds(0, 0, 1200, 700);
        jPanel.add(car);
    }
}
