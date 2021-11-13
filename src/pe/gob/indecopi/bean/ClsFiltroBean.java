package pe.gob.indecopi.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

public class ClsFiltroBean implements Serializable {
    @SuppressWarnings("compatibility:7554499064044373945")
    private static final long serialVersionUID = 1L;

    private String vcIdRRHH;
    private String vcUsuarioApp;
    private String vcNombreUsuario;
    private int nuIDTipoAplicacion;
    private int nuIDTipoConsulta;
    private Date dtFechaInicio;
    private Date dtFechaFin;
    private Date dtPrFechaInicio;
    private Date dtPrFechaFin;
    private String vcNroExpediente;
    private String vcNroResolucion;
    private String vcNroCertificado;
    private String vcUsuarioSeg; 
    private String vcIdProfesional;
    private String vcIdPlazo;
    private String vcSentidoResolucion;
    private String vcIdTipoSolicitud;
    private String vcIdTipoNotificacion;
    private String vcIdUsuarioAsignado;    
    private String vcTipoPresentacion;
    private String vcFormaEnvio;
    private String vcIdSerieExpediente;
    private int nuIdEstadoDocumento;
    
    private String vcCoordinador;
    private boolean boPdfCreado;
    private boolean boWordCreado;
    private boolean boTodosDocPdf;
    private int nuEstadoPdf;
    private int nuTipoReporte;
    
    private List<SelectItem> lstCoordinadores;
    private List<SelectItem> lstEstadoDocumentos;
    private List<SelectItem> lstFormaEnvio;
    private List<SelectItem> lstPlazos;
    private List<SelectItem> lstProfesionales;
    private List<SelectItem> lstSentidoResoluciones;
    private List<SelectItem> lstSerieExpediente;
    private List<SelectItem> lstTipoNotificacion;
    private List<SelectItem> lstTipoPresentacion;
    private List<SelectItem> lstTipoSolicitudes;
    private List<SelectItem> lstUsuarioAsignado;
    private List<SelectItem> lstPdfEstado;
    
    private ClsConstantesBean clsConstantes;
    
    private Integer nuIdCategoriaNotificacion;
    private Integer nuIdTipoNotificacion;
    private String vcCategoriaNotificacion;
    private String vcTipoNotificacion;
    private boolean blFlagSelecCboEnvio;
    private boolean blFlagMensajeHorario;
    private String vcMensajeHorario;
    private Map<String, String> lstCategoria;
    private Map<String, String> lstTipoNot;
    private Map<String, String> lstEstadoNotificacion;
    private Map<String, String> lstTipoReclasificacion;
    private int nuEstadoNotificacion;
    private boolean blFlagCondicionEnvio;
    
    //Descarga auxiliar desde un modal
    private String vcRutaDescarga;
    private String vcNombreDescarga;
    private String vcNombreExterno;
    private String vcExtensionDescarga;
    private String vcNombreExternoDescarga;
    private int nuFilaSeleccionada;
    
    //Anexos expediente
    private String vclinkArchivo;
    private Integer nuIdTramite;
    
    //Reclasificacion 
    private Integer nuIdTipoReclasificacion;
    
    private boolean blFlagReiniciar;
    
    //DDA
    private String vcAnioExpediente;
    

        
    public ClsFiltroBean() {
        super();
        this.setBlFlagSelecCboEnvio(false);
        this.setBlFlagMensajeHorario(false);
        
        this.lstCategoria= new LinkedHashMap<String, String>();
        this.lstTipoNot=new LinkedHashMap<String, String>();
        this.lstEstadoNotificacion=new LinkedHashMap<String, String>();
        this.setNuEstadoNotificacion(-1);
        this.setBlFlagCondicionEnvio(true);//se muetsra el boton enviar a casilla
    }

    public void setVcAnioExpediente(String vcAnioExpediente) {
        this.vcAnioExpediente = vcAnioExpediente;
    }

    public String getVcAnioExpediente() {
        return vcAnioExpediente;
    }

    public void setLstPdfEstado(List<SelectItem> lstPdfEstado) {
        this.lstPdfEstado = lstPdfEstado;
    }

    public void setNuEstadoPdf(int nuEstadoPdf) {
        this.nuEstadoPdf = nuEstadoPdf;
    }

    public int getNuEstadoPdf() {
        return nuEstadoPdf;
    }

    public List<SelectItem> getLstPdfEstado() {
        return lstPdfEstado;
    }

    public void setNuTipoReporte(int nuTipoReporte) {
        this.nuTipoReporte = nuTipoReporte;
    }

    public int getNuTipoReporte() {
        return nuTipoReporte;
    }
    
    public void setVcIdRRHH(String vcIdRRHH) {
        this.vcIdRRHH = vcIdRRHH;
    }

