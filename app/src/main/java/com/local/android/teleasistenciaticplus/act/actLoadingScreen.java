package com.local.android.teleasistenciaticplus.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.local.android.teleasistenciaticplus.act.offline.actMainOffline;
import com.local.android.teleasistenciaticplus.act.online.actMain;

import com.local.android.teleasistenciaticplus.R;
import com.local.android.teleasistenciaticplus.lib.networking.Networking;
import com.local.android.teleasistenciaticplus.lib.networking.ServerOperations;
import com.local.android.teleasistenciaticplus.modelo.Constants;
import com.local.android.teleasistenciaticplus.modelo.GlobalData;
import com.local.android.teleasistenciaticplus.modelo.OperationMode;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Esta actividad realizará funciones de comprobación y parcialmente
 * de inicialización.
 * Comprobaciones:
 * 1. El telefono tiene acceso de datos (WIFI o GPRS)
 * 2. El servidor de la aplicación está online
 * 3. El teléfono está registrado en la aplicación
 */
public class actLoadingScreen extends ActionBarActivity implements Constants {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ////////////////////////////////////////////////////////
        /// Operaciones cosméticas sobre la UI de la actividad
        ////////////////////////////////////////////////////////
        View decorView2 = getWindow().getDecorView();
        // Oculta status bar
        //Para ocultar también el navigation bar, quitar la parte comentada
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN; // | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView2.setSystemUiVisibility(uiOptions);


        ////////////////////////////////////////////////////
        // LAYOUT
        // Creación de la pantalla de carga
        setContentView(R.layout.layout_loadingscreen);
        ////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        // NO DATOS, NO SERVER ONLINE = Aplicación en modo OFFLINE
        // DATOS, SERVER ONLINE, USUARIO REGISTRADO = modo ONLINE
        ////////////////////////////////////////////////////

        if ((Networking.isConnectedToInternet()) &&  //Conectados a internet
                (ServerOperations.serverIsOnline()) &&  //El servidor está online
                (ServerOperations.isRegisteredOnServer())) { //EL usuario está registrado en la aplicación

            GlobalData.setOperationMode(OperationMode.ONLINE);

        } else {

            GlobalData.setOperationMode(OperationMode.OFFLINE);

        }

        ////////////////////////////////////////////////////////////////////
        // CARGA DE LA SIGUIENTE ACTIVITY
        ////////////////////////////////////////////////////////////////////
        // El modo OFFLINE carga la actividad correspondiente
        // El modo ONLINE carga la actividad correspondiente

        final Class actToLoad;

        if (GlobalData.getOperationMode() == OperationMode.ONLINE) {
            actToLoad = actMain.class; //MODO ONLINE
        } else {
            actToLoad = actMain.class; //MODO OFFLINE
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent;
                mainIntent = new Intent().setClass(actLoadingScreen.this, actToLoad);
                startActivity(mainIntent);
                overridePendingTransition(R.animator.animation1, R.animator.animation2);
                // Cerramos la ventana de carga para que salga del BackStack
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,Constants.LOADING_SCREEN_DELAY);

    } //Fin onCreate
} // Fin actLoadingScreen