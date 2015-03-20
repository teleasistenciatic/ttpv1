package com.local.android.teleasistenciaticplus.lib.phone;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.local.android.teleasistenciaticplus.modelo.GlobalData;

/**
 * Created by FESEJU on 20/03/2015.
 */
public class PhoneContacts {

    /*
    public String GetPhoneNumberByData(Intent data)
    {

        Context context = GlobalData.getAppContext();
        Uri contactData = data.getData();
        Cursor c = context.getContentResolver().query(contactData, null, null, null, null);

        if (c.moveToFirst()) {

            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String hasPhoneNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

            String phoneNumber = new PhoneContacts().GetPhoneNumber( contactId );

            // TODO Whatever you want to do with the selected contact name.
            Toast.makeText(getApplicationContext(), phoneNumber, Toast.LENGTH_SHORT).show();

        }


        return number;
    }*/

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
