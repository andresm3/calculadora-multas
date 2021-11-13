package indecopi.gob.pe.util;

import java.io.Serializable;

public class Constantes implements Serializable {
    public static final String CRIPTO_KEY="@-;?*$]3(X!=";
    public static final String SEPARADOR="\\|";
    public static final String PARAMETRO_GET="jgapps";
    
    public static final int PERSONA_NATURAL=1;
    public static final int PERSONA_JURIDICA=2;
         
    public static final String SQL_QUERY_PARAMS="?,";
      
    public static final int TABLA_TIPOSDOC_SOLICITUD=1;
    public static final int TABLA_TIPOSDOC_NOTIFICABLES=5;
    public static final int TABLA_TIPOSARCH_NOTIFICABLES=6;
    public static final int TABLA_TIPO_SOLICITUD=10;
    public static final int TABLA_TIPO_CASILLA=11;
      
    public static final int TIPO_ARCHIVO_ESQUELA=1;
    
    public static final String KEY_ORDEN_PUBLICACION="Orden de Publicación";  
    public static final String VALUE_ORDEN_PUBLICACION="10";  
        
    public static final String OK="OK";
    public static final String ERROR="ERROR";
    public static final String FINALIZAR="FINALIZAR";
    public static final String REGRESAR="REGRESAR";
    public static final String ENVIAR="ENVIAR";
      
    public static final String NOMBRE_CONTEXTO_LOCAL="notifica";
      
    public static final String FORMATO_FECHA_COMPLETO = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATO_FECHA_DIA = "yyyy-MM-dd";
    public static final String FORMATO_FECHA_HORA = "HH:mm:ss";
    
    public static final int TIPO_REPOSITORIO_LOCAL = 1;
    public static final int TIPO_REPOSITORIO_FILESERVER = 2;
    public static final int TIPO_REPOSITORIO_UCM = 3;
    
    public static final int DOCNOT_ACTIVO = 1;//Documento de referencia (Notificable) activo
    public static final int DOCNOT_DESACTIVO = 0;//Documento de referencia (Notificable) desactivo
    
    public static final int NU_ID_TIPO_ARCHIVO_CONTRATOSUSCRIPCION = 1; //Tipo de archivo contrato de suscripcion de casillas electrónica
    public static final int NU_ID_TIPO_ARCHIVO_ANEXO = 3; //Tipo de archivo anexo
    
    public static final int NU_NOTIFICACION_PENDIENTE = 1;
    public static final int NU_NOTIFICACION_NO_PENDIENTE = 0;
    
    public static final int TIPO_SOLICITUD_REGISTRO = 1;
    public static final int TIPO_SOLICITUD_USUASO = 2;
    public static final int TIPO_SOLICITUD_DOCNOT = 3;
    public static final int TIPO_RESERVA_REGISTRO = 1;
    public static final int TIPO_RESERVA_USUASO = 2;
    public static final int TIPO_RESERVA_DOCNOT = 3;
    
    
    /*ACCION DE IMPLEMENTACION*/
    public static final int DATO_NUEVO = 1;
    public static final int DATO_EDITAR = 2;
    
}
