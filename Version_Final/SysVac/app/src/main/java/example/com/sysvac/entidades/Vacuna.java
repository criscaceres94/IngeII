package example.com.sysvac.entidades;

/**
 * Created by Cristian on 15/3/2017.
 */

public class Vacuna {
    private int id_vacuna;
    private String nombre;
    private int dosis;
    private int edad;
    private String fecha;
    private String lote;
    private String nombre_medico;
    private String descripcion;
    private int id_hijo;
    private int aplicada;
    private int mes_aplicacion;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    private int id_usuario;
    public Vacuna (){}

    public Vacuna(int id_vacuna, String nombre, int dosis, int edad, String fecha,
                  String lote, String nombre_medico, String descripcion, int id_hijo,
                  int aplicada, int mes_aplicacion, int id_usuario) {
        this.id_vacuna = id_vacuna;
        this.nombre = nombre;
        this.dosis = dosis;
        this.edad = edad;
        this.fecha = fecha;
        this.lote = lote;
        this.nombre_medico = nombre_medico;
        this.descripcion = descripcion;
        this.id_hijo = id_hijo;
        this.aplicada=aplicada;
        this.mes_aplicacion=mes_aplicacion;
        this.mes_aplicacion=id_usuario;
    }




    public int getId_hijo() {
        return id_hijo;
    }

    public void setId_hijo(int id_hijo) {
        this.id_hijo = id_hijo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre_medico() {
        return nombre_medico;
    }

    public void setNombre_medico(String nombre_medico) {
        this.nombre_medico = nombre_medico;
    }

    public String getLote() {

        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getFecha() {

        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDosis() {
        return dosis;

    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAplicada() {
        return aplicada;
    }

    public void setAplicada(int aplicada) {
        this.aplicada = aplicada;
    }

    public int getMes_aplicacion() {
        return mes_aplicacion;
    }

    public void setMes_aplicacion(int mes_aplicacion) {
        this.mes_aplicacion = mes_aplicacion;
    }

    public int getId_vacuna() {
        return id_vacuna;

    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    @Override
    public String toString() {
        return "Vacuna{" +
                "id_vacuna=" + id_vacuna +
                ", nombre='" + nombre + '\'' +
                ", dosis=" + dosis +
                ", edad=" + edad +
                ", fecha='" + fecha + '\'' +
                ", lote='" + lote + '\'' +
                ", nombre_medico='" + nombre_medico + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", id_hijo=" + id_hijo +
                '}';
    }
}
