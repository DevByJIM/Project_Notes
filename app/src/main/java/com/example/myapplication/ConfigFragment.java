package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnTitleColor, btnContentColor, btnPrimaryColor, btnSecondaryColor;

    public ConfigFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ConfigFragment newInstance(String param1, String param2) {
        ConfigFragment fragment = new ConfigFragment();
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


    public void onClic(View view){
        switch(view.getId()){
            case R.id.btnTitleColour:
                DameColor(getActivity().getResources().getColor(R.color.titleColour),R.color.titleColour);
                break;
            case R.id.btnContentColour:
                DameColor(getActivity().getResources().getColor(R.color.textColor),R.color.textColor);
                break;
            case R.id.btnPrimaryColour:
                DameColor(getActivity().getResources().getColor(R.color.primary_200),R.color.primary_200);
                break;
            case R.id.btnSecondaryColour:
                DameColor(getActivity().getResources().getColor(R.color.primary_500),R.color.primary_500);
                break;
        }
    }

    private void DameColor(int currentColor,int idRecurso){
        ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Choose color")
                .initialColor(currentColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        guardarCambio(idRecurso,selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                })
                .build()
                .show();



    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_config, container, false);


        btnTitleColor = ((Button)view.findViewById(R.id.btnTitleColour));

        btnTitleColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClic(v);
            }
        });

        btnContentColor = ((Button)view.findViewById(R.id.btnContentColour));

        btnContentColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClic(v);
            }
        });

        btnPrimaryColor = ((Button)view.findViewById(R.id.btnPrimaryColour));

        btnPrimaryColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClic(v);
            }
        });

        btnSecondaryColor = ((Button)view.findViewById(R.id.btnSecondaryColour));

        btnSecondaryColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClic(v);
            }
        });

    actualizarVisual();



        return view;


    }

    public void actualizarVisual()
    {
        btnTitleColor.setBackgroundColor(getActivity().getResources().getColor(R.color.titleColour));
        btnContentColor.setBackgroundColor(getActivity().getResources().getColor(R.color.textColor));
        btnPrimaryColor.setBackgroundColor(getActivity().getResources().getColor(R.color.primary_200));
        btnSecondaryColor.setBackgroundColor(getActivity().getResources().getColor(R.color.primary_500));
    }


    public void guardarCambio(int idModificar,int valor)
    {
        SharedPreferences.Editor edit = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).edit();

        edit.putInt(idModificar+"",valor);

        edit.apply();
        edit.commit();

        actualizarVisual();
    }


}