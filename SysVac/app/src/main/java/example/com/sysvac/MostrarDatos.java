package example.com.sysvac;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.sysvac.entidades.Hijo;
/**
 * Created by Jose Alvarez on 10/3/2017.
 */

public class MostrarDatos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    protected SQLiteDatabase db;
    protected Spinner clientesSpin;
    protected TextView direccionCli;
    protected TextView telefonoCli;
    protected TextView correoCli;
    protected TextView tdireccionCli;
    protected TextView ttelefonoCli;
    protected TextView tcorreoCli;

    protected List<Hijo> hijoList = new ArrayList<Hijo>();
    protected List<String> nombreClientes = new ArrayList<String>();
    public static Hijo clienteSeleccionado = new Hijo();
    public int posi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);
        //Abre la base de datos, que se encuentra en data/data/"tudominio"/sisventas/databases
//Inicializa el spinner a partir del spinner definido en la vista

        llenar_lv();
        clientesSpin = (Spinner) findViewById(R.id.combohijos);
        clientesSpin.setOnItemSelectedListener(this);
        ArrayAdapter<Hijo> dataAdapter = new ArrayAdapter<Hijo>(this, android.R.layout.simple_spinner_item, hijoList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientesSpin.setAdapter(dataAdapter);

        // Instanciación de variables controladoras de views
        direccionCli = (TextView) findViewById(R.id.direccion);
        telefonoCli = (TextView) findViewById(R.id.telefono);
        correoCli = (TextView) findViewById(R.id.email);
        tdireccionCli = (TextView) findViewById(R.id.tdireccion);
        ttelefonoCli = (TextView) findViewById(R.id.ttelefono);
        tcorreoCli = (TextView) findViewById(R.id.temail);






    }
    public void llenar_lv() {
        DB x = new DB(getApplicationContext(),"DBHijos",null,1);
        db = x.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Datos", null);
        c.moveToFirst();
        Hijo hijo = new Hijo();
        hijo.setId_hijo(0);
        hijo.setNombres("Nombres  ");
        hijo.setFecha_nacimiento(" Nacimiento ");
        hijo.setSexo("Sexo");
        nombreClientes.add(0, "Selecionar Hij@");
        hijoList.add(0, hijo);

        //Guarda los clientes en una lista
        for (int i = 1; i <= c.getCount(); i++){
            hijo= new Hijo();
            hijo.setId_hijo(c.getInt(c.getColumnIndex("id_usuario")));
            hijo.setCedulaRuc(c.getInt(c.getColumnIndex("cedula")));
            hijo.setNombres(c.getString(c.getColumnIndex("nombres")));
            hijo.setApellidos(c.getString(c.getColumnIndex("apellidos")));
            hijo.setLugar_nacimiento(c.getString(c.getColumnIndex("lugar_nacimiento")));
            hijo.setFecha_nacimiento(c.getString(c.getColumnIndex("fecha_nacimiento")));
            hijo.setNacionalidad(c.getString(c.getColumnIndex("nacionalidad")));
            hijo.setSexo(c.getString(c.getColumnIndex("sexo")));
            hijo.setId_usuario(c.getInt(c.getColumnIndex("id_usuario")));
            nombreClientes.add(i, c.getString(c.getColumnIndex("nombres")));
            hijoList.add(i, hijo);
            c.moveToNext();//salta a la siguiente fila de resultados
        }
        c.close();
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // Al seleccionar un elemento en el spinner
        String item = parent.getItemAtPosition(position).toString();

        // Muestra el elemento seleccionado y pone los datos de contacto en la pantalla
        if(position == 0){
            Toast.makeText(parent.getContext(), "Seleccione un hijo/a ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(parent.getContext(), "Seleccionó: " + item, Toast.LENGTH_SHORT).show();
        }
        if(position == 0){
            tdireccionCli.setVisibility(View.INVISIBLE);
            tcorreoCli.setVisibility(View.INVISIBLE);
            ttelefonoCli.setVisibility(View.INVISIBLE);
            direccionCli.setVisibility(View.INVISIBLE);
            correoCli.setVisibility(View.INVISIBLE);
            telefonoCli.setVisibility(View.INVISIBLE);
        }
        else{
            tdireccionCli.setVisibility(View.VISIBLE);
            tcorreoCli.setVisibility(View.VISIBLE);
            ttelefonoCli.setVisibility(View.VISIBLE);
            direccionCli.setVisibility(View.VISIBLE);
            correoCli.setVisibility(View.VISIBLE);
            telefonoCli.setVisibility(View.VISIBLE);
        }
        direccionCli.setText(((hijoList.get(position).getNombres() == null) ? ""
                : hijoList.get(position).getNombres()));
        telefonoCli.setText(((hijoList.get(position).getFecha_nacimiento() ==null) ? ""
                : hijoList.get(position).getFecha_nacimiento()));
        correoCli.setText(((hijoList.get(position).getSexo() == null) ? ""
                : hijoList.get(position).getSexo()));

        // Extrae el cliente seleccionado a una variable para su uso en otras actividades
        clienteSeleccionado = hijoList.get(position);
        posi=position;
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void siguiente1 (View v){

        if (clienteSeleccionado != null
                & clienteSeleccionado != hijoList.get(0)){
            Intent intento = new Intent(MostrarDatos.this, MostrarVacunas.class);
            intento.putExtra("parametro",posi);
            startActivity(intento);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
        else{
            Toast.makeText(v.getContext(), "Debe seleccionar un hijo/a!", Toast.LENGTH_SHORT).show();
        }
    }
    public void mostrarMas(View v){
        if (clienteSeleccionado != null
                & clienteSeleccionado != hijoList.get(0)){
            Intent intento = new Intent(MostrarDatos.this, Lista.class);
            intento.putExtra("parametro",posi);
            startActivity(intento);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
        else{
            Toast.makeText(v.getContext(), "Debe seleccionar un hijo/a!", Toast.LENGTH_SHORT).show();
        }
    }

}
