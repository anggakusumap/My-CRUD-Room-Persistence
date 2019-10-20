package com.angga.kusuma.crudandroidroompersistence.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.angga.kusuma.crudandroidroompersistence.database.MahasiswaRepository;
import com.angga.kusuma.crudandroidroompersistence.entity.Mahasiswa;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {

    private MahasiswaRepository mRepository;

    private LiveData<List<Mahasiswa>> mAllMahasiswa;

    public MahasiswaViewModel(Application application) {
        super(application);
        mRepository = new MahasiswaRepository(application);
        mAllMahasiswa = mRepository.getmAllMahasiswa();
    }

    public LiveData<List<Mahasiswa>> getmAllMahasiswa() { return mAllMahasiswa; }

    public void insert(Mahasiswa mahasiswa) { mRepository.insert(mahasiswa); }
    public void update(Mahasiswa mahasiswa) { mRepository.update(mahasiswa); }
    public void delete(Mahasiswa mahasiswa) { mRepository.delete(mahasiswa); }
}