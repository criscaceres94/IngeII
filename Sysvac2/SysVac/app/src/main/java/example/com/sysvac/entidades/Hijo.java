package example.com.sysvac.entidades;

import android.content.ContentValues;
import android.database.Cursor;

import example.com.sysvac.entidades.EstructuraVac.HijosEntry;
/**
 * Created by Cristian on 10/3/2017.
 */

public class Hijo {
    int id_hijo;
    int cedula;
    String nombres;
    String apellidos;
    String lugar_nacimiento;
    String fecha_nacimiento;
    String sexo;
    String nacionalidad;
    int id_usuario;
    public Hijo(){};
    public Hijo(Cursor cursor) {
        id_hijo = cursor.getInt(cursor.getColumnIndex(HijosEntry.ID));
        cedula = cursor.getInt(cursor.getColumnIndex(HijosEntry.CI));
        nombres = cursor.getString(cursor.getColumnIndex(HijosEntry.NOMBRE));
        apellidos = cursor.getString(cursor.getColumnIndex(HijosEntry.APELLIDO));
        fecha_nacimiento = cursor.getString(cursor.getColumnIndex(HijosEntry.FECHA_NAC));
        lugar_nacimiento = cursor.getString(cursor.getColumnIndex(HijosEntry.LUGAR_NAC));
        sexo = cursor.getString(cursor.getColumnIndex(HijosEntry.SEXO));
        nacionalidad = cursor.getString(cursor.getColumnIndex(HijosEntry.NACIONALIDAD));
        /*direccion = cursor.getString(cursor.getColumnIndex(HijosEntry.DIRECCION));
        departamento = cursor.getString(cursor.getColumnIndex(HijosEntry.DEPARTAMENTO));
        municipio = cursor.getString(cursor.getColumnIndex(HijosEntry.MUNICIPIO));
        barrio = cursor.getString(cursor.getColumnIndex(HijosEntry.BARRIO));
        referencia = cursor.getString(cursor.getColumnIndex(HijosEntry.REFERENCIA));
        nombre_resp = cursor.getString(cursor.getColumnIndex(HijosEntry.NOMBRE_RESPONSABLE));
        tel = cursor.getString(cursor.getColumnIndex(HijosEntry.TEL));
        seguro = cursor.getString(cursor.getColumnIndex(HijosEntry.SEGURO));
        alergias = cursor.getString(cursor.getColumnIndex(HijosEntry.ALERGIA));*/
        id_usuario = cursor.getInt(cursor.getColumnIndex(HijosEntry.ID_USU));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(HijosEntry.ID, id_hijo);
        values.put(HijosEntry.CI, cedula);
        values.put(HijosEntry.NOMBRE, nombres);
        values.put(HijosEntry.APELLIDO, apellidos);
        values.put(HijosEntry.FECHA_NAC, fecha_nacimiento);
        values.put(HijosEntry.LUGAR_NAC, lugar_nacimiento);
        values.put(HijosEntry.SEXO, sexo);
        /*values.put(HijosEntry.NACIONALIDAD, nacionalidad);
        values.put(HijosEntry.DIRECCION, direccion);
        values.put(HijosEntry.DEPARTAMENTO, departamento);
        values.put(HijosEntry.MUNICIPIO, municipio);
        values.put(HijosEntry.BARRIO, barrio);
        values.put(HijosEntry.REFERENCIA, referencia);
        values.put(HijosEntry.NOMBRE_RESPONSABLE, nombre_resp);
        values.put(HijosEntry.TEL, tel);
        values.put(HijosEntry.SEGURO, seguro);
        values.put(HijosEntry.ALERGIA, alergias);*/
        values.put(HijosEntry.ID_USU, id_usuario);
        return values;
    }

    public int getId_hijo() {
        return id_hijo;
    }

    public int getCedula() {
        return cedula;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getLugar_nacimiento() {
        return lugar_nacimiento;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setLugar_nacimiento(String lugar_nacimiento) {
        this.lugar_nacimiento = lugar_nacimiento;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {

        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setCedulaRuc(int cedulaRuc) {
        this.cedula = cedulaRuc;
    }

    public void setId_hijo(int id_hijo) {
        this.id_hijo = id_hijo;
    }

    @Override
    public String toString() {
        return nombres + " " +fecha_nacimiento + " " +sexo;
    }
}

