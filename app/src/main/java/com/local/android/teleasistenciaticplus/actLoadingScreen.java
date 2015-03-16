package com.local.android.teleasistenciaticplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.local.android.teleasistenciaticplus.lib.helper.AlertDialogShow;
import com.local.android.teleasistenciaticplus.lib.helper.AppLog;
import com.local.android.teleasistenciaticplus.lib.networking.Networking;
import com.local.android.teleasistenciaticplus.lib.networking.ServerOperations;
import com.local.android.teleasistenciaticplus.modelo.Constants;

import java.util.Timer;
import java.util.TimerTask;


public class actLoadingScreen extends ActionBarActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView2 = getWindow().getDecorView();
        // Oculta status bar
        //Para ocultar también el navigation bar, quitar la parte comentada
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN; // | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView2.setSystemUiVisibility(uiOptions);

        //TODO hacer que el siguiente código no falle
        // Recordar que si se oculta el Status Bar, nunca se deberÃ­a mostrar el ActionBar
        /*ActionBar actionBar2 = getActionBar();
        actionBar2.hide();*/

        ////////////////////////////////////////////////////
        // LAYOUT
        //Creación de la pantalla de carga
        setContentView(R.layout.layout_loadingscreen);
        ////////////////////////////////////////////////////

        //Comprobación inicial de conexión
        //Si falla alguna comprobación, salimos de la App
        int exitApp = 0;

        //1. Comprobación de que exista conexión de datos en el teléfono
        final Boolean isNetworkAvailable = Networking.isConnectedToInternet();

        //2. Comprobación de conexión al servidor
        final Boolean isServerAvailable = ServerOperations.serverIsOnline();

        //3. Comprobación de usuario registrado en el sistema
        final Boolean isUserRegistered = ServerOperations.isRegisteredInServer();

        if (isNetworkAvailable == false) {
            //Si no hay conexión mostramos alerta en pantalla
            exitApp = 1;
        } else if (isServerAvailable == false) {
            //Si el servidor no está disponible mostramos alerta en pantalla
            exitApp = 2;

        } else if (isUserRegistered == false) {
            //Si el usuario no está registrado mostramos alerta en pantalla
            exitApp = 3;
        }

        ////////////////////////////////////////////////////////////////////
        // CARGA DE La SIGUIENTE ACTIVITY
        ////////////////////////////////////////////////////////////////////
        //Si no hay problemas de conexión ni login, continuamos con la carga del botón de ayuda

        if (exitApp == 0) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    // Comenzamos la nueva aplicación
                    Intent mainIntent;
                    mainIntent = new Intent().setClass(actLoadingScreen.this, actMain.class);
                    startActivity(mainIntent);
                    overridePendingTransition(R.animator.animation1, R.animator.animation2);

                    // Cerramos la ventana de carga para que salga del BackStack
                    finish();
                }
            };

            // Simulamos un lento proceso de carga
            Timer timer = new Timer();
            timer.schedule(task, Constants.LOADING_SCREEN_TIME);

            //Si hay algún problema de conexión salimos
        } else {

            String textoExitApp = "";

            switch (exitApp) {

                case 1:
                    textoExitApp = "No hay conexión a internet";
                    break;
                case 2:
                    textoExitApp = "El servidor no está disponible";
                    break;
                case 3:
                    textoExitApp = "El usuario no está registrado";
                    break;

            }

            AppLog.i("actLoadingScreen -> ", "Fallo de conexión: " + textoExitApp);

            //Mostramos ventana de error de conexión y cerramos la app
            AlertDialogShow popup_conn = new AlertDialogShow();

            //personalizamos el mensaje del Dialog
            switch (exitApp) {

                case 1:
                    popup_conn.setTitulo(getResources().getString(R.string.check_internet_conn_title));
                    popup_conn.setMessage(getResources().getString(R.string.check_internet_conn_error));
                    popup_conn.setLabelNeutral(getResources().getString(R.string.close_window));
                    break;

                case 2:
                    popup_conn.setTitulo(getResources().getString(R.string.check_server_conn_title));
                    popup_conn.setMessage(getResources().getString(R.string.check_server_conn_error));
                    popup_conn.setLabelNeutral(getResources().getString(R.string.close_window));
                    break;

                case 3:
                    popup_conn.setTitulo(getResources().getString(R.string.check_user_account_title));
                    popup_conn.setMessage(getResources().getString(R.string.check_user_account_error));
                    popup_conn.setLabelNeutral(getResources().getString(R.string.close_window));
                    break;
            }
            popup_conn.show(getFragmentManager(), "internetAccessTAG");

            //Cerramos la APP transcurridos unos segundos
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // Cerramos la ventana de carga para que salga del BackStack
                    finish();

                }
            };
            // Simulamos un lento proceso de carga
            Timer timer = new Timer();
            timer.schedule(task, Constants.LOADING_SCREEN_TIME);
        }
    }
}