package mx.edu.ittepic.michel.p1_u2_uribedavalos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    EditText descripcion;
    Button consultar;
    ListView resulta;
    Proyecto[] proyectoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        descripcion=findViewById(R.id.condes);
        consultar=findViewById(R.id.consulta);
        resulta=findViewById(R.id.listaconsulta);

        
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultacion(descripcion.getText().toString());
                descripcion.setText("");
            }
        });
    }

    private void consultacion(String llave) {
        Proyecto p= new Proyecto(this);
        proyectoss= p.consultac(llave);
        String[] datos= null;

        if (proyectoss==null){
            datos= new String[1];
            datos[0]= "No se encontro ningun proyecto";
        }else {
            datos= new String[proyectoss.length];
            for (int i=0; i<proyectoss.length; i++){
                Proyecto temp= proyectoss[i];
                datos[i]= temp.getDescripcion()+"\n"+temp.getUbicacion()+"\n"+
                        temp.getFecha()+"\n"+temp.getPresupuesto();
            }
        }

        ArrayAdapter<String> adaptador= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datos);
        resulta.setAdapter(adaptador);
    }
}
