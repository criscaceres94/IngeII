package example.com.sysvac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;
import java.util.ArrayList;
import example.com.sysvac.entidades.Vacuna;
import example.com.sysvac.utilidades.Filtro;
import example.com.sysvac.utilidades.Tabla;
/**
 * Created by Belen Desvars on 15/3/2017.
 */
public class MostrarVacunas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<Vacuna> elemento;
    protected Spinner clientesSpin;
    ArrayList<Filtro> listita=new ArrayList<>();
    public int orden;
    protected TableLayout tab;
    Tabla tabla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_vacunas);
        Filtro aux=new Filtro();
        aux.setNombre("PRESIONE AQUI");
        aux.setPosicion(0);
        listita.add(aux);
        aux=new Filtro();
        aux.setNombre("Aplicadas");
        aux.setPosicion(1);
        listita.add(aux);
        aux=new Filtro();
        aux.setNombre("No Aplicadas");
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


        if(position!=0) {
            DB x = new DB(getApplicationContext(), "DBHijos", null, 1);
            elemento = x.llenar_lv(position, getIntent().getExtras().getInt("parametro"));
            int tamano = elemento.size();
            tabla.agregarCabecera(R.array.cabecera_tabla);
            for (int i = 0; i < tamano; i++) {
                ArrayList<String> elementos = new ArrayList<String>();
                //elementos.add(Integer.toString(i));
                elementos.add(Integer.toString(elemento.get(i).getId_vacuna()));
                elementos.add(elemento.get(i).getNombre());
                elementos.add(elemento.get(i).getFecha());
                if (elemento.get(i).getAplicada() == 0) {
                    elementos.add("NO");
                } else {
                    elementos.add("SI");
                }

                tabla.agregarFilaTabla(elementos);
            }
        }
        orden=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

