package com.example.incidencias10.DB;

import android.provider.BaseColumns;

class IncidenciaContract  {
    private IncidenciaContract(){}
    public static class IncidenciaEntry implements BaseColumns {
        public static final String TABLE_NAME ="incidencia";
        public static final String ID = "id";
        public static final String ColumnTitle = "title";
        public static final String ColumnInci = "incidencia";
        public static final String ColumnDesc = "descripcion";
        public static final String ColumnEstate = "estate";
        public static final String ColumnDate = "date";
    }
}
