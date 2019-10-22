package net.ivanvega.dialogosynotificacionesp77a;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    AlarmManager am;

    String colores[] =
            new String[]
                    {"verde", "blanco", "rojo"};

    String generos_musicales[] =
            new String[]
                    {"rock", "clasica", "jazz", "pop", };

    Boolean edoCkeclList [] = {true, false, true, false};

    private String CHANNEL_ID="CANALID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

    }

    public void btnInfo_click(View view) {
        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Cuadro de dialogo")
                        .setIcon(android.R.drawable.ic_btn_speak_now)
                        .setMessage("Hola Mubndo desde Android")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(MainActivity.this,
                                        "Presiono OK", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,
                                        "Presiono Cancel", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .create();

        dialog.show();
    }

    public void btnList_click(View view) {
        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Cuadro de dialogo")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(colores, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,
                                        colores[which], Toast.LENGTH_SHORT)
                                        .show();
                                dialog.dismiss();
                            }
                        })
                        .create();

        dialog.show();
    }

    public void btnCheckList_click(View view) {
        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("Cuadro de dialogo")
                        .setIcon(R.mipmap.ic_launcher)

                        .setMultiChoiceItems(generos_musicales,
                                new boolean[]{true, false, true, false}
                                , new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        Toast.makeText(MainActivity.this,
                                                generos_musicales[which]
                                                        + (isChecked ? " Verificado": "No Verificado"),
                                                Toast.LENGTH_SHORT)
                                                .show();

                                        edoCkeclList [which] = isChecked;
                                    }
                                })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for(int j=0; j<edoCkeclList.length; j++){
                                    Toast.makeText(MainActivity.this,
                                            generos_musicales[j] + ": " + (edoCkeclList[j]?"Verificcado": "No verificado"),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .create();

        dialog.show();
    }

    public void btnTimePicker_click(View view) {
        DialogFragment timePickerDialogFragment =
                new MiTimePickerFragment();

        timePickerDialogFragment.
                show(getSupportFragmentManager(),
                        "timepicker");
    }

    public void btnDatePicker_click(View view) {
        // Use the current date as the default date in the picker
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickDialog =
                new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Log.d("DATEPICKER", "Fecha seleccionada: "
                                + String.valueOf(dayOfMonth) + "/" +
                                String.valueOf(month) + "/" + String.valueOf(year));
                    }

                }, year,month,day);

        datePickDialog.show();
    }

    public void btnAL_click(View view) {

        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(),
                PlanaficarAlarma.class);

        PendingIntent pi =
                PendingIntent.getBroadcast(getApplicationContext(),
                        0, intent, 0);

        am.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                10000,
                5*1000, pi);


    }

    public void btnNS_click(View view) {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(
            "net.ivanvega.audioenandroidcurso.CAPTURARAUDIO"
        );
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_notification_overlay)
                        .setContentTitle("Titulo  de la notificacion")
                        .setContentText("Cuerpo y contenidod de la notificacion")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);



        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1001, mBuilder.build());

    }
}
