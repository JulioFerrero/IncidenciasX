package com.example.incidencias10.ui.list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.incidencias10.DB.IncidenciaDBHelper;
import com.example.incidencias10.R;

import java.util.ArrayList;


public class AdapaterDatos extends RecyclerView.Adapter <AdapaterDatos.ViewHolderDatos>{

    ArrayList<DatosVO> listDatos;

    public AdapaterDatos(ArrayList<DatosVO> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item,null,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        //holder.asignarDatos(listDatos.get(position));
        holder.EtiID.setText(listDatos.get(position).getID());
        String test = listDatos.get(position).getID();
        holder.EtiTitulo.setText(listDatos.get(position).getTitle());
        holder.EtiInci.setText(listDatos.get(position).getIncidencia());
        holder.EtiDesc.setText(listDatos.get(position).getDescripcion());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: "+ position);
                Intent MoreInfo = new Intent(v.getContext(),AllInfoRecycler.class);
                MoreInfo.putExtra("holderID", position);
                v.getContext().startActivity(MoreInfo);
            }
        });

        holder.button3dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(), holder.button3dots);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                int actualPosition = holder.getAdapterPosition();
                                IncidenciaDBHelper database = new IncidenciaDBHelper(view.getContext());
                                database.deleteRow(Integer.parseInt(listDatos.get(position).getID()));
                                listDatos.remove(position);
                                notifyItemRemoved(actualPosition);
                                notifyItemRangeChanged(actualPosition, listDatos.size());

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView EtiID,EtiTitulo,EtiInci,EtiDesc, button3dots;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            EtiID = (TextView) itemView.findViewById(R.id.id);
            EtiTitulo = (TextView) itemView.findViewById(R.id.titulo);
            EtiInci = (TextView) itemView.findViewById(R.id.inci);
            EtiDesc = (TextView) itemView.findViewById(R.id.description);
            button3dots = (TextView) itemView.findViewById(R.id.textViewOptions);
        }
    }
}
