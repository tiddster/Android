package Bean;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientBookDao {
    @Insert
    void InsertDate(PatientBookDate... patientBookDates);

    @Delete
    void DeleteDate(PatientBookDate... patientBookDates);

    @Query("SELECT * FROM PatientBookDate ORDER BY id")
    List<PatientBookDate> getDateList();
}
