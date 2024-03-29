package com.example.customlistview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<rowitem> list;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        list.add(new rowitem(R.drawable.download, "first"));
        list.add(new rowitem(R.drawable.dnld2, "second"));
        list.add(new rowitem(R.drawable.download3, "third"));
        list.add(new rowitem(R.drawable.dnd4, "fourth"));
        list.add(new rowitem(R.drawable.dnd5, "fifth"));
        //list.remove(0);
        final adapter myadapter = new adapter(this, R.layout.customview, list);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myadapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, position + " selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Do you want to delete?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(position);
                                myadapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this,"Item deleted successfully",Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        return false;
                    }
                }
        );
    }
}


