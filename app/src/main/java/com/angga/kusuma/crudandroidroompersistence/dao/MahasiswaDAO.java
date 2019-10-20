package com.angga.kusuma.crudandroidroompersistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.angga.kusuma.crudandroidroompersistence.entity.Mahasiswa;

import java.util.List;

@Dao
public interface MahasiswaDAO {
    @Insert
    void insert(Mahasiswa mahasiswa);

    @Update
    void update(Mahasiswa mahasiswa);

    @Delete
    void delete(Mahasiswa mahasiswa);

    @Query("DELETE FROM mahasiswas")
    void deleteAll();

    @Query("SELECT * from mahasiswas ORDER BY mahasiswa_name ASC")
    LiveData<List<Mahasiswa>> getAllMahasiswa();
}
