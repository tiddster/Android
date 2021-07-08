package Bean;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {PatientInfo.class,PatientBookDate.class}, exportSchema = false)
public abstract class PatientDataBase extends RoomDatabase {
    public static PatientDataBase BasicINSTANCE;
    public static synchronized PatientDataBase getBasicInstance(Context context){
        if(BasicINSTANCE == null){
            BasicINSTANCE = Room.databaseBuilder(context.getApplicationContext(),PatientDataBase.class,"PatientBasicDataBase")
                    .allowMainThreadQueries()
                    .build();
        }
        return BasicINSTANCE;
    }

    public abstract PatientInfoDao getPatientInfoDao();

    public static PatientDataBase DateINSTANCE;
    public static synchronized PatientDataBase getDateInstance(Context context){
        if(DateINSTANCE == null){
            DateINSTANCE = Room.databaseBuilder(context.getApplicationContext(),PatientDataBase.class,"PatientDateDataBase")
                    .allowMainThreadQueries()
                    .build();
        }
        return DateINSTANCE;
    }

    public abstract PatientBookDao getDateDao();
}
