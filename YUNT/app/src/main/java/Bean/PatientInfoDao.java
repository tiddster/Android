package Bean;

import androidx.room.Dao;
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

    @Query("SELECT * FROM patientinfo ORDER BY number")
    List<PatientInfo> getList();

    @Query("SELECT * FROM patientinfo WHERE name IN(:name)")
    int getIdByName(String name);
}
