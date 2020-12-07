//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities;

import Database.database;
import Tools.tools;

public class group implements mainFunctions {
    private int group_id;
    private int subject_id;
    private int year_id;
    private int instructor_id;
    private String group_name;
    private String dateOne;
    private String dateOne_hour;
    private String dateTwo;
    private String dateTwo_hour;

    public group() {
    }

    public int getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return this.group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getYear_id() {
        return this.year_id;
    }

    public void setYear_id(int year_id) {
        this.year_id = year_id;
    }

    public int getInstructor_id() {
        return this.instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getDateTwo() {
        return this.dateTwo;
    }

    public void setDateTwo(String dateTwo) {
        this.dateTwo = dateTwo;
    }

    public String getDateTwo_hour() {
        return this.dateTwo_hour;
    }

    public void setDateTwo_hour(String dateTwo_hour) {
        this.dateTwo_hour = dateTwo_hour;
    }

    public void add() {
        String strInsert = "insert into groups values(" + this.group_id + " , '" + this.group_name + "' , " + this.year_id + " , " + this.subject_id + " , " + this.instructor_id + " , '" + this.dateOne + "' , '" + this.dateOne_hour + "' , '" + this.dateTwo + "' , '" + this.dateTwo_hour + "' )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه المجموعه بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء اضافه المجموعه");
        }

    }

    public void update() {
        String strUpdate = "update groups set group_name = '" + this.group_name + "' , year_id = " + this.year_id + " , subject_id = " + this.subject_id + " , instructor_id = " + this.instructor_id + " , dateOne = '" + this.dateOne + "' , dateOne_hour = '" + this.dateOne_hour + "' , dateTwo = '" + this.dateTwo + "' , dateTwo_hour = '" + this.dateTwo_hour + "'  where group_id =" + this.group_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل المجموعه بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء تعديل المجموعه");
        }

    }

    public void delete() {
        String strDelete = "delete from groups where group_id = " + this.group_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم حذف المجموعه بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ اثناء الحذف");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("groups", "group_id");
        return strAuto;
    }

    public int getSubject_id() {
        return this.subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getDateOne() {
        return this.dateOne;
    }

    public void setDateOne(String dateOne) {
        this.dateOne = dateOne;
    }

    public String getDateOne_hour() {
        return this.dateOne_hour;
    }

    public void setDateOne_hour(String dateOne_hour) {
        this.dateOne_hour = dateOne_hour;
    }
}
