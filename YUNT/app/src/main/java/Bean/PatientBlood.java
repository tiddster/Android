package Bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PatientBlood {
    @PrimaryKey(autoGenerate = true)
    public int blood_id;

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
    @ColumnInfo(name = "next_month")
    public int next_month;
    @ColumnInfo(name = "next_day")
    public int next_day;
    @ColumnInfo(name = "CR")
    public float CR;

    public PatientBlood(int id, float BWeight, float AWeight, float ABloodP, float BBloodP, float CR, int next_month, int next_day) {
        this.id = id;
        this.BWeight = BWeight;
        this.AWeight = AWeight;
        this.ABloodP = ABloodP;
        this.BBloodP = BBloodP;
        this.next_day = next_day;
        this.next_month = next_month;
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
}
