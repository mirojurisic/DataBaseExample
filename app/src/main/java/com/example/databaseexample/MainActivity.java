package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    PlayerDatabase pDB;
    EditText eName;
    EditText eLastName;
    EditText eBirthday;
    EditText eSport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName = findViewById(R.id.editTextName);
        eLastName = findViewById(R.id.editTextLast);
        eBirthday = findViewById(R.id.editTextBirth);
        eSport = findViewById(R.id.editTextSport);
        pDB = PlayerDatabase.getInstance(this);
    }
    public void onSaveButtonClick()
    {
        String name =eName.getText().toString();
        String lastName= eLastName.getText().toString();
        String birthday = eBirthday.getText().toString();
        String sport = eSport.getText().toString();
        PlayerTask playerTask = new PlayerTask(name,lastName,birthday,sport);
        pDB.getPlayerDao().insertTask(playerTask);
        finish();

    }
}
