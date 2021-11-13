package pe.gob.indecopi.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Properties;

import org.apache.log4j.Logger;

public class ClsProperties implements Serializable {
    @SuppressWarnings("compatibility:-5608655380465793837")
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(ClsProperties.class);

  public ClsProperties() {
    super();
  }

  public String doUbicacion() throws FileNotFoundException {
    logger.info(">>doUbicacion");
    Properties props = new Properties();
    String rutasPlantillas = null;
    String dbPropsFile = "/pe/gob/indecopi/dao/connection.properties";
    String vc_so = "lnx";
    if (!System.getProperty("file.separator").equals("/"))
      vc_so = "win";
    String url_uploadfile = "";
    String url_uploadfile_sep = "";
    String url_uploadfile_word = "";
    String url_uploadfile_pdf = "";
    String url_uploadfile_tmp = "";
    String url_uploadfile_dwld = "";
    String vcPath = "";
    String url_uploadfile_img = "";
    String vcPathImg = "";
    InputStream inputStream = this.getClass().getResourceAsStream(dbPropsFile);
    if (inputStream == null) {
      throw new FileNotFoundException("Error: no se encuentra archivo properties");
    }
    try {
      props.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException("No se puede leer el archivo: " + dbPropsFile + e);
    }
    url_uploadfile = props.getProperty("file_destino_" + vc_so);
    url_uploadfile_sep = props.getProperty("file_folder_separador_" + vc_so);
    url_uploadfile_img = props.getProperty("file_img");

    vcPath = url_uploadfile + url_uploadfile_sep + "plantillas" + url_uploadfile_sep;
    vcPathImg = url_uploadfile + url_uploadfile_sep + "plantillas" + url_uploadfile_sep + url_uploadfile_img + url_uploadfile_sep;

    logger.info(">>La carpeta de destino : " + vcPath);
    logger.info(">>La carpeta destino img     : " + vcPathImg);

    rutasPlantillas = vcPath + "¬" + vcPathImg + "¬" + url_uploadfile_sep;

    return rutasPlantillas;
  }

  public String doUbicacionImagen() throws FileNotFoundException, IOException {
    logger.info(">>doUbicacionImagenes");
    Properties props = new Properties();
    String rutasImagenes = null;
    String dbPropsFile = "/pe/gob/indecopi/dao/connection.properties";
    String vc_so = "lnx";
    if (!System.getProperty("file.separator").equals("/"))
      vc_so = "win";
    String url_uploadfile = "";
    String url_uploadfile_sep = "";
    String url_uploadfile_dsd = "";
    InputStream inputStream = this.getClass().getResourceAsStream(dbPropsFile);
    if (inputStream == null) {
      throw new FileNotFoundException("Error: no se encuentra archivo properties");
    }
    props.load(inputStream);
    url_uploadfile = props.getProperty("file_origen_logo_" + vc_so);
    url_uploadfile_sep = props.getProperty("file_folder_separador_" + vc_so);

    rutasImagenes = url_uploadfile + "¬" + url_uploadfile_sep;
    //        logger.info(">>La ruta del Origen: " + rutasImagenes);
    return rutasImagenes;
  }

