//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities;

import Database.database;
import Tools.tools;

public class subjects implements mainFunctions {
    private int subject_id;
    private int year_id;
    private String subject_name;
    private double subject_price;

    public subjects() {
    }

    public int getSubject_id() {
        return this.subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return this.subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public double getSubject_price() {
        return this.subject_price;
    }

    public void setSubject_price(double subject_price) {
        this.subject_price = subject_price;
    }

    public void add() {
        String strInsert = "insert into subjects values(" + this.subject_id + " ,'" + this.subject_name + "' , " + this.subject_price + " , " + this.year_id + " )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه الماده بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء اضافه الماده");
        }

    }

    public void update() {
        String strUpdate = "update subjects set subject_name = '" + this.subject_name + "' , subject_price = '" + this.subject_price + "' , year_id = " + this.year_id + "  where subject_id =" + this.subject_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل الماده بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء تعديل الماده");
        }

    }

    public void delete() {
        String strDelete = "delete from subjects where subject_id = " + this.subject_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم حذف الماده بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ اثناء الحذف");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("subjects", "subject_id");
        return strAuto;
    }

    public int getYear_id() {
        return this.year_id;
    }

    public void setYear_id(int year_id) {
        this.year_id = year_id;
    }
}
