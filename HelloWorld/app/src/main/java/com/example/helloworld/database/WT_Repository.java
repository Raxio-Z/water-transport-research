package com.example.helloworld.database;

import android.app.Application;


import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class WT_Repository {
    private user_BoatDao muser_boatDao;
    private user_CargoDao muser_cargoDao;
    //private LiveData<List<user_Boat>> mAll_userBoat;
    //private LiveData<List<user_Cargo>> mAll_userCargo;


    public WT_Repository(Application application){
        WT_RoomDatabase db = WT_RoomDatabase.getDatabase(application);
        muser_boatDao = db.user_boatDao();
        muser_cargoDao = db.user_cargoDao();
    }

    public void insert(user_Boat userBoat){
        WT_RoomDatabase.databaseWriteExecutor.execute(()-> {
            muser_boatDao.insert(userBoat);
        });
    }

    public void insert(user_Cargo userCargo){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            muser_cargoDao.insert(userCargo);
        });
    }

    public ListenableFuture<user_Boat> finduserBoatWithUsername(String un){
        return muser_boatDao.finduserBoatWithUsername(un);
    }

    public ListenableFuture<user_Boat> finduserBoatWithId(Integer id){
        return muser_boatDao.finduserBoatWithId(id);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithId(Integer id){
        return muser_cargoDao.finduserCargoWithId(id);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithUsername(String un){
        return muser_cargoDao.finduserCargoWithUsername(un);
    }

    public void update(user_Cargo userCargo){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            muser_cargoDao.updata(userCargo);
        });
    }

    public void update(user_Boat userBoat){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            muser_boatDao.update(userBoat);
        });
    }

    public ListenableFuture<List<user_Boat>> getAlluserBoat(){
        return muser_boatDao.getAlluserBoat();
    }

    public LiveData<List<user_Cargo>> getAlluserCargo(){
        return muser_cargoDao.getAlluserCargo();
    }

    public void deleteAllBoat(){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            muser_boatDao.deleteAllBoat();
        });
    }

    public void deleteAllCargo(){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            muser_cargoDao.deleteAllCargo();
        });
    }
}
