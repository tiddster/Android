package Bean;

import java.util.List;

//缺时间
public class PatientInfo {
    public String name;
    public int number;
    public String sex;
    public int age;
    public String blood;
    public List<PatientBloodInfo> mPatientBloodInfoList;

    public PatientInfo(String name, int number, String sex, int age, String blood, List<PatientBloodInfo> patientBloodInfoList) {
        this.name = name;
        this.number = number;
        this.sex = sex;
        this.age = age;
        this.blood = blood;
        mPatientBloodInfoList = patientBloodInfoList;
    }

    public PatientInfo(String name, int number, String sex, int age, String blood) {
        this.name = name;
        this.number = number;
        this.sex = sex;
        this.age = age;
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
}
