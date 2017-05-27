package example.com.sysvac;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

//TODO: cambiar texto de la notificacion

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("parametro", 1);
        int mes = intent.getIntExtra("mes", 0);
        String nombre = intent.getStringExtra("nombre");
        String texto = "";
        switch (mes) {
            case 0:
                texto = "Mes 0";
                break;
            case 2:
                texto = "Mes 2";
                break;
            case 4:
                texto = "Mes 4";
                break;
            case 6:
                texto = "Mes 6";
                break;
            case 12:
                texto = "Mes 12";
                break;
            case 15:
                texto = "Mes 15";
                break;
            case 18:
                texto = "Mes 18";
                break;
            case 48:
                texto = "Mes 48";
                break;
        }
        NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setContentTitle(nombre)
                    .setContentText("Vacuna pendiente. " +  texto);
        Intent resultIntent = new Intent(context, MostrarVacunas.class);
        resultIntent.putExtra("parametro", id);
        resultIntent.putExtra("noti",true);
        resultIntent.setAction(nombre+String.valueOf(mes));
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MostrarVacunas.class);
        stackBuilder.addNextIntent(resultIntent);
        int random = (int)System.currentTimeMillis();
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(random, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setAutoCancel(true);
        long[] pattern = new long[]{1000,500,1000};
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        mBuilder.setSound(defaultSound);

        mBuilder.setVibrate(pattern);
        mNotificationManager.notify(random, mBuilder.build());
    }
}