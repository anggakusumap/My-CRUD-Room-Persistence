package com.angga.kusuma.crudandroidroompersistence.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.angga.kusuma.crudandroidroompersistence.R;
import com.angga.kusuma.crudandroidroompersistence.adapter.MahasiswaListAdapter;
import com.angga.kusuma.crudandroidroompersistence.adapter.RecyclerItemClickListener;
import com.angga.kusuma.crudandroidroompersistence.entity.Mahasiswa;
import com.angga.kusuma.crudandroidroompersistence.utils.ApplicationStrings;
import com.angga.kusuma.crudandroidroompersistence.viewmodel.MahasiswaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 2;

    private MahasiswaViewModel mMahasiswaViewModel;
    private Button editButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MahasiswaListAdapter adapter = new MahasiswaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Get a new or existing ViewModel from the ViewModelProvider.
        mMahasiswaViewModel = ViewModelProviders.of(this).get(MahasiswaViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mMahasiswaViewModel.getmAllMahasiswa().observe(this, new Observer<List<Mahasiswa>>() {
            @Override
            public void onChanged(@Nullable final List<Mahasiswa> mahasiswas) {
                // Update the cached copy of the words in the adapter.
                adapter.setmMahasiswas(mahasiswas);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewMahasiswaActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int position) {
                        editButton = v.findViewById(R.id.button_edit);
                        //Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                        deleteButton = v.findViewById(R.id.button_delete);

                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Delete button is called"+adapter.getMahasiswaAt(position).getMahasiswaName() , Toast.LENGTH_SHORT).show();

                                mMahasiswaViewModel.delete(adapter.getMahasiswaAt(position));
                            }
                        });

                        editButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Edit button is called"+adapter.getMahasiswaAt(position).getMahasiswaName() , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, NewMahasiswaActivity.class);
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_ID, adapter.getMahasiswaAt(position).getMahasiswaId());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_NIM, adapter.getMahasiswaAt(position).getMahasiswaName());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_EMAIL, adapter.getMahasiswaAt(position).getMahasiswaEmail());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_NIM,  Integer.toString(adapter.getMahasiswaAt(position).getMahasiswaNim()));
                                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST_CODE);
                            }
                        });
                    }
                })
        );


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String mahasiswaName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_NAME);
            String mahasiswaEmail = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_EMAIL);
            String mahasiswaNim = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_NIM);
            int nim = Integer.parseInt(mahasiswaNim);

            Mahasiswa mahasiswa = new Mahasiswa(mahasiswaName,mahasiswaEmail,nim);
            mMahasiswaViewModel.insert(mahasiswa);
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int id = data.getIntExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String mahasiswaName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_NAME);
            String mahasiswaEmail = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_EMAIL);
            String mahasiswaNim = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_MAHASISWA_NIM);
            int nim = Integer.parseInt(mahasiswaNim);

            Mahasiswa mahasiswa = new Mahasiswa(mahasiswaName,mahasiswaEmail,nim);
            mahasiswa.setMahasiswaId(id);
            mMahasiswaViewModel.update(mahasiswa);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }



}
