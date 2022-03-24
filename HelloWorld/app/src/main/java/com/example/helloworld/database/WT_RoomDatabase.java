package com.example.helloworld.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {user_Boat.class,user_Cargo.class},version = 5,exportSchema = false)
public abstract class WT_RoomDatabase extends RoomDatabase {
    //public abstract UserDao userDao();
    public abstract user_BoatDao user_boatDao();
    public abstract user_CargoDao user_cargoDao();

    private static volatile WT_RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    //创建线程池，保证对数据库的操作都不是在主线程中进行的
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WT_RoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            //开了线程
            synchronized (WT_RoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WT_RoomDatabase.class,"WT_database")
                            .addCallback(sRoomsRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //数据库初始插入信息
    private static RoomDatabase.Callback sRoomsRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                user_CargoDao user_cargoDao = INSTANCE.user_cargoDao();
                user_BoatDao user_boatDao = INSTANCE.user_boatDao();
                user_cargoDao.deleteAllCargo();
                user_boatDao.deleteAllBoat();

                user_Boat[] userBoat = new user_Boat[10];// = new user_Boat("liuzheng","123","刘铮","123456",1000,1000,"沙土石子","上海");
                //userBoat.setIsFillInfo();
                user_Cargo[] userCargo = new user_Cargo[10];//new user_Cargo("lijianqiang","123","李建强","123456",1000,"沙土石子","上海","重庆",1,1);

                userCargo[0] = new
                        user_Cargo("mql","123","马千里","10086",800,"沙土石子","重庆","上海",3, 13);
                userCargo[1] = new
                        user_Cargo("cj","123","陈静","10086",1000,"煤炭","镇江","上海",3, 12);
                userCargo[2] = new
                        user_Cargo("wm","123","吴敏","10086",2000,"沙土石子","巫山","南京",3, 12);
                userCargo[3] = new
                        user_Cargo("lhf","123","刘航帆","10086",600,"煤炭","江阴","岳阳",3, 15);
                userCargo[4] = new
                        user_Cargo("lhp","123","李和平","10086",1100,"矿石","重庆","南通", 3, 11);
                userCargo[5] = new
                        user_Cargo("cjn","123","程敬南","10086",2800,"矿石","黄石","铜陵",3, 11);
                userCargo[6] = new
                        user_Cargo("hy","123","华勇","10086",1200,"沙土石子","芜湖","上海",3, 12);
                userCargo[7] = new
                        user_Cargo("wj","123","王军","10086",1800,"沙土石子","江陵","南京",3, 13);
                userCargo[8] = new
                        user_Cargo("zzw","123","张中文","10086",1250,"煤炭","黄石","镇江",3, 14);
                userCargo[9] = new
                        user_Cargo("zsm","123","赵生明","10086",1400,"矿石","岳阳","江阴",3, 11);





                userBoat[0] = new
                        user_Boat("xxs","123","许先生","95566",2000,1300,"沙土石子", "重庆");
                userBoat[1] = new
                        user_Boat("wxs","123","吴先生","95566",2100,1500,"矿石", "上海");
                userBoat[2] = new
                        user_Boat("axs","123","安先生","95566",3200,3000,"矿石", "江阴");
                userBoat[3] = new
                        user_Boat("hns","123","何女士","95566",2200,2000,"沙土石子", "南通");
                userBoat[4] = new
                        user_Boat("yxs","123","闫先生","95566",2100,1000,"煤炭", "铜陵");
                userBoat[5] = new
                        user_Boat("lxs","123","刘先生","95566",1900,1500,"煤炭", "镇江");
                userBoat[6] = new
                        user_Boat("cns","123","陈女士","95566",3200,2000,"沙土石子", "黄石");
                userBoat[7] = new
                        user_Boat("bxs","123","包先生","95566",2000,1800,"沙土石子", "芜湖");
                userBoat[8] = new
                        user_Boat("dxs","123","董先生","95566",2300,1900,"煤炭", "岳阳");
                userBoat[9] = new
                        user_Boat("zxs","123","张先生","95566",2500,2300,"矿石", "巫山");

                for(int i=0;i<10;i++){
                    userCargo[i].setIsFillInfo();
                    userBoat[i].setIsFillInfo();
                    user_boatDao.insert(userBoat[i]);
                    user_cargoDao.insert(userCargo[i]);
                }

                //userCargo.setIsFillInfo();
                //user_cargoDao.insert(userCargo);
                //user_boatDao.insert(userBoat);
            });
        }
    };
}
