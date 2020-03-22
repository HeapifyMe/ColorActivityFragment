package edu.temple.coloractivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class colorAdapter extends BaseAdapter {
    private Context colorActivity;
    private String[] color;
    private String[] colorids;
    private String[] color_name;
    public colorAdapter(Context context, String[] color, String[] colorids)
    {
        this.colorActivity = context;
        this.color=color;
        this.colorids = colorids;
        //this.color_name = color_name;
    }

    @Override
    public int getCount() {
        //returns how many views it'll generate
        return colorids.length;
    }

    @Override
    public Object getItem(int position) {
        //returns item in the position of arraylist
        return colorids[position];
    }

    @Override
    public long getItemId(int position) {
        //returns index
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View colorView;
        if (convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(colorActivity);
            colorView = inflater.inflate(R.layout.spinner_item,parent,false);

        }
        else
            colorView=convertView;
        TextView colorChoice = colorView.findViewById(R.id.tvColor);
        colorChoice.setText(color[position]);
        colorChoice.setBackgroundColor(Color.parseColor(colorids[position]));

//        colorView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                colorActivity.Layout.setBackgroundColor(Color.parseColor(colorids[position]));
//                colorActivity.colorChoice=colorids[position];
//                Intent i=new Intent(colorActivity,CanvasActivity.class);
//                colorActivity.startActivity(i);
//            }
//        });

        return colorView;

    }
}
