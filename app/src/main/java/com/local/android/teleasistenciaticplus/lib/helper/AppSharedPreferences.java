package com.local.android.teleasistenciaticplus.lib.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.local.android.teleasistenciaticplus.modelo.Constants;
import com.local.android.teleasistenciaticplus.modelo.GlobalData;


/**
 * Created by FESEJU on 23/03/2015.
 * Leera y guardará preferencias de la aplicación mediante SharedPreferences
 */
public class AppSharedPreferences implements Constants {

    public void setUserData (String nombre, String apellidos) {
        SharedPreferences.Editor editor = GlobalData.getAppContext().getSharedPreferences( APP_SHARED_PREFERENCES_FILE , Context.MODE_PRIVATE ).edit();
        editor.putString("nombre", nombre);
        editor.putString("apellidos", apellidos);
        editor.commit();
    }

    public String[] getUserData() {

        SharedPreferences prefs = GlobalData.getAppContext().getSharedPreferences( APP_SHARED_PREFERENCES_FILE , Context.MODE_PRIVATE);
        String nombre = prefs.getString("nombre", "");//"No name defined" is the default value.
        String apellidos = prefs.getString("apellidos", ""); //0 is the default value.

        String[] datosPersonalesUsuario = {nombre, apellidos};

        return datosPersonalesUsuario;
    }
}
