package com.example.gkdoe.skate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArticleActivity extends AppCompatActivity {

    private  String[] data = {"Ollie","T-Tac","Kickflip","Shove it","Pop Shove it","Power Slide","Manual","Pressure Flip",
            "Wall Ride","Acid Drop","Impossible","No Comply","Boneless"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ArticleActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_View);
        listView.setAdapter(adapter);
    }
}
