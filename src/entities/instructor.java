//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities;

import Database.database;
import Tools.tools;

public class instructor implements mainFunctions {
    private int instructor_id;
    private int subject_id;
    private String instructor_name;
    private String instructor_phone;
    private String instructor_address;
    private double instructor_salary;

    public instructor() {
    }

    public int getInstructor_id() {
        return this.instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getInstructor_name() {
        return this.instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }

    public double getInstructor_salary() {
        return this.instructor_salary;
    }

    public void setInstructor_salary(double instructor_salary) {
        this.instructor_salary = instructor_salary;
    }

    public void add() {
        String strInsert = "insert into instructors values(" + this.instructor_id + " , '" + this.instructor_name + "' , '" + this.instructor_phone + "' , '" + this.instructor_address + "' , " + this.instructor_salary + " , " + this.subject_id + " )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه المدرس بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء اضافه المدرس");
        }

    }

    public void update() {
        String strUpdate = "update instructors set instructor_name = '" + this.instructor_name + "' , instructor_phone = '" + this.instructor_phone + "' , instructor_address = '" + this.instructor_address + "' , instructor_salary = " + this.instructor_salary + " , subject_id = " + this.subject_id + "  where instructor_id =" + this.instructor_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل المدرس بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ ما اثناء تعديل المدرس");
        }

    }

    public void delete() {
        String strDelete = "delete from instructors where instructor_id = " + this.instructor_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم حذف المدرس بنجاح");
        } else {
            tools.ErrorBox("حدث خطأ اثناء الحذف");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("instructors", "instructor_id");
        return strAuto;
    }

    public int getSubject_id() {
        return this.subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getInstructor_phone() {
        return this.instructor_phone;
    }

    public void setInstructor_phone(String instructor_phone) {
        this.instructor_phone = instructor_phone;
    }

    public String getInstructor_address() {
        return this.instructor_address;
    }

    public void setInstructor_address(String instructor_address) {
        this.instructor_address = instructor_address;
    }
}
