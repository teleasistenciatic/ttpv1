package com.local.android.teleasistenciaticplus.act.debug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.local.android.teleasistenciaticplus.R;
import com.local.android.teleasistenciaticplus.act.online.actMain;
import com.local.android.teleasistenciaticplus.lib.cifrado.Cifrado;
import com.local.android.teleasistenciaticplus.lib.helper.AlertDialogShow;
import com.local.android.teleasistenciaticplus.lib.helper.AppLog;
import com.local.android.teleasistenciaticplus.lib.networking.HttpUrlTextRead;
import com.local.android.teleasistenciaticplus.lib.networking.Networking;
import com.local.android.teleasistenciaticplus.modelo.Constants;

public class actMainDebug extends ActionBarActivity {

    /**
     * Creación de la actividad de depuración
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /////////////////////////////////////////////////////////////////////
        // Creación del UI de ListView con los subapartados de Depuración
        ////////////////////////////////////////////////////////////////////

            /// Layout
            setContentView(R.layout.layout_debug_main);
            /// Listview
            final ListView listView = (ListView) findViewById(R.id.debug_main_listView);
            /// String para el ListView
            String[] values = new String[]{
                    "Uso de la memoria", //id 0
                    "Comprobacion servidor", // id 1
                    "Encriptacion/Desencriptacin", //id 2
                    "Envío SMS", //id 3
                    "Configuración usuario",
                    "Lectura .LOG"
            };
            /// Creación del adaptador con su String
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);
            /// Vinculación del adaptador con la lista
            listView.setAdapter(adapter);

            /// Creación del OnClickListener para las pulsaciones
            ////////////////////////////////////////////////////////////////////

            // ListView Item Click Listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Class actToLoad = null;

                    switch(position) {
                        case 0: //"Uso de la memoria"
                            actToLoad = actDebugMemory.class;
                            break;
                    }

                    Intent newIntent;
                    newIntent = new Intent().setClass(actMainDebug.this, actToLoad);
                    startActivity(newIntent);

                    /*
                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) listView.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(getApplicationContext(),
                            "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                            .show();
                    */
                }

            });

        /// Fin creación listView
        ////////////////////////////////////////////////////////////////////

        /*
        //Texto de la dirección de servidor
        /*TextView serverAddress = (TextView) findViewById(R.id.edit_server_adress);
        serverAddress.setText(Constants.SERVER_URL);
        */
    }

    /**
     * Menus de la actividad
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_act_main_debug_exit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void exit_button(View view) {
        finish();
    }



    /*

    public void main_debug_button_check_online(View view) {
        ////////////////////////////////////////////////////
        // Comprobación de estado online servidor
        ////////////////////////////////////////////////////

        //Interfaz
        TextView serverAddress = (TextView) findViewById(R.id.edit_server_adress);

        String url = serverAddress.getText().toString(); //primero usamos la introducida en la caja de texto

        if (url.length() == 0) {  //Si la cadena está vacia usamos la url por defecto
            serverAddress.setText(Constants.SERVER_URL);
        }

        Boolean isNetworkAvailable = Networking.isConnectedToInternet();

        showConnectionToInternetInTextBackground(serverAddress); //Indicamos el estado online mediante color rojo o verde

        //Comprobación de el servidor está disponible (se hace mediante la lectura de un fichero en el mismo)
        //y sólo se realiza si existe conexión de internet

        if (isNetworkAvailable) {

            String textRead = null;

            try {
                HttpUrlTextRead miUrl = new HttpUrlTextRead(url);
                textRead = miUrl.getText();
            } catch (Exception e) {
                AppLog.d("actMainDebug", "Error leyendo el archivo");
            }

            String resultado;

            if (textRead == null) {
                resultado = getResources().getString(R.string.ERROR);
                AppLog.i("actMainDebug", "Error accediendo a la dirección:\"" + url + "\"");
            } else {
                resultado = getResources().getString(R.string.CORRECTO);
                AppLog.i("actMainDebug", textRead);
            }

            /////////
            //Generación de alerta en pantalla con el resultado de la conexión
            /////////
            AlertDialogShow popup_conn = new AlertDialogShow();
            popup_conn.setTitulo(getResources().getString(R.string.check_server_conn_title));
            if (resultado.equals(getResources().getString(R.string.ERROR))) {
                popup_conn.setMessage(getResources().getString(R.string.check_server_conn_error));
            } else {
                popup_conn.setMessage(getResources().getString(R.string.check_server_conn_ok));
            }
            popup_conn.setLabelNeutral(getResources().getString(R.string.close_window));
            popup_conn.show(getFragmentManager(), "internetAccessTAG");
            //Fin del mensaje de alerta
        }
    }

    public void main_debug_button_cifrado_test(View view) throws Exception {


        //Obtenemos el texto de la caja de texto
        TextView textEditConTextoACifrar = (TextView) findViewById(R.id.edit_string_cifrado_input);
        String textoACifrar = textEditConTextoACifrar.getText().toString();

        if (textoACifrar.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.error_empty_string), Toast.LENGTH_SHORT).show();
            return;
        }

        //Llamamos a la encriptación
        Cifrado miCifrado = new Cifrado();
        String cifrado = miCifrado.cifrar(textoACifrar);
        ///////////////////////////////

        //Obtenemos la caja de texto de salida
        TextView textEditConCifrado = (TextView) findViewById(R.id.edit_string_cifrado_output);
        textEditConCifrado.setText(cifrado);

        AppLog.i(">>actMainDebug<< texto cifrado", cifrado);
    }

    public void main_debug_button_descifrado_test(View view) throws Exception {

        //Obtenemos el texto de la caja de texto
        TextView textEditConTextoADescifrar = (TextView) findViewById(R.id.edit_string_cifrado_input);
        String textoADescifrar = textEditConTextoADescifrar.getText().toString();

        if (textoADescifrar.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.error_empty_string), Toast.LENGTH_SHORT).show();
            return;
        }

        //Llamamos a la desencriptación
        Cifrado miCifrado = new Cifrado();
        String descifrado = miCifrado.descifrar(textoADescifrar);
        ///////////////////////////////

        //Obtenemos la caja de texto de salida
        TextView textEditConDescifrado = (TextView) findViewById(R.id.edit_string_cifrado_output);
        textEditConDescifrado.setText(descifrado);

        AppLog.i(">>actMainDebug<< texto descrifrado", descifrado);
    }

    private void showConnectionToInternetInTextBackground(TextView serverAddress) {
        //Comprobación de que exista conexión de datos en el teléfono
        final Boolean isNetworkAvailable = Networking.isConnectedToInternet();

        if (isNetworkAvailable) {
            //Server online fondo verde
            serverAddress.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            //Server offline fondo rojo
            serverAddress.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }
    */

} // Fin actividadMainDebug