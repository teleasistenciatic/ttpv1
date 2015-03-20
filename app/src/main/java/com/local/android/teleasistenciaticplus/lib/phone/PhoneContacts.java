package com.local.android.teleasistenciaticplus.lib.phone;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.local.android.teleasistenciaticplus.modelo.GlobalData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FESEJU on 20/03/2015.
 * Clase que automatiza y abstrae el manejo de los datos de la
 * libreta de direcciones.
 */
public class PhoneContacts {

    private String displayName;
    private String hasPhoneNumber;
    private String phoneNumber;
    private String contactId;

    /**
     *
     * @param data
     */
    public PhoneContacts(Intent data) {

        GetPhoneNumberByIntentData(data);

    }

    /**
     * Devuelve los datos de un contacto
     * @return Map con los datos que necesitamos del contacto
     */
    public Map getPhoneContact() {

        Map<String, String> contactMap = new HashMap<>();
        contactMap.put("displayName", displayName);
        contactMap.put("hasPhoneNumber", hasPhoneNumber);
        contactMap.put("phoneNumber", phoneNumber);
        contactMap.put("contactId", contactId);

        return contactMap;
    }


    /**
     * Obtiene el numero de teléfono a partir de los datos obtenidos
     * de la llamada a un intent
     * @param data
     * @return number Número de teléfono
     */
    private void GetPhoneNumberByIntentData(Intent data)
    {
        //Contexto desde nuestro singleton
        Context context = GlobalData.getAppContext();

        Uri contactData = data.getData();
        Cursor c = context.getContentResolver().query(contactData, null, null, null, null);

        if (c.moveToFirst()) {

            displayName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            hasPhoneNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

            phoneNumber = GetPhoneNumber( contactId );
        }
    }

    /**
     * Los datos de numero de teléfono no se encuentran en .Contacts si no en .Phone
     * @param id
     * @return cadena con el numero de teléfono
     */
    private String GetPhoneNumber(String id)
    {
        Context context = GlobalData.getAppContext();
        String number = "";
        Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone._ID + " = " + id, null, null);

        if(phones.getCount() > 0)
        {
            while(phones.moveToNext())
            {
                number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            System.out.println(number);
        }

        phones.close();

        return number;
    }
}
