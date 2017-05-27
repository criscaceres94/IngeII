package example.com.sysvac;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import example.com.sysvac.entidades.Vacuna;
import example.com.sysvac.utilidades.Filtro;
import example.com.sysvac.utilidades.Tabla;
import example.com.sysvac.utilidades.Utiles;

/**
 * Created by Belen Desvars on 15/3/2017.
 */
public class MostrarVacunas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Activity actividad=this;
    protected Spinner clientesSpin;
    ArrayList<Filtro> listita=new ArrayList<>();
    ArrayList<Vacuna> vacunaList;
    public int orden;
    protected TableLayout tab;
    Tabla tabla;
    protected boolean notifi = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_vacunas);

        cargar();
        clientesSpin = (Spinner) findViewById(R.id.spinner);
        clientesSpin.setOnItemSelectedListener(this);
        ArrayAdapter<Filtro> dataAdapter = new ArrayAdapter<Filtro>(this, android.R.layout.simple_spinner_item, listita);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientesSpin.setAdapter(dataAdapter);
        notifi=getIntent().getExtras().getBoolean("noti");
        if(notifi){
        clientesSpin.setSelection(1);}
        tab = (TableLayout) findViewById(R.id.tabla);
        //tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Muestra el elemento seleccionado y pone los datos de contacto en la pantalla
        if(position == 0){
            Toast.makeText(parent.getContext(), "Seleccione un Filtro ", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(parent.getContext(), "Seleccionó: " + item, Toast.LENGTH_SHORT).show();


        }
        // instancia la tabla dinamica y remueve las filas para la proxima llmada
        tab.removeAllViews();
        tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        orden=position;
        if(position!=0) {

            TareaWSVListar tarea = new TareaWSVListar();
            tarea.execute();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void cargar(){
        // se crea el filtro para el spinner
        Filtro aux=new Filtro();
        aux.setNombre("PRESIONE AQUI");
        aux.setPosicion(0);
        listita.add(aux);
        aux=new Filtro();
        aux.setNombre("No Aplicadas");
        aux.setPosicion(1);
        listita.add(aux);
        aux=new Filtro();
        aux.setNombre("Aplicadas");
        aux.setPosicion(2);
        listita.add(aux);
        aux=new Filtro();
        aux.setNombre("Alfabeticamente");
        aux.setPosicion(3);
        listita.add(aux);
        aux=new Filtro();
        aux.setNombre("Por Fecha");
        aux.setPosicion(4);
        listita.add(aux);

    }

    //Tarea Asincrona para llamar al WS de listado en segundo plano
    private class TareaWSVListar extends AsyncTask<String,Integer,Boolean> {



        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet del;
            if (orden==0){
                del =
                        new HttpGet(Utiles.Uri.UriVacunas+"00000"); // default no retorna nada porque no existe
            }
            else {
                if (orden == 1) {//aplicadas
                     del =  new HttpGet(Utiles.Uri.UriVacunas+getIntent().getExtras().getInt("parametro")+"/0");

                } else {
                    if (orden == 2) {//no aplicadas
                         del =  new HttpGet(Utiles.Uri.UriVacunas+getIntent().getExtras().getInt("parametro")+"/1");
                    } else {
                        if (orden == 3) {//alfabeticamente
                            del =   new HttpGet(Utiles.Uri.UriVacunas+"ordenado/"+getIntent().getExtras().getInt("parametro"));
                        } else {//fecha
                            del =   new HttpGet(Utiles.Uri.UriVacunas+getIntent().getExtras().getInt("parametro"));
                        }
                    }
                }
            }

            del.setHeader("content-type", "application/json");
            // llena el primer elemento del spinner

            //
            try
            {


                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());

                JSONArray respJSON = new JSONArray(respStr);

                vacunaList= new ArrayList<>();
                // se llena las vacunas obtenidas segun el filtro en un arrayList de vacunas
                for(int i=0; i<respJSON.length(); i++)
                {
                    JSONObject obj = respJSON.getJSONObject(i);
                    Vacuna vacuna;

                    vacuna= new Vacuna();
                    vacuna.setNombre(obj.getString("nombre"));
                    vacuna.setFecha(obj.getString("fecha"));
                    vacuna.setAplicada(obj.getInt("aplicada"));

                    vacunaList.add(i, vacuna);
                }
            }
            catch(Exception ex)
            {
                System.out.println("");
                System.out.println("");
                System.out.println("aqui exploto");
                System.out.println("");
                System.out.println("");
                Log.e("ServicioRest","Error!", ex);
                resul = false;
            }

            return resul;
        }

        protected void onPostExecute(Boolean result) {

            if (result)
            {   //se carga los elementos en la tabla dependiendo del filtro
                if (orden==3){
                    Collections.sort(vacunaList, new Comparator<Vacuna>() {
                        public int compare(Vacuna obj1, Vacuna obj2) {
                            return obj1.getNombre().compareTo(obj2.getNombre());
                        }
                    });
                }
                int tamano = vacunaList.size();
                tabla.agregarCabecera(R.array.cabecera_tabla);
                for (int i = 0; i < tamano; i++) {
                    ArrayList<String> elementos = new ArrayList<String>();
                    elementos.add(vacunaList.get(i).getNombre());
                    elementos.add(vacunaList.get(i).getFecha());
                    if (vacunaList.get(i).getAplicada() == 0) {
                        elementos.add("NO");
                    } else {
                        elementos.add("SI");
                    }

                    tabla.agregarFilaTabla(elementos);
                }
            }
        }
    }


}

