package com.example.incidencias10.ui.preferences;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.incidencias10.MainActivity;
import com.example.incidencias10.R;

import java.util.Locale;
import java.util.Objects;
import static com.example.incidencias10.DB.IncidenciaDBHelper.DATABASE_NAME;


public class PreferencesFragment extends Fragment {

    public PreferencesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View addIncidencia = inflater.inflate(R.layout.fragment_preferences, container, false);

        AutoCompleteTextView dropDownText = addIncidencia.findViewById(R.id.dropdown_text);
        String[ ] labelsIdiomas;
        Resources res1 = getResources();
        labelsIdiomas = res1.getStringArray( R.array.Languages );

        ArrayAdapter<String> adapterLanguages = new ArrayAdapter<>(
                addIncidencia.getContext(),
                R.layout.dropdown_item,
                labelsIdiomas
        );
        dropDownText.setAdapter(adapterLanguages);

        final Button btn = addIncidencia.findViewById(R.id.btnSave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = dropDownText.getText().toString();
                if (item .equals("Spanish")){
                    setLocale("es");
                }
                if (item .equals("English")) {
                    setLocale("en");
                }
            }
        });

        final Button btn1 = addIncidencia.findViewById(R.id.btnRemove);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().deleteDatabase(DATABASE_NAME);
                Toast.makeText(getActivity(), "Removed!", Toast.LENGTH_LONG).show();
            }
        });


        return addIncidencia;
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getContext(), MainActivity.class);
        requireActivity().finish();
        startActivity(refresh);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}