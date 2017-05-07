package example.com.sysvac.entidades;

/**
 * Created by Cristian on 28/4/2017.
 */

public class VacunaPK {
    private int id_vacuna;
    private int id_hijo;
    private int id_usuario;

    public VacunaPK(){}

    public VacunaPK(int id_vacuna, int id_hijo, int id_usuario) {
        this.id_vacuna = id_vacuna;
        this.id_hijo = id_hijo;
        this.id_usuario = id_usuario;
    }

    public int getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public int getId_hijo() {
        return id_hijo;
    }

    public void setId_hijo(int id_hijo) {
        this.id_hijo = id_hijo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
