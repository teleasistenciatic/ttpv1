package com.local.android.teleasistenciaticplus.modelo;

import android.app.Application;

import com.local.android.teleasistenciaticplus.lib.phone.PhoneData;
import com.local.android.teleasistenciaticplus.lib.helper.AppLog;

/**
 * Created by FESEJU on 11/02/2015.
 * - HOOK que se ejecutara en primer lugar, incluso antes que la actividad principal.
 * Lo usaremos para operaciones iniciales, como por ejemplo guardar el contexto de la aplicación
 *
 */

public class Hook extends Application {

    /**
     * Almacenamos el contexto de la aplicación y otros valores globales
     * Cuando se sale del entorno de una actividad, podemos pasar el contexto
     * pero para simplificar todas las cases externas se ha decidido tener
     * un punto común de acceso al BIG context.
     */
    public void onCreate(){

        super.onCreate();

        GlobalData.setContext( getApplicationContext() );
        GlobalData.setPhoneNumber( new PhoneData().getNumber() );

        AppLog.i("Hook.class", "Ejecutado Hook de aplicación"); //:LOG:
    }

} //Fin CLASS