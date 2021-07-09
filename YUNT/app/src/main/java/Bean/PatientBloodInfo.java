package Bean;

public class PatientBloodInfo {
    public int id;
    public float BWeight;
    public float AWeight;
    public float ABloodP;
    public float BBloodP;
    public int next_month;
    public int next_day;
    public float CR;

    public PatientBloodInfo(int id, float BWeight, float AWeight, float ABloodP, float BBloodP, int interval, float CR,int next_month,int next_day) {
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
}
