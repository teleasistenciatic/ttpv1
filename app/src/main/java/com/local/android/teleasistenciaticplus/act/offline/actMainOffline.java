package com.local.android.teleasistenciaticplus.act.offline;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.local.android.teleasistenciaticplus.R;
import com.local.android.teleasistenciaticplus.act.debug.actMainDebug;
import com.local.android.teleasistenciaticplus.act.user.actUserOptions;
import com.local.android.teleasistenciaticplus.fragment.fragUserRegister;
import com.local.android.teleasistenciaticplus.lib.helper.AlertDialogShow;
import com.local.android.teleasistenciaticplus.lib.helper.AppSharedPreferences;
import com.local.android.teleasistenciaticplus.modelo.Constants;
import com.local.android.teleasistenciaticplus.modelo.DebugLevel;

public class actMainOffline extends Activity implements fragUserRegister.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_offline);

        /////////////////////////////////////////////////////////////
        // Si no tiene datos personales (nombre + apellidos) en la aplicación se muestra el fragmento
        /////////////////////////////////////////////////////////////

        boolean hasUserData = new AppSharedPreferences().hasUserData();
        if (!hasUserData) {
            Fragment miFragRegistroUsuario = new fragUserRegister();

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.overlay_user_register_loading_screen, miFragRegistroUsuario)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Si estamos en modo de depuración
        if ( Constants.DEBUG_LEVEL == DebugLevel.DEBUG ) {
            getMenuInflater().inflate(R.menu.menu_act_mainoffline, menu);
        } else { //si estamos en modo de producción no mostramos el menu de depuración
            getMenuInflater().inflate(R.menu.menu_act_mainoffline_produccion, menu);
        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_actmainoffline_exit_app) {
            finish();
        } else if ( id == R.id.menu_actmainoffline_debug_screen ) {
            Intent intent = new Intent(this, actMainDebug.class);
            startActivity(intent);
        } else if ( id == R.id.menu_act_user_options ) {
            Intent intent = new Intent(this, actUserOptions.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Se encarga de gestionar la UI del fragmento de registro de nuevo usuario
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void user_register_datos_personales_aceptar(View view) {

        TextView textEditNombre = (TextView) findViewById(R.id.user_register_datos_personales_nombre_text);
        TextView textEditApellidos = (TextView) findViewById(R.id.user_register_datos_personales_apellidos_text);

        AppSharedPreferences userSharedPreferences = new AppSharedPreferences();
        userSharedPreferences.setUserData( textEditNombre.getText().toString() , textEditApellidos.getText().toString() );

        /////////
        //Feedback al usuario tras la actualización
        /////////
            AlertDialogShow popup_conn = new AlertDialogShow();
            popup_conn.setTitulo(getResources().getString(R.string.user_options_datos_personales_edit));

            popup_conn.setMessage(getResources().getString(R.string.user_options_datos_personales_correct_edit));

            popup_conn.setLabelNeutral(getResources().getString(R.string.close_window));
            popup_conn.show(getFragmentManager(), "dummyTAG");
        //Fin del mensaje de información


        fragmentUserRegisterHide();

    }

    public void fragmentUserRegisterHide() {

        Fragment miFragRegistroUsuario = new fragUserRegister();

        FragmentManager fragmentManager = getFragmentManager();
        Fragment userFragment = getFragmentManager().findFragmentById(R.id.overlay_user_register_loading_screen);
        fragmentManager.beginTransaction()
                .hide(userFragment)
                .commit();
    }

}
