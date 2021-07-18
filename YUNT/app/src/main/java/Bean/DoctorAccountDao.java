package Bean;

import androidx.room.Dao;
import androidx.room.Delete;
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

    @Delete
    void DeleteAccount(DoctorAccount... doctorAccounts);

    @Query("SELECT * FROM DoctorAccount ORDER BY id")
    List<DoctorAccount> getAllDoctorAccount();

    @Query("SELECT * FROM doctoraccount WHERE ID IN(:ID)")
    DoctorAccount getById(int ID);
}
