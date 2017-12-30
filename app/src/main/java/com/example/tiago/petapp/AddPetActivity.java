package com.example.tiago.petapp;



import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPetActivity extends Fragment {

    EditText editNome;
    EditText editIdade;
    EditText editRaça;
    EditText editEspecie;
    TextView textNome;
    Button buttonInsert;
    Toolbar toolbar;
    protected AdaptadorBaseDados a;

    public AddPetActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_add_pet, container, false);
        a = new AdaptadorBaseDados(getActivity()).open();
        /*final Button mainButton = (Button) rootView.findViewById(R.id.main_button);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainButton.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
            }
        });*/

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Adicionar Animal");

        //Variaveis
        textNome = (TextView) rootView.findViewById(R.id.textView2);
        editNome = (EditText) rootView.findViewById(R.id.editNome);
        editRaça = (EditText) rootView.findViewById(R.id.editRaça);
        editIdade = (EditText) rootView.findViewById(R.id.editIdade);
        buttonInsert = (Button) rootView.findViewById(R.id.buttonInsert);
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerEspecie);

        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.animals_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                a.inserirPet(editNome.getText().toString(),
                        editIdade.getText().toString(), spinner.getSelectedItem().toString(),
                        editRaça.getText().toString()
                );
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }




}
