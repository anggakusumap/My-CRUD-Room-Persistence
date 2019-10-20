package com.angga.kusuma.crudandroidroompersistence.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.angga.kusuma.crudandroidroompersistence.R;
import com.angga.kusuma.crudandroidroompersistence.entity.Mahasiswa;
import com.angga.kusuma.crudandroidroompersistence.viewmodel.MahasiswaViewModel;

import java.util.List;

public class MahasiswaListAdapter extends RecyclerView.Adapter<MahasiswaListAdapter.MahasiswaViewHolder> {
    private MahasiswaViewModel mahasiswaViewModel;

    class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private final TextView mahasiswaNameView, mahasiswaEmailView, mahasiswaNimView;
        private final Button editButton, deleteButton;
        private MahasiswaViewHolder(final View itemView) {
            super(itemView);
            mahasiswaNameView = itemView.findViewById(R.id.textView_mahasiswa_name);
            mahasiswaEmailView = itemView.findViewById(R.id.textView_mahasiswa_email);
            mahasiswaNimView = itemView.findViewById(R.id.textView_nim);

            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);
        }
    }

    private final LayoutInflater mInflater;
    private List<Mahasiswa> mMahasiswas; // Cached copy of words

    public MahasiswaListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MahasiswaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MahasiswaViewHolder holder, int position) {
        if (mMahasiswas != null) {
            Mahasiswa current = mMahasiswas.get(position);
            holder.mahasiswaNameView.setText("Nama : "+current.getMahasiswaName());
            holder.mahasiswaEmailView.setText("Email : "+current.getMahasiswaEmail());
            holder.mahasiswaNimView.setText("Nim   : "+current.getMahasiswaNim());

        } else {
            // Covers the case of data not being ready yet.
            holder.mahasiswaNameView.setText("No Mahasiswa");
        }

    }

    public void setmMahasiswas(List<Mahasiswa> mahasiswas){
        mMahasiswas = mahasiswas;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mMahasiswas != null)
            return mMahasiswas.size();
        else return 0;
    }

    public Mahasiswa getMahasiswaAt(int position) {
        return mMahasiswas.get(position);
    }
}
