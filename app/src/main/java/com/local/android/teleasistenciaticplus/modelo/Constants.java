package com.local.android.teleasistenciaticplus.modelo;

/**
 * Created by FESEJU on 11/02/2015.
 * Interfaz de constantes generales de la aplicación
 */

public interface Constants {

    ////////////////////////////////////////////////
    // VALORES DE DESARROLLO DE LA APLICACIÓN
    ////////////////////////////////////////////////

        public static final DebugLevel DEBUG_LEVEL = DebugLevel.DEBUG;

        public static final Boolean LOG_TO_FILE = false;
        public static final String DEBUG_LOG_FILE = "sdcard/teleasistencia.log";

    ////////////////////////////////////////////////
    // MISCELANEA
    ////////////////////////////////////////////////

        public static final long LOADING_SCREEN_DELAY = 2000; //Con 1000 a veces da problemas, no le ha dado tiempo a terminar de ejecutar la lectura del archivo
        public static final long MEMORY_DIVIDER = 1048576L; //BytestoMegabytes

        /*
        1024 bytes      == 1 kilobyte
        1024 kilobytes  == 1 megabyte

        1024 * 1024     == 1048576*/


    ////////////////////////////////////////////////
    // SERVIDOR TELEASISTENCIA
    ////////////////////////////////////////////////

        public static final String SERVER_PROTOCOL = "http://";
        public static final String SERVER_IP = "10.0.0.190";
        //public static final String SERVER_ADDRESS = SERVER_PROTOCOL + "ttpserver.dev";
        public static final String SERVER_URL = SERVER_PROTOCOL + SERVER_IP;

        ////////////////////////////////////////////////
        // Controladores
        ////////////////////////////////////////////////
        public static final String CONTROLLER_CHECK_ONLINE_SERVER = "/serverstatus";
        public static final String CONTROLLER_CHECK_PHONE = "/phone/check/";
        public static final String CONTROLLER_USER_NAME = "/phoneuser/name/";
        public static final String CONTROLLER_AVISO_CHECK = "/aviso/check/";
        public static final String CONTROLLER_AVISO_CREATE = "/aviso/create/";
        public static final String CONTROLLER_AVISO_DELETE = "/aviso/delete/";

    ////////////////////////////////////////////////
    // OPERACIONES HTTP
    ////////////////////////////////////////////////
    public static final int HTTP_OPERATION_DELAY = 1000;

}