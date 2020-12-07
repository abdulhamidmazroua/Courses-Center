//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Designs;

import Database.database;
import Designs.Home.Main;
import Tools.tools;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Button logout;
    public static Stage s;

    public Login() {
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            s = new Stage();
            HBox newLoginForm = (HBox)FXMLLoader.load(this.getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(newLoginForm, 700.0D, 500.0D);
            primaryStage.centerOnScreen();
            primaryStage.setTitle("تسجيل الدخول  ");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("logo.png"));
            s = primaryStage;
        } catch (IOException var4) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loginMethod() {
        if (database.checkUserAndPassword(this.username.getText(), this.password.getText())) {
            (new Main()).start(new Stage());
            s.close();
        } else {
            tools.ErrorBox("من فضلك ادخل اسم مستخدم و كلمه مرور صحيحه !");
        }

    }

    public void logoutMethod() {
        s.close();
    }
}
