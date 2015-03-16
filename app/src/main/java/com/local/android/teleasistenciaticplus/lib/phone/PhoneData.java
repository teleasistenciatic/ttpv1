package com.local.android.teleasistenciaticplus.lib.phone;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.local.android.teleasistenciaticplus.lib.helper.AppLog;
import com.local.android.teleasistenciaticplus.modelo.GlobalData;

/**
 * Created by GAMO1J on 02/03/2015.
 * Clase para recuperar los datos del terminal
 */
public class PhoneData {

    private Context mContext; //Contexto privado que saco de la global de la aplicación
    private TelephonyManager tm; //

    //Datos importantes del teléfono
    private String phoneNumber;

    /**
     * Constructor
     */
    public PhoneData() {
        mContext = GlobalData.getAppContext();

        //Recuperamos el número de teléfono
        //En teléfonos sin SIM, no se puede obtener el numero de teléfono
        try {
            tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            phoneNumber = tm.getLine1Number();
        } catch (Exception e) {
            phoneNumber = "012345678";// Antonio Alameda
            AppLog.e("PhoneData","Error recuperando el valor del terminal",e);
        }

        if ( phoneNumber.length() == 0) {
            AppLog.e("PhoneData", "Número de teléfono vacío");
        }
        //TODO: ver cómo se puede recuperar el teléfono de una segunda tarjeta
    }

    /**
     * Getter del numero de teléfono
     * @return phoneNumber
     */
    public String getNumber() {
        return phoneNumber;
    }
}
