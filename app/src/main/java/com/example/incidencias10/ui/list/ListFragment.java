package com.example.incidencias10.ui.list;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.incidencias10.DB.IncidenciaDBHelper;
import com.example.incidencias10.R;

public class ListFragment extends Fragment {

    IncidenciaDBHelper dbHelper;
    SQLiteDatabase db;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View addIncidencia = inflater.inflate(R.layout.fragment_list, container, false);

        dbHelper = new IncidenciaDBHelper(requireActivity().getApplicationContext());
        db = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = addIncidencia.findViewById(R.id.RecyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(addIncidencia.getContext(),LinearLayoutManager.VERTICAL,false));

        //IMPORTANTE
        AdapaterDatos adapter=new AdapaterDatos(dbHelper.returnName());
        recyclerView.setAdapter(adapter);


        if(dbHelper.returnName().size() == 0){
            TextView textView = addIncidencia.findViewById(R.id.textView);
            textView.setText("ᕕ( ᐛ )ᕗ");
        }

        return addIncidencia;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}