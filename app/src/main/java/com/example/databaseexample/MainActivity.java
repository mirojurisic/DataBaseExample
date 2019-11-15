package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    PlayerDatabase pDB;
    EditText eName;
    EditText eLastName;
    EditText eBirthday;
    EditText eSport;
    ListView listView;
    List<PlayerTask> list_of_players;
    MyAdapter myAdapter;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName = findViewById(R.id.editTextName);
        eLastName = findViewById(R.id.editTextLast);
        eBirthday = findViewById(R.id.editTextBirth);
        eSport = findViewById(R.id.editTextSport);
        pDB = PlayerDatabase.getInstance(getApplicationContext());
        listView = findViewById(R.id.listView);
        list_of_players = new ArrayList<>();
        list_of_players = pDB.getPlayerDao().loadAllTasks();
        myAdapter = new MyAdapter(getApplicationContext(),0,list_of_players);
        listView.setAdapter(myAdapter);
    }
    public void onSaveButtonClick(View w)
    {
        String name =eName.getText().toString();
        String lastName= eLastName.getText().toString();
        String birthday = eBirthday.getText().toString();
        String sport = eSport.getText().toString();
        PlayerTask playerTask = new PlayerTask(name,lastName,birthday,sport);
        pDB.getPlayerDao().insertTask(playerTask);
        list_of_players = pDB.getPlayerDao().loadAllTasks();
        myAdapter = new MyAdapter(getApplicationContext(),0,list_of_players);
        listView.setAdapter(myAdapter);
    }

    public void clearData(View view) {
        pDB.getPlayerDao().nukeTable();
        list_of_players = pDB.getPlayerDao().loadAllTasks();
        myAdapter = new MyAdapter(getApplicationContext(),0,list_of_players);
        listView.setAdapter(myAdapter);
    }
}
