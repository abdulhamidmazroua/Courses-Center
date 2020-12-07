//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Designs.MainData;

import Database.database;
import Tools.tools;
import entities.subjects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

public class SubjectsForm {
    public static HBox root;
    StackPane main;
    Label tittle = new Label("المواد التعليميه");
    Label idLabel = new Label("الكود");
    Label nameLabel = new Label("الاسم");
    Label yearLabel = new Label("السنه الدراسيه");
    Label priceLabel = new Label("السعر");
    public static TextField id;
    public static TextField name;
    public static TextField price;
    public static TextField search;
    public static ComboBox year;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<subjects> table;
    subjects y = new subjects();

    public SubjectsForm() {
        this.tittle.setId("tittle");
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.y.getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم الماده");
        price = new TextField();
        price.setPromptText("سعر الماده");
        search = new TextField();
        search.setPromptText("كلمه البحث او الكود");
        year = new ComboBox();
        year.setPromptText("اختر العام الدراسى");
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
            if (tools.confirmMsg("سوف يتم مسح الماده بكل المجموعات الذين منها  من قاعده البيانات هل انت متأكد ؟")==0) {
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
        table.setMaxHeight(290.0D);
        table.setMinWidth(280.0D);
        table.setItems(database.buildSubjectsTable("select * from subjects"));
        TableColumn<subjects, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("subject_id"));
        TableColumn<subjects, String> nameCoulmn = new TableColumn("الاسم");
        nameCoulmn.setMinWidth(40.0D);
        nameCoulmn.setCellValueFactory(new PropertyValueFactory("subject_name"));
        TableColumn<subjects, String> priceCoulmn = new TableColumn("السعر");
        priceCoulmn.setMinWidth(40.0D);
        priceCoulmn.setCellValueFactory(new PropertyValueFactory("subject_price"));
        TableColumn<subjects, String> yearCoulmn = new TableColumn("كود العام الدراسى");
        yearCoulmn.setMinWidth(40.0D);
        yearCoulmn.setCellValueFactory(new PropertyValueFactory("year_id"));
        table.getColumns().addAll(new TableColumn[]{idColumn, nameCoulmn, priceCoulmn, yearCoulmn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((subjects)table.getSelectionModel().getSelectedItem()).getSubject_id() + "");
                name.setText(((subjects)table.getSelectionModel().getSelectedItem()).getSubject_name());
                price.setText(((subjects)table.getSelectionModel().getSelectedItem()).getSubject_price() + "");
                String year_id = ((subjects)table.getSelectionModel().getSelectedItem()).getYear_id() + "";
                String year_name = database.getTableData("select year_name from years where year_id =" + year_id).items[0][0].toString();
                year.getSelectionModel().select(year_name);
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
        HBox three = new HBox(10.0D, new Node[]{price, this.priceLabel});
        HBox four = new HBox(10.0D, new Node[]{year, this.yearLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, three, four});
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
                table.setItems(database.buildSubjectsTable("select * from subjects where subject_id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildSubjectsTable("select * from subjects where subject_name like  '%" + search.getText() + "%';"));
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
            this.y.setSubject_id(Integer.parseInt(id.getText()));
            this.y.setSubject_name(name.getText());
            this.y.setSubject_price(Double.parseDouble(price.getText()));
            String year_name = year.getSelectionModel().getSelectedItem().toString();
            System.out.println("year name = : "+year_name);
            String year_id = database.getTableData("select year_id from years where year_name ='" + year_name + "';").items[0][0].toString();
            this.y.setYear_id(Integer.parseInt(year_id));
        } catch (Exception var3) {
        }

    }

    void clean() {
        table.setItems(database.buildSubjectsTable("select * from subjects"));
        id.setText(this.y.getAutoNumber());
        name.setText("");
        price.setText("");
        search.setText("");
        year.getItems().clear();
        database.fillComboBoxData("years", "year_name", " ;", year);
        this.add.setDisable(false);
        this.update.setDisable(true);
        this.delete.setDisable(true);
    }
}
