package example.com.sysvac;

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

import example.com.sysvac.entidades.Vacuna;
import example.com.sysvac.utilidades.Filtro;
import example.com.sysvac.utilidades.Tabla;
import example.com.sysvac.utilidades.Utiles;

/**
 * Created by Belen Desvars on 15/3/2017.
 */
public class MostrarVacunas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected Spinner clientesSpin;
    ArrayList<Filtro> listita=new ArrayList<>();
    ArrayList<Vacuna> vacunaList= new ArrayList<>();
    public int orden;
    protected TableLayout tab;
    Tabla tabla;
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
        tab = (TableLayout) findViewById(R.id.tabla);


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
                if (orden == 1) {
                     del =  new HttpGet(Utiles.Uri.UriVacunas+getIntent().getExtras().getInt("parametro")+"/0");

                } else {
                    if (orden == 2) {
                         del =  new HttpGet(Utiles.Uri.UriVacunas+getIntent().getExtras().getInt("parametro")+"/1");
                    } else {
                        if (orden == 3) {
                            del =   new HttpGet(Utiles.Uri.UriVacunas+"ordenado/"+getIntent().getExtras().getInt("parametro"));
                        } else {
                            del =   new HttpGet(Utiles.Uri.UriVacunas+getIntent().getExtras().getInt("parametro"));
                        }
                    }
                }
            }



                //HttpGet del =
                  //  new HttpGet("http://192.168.1.104:8084/jehe/webresources/datos/where/1");

            del.setHeader("content-type", "application/json");
            // llena el primer elemento del spinner

            //
            try
            {


                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());

                JSONArray respJSON = new JSONArray(respStr);

                //clientes = new String[respJSON.length()];

                for(int i=0; i<respJSON.length(); i++)
                {
                    JSONObject obj = respJSON.getJSONObject(i);
                    Vacuna vacuna;
                    vacuna= new Vacuna();
                    vacuna.setNombre(obj.getString("nombre"));
                    vacuna.setFecha(obj.getString("fecha"));
                    vacuna.setAplicada(obj.getInt("aplicada"));
                    //nombreClientes.add(i,obj.getString("nombres") );
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
            {   tab.removeAllViews();
                int tamano = vacunaList.size();
                tabla.agregarCabecera(R.array.cabecera_tabla);
                for (int i = 0; i < tamano; i++) {
                    ArrayList<String> elementos = new ArrayList<String>();
                    //elementos.add(Integer.toString(i));
                    //elementos.add(Integer.toString(elemento.get(i).getId_vacuna()));
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