    public String getVcIdRRHH() {
        return vcIdRRHH;
    }


    public void setBoPdfCreado(boolean boPdfCreado) {
        this.boPdfCreado = boPdfCreado;
    }

    public boolean isBoPdfCreado() {
        return boPdfCreado;
    }

    public void setBoWordCreado(boolean boWordCreado) {
        this.boWordCreado = boWordCreado;
    }

    public boolean isBoWordCreado() {
        return boWordCreado;
    }

    public void setBoTodosDocPdf(boolean boTodosDocPdf) {
        this.boTodosDocPdf = boTodosDocPdf;
    }

    public boolean isBoTodosDocPdf() {
        return boTodosDocPdf;
    }

    public void setVcUsuarioApp(String vcUsuarioApp) {
        this.vcUsuarioApp = vcUsuarioApp;
    }

    public String getVcUsuarioApp() {
        return vcUsuarioApp;
    }

    public void setNuIDTipoAplicacion(int nuIDTipoAplicacion) {
        this.nuIDTipoAplicacion = nuIDTipoAplicacion;
    }

    public int getNuIDTipoAplicacion() {
        return nuIDTipoAplicacion;
    }

    public void setNuIDTipoConsulta(int nuIDTipoConsulta) {
        this.nuIDTipoConsulta = nuIDTipoConsulta;
    }

    public int getNuIDTipoConsulta() {
        return nuIDTipoConsulta;
    }

    public void setDtFechaInicio(Date dtFechaInicio) {
        this.dtFechaInicio = dtFechaInicio;
    }

    public void setVcCoordinador(String vcCoordinador) {
        this.vcCoordinador = vcCoordinador;
    }

    public String getVcCoordinador() {
        return vcCoordinador;
    }

    public Date getDtFechaInicio() {
        return dtFechaInicio;
    }

    public void setDtFechaFin(Date dtFechaFin) {
        this.dtFechaFin = dtFechaFin;
    }

    public Date getDtFechaFin() {
        return dtFechaFin;
    }

    public void setDtPrFechaInicio(Date dtPrFechaInicio) {
        this.dtPrFechaInicio = dtPrFechaInicio;
    }

    public Date getDtPrFechaInicio() {
        return dtPrFechaInicio;
    }

    public void setDtPrFechaFin(Date dtPrFechaFin) {
        this.dtPrFechaFin = dtPrFechaFin;
    }

    public Date getDtPrFechaFin() {
        return dtPrFechaFin;
    }

    public void setVcNroExpediente(String vcNroExpediente) {
        this.vcNroExpediente = vcNroExpediente;
    }

    public String getVcNroExpediente() {
        return vcNroExpediente;
    }

    public void setVcNroResolucion(String vcNroResolucion) {
        this.vcNroResolucion = vcNroResolucion;
    }

    public String getVcNroResolucion() {
        return vcNroResolucion;
    }

    public void setVcNroCertificado(String vcNroCertificado) {
        this.vcNroCertificado = vcNroCertificado;
    }

    public String getVcNroCertificado() {
        return vcNroCertificado;
    }

    public void setVcUsuarioSeg(String vcUsuarioSeg) {
        this.vcUsuarioSeg = vcUsuarioSeg;
    }

    public String getVcUsuarioSeg() {
        return vcUsuarioSeg;
    }

    public void setVcIdProfesional(String vcIdProfesional) {
        this.vcIdProfesional = vcIdProfesional;
    }

    public String getVcIdProfesional() {
        return vcIdProfesional;
    }

    public void setVcIdPlazo(String vcIdPlazo) {
        this.vcIdPlazo = vcIdPlazo;
    }

    public String getVcIdPlazo() {
        return vcIdPlazo;
    }

    public void setVcSentidoResolucion(String vcSentidoResolucion) {
        this.vcSentidoResolucion = vcSentidoResolucion;
    }

    public String getVcSentidoResolucion() {
        return vcSentidoResolucion;
    }

    public void setVcIdTipoSolicitud(String vcIdTipoSolicitud) {
        this.vcIdTipoSolicitud = vcIdTipoSolicitud;
    }

    public String getVcIdTipoSolicitud() {
        return vcIdTipoSolicitud;
    }

    public void setVcIdTipoNotificacion(String vcIdTipoNotificacion) {
        this.vcIdTipoNotificacion = vcIdTipoNotificacion;
    }

    public String getVcIdTipoNotificacion() {
        return vcIdTipoNotificacion;
    }

    public void setVcIdUsuarioAsignado(String vcIdUsuarioAsignado) {
        this.vcIdUsuarioAsignado = vcIdUsuarioAsignado;
    }

    public String getVcIdUsuarioAsignado() {
        return vcIdUsuarioAsignado;
    }

    public void setVcTipoPresentacion(String vcTipoPresentacion) {
        this.vcTipoPresentacion = vcTipoPresentacion;
    }

    public String getVcTipoPresentacion() {
        return vcTipoPresentacion;
    }

    public void setVcFormaEnvio(String vcFormaEnvio) {
        this.vcFormaEnvio = vcFormaEnvio;
    }

    public String getVcFormaEnvio() {
        return vcFormaEnvio;
    }

    public void setVcIdSerieExpediente(String vcIdSerieExpediente) {
        this.vcIdSerieExpediente = vcIdSerieExpediente;
    }

    public String getVcIdSerieExpediente() {
        return vcIdSerieExpediente;
    }

    public void setNuIdEstadoDocumento(int nuIdEstadoDocumento) {
        this.nuIdEstadoDocumento = nuIdEstadoDocumento;
    }

    public int getNuIdEstadoDocumento() {
        return nuIdEstadoDocumento;
    }

    public void setLstCoordinadores(List<SelectItem> lstCoordinadores) {
        this.lstCoordinadores = lstCoordinadores;
    }

    public List<SelectItem> getLstCoordinadores() {
        return lstCoordinadores;
    }

    public void setLstEstadoDocumentos(List<SelectItem> lstEstadoDocumentos) {
        this.lstEstadoDocumentos = lstEstadoDocumentos;
    }

    public List<SelectItem> getLstEstadoDocumentos() {
        return lstEstadoDocumentos;
    }

    public void setLstFormaEnvio(List<SelectItem> lstFormaEnvio) {
        this.lstFormaEnvio = lstFormaEnvio;
    }

    public List<SelectItem> getLstFormaEnvio() {
        return lstFormaEnvio;
    }

    public void setLstPlazos(List<SelectItem> lstPlazos) {
        this.lstPlazos = lstPlazos;
    }

    public List<SelectItem> getLstPlazos() {
        return lstPlazos;
    }

    public void setLstProfesionales(List<SelectItem> lstProfesionales) {
        this.lstProfesionales = lstProfesionales;
    }

    public List<SelectItem> getLstProfesionales() {
        return lstProfesionales;
    }

    public void setLstSentidoResoluciones(List<SelectItem> lstSentidoResoluciones) {
        this.lstSentidoResoluciones = lstSentidoResoluciones;
    }

    public List<SelectItem> getLstSentidoResoluciones() {
        return lstSentidoResoluciones;
    }

    public void setLstSerieExpediente(List<SelectItem> lstSerieExpediente) {
        this.lstSerieExpediente = lstSerieExpediente;
    }

    public List<SelectItem> getLstSerieExpediente() {
        return lstSerieExpediente;
    }

    public void setLstTipoNotificacion(List<SelectItem> lstTipoNotificacion) {
        this.lstTipoNotificacion = lstTipoNotificacion;
    }

    public List<SelectItem> getLstTipoNotificacion() {
        return lstTipoNotificacion;
    }

    public void setLstTipoPresentacion(List<SelectItem> lstTipoPresentacion) {
        this.lstTipoPresentacion = lstTipoPresentacion;
    }

    public List<SelectItem> getLstTipoPresentacion() {
        return lstTipoPresentacion;
    }

    public void setLstTipoSolicitudes(List<SelectItem> lstTipoSolicitudes) {
        this.lstTipoSolicitudes = lstTipoSolicitudes;
    }

    public List<SelectItem> getLstTipoSolicitudes() {
        return lstTipoSolicitudes;
    }

    public void setLstUsuarioAsignado(List<SelectItem> lstUsuarioAsignado) {
        this.lstUsuarioAsignado = lstUsuarioAsignado;
    }

    public List<SelectItem> getLstUsuarioAsignado() {
        return lstUsuarioAsignado;
    }

    public void setClsConstantes(ClsConstantesBean clsConstantes) {
        this.clsConstantes = clsConstantes;
    }

    public ClsConstantesBean getClsConstantes() {
        return clsConstantes;
    }

    public void setVcNombreUsuario(String vcNombreUsuario) {
        this.vcNombreUsuario = vcNombreUsuario;
    }

    public String getVcNombreUsuario() {
        return vcNombreUsuario;
    }

    public void setNuIdCategoriaNotificacion(Integer nuIdCategoriaNotificacion) {
        this.nuIdCategoriaNotificacion = nuIdCategoriaNotificacion;
    }

    public Integer getNuIdCategoriaNotificacion() {
        return nuIdCategoriaNotificacion;
    }

    public void setNuIdTipoNotificacion(Integer nuIdTipoNotificacion) {
        this.nuIdTipoNotificacion = nuIdTipoNotificacion;
    }

    public Integer getNuIdTipoNotificacion() {
        return nuIdTipoNotificacion;
    }

