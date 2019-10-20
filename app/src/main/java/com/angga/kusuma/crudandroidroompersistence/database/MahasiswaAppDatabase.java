package com.angga.kusuma.crudandroidroompersistence.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.angga.kusuma.crudandroidroompersistence.dao.MahasiswaDAO;
import com.angga.kusuma.crudandroidroompersistence.entity.Mahasiswa;

@Database(entities = {Mahasiswa.class}, version = 1, exportSchema = false)
public abstract  class MahasiswaAppDatabase extends RoomDatabase {

    public abstract MahasiswaDAO mahasiswaDAO();

    private static volatile MahasiswaAppDatabase INSTANCE;

    static MahasiswaAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MahasiswaAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MahasiswaAppDatabase.class, "mahasiswa_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
