//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Tools;
// this class aims to provide the messages of successing or failing on the sql statement
import javax.swing.JOptionPane;

public class tools {
    public tools() {
    }

    public static void InformationBox(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void WarningBox(String message) {
        JOptionPane.showMessageDialog(null, message);

    }
// errors that happens due to sql exception
    public static void ErrorBox(String message) {
        JOptionPane.showMessageDialog(null, message);

    }

    public static int confirmMsg(String message) {
      return  JOptionPane.showConfirmDialog(null, message);

    }

    public static Object inputBox(String tittle) {
             return JOptionPane.showInputDialog(null, tittle);

    }

    public static class table {
        public int columns;
        public Object[][] items;

        public table(int columns) {
            this.columns = columns;
            this.items = new Object[0][columns];
        }

        public void addNewRow(Object[] row) {
            Object[][] tempItems = this.items;
            this.items = new Object[this.items.length + 1][this.columns];

            for(int x = 0; x < tempItems.length; ++x) {
                this.items[x] = tempItems[x];
            }

            this.items[this.items.length - 1] = row;
        }
    }
}
