package example.com.sysvac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.HashSet;
import java.util.List;

import example.com.sysvac.entidades.Hijo;
import example.com.sysvac.entidades.Vacuna;
import example.com.sysvac.utilidades.Utiles;

/**
 * Created by Jose Alvarez on 10/3/2017.
 */

public class MostrarDatos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Uri uri;
    private static final String MY_ACCOUNT_NAME ="Vacunas" ;

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

    protected String id_usuario;
    private ArrayList<Vacuna> lista = new  ArrayList();
    private ArrayList<Integer> lista_final = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);
        // Instanciación de variables controladoras de views
        direccionCli = (TextView) findViewById(R.id.direccion);
        telefonoCli = (TextView) findViewById(R.id.telefono);
        correoCli = (TextView) findViewById(R.id.email);
        tdireccionCli = (TextView) findViewById(R.id.tdireccion);
        ttelefonoCli = (TextView) findViewById(R.id.ttelefono);
        tcorreoCli = (TextView) findViewById(R.id.temail);
        clientesSpin = (Spinner) findViewById(R.id.combohijos);
        clientesSpin.setOnItemSelectedListener(this);
        id_usuario=getIntent().getExtras().getString("id_usuario");
        //obtiene la lista de los hijos
        TareaWSListar tarea = new TareaWSListar();
        tarea.execute();
        // obtiene la lista de los meses por grupo de vacunas dependiendo del hijo
        TareaWSVListarMes tarea2 = new TareaWSVListarMes();
        tarea2.execute();

        // se carga el spinner
        ArrayAdapter<Hijo> dataAdapter = new ArrayAdapter<Hijo>(this, android.R.layout.simple_spinner_item, hijoList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientesSpin.setAdapter(dataAdapter);


    }
    public void llenar_lv1(){
        //se carga un hijo en la primera posicion del array  ya que la primera posicion del spinner es vacio
        Hijo hijo = new Hijo();
        hijo.setId_hijo(0);
        hijo.setNombres("Nombres  ");
        hijo.setFecha_nacimiento(" Nacimiento ");
        hijo.setSexo("Sexo");
        nombreClientes.add(0, "Selecionar Hijo/a");
        hijoList.add(0, hijo);

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
        else{//muestra los datos del hijo en version resumida
            tdireccionCli.setVisibility(View.VISIBLE);
            tcorreoCli.setVisibility(View.VISIBLE);
            ttelefonoCli.setVisibility(View.VISIBLE);
            direccionCli.setVisibility(View.VISIBLE);
            correoCli.setVisibility(View.VISIBLE);
            telefonoCli.setVisibility(View.VISIBLE);
        }
        // carga los datos
        direccionCli.setText(((hijoList.get(position).getNombres() == null) ? ""
                : hijoList.get(position).getNombres()));
        telefonoCli.setText(((hijoList.get(position).getFecha_nacimiento() ==null) ? ""
                : hijoList.get(position).getFecha_nacimiento()));
        correoCli.setText(((hijoList.get(position).getSexo() == null) ? ""
                : hijoList.get(position).getSexo()));

        // Extrae el cliente seleccionado a una variable para su uso en otras actividades
        clienteSeleccionado = hijoList.get(position);
        posi=hijoList.get(position).getCedula();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void siguiente1 (View v){//boton que lleva a la actividad de mostrar vacunas por hijo

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
    public void mostrarMas(View v){//boton que muestra mas detalles del hijo seleccionado
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
    /// carga las notificaciones en modo interno  con las notificaciones push



    //Tarea Asincrona para llamar al WS de listado en segundo plano
    private class TareaWSListar extends AsyncTask<String,Integer,Boolean> {



        protected Boolean doInBackground(String... params) {

            boolean resul = true;
            HttpClient httpClient = new DefaultHttpClient();

            HttpGet del =
                    new HttpGet(Utiles.Uri.UriDatosUsuario+id_usuario);

            del.setHeader("content-type", "application/json");
            llenar_lv1();
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
                    Hijo hijo;
                    hijo= new Hijo();
                    hijo.setId_hijo(i+1);
                    hijo.setCedulaRuc(obj.getInt("cedula"));
                    hijo.setNombres(obj.getString("nombres"));
                    hijo.setApellidos(obj.getString("apellidos"));
                    hijo.setLugar_nacimiento(obj.getString("lugarNacimiento"));
                    hijo.setFecha_nacimiento(obj.getString("fechaNacimiento"));
                    hijo.setNacionalidad(obj.getString("nacionalidad"));
                    hijo.setSexo(obj.getString("sexo"));
                    hijo.setId_usuario(obj.getInt("id_usuario"));
                    nombreClientes.add(i,obj.getString("nombres") );
                    hijoList.add(i+1, hijo);




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
            {
                ArrayAdapter<Hijo> dataAdapter = new ArrayAdapter<Hijo>(MostrarDatos.this, android.R.layout.simple_spinner_item, hijoList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                clientesSpin.setAdapter(dataAdapter);




            }
            else{
                // llena el primer elemento del spinner
                //llenar_lv1();
                //
                ArrayAdapter<Hijo> dataAdapter = new ArrayAdapter<Hijo>(MostrarDatos.this, android.R.layout.simple_spinner_item, hijoList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                clientesSpin.setAdapter(dataAdapter);
            }
        }
    }


    private class TareaWSVListarMes extends AsyncTask<String,Integer,Boolean> {

       //private ArrayList<Vacuna> lista = new  ArrayList();
        //private ArrayList<ArrayList<Vacuna>> lista_final = new ArrayList(new ArrayList<Vacuna>());
        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet del;
                del = new HttpGet(Utiles.Uri.UriVacunasUsuario + id_usuario);
                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());

                JSONArray respJSON = new JSONArray(respStr);

                //clientes = new String[respJSON.length()];
                int w=0;
                System.out.println(respJSON.length());
                for (int i = 0; i < respJSON.length(); i++) {
                    Vacuna vacuna = new Vacuna();
                    JSONObject obj = respJSON.getJSONObject(i);
                    System.out.println("***");
                    System.out.println("***");
                    System.out.println("ENTRO POR LO MENOS");
                    System.out.println("***");
                    System.out.println("***");
                    vacuna.setId_hijo(obj.getJSONObject("vacunasPK").getInt("idHijo"));
                    vacuna.setMes_aplicacion(obj.getInt("mesAplicacion"));
                    vacuna.setAplicada(obj.getInt("aplicada"));
                    System.out.println(vacuna.getId_hijo()+" "+vacuna.getMes_aplicacion()+" " +vacuna.getAplicada());
                    lista.add(i,vacuna);
                    //w=w+1;
                    //nombreClientes.add(i,obj.getString("nombres") );
                }
            } catch (Exception ex) {
                System.out.println("");
                System.out.println("");
                System.out.println("aqui exploto");
                System.out.println("");
                System.out.println("");
                Log.e("ServicioRest", "Error!", ex);
                resul =false;

            }
            return resul;
        }
        protected void onPostExecute(Boolean result) {
            if (result) {
                System.out.println("LLEGA AQUI EN POSTEXECUTe");

                System.out.println("LLEGA AQUI EN POSTEXECUTe");
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                if (!prefs.getBoolean("firstTime", false)) {
                    // <---- run your one time code here
                    obtenerCantidad();
                    loadNotificaciones();

                    // mark first time has runned.
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("firstTime", true);
                    editor.commit();
                }
            }
        }


        private void loadNotificaciones() {//carga las notificaciones
        Utiles util = new Utiles();

        if ((hijoList.size())==(lista_final.size()+1)){
            System.out.println("EL TAMAÑO es "+hijoList.size());
            System.out.println("EL TAMAÑO es "+hijoList.size());
            System.out.println("EL TAMAÑO es "+hijoList.size());
            System.out.println("EL TAMAÑO es "+hijoList.size());
            System.out.println("EL TAMAÑO es "+hijoList.size());

            System.out.println("EL TAMAÑO es "+lista_final.size());

            System.out.println("EL TAMAÑO es "+lista_final.size());

            System.out.println("EL TAMAÑO es "+lista_final.size());

            System.out.println("EL TAMAÑO es "+lista_final.size());

            try{
                for (int k=1;k<hijoList.size();k++){
                //int k=2;
                    ArrayList<Integer> mes=obtenerMes(hijoList.get(k).getCedula());
                    System.out.println(mes.size());

                    if (mes.size()!=0){
                        for (int i=0; i<mes.size();i++){
                            String fecha = util.calcularFechaAAplicar(hijoList.get(k).getFecha_nacimiento(), mes.get(i));
                            new Notificacion(getApplicationContext(),
                                    util.calcularNotificacion(fecha),
                                    hijoList.get(k).getCedula(),
                                    hijoList.get(k).getNombres() + " " +hijoList.get(k).getApellidos(),
                                    mes.get(i));
                            System.out.println(fecha);
                        }

                        }
                }

            }catch (Exception e){
                System.out.println("NOTIFICACION");
                System.out.println("NOTIFICACION");
                System.out.println("NOTIFICACION");
                System.out.println("NOTIFICACION");
            }




        }
    }

        ArrayList<Integer> obtenerMes(Integer id_hijo){// obtiene la cantidad de meses restantes de un hijo para
            // poder cargar las notificaciones restantes

            ArrayList<Integer> aux = new ArrayList<Integer>();
            for (int i=0;i<lista.size();i++){
                lista_final.add(lista.get(i).getId_hijo());
                if((lista.get(i).getId_hijo()==id_hijo) && (lista.get(i).getAplicada()==0)){
                    aux.add(lista.get(i).getMes_aplicacion());
                    System.out.println(lista.get(i).getMes_aplicacion());
                }
            }


            HashSet<Integer> hashSet = new HashSet<Integer>(aux);
            aux.clear();
            aux.addAll(hashSet);

            for(Integer list : aux){
            System.out.println(list);
            }
            Collections.sort(aux);

            HashSet<Integer> hashSet1 = new HashSet<Integer>(lista_final);
            lista_final.clear();
            lista_final.addAll(hashSet1);

            for(Integer list : lista_final){
                System.out.println(lista_final);
            }
            Collections.sort(lista_final);
                return aux;
        }
        public void obtenerCantidad(){

            ArrayList<Integer> aux = new ArrayList<Integer>();
            for (int i=0;i<lista.size();i++){
                lista_final.add(lista.get(i).getId_hijo());
                }
            HashSet<Integer> hashSet1 = new HashSet<Integer>(lista_final);
            lista_final.clear();
            lista_final.addAll(hashSet1);

            for(Integer list : lista_final){
                System.out.println(lista_final);
            }
            Collections.sort(lista_final);

        }

   /* */}


}
