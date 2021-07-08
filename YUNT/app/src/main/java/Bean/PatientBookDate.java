package Bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PatientBookDate {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "bookMonth")
    public String book_month;

    @ColumnInfo
    public String book_day;

    @ColumnInfo(name = "hours")
    public int Hours;

    @ColumnInfo(name = "circumstances")
    public int circumstance;   //0:未预约  1：已预约，未查看   2：已预约，已查看

    public PatientBookDate(String book_month, String book_day, int Hours, int circumstance) {
        this.book_month = book_month;
        this.book_day = book_day;
        this.Hours = Hours;
        this.circumstance = circumstance;
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
}