  public String doUbicacionSamba() throws FileNotFoundException, IOException {
    logger.info(">>doUbicacionSamba");


    Properties props = new Properties();
    String dbPropsFile = "/pe/gob/indecopi/dao/connection.properties";
    String vc_so = "lnx";
    if (!System.getProperty("file.separator").equals("/"))
      vc_so = "win";
    String rutasPlantillas = null;
    String url_uploadfile = "";
    String url_uploadfile_sep = "";
    String url_uploadfile_usr = "";
    String url_uploadfile_pas = "";
    String url_uploadfile_smb = "";
    String url_uploadfile_smb_imagenes = "";
    String url_uploadfile_sepsmb = "";
    String url_uploadfile_word = "";
    String url_uploadfile_pdf = "";
    String url_uploadfile_tmp = "";
    String url_uploadfile_dwld = "";
    String vcPathWord = "";
    String vcPathPdf = "";
    String vcPathTmp = "";
    String vcPathDwld = "";

    InputStream inputStream = this.getClass().getResourceAsStream(dbPropsFile);
    if (inputStream == null) {
      throw new FileNotFoundException("Error: no se encuentra archivo properties");
    }
    props.load(inputStream);


    url_uploadfile = props.getProperty("file_destino_" + vc_so);
    url_uploadfile_sep = props.getProperty("file_folder_separador_" + vc_so);
    url_uploadfile_word = props.getProperty("file_word");
    url_uploadfile_pdf = props.getProperty("file_pdf");
    url_uploadfile_tmp = props.getProperty("file_tmp");
    url_uploadfile_dwld = props.getProperty("file_dwld");
    //------------------------------------------//

    vcPathWord = url_uploadfile + url_uploadfile_sep + url_uploadfile_word + url_uploadfile_sep;
    vcPathPdf = url_uploadfile + url_uploadfile_sep + url_uploadfile_pdf + url_uploadfile_sep;
    vcPathTmp = url_uploadfile + url_uploadfile_sep + url_uploadfile_tmp + url_uploadfile_sep;
    vcPathDwld = url_uploadfile + url_uploadfile_sep + url_uploadfile_dwld + url_uploadfile_sep;

    url_uploadfile_sepsmb = props.getProperty("file_folder_separador_smb");
    url_uploadfile_smb_imagenes = props.getProperty("smb_imagenes");
    url_uploadfile_usr = props.getProperty("file_user_win");
    url_uploadfile_pas = props.getProperty("file_password_win");
    url_uploadfile_smb = props.getProperty("smb_general");

    logger.info(">>La carpeta de destino word : " + vcPathWord);
    logger.info(">>La carpeta de destino  pdf : " + vcPathPdf);
    logger.info(">>La carpeta de destino  tmp : " + vcPathTmp);
    logger.info(">>La carpeta de destino dwld : " + vcPathDwld);

    rutasPlantillas =
      vcPathWord + "¬" + vcPathPdf + "¬" + vcPathTmp + "¬" + vcPathDwld + "¬" + url_uploadfile_sepsmb + "¬" + url_uploadfile_smb_imagenes + "¬" + url_uploadfile_usr + "¬" + url_uploadfile_pas + "¬" +
      url_uploadfile_smb;

    return rutasPlantillas;
  }


  public String getDirectoryFileServerNotifica() {
    Properties props = new Properties();
    String dbPropsFile = "/pe/gob/indecopi/dao/connection.properties";
    String sFileServer = "";
    String sSeparador = "";
    String sCarpeta = "";
    String sPath = "";
    try {
      InputStream inputStream = this.getClass().getResourceAsStream(dbPropsFile);
      if (inputStream == null) {
        throw new FileNotFoundException("property file '" + dbPropsFile + "' not found in the classpath");
      }
      props.load(inputStream);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    String vc_so = "lnx";
    if (!System.getProperty("file.separator").equals("/"))
      vc_so = "win";

    sFileServer = props.getProperty("file_destino_not_" + vc_so);
    sSeparador = props.getProperty("file_separator_" + vc_so);
    sCarpeta = props.getProperty("path_fileserver_doc_" + vc_so);
    sPath = sFileServer + sSeparador + sCarpeta + sSeparador;

    logger.debug("FILE SERVER NOTIFICACION: " + sPath);
    return sPath;
  }

  public String getDirectoryFileServerRM() {
    Properties props = new Properties();
    String dbPropsFile = "/pe/gob/indecopi/dao/connection.properties";
    String sFileServer = "";
    String sSeparador = "";
    String sCarpeta = "";
    String sPath = "";
    try {
      InputStream inputStream = this.getClass().getResourceAsStream(dbPropsFile);
      if (inputStream == null) {
        throw new FileNotFoundException("property file '" + dbPropsFile + "' not found in the classpath");
      }
      props.load(inputStream);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    String vc_so = "lnx";
    if (!System.getProperty("file.separator").equals("/"))
      vc_so = "win";

    sFileServer = props.getProperty("file_destino_rm_" + vc_so);
    sSeparador = props.getProperty("file_separator_" + vc_so);
    sCarpeta = props.getProperty("path_fileserver_rm_" + vc_so);
    sPath = sFileServer + sSeparador + sCarpeta + sSeparador;

    logger.debug("FILE SERVER NOTIFICACION: " + sPath);
    return sPath;
  }

  public String getDirectoryFileServerObras() {
    Properties props = new Properties();
    String dbPropsFile = "/pe/gob/indecopi/dao/connection.properties";
    String sFileServer = "";
    String sSeparador = "";
    String sCarpeta = "";
    String sPath = "";
    try {
      InputStream inputStream = this.getClass().getResourceAsStream(dbPropsFile);
      if (inputStream == null) {
        throw new FileNotFoundException("property file '" + dbPropsFile + "' not found in the classpath");
      }
      props.load(inputStream);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    String vc_so = "lnx";
    if (!System.getProperty("file.separator").equals("/"))
      vc_so = "win";

    sFileServer = props.getProperty("file_destino_obras_" + vc_so);

    sPath = sFileServer + sSeparador + sCarpeta + sSeparador;

    return sPath;
  }


}
