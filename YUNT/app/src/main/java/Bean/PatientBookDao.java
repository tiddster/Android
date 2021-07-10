package Bean;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientBookDao {
    @Insert
    void InsertDate(PatientBook... patientBooks);

    @Update
    void UpdateData(PatientBook... patientBooks);

    @Delete
    void DeleteDate(PatientBook... patientBooks);

    @Query("SELECT * FROM PatientBook ORDER BY booking_number DESC")
    List<PatientBook> getDateList();
}
