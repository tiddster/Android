package Bean;

public class PatientBloodInfo {
    public float BWeight;
    public float AWeight;
    public float ABloodP;
    public float BBloodP;
    public int interval;
    public float CR;

    public PatientBloodInfo(float BWeight, float AWeight, float ABloodP, float BBloodP, int interval, float CR) {
        this.BWeight = BWeight;
        this.AWeight = AWeight;
        this.ABloodP = ABloodP;
        this.BBloodP = BBloodP;
        this.interval = interval;
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

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public float getCR() {
        return CR;
    }

    public void setCR(float CR) {
        this.CR = CR;
    }
}
