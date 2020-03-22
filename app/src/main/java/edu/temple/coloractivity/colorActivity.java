package edu.temple.coloractivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class colorActivity extends AppCompatActivity implements PaletteFragment.OnFragmentInteractionListener
,CanvasFragment.OnFragmentInteractionListener{
    //String color[] = {"blue", "red", "green", "yellow", "purple", "orange", "black", "space", "orchid", "ocean", "white", "brown"};

    colorAdapter Adapter;
   public static RelativeLayout Layout;
   public static String colorChoice;
   public static String colorName;
   String firstTime="yes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // open PaletteFragment on colorActivity launch
        CallFragment1(colorActivity.this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

  //  Create one instance of your PaletteFragment and attach it to the activity in a container (container1).
    public static void CallFragment1(Context context){
        if (context != null){
            FragmentTransaction transaction= ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container1, new PaletteFragment()); //this is container1
            transaction.commit();
        }
    }




    @Override
    public void onBackPressed() {
        //create instance of PaletteFragment
        Fragment f1 = getSupportFragmentManager().findFragmentById(R.id.container1);
        //create instance of CanvasFragment
        Fragment f2 = getSupportFragmentManager().findFragmentById(R.id.container2);
        //close CanvasFragment on back click
        if (f2 instanceof CanvasFragment) {
            getSupportFragmentManager().beginTransaction().remove(f2).commit();
        }
        //close PaletteFragment on back click
        else if (f1 instanceof PaletteFragment) {
            getSupportFragmentManager().beginTransaction().remove(f1).commit();
        }
        // close app on click of back
        else {
            super.onBackPressed();
        }
    }
}