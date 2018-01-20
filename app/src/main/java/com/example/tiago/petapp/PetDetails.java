package com.example.tiago.petapp;

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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetDetails extends Fragment {

    TextView nome, raca, especie, idade;
    protected AdaptadorBaseDados a;
    Button buttonDelete, buttonEdit;
    String[] osDetails;
    Toolbar toolbar;
    ListView listCons;
    List asCons;
    String[] consData;

    public PetDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pet_details, container, false);
        //Mostrar todos os animais desse utilizador
        a = new AdaptadorBaseDados(getActivity()).open();
        final String id = getArguments().getString("id");
        osDetails = a.obterDetalhesRegisto(id);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(String.valueOf(osDetails[1]));
        buttonDelete = (Button) rootView.findViewById(R.id.buttonDelete);
        buttonEdit = (Button) rootView.findViewById(R.id.buttonEdit);
        nome = (TextView) rootView.findViewById(R.id.nome);
        raca = (TextView) rootView.findViewById(R.id.raca);
        especie = (TextView) rootView.findViewById(R.id.especie);
        idade = (TextView) rootView.findViewById(R.id.idade);

        nome.setText(String.valueOf(osDetails[1]));
        idade.setText(String.valueOf(osDetails[2]));
        especie.setText(String.valueOf(osDetails[3]));
        raca.setText(String.valueOf(osDetails[4]));

        //Consultas
        listCons = (ListView) rootView.findViewById(R.id.consultasAnim);
        asCons = a.obterConsultasAnimal(osDetails[0]);

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
                int total = listCons.getAdapter().getCount();

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

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Fragment fragment = new EditPet();
                Bundle bundle = new Bundle();
                bundle.putString("id", (id));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a.deletePet(id);

                CharSequence text = "Registo Eliminado";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getActivity(), text, duration);
                toast.show();

                Fragment fragment = new MainFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });



        return rootView;
    }


}
