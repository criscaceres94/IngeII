package example.com.sysvac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
/**
 * Created by Belen Desvars on 17/3/2017.
 */
public class Lista extends AppCompatActivity {
    ListView lv ;
    ArrayList<String> lista;
    ArrayAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lv = (ListView)findViewById(R.id.lista);
        DB db = new DB(getApplicationContext(), "DBHijos", null, 1);
        lista = db.llenar(getIntent().getExtras().getInt("parametro"));
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adaptador);
    }
}
