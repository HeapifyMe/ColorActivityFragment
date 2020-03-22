package edu.temple.coloractivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaletteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaletteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

// Your PaletteFragment class will allow a user to select a color.
    // the CanvasFragment will have the ability to change its background color.
public class PaletteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PaletteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaletteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaletteFragment newInstance(String param1, String param2) {
        PaletteFragment fragment = new PaletteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    colorAdapter Adapter;
    public static RelativeLayout Layout;
    public static String colorChoice;
    public static String colorName;
    String firstTime="yes";
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view=    inflater.inflate(R.layout.fragment_palette, container, false);
        Resources res = getResources();
        final String[] colorids = res.getStringArray(R.array.colorids);
        context=getActivity();
        String[] color = res.getStringArray(R.array.colors);
        Spinner spinner = view.findViewById(R.id.spinner1);
        Adapter = new colorAdapter(context, color, colorids);
        spinner.setAdapter(Adapter);
        Layout = view.findViewById(R.id.layout1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colorChoice = parent.getSelectedItem().toString();
                view.findViewById(R.id.tvColor).setBackgroundColor(Color.WHITE);
                if(firstTime.equals("yes")){
                    firstTime="no";
                    Layout.setBackgroundColor(Color.parseColor(colorChoice));
                }else {
                    Layout.setBackgroundColor(Color.parseColor(colorChoice));

                    Layout.setBackgroundColor(Color.parseColor(colorids[position]));
                    colorActivity.colorChoice=colorids[position];
//                    Intent i=new Intent(context,CanvasActivity.class);
//                    startActivity(i);

                    //launch CanvasFragment after selecting color from Spinner
                    CallFragment2(context);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view; }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //  Create one instance of your CanvasFragment and attach it to the activity in a container (container2).
    public static void CallFragment2(Context context ){
        if (context != null){
            FragmentTransaction transaction= ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container2, new CanvasFragment());
            transaction.commit();
        }
    }
}
