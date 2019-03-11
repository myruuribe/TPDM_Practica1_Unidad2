package mx.edu.ittepic.michel.p1_u2_uribedavalos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Proyecto proyectos[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista= findViewById(R.id.listaproyecto);
    }

    protected void onStart(){
        Proyecto p= new Proyecto(this);
        proyectos= p.consultar();
        String[] datos= null;

        if (proyectos==null){
            datos= new String[1];
            datos[0]= "No hay proyectos insertados";
        }else {
            datos= new String[proyectos.length];
            for (int i=0; i<proyectos.length; i++){
                Proyecto temp= proyectos[i];
                datos[i]= temp.getDescripcion();
            }
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datos);
        lista.setAdapter(adapter);

        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menupop,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertarp:
                Intent insertarp= new Intent(this,Main2Activity.class);

                startActivity(insertarp);
                break;
            case R.id.consultarp:
                Intent consultarp= new Intent(this,Main3Activity.class);

                startActivity(consultarp);
                break;
            case R.id.editarp:
                Intent editarp= new Intent(this,Main4Activity.class);

                startActivity(editarp);
                break;
            case R.id.eliminarp:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
