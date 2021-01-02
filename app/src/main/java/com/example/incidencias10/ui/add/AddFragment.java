package com.example.incidencias10.ui.add;

import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.incidencias10.DB.IncidenciaDBHelper;
import com.example.incidencias10.R;
import com.example.incidencias10.databinding.FragmentAddBinding;


public class AddFragment extends Fragment {

    IncidenciaDBHelper dbHelper;
    SQLiteDatabase db;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View addIncidencia = inflater.inflate(R.layout.fragment_add, container, false);

        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(requireActivity().getApplicationContext());
        db = dbHelper.getWritableDatabase();

        AutoCompleteTextView dropDownText = addIncidencia.findViewById(R.id.dropdown_text);
        AutoCompleteTextView dropDownText2 = addIncidencia.findViewById(R.id.dropdown_text2);

        String[ ] labelsImpotancia;
        Resources res1 = getResources();
        labelsImpotancia = res1.getStringArray( R.array.Incidents ) ;

        String[ ] labelsEstado;
        Resources res = getResources();
        labelsEstado = res.getStringArray( R.array.State ) ;

        ArrayAdapter<String> adapterIncidents = new ArrayAdapter<>(
                addIncidencia.getContext(),
                R.layout.dropdown_item,
                labelsImpotancia
        );
        dropDownText.setAdapter(adapterIncidents);

        ArrayAdapter<String> adapterState = new ArrayAdapter<>(
                addIncidencia.getContext(),
                R.layout.dropdown_item,
                labelsEstado
        );
        dropDownText2.setAdapter(adapterState);

        final Button btnsave = addIncidencia.findViewById(R.id.btnSave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTitle = addIncidencia.findViewById(R.id.textTitle);
                String title = txtTitle.getText().toString();

                EditText txtDescription = addIncidencia.findViewById(R.id.textDecription);
                String description = txtDescription.getText().toString();

                String item = dropDownText.getText().toString();
                String item2 = dropDownText2.getText().toString();

                dbHelper.insertIncidencia(db,title,item,description,item,"Date");

                Toast.makeText(getActivity(),"Saved!",Toast.LENGTH_SHORT).show();
            }
        });

        return addIncidencia;
    }

    @Override
    public void onDestroyView() {
        dbHelper.close();
        db.close();
        super.onDestroyView();
    }
}