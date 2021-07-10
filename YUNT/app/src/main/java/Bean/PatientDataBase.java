package Bean;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {PatientInfo.class, PatientBook.class,DoctorAccount.class}, exportSchema = false)
public abstract class PatientDataBase extends RoomDatabase {
    //用户基本信息数据库
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

    //用户预约数据库
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

    //医生账户数据库
    public static PatientDataBase DAINSTANCE;
    public static synchronized PatientDataBase getDAInstance(Context context){
        if(DAINSTANCE == null){
            DAINSTANCE = Room.databaseBuilder(context.getApplicationContext(),PatientDataBase.class,"DoctorAccountDataBase")
                    .allowMainThreadQueries()
                    .build();
        }
        return DAINSTANCE;
    }
    public abstract DoctorAccountDao getDADao();
}