    public void setVcCategoriaNotificacion(String vcCategoriaNotificacion) {
        this.vcCategoriaNotificacion = vcCategoriaNotificacion;
    }

    public String getVcCategoriaNotificacion() {
        return vcCategoriaNotificacion;
    }

    public void setVcTipoNotificacion(String vcTipoNotificacion) {
        this.vcTipoNotificacion = vcTipoNotificacion;
    }

    public String getVcTipoNotificacion() {
        return vcTipoNotificacion;
    }

    public void setBlFlagSelecCboEnvio(boolean blFlagSelecCboEnvio) {
        this.blFlagSelecCboEnvio = blFlagSelecCboEnvio;
    }

    public boolean isBlFlagSelecCboEnvio() {
        return blFlagSelecCboEnvio;
    }

    public void setBlFlagMensajeHorario(boolean blFlagMensajeHorario) {
        this.blFlagMensajeHorario = blFlagMensajeHorario;
    }

    public boolean isBlFlagMensajeHorario() {
        return blFlagMensajeHorario;
    }

    public void setVcMensajeHorario(String vcMensajeHorario) {
        this.vcMensajeHorario = vcMensajeHorario;
    }

    public String getVcMensajeHorario() {
        return vcMensajeHorario;
    }

    public void setLstCategoria(Map<String, String> lstCategoria) {
        this.lstCategoria = lstCategoria;
    }

    public Map<String, String> getLstCategoria() {
        return lstCategoria;
    }

    public void setLstTipoNot(Map<String, String> lstTipoNot) {
        this.lstTipoNot = lstTipoNot;
    }

    public Map<String, String> getLstTipoNot() {
        return lstTipoNot;
    }

    public void setLstEstadoNotificacion(Map<String, String> lstEstadoNotificacion) {
        this.lstEstadoNotificacion = lstEstadoNotificacion;
    }

    public Map<String, String> getLstEstadoNotificacion() {
        return lstEstadoNotificacion;
    }

    public void setNuEstadoNotificacion(int nuEstadoNotificacion) {
        this.nuEstadoNotificacion = nuEstadoNotificacion;
    }

    public int getNuEstadoNotificacion() {
        return nuEstadoNotificacion;
    }

    public void setBlFlagCondicionEnvio(boolean blFlagCondicionEnvio) {
        this.blFlagCondicionEnvio = blFlagCondicionEnvio;
    }

    public boolean isBlFlagCondicionEnvio() {
        return blFlagCondicionEnvio;
    }

    public void setVcRutaDescarga(String vcRutaDescarga) {
        this.vcRutaDescarga = vcRutaDescarga;
    }

    public String getVcRutaDescarga() {
        return vcRutaDescarga;
    }

    public void setVcNombreDescarga(String vcNombreDescarga) {
        this.vcNombreDescarga = vcNombreDescarga;
    }

    public String getVcNombreDescarga() {
        return vcNombreDescarga;
    }

    public void setVcExtensionDescarga(String vcExtensionDescarga) {
        this.vcExtensionDescarga = vcExtensionDescarga;
    }

    public String getVcExtensionDescarga() {
        return vcExtensionDescarga;
    }

    public void setVcNombreExternoDescarga(String vcNombreExternoDescarga) {
        this.vcNombreExternoDescarga = vcNombreExternoDescarga;
    }

    public String getVcNombreExternoDescarga() {
        return vcNombreExternoDescarga;
    }

    public void setNuFilaSeleccionada(int nuFilaSeleccionada) {
        this.nuFilaSeleccionada = nuFilaSeleccionada;
    }

    public int getNuFilaSeleccionada() {
        return nuFilaSeleccionada;
    }

    public void setNuIdTramite(Integer nuIdTramite) {
        this.nuIdTramite = nuIdTramite;
    }

    public Integer getNuIdTramite() {
        return nuIdTramite;
    }

    public void setVclinkArchivo(String vclinkArchivo) {
        this.vclinkArchivo = vclinkArchivo;
    }

    public String getVclinkArchivo() {
        return vclinkArchivo;
    }

    public void setNuIdTipoReclasificacion(Integer nuIdTipoReclasificacion) {
        this.nuIdTipoReclasificacion = nuIdTipoReclasificacion;
    }

    public Integer getNuIdTipoReclasificacion() {
        return nuIdTipoReclasificacion;
    }

    public void setLstTipoReclasificacion(Map<String, String> lstTipoReclasificacion) {
        this.lstTipoReclasificacion = lstTipoReclasificacion;
    }

    public Map<String, String> getLstTipoReclasificacion() {
        return lstTipoReclasificacion;
    }

    public void setBlFlagReiniciar(boolean blFlagReiniciar) {
        this.blFlagReiniciar = blFlagReiniciar;
    }

    public boolean isBlFlagReiniciar() {
        return blFlagReiniciar;
    }
}
