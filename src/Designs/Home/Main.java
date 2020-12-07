package Designs.Home;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
// Description:
    // this class aims to provide an implementation for designing the FIRST page that appears after the login process
    // The FIRST page contains the navigation bar (including mainData button and logout button) and an empty page
public class Main extends Application {
    Button mainData;    // the first button on the navigation bar
    Button logout;      // the second button on the navigation bar
    public static Scene scene;      // the resulted scene that will be placed on the primary stage
    public static BorderPane root;      // the root pane that will be fixed and placed behind each form
    public static TitledPane rootTop;       // to allow hiding the navigation bar
    public static ScrollPane rootCenter;      // to allow scrolling the Form if the contents bounds exceeded the screen's bounds
    public static ScrollPane scrollTop;          // to allow scrolling the navigation bar if the contents exceed the screen's bounds
    public static HBox Center;    // the specific form that will be displayed after clicking on its navigation bar button

    public Main() {
    }

    @Override
    public void start(Stage primaryStage) {
        //images
        ImageView mainDataImage = new ImageView("images/mainData.png");
        ImageView logoutImage = new ImageView("images/logout.png");
        
        VBox navBar = new VBox();   // to hold the Buttons box
        HBox Buttons = new HBox(22.0D);     //this box for holding buttons at the navigation bar
        
        Center = new HBox();      // this box for holding the form that will be displayed when the mainDataForm button is clicked
        this.mainData = new Button("البيانات الرئيسيه ", mainDataImage);
        this.mainData.setSkin(new Main.Trans(this.mainData));       // for setting a fade transition for the mainData button
        this.mainData.setOnAction((event) -> {

//the action of clicking on the mainData button (at the navigation bar) will result on setting the Center box to the root of the mainDataForm
            mainDataForm s = new mainDataForm();    // to implement the constructor of the mainDataForm(which includes the design)
            Center = mainDataForm.root;
            rootCenter.setContent(Center);  //scrollCenter is the pane that will be placed on the final resulted design
            Center.setAlignment(Pos.CENTER);  // to make the Center box at the center of the scrollCenter pane
        });

        this.logout = new Button(" خروج ", logoutImage);
        this.logout.setSkin(new Main.Trans(this.logout));           // for setting a fade transition for the logout button
        this.logout.setOnAction((event) -> {    // the event of clicking on the logout button will result on closing the program(stage)
            primaryStage.close();
        });
        
        navBar.setId("left-pane");        // this id will be used on the mainData.css file to style the navBar Box
        Buttons.setId("buttons-pane");      // this id will be used on the mainData.css file to style the buttons at the navigation bar
        Buttons.getChildren().addAll(this.mainData, this.logout);
        navBar.getChildren().addAll(Buttons);
        
// the root pane that will be fixed and displayed      
        root = new BorderPane();
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/mainStyle.css").toExternalForm());
        
//Top of the root border pane
        scrollTop = new ScrollPane(navBar); // to allow scrolling the navigation bar if the contents exceed the screen's bounds
        rootTop = new TitledPane("", scrollTop);     // to allow hiding the navigation bar

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(primScreenBounds.getHeight());
        i.setFitWidth(primScreenBounds.getWidth());
        i.setCache(true);
        Center.getChildren().add(new HBox(i));

//center of the root border pane
        rootCenter = new ScrollPane(Center);
//setting the top and center of the root border pane
        root.setTop(rootTop);
        root.setCenter(rootCenter);
// final operations:
        scene = new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight());
        primaryStage.setTitle("CourseCenter");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
// this class aims to define the action of the fade transition for the button on the navigation bar when the mouse is hovered on it.
    
    public class Trans extends ButtonSkin {
        public Trans(Button b) {
            super(b);
            FadeTransition ft = new FadeTransition(Duration.millis(1000.0D), b);
            ft.setFromValue(1.0D); // to starting the fading process from 1.0 value
            ft.setToValue(0.1D);    // to finish the fading process at 0.1 value
       //     ft.setCycleCount(-1);
            ft.setAutoReverse(true);    // to reverse the direction on the next cycle
            b.setOnMouseEntered((e) -> {
                ft.playFromStart(); //  to play the animation from the begining if the button is entered
            });
            b.setOnMouseExited((event) -> {
                ft.stop();      // stops the animation if the mouse removed from the button
            });
        }
    }
}
