package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

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
        loadAllData();
        //list_of_players = loadAllData();

    }
    public void onSaveButtonClick(View w)
    {
        String name =eName.getText().toString();
        String lastName= eLastName.getText().toString();
        String birthday = eBirthday.getText().toString();
        String sport = eSport.getText().toString();
        PlayerTask playerTask = new PlayerTask(name,lastName,birthday,sport);
        insertPlayerData(playerTask);
        loadAllData();
    }

    public void clearData(View view) {
        nukePlayerData();

    }
    //using live data to load an update database
    // any moment when there is a change in a database onChanged will be called and update the UI !!!
    public void loadAllData(){
        LiveData<List<PlayerTask>> tasks = pDB.getPlayerDao().loadAllTasks();
        tasks.observe(this, new Observer<List<PlayerTask>>() {
            @Override
            public void onChanged(List<PlayerTask> playerTasks) {
                list_of_players= playerTasks;
                updateUI();
            }
        });
    }
    public void insertPlayerData(final PlayerTask playerTask){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                pDB.getPlayerDao().insertTask(playerTask);
            }
        });
    }
    public void nukePlayerData(){
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                pDB.getPlayerDao().nukeTable();
            }

        });
    }
    public void updateUI(){
        myAdapter = new MyAdapter(getApplicationContext(),0,list_of_players);
        listView.setAdapter(myAdapter);
    }


}
