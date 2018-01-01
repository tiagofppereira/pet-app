package com.example.tiago.petapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultaDetails extends Fragment {

    TextView razao, data, hora, id_animal;
    protected AdaptadorBaseDados a;
    String[] osDetails;
    String[] oAnimal;
    Toolbar toolbar;

    public ConsultaDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_consulta_details, container, false);
        //Mostrar todos os animais desse utilizador
        a = new AdaptadorBaseDados(getActivity()).open();
        String id = getArguments().getString("id");
        osDetails = a.obterDetalhesConsulta(id);
        oAnimal = a.obterDetalhesRegisto(osDetails[4]);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(String.valueOf(osDetails[1]));

        data = (TextView) rootView.findViewById(R.id.data);
        hora = (TextView) rootView.findViewById(R.id.hora);
        id_animal = (TextView) rootView.findViewById(R.id.id_animal);
        razao = (TextView) rootView.findViewById(R.id.razao);

        data.setText(String.valueOf(osDetails[2]));
        hora.setText(String.valueOf(osDetails[3]));
        id_animal.setText(String.valueOf(oAnimal[1]));
        razao.setText(String.valueOf(osDetails[1]));

        return rootView;
    }


}