package example.com.sysvac;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.ALARM_SERVICE;

public class Notificacion {

    public Notificacion(Context contexto, String dt, int hijoId, String nombre, int mes) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fecha = Calendar.getInstance();
        try {
            fecha.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(fecha.getTime());
        Intent intent = new Intent(contexto, AlarmReceiver.class);
        intent.putExtra("parametro", hijoId);
        intent.putExtra("mes", mes);
        intent.putExtra("nombre", nombre);
        intent.setAction(String.valueOf(hijoId)+String.valueOf(mes));
        int random = (int)System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(contexto, random, intent, FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager)contexto.getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, fecha.getTimeInMillis(), pendingIntent);
    }
}
