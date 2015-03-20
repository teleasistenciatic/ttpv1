package com.local.android.teleasistenciaticplus.lib.phone;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.local.android.teleasistenciaticplus.modelo.GlobalData;

/**
 * Created by FESEJU on 20/03/2015.
 * Clase que automatiza y abstrae el manejo de los datos de la
 * libreta de direcciones.
 */
public class PhoneContacts {

    /**
     * Obtiene el numero de teléfono a partir de los datos obtenidos
     * de la llamada a un intent
     * @param data
     * @return number Número de teléfono
     */
    public String GetPhoneNumberByData(Intent data)
    {

        Context context = GlobalData.getAppContext();
        Uri contactData = data.getData();
        Cursor c = context.getContentResolver().query(contactData, null, null, null, null);

        String number = null;
        if (c.moveToFirst()) {

            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String hasPhoneNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

            number = new PhoneContacts().GetPhoneNumber( contactId );
        }

        return number;
    }

    public String GetPhoneNumber(String id)
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
