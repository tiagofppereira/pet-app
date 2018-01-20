package com.example.tiago.petapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditConsulta extends Fragment {

    EditText editRazao;
    EditText editHora;
    EditText editData;
    EditText spinnerAnimal;
    TextView editAnimal;
    Button buttonInsert, buttonTimePicker, buttonDatePicker, buttonEdit;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String[] osDetails;
    String[] oAnimal;
    Toolbar toolbar;
    protected AdaptadorBaseDados a;
    List pets;
    String[] anim;
    int id;



    public EditConsulta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_consulta, container, false);

        a = new AdaptadorBaseDados(getActivity()).open();

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Adicionar Consulta");

        //Variaveis
        editRazao = (EditText) rootView.findViewById(R.id.editRazao);
        editData = (EditText) rootView.findViewById(R.id.editData);
        editHora = (EditText) rootView.findViewById(R.id.editHora);
        editAnimal = (TextView) rootView.findViewById(R.id.editAnimal);
        buttonTimePicker = (Button) rootView.findViewById(R.id.buttonTimePicker);
        buttonDatePicker = (Button) rootView.findViewById(R.id.buttonDatePicker);
        buttonInsert = (Button) rootView.findViewById(R.id.buttonAddConsulta);


        //Spinner

        pets = a.obterAnimais();
        pets.toArray(new String[0]);

        final String id = getArguments().getString("id");
        osDetails = a.obterDetalhesConsulta(id);
        oAnimal = a.obterDetalhesRegisto(osDetails[4]);

        editAnimal.setText(String.valueOf(oAnimal[1]));


        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnTimePicker();
            }
        });

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDatePickerMethod();
            }
        });

        final int oId = Integer.parseInt(String.valueOf(osDetails[0]));

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.updateConsulta(oId, editRazao.getText().toString(),
                        editData.getText().toString(), editHora.getText().toString());

                CharSequence text = "Consulta Editada!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getActivity(), text, duration);
                toast.show();

                Fragment fragment = new ConsultaDetails();
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

    public void btnDatePickerMethod(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        editData.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void btnTimePicker() {

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        editHora.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();

    }
}