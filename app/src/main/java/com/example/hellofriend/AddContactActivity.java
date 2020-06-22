package com.example.hellofriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    EditText name_input, phone_input, hint_input;
    Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name_input = findViewById(R.id.name_input);
        phone_input = findViewById(R.id.phone_input);
        hint_input = findViewById(R.id.hint_input);
        save_button = findViewById(R.id.add_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(AddContactActivity.this);
                dbHelper.addContact(name_input.getText().toString().trim(),
                        phone_input.getText().toString().trim(),
                        hint_input.getText().toString().trim());
                finish();
                //Връща потребителя към MainActivity. Без този код MainActivity не се обновява в случая
                Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}