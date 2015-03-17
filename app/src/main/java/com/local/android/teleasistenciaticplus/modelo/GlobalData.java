package com.local.android.teleasistenciaticplus.modelo;

import android.app.Application;
import android.content.Context;

/**
 * Created by FESEJU on 11/02/2015.
 * - Se ejecutara en primer lugar, incluso antes que la actividad principal
 * - Es un Singleton que guardará el contexto de la aplicación allá cuando sea necesario
 * - Al guardar el Contexto (BIG) de la aplicación mediante getApplicationContext()
 * no tendremos el problema de memoryLeak
 *
 */

public class GlobalData extends Application {

    private static Context context; //Contexto de la aplicación
    private static String phoneNumber; //Número de teléfono del terminal
    private static Boolean offlineMode; //Modo de operación de funcionamiento (offline u online)

    /**
     * Se guarda el contexto de la aplicación
     * @param context contexto BIG
     */
    public static void setContext(Context context) {
        GlobalData.context = context;
    }

    /**
     * Se guarda el numero de teléfono
     * @param phoneNumber número de teléfono
     */
    public static void setPhoneNumber( String phoneNumber ) {
        GlobalData.phoneNumber = phoneNumber;
    }

    /**
     * Getter del contexto de la aplicación
     * @return contexto BIG
     */
    public static Context getAppContext() {
        return GlobalData.context;
    }

    /**
     * Getter del numero de teléfono de la aplicación
     * @return numero de teléfono
     */
    public static String getPhoneNumber() { return GlobalData.phoneNumber;}

    /**
     * Devuelve si se encuentra en modo OFFLINE u ONLINE
     * @return boolean Si está en modo offline
     */
    public static Boolean getOfflineMode() {
        return offlineMode;
    }

    /**
     * Establece si se encuentra en modo offline
     * @param offlineMode
     */
    public static void setOfflineMode(Boolean offlineMode) {
        GlobalData.offlineMode = offlineMode;
    }

}