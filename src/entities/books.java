//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities;

import Database.database;
import Tools.tools;

public class books implements mainFunctions {
    private int book_id;
    private int year_id;
    private int subject_id;
    private String book_name;
    private String booksNo;
    private double book_price;

    public books() {
    }

    public String getBooksNo() {
        return this.booksNo;
    }

    public void setBooksNo(String booksNo) {
        this.booksNo = booksNo;
    }

    public int getSubject_id() {
        return this.subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getBook_id() {
        return this.book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return this.book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public double getBook_price() {
        return this.book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public void add() {
        String strInsert = "insert into books values(" + this.book_id + " ,'" + this.book_name + "' , " + this.book_price + " , " + this.year_id + " , " + this.subject_id + " , '" + this.booksNo + "' )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه الكتاب بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء اضافه الكتاب");
        }

    }

    public void update() {
        String strUpdate = "update books set book_name = '" + this.book_name + "' , book_price = " + this.book_price + " , year_id = " + this.year_id + " , booksNo = '" + this.booksNo + "' , subject_id = " + this.subject_id + "    where subject_id =" + this.book_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل الكتاب بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء تعديل الكتاب");
        }

    }

    public void delete() {
        String strDelete = "delete from books where book_id = " + this.book_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم حذف الكتاب بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ اثناء الحذف");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("books", "book_id");
        return strAuto;
    }

    public int getYear_id() {
        return this.year_id;
    }

    public void setYear_id(int year_id) {
        this.year_id = year_id;
    }
}
