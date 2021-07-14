package Bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PatientBook {
    @PrimaryKey(autoGenerate = true)
    public int booking_number;

    @ColumnInfo(name = "p_book_hospital")
    public String hospital;

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "bookMonth")
    public String book_month;

    @ColumnInfo
    public String book_day;

    @ColumnInfo(name = "hours")
    public int Hours;

    @ColumnInfo(name = "circumstances")
    public int circumstance;   //0:未预约  1：已预约，未完成  2：已完成

    public PatientBook(String name , int id,String book_month, String book_day, int Hours, int circumstance, int age) {
        this.id = id;
        this.book_month = book_month;
        this.book_day = book_day;
        this.Hours = Hours;
        this.circumstance = circumstance;
        this.name = name;
        this.age = age;
    }

    public String getBook_month() {
        return book_month;
    }

    public void setBook_month(String book_month) {
        this.book_month = book_month;
    }

    public String getBook_day() {
        return book_day;
    }

    public void setBook_day(String book_day) {
        this.book_day = book_day;
    }

    public int getHours() {
        return Hours;
    }

    public void setHours(int hours) {
        Hours = hours;
    }

    public int getCircumstance() {
        return circumstance;
    }

    public void setCircumstance(int circumstance) {
        this.circumstance = circumstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBooking_number() {
        return booking_number;
    }

    public void setBooking_number(int booking_number) {
        this.booking_number = booking_number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
