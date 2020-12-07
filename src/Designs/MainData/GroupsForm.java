//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Designs.MainData;

import Database.database;
import Tools.tools;
import entities.group;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class GroupsForm {
    public static HBox root;
    StackPane main;
    Label tittle = new Label("المجموعات");
    Label idLabel = new Label("الكود");
    Label nameLabel = new Label("اسم المجموعه");
    Label subjectLabel = new Label("الماده");
    Label teacherLabel = new Label("المدرس");
    Label yearLabel = new Label("العام الدراسى");
    Label satardayLabel = new Label("السبت");
    Label sundayLabel = new Label("الاحد");
    Label thursdayLabel = new Label("الثلاثاء");
    Label tuesdayLabel = new Label("الخميس");
    Label wednesdayLabel = new Label("الاربعاء");
    Label mondayLabel = new Label("الاثنين");
    Label fridayLabel = new Label("الجمعه");
    Label dateLabel = new Label("اختار مواعيد المجموعه");
    public static TextField id;
    public static TextField name;
    public static TextField search;
    public static ComboBox teacher;
    public static ComboBox subject;
    public static ComboBox year;
    public static ComboBox yearSearch;
    public static ComboBox subjectSearch;
    public static TextField satarday_hour;
    public static TextField sunday_hour;
    public static TextField thursday_hour;
    public static TextField tuesday_hour;
    public static TextField wednesday_hour;
    public static TextField monday_hour;
    public static TextField friday_hour;
    public static CheckBox satarday;
    public static CheckBox sunday;
    public static CheckBox thursday;
    public static CheckBox tuesday;
    public static CheckBox wednesday;
    public static CheckBox monday;
    public static CheckBox friday;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<group> table;
    group y = new group();
    public static String year_id;

    public GroupsForm() {
        this.tittle.setId("tittle");
        year = new ComboBox();
        year.setPromptText("اختر العام الدراسى");
        year.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String year_id = database.getTableData("select year_id from years where year_name ='" + newValue + "'").items[0][0].toString();
                int y_id = Integer.parseInt(year_id);
                subject.getItems().clear();
                database.fillComboBoxData("subjects", "subject_name", " where year_id=" + y_id, subject);
            } catch (Exception var5) {
            }

        });
        yearSearch = new ComboBox();
        yearSearch.setPromptText("اختر العام الدراسى");
        yearSearch.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                year_id = database.getTableData("select year_id from years where year_name ='" + newValue + "'").items[0][0].toString();
                int y_id = Integer.parseInt(year_id);
                subjectSearch.getItems().clear();
                database.fillComboBoxData("subjects", "subject_name", " where year_id=" + y_id, subjectSearch);
                table.setItems(database.buildGroupsTable("select * from groups where year_id=" + y_id));
            } catch (Exception var4) {
            }

        });
        teacher = new ComboBox();
        teacher.setPromptText("اسم المدرس");
        subject = new ComboBox();
        subject.setPromptText("اختر اسم الماده");
        subjectSearch = new ComboBox();
        subjectSearch.setPromptText("اختر اسم الماده");
        subjectSearch.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String sub_name = subjectSearch.getSelectionModel().getSelectedItem().toString();
                String sub_id = database.getTableData("select subject_id from subjects where subject_name='" + sub_name + "' and year_id=" + year_id).items[0][0].toString();
                table.setItems(database.buildGroupsTable("select * from groups where subject_id=" + sub_id));
            } catch (Exception var5) {
            }

        });
        satarday = new CheckBox();
        sunday = new CheckBox();
        monday = new CheckBox();
        thursday = new CheckBox();
        wednesday = new CheckBox();
        tuesday = new CheckBox();
        friday = new CheckBox();
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.y.getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم المجموعه");
        search = new TextField();
        search.setPromptText("كلمه البحث او الكود");
        satarday_hour = new TextField();
        sunday_hour = new TextField();
        monday_hour = new TextField();
        thursday_hour = new TextField();
        wednesday_hour = new TextField();
        tuesday_hour = new TextField();
        friday_hour = new TextField();
        this.add = new Button("اضافه", new ImageView("images/add.png"));
        this.add.setOnAction((event) -> {
            this.setValues();
            this.y.add();
            this.clean();
        });
        this.update = new Button("تعديل", new ImageView("images/update.png"));
        this.update.setOnAction((event) -> {
            this.setValues();
            this.y.update();
            this.clean();
        });
        this.delete = new Button("مسح", new ImageView("images/delete.png"));
        this.delete.setOnAction((event) -> {
            if (tools.confirmMsg("سوف يتم مسح المجموعه بكل الطلاب الذين منها  من قاعده البيانات هل انت متأكد ؟")==0) {
                this.setValues();
                this.y.delete();
                this.clean();
            }

        });
        this.clear = new Button("تنظيف", new ImageView("images/clean.png"));
        this.clear.setId("last_btn");
        this.clear.setOnAction((event) -> {
            this.clean();
        });
        table = new TableView();
        table.setMaxHeight(580.0D);
        table.setMinHeight(580.0D);
        table.setMinWidth(300.0D);
        table.setItems(database.buildGroupsTable("select * from groups"));
        TableColumn<group, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(5.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("group_id"));
        TableColumn<group, String> nameCoulmn = new TableColumn("الاسم");
        nameCoulmn.setMinWidth(40.0D);
        nameCoulmn.setCellValueFactory(new PropertyValueFactory("group_name"));
        TableColumn<group, String> yearCoulmn = new TableColumn("كود العام الدراسى");
        yearCoulmn.setMinWidth(5.0D);
        yearCoulmn.setCellValueFactory(new PropertyValueFactory("year_id"));
        TableColumn<group, String> subCoulmn = new TableColumn("كود الماده");
        subCoulmn.setMinWidth(5.0D);
        subCoulmn.setCellValueFactory(new PropertyValueFactory("subject_id"));
        TableColumn<group, String> teacherCoulmn = new TableColumn("كود المدرس");
        teacherCoulmn.setMinWidth(5.0D);
        teacherCoulmn.setCellValueFactory(new PropertyValueFactory("instructor_id"));
        TableColumn<group, String> dateOneCoulmn = new TableColumn("الميعاد الاول");
        dateOneCoulmn.setMinWidth(20.0D);
        dateOneCoulmn.setCellValueFactory(new PropertyValueFactory("dateOne"));
        TableColumn<group, String> dateOne_HourCoulmn = new TableColumn("الساعه");
        dateOne_HourCoulmn.setMinWidth(5.0D);
        dateOne_HourCoulmn.setCellValueFactory(new PropertyValueFactory("dateOne_hour"));
        TableColumn<group, String> dateTwoCoulmn = new TableColumn("الميعاد الثانى");
        dateTwoCoulmn.setMinWidth(20.0D);
        dateTwoCoulmn.setCellValueFactory(new PropertyValueFactory("dateTwo"));
        TableColumn<group, String> dateTwo_hourCoulmn = new TableColumn("الساعه");
        dateTwo_hourCoulmn.setMinWidth(5.0D);
        dateTwo_hourCoulmn.setCellValueFactory(new PropertyValueFactory("dateTwo_hour"));
        table.getColumns().addAll(new TableColumn[]{idColumn, nameCoulmn, dateOneCoulmn, dateOne_HourCoulmn, dateTwoCoulmn, dateTwo_hourCoulmn, subCoulmn, yearCoulmn, teacherCoulmn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((group)table.getSelectionModel().getSelectedItem()).getGroup_id() + "");
                name.setText(((group)table.getSelectionModel().getSelectedItem()).getGroup_name());
                String year_id = ((group)table.getSelectionModel().getSelectedItem()).getYear_id() + "";
                String teacher_id = ((group)table.getSelectionModel().getSelectedItem()).getInstructor_id() + "";
                String subject_id = ((group)table.getSelectionModel().getSelectedItem()).getSubject_id() + "";
                String year_name = database.getTableData("select year_name from years where year_id=" + year_id).items[0][0].toString();
                String teacher_name = database.getTableData("select instructor_name from instructors where instructor_id=" + teacher_id).items[0][0].toString();
                String subject_name = database.getTableData("select subject_name from subjects where subject_id=" + subject_id).items[0][0].toString();
                year.getSelectionModel().select(year_name);
                teacher.getSelectionModel().select(teacher_name);
                subject.getSelectionModel().select(subject_name);
                String dayOne = ((group)table.getSelectionModel().getSelectedItem()).getDateOne();
                String dayOne_hour = ((group)table.getSelectionModel().getSelectedItem()).getDateOne_hour();
                String dayTwo = ((group)table.getSelectionModel().getSelectedItem()).getDateTwo();
                String dayTwo_hour = ((group)table.getSelectionModel().getSelectedItem()).getDateTwo_hour();
                if (dayOne.equals("السبت")) {
                    satarday.setSelected(true);
                    sunday.setSelected(false);
                    monday.setSelected(false);
                    satarday_hour.setText(dayOne_hour);
                    sunday_hour.setText("");
                    monday_hour.setText("");
                } else if (dayOne.equals("الاحد")) {
                    sunday.setSelected(true);
                    satarday.setSelected(false);
                    monday.setSelected(false);
                    sunday_hour.setText(dayOne_hour);
                    satarday_hour.setText("");
                    monday_hour.setText("");
                } else if (dayOne.equals("الاثنين")) {
                    monday.setSelected(true);
                    sunday.setSelected(false);
                    satarday.setSelected(false);
                    monday_hour.setText(dayOne_hour);
                    sunday_hour.setText("");
                    satarday_hour.setText("");
                }

                if (dayTwo.equals("الثلاثاء")) {
                    thursday.setSelected(true);
                    wednesday.setSelected(false);
                    tuesday.setSelected(false);
                    friday.setSelected(false);
                    tuesday_hour.setText("");
                    wednesday_hour.setText("");
                    thursday_hour.setText(dayTwo_hour);
                    friday_hour.setText("");
                } else if (dayTwo.equals("الاربعاء")) {
                    wednesday.setSelected(true);
                    tuesday.setSelected(false);
                    thursday.setSelected(false);
                    friday.setSelected(false);
                    wednesday_hour.setText(dayTwo_hour);
                    tuesday_hour.setText("");
                    thursday_hour.setText("");
                    friday_hour.setText("");
                } else if (dayTwo.equals("الخميس")) {
                    tuesday.setSelected(true);
                    wednesday.setSelected(false);
                    thursday.setSelected(false);
                    friday.setSelected(false);
                    thursday_hour.setText("");
                    wednesday_hour.setText("");
                    tuesday_hour.setText(dayTwo_hour);
                    friday_hour.setText("");
                } else if (dayTwo.equals("الجمعه")) {
                    friday.setSelected(true);
                    thursday.setSelected(false);
                    wednesday.setSelected(false);
                    tuesday.setSelected(false);
                    friday_hour.setText(dayTwo_hour);
                    thursday_hour.setText("");
                    wednesday_hour.setText("");
                    tuesday_hour.setText("");
                }

                this.add.setDisable(true);
                this.update.setDisable(false);
                this.delete.setDisable(false);
            } catch (Exception var12) {
            }

        });
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(primScreenBounds.getHeight() + 350.0D);
        i.setFitWidth(primScreenBounds.getWidth());
        HBox one = new HBox(10.0D, new Node[]{id, this.idLabel});
        HBox two = new HBox(10.0D, new Node[]{name, this.nameLabel});
        HBox h3 = new HBox(10.0D, new Node[]{year, this.yearLabel});
        HBox h4 = new HBox(10.0D, new Node[]{subject, this.subjectLabel});
        HBox h5 = new HBox(10.0D, new Node[]{teacher, this.teacherLabel});
        HBox h6 = new HBox(10.0D, new Node[]{this.dateLabel});
        HBox h7 = new HBox(10.0D, new Node[]{satarday_hour, satarday, this.satardayLabel});
        HBox h8 = new HBox(10.0D, new Node[]{sunday_hour, sunday, this.sundayLabel});
        HBox h9 = new HBox(10.0D, new Node[]{monday_hour, monday, this.mondayLabel});
        HBox h10 = new HBox(10.0D, new Node[]{thursday_hour, thursday, this.thursdayLabel});
        HBox h11 = new HBox(10.0D, new Node[]{wednesday_hour, wednesday, this.wednesdayLabel});
        HBox h12 = new HBox(10.0D, new Node[]{tuesday_hour, tuesday, this.tuesdayLabel});
        HBox h13 = new HBox(10.0D, new Node[]{friday_hour, friday, this.fridayLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13});
        allText_andLabels.setId("lbl_txt_box");
        HBox ButtonsRow = new HBox(10.0D, new Node[]{this.add, this.update, this.delete});
        ButtonsRow.setId("three_btn");
        VBox allButtons = new VBox(10.0D, new Node[]{ButtonsRow, this.clear});
        allButtons.setId("btnbox");
        VBox left = new VBox(20.0D, new Node[]{allText_andLabels, allButtons});
        ImageView s = new ImageView("images/search.png");
        s.setOnMouseClicked((event) -> {
            try {
                int x = Integer.parseInt(search.getText());
                table.setItems(database.buildGroupsTable("select * from groups where group_id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildGroupsTable("select * from groups where group_name like  '%" + search.getText() + "%';"));
                } catch (Exception var3) {
                    tools.ErrorBox("Error");
                }
            }

        });
        HBox searchRow = new HBox(5.0D, new Node[]{search, s, subjectSearch, yearSearch});
        searchRow.setPadding(new Insets(0.0D, 20.0D, 0.0D, 121.0D));
        HBox tableRow = new HBox(new Node[]{table});
        table.setId("table");
        VBox right = new VBox(5.0D, new Node[]{searchRow, tableRow});
        HBox center = new HBox(30.0D, new Node[]{left, right});
        center.setId("root");
        center.setOpacity(1.0D);
        HBox tittle_hbox = new HBox(new Node[]{this.tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        tittle_hbox.setMinWidth(primScreenBounds.getWidth());
        this.tittle.setId("tittle");
        this.tittle.setAlignment(Pos.CENTER);
        VBox all = new VBox(new Node[]{tittle_hbox, center});
        this.main = new StackPane(new Node[]{i, all});
        searchRow.setId("searchRow");
        tableRow.setId("tableRow");
        root = new HBox(new Node[]{this.main});
        this.clean();
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    void setValues() {
        try {
            this.y.setGroup_id(Integer.parseInt(id.getText()));
            this.y.setGroup_name(name.getText());
            String year_name = year.getSelectionModel().getSelectedItem().toString();
            String teacher_name = teacher.getSelectionModel().getSelectedItem().toString();
            String subject_name = subject.getSelectionModel().getSelectedItem().toString();
            String year_id = database.getTableData("select year_id from years where year_name ='" + year_name + "'").items[0][0].toString();
            String teacher_id = database.getTableData("select instructor_id from instructors where instructor_name ='" + teacher_name + "'").items[0][0].toString();
            String subject_id = database.getTableData("select subject_id from subjects where subject_name ='" + subject_name + "' and year_id="+year_id).items[0][0].toString();
            
            System.out.println("Groups Form : year_id="+year_id+"  and subject_id = "+subject_id);
            
            this.y.setInstructor_id(Integer.parseInt(teacher_id));
            this.y.setSubject_id(Integer.parseInt(subject_id));
            this.y.setYear_id(Integer.parseInt(year_id));
            if (satarday.isSelected()) {
                this.y.setDateOne("السبت");
                this.y.setDateOne_hour(satarday_hour.getText());
            } else if (sunday.isSelected()) {
                this.y.setDateOne("الاحد");
                this.y.setDateOne_hour(sunday_hour.getText());
            } else if (monday.isSelected()) {
                this.y.setDateOne("الاثنين");
                this.y.setDateOne_hour(monday_hour.getText());
            } else {
                this.y.setDateOne("");
                this.y.setDateOne_hour("");
            }

            if (thursday.isSelected()) {
                this.y.setDateTwo("الثلاثاء");
                this.y.setDateTwo_hour(thursday.getText());
            } else if (wednesday.isSelected()) {
                this.y.setDateTwo("الاربعاء");
                this.y.setDateTwo_hour(wednesday_hour.getText());
            } else if (tuesday.isSelected()) {
                this.y.setDateTwo("الخميس");
                this.y.setDateTwo_hour(tuesday_hour.getText());
            } else if (friday.isSelected()) {
                this.y.setDateTwo("الجمعه");
                this.y.setDateTwo_hour(friday_hour.getText());
            }
        } catch (Exception var7) {
        }

    }

    void clean() {
        table.setItems(database.buildGroupsTable("select * from groups"));
        id.setText(this.y.getAutoNumber());
        name.setText("");
        search.setText("");
        year.getItems().clear();
        teacher.getItems().clear();
        subject.getItems().clear();
        database.fillComboBoxData("years", "year_name", " ;", year);
        database.fillComboBoxData("years", "year_name", " ;", yearSearch);
        database.fillComboBoxData("instructors", "instructor_name", " ;", teacher);
        satarday_hour.setText("");
        sunday_hour.setText("");
        monday_hour.setText("");
        thursday_hour.setText("");
        wednesday_hour.setText("");
        tuesday_hour.setText("");
        friday_hour.setText("");
        friday.setSelected(false);
        thursday.setSelected(false);
        wednesday.setSelected(false);
        tuesday.setSelected(false);
        monday.setSelected(false);
        sunday.setSelected(false);
        satarday.setSelected(false);
        this.add.setDisable(false);
        this.update.setDisable(true);
        this.delete.setDisable(true);
    }
}
