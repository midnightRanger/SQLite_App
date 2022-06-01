package com.example.sqlite_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements UserAdapter.ItemClickListener {

    RecyclerView userRecyclerView;
    Button insert;
    DatabaseHelper databaseHelper;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        userRecyclerView = findViewById(R.id.users_rv);
        insert = findViewById(R.id.insert_btn);
        insert.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddUserActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        users = databaseHelper.getUsers();
        UserAdapter adapter = new UserAdapter(this, users, this);
        userRecyclerView.setAdapter(adapter);
    }

    @Override
    public void OnUserClick(int position) {
        Intent intent = new Intent(this, UserInfoActivity.class);
        User toPut = users.get(position);
        intent.putExtra("user", toPut);
        startActivity(intent);
    }
}