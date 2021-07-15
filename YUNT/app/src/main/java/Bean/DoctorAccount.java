package Bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DoctorAccount {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "account")
    String account;

    @ColumnInfo(name = "d_password")
    String password;

    @ColumnInfo(name = "hospital")
    String hospital;

    public DoctorAccount(String account, String password, String hospital,int id) {
        this.account = account;
        this.password = password;
        this.hospital = hospital;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
