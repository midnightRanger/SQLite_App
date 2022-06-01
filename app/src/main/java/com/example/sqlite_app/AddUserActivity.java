package com.example.sqlite_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {

    DatabaseHelper database;
    EditText nameET, phoneET, birthdateET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        database = new DatabaseHelper(this);
        nameET = findViewById(R.id.name_et);
        phoneET = findViewById(R.id.phone_et);
        birthdateET = findViewById(R.id.birthdate_et);
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(v -> add());
    }

    private void add() {
        String name = nameET.getText().toString();
        String phone = phoneET.getText().toString();
        String birthdate = birthdateET.getText().toString();

        if (name.trim().isEmpty() || phone.trim().isEmpty() || birthdate.trim().isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!database.insert(name, phone, birthdate)) {
            Toast.makeText(this, "Не удалось добавить пользователя", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
