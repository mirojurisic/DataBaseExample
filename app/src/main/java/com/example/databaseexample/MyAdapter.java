package com.example.databaseexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter {
    List<PlayerTask> list;
    Context context;
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        convertView =(View) LayoutInflater.from(container.getContext())
                .inflate(R.layout.single_player, container, false);
        PlayerTask playerTask = list.get(position);
        ((TextView)convertView.findViewById(R.id.name_TV)).setText(playerTask.getName());
        ((TextView)convertView.findViewById(R.id.sport_TV)).setText(playerTask.getSport());
        ((TextView)convertView.findViewById(R.id.lastName_TV)).setText(playerTask.getLastName());
        ((TextView)convertView.findViewById(R.id.birth_TV)).setText(playerTask.getBirthday());
        return convertView;
    }

    public MyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        list = objects;

    }
    public void setPlayerList(List<PlayerTask> list){
        this.list= list;
        notifyDataSetChanged();
    }
}
