package mx.edu.ittepic.michel.p1_u2_uribedavalos;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText descrp, ubica, date, presupu;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        descrp=findViewById(R.id.descripcion);
        ubica=findViewById(R.id.ubicacion);
        date=findViewById(R.id.fecha);
        presupu=findViewById(R.id.presupuesto);
        ingresar=findViewById(R.id.insert);
        
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }

    private void insertar() {
        String mansaje="";

        Proyecto proyecto= new Proyecto(this);
        boolean respuesta= proyecto.insertar(new Proyecto(-1,descrp.getText().toString(),
                ubica.getText().toString(),date.getText().toString(), Float.parseFloat(presupu.getText().toString())));

        if (respuesta){
            mansaje= "Se pudo insertar el proyecto "+descrp.getText().toString();
        }else {
            mansaje="Error! no se pudo crear el proyecto, respues SQLite "+proyecto.error;
        }

        AlertDialog.Builder alerta= new AlertDialog.Builder(this);
        alerta.setTitle("ATENCION").setMessage(mansaje).setPositiveButton("Ok",null).show();
    }
}
