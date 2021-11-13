package pe.gob.indecopi.util;

import pe.gob.indecopi.bean.ClsCalculoBean;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import javax.imageio.ImageIO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class ClsPlantilla {

    static Logger logger = Logger.getLogger(ClsPlantilla.class);

    //Ancho máximo
    public static int MAX_WIDTH = 180;
    //Alto máximo
    public static int MAX_HEIGHT = 180;

    public ClsPlantilla() {
        super();
    }

/* public static String doGenerarIdentificador(String strURL, String strRMethod, String strJson,String strToken){
//public static String doConsumirRest(String strURL, String strRMethod, String strJson){
    
    String strResult = "";
    URL urlREST;
    try{
        urlREST = new URL(strURL);
        HttpURLConnection conn = (HttpURLConnection) urlREST.openConnection();
        
        conn.setDoOutput(true);
        conn.setRequestMethod(strRMethod);
        conn.setRequestProperty("Authorization", strToken);  
        conn.setRequestProperty("Content-Type", "application/json");
        //System.out.println(">>URL>>"+ strURL + ">>" +strRMethod);
        //System.out.println(">>JSON>>"+ strJson);
        OutputStream os = conn.getOutputStream();
        os.write(strJson.getBytes());
        os.flush();
        
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        for (String line; (line = br.readLine()) != null; strResult += line);
        conn.disconnect();
    }catch (Exception e){
        strResult = "ERROR";
        e.printStackTrace();
    }  
    
    return strResult;
} */

    /* @SuppressWarnings("unchecked")
    public String doGenerarCertificado(ClsCertificadoBean objCertificado, String strPlantilla,String strRutaArchivoTemp){
        
        try{
            
            ClsProperties clsProps = new ClsProperties();
            String rutasProps;
            
                rutasProps = clsProps.doUbicacion();
            
                String[] vcSplitRutasProps = rutasProps.split("¬");
                String vcPathWord = vcSplitRutasProps[0];
                String vcPathPdf = vcSplitRutasProps[1];
                String vcPathTmp = vcSplitRutasProps[2];
                String vcPathDwld = vcSplitRutasProps[3];
                String vcPathImg  = vcSplitRutasProps[4];
            
        //Numeracion Autores
            int j=1;
            for(ClsAutoresBean objTitulares : objCertificado.getLstTitulares()){
                DecimalFormat formatter = new DecimalFormat("00");
                objTitulares.setVcNroAutor(formatter.format(j));
                j++;
            }
            j=1;
            for(ClsAutoresBean objAutores : objCertificado.getLstAutores()){
                DecimalFormat formatter = new DecimalFormat("00");
                objAutores.setVcNroAutor(formatter.format(j));
                j++;
            }
            
        
        File reportFile= new File(strPlantilla);

        String vcNombreArchivo = "doc_" + ClsUtils.doGenerarNombreArchivoAleatorio();
            
        if(reportFile.exists()){
            logger.info("Existe la plantilla.");
        }else{
            logger.info("No existe la plantilla.");
        }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ClsUtils.doListDataSource());    
            
            HashMap map = new HashMap();  
            
            map.put("vcPathImagen", vcPathImg);
            map.put("vcTituloObra", objCertificado.getVcTituloObra());
            
            map.put("vcIneditaUPublicada", objCertificado.getVcIneditaUPublicada());
            
            map.put("vcPartidaRegistral", objCertificado.getVcPartidaRegistral());
            map.put("vcNroAsiento", objCertificado.getVcNroAsiento());
            map.put("vcFechaPresentacion", objCertificado.getVcFechaPresentacion());
            
            map.put("vcFechaInscripcion", objCertificado.getVcFechaRegistro());
            
            map.put("vcNroExpediente", objCertificado.getVcNroExpediente()+"-"+objCertificado.getVcAnioExpediente());
            map.put("vcTipoObra", objCertificado.getVcOriginalDerivada());
            map.put("vcPaginas", objCertificado.getVcNroPaginas());
            
            map.put("vcLugarPublicacion", objCertificado.getVcLugarPublicacion());
            map.put("vcFechaPublicacion", objCertificado.getVcFechaPublicacion());
            map.put("vcOriginalDerivada", objCertificado.getVcOriginalDerivada());
            map.put("vcNroEjemplar", objCertificado.getVcNroEjemplar());
            
            map.put("vcObservaciones", objCertificado.getVcObservacion());
            
            JRBeanCollectionDataSource lstCollectionTitulares = new JRBeanCollectionDataSource(objCertificado.getLstTitulares());
            map.put("lstTitulares", lstCollectionTitulares); 
            
            JRBeanCollectionDataSource lstCollectionAutor = new JRBeanCollectionDataSource(objCertificado.getLstAutores());
            map.put("lstAutores", lstCollectionAutor);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), map, beanCollectionDataSource);
            
            JasperExportManager.exportReportToPdfFile(jasperPrint, strRutaArchivoTemp);

        }catch (Exception e){
            logger.info(e);
            strRutaArchivoTemp=null;
        }
        
        return strRutaArchivoTemp;
    } */
    
    /* public String doGenerarReporteConsumidor_Pre(ClsCalculoBean objCalculo, String strPlantilla,String strRutaArchivoTemp){
        
        try{
            
            ClsProperties clsProps = new ClsProperties();
            String rutasProps;
            
                rutasProps = clsProps.doUbicacion();
            
                String[] vcSplitRutasProps = rutasProps.split("¬");
                String vcPathWord = vcSplitRutasProps[0];
                String vcPathPdf = vcSplitRutasProps[1];
                String vcPathTmp = vcSplitRutasProps[2];
                String vcPathDwld = vcSplitRutasProps[3];
                String vcPathImg  = vcSplitRutasProps[4];
            
        File reportFile = new File(strPlantilla);

        if(reportFile.exists()){
            logger.info("Existe el archivo.");
        }else{
            logger.info("No existe el archivo.");
        }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ClsUtils.doListDataSource());    
            
            
            HashMap map = new HashMap();  
            
            map.put("vcPathImagen", vcPathImg);
            
            map.put("vcOrgResolutivo", objCalculo.getVcOrgResolutivo());
            
            map.put("vcRuc", objCalculo.getVcRuc());
            map.put("vcRazonSocial", objCalculo.getVcRazonSocial());
            map.put("vcTamEmpresa", objCalculo.getVcTamEmpresa());
            map.put("vcTipoAfectacion", objCalculo.getVcTipoAfectacion());
        
            map.put("vcNivelAfectacion", objCalculo.getVcNivelAfectacion());
            map.put("nuFactorDuracion", objCalculo.getNuFactorDuracion());
            
            map.put("nuMultaBase", objCalculo.getNuMultaBase());
            map.put("nuValorFactorAA", objCalculo.getNuValorFactorAA());
            map.put("nuMultaPreUIT", objCalculo.getNuMultaPreUIT());
            map.put("nuIngresosTope", objCalculo.getNuIngresosTope());
            
            map.put("nuMultaFinal", objCalculo.getNuMultaFinal());
            
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), map, beanCollectionDataSource);
            
            JasperExportManager.exportReportToPdfFile(jasperPrint, strRutaArchivoTemp);
            
        }catch (Exception e){
            logger.info(e);
            strRutaArchivoTemp=null;
        }
        
        return strRutaArchivoTemp;
    } */
    

    /*-----*/
}
