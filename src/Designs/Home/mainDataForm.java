
package Designs.Home;

import Controller.HomeController.mainDataController;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

// Description:
    // this class aims to provide a design for the mainDataForm pane and palces it on the root HBox

public class mainDataForm {
    public static HBox root;    // the resulted box of the mainDataForm
    public static Button years;
    public static Button subjects;
    public static Button groups;
    public static Button teachers;
    public static Button books;
    
    public mainDataForm() {
        mainDataController mainDataController = new mainDataController();   // the event handler for the action of clicking on {years, subjects, groups, teachers, books}
            // images:
        ImageView yearsImage = new ImageView("images/years.png");
        ImageView supjectsImage = new ImageView("images/subjects.png");
        ImageView teachersImage = new ImageView("images/teachers.png");
        ImageView groupsImage = new ImageView("images/groups.png");
        ImageView booksImage = new ImageView("images/books.png");
            // subjects form:
        subjects = new Button("المواد", supjectsImage);
        subjects.setContentDisplay(ContentDisplay.TOP);
        subjects.setOnAction(mainDataController);
            // groups form
        groups = new Button("المجموعات", groupsImage);
        groups.setContentDisplay(ContentDisplay.TOP);
        groups.setOnAction(mainDataController);
        // second row for subjects and groups
        HBox SecondRow = new HBox(subjects, groups);
        SecondRow.setSpacing(15.0D);
        SecondRow.setAlignment(Pos.CENTER);     // to centeralize the HBox on the middle of the page
        
            // years form:
        years = new Button("الصفوف الدراسيه", yearsImage);
        years.setContentDisplay(ContentDisplay.TOP);    // content(the image) will be placed at the top of the label
        years.setOnAction(mainDataController);          // to set the source event to years [event.getSource = mainDataForm.years]
            //  teachers form:
        teachers = new Button("المدرسيين", teachersImage);
        teachers.setContentDisplay(ContentDisplay.TOP);
        teachers.setOnAction(mainDataController);
        //third row for years and teachers
        HBox thirdRow = new HBox(years, teachers);
        thirdRow.setAlignment(Pos.CENTER);
        thirdRow.setSpacing(15.0D);
        
            // books from:
        books = new Button("الكتب", booksImage);
        books.setContentDisplay(ContentDisplay.TOP);
        books.setOnAction(mainDataController);
        //fourth row for books only
        HBox Row4 = new HBox(books);
        Row4.setAlignment(Pos.CENTER);
        Row4.setSpacing(15.0D);
        books.setAlignment(Pos.CENTER);
        
        //vertical box for the rows
        VBox main = new VBox(SecondRow, thirdRow, Row4);
        main.setSpacing(30.0D);
        main.setAlignment(Pos.CENTER);  // to centeralize the VBox at the middle of the page
        main.setId("Buttons");      // this id will be used on the mainData.css file to style the main VBox
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();   // used to get the bounds of your screen
        ImageView i = new ImageView("images/back.jpg");     // the main background
        i.setFitHeight(primScreenBounds.getHeight());
        i.setFitWidth(primScreenBounds.getWidth());
        StackPane r = new StackPane(i, main);   // this pane is the final result for the main data form that will be palaced on the root
        root = new HBox(r); // to place the mainDataForm pane on the root of the mainDataForm page
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/mainData.css").toExternalForm());
    }
}
