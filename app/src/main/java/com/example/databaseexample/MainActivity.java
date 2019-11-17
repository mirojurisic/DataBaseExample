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
        list_of_players = loadAllData();
    }
    public void onSaveButtonClick(View w)
    {
        String name =eName.getText().toString();
        String lastName= eLastName.getText().toString();
        String birthday = eBirthday.getText().toString();
        String sport = eSport.getText().toString();
        PlayerTask playerTask = new PlayerTask(name,lastName,birthday,sport);
        insertPlayerData(playerTask);
        list_of_players = loadAllData();
    }

    public void clearData(View view) {
        nukePlayerData();

    }
    public List<PlayerTask> loadAllData(){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                list_of_players = pDB.getPlayerDao().loadAllTasks();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                    }
                });
            }
        });
        return list_of_players;
    }
    public void insertPlayerData(final PlayerTask playerTask){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                pDB.getPlayerDao().insertTask(playerTask);
                list_of_players = pDB.getPlayerDao().loadAllTasks();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                    }
                });
            }
        });
    }
    public void nukePlayerData(){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                pDB.getPlayerDao().nukeTable();
                list_of_players = pDB.getPlayerDao().loadAllTasks();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                    }
                });
            }

        });
    }
    public void updateUI(){
        myAdapter = new MyAdapter(getApplicationContext(),0,list_of_players);
        listView.setAdapter(myAdapter);
    }


}
