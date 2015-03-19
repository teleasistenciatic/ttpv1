package com.local.android.teleasistenciaticplus.lib.networking;

import android.telephony.SmsManager;

import com.local.android.teleasistenciaticplus.lib.helper.AppLog;

/**
 * Created by FESEJU on 19/03/2015.
 */
public class SmsDispatcher {

    private String phoneNumber; //Destinatario
    private String message; //cuerpo del mensaje

    public SmsDispatcher(String phone, String message) {
        this.phoneNumber = phone;
        this.message = message;
    }

    public void send() {
        // ¿Qué import es? import android.telephony.gsm.SmsManager;
        SmsManager sms = SmsManager.getDefault();
        try {
            sms.sendTextMessage(this.phoneNumber, null, message, null, null);
        } catch (Exception e) {
            AppLog.e("SmsDispatcher", "SMS send error", e);
        }
        AppLog.i("SMSSend", this.phoneNumber + " " + message);
    }

    /**
     * Setter
     *
     * @param phoneNumber destinatario
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Setter
     *
     * @param message cuerpo del mensaje
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

/**
 * // Enviar un mensaje con intent
 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber)));
 **/