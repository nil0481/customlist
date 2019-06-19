package com.example.customlistview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapter extends ArrayAdapter<rowitem> {
    //List<rowitem> rowitems;
    Context context;
    int resource;
    List<rowitem> list;

    public adapter(Context context, int resource, List<rowitem> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        rowitem rowitem = list.get(position);
        textView.setText(rowitem.getName());
        imageView.setImageDrawable(context.getResources().getDrawable(rowitem.getImage()));

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeitem(position);
            }
        });


        return view;
    }

    private void removeitem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Do you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    list.remove(position);
                    notifyDataSetChanged();

                } catch (Exception e) {

                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}
