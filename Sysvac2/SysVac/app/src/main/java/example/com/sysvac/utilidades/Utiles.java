package example.com.sysvac.utilidades;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//TODO: Crear vencido

public class Utiles {

    public Utiles(){

    }

    public String calcularFechaAAplicar(String dt, int meses) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.MONTH, meses);  // number of months to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        return dt;
    }

    public boolean enTiempo(String dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = new Date();
        try {
            fecha = sdf.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date hoy = new Date();
        return fecha.before(hoy);
    }

    public String calcularNotificacion(String dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_YEAR, -2);
        dt = sdf.format(c.getTime());  // dt is now the new date
        System.out.println(dt);
        return dt;
    }

    public boolean vencido(String dt, int meses){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (meses) {
            case 2:
                c.add(Calendar.MONTH, 2);
            case 4:
                c.add(Calendar.MONTH, 2);
            case 6:
                c.add(Calendar.MONTH, 6);
            case 12:
                c.add(Calendar.MONTH, 3);
            case 15:
                c.add(Calendar.MONTH, 3);
            case 18:
                c.add(Calendar.MONTH, 30);
            case 48:
                c.add(Calendar.MONTH, 12);
        }
        Date fecha = c.getTime();
        Date hoy = new Date();
        return fecha.before(hoy);
    }
}
