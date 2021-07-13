package Bean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientBloodDao {
    @Insert
    void InsertBlood(PatientBlood... patientBloods);

    @Update
    void UpdateBlood(PatientBlood... patientBloods);

    @Query("select * from PatientBlood order by blood_id desc")
    List<PatientBlood> getAllBlood();
}
