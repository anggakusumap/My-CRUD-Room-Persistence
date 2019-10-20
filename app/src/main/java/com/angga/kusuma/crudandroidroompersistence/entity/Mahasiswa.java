package com.angga.kusuma.crudandroidroompersistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "mahasiswas")
public class Mahasiswa {

    @PrimaryKey(autoGenerate = true)
    private int mahasiswaId;

    @NonNull
    @ColumnInfo(name = "mahasiswa_name")
    private String mahasiswaName;

    @NonNull
    @ColumnInfo(name = "mahasiswa_email")
    private String mahasiswaEmail;

    @NonNull
    @ColumnInfo(name = "mahasiswa_nim")
    private int mahasiswaNim;

    public Mahasiswa(@NonNull String mahasiswaName, @NonNull String mahasiswaEmail, int mahasiswaNim) {
        this.mahasiswaName = mahasiswaName;
        this.mahasiswaEmail = mahasiswaEmail;
        this.mahasiswaNim = mahasiswaNim;
    }

    public int getMahasiswaId() {
        return mahasiswaId;
    }

    public void setMahasiswaId(int mahasiswaId) {
        this.mahasiswaId = mahasiswaId;
    }

    @NonNull
    public String getMahasiswaName() {
        return mahasiswaName;
    }

    public void setMahasiswaName(@NonNull String mahasiswaName) {
        this.mahasiswaName = mahasiswaName;
    }

    @NonNull
    public String getMahasiswaEmail() {
        return mahasiswaEmail;
    }

    public void setMahasiswaEmail(@NonNull String mahasiswaEmail) {
        this.mahasiswaEmail = mahasiswaEmail;
    }

    public int getMahasiswaNim() {
        return mahasiswaNim;
    }

    public void setMahasiswaNim(int mahasiswaNim) {
        this.mahasiswaNim = mahasiswaNim;
    }
}
