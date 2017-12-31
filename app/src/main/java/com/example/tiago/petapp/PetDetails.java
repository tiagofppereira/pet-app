package com.example.tiago.petapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetDetails extends Fragment {

    TextView nome, raca, especie, idade;
    protected AdaptadorBaseDados a;
    String[] osDetails;
    Toolbar toolbar;

    public PetDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pet_details, container, false);
        //Mostrar todos os animais desse utilizador
        a = new AdaptadorBaseDados(getActivity()).open();
        String id = getArguments().getString("id");
        osDetails = a.obterDetalhesRegisto(id);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(String.valueOf(osDetails[1]));

        nome = (TextView) rootView.findViewById(R.id.nome);
        raca = (TextView) rootView.findViewById(R.id.raca);
        especie = (TextView) rootView.findViewById(R.id.especie);
        idade = (TextView) rootView.findViewById(R.id.idade);

        nome.setText(String.valueOf(osDetails[1]));
        idade.setText(String.valueOf(osDetails[2]));
        especie.setText(String.valueOf(osDetails[3]));
        raca.setText(String.valueOf(osDetails[4]));

        return rootView;
    }


}
