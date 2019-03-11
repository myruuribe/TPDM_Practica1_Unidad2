package mx.edu.ittepic.michel.p1_u2_uribedavalos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Proyecto {

    private BaseDatos base;
    private int id;
    private String descripcion;
    private String ubicacion;
    private String fecha;
    private float presupuesto;
    protected String error;

    public Proyecto(Activity activity){
        base= new BaseDatos(activity,"Civil",null,1);
    }

    public Proyecto(int i,String d,String u,String f,float p){
        this.id=i;
        descripcion=d;
        ubicacion=u;
        fecha=f;
        presupuesto=p;
    }

    //metodo insertar
    public boolean insertar(Proyecto proyecto){
        try {
            SQLiteDatabase transaccioninsertar= base.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("DESCRIPCION",proyecto.getDescripcion());
            datos.put("UBICACION",proyecto.getUbicacion());
            datos.put("FECHA",proyecto.getFecha());
            datos.put("PRESUPUESTO",proyecto.getPresupuesto());

            long resultado= transaccioninsertar.insert("PROYECTO","IDPROYECTO=?",datos);
            transaccioninsertar.close();
            if (resultado== -1) return false;
        }catch (SQLiteException e){
            error=e.getMessage();
            return false;
        }

        return true;
    }

    //metodo consultar
    public Proyecto[]consultac(String llave){
        Proyecto[] resultado= null;
        try {
            SQLiteDatabase verconsulta= base.getWritableDatabase();
            String SQL= "SELECT * FROM PROYECTO WHERE IDPROYECTO="+llave;
            Cursor c= verconsulta.rawQuery(SQL,null);

            if (c.moveToFirst()){
                resultado= new Proyecto[c.getCount()];
                int posi=0;
                do {
                    resultado[posi]= new Proyecto(c.getInt(0),c.getString(1),
                            c.getString(2),c.getString(3),c.getFloat(4));
                    posi++;
                }while (c.moveToNext());
            }else {
                String SQLL= "SELECT * FROM PROYECTO WHERE DESCRIPCION='"+llave+"'";
                c= verconsulta.rawQuery(SQLL,null);
                if (c.moveToFirst()){
                    resultado= new Proyecto[c.getCount()];
                    int posi=0;
                    do {
                        resultado[posi]= new Proyecto(c.getInt(0),c.getString(1),
                                c.getString(2),c.getString(3),c.getFloat(4));
                        posi++;
                    }while (c.moveToNext());
                }
            }
            verconsulta.close();
        }catch (SQLiteException e){
            error= e.getMessage();
            return null;
        }
        return resultado;
    }

    public Proyecto[] consultar(){
        Proyecto[] resultado= null;
        try {
            SQLiteDatabase consulta= base.getReadableDatabase();
            String SQL= "SELECT * FROM PROYECTO";
            Cursor c= consulta.rawQuery(SQL,null);

            if (c.moveToNext()){
                resultado= new Proyecto[c.getCount()];
                int posi= 0;
                do{
                    resultado[posi]= new Proyecto(c.getInt(0),c.getString(1),
                            c.getString(2),c.getString(3),c.getFloat(4));
                    posi++;
                }while (c.moveToNext());
            }
            consulta.close();
        }catch (SQLiteException e){
                return null;
        }
        return resultado;
    }

    public boolean actualizar(Proyecto proyecto){
        try {
            SQLiteDatabase actuali= base.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("DESCRIPCION",proyecto.getDescripcion());
            datos.put("UBICACION",proyecto.getUbicacion());
            datos.put("FECHA",proyecto.getFecha());
            datos.put("PRESUPUESTO",proyecto.getPresupuesto());

            String id[]= {""+proyecto.getId()};
            long resultado= actuali.update("PROYECTO",datos,"IDPROYECTO=?",id);
            actuali.close();
            if (resultado==-1) return false;
        }catch (SQLiteException e){
            error= e.getMessage();
            return false;
        }
        return true;
    }

    public boolean eliminar(Proyecto proyecto){
        int resultado;
        try {
            SQLiteDatabase elimi= base.getWritableDatabase();
            String id[]= {""+proyecto.getId()};
            resultado= elimi.delete("PROYECTO","IDPROYECTO=?",id);
            elimi.close();
        }catch (SQLiteException e){
            error= e.getMessage();
            return false;
        }
        return resultado>0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public float getPresupuesto(){
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }
}
