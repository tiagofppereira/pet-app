package com.example.tiago.petapp;



import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddConsultaActivity extends Fragment {

    EditText editNome;
    EditText editHora;
    EditText editData;
    Spinner spinnerAnimal;
    Button buttonInsert, buttonTimePicker, buttonDatePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Toolbar toolbar;

    public AddConsultaActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_add_consulta, container, false);

        /*final Button mainButton = (Button) rootView.findViewById(R.id.main_button);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainButton.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
            }
        });*/

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Adicionar Consulta");

        //Variaveis
        editData = (EditText) rootView.findViewById(R.id.editData);
        editHora = (EditText) rootView.findViewById(R.id.editHora);
        spinnerAnimal = (Spinner) rootView.findViewById(R.id.spinnerAnimal);
        buttonTimePicker = (Button) rootView.findViewById(R.id.buttonTimePicker);
        buttonDatePicker = (Button) rootView.findViewById(R.id.buttonDatePicker);


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

                        editData.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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