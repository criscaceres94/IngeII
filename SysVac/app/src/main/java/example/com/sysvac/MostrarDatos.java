package example.com.sysvac;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import example.com.sysvac.entidades.Hijo;
import example.com.sysvac.entidades.Vacuna;

/**
 * Created by Jose Alvarez on 10/3/2017.
 */

public class MostrarDatos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Uri uri;
    private static final String MY_ACCOUNT_NAME ="Vacunas" ;
    protected SQLiteDatabase db;
    protected Spinner clientesSpin;
    protected TextView direccionCli;
    protected TextView telefonoCli;
    protected TextView correoCli;
    protected TextView tdireccionCli;
    protected TextView ttelefonoCli;
    protected TextView tcorreoCli;
    ArrayList<Vacuna> elemento;
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

        DB nuevo = new DB(getApplicationContext(), "DBHijos", null, 1);
        SQLiteDatabase nuevo1 = nuevo.getWritableDatabase();

        Cursor cuenta = nuevo1.rawQuery("SELECT count(*) from OK", null);
        cuenta.moveToFirst();
        int c = cuenta.getInt(cuenta.getColumnIndex("count(*)"));
        if (c == 0) {
            CrearCalendario();
            Long calID= getCalendarId();
            String nombre="";
            String titulo="";
            String descripcion="";
            String fecha="";
            for (int w=1;w<hijoList.size();w++) {
                nombre = hijoList.get(w).getNombres();
                DB x = new DB(getApplicationContext(), "DBHijos", null, 1);
                elemento = x.llenar_lv(4, w);


                for (int xe = 0; xe < 24; xe++) {
                    titulo = elemento.get(xe).getNombre();
                    descripcion = elemento.get(xe).getDescripcion() + ", para " + nombre;
                    fecha = elemento.get(xe).getFecha();
                    System.out.println("****");
                    System.out.println(fecha);
                    System.out.println("****");
                    CargarEve(calID, titulo, descripcion, fecha);
                }
            }
            System.out.println("----------------");
            System.out.println("----------------");
            System.out.println(nuevo.guardar("listo"));
            System.out.println("----------------");
            System.out.println("----------------");
        }

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

        //}






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





    public void CrearCalendario(){

        ContentValues values = new ContentValues();
        values.put(
                CalendarContract.Calendars.ACCOUNT_NAME,
                MY_ACCOUNT_NAME);
        values.put(
                CalendarContract.Calendars.ACCOUNT_TYPE,
                CalendarContract.ACCOUNT_TYPE_LOCAL);
        values.put(
                CalendarContract.Calendars.NAME,
                "Vacunas Calendar");
        values.put(
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                "Vacunas Calendar");
        values.put(
                CalendarContract.Calendars.CALENDAR_COLOR,
                0xffff0000);
        values.put(
                CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
                CalendarContract.Calendars.CAL_ACCESS_OWNER);
        values.put(
                CalendarContract.Calendars.OWNER_ACCOUNT,
                "vacunas@googlemail.com");
        values.put(
                CalendarContract.Calendars.CALENDAR_TIME_ZONE,
                "Paraguay/Asunción");
        values.put(
                CalendarContract.Calendars.SYNC_EVENTS,
                1);
        Uri.Builder builder =
                CalendarContract.Calendars.CONTENT_URI.buildUpon();
        builder.appendQueryParameter(
                CalendarContract.Calendars.ACCOUNT_NAME,
                "com.grokkingandroid");
        builder.appendQueryParameter(
                CalendarContract.Calendars.ACCOUNT_TYPE,
                CalendarContract.ACCOUNT_TYPE_LOCAL);
        builder.appendQueryParameter(
                CalendarContract.CALLER_IS_SYNCADAPTER,
                "true");
        uri = getContentResolver().insert(builder.build(), values);
        System.out.println("¨**************");
        System.out.println("¨**************");
        System.out.println(getCalendarId());
        System.out.println("¨**************");
        System.out.println("¨**************");
    }
    private long getCalendarId() {
        String[] projection = new String[]{CalendarContract.Calendars._ID};
        String selection =
                CalendarContract.Calendars.ACCOUNT_NAME +
                        " = ? AND " +
                        CalendarContract.Calendars.ACCOUNT_TYPE +
                        " = ? ";
        // use the same values as above:
        String[] selArgs =
                new String[]{
                        MY_ACCOUNT_NAME,
                        CalendarContract.ACCOUNT_TYPE_LOCAL};
        Cursor cursor = null;
        try{
            cursor =
                    getContentResolver().
                            query(
                                    CalendarContract.Calendars.CONTENT_URI,
                                    projection,
                                    selection,
                                    selArgs,
                                    null);
        }catch (SecurityException e){
            System.out.println("algo salio mal");
        }

        if (cursor.moveToFirst()) {
            return cursor.getLong(0);
        }
        return -1;
    }

    public void CargarEventos(long calID,String Title,String Descripcion,String fecha){
        String[] fechArray = fecha.split("/");
        int dia = Integer.valueOf(fechArray[0]);
        int mes = Integer.valueOf(fechArray[1]) - 1;
        int anio = Integer.valueOf(fechArray[2]);
        System.out.println("****");
        System.out.println(dia+" "+mes+" "+anio);
        System.out.println("****");
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(anio,dia, mes, 0, 0);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(anio, dia, mes, 23, 0);
        endMillis = endTime.getTimeInMillis();

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, Title);
        values.put(CalendarContract.Events.DESCRIPTION, Descripcion);
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Paraguay/Asunción");
        try{
             uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        }catch (SecurityException e){
            System.out.println("algo anda mal jeje");
        }


// get the event ID that is the last element in the Uri
        long eventID = Long.parseLong(uri.getLastPathSegment());



        cr = getContentResolver();
        values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, 2);
        values.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        try{
            uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
        }catch (SecurityException e){
            System.out.println( "algo anda mal");

        }

    }

    public void CargarEve(long calID,String Title,String Descripcion,String fecha){
        String[] fechArray = fecha.split("/");


        int dia = Integer.valueOf(fechArray[0]);
        int mes = Integer.valueOf(fechArray[1]) - 1;
        int anio = Integer.valueOf(fechArray[2]);

        long startMillis = 0;
        long endMillis = 0;
        /*Calendar cal = new GregorianCalendar(anio, mes, dia);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        //Calendar beginTime = Calendar.getInstance();
        //beginTime.set(anio,dia, mes, 0, 0);
        startMillis = cal.getTimeInMillis();
        cal = new GregorianCalendar(anio, mes, dia);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //Calendar endTime = Calendar.getInstance();
        //endTime.set(anio, dia, mes, 23, 0);
        endMillis = cal.getTimeInMillis();
        */

        Calendar cal = new GregorianCalendar(anio, mes, dia);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long start=cal.getTimeInMillis();
        cal = new GregorianCalendar(anio, mes, dia);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long end=cal.getTimeInMillis();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, start);
        values.put(CalendarContract.Events.DTEND, end);

        values.put(CalendarContract.Events.TITLE, Title);
        values.put(CalendarContract.Events.EVENT_LOCATION, "Paraguay");
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Berlin");
        values.put(CalendarContract.Events.DESCRIPTION,
                Descripcion);
// reasonable defaults exist:
        values.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        values.put(CalendarContract.Events.SELF_ATTENDEE_STATUS,
                CalendarContract.Events.STATUS_CONFIRMED);
        values.put(CalendarContract.Events.ALL_DAY, 1);
        ContentResolver cr = getContentResolver();
        try{
            uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        }catch (SecurityException e){
            System.out.println("algo anda mal jeje");
        }


// get the event ID that is the last element in the Uri
        long eventID = Long.parseLong(uri.getLastPathSegment());



        cr = getContentResolver();
        values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, 2880);
        values.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        try{
            uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
        }catch (SecurityException e){
            System.out.println( "algo anda mal");

        }
        values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, 1140);
        values.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        try{
            uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
        }catch (SecurityException e){
            System.out.println( "algo anda mal");

        }
        values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, 60);
        values.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        try{
            uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
        }catch (SecurityException e){
            System.out.println( "algo anda mal");

        }


    }

}
