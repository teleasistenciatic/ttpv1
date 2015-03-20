package com.local.android.teleasistenciaticplus.act.debug;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.local.android.teleasistenciaticplus.R;
import com.local.android.teleasistenciaticplus.lib.networking.SmsDispatcher;
import com.local.android.teleasistenciaticplus.lib.phone.PhoneContacts;


/**
 * Actividad de DEBUG que enviará un SMS al numero de teléfono indicado
 */
public class actDebugSMS extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_debug_sms);
    }

    /**
     * Debug SMS send
     *
     * @param view vista
     */
    public void sms_send(View view) {

        TextView phoneNumberEdit = (TextView) findViewById(R.id.debug_edit_sms_number);
        String phoneNumber = phoneNumberEdit.getText().toString();

        TextView smsMessageEdit = (TextView) findViewById(R.id.debug_edit_sms_message);
        String smsBodyText = smsMessageEdit.getText().toString();

        new SmsDispatcher(phoneNumber, smsBodyText).send();
    }


    public void get_contact_from_contactlist(View view) {

        //Abrir la lista de contactos
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

        startActivityForResult(intent, 1);

    }

/*
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (1):
                if (resultCode == Activity.RESULT_OK) {

                    String phoneNumber = new PhoneContacts().GetPhoneNumberByData( data );
                    Toast.makeText(getApplicationContext(), phoneNumber, Toast.LENGTH_SHORT).show();


                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);

                    if (c.moveToFirst()) {

                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String hasPhoneNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

                        String phoneNumber = new PhoneContacts().GetPhoneNumber( contactId );

                        // TODO Whatever you want to do with the selected contact name.
                        Toast.makeText(getApplicationContext(), phoneNumber, Toast.LENGTH_SHORT).show();

                    }

                }
                break;
        }

    }*/

    /**
     * Salida de la aplicación al pulsar el botón de salida del layout
     *
     * @param view vista
     */
    public void exit_button(View view) {
        finish();
    }

}
