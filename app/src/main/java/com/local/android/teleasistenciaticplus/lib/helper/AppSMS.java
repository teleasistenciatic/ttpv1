package com.local.android.teleasistenciaticplus.lib.helper;

/**
 * Created by FESEJU on 25/03/2015.
 * Clase helper encargada de generar el texto de los mensajes SMS que luego enviará SmsDispatcher
 */
public class AppSMS {

    String title;
    String currentDateandTime;
    String phoneNumber;

    public AppSMS() {
        this.currentDateandTime = new AppTime().getTimeDate(); //En el formato que le indiquemos
    }

    public void generateSmsIamOK() {
        // Andres García comunica que se encuentra bien a las 12:00 del día 12/03/2015

        // Obtenemos el nombre de usuario

        // la fecha
        // creamos el mensaje
    }

}
