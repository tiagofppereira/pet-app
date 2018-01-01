package com.example.tiago.petapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment {

    Button addPet;
    Button addConsulta;
    Toolbar toolbar;
    ListView list;
    List osPets, asCons, asHoras;
    ListView listCons;
    protected AdaptadorBaseDados a;
    String[] consData;

    private void executarOutraActivity(Class<?> subActividade, int oValor) {
        Intent x = new Intent(getActivity(), subActividade);
        x.putExtra("umInt", oValor);
        startActivity(x);
    }

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Pet App");

        addPet = (Button) rootView.findViewById(R.id.buttonAddPet);
        addConsulta = (Button) rootView.findViewById(R.id.buttonAddCons);

        addPet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new AddPetActivity();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        addConsulta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new AddConsultaActivity();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        a = new AdaptadorBaseDados(getActivity()).open();

        //Animais
        osPets = a.obterAnimais();
        list = (ListView) rootView.findViewById(R.id.listPet);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, osPets);


        // Assign adapter to ListView
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue = (String) list.getItemAtPosition(position);

                //a.obterDetalhesRegisto(id);

                Fragment fragment = new PetDetails();
                Bundle bundle = new Bundle();
                bundle.putString("id", (Integer.toString(position + 1)));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        //Consultas
        listCons = (ListView) rootView.findViewById(R.id.listCons);
        asCons = a.obterConsultas();

        ArrayAdapter<String> adapterCons = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, asCons);


        // Assign adapter to ListView
        listCons.setAdapter(adapterCons);

        listCons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                consData = a.obterDetalhesConsultaData((String) listCons.getItemAtPosition(position));
                //a.obterDetalhesRegisto(id);

                Fragment fragment = new ConsultaDetails();
                Bundle bundle = new Bundle();
                bundle.putString("id", (consData[0]));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });






        // Inflate the layout for this fragment
        return rootView;
    }


}

