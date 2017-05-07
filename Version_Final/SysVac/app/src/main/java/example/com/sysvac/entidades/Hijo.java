package example.com.sysvac.entidades;

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

    public Hijo(int id_hijo, int cedula, String nombres, String apellidos, String lugar_nacimiento,
                String fecha_nacimiento, String sexo, String nacionalidad, int id_usuario) {
        this.id_hijo = id_hijo;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.lugar_nacimiento = lugar_nacimiento;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.id_usuario = id_usuario;
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

