package Bean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DoctorAccountDao {
    @Insert
    void insertAccount(DoctorAccount... doctorAccounts);

    @Update
    void UpdateAccount(DoctorAccount... doctorAccounts);

    @Query("SELECT * FROM DoctorAccount ORDER BY id")
    List<DoctorAccount> getAllDoctorAccount();
}
