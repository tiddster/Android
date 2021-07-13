package Bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class PatientInfo {
    @ColumnInfo(name = "name")
    public String name;

    @PrimaryKey(autoGenerate = true)
    public int number;

    @ColumnInfo(name = "p_Info_doctor")
    public String doctorName;

    @ColumnInfo(name = "p_Info_hospital")
    public String hospital;

    @ColumnInfo(name = "sex")
    public String sex;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "blood")
    public String blood;

    @ColumnInfo(name = "P_password")
    public String password;

    /*
    public String book_month;
    public String book_day;
    public int Hours;
    public int circumstance;   //0:未预约  1：已预约，未查看   2：已预约，已查看  3:已取消
     */
    /*
    @ColumnInfo(name = "BloodList")
    public List<PatientBloodInfo> mPatientBloodInfoList;

    @ColumnInfo(name = "DateList")
    public List<PatientBookDate> mPatientBookDateList;

     */

    public PatientInfo(String name, int number, String sex, int age, String blood) {
        this.name = name;
        this.number = number;
        this.sex = sex;
        this.age = age;
        this.blood = blood;
        this.hospital = hospital;
        this.doctorName = doctorName;
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
}
