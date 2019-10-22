package net.ivanvega.dialogosynotificacionesp77a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MiTimePickerFragment extends
        DialogFragment implements
        TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return
                new TimePickerDialog
                        (getActivity(),this,hour,minute, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        Log.d("LISTENERHORA",   "Hora: " + String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        Toast.makeText(getActivity(),
                "Hora: " + String.valueOf(hourOfDay) + ":" + String.valueOf(minute),
                Toast.LENGTH_SHORT).show();
    }
}
