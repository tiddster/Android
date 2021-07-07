package Bean;

import java.util.List;

//缺时间
public class PatientInfo {
    public String name;
    public int number;
    public String sex;
    public int age;
    public String blood;
    public String password;
    public String book_month;
    public String book_day;
    public int Hours;
    public int circumstance;   //0:未预约  1：已预约，未查看   2：已预约，已查看
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<PatientBloodInfo> getPatientBloodInfoList() {
        return mPatientBloodInfoList;
    }

    public void setPatientBloodInfoList(List<PatientBloodInfo> patientBloodInfoList) {
        mPatientBloodInfoList = patientBloodInfoList;
    }
}
