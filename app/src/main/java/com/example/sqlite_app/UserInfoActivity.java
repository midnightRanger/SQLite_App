package com.example.sqlite_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    private int id;
    EditText nameET, phoneET, birthdateET;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        database = new DatabaseHelper(this);
        User user = getIntent().getParcelableExtra("user");
        TextView idTV = findViewById(R.id.id_tv);
        nameET = findViewById(R.id.name_et);
        phoneET = findViewById(R.id.phone_et);
        birthdateET = findViewById(R.id.birthdate_et);
        Button save = findViewById(R.id.save_btn);
        Button delete = findViewById(R.id.delete_btn);

        id = user.id;
        idTV.setText(String.format("ID: %s", id));
        nameET.setText(user.name);
        phoneET.setText(user.phone);
        birthdateET.setText(user.birthdate);
        save.setOnClickListener(v -> save());
        delete.setOnClickListener(v -> delete());
    }

    private void save() {
        String name = nameET.getText().toString();
        String phone = phoneET.getText().toString();
        String birthdate = birthdateET.getText().toString();
        database.edit(id, name, phone, birthdate);
        finish();
    }

    private void delete() {
        database.delete(id);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}