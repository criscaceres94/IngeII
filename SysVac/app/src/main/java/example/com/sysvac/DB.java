package example.com.sysvac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import example.com.sysvac.entidades.EstructuraVac;
import example.com.sysvac.entidades.Hijo;
import example.com.sysvac.entidades.Vacuna;
/**
 * Created by Cristian on 7/3/2017.
 */

public class DB extends SQLiteOpenHelper {
    protected List<String> nombreClientes = new ArrayList<String>();
    protected List<Hijo> hijoList = new ArrayList<Hijo>();
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  db.execSQL(Create table );
        try {
        /*db.execSQL("CREATE TABLE IF NOT EXISTS Usuarios (id_usuario INTEGER PRIMARY KEY," +
                    "nombres TEXT, apellidos TEXT, telefono TEXT, correo_electronico TEXT," +
                    "direccion TEXT);");*/
            db.execSQL("CREATE TABLE IF NOT EXISTS Datos (id_hijo INTEGER PRIMARY KEY," +
                    "cedula INTEGER, nombres TEXT, apellidos TEXT,lugar_nacimiento TEXT, fecha_nacimiento TEXT," +
                    "sexo TEXT, nacionalidad TEXT,id_usuario INTEGER);");
            //"FOREING KEY (id_usuario) REFERENCES  Usuarios(id_usuario));");

            db.execSQL("CREATE TABLE IF NOT EXISTS Vacunas ( id_vacuna integer not null , nombre text not null, " +
                    "dosis intenger, edad integer, fecha text, lote text, nombre_medico text, descripcion text," +
                    "id_hijo integer not null," +
                    "aplicada INTEGER," +
                    "PRIMARY KEY (id_vacuna,id_hijo)," +
                    "FOREIGN KEY(id_hijo) REFERENCES Datos(id_hijos));");

            Cursor cuenta = db.rawQuery("SELECT count(*) from Datos", null);
            cuenta.moveToFirst();
            int c = cuenta.getInt(cuenta.getColumnIndex("count(*)"));
            if (c == 0) {
                ContentValues valores = new ContentValues();
                /*valores.put("id_usuario", "1");
                valores.put("nombres", "maria juana");
                valores.put("apellidos", "perez");
                valores.put("telefono", "10");
                valores.put("correo_electronico", "7500");
                valores.put("direccion", "litros (lts)");
                db.insert("Usuarios", null, valores);
                valores = new ContentValues();*/
                valores.put("id_hijo", "1");
                valores.put("cedula","1234");
                valores.put("nombres", "Juan gregorio");
                valores.put("apellidos", "Caceres Pérez");
                valores.put("lugar_nacimiento", "asuncion");
                valores.put("fecha_nacimiento", "08/03/2016");
                valores.put("sexo", "M");
                valores.put("nacionalidad", "Paraguaya");
                valores.put("id_usuario", "1");
                db.insert("Datos", null, valores);
                valores = new ContentValues();
                valores.put("id_hijo", "2");
                valores.put("cedula","123");
                valores.put("nombres", "maria juana");
                valores.put("apellidos", "Caceres Pérez");
                valores.put("lugar_nacimiento", "asuncion");
                valores.put("fecha_nacimiento", "08/03/2016");
                valores.put("sexo", "f");
                valores.put("nacionalidad", "Paraguaya");
                valores.put("id_usuario", "1");
                db.insert("Datos", null, valores);
                insertarDatos(db);
            }
            Cursor jeje = db.rawQuery("SELECT * from Vacunas", null);
            int contar=0;
            if(jeje.moveToFirst()){

                do{
                    contar++;
                }while(jeje.moveToNext());
            }

            System.out.println("*********");
            System.out.println("*********");
            System.out.println(contar);
            System.out.println("*********");
            System.out.println("*********");
        } catch (Exception e) {
            System.out.println("no se cargo nada");
        }
        db.execSQL("CREATE TABLE IF NOT EXISTS OK(BANDERA TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertarVacunas(SQLiteDatabase db, Vacuna vacuna){
        return db.insert(
                EstructuraVac.VacunaEntry.TABLE_NAME,
                null,
                vacuna.toContentValues());
    }
    public void insertarDatos( SQLiteDatabase sqLiteDatabase) {
        insertarVacunas(sqLiteDatabase, new Vacuna(1, "BCG",1 , 0, "08/03/2016",
                "BXAS22", "Marcos", "unica dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(2, "ROTAVIRUS",1 , 0, "08/05/2016",
                "BXAS22", "Marcos", "primera dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(3, "IPV",1 , 0, "08/05/2016",
                "BXAS22", "Marcos", "primera dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(4, "PCV 10 VALENTE",1 , 0, "08/05/2016",
                "BXAS22", "Marcos", "primera dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(5, "PENTAVALENTE",1 , 0, "08/05/2016",
                "BXAS22", "Marcos", "primera dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(6, "OPV/IPV",2 , 0, "08/07/2016",
                "BXAS22", "Marcos", "segunda dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(7, "ROTAVIRUS",2 , 0, "08/07/2016",
                "BXAS22", "Marcos", "segunda dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(8, "PCV 10 VALENTE",2 , 0, "08/07/2016",
                "BXAS22", "Marcos", "segunda dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(9, "PENTAVALENTE",2 , 0, "08/07/2016",
                "BXAS22", "Marcos", "segunda dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(10, "OPV/IPV",3 , 0, "08/09/2016",
                "BXAS22", "Marcos", "tercera dosis",1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(11, "PENTAVALENTE",3 , 0, "08/09/2016",
                "BXAS22", "Marcos", "tercera dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(12, "INFLUENZA 1RA",1 , 0, "08/09/2016",
                "BXAS22", "Marcos", "tercera dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(13, "INFLUENZA 1RA",2 , 0, "08/09/2016",
                "BXAS22", "Marcos", "tercera dosis", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(14, "S.P.R",1 , 1, "08/03/2017",
                "BXAS22", "Marcos", "al 1 año", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(15, "PCV 10 REF",2 , 1, "08/03/2017",
                "BXAS22", "Marcos", "al 1 año", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(16, "AA",1 , 1, "08/03/2017",
                "BXAS22", "Marcos", "al 1 año", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(17, "INFLUENZA",3 , 1, "08/03/2017",
                "BXAS22", "Marcos", "al 1 año", 1,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(18," V.V.Z",1 , 1, "08/06/2017",
                null, null, "al año y 3 meses", 1,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(19, " V.H.A",1 ,1, "08/06/2017",
                null, null, "al año y 3 meses", 1,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(20, "OPV/IPV",5 , 0, "08/09/2017",
                null, null, "1er refuezo", 1,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(21, "D.T.P",1 , 0, "08/09/2017",
                null, null, "1er refuezo", 1,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(22, "OPV/IPV",6 , 1, "08/03/2020",
                null, null, "2do refuerzo", 1,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(23, "D.T.P",2 , 1, "08/03/2020",
                null, null, "2do refuerzo", 1,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(24, "S.P.R",2 , 4, "08/03/2020",
                null, null, "2do refuerzo", 1,0));


        /*INSERTAR HIJO NUMERO 2*/

        insertarVacunas(sqLiteDatabase, new Vacuna(1, "BCG",1 , 0, "08/03/2017",
                "BXAS22", "Marcos", "unica dosis", 2,1));
        insertarVacunas(sqLiteDatabase, new Vacuna(2, "ROTAVIRUS",1 , 0, "08/05/2017",
                null, null, "primera dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(3, "IPV",1 , 0, "08/05/2017",
                null, null, "primera dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(4, "PCV 10 VALENTE",1 , 0, "08/05/2017",
                null, null, "primera dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(5, "PENTAVALENTE",1 , 0, "08/05/2017",
                null, null, "primera dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(6, "OPV/IPV",2 , 0, "08/07/2017",
                null, null, "segunda dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(7, "ROTAVIRUS",2 , 0, "08/07/2017",
                null, null, "segunda dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(8, "PCV 10 VALENTE",2 , 0, "08/07/2017",
                null, null, "segunda dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(9, "PENTAVALENTE",2 , 0, "08/07/2017",
                null, null, "segunda dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(10, "OPV/IPV",3 , 0, "08/09/2017",
                null, null, "tercera dosis",2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(11, "PENTAVALENTE",3 , 0, "08/09/2017",
                null, null, "tercera dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(12, "INFLUENZA 1RA",1 , 0, "08/09/2017",
                null, null, "tercera dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(13, "INFLUENZA 1RA",2 , 0, "08/09/2017",
                null, null, "tercera dosis", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(14, "S.P.R",1 , 1, "08/03/2018",
                null, null, "al 1 año", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(15, "PCV 10 REF",2 , 1, "08/03/2018",
                null, null, "al 1 año", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(16, "AA",1 , 1, "08/03/2018",
                null, null, "al 1 año", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(17, "INFLUENZA",3 , 1, "08/03/2018",
                null, null, "al 1 año", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(18," V.V.Z",1 , 1, "08/06/2018",
                null, null, "al año y 3 meses", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(19, " V.H.A",1 ,1, "08/06/2018",
                null, null, "al año y 3 meses", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(20, "OPV/IPV",5 , 0, "08/09/2018",
                null, null, "1er refuezo", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(21, "D.T.P",1 , 0, "08/09/2018",
                null, null, "1er refuezo", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(22, "OPV/IPV",6 , 1, "08/03/2021",
                null, null, "2do refuerzo", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(23, "D.T.P",2 , 1, "08/03/2021",
                null, null, "2do refuerzo", 2,0));
        insertarVacunas(sqLiteDatabase, new Vacuna(24, "S.P.R",2 , 4, "08/03/2021",
                null, null, "2do refuerzo", 2,0));



    }

    public ArrayList llenar_lv(int orden, int id_hijo){
        ArrayList<Vacuna> lista = new ArrayList<>();
        String q = null;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor registros;
        if (orden==0){
            q = "SELECT * FROM Vacunas where aplicada=2 and id_hijo=?;";
            registros= database.rawQuery(q,null);
        }
        else {
              if (orden == 1) {
                q = "SELECT * FROM Vacunas where aplicada=? and id_hijo=?;";
                registros = database.rawQuery(q, new String[]{"0", Integer.toString(id_hijo)});

              } else {
                if (orden == 2) {
                    q = "SELECT * FROM Vacunas where aplicada=? and id_hijo=?;";
                    registros = database.rawQuery(q, new String[]{"1", Integer.toString(id_hijo)});
                } else {
                    if (orden == 3) {
                        q = "SELECT * FROM Vacunas where id_hijo=? order by nombre;";
                        registros = database.rawQuery(q, new String[]{Integer.toString(id_hijo)});
                    } else {
                            q = "SELECT * FROM Vacunas where id_hijo=?;";
                            registros = database.rawQuery(q, new String[]{Integer.toString(id_hijo)});
                    }
                }
              }
        }



        Vacuna vac;
        if(registros.moveToFirst()){

            do{
                vac=new Vacuna();
                vac.setId_vacuna(registros.getInt(0));
                vac.setNombre(registros.getString(1));
                vac.setFecha(registros.getString(4));
                vac.setDescripcion(registros.getString(7));
                vac.setAplicada(registros.getInt(9));
                lista.add(vac);
            }while(registros.moveToNext());
        }
        return lista;

    }

    public ArrayList llenar(int id_hijo){
        ArrayList<String> lista = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM datos where id_hijo=?";
        Cursor registros = database.rawQuery(q,new String[]{Integer.toString(id_hijo)});
        if(registros.moveToFirst()){
            do{
                lista.add("ID HIJO:  "+registros.getString(0));
                lista.add("CEDULA:  "+registros.getString(1));
                lista.add("NOMBRES:  "+registros.getString(2));
                lista.add("APELLIDOS:  "+registros.getString(3));
                lista.add("LUGAR NACIMIENTO: "+registros.getString(4));
                lista.add("FECHA NACIMIENTO: "+registros.getString(5));
                lista.add("SEXO:  "+registros.getString(6));
                lista.add("NACIONAD:  "+registros.getString(7));
                lista.add("ID USUARIO:  "+registros.getString(8));

            }while(registros.moveToNext());
        }
        return lista;

    }



    public String guardar(String bandera){
        String mensaje="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("BANDERA",bandera);
        try {
            database.insertOrThrow("OK",null,contenedor);
            mensaje="Ingresado Correctamente";
        }catch (SQLException e){
            mensaje="No Ingresado";
        }
        database.close();
        return mensaje;
    }



}

