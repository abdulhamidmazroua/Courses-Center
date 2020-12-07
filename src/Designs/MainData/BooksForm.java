//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Designs.MainData;

import Database.database;
import Tools.tools;
import entities.books;
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

public class BooksForm {
    public static HBox root;
    StackPane main;
    Label tittle = new Label("الكتب و المذكرات التعليميه");
    Label idLabel = new Label("الكود");
    Label nameLabel = new Label("الاسم");
    Label priceLabel = new Label("السعر");
    Label yearLabel = new Label("العام الدراسى");
    Label subjectLabel = new Label("اسم الماده");
    Label booksNoLabel = new Label("الكميه");
    public static TextField id;
    public static TextField name;
    public static TextField search;
    public static TextField price;
    public static TextField booksNo;
    public static ComboBox year;
    public static ComboBox subject;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<books> table;
    books y = new books();

    public BooksForm() {
        this.tittle.setId("tittle");
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.y.getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم الكتاب");
        booksNo = new TextField();
        booksNo.setPromptText("الكميه المتاحه");
        price = new TextField();
        price.setPromptText("سعر الكتاب");
        search = new TextField();
        search.setPromptText("كلمه البحث او الكود");
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
        subject = new ComboBox();
        subject.setPromptText("اسم الماده");
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
            if (tools.confirmMsg("سوف يتم مسح الشركه بكل الموردين الذين منها  من قاعده البيانات هل انت متأكد ؟")==0) {
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
        table.setMaxHeight(300.0D);
        table.setMinWidth(300.0D);
        table.setItems(database.buildBooksTable("select * from books"));
        TableColumn<books, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("book_id"));
        TableColumn<books, String> priceCoulmn = new TableColumn("السعر");
        priceCoulmn.setMinWidth(40.0D);
        priceCoulmn.setCellValueFactory(new PropertyValueFactory("book_price"));
        TableColumn<books, String> yearCoulmn = new TableColumn("كود العام الدراسى");
        yearCoulmn.setMinWidth(40.0D);
        yearCoulmn.setCellValueFactory(new PropertyValueFactory("year_id"));
        TableColumn<books, String> nameCoulmn = new TableColumn("الاسم");
        nameCoulmn.setMinWidth(40.0D);
        nameCoulmn.setCellValueFactory(new PropertyValueFactory("book_name"));
        TableColumn<books, String> subjectCoulmn = new TableColumn("كود الماده");
        subjectCoulmn.setMinWidth(40.0D);
        subjectCoulmn.setCellValueFactory(new PropertyValueFactory("subject_id"));
        TableColumn<books, String> booksNoCoulmn = new TableColumn("الكميه");
        booksNoCoulmn.setMinWidth(40.0D);
        booksNoCoulmn.setCellValueFactory(new PropertyValueFactory("booksNo"));
        table.getColumns().addAll(new TableColumn[]{idColumn, nameCoulmn, priceCoulmn, yearCoulmn, subjectCoulmn, booksNoCoulmn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((books)table.getSelectionModel().getSelectedItem()).getBook_id() + "");
                name.setText(((books)table.getSelectionModel().getSelectedItem()).getBook_name());
                booksNo.setText(((books)table.getSelectionModel().getSelectedItem()).getBooksNo());
                price.setText(((books)table.getSelectionModel().getSelectedItem()).getBook_price() + "");
                String sub_id = ((books)table.getSelectionModel().getSelectedItem()).getSubject_id() + "";
                String year_id = ((books)table.getSelectionModel().getSelectedItem()).getYear_id() + "";
                String sub_name = database.getTableData("select subject_name from subjects where subject_id=" + sub_id).items[0][0].toString();
                String year_name = database.getTableData("select year_name from years where year_id=" + year_id).items[0][0].toString();
                year.getSelectionModel().select(year_name);
                subject.getSelectionModel().select(sub_name);
                this.add.setDisable(true);
                this.update.setDisable(false);
                this.delete.setDisable(false);
            } catch (Exception var6) {
            }

        });
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(primScreenBounds.getHeight());
        i.setFitWidth(primScreenBounds.getWidth());
        HBox one = new HBox(10.0D, new Node[]{id, this.idLabel});
        HBox two = new HBox(10.0D, new Node[]{name, this.nameLabel});
        HBox three = new HBox(10.0D, new Node[]{price, this.priceLabel});
        HBox t = new HBox(10.0D, new Node[]{booksNo, this.booksNoLabel});
        HBox four = new HBox(10.0D, new Node[]{year, this.yearLabel});
        HBox five = new HBox(10.0D, new Node[]{subject, this.subjectLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, three, t, four, five});
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
                table.setItems(database.buildBooksTable("select * from books where book_id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildBooksTable("select * from books where book_name like  '%" + search.getText() + "%';"));
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
            this.y.setBook_id(Integer.parseInt(id.getText()));
            this.y.setBook_name(name.getText());
            this.y.setBooksNo(booksNo.getText());
            this.y.setBook_price(Double.parseDouble(price.getText()));
            String year_name = year.getSelectionModel().getSelectedItem().toString();
            String subject_name = subject.getSelectionModel().getSelectedItem().toString();
            String year_id = database.getTableData("select year_id from years where year_name='" + year_name + "';").items[0][0].toString();
            String sub_id = database.getTableData("select subject_id from subjects where subject_name='" + subject_name + "' and year_id=" + year_id + ";").items[0][0].toString();
            this.y.setYear_id(Integer.parseInt(year_id));
            this.y.setSubject_id(Integer.parseInt(sub_id));
        } catch (Exception var5) {
        }

    }

    void clean() {
        table.setItems(database.buildBooksTable("select * from books"));
        id.setText(this.y.getAutoNumber());
        name.setText("");
        booksNo.setText("");
        search.setText("");
        price.setText("");
        year.getItems().clear();
        subject.getItems().clear();
        database.fillComboBoxData("years", "year_name", " ;", year);
        this.add.setDisable(false);
        this.update.setDisable(true);
        this.delete.setDisable(true);
    }
}
