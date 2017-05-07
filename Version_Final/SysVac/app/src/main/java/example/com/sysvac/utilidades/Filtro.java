package example.com.sysvac.utilidades;

/**
 * Created by Cristian on 16/3/2017.
 */

public class Filtro {
    int posicion;
    String nombre;

    @Override
    public String toString() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

