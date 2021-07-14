package Bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PatientBlood {
    @PrimaryKey(autoGenerate = true)
    public int blood_id;

    @ColumnInfo(name = "p_blood_doctorName")
    public String doctorName;
    @ColumnInfo(name = "p_blood_hospital")
    public String hospital;
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "BWeight")
    public float BWeight;
    @ColumnInfo(name = "AWeight")
    public float AWeight;
    @ColumnInfo(name = "ABloodP")
    public float ABloodP;
    @ColumnInfo(name = "BBloodP")
    public float BBloodP;
    @ColumnInfo
    public int next_year;
    @ColumnInfo(name = "next_month")
    public int next_month;
    @ColumnInfo(name = "next_day")
    public int next_day;
    @ColumnInfo
    public int this_year;
    @ColumnInfo
    public int this_month;
    @ColumnInfo
    public int this_day;
    @ColumnInfo(name = "CR")
    public float CR;

    public PatientBlood(int id, float BWeight, float AWeight, float ABloodP, float BBloodP, float CR,
                        int next_year,int next_month, int next_day,int this_year,int this_month,int this_day) {
        this.id = id;
        this.BWeight = BWeight;
        this.AWeight = AWeight;
        this.ABloodP = ABloodP;
        this.BBloodP = BBloodP;
        this.next_day = next_day;
        this.next_month = next_month;
        this.next_year = next_year;
        this.this_year = this_year;
        this.this_month = this_month;
        this.this_day = this_day;
        this.CR = CR;
    }

    public float getBWeight() {
        return BWeight;
    }

    public void setBWeight(float BWeight) {
        this.BWeight = BWeight;
    }

    public float getAWeight() {
        return AWeight;
    }

    public void setAWeight(float AWeight) {
        this.AWeight = AWeight;
    }

    public float getABloodP() {
        return ABloodP;
    }

    public void setABloodP(float ABloodP) {
        this.ABloodP = ABloodP;
    }

    public float getBBloodP() {
        return BBloodP;
    }

    public void setBBloodP(float BBloodP) {
        this.BBloodP = BBloodP;
    }

    public int getNext_month() {
        return next_month;
    }

    public void setNext_month(int next_month) {
        this.next_month = next_month;
    }

    public int getNext_day() {
        return next_day;
    }

    public void setNext_day(int next_day) {
        this.next_day = next_day;
    }

    public float getCR() {
        return CR;
    }

    public void setCR(float CR) {
        this.CR = CR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlood_id() {
        return blood_id;
    }

    public void setBlood_id(int blood_id) {
        this.blood_id = blood_id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getThis_month() {
        return this_month;
    }

    public void setThis_month(int this_month) {
        this.this_month = this_month;
    }

    public int getThis_day() {
        return this_day;
    }

    public void setThis_day(int this_day) {
        this.this_day = this_day;
    }

    public int getNext_year() {
        return next_year;
    }

    public void setNext_year(int next_year) {
        this.next_year = next_year;
    }

    public int getThis_year() {
        return this_year;
    }

    public void setThis_year(int this_year) {
        this.this_year = this_year;
    }
}
