package com.local.android.teleasistenciaticplus.act.debug;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.local.android.teleasistenciaticplus.R;
import com.local.android.teleasistenciaticplus.lib.networking.Networking;

public class actDebugDataConnection extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_debug_data_connection);
    }

    /**
     * Fin de la activity
     * @param view vista
     */
    public void exit_button(View view) {
        finish();
    }

    /**
     * Comprueba si hay conexión de datos y lo muestra con color de fondo en una caja de introducción de texto
     * @param view
     */
    public void main_debug_data_connection_check(View view) {

        TextView dataConnection = (TextView) findViewById(R.id.edit_check_data_connection);
        TextView activeNetwork = (TextView) findViewById(R.id.edit_check_data_connection_active_network);
        TextView activeNetworkAvailable = (TextView) findViewById(R.id.edit_check_data_connection_active_network_is_available);
        TextView activeNetworkConnected = (TextView) findViewById(R.id.edit_check_data_connection_active_network_is_connected);

        Boolean isNetworkAvailable = Networking.isConnectedToInternet();

        setTextBackground(dataConnection,isNetworkAvailable); //Muestra si es positivo o negativo en base al color de fondo de la caja de texto
        setTextBackground(activeNetwork, Networking.activeNetworkNotNull() );
        setTextBackground(activeNetworkAvailable, Networking.activeNetworkIsAvailable() );
        setTextBackground(activeNetworkConnected, Networking.activeNetworkIsConnected() );

        ///////////////////////////////////////////////////////////
        // Comprobaciones pormenorizadas para más información
        ///////////////////////////////////////////////////////////

    }

    /*
     public static boolean isConnectedToInternet() {
        Context mContext = GlobalData.getAppContext();

        try {
            ConnectivityManager cm =
                    (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null &&
                    activeNetwork.isAvailable() &&
                    activeNetwork.isConnected();
        } catch (Exception e) {
            return false;
        }
    }

     */

    private void setTextBackground(TextView serverAddress, Boolean valorPositivo) {

        if ( valorPositivo ) {
            serverAddress.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            serverAddress.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

}
