package example.com.sysvac.entidades;

import android.provider.BaseColumns;

/**
 * Created by Cristian on 15/3/2017.
 */
public class EstructuraVac {


    /**
     * Esquema de la base de datos
     */

        public static abstract class VacunaEntry implements BaseColumns {
            public static final String TABLE_NAME ="Vacunas";

            public static final String ID = "id_vacuna";
            public static final String NOMBRE = "nombre";
            public static final String DOSIS = "dosis";
            public static final String EDAD = "edad";
            public static final String FECHA = "fecha";
            public static final String LOTE = "lote";
            public static final String NOMBRE_MEDICO = "nombre_medico";
            public static final String DESCRIPCION = "descripcion";
            public static final String ID_HIJO = "id_hijo";
            public static final String APLICADA = "aplicada";
            public static final String MES_APLICADA = "mes_aplicacion";

    }

    public static abstract class HijosEntry implements BaseColumns{
        public static final String TABLE_NAME ="Datos";

        public static final String ID = "id_hijo";
        public static final String CI = "cedula";
        public static final String NOMBRE = "nombres";
        public static final String APELLIDO = "apellidos";
        public static final String FECHA_NAC = "fecha_nacimiento";
        public static final String LUGAR_NAC = "lugar_nacimiento";
        public static final String SEXO = "sexo";
        public static final String NACIONALIDAD = "nacionalidad";
       /* public static final String DIRECCION = "direccion";
        public static final String DEPARTAMENTO = "departamento";
        public static final String MUNICIPIO = "municipio";
        public static final String BARRIO = "barrio";
        public static final String REFERENCIA = "referencia";
        public static final String NOMBRE_RESPONSABLE = "nombre_responsable";
        public static final String TEL = "tel";
        public static final String SEGURO = "seguro";
        public static final String ALERGIA = "alergia";*/
        public static final String ID_USU = "id_usuario";
    }



}
