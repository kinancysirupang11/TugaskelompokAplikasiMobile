package com.kinan.SQLite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private EditText input, update;
    private CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //inisialisasi component
        text = findViewById(R.id.text);
        input = findViewById(R.id.input);
        update = findViewById(R.id.update);

        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        crud = new CRUD(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.getText().toString().isEmpty()) {
                    boolean create = crud.createData(input.getText().toString());
                    if (create) {
                        sukses("Create");
                    } else {
                        gagal("Create");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Nilai Input Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validasi(false)) {
                    boolean updates = crud.updateData(update.getText().toString(), input.getText().toString());
                    if (updates) {
                        sukses("Update");
                    } else {
                        gagal("Update");
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validasi(true)) {
                    boolean delete = crud.deleteData(input.getText().toString());
                    if (delete) {
                        sukses("Delete");
                    } else {
                        gagal("Delete");
                    }
                }
            }
        });

        viewData();
    }

    private boolean validasi(boolean isDelete) {
        String txtInput = input.getText().toString();
        String txtUpdateDelete = update.getText().toString();

        //nilai input & update/delete tidak boleh kosong
        if (txtInput.isEmpty()) {
            Toast.makeText(this, "Nilai Input Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!isDelete && txtUpdateDelete.isEmpty()) {
            Toast.makeText(this, "Nilai Update Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            return false;
        }

        //nilai input harus ada di database sebelumnya
        if (!crud.getDataByInput(txtInput)) {
            Toast.makeText(this, "Nilai Input Tidak Ditemukan Didatabase", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void sukses(String info) {
        Toast.makeText(MainActivity.this, info + " Berhasil", Toast.LENGTH_LONG).show();
        input.setText("");
        update.setText("");
        viewData();
    }

    private void gagal(String info) {
        Toast.makeText(MainActivity.this, info + " Gagal", Toast.LENGTH_LONG).show();
    }

    private void viewData() {
        text.setText(crud.getData() != null ? crud.getData() : "-");
    }
}
