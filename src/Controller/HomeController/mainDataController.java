//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Controller.HomeController;

import Designs.Home.mainDataForm;
import Designs.Home.Main;
import Designs.MainData.BooksForm;
import Designs.MainData.GroupsForm;
import Designs.MainData.SubjectsForm;
import Designs.MainData.TeachersForm;
import Designs.MainData.YearsForm;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

// Description:
    // this class aims to provide a handler to the action of clicking on any button inside the mainData page.

public class mainDataController implements EventHandler<ActionEvent> {
    public mainDataController() {
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == mainDataForm.years) {
            YearsForm s = new YearsForm();
            Main.Center = YearsForm.root;
            Main.rootCenter.setContent(Main.Center);
            Main.Center.setAlignment(Pos.CENTER);
        }
        if (event.getSource() == mainDataForm.books) {
            BooksForm s = new BooksForm();
            Main.Center = BooksForm.root;
            Main.rootCenter.setContent(Main.Center);
            Main.Center.setAlignment(Pos.CENTER);
        }

        if (event.getSource() == mainDataForm.teachers) {
            TeachersForm s = new TeachersForm();
            Main.Center = TeachersForm.root;
            Main.rootCenter.setContent(Main.Center);
            Main.Center.setAlignment(Pos.CENTER);
        }

        if (event.getSource() == mainDataForm.groups) {
            GroupsForm s = new GroupsForm();
            Main.Center = GroupsForm.root;
            Main.rootCenter.setContent(Main.Center);
            Main.Center.setAlignment(Pos.CENTER);
        }

        if (event.getSource() == mainDataForm.subjects) {
            SubjectsForm s = new SubjectsForm();
            Main.Center = SubjectsForm.root;
            Main.rootCenter.setContent(Main.Center);
            Main.Center.setAlignment(Pos.CENTER);
        }

    }
}
