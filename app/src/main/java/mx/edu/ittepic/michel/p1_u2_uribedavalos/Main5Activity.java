package mx.edu.ittepic.michel.p1_u2_uribedavalos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    EditText des, ubi, fech, pre;
    Button eli;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        des=findViewById(R.id.descripcion);
        ubi=findViewById(R.id.ubicacion);
        fech=findViewById(R.id.fecha);
        pre=findViewById(R.id.presupuesto);
        eli=findViewById(R.id.delete);

        Bundle parametros= getIntent().getExtras();
        des.setText(parametros.getString("descripcion"));
        ubi.setText(parametros.getString("ubicacion"));
        fech.setText(parametros.getString("fecha"));
        pre.setText(parametros.getString("presupuesto"));
        id= parametros.getInt("id");

        eli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });
    }

    private void eliminar() {
        Proyecto p= new Proyecto(this);
        String mensaje;
        boolean respuesta= p.eliminar(new Proyecto(id,des.getText().toString(),ubi.getText().toString(),
                fech.getText().toString(),Float.parseFloat(pre.getText().toString())));

        if (respuesta){
            mensaje="Proyecto eliminado";
        }else {
            mensaje="Error, no se pudo eliminar el proyecto";
        }
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }
}
