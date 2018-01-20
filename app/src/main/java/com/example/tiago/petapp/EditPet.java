package com.example.tiago.petapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditPet extends Fragment {

    EditText editNome;
    EditText editIdade;
    EditText editRaça;
    EditText editEspecie;
    TextView textNome;
    Button buttonEdit;
    Toolbar toolbar;
    String[] osDetalhes;
    protected AdaptadorBaseDados a;


    public EditPet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_pet, container, false);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Adicionar Animal");

        //Variaveis
        textNome = (TextView) rootView.findViewById(R.id.textView2);
        editNome = (EditText) rootView.findViewById(R.id.editNome);
        editRaça = (EditText) rootView.findViewById(R.id.editRaça);
        editIdade = (EditText) rootView.findViewById(R.id.editIdade);
        editEspecie = (EditText) rootView.findViewById(R.id.editEspecie);
        buttonEdit = (Button) rootView.findViewById(R.id.buttonEditPet);

        //Spinner
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.animals_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/
        a = new AdaptadorBaseDados(getActivity()).open();
        final String id = getArguments().getString("id");
        osDetalhes = a.obterDetalhesRegisto(id);


        editNome.setText(osDetalhes[1]);
        editRaça.setText(osDetalhes[4]);
        editIdade.setText(osDetalhes[2]);
        editEspecie.setText(osDetalhes[3]);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a.updatePet(Integer.parseInt(id), editNome.getText().toString(), editEspecie.getText().toString(),
                        editRaça.getText().toString(), editIdade.getText().toString());

                CharSequence text = "Registo Editado!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getActivity(), text, duration);
                toast.show();

                Fragment fragment = new PetDetails();
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


        // Inflate the layout for this fragment
        return rootView;
    }




}