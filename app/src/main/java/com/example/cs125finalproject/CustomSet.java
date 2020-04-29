package com.example.cs125finalproject;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CustomSet extends AppCompatActivity {
    private RecyclerView xrecyclerView;
    private RecyclerView.Adapter xadapter;
    private RecyclerView.LayoutManager xlayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_game);
        ArrayList<CustomCard> cardSet = new ArrayList<CustomCard>();
        cardSet.add(0, new CustomCard("sample", 0));


    }
}
