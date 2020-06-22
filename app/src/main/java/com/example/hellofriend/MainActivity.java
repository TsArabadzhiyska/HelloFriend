package com.example.hellofriend;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseHelper dbHelper;
    //CustomRecyclerViewAdapter използва тези променливи
    ArrayList<String> contact_id, name, phone, hint;
    CustomRecyclerViewAdapter customRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DatabaseHelper(MainActivity.this);

        contact_id = new ArrayList<>();
        name = new ArrayList<>();
        phone = new ArrayList<>();
        hint = new ArrayList<>();

        storeDataInArrays();

        customRecyclerViewAdapter = new CustomRecyclerViewAdapter(MainActivity.this,this,contact_id, name, phone, hint);
        recyclerView.setAdapter(customRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = dbHelper.readAllData();
        while (cursor.moveToNext()){
             contact_id.add(cursor.getString(0));
             name.add(cursor.getString(1));
             phone.add(cursor.getString(2));
             hint.add(cursor.getString(3));
        }
    }
}
