package com.example.tiago.petapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultaFragment extends Fragment {


    public ConsultaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_consulta, container, false);
        //Mostrar todos os animais desse utilizador
        Button b = (Button) rootView.findViewById(R.id.buttonA);
        // Inflate the layout for this fragment
        return rootView;
    }


}
