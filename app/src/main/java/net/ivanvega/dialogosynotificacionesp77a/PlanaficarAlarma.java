package net.ivanvega.dialogosynotificacionesp77a;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PlanaficarAlarma
        extends
        BroadcastReceiver {


    private static final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarma repetida", Toast.LENGTH_LONG).
                show();
        Log.d("ALARMANAGER", "ALARMA RECURRENTE");

        lanzarNotificacion(context);
    }

    private void lanzarNotificacion(Context ctx) {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(ctx,    MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, intent, 0);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx, CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_notification_overlay)
                        .setContentTitle("Titulo  de la notificacion")
                        .setContentText("Cuerpo y contenidod de la notificacion")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);



        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1001, mBuilder.build());
    }


}
