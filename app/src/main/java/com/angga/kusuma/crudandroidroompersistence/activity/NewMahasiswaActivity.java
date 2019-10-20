package com.angga.kusuma.crudandroidroompersistence.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.angga.kusuma.crudandroidroompersistence.R;

public class NewMahasiswaActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_MAHASISWA_ID = "com.kayum.mamun.roomapp.EXTRA_ID";
    public static final String EXTRA_REPLY_MAHASISWA_NAME = "com.kayum.mamun.roomapp.NAME";
    public static final String EXTRA_REPLY_MAHASISWA_EMAIL = "com.kayum.mamun.roomapp.EMAIL";
    public static final String EXTRA_REPLY_MAHASISWA_NIM = "com.kayum.mamun.roomapp.NIM";

    private EditText mEditMahasiswaNameView, mEditMahasiswaEmailView, mEditMahasiswaNimView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mahasiswa);

        mEditMahasiswaNameView = findViewById(R.id.edit_mahasiswa_name);
        mEditMahasiswaEmailView = findViewById(R.id.edit_mahasiswa_email);
        mEditMahasiswaNimView = findViewById(R.id.edit_mahasiswa_nim);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_REPLY_MAHASISWA_ID)) {
            setTitle("Edit Mahasiswa");
            mEditMahasiswaNameView.setText(intent.getStringExtra(EXTRA_REPLY_MAHASISWA_NAME));
            mEditMahasiswaEmailView.setText(intent.getStringExtra(EXTRA_REPLY_MAHASISWA_EMAIL));
            mEditMahasiswaNimView.setText(intent.getStringExtra(EXTRA_REPLY_MAHASISWA_NIM));

        } else {
            setTitle("Tambah Mahasiswa");
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditMahasiswaNameView.getText()) || TextUtils.isEmpty(mEditMahasiswaEmailView.getText()) || TextUtils.isEmpty(mEditMahasiswaNimView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String mahasiswaName = mEditMahasiswaNameView.getText().toString();
                    String mahasiswaEmail = mEditMahasiswaEmailView.getText().toString();
                    String mahasiswaNim = mEditMahasiswaNimView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_MAHASISWA_NAME, mahasiswaName);
                    replyIntent.putExtra(EXTRA_REPLY_MAHASISWA_EMAIL, mahasiswaEmail);
                    replyIntent.putExtra(EXTRA_REPLY_MAHASISWA_NIM, mahasiswaNim);

                    int id = getIntent().getIntExtra(EXTRA_REPLY_MAHASISWA_ID, -1);
                    if (id != -1) {
                        replyIntent.putExtra(EXTRA_REPLY_MAHASISWA_ID, id);
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
