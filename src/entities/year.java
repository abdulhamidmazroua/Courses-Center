//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities;

import Database.database;
import Tools.tools;

public class year implements mainFunctions {
    private int year_id;
    private String year_name;

    public year() {
    }

    public int getYear_id() {
        return this.year_id;
    }

    public void setYear_id(int year_id) {
        this.year_id = year_id;
    }

    public String getYear_name() {
        return this.year_name;
    }

    public void setYear_name(String year_name) {
        this.year_name = year_name;
    }
//insert operation
    public void add() {
        String strInsert = "insert into years values(" + this.year_id + " ,'" + this.year_name + "' )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه السنه الدراسيه بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء اضافه السنه الدراسيه");
        }

    }

    public void update() {
        String strUpdate = "update years set year_name = '" + this.year_name + "'   where year_id =" + this.year_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل السنه الدراسيه بنجاح بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء تعديل السنه الدراسيه");
        }

    }

    public void delete() {
        String strDelete = "delete from years where year_id = " + this.year_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم حذف السنه الدراسيه بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ اثناء الحذف");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("years", "year_id");
        return strAuto;
    }
}
