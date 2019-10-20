package com.angga.kusuma.crudandroidroompersistence.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.angga.kusuma.crudandroidroompersistence.dao.MahasiswaDAO;
import com.angga.kusuma.crudandroidroompersistence.entity.Mahasiswa;

import java.util.List;

public class MahasiswaRepository {
    private MahasiswaDAO mahasiswaDAO;
    private LiveData<List<Mahasiswa>> mAllMahasiswa;

   public MahasiswaRepository(Application application) {
        MahasiswaAppDatabase db = MahasiswaAppDatabase.getDatabase(application);
        mahasiswaDAO = db.mahasiswaDAO();
        mAllMahasiswa = mahasiswaDAO.getAllMahasiswa();
    }

    public LiveData<List<Mahasiswa>> getmAllMahasiswa() {
        return mAllMahasiswa;
    }


    public void insert (Mahasiswa mahasiswa) {
        new insertAsyncTask(mahasiswaDAO).execute(mahasiswa);
    }

    public void delete(Mahasiswa mahasiswa) {
        new DeleteMahasiswaAsyncTask(mahasiswaDAO).execute(mahasiswa);
    }

    public void update(Mahasiswa mahasiswa) {
        new UpdateMahasiswaAsyncTask(mahasiswaDAO).execute(mahasiswa);
    }

    private static class insertAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {

        private MahasiswaDAO mAsyncTaskDao;

        insertAsyncTask(MahasiswaDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Mahasiswa... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdateMahasiswaAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDAO mahasiswaDAO;

        private UpdateMahasiswaAsyncTask(MahasiswaDAO mahasiswaDAO) {
            this.mahasiswaDAO = mahasiswaDAO;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            mahasiswaDAO.update(mahasiswas[0]);
            return null;
        }
    }

    private static class DeleteMahasiswaAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private MahasiswaDAO mahasiswaDao;

        private DeleteMahasiswaAsyncTask(MahasiswaDAO mahasiswaDAO) {
            this.mahasiswaDao = mahasiswaDAO;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            mahasiswaDao.delete(mahasiswas[0]);
            return null;
        }
    }
}
