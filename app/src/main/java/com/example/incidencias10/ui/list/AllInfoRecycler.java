package com.example.incidencias10.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.incidencias10.DB.IncidenciaDBHelper;
import com.example.incidencias10.R;

public class AllInfoRecycler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_info_recycler);
        IncidenciaDBHelper database = new IncidenciaDBHelper(getApplicationContext());

        int holderID = getIntent().getIntExtra("holderID", 0);

        TextView txtID = (TextView)findViewById(R.id.txtID);
        String ID = database.returnName().get(holderID).getID();
        txtID.setText(ID);

        TextView txtTitulo = (TextView)findViewById(R.id.txtTitulo);
        String Titulo = database.returnName().get(holderID).getTitle();
        txtTitulo.setText(Titulo);

        TextView txtInci = (TextView)findViewById(R.id.txtInci);
        String Incidencia = database.returnName().get(holderID).getIncidencia();
        txtInci.setText(Incidencia);

        TextView txtDesc = (TextView)findViewById(R.id.txtDesc);
        String Descripcion = database.returnName().get(holderID).getDescripcion();
        txtDesc.setText(Descripcion);

        TextView txtEst = (TextView)findViewById(R.id.txtEst);
        String Estado = database.returnName().get(holderID).getEstado();
        txtEst.setText(Estado);

        TextView txtDate = (TextView)findViewById(R.id.txtDate);
        String Date = database.returnName().get(holderID).getFecha();
        txtDate.setText(Date);

        Log.d("TAG", "onCreate: "+holderID);

    }
}