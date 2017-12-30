package com.example.tiago.petapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetFragment extends Fragment {

    ListView list;
    List osPets;
    protected AdaptadorBaseDados a;

    public PetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pet, container, false);
        //Mostrar todos os animais desse utilizador
        a = new AdaptadorBaseDados(getActivity()).open();
        osPets = a.obterAnimais();

        list = (ListView) rootView.findViewById(R.id.listA);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, osPets);


        // Assign adapter to ListView
        list.setAdapter(adapter);


        // Inflate the layout for this fragment
        return rootView;
    }


}
