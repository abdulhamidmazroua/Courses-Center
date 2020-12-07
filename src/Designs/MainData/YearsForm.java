//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Designs.MainData;

import Database.database;
import Tools.tools;
import entities.year;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

public class YearsForm {
    public static HBox root;
    StackPane main;
    Label tittle = new Label("الدفعات الدراسيه");
    Label idLabel = new Label("الكود");
    Label nameLabel = new Label("الاسم");
    public static TextField id;
    public static TextField name;
    public static TextField search;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<year> table;
    year y = new year();

    public YearsForm() {
        this.tittle.setId("tittle");
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.y.getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم الدفعه");
        search = new TextField();
        search.setPromptText("كلمه البحث او الكود");
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
            if (tools.confirmMsg("سوف يتم مسح الدفعه بكل المواد و الطلبه الذين منها  من قاعده البيانات هل انت متأكد ؟")==0) {
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
        table.setMaxHeight(180.0D);
        table.setMinWidth(300.0D);
        table.setItems(database.buildYearsTable("select * from years"));    // to return an observable list<year> and be set as table items
        TableColumn<year, Integer> idColumn = new TableColumn("الكود"); // to create a column specifying its label and type of value
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("year_id"));  // to specify the value of each cell in this column
        TableColumn<year, String> nameCoulmn = new TableColumn("الاسم");
        nameCoulmn.setMinWidth(250.0D);
        nameCoulmn.setCellValueFactory(new PropertyValueFactory("year_name"));
        table.getColumns().addAll(new TableColumn[]{idColumn, nameCoulmn}); // to add the column to the table
        table.setOnMouseClicked((event) -> {
            try {
                // to set the id and name on the text field as the selected cell
                id.setText(((year)table.getSelectionModel().getSelectedItem()).getYear_id() + "");
                name.setText(((year)table.getSelectionModel().getSelectedItem()).getYear_name());
                this.add.setDisable(true);
                this.update.setDisable(false);
                this.delete.setDisable(false);
            } catch (Exception var3) {
            }

        });
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(primScreenBounds.getHeight());
        i.setFitWidth(primScreenBounds.getWidth());
        HBox one = new HBox(10.0D, id, this.idLabel);
        HBox two = new HBox(10.0D,name, this.nameLabel);
        VBox allText_andLabels = new VBox(20.0D, one, two);
        allText_andLabels.setId("lbl_txt_box");     // to style on css
        HBox ButtonsRow = new HBox(10.0D, this.add, this.update, this.delete);
        ButtonsRow.setId("three_btn");  // to style on css
        VBox allButtons = new VBox(10.0D, ButtonsRow, this.clear);
        allButtons.setId("btnbox"); // to style on css
        VBox left = new VBox(20.0D, allText_andLabels, allButtons);
        ImageView s = new ImageView("images/search.png");
        s.setOnMouseClicked((event) -> {
            try {
                int x = Integer.parseInt(search.getText());
                table.setItems(database.buildYearsTable("select * from years where year_id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildYearsTable("select * from years where year_name like  '%" + search.getText() + "%';"));
                } catch (Exception var3) {
                    tools.ErrorBox("Error");
                }
            }

        });
        HBox searchRow = new HBox(5.0D, search, s);
        searchRow.setPadding(new Insets(0.0D, 20.0D, 0.0D, 121.0D));
        HBox tableRow = new HBox(table);
        table.setId("table");   // to style on css
        VBox right = new VBox(5.0D, searchRow, tableRow);
        HBox center = new HBox(30.0D, left, right);
        center.setId("root");   // to style on css
        center.setOpacity(1.0D);
        HBox tittle_hbox = new HBox(this.tittle);
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
            this.y.setYear_id(Integer.parseInt(id.getText()));
            this.y.setYear_name(name.getText());
        } catch (Exception var2) {
        }

    }

    void clean() {
        table.setItems(database.buildYearsTable("select * from years"));
        id.setText(this.y.getAutoNumber());
        name.setText("");
        search.setText("");
        this.add.setDisable(false);
        this.update.setDisable(true);
        this.delete.setDisable(true);
    }
}
