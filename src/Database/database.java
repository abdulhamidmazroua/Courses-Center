//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Database;

import Designs.Home.group_S;
import Tools.tools;
import Tools.tools.table;
import entities.books;
import entities.group;
import entities.instructor;
import entities.subjects;
import entities.year;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

public class database {
    public static Connection con;
    private static final String DB_NAME = "course";
    private static String url;
    public static ImageView backGround;

    public database() {
    }

    private static String setURL() {
        url = "jdbc:mysql://localhost:3306/course?autoReconnect=true&amp;useEncoding=true&amp;characterEncoding=UTF-8";
        return url;
    }
// establishing the connection
    public static void setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(setURL(), "root", "");
        } catch (SQLException var1) {
            tools.ErrorBox(var1.getMessage());
            System.out.println(var1.getMessage());
        } catch (ClassNotFoundException var2) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String)null, var2);
        } catch (InstantiationException var3) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String)null, var3);
        } catch (IllegalAccessException var4) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

    }

    public static boolean checkUserAndPassword(String userName, String password) {
        try {
            setConnection();
            Statement statement = con.createStatement();
            String strCheck = "select * from users where username= '" + userName + "' and password= '" + password + "'";
            statement.executeQuery(strCheck);
            if (statement.getResultSet().next()) {
                return true;
            }

            con.close();
        } catch (SQLException var4) {
            tools.ErrorBox(var4.getMessage());
        }

        return false;
    }
// to check the sql statement validity
    public static boolean excuteQuery(String sqlStatement) {
        try {
            setConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(sqlStatement);
            con.close();
            return true;
        } catch (SQLException var2) {
            return false;
        }
    }

    public static String AutoIncrementCoulmn(String tableName, String coulmnName) {
        try {
            setConnection();
            Statement statement = con.createStatement();
            String strDBcode = "select max(" + coulmnName + ") +1 AS auto from " + tableName;
            statement.executeQuery(strDBcode);

            String num;
            for(num = ""; statement.getResultSet().next(); num = statement.getResultSet().getString("auto")) {
            }

            con.close();
            return num != null && !"".equals(num) ? num : "1";
        } catch (SQLException var5) {
            tools.ErrorBox(var5.getMessage());
            return null;
        }
    }

    public static table getTableData(String statement) {
        tools t = new tools();

        try {
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            ResultSetMetaData rsmd = rs.getMetaData();
            int coulmnCount = rsmd.getColumnCount();
            t.getClass();
            Tools.tools.table table = new table(coulmnCount);

            while(rs.next()) {
                Object[] row = new Object[coulmnCount];

                for(int i = 0; i < coulmnCount; ++i) {
                    row[i] = rs.getString(i + 1);
                }

                table.addNewRow(row);
            }

            con.close();
            return table;
        } catch (SQLException var9) {
            t.getClass();
            return new table(0);
        }
    }

    public static void fillComboBoxData(String tableName, String coulmnName, String condition, ComboBox<String> combo) {
        try {
            setConnection();
            Statement stmt = con.createStatement();
            String strSelect = "select " + coulmnName + " from " + tableName + condition;
            ResultSet rs = stmt.executeQuery(strSelect);
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            String[] values = new String[rowCount];

            for(int i = 0; rs.next(); ++i) {
                values[i] = rs.getString(coulmnName);
            }

            con.close();
            combo.getItems().addAll(values);
        } catch (SQLException var10) {
        }

    }

    public static ObservableList<books> buildBooksTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while(rs.next()) {
                books cm = new books();
                cm.setSubject_id(Integer.parseInt(rs.getString("subject_id")));
                cm.setBook_id(Integer.parseInt(rs.getString("book_id")));
                cm.setYear_id(Integer.parseInt(rs.getString("year_id")));
                cm.setBook_name(rs.getString("book_name"));
                cm.setBooksNo(rs.getString("booksNo"));
                cm.setBook_price(Double.parseDouble(rs.getString("book_price")));
                data.add(cm);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String)null, var11);
            }

            return data;
        }
    }

    public static ObservableList<subjects> buildSubjectsTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while(rs.next()) {
                subjects in = new subjects();
                in.setSubject_id(Integer.parseInt(rs.getString("subject_id")));
                in.setSubject_name(rs.getString("subject_name"));
                in.setSubject_price(Double.parseDouble(rs.getString("subject_price")));
                in.setYear_id(Integer.parseInt(rs.getString("year_id")));
                data.add(in);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String)null, var11);
            }

            return data;
        }
    }

    public static ObservableList<group> buildGroupsTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while(rs.next()) {
                group out = new group();
                out.setGroup_id(Integer.parseInt(rs.getString("group_id")));
                out.setGroup_name(rs.getString("group_name"));
                out.setYear_id(Integer.parseInt(rs.getString("year_id")));
                out.setSubject_id(Integer.parseInt(rs.getString("subject_id")));
                out.setInstructor_id(Integer.parseInt(rs.getString("instructor_id")));
                out.setDateOne(rs.getString("dateOne"));
                out.setDateOne_hour(rs.getString("dateOne_hour"));
                out.setDateTwo(rs.getString("dateTwo"));
                out.setDateTwo_hour(rs.getString("dateTwo_hour"));
                data.add(out);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String)null, var11);
            }

            return data;
        }
    }

    public static ObservableList<instructor> buildInstructorsTable(String SQL) {
        ObservableList<instructor> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while(rs.next()) {
                instructor s = new instructor();
                s.setInstructor_id(Integer.parseInt(rs.getString("instractor_id")));
                s.setInstructor_name(rs.getString("instractor_name"));
                s.setInstructor_phone(rs.getString("instractor_phone"));
                s.setInstructor_address(rs.getString("instractor_address"));
                s.setInstructor_salary((double)Integer.parseInt(rs.getString("instractor_notes")));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            tools.WarningBox(var7.getMessage());
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<year> buildYearsTable(String SQL) {
        ObservableList<year> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while(rs.next()) {
                year s = new year();
                s.setYear_id(Integer.parseInt(rs.getString("year_id")));
                s.setYear_name(rs.getString("year_name"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            tools.WarningBox(var7.getMessage());
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<instructor> buildTeachersTable(String SQL) {
        ObservableList<instructor> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while(rs.next()) {
                instructor s = new instructor();
                s.setInstructor_id(Integer.parseInt(rs.getString("instructor_id")));
                s.setSubject_id(Integer.parseInt(rs.getString("subject_id")));
                s.setInstructor_name(rs.getString("instructor_name"));
                s.setInstructor_phone(rs.getString("instructor_phone"));
                s.setInstructor_address(rs.getString("instructor_address"));
                s.setInstructor_salary(Double.parseDouble(rs.getString("instructor_salary")));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            tools.WarningBox(var7.getMessage());
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<group_S> buildAllStudentsInGroupTable(String SQL) {
        ObservableList<group_S> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while(rs.next()) {
                group_S s = new group_S();
                s.setS_code(rs.getString("s_code"));
                s.setS_name(rs.getString("s_name"));
                s.setGroup_name(rs.getString("group_name"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setYear_name(rs.getString("year_name"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<group_S> buildAllTeacherInGroupTable(String SQL) {
        ObservableList<group_S> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while(rs.next()) {
                group_S s = new group_S();
                s.setS_code(rs.getString("instructor_id"));
                s.setS_name(rs.getString("instructor_name"));
                s.setGroup_name(rs.getString("instructor_phone"));
                s.setSubject_name(rs.getString("subject_id"));
                s.setYear_name(rs.getString("instructor_address"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            return data;
        } finally {
            ;
        }
    }


}
