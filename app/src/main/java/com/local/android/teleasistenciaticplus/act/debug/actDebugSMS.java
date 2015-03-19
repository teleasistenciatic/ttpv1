package com.local.android.teleasistenciaticplus.act.debug;


import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;

import com.local.android.teleasistenciaticplus.R;
import com.local.android.teleasistenciaticplus.lib.networking.SmsDispatcher;


/**
 * Actividad de DEBUG que enviará un SMS al numero de teléfono indicado
 */
public class actDebugSMS extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_debug_sms);
    }

    /**
     * Debug SMS send
     * @param view vista
     */
    public void sms_send(View view) {

        TextView phoneNumberEdit = (TextView) findViewById(R.id.debug_edit_sms_number);
        String phoneNumber = phoneNumberEdit.getText().toString();

        TextView smsMessageEdit = (TextView) findViewById(R.id.debug_edit_sms_message);
        String smsBodyText = smsMessageEdit.getText().toString();

        new SmsDispatcher(phoneNumber , smsBodyText).send();
    }


    /**
     * Salida de la aplicación al pulsar el botón de salida del layout
     * @param view vista
     */
    public void exit_button(View view) {
        finish();
    }

}
