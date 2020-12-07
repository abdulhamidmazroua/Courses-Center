//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Designs.MainData;

import Database.database;
import Tools.tools;
import entities.instructor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class TeachersForm {
    public static HBox root;
    StackPane main;
    Label tittle = new Label("المحاضرين و المدرسين");
    Label idLabel = new Label("الكود");
    Label nameLabel = new Label("الاسم");
    Label phoneLabel = new Label("الهاتف");
    Label addressLabel = new Label("العنوان");
    Label salaryLabel = new Label("المرتب");
    Label subjectsLabel = new Label("الماده");
    public static TextField id;
    public static TextField name;
    public static TextField phone;
    public static TextField salary;
    public static TextField search;
    public static TextArea address;
    public static ComboBox subjects;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<instructor> table;
    instructor y = new instructor();

    public TeachersForm() {
        this.tittle.setId("tittle");
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.y.getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم المدرس");
        phone = new TextField();
        phone.setPromptText("هاتف المدرس");
        salary = new TextField();
        salary.setPromptText("مرتب المدرس");
        address = new TextArea();
        name.setPromptText("عنوان المدرس");
        search = new TextField();
        search.setPromptText("كلمه البحث او الكود");
        subjects = new ComboBox();
        subjects.setPromptText("اختر الماده");
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
            if (tools.confirmMsg("سوف يتم مسح المدرس هل انت موافق ؟")==0) {
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
        table.setMaxHeight(400.0D);
        table.setMinWidth(300.0D);
        table.setItems(database.buildTeachersTable("select * from instructors"));
        TableColumn<instructor, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("instructor_id"));
        TableColumn<instructor, String> nameCoulmn = new TableColumn("الاسم");
        nameCoulmn.setMinWidth(30.0D);
        nameCoulmn.setCellValueFactory(new PropertyValueFactory("instructor_name"));
        TableColumn<instructor, String> addressCoulmn = new TableColumn("العنوان");
        addressCoulmn.setMinWidth(30.0D);
        addressCoulmn.setCellValueFactory(new PropertyValueFactory("instructor_address"));
        TableColumn<instructor, String> phoneCoulmn = new TableColumn("الهاتف");
        phoneCoulmn.setMinWidth(30.0D);
        phoneCoulmn.setCellValueFactory(new PropertyValueFactory("instructor_phone"));
        TableColumn<instructor, String> salaryCoulmn = new TableColumn("المرتب");
        salaryCoulmn.setMinWidth(30.0D);
        salaryCoulmn.setCellValueFactory(new PropertyValueFactory("instructor_salary"));
        TableColumn<instructor, String> snb_idCoulmn = new TableColumn("كود الماده");
        snb_idCoulmn.setMinWidth(30.0D);
        snb_idCoulmn.setCellValueFactory(new PropertyValueFactory("subject_id"));
        table.getColumns().addAll(new TableColumn[]{idColumn, nameCoulmn, phoneCoulmn, salaryCoulmn, snb_idCoulmn, addressCoulmn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((instructor)table.getSelectionModel().getSelectedItem()).getInstructor_id() + "");
                name.setText(((instructor)table.getSelectionModel().getSelectedItem()).getInstructor_name());
                phone.setText(((instructor)table.getSelectionModel().getSelectedItem()).getInstructor_phone());
                address.setText(((instructor)table.getSelectionModel().getSelectedItem()).getInstructor_address());
                salary.setText(((instructor)table.getSelectionModel().getSelectedItem()).getInstructor_salary() + "");
                String sub_id = ((instructor)table.getSelectionModel().getSelectedItem()).getSubject_id() + "";
                String sub_name = database.getTableData("select subject_name from subjects where subject_id =" + sub_id).items[0][0].toString();
                subjects.getSelectionModel().select(sub_name);
                this.add.setDisable(true);
                this.update.setDisable(false);
                this.delete.setDisable(false);
            } catch (Exception var4) {
            }

        });
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(primScreenBounds.getHeight());
        i.setFitWidth(primScreenBounds.getWidth());
        HBox one = new HBox(10.0D, new Node[]{id, this.idLabel});
        HBox two = new HBox(10.0D, new Node[]{name, this.nameLabel});
        HBox three = new HBox(10.0D, new Node[]{phone, this.phoneLabel});
        HBox four = new HBox(10.0D, new Node[]{salary, this.salaryLabel});
        HBox five = new HBox(10.0D, new Node[]{address, this.addressLabel});
        HBox seven = new HBox(10.0D, new Node[]{subjects, this.subjectsLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, three, four, five, seven});
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
                table.setItems(database.buildTeachersTable("select * from instructors where instructor_id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildTeachersTable("select * from instructors where instructor_name like  '%" + search.getText() + "%';"));
                } catch (Exception var3) {
                    tools.ErrorBox("Error");
                }
            }

        });
        HBox searchRow = new HBox(5.0D, new Node[]{search, s});
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
            this.y.setInstructor_id(Integer.parseInt(id.getText()));
            this.y.setInstructor_name(name.getText());
            this.y.setInstructor_address(address.getText());
            this.y.setInstructor_phone(phone.getText());
            this.y.setInstructor_salary(Double.parseDouble(salary.getText()));
            String sub_name = subjects.getSelectionModel().getSelectedItem().toString();
            String sub_id = database.getTableData("select subject_id from subjects where subject_name = '" + sub_name + "'").items[0][0].toString();
            this.y.setSubject_id(Integer.parseInt(sub_id));
        } catch (Exception var3) {
        }

    }

    void clean() {
        table.setItems(database.buildTeachersTable("select * from instructors"));
        id.setText(this.y.getAutoNumber());
        name.setText("");
        phone.setText("");
        address.setText("");
        salary.setText("");
        search.setText("");
        subjects.getItems().clear();
        database.fillComboBoxData("subjects", "subject_name", " ;", subjects);
        this.add.setDisable(false);
        this.update.setDisable(true);
        this.delete.setDisable(true);
    }
}
