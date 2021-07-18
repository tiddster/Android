package Bean;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientInfoDao {
    @Insert
    void InsertPatient(PatientInfo... patientInfos);

    @Update
    void UpdatePatient(PatientInfo... patientInfos);

    @Delete
    void DeletePatient(PatientInfo... patientInfos);

    @Query("SELECT * FROM patientinfo ORDER BY number")
    List<PatientInfo> getList();

    @Query("SELECT * FROM patientinfo WHERE name IN(:name)")
    PatientInfo getByName(String name);

    @Query("SELECT * FROM patientinfo WHERE number IN(:number)")
    PatientInfo getById(int number);
}
