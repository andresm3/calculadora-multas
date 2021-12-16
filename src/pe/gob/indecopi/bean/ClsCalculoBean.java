package pe.gob.indecopi.bean;

import java.io.Serializable;

import java.math.RoundingMode;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

public class ClsCalculoBean implements Serializable {
    @SuppressWarnings("compatibility:-8053724703337184801")
    private static final long serialVersionUID = 1L;

    private String vcOrgResolutivo;
    private String vcInstancia;
    private String vcMetodo;
    private String vcRuc;
    private String vcRazonSocial;
    private String vcTamEmpresa;
    private int nuIdTamEmpresa;
    private String vcTamEmpresaIngresado;
    private String vcTipoAfectacion;
    private String vcIdNivelAfectacion;
    private String vcNivelAfectacion;
    private Date dtFechaInicio;
    private Date dtFechaFin;
    private double nuFactorDuracion;
    private String vcAnioResolucion;
    private int nuIdEstadoDocumento;
    private double nuValorMatriz;
    private double nuMultaBase;
    private double nuMultaBaseCompleto;
    private double nuValorSecEconomico;
    

    private Map<String, String> lstMetodos;
    private Map<String, String> lstInstancias;
    private Map<String, String> lstOrganosMetodo;
    
    private Map<String, String> lstTipoBarreras;
    private Map<String, String> lstTipoAlcanceBarreras;
    private Map<String, String> lstFactorPoblacionBarreras;
    private Map<String, String> lstFactorSecEconomicoBarreras;
    private Map<String, String> lstServicioProductoAcreditadoFirma;
    private Map<String, String> lstGravedadTopePi;
    private Map<String, String> lstTamEmpresaAdhoc;
    private Map<String, String> lstGravedadTopeAdhoc;

    private List<SelectItem> lstAniosUIT;
    private List<SelectItem> lstTipoAfectacion;
    private List<SelectItem> lstMetodosCalculo;
    private List<SelectItem> lstInstanciasCalculo;
    
    private List<SelectItem> lstTamanoEmpresa;
    private String vcTamEmpresaSeleccion;
    private String vcTamEmpresaSeleccionCcd;
        
    private String vcInfraccion;


    private double nuFacturacionAnual;
    private int nuAnioUIT;//no se muestra
    private double nuValorUIT;

    /////2da pantalla
    private String vcF1Val;
    private String vcF2Val;
    private String vcF3Val;
    private String vcF4Val;
    private String vcF5Val;
    private String vcF6Val;
    private String vcF01Val;
    private String vcF02Val;
    private String vcF03Val;
    private String vcF01Criterio;
    private String vcF02Criterio;
    private String vcF03Criterio;
    
    private String vcF7Val;
    private String vcF8Val;
    private String vcF9Val;
    private String vcF10Val;
    private String vcF11Val;
    private String vcF01DctoVal;
    private String vcF02DctoVal;
    private String vcF03DctoVal;
    private String vcF01DctoCriterio;
    private String vcF02DctoCriterio;
    private String vcF03DctoCriterio;

    private double nuValorIncrementoAA;
    private double nuValorDescuentoAA;
    private double nuValorIncrementoAATemp;
    private double nuValorDescuentoAATemp;
    private double nuValorFactorAA;
    private static DecimalFormat df = new DecimalFormat("0.00");
    //////////
    ///3ra pantalla
    private double nuPorcTope;
    private double nuMultaPreUIT;
    private String vcGravedadMulta;
    private double nuGravedadTope;
    private double nuIngresosTope;
    private double nuMultaFinal;
    private double nuMultaFinalSoles;
    ////////////

    //////LIBRO RECLAMACIONES
    private String vcClasificacion;
    private String vcRucLibro;
    private double nuFacturacionAnualLibro;
    private String vcAnioResolucionLibro;
    private String vcInfraccionLibro;
    private String vcTipoAfectacionLibro;
    private double nuMultaRefLibro;
    private double nuFactorFci;
    private double nuFactorFco;
    private double nuFactorAo;
    private double nuFactorAi;
    private double nuMultaBaseLibro;
    private double nuMultaBaseLibroCompleto;
    private double nuMultaAALibro;
    private double nuAjusIngLibro;

    private double nuPorcTopeLibro;
    /////////////////////////////////

    //////BARRERAS
    private String vcRucBarreras;
    private String vcIdTipoBarrera;
    private String vcTipoBarrera;
    private double nuFacturacionAnualBarrera;
    private String vcAnioResolucionBarrera;
    private String vcInfraccionBarrera;
    private String vcTipoAfectacionBarrera;
    private String vcClasificacionBarrera;
    private int nuDanioBaseBarrera;
    private double nuAjusteMinBarrera;
    private double nuAjusteMaxBarrera;
    private List<SelectItem> lstProbabilidadBarreras;
    private String vcCaso;
    private String vcDefinicionBarreras;
    private String vcIdTipoAlcanceBarrera;
    private String vcTipoAlcanceBarrera;
    private String vcIdFactPoblacionBarrera;
    private String vcFactPoblacionBarrera;
    private String vcIdFactSecEconBarrera;
    private String vcFactSecEconBarrera;
    private double nuAlcanceBarrera;
    private double nuMultaDBarrera;
    private double nuFactorPBarrera;
    private double nuMultaBaseBarrera;
    private double nuMultaBaseBarreraCompleto;
    private double nuMultaAABarrera;
    private double nuMultaAjustadaBarrera;
    private double nuAjusteIngBarrera;

    //////////////////////////////////
    //////PREESTABLECIDO PI
    private String vcInstanciaPi;
    private String vcRucPi;
    private double nuFacturacionAnualPi;
    private String vcAnioResolucionPi;
    private String vcInfraccionPi;
    private String vcOrgano1raInstancia;
    private int nuSubTipoPi;
    private String vcTipoAfectacionPi;
    private String vcIdNivelAfectacionPi;
    private String vcNivelAfectacionPi;
    private double nuMultaBasePi;
    private double nuMultaBasePiCompleto;
    private double nuMultaPreUITPi;
    private double nuGravedadTopePi;
    
    //////////////////////////////////
    //////PREESTABLECIDO CCD
    private String vcInstanciaCcd;
    private String vcRucCcd;
    private double nuFacturacionAnualCcd;
    private String vcAnioResolucionCcd;
    private String vcInfraccionCcd;
    private String vcOrgano1raInstanciaCcd;
    private String vcTipoAfectacionCcd;
    private String vcIdNivelAfectacionCcd;
    private String vcNivelAfectacionCcd;
    private double nuMultaBaseCcd;
    private double nuMultaBaseCcdCompleto;
    
    private Date dtFechaInicioCcd;
    private Date dtFechaFinCcd;
    private double nuFactorDuracionCcd;
    
    private double nuPorcVtasCcd;
    private double nuMultaPreUITCcd;
    private String vcGravedadMultaCcd;
    private double nuGravedadTopeCcd;
    private double nuIngresosTopeCcd;
    
    //////FIRMA
    private String vcRucFirma;
    private String vcIdTipoServicioFirma;
    private String vcTipoServicioFirma;
    private double nuFacturacionAnualFirma;
    private String vcAnioResolucionFirma;
    private String vcInfraccionFirma;
    private String vcTipoAfectacionFirma;
    private String vcIdNivelAfectacionFirma;
    private String vcNivelAfectacionFirma;
    private int nuDanioBaseFirma;
    private double nuFactorFirma;
    private double nuMultaBaseFirma;
    private double nuMultaBaseFirmaCompleto;
    private int nuFactorPFirma;
    private double nuMultaAAFirma;
    private double nuTopeLegalFirma;
    //private double nuAjusteMaxBarrera;
    
    //////PORCENTAJE VENTAS
    private String vcInstanciaVentas;
    private String vcOrgano1raInstanciaVentas;
    private String vcRucVentas;
    private double nuFacturacionAnualVentas;
    private double nuFacturacionAnualProductoVentas;
    private String vcAnioResolucionVentas;
    private String vcInfraccionVentas;
    private String vcTipoAfectacionVentas;
    private String vcIdNivelAfectacionVentas;
    private String vcNivelAfectacionVentas;
    private double nuUmbralFactorAVentas;
    private double nuFactorAVentas;
    private double nuFactorGVentas;
    private double nuFactorGDifVentas;
    private double nuUmbralFactorGDifVentas;
    private String vcFactorGDifAccion;
    private double nuMultaBaseVentas;
    private double nuMultaBaseVentasCompleto;
    private double nuMultaBaseUITVentas;
    private double nuMultaBaseUITVentasCompleto;
    private double nuMultaAAVentas;
    private double nuMultaPreUITVentas;
    private int nuIdGravedadVentas;
    private double nuGravedadTopeVentas;
    private String vcGravedadTopeVentas;
    private double nuGravedadPorcTopeVentas;
    private double nuAjusteIngVentas;

    //////ADHOC
    private String vcInstanciaAdhoc;
    private String vcOrgano1raInstanciaAdhoc;
    private String vcCategoriaAdhoc;
    private String vcRucAdhoc;
    private double nuFacturacionAnualAdhoc;
    private String vcAnioResolucionAdhoc;
    private String vcInfraccionAdhoc;
    private String vcTipoAfectacionAdhoc;
    private String vcIdNivelAfectacionAdhoc;
    private String vcNivelAfectacionAdhoc;
    private double nuFactorBAdhoc;
    private double nuFactorPAdhoc;
    private String vcFactorPAdhoc;
    private double nuFactorPDifAdhoc;
    private double nuUmbralFactorPDifAdhoc;
    private String vcFactorPDifAccion;
    private double nuMultaBaseAdhoc;
    private double nuMultaBaseAdhocCompleto;
    private double nuMultaBaseUITAdhoc;
    private double nuMultaBaseUITAdhocCompleto;
    private double nuMultaAAAdhoc;
    private double nuMultaPreUITAdhoc;

    private int nuIdGravedadAdhoc;
    private double nuGravedadTopeAdhoc;
    private String vcGravedadTopeAdhoc;
    private double nuGravedadPorcTopeAdhoc;
    private double nuAjusteIngAdhoc;
    
    private double nuMinUIT;
    private double nuMaxUIT;    
    private boolean isBlCheckUIT;
    private boolean isBlCheckUITCcd;
    
    private boolean isBlMultaBase;
    private boolean isBlMultaBaseLibro;
    private boolean isBlMultaBasePi;
    private boolean isBlMultaBaseBarrera;
    private boolean isBlMultaBaseCcd;
    private boolean isBlMultaBaseVentas;
    private boolean isBlMultaBaseAdhoc;
    private boolean isBlMultaBaseFirma;

    private boolean isBlLstAfectacion;
    
    public ClsCalculoBean() {
        super();
        this.lstMetodos= new LinkedHashMap<String, String>();
        this.lstInstancias=new LinkedHashMap<String, String>();
        this.lstOrganosMetodo=new LinkedHashMap<String, String>();
        this.lstTipoBarreras=new LinkedHashMap<String, String>();
        this.lstTipoAlcanceBarreras=new LinkedHashMap<String, String>();
        this.lstFactorPoblacionBarreras=new LinkedHashMap<String, String>();
        this.lstFactorSecEconomicoBarreras=new LinkedHashMap<String, String>();
        this.lstServicioProductoAcreditadoFirma=new LinkedHashMap<String, String>();
        this.lstGravedadTopePi=new LinkedHashMap<String, String>();
        this.lstGravedadTopeAdhoc=new LinkedHashMap<String, String>();
        this.lstTamEmpresaAdhoc=new LinkedHashMap<String, String>();
        this.lstAniosUIT = new ArrayList<SelectItem>();
        this.lstTipoAfectacion = new ArrayList<SelectItem>();
        this.lstProbabilidadBarreras = new ArrayList<SelectItem>();
        this.lstMetodosCalculo = new ArrayList<SelectItem>();
        this.lstInstanciasCalculo = new ArrayList<SelectItem>();
        //this.lstInfracciones=new LinkedHashMap<String, String>();
        this.lstTamanoEmpresa = new ArrayList<SelectItem>();

        df.setRoundingMode(RoundingMode.UP);
        
    }

    public void setVcOrgResolutivo(String vcOrgResolutivo) {
        this.vcOrgResolutivo = vcOrgResolutivo;
    }

    public String getVcOrgResolutivo() {
        return vcOrgResolutivo;
    }

    public void setVcInstancia(String vcInstancia) {
        this.vcInstancia = vcInstancia;
    }

    public String getVcInstancia() {
        return vcInstancia;
    }

    public void setVcMetodo(String vcMetodo) {
        this.vcMetodo = vcMetodo;
    }

    public String getVcMetodo() {
        return vcMetodo;
    }

    public void setVcRuc(String vcRuc) {
        this.vcRuc = vcRuc;
    }

    public String getVcRuc() {
        return vcRuc;
    }

    public void setVcRazonSocial(String vcRazonSocial) {
        this.vcRazonSocial = vcRazonSocial;
    }

    public String getVcRazonSocial() {
        return vcRazonSocial;
    }

    public void setVcTamEmpresa(String vcTamEmpresa) {
        this.vcTamEmpresa = vcTamEmpresa;
    }

    public String getVcTamEmpresa() {
        return vcTamEmpresa;
    }

    public void setVcTamEmpresaIngresado(String vcTamEmpresaIngresado) {
        this.vcTamEmpresaIngresado = vcTamEmpresaIngresado;
    }

    public String getVcTamEmpresaIngresado() {
        return vcTamEmpresaIngresado;
    }

    public void setVcTipoAfectacion(String vcTipoAfectacion) {
        this.vcTipoAfectacion = vcTipoAfectacion;
    }

    public String getVcTipoAfectacion() {
        return vcTipoAfectacion;
    }

    public void setVcNivelAfectacion(String vcNivelAfectacion) {
        this.vcNivelAfectacion = vcNivelAfectacion;
    }

    public String getVcNivelAfectacion() {
        return vcNivelAfectacion;
    }

    public void setVcInfraccion(String vcInfraccion) {
        this.vcInfraccion = vcInfraccion;
    }

    public String getVcInfraccion() {
        return vcInfraccion;
    }

    public void setVcAnioResolucion(String vcAnioResolucion) {
        this.vcAnioResolucion = vcAnioResolucion;
    }

    public String getVcAnioResolucion() {
        return vcAnioResolucion;
    }
    
    public void setDtFechaInicio(Date dtFechaInicio) {
        this.dtFechaInicio = dtFechaInicio;
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

    public void setNuIdEstadoDocumento(int nuIdEstadoDocumento) {
        this.nuIdEstadoDocumento = nuIdEstadoDocumento;
    }

    public int getNuIdEstadoDocumento() {
        return nuIdEstadoDocumento;
    }

    public void setNuValorMatriz(double nuValorMatriz) {
        this.nuValorMatriz = nuValorMatriz;
    }

    public double getNuValorMatriz() {
        return nuValorMatriz;
    }

    public void setNuMultaBase(double nuMultaBase) {
        this.nuMultaBase = nuMultaBase;
    }

    public double getNuMultaBase() {
        return nuMultaBase;
    }

    public void setNuFactorDuracion(double nuFactorDuracion) {
        this.nuFactorDuracion = nuFactorDuracion;
    }

    public double getNuFactorDuracion() {
        return nuFactorDuracion;
    }

    public void setLstMetodos(Map<String, String> lstMetodos) {
      this.lstMetodos = lstMetodos;
    }

    public Map<String, String> getLstMetodos() {
        return lstMetodos;
    }

    /* public void setLstInfracciones(Map<String, String> lstInfracciones) {
        this.lstInfracciones = lstInfracciones;
    }

    public Map<String, String> getLstInfracciones() {
        return lstInfracciones;
    } */

    public void setLstInstancias(Map<String, String> lstInstancias) {
        this.lstInstancias = lstInstancias;
    }

    public Map<String, String> getLstInstancias() {
        return lstInstancias;
    }

    public void setLstAniosUIT(List<SelectItem> lstAniosUIT) {
        this.lstAniosUIT = lstAniosUIT;
    }

    public List<SelectItem> getLstAniosUIT() {
        return lstAniosUIT;
    }

    public void setLstTipoAfectacion(List<SelectItem> lstTipoAfectacion) {
        this.lstTipoAfectacion = lstTipoAfectacion;
    }

    public List<SelectItem> getLstTipoAfectacion() {
        return lstTipoAfectacion;
    }

    public void setVcIdNivelAfectacion(String vcIdNivelAfectacion) {
        this.vcIdNivelAfectacion = vcIdNivelAfectacion;
    }

    public String getVcIdNivelAfectacion() {
        return vcIdNivelAfectacion;
    }

    public void setVcF1Val(String vcF1Val) {
        this.vcF1Val = vcF1Val;
    }

    public String getVcF1Val() {
        return vcF1Val;
    }

    public void setVcF2Val(String vcF2Val) {
        this.vcF2Val = vcF2Val;
    }

    public String getVcF2Val() {
        return vcF2Val;
    }

    public void setVcF3Val(String vcF3Val) {
        this.vcF3Val = vcF3Val;
    }

    public String getVcF3Val() {
        return vcF3Val;
    }

    public void setVcF4Val(String vcF4Val) {
        this.vcF4Val = vcF4Val;
    }

    public String getVcF4Val() {
        return vcF4Val;
    }

    public void setVcF5Val(String vcF5Val) {
        this.vcF5Val = vcF5Val;
    }

    public String getVcF5Val() {
        return vcF5Val;
    }

    public void setVcF6Val(String vcF6Val) {
        this.vcF6Val = vcF6Val;
    }

    public String getVcF6Val() {
        return vcF6Val;
    }

    public void setVcF01Val(String vcF01Val) {
        this.vcF01Val = vcF01Val;
    }

    public String getVcF01Val() {
        return vcF01Val;
    }

    public void setVcF7Val(String vcF7Val) {
        this.vcF7Val = vcF7Val;
    }

    public String getVcF7Val() {
        return vcF7Val;
    }

    public void setVcF8Val(String vcF8Val) {
        this.vcF8Val = vcF8Val;
    }

    public String getVcF8Val() {
        return vcF8Val;
    }

    public void setVcF9Val(String vcF9Val) {
        this.vcF9Val = vcF9Val;
    }

    public String getVcF9Val() {
        return vcF9Val;
    }

    public void setVcF10Val(String vcF10Val) {
        this.vcF10Val = vcF10Val;
    }

    public String getVcF10Val() {
        return vcF10Val;
    }

    public void setVcF11Val(String vcF11Val) {
        this.vcF11Val = vcF11Val;
    }

    public String getVcF11Val() {
        return vcF11Val;
    }

    public void setNuValorIncrementoAA(double nuValorIncrementoAA) {
        this.nuValorIncrementoAA = nuValorIncrementoAA;
    }

    public double getNuValorIncrementoAA() {
        return nuValorIncrementoAA;
    }

    public void setNuValorDescuentoAA(double nuValorDescuentoAA) {
        this.nuValorDescuentoAA = nuValorDescuentoAA;
    }

    public double getNuValorDescuentoAA() {
        return nuValorDescuentoAA;
    }

    public void setNuValorIncrementoAATemp(double nuValorIncrementoAATemp) {
        this.nuValorIncrementoAATemp = nuValorIncrementoAATemp;
    }

    public double getNuValorIncrementoAATemp() {
        return nuValorIncrementoAATemp;
    }

    public void setNuValorDescuentoAATemp(double nuValorDescuentoAATemp) {
        this.nuValorDescuentoAATemp = nuValorDescuentoAATemp;
    }

    public double getNuValorDescuentoAATemp() {
        return nuValorDescuentoAATemp;
    }

    public void setNuValorFactorAA(double nuValorFactorAA) {
        this.nuValorFactorAA = nuValorFactorAA;
    }

    public double getNuValorFactorAA() {
        return nuValorFactorAA;
    }

    public void setNuAnioUIT(int nuAnioUIT) {
        this.nuAnioUIT = nuAnioUIT;
    }

    public int getNuAnioUIT() {
        return nuAnioUIT;
    }

    public void setNuMultaPreUIT(double nuMultaPreUIT) {
        this.nuMultaPreUIT = nuMultaPreUIT;
    }

    public double getNuMultaPreUIT() {
        return nuMultaPreUIT;
    }

    public void setVcGravedadMulta(String vcGravedadMulta) {
        this.vcGravedadMulta = vcGravedadMulta;
    }

    public String getVcGravedadMulta() {
        return vcGravedadMulta;
    }

    public void setNuGravedadTope(double nuGravedadTope) {
        this.nuGravedadTope = nuGravedadTope;
    }

    public double getNuGravedadTope() {
        return nuGravedadTope;
    }

    public void setNuIngresosTope(double nuIngresosTope) {
        this.nuIngresosTope = nuIngresosTope;
    }

    public double getNuIngresosTope() {
        return nuIngresosTope;
    }

    public void setNuMultaFinal(double nuMultaFinal) {
        this.nuMultaFinal = nuMultaFinal;
    }

    public double getNuMultaFinal() {
        return nuMultaFinal;
    }

    public void setNuMultaFinalSoles(double nuMultaFinalSoles) {
        this.nuMultaFinalSoles = nuMultaFinalSoles;
    }

    public double getNuMultaFinalSoles() {
        return nuMultaFinalSoles;
    }

    public void setVcF02Val(String vcF02Val) {
        this.vcF02Val = vcF02Val;
    }

    public String getVcF02Val() {
        return vcF02Val;
    }

    public void setVcF03Val(String vcF03Val) {
        this.vcF03Val = vcF03Val;
    }

    public String getVcF03Val() {
        return vcF03Val;
    }

    public void setVcF01Criterio(String vcF01Criterio) {
        this.vcF01Criterio = vcF01Criterio;
    }

    public String getVcF01Criterio() {
        return vcF01Criterio;
    }

    public void setVcF02Criterio(String vcF02Criterio) {
        this.vcF02Criterio = vcF02Criterio;
    }

    public String getVcF02Criterio() {
        return vcF02Criterio;
    }

    public void setVcF03Criterio(String vcF03Criterio) {
        this.vcF03Criterio = vcF03Criterio;
    }

    public String getVcF03Criterio() {
        return vcF03Criterio;
    }

    public void setVcF01DctoVal(String vcF01DctoVal) {
        this.vcF01DctoVal = vcF01DctoVal;
    }

    public String getVcF01DctoVal() {
        return vcF01DctoVal;
    }

    public void setVcF02DctoVal(String vcF02DctoVal) {
        this.vcF02DctoVal = vcF02DctoVal;
    }

    public String getVcF02DctoVal() {
        return vcF02DctoVal;
    }

    public void setVcF03DctoVal(String vcF03DctoVal) {
        this.vcF03DctoVal = vcF03DctoVal;
    }

    public String getVcF03DctoVal() {
        return vcF03DctoVal;
    }

    public void setVcF01DctoCriterio(String vcF01DctoCriterio) {
        this.vcF01DctoCriterio = vcF01DctoCriterio;
    }

    public String getVcF01DctoCriterio() {
        return vcF01DctoCriterio;
    }

    public void setVcF02DctoCriterio(String vcF02DctoCriterio) {
        this.vcF02DctoCriterio = vcF02DctoCriterio;
    }

    public String getVcF02DctoCriterio() {
        return vcF02DctoCriterio;
    }

    public void setVcF03DctoCriterio(String vcF03DctoCriterio) {
        this.vcF03DctoCriterio = vcF03DctoCriterio;
    }

    public String getVcF03DctoCriterio() {
        return vcF03DctoCriterio;
    }

    public void setVcClasificacion(String vcClasificacion) {
        this.vcClasificacion = vcClasificacion;
    }

    public String getVcClasificacion() {
        return vcClasificacion;
    }

    public void setVcRucLibro(String vcRucLibro) {
        this.vcRucLibro = vcRucLibro;
    }

    public String getVcRucLibro() {
        return vcRucLibro;
    }

    public void setVcAnioResolucionLibro(String vcAnioResolucionLibro) {
        this.vcAnioResolucionLibro = vcAnioResolucionLibro;
    }

    public String getVcAnioResolucionLibro() {
        return vcAnioResolucionLibro;
    }

    public void setVcInfraccionLibro(String vcInfraccionLibro) {
        this.vcInfraccionLibro = vcInfraccionLibro;
    }

    public String getVcInfraccionLibro() {
        return vcInfraccionLibro;
    }

    public void setVcTipoAfectacionLibro(String vcTipoAfectacionLibro) {
        this.vcTipoAfectacionLibro = vcTipoAfectacionLibro;
    }

    public String getVcTipoAfectacionLibro() {
        return vcTipoAfectacionLibro;
    }

    public void setNuMultaRefLibro(double nuMultaRefLibro) {
        this.nuMultaRefLibro = nuMultaRefLibro;
    }

    public double getNuMultaRefLibro() {
        return nuMultaRefLibro;
    }

    public void setNuFactorFci(double nuFactorFci) {
        this.nuFactorFci = nuFactorFci;
    }

    public double getNuFactorFci() {
        return nuFactorFci;
    }

    public void setNuMultaBaseLibro(double nuMultaBaseLibro) {
        this.nuMultaBaseLibro = nuMultaBaseLibro;
    }

    public double getNuMultaBaseLibro() {
        return nuMultaBaseLibro;
    }

    public void setNuValorUIT(double nuValorUIT) {
        this.nuValorUIT = nuValorUIT;
    }

    public double getNuValorUIT() {
        return nuValorUIT;
    }

    public void setNuPorcTope(double nuPorcTope) {
        this.nuPorcTope = nuPorcTope;
    }

    public double getNuPorcTope() {
        return nuPorcTope;
    }

    public void setNuPorcTopeLibro(double nuPorcTopeLibro) {
        this.nuPorcTopeLibro = nuPorcTopeLibro;
    }

    public double getNuPorcTopeLibro() {
        return nuPorcTopeLibro;
    }

    public void setNuMultaAALibro(double nuMultaAALibro) {
        this.nuMultaAALibro = nuMultaAALibro;
    }

    public double getNuMultaAALibro() {
        return nuMultaAALibro;
    }

    public void setNuAjusIngLibro(double nuAjusIngLibro) {
        this.nuAjusIngLibro = nuAjusIngLibro;
    }

    public double getNuAjusIngLibro() {
        return nuAjusIngLibro;
    }

    public void setVcIdTipoBarrera(String vcIdTipoBarrera) {
        this.vcIdTipoBarrera = vcIdTipoBarrera;
    }

    public String getVcIdTipoBarrera() {
        return vcIdTipoBarrera;
    }

    public void setVcTipoBarrera(String vcTipoBarrera) {
        this.vcTipoBarrera = vcTipoBarrera;
    }

    public String getVcTipoBarrera() {
        return vcTipoBarrera;
    }

    public void setLstTipoBarreras(Map<String, String> lstTipoBarreras) {
        this.lstTipoBarreras = lstTipoBarreras;
    }

    public Map<String, String> getLstTipoBarreras() {
        return lstTipoBarreras;
    }

    public void setVcAnioResolucionBarrera(String vcAnioResolucionBarrera) {
        this.vcAnioResolucionBarrera = vcAnioResolucionBarrera;
    }

    public String getVcAnioResolucionBarrera() {
        return vcAnioResolucionBarrera;
    }

    public void setVcInfraccionBarrera(String vcInfraccionBarrera) {
        this.vcInfraccionBarrera = vcInfraccionBarrera;
    }

    public String getVcInfraccionBarrera() {
        return vcInfraccionBarrera;
    }

    public void setVcTipoAfectacionBarrera(String vcTipoAfectacionBarrera) {
        this.vcTipoAfectacionBarrera = vcTipoAfectacionBarrera;
    }

    public String getVcTipoAfectacionBarrera() {
        return vcTipoAfectacionBarrera;
    }

    public void setVcClasificacionBarrera(String vcClasificacionBarrera) {
        this.vcClasificacionBarrera = vcClasificacionBarrera;
    }

    public String getVcClasificacionBarrera() {
        return vcClasificacionBarrera;
    }

    public void setNuDanioBaseBarrera(int nuDanioBaseBarrera) {
        this.nuDanioBaseBarrera = nuDanioBaseBarrera;
    }

    public int getNuDanioBaseBarrera() {
        return nuDanioBaseBarrera;
    }

    public void setLstProbabilidadBarreras(List<SelectItem> lstProbabilidadBarreras) {
        this.lstProbabilidadBarreras = lstProbabilidadBarreras;
    }

    public List<SelectItem> getLstProbabilidadBarreras() {
        return lstProbabilidadBarreras;
    }

    public void setVcDefinicionBarreras(String vcDefinicionBarreras) {
        this.vcDefinicionBarreras = vcDefinicionBarreras;
    }

    public String getVcDefinicionBarreras() {
        return vcDefinicionBarreras;
    }

    public void setVcCaso(String vcCaso) {
        this.vcCaso = vcCaso;
    }

    public String getVcCaso() {
        return vcCaso;
    }

    public void setLstTipoAlcanceBarreras(Map<String, String> lstTipoAlcanceBarreras) {
        this.lstTipoAlcanceBarreras = lstTipoAlcanceBarreras;
    }

    public Map<String, String> getLstTipoAlcanceBarreras() {
        return lstTipoAlcanceBarreras;
    }

    public void setLstFactorPoblacionBarreras(Map<String, String> lstFactorPoblacionBarreras) {
        this.lstFactorPoblacionBarreras = lstFactorPoblacionBarreras;
    }

    public Map<String, String> getLstFactorPoblacionBarreras() {
        return lstFactorPoblacionBarreras;
    }

    public void setLstFactorSecEconomicoBarreras(Map<String, String> lstFactorSecEconomicoBarreras) {
        this.lstFactorSecEconomicoBarreras = lstFactorSecEconomicoBarreras;
    }

    public Map<String, String> getLstFactorSecEconomicoBarreras() {
        return lstFactorSecEconomicoBarreras;
    }

    public void setVcIdTipoAlcanceBarrera(String vcIdTipoAlcanceBarrera) {
        this.vcIdTipoAlcanceBarrera = vcIdTipoAlcanceBarrera;
    }

    public String getVcIdTipoAlcanceBarrera() {
        return vcIdTipoAlcanceBarrera;
    }

    public void setVcTipoAlcanceBarrera(String vcTipoAlcanceBarrera) {
        this.vcTipoAlcanceBarrera = vcTipoAlcanceBarrera;
    }

    public String getVcTipoAlcanceBarrera() {
        return vcTipoAlcanceBarrera;
    }

    public void setVcIdFactPoblacionBarrera(String vcIdFactPoblacionBarrera) {
        this.vcIdFactPoblacionBarrera = vcIdFactPoblacionBarrera;
    }

    public String getVcIdFactPoblacionBarrera() {
        return vcIdFactPoblacionBarrera;
    }

    public void setVcFactPoblacionBarrera(String vcFactPoblacionBarrera) {
        this.vcFactPoblacionBarrera = vcFactPoblacionBarrera;
    }

    public String getVcFactPoblacionBarrera() {
        return vcFactPoblacionBarrera;
    }

    public void setVcIdFactSecEconBarrera(String vcIdFactSecEconBarrera) {
        this.vcIdFactSecEconBarrera = vcIdFactSecEconBarrera;
    }

    public String getVcIdFactSecEconBarrera() {
        return vcIdFactSecEconBarrera;
    }

    public void setVcFactSecEconBarrera(String vcFactSecEconBarrera) {
        this.vcFactSecEconBarrera = vcFactSecEconBarrera;
    }

    public String getVcFactSecEconBarrera() {
        return vcFactSecEconBarrera;
    }

    public void setNuMultaDBarrera(double nuMultaDBarrera) {
        this.nuMultaDBarrera = nuMultaDBarrera;
    }

    public double getNuMultaDBarrera() {
        return nuMultaDBarrera;
    }

    public void setNuFactorPBarrera(double nuFactorPBarrera) {
        this.nuFactorPBarrera = nuFactorPBarrera;
    }

    public double getNuFactorPBarrera() {
        return nuFactorPBarrera;
    }

    public void setNuMultaBaseBarrera(double nuMultaBaseBarrera) {
        this.nuMultaBaseBarrera = nuMultaBaseBarrera;
    }

    public double getNuMultaBaseBarrera() {
        return nuMultaBaseBarrera;
    }

    public void setNuMultaAABarrera(double nuMultaAABarrera) {
        this.nuMultaAABarrera = nuMultaAABarrera;
    }

    public double getNuMultaAABarrera() {
        return nuMultaAABarrera;
    }

    public void setNuMultaAjustadaBarrera(double nuMultaAjustadaBarrera) {
        this.nuMultaAjustadaBarrera = nuMultaAjustadaBarrera;
    }

    public double getNuMultaAjustadaBarrera() {
        return nuMultaAjustadaBarrera;
    }

    public void setNuAjusteIngBarrera(double nuAjusteIngBarrera) {
        this.nuAjusteIngBarrera = nuAjusteIngBarrera;
    }

    public double getNuAjusteIngBarrera() {
        return nuAjusteIngBarrera;
    }

    public void setNuAjusteMinBarrera(double nuAjusteMinBarrera) {
        this.nuAjusteMinBarrera = nuAjusteMinBarrera;
    }

    public double getNuAjusteMinBarrera() {
        return nuAjusteMinBarrera;
    }

    public void setNuAjusteMaxBarrera(double nuAjusteMaxBarrera) {
        this.nuAjusteMaxBarrera = nuAjusteMaxBarrera;
    }

    public double getNuAjusteMaxBarrera() {
        return nuAjusteMaxBarrera;
    }

    public void setLstOrganosMetodo(Map<String, String> lstOrganosMetodo) {
        this.lstOrganosMetodo = lstOrganosMetodo;
    }

    public Map<String, String> getLstOrganosMetodo() {
        return lstOrganosMetodo;
    }

    public void setVcRucPi(String vcRucPi) {
        this.vcRucPi = vcRucPi;
    }

    public String getVcRucPi() {
        return vcRucPi;
    }

    public void setVcAnioResolucionPi(String vcAnioResolucionPi) {
        this.vcAnioResolucionPi = vcAnioResolucionPi;
    }

    public String getVcAnioResolucionPi() {
        return vcAnioResolucionPi;
    }

    public void setVcOrgano1raInstancia(String vcOrgano1raInstancia) {
        this.vcOrgano1raInstancia = vcOrgano1raInstancia;
    }

    public String getVcOrgano1raInstancia() {
        return vcOrgano1raInstancia;
    }

    public void setVcInstanciaPi(String vcInstanciaPi) {
        this.vcInstanciaPi = vcInstanciaPi;
    }

    public String getVcInstanciaPi() {
        return vcInstanciaPi;
    }

    public void setVcInfraccionPi(String vcInfraccionPi) {
        this.vcInfraccionPi = vcInfraccionPi;
    }

    public String getVcInfraccionPi() {
        return vcInfraccionPi;
    }

    public void setVcTipoAfectacionPi(String vcTipoAfectacionPi) {
        this.vcTipoAfectacionPi = vcTipoAfectacionPi;
    }

    public String getVcTipoAfectacionPi() {
        return vcTipoAfectacionPi;
    }

    public void setVcIdNivelAfectacionPi(String vcIdNivelAfectacionPi) {
        this.vcIdNivelAfectacionPi = vcIdNivelAfectacionPi;
    }

    public String getVcIdNivelAfectacionPi() {
        return vcIdNivelAfectacionPi;
    }

    public void setVcNivelAfectacionPi(String vcNivelAfectacionPi) {
        this.vcNivelAfectacionPi = vcNivelAfectacionPi;
    }

    public String getVcNivelAfectacionPi() {
        return vcNivelAfectacionPi;
    }

    public void setNuMultaBasePi(double nuMultaBasePi) {
        this.nuMultaBasePi = nuMultaBasePi;
    }

    public double getNuMultaBasePi() {
        return nuMultaBasePi;
    }

    public void setNuSubTipoPi(int nuSubTipoPi) {
        this.nuSubTipoPi = nuSubTipoPi;
    }

    public int getNuSubTipoPi() {
        return nuSubTipoPi;
    }

    public void setNuMultaPreUITPi(double nuMultaPreUITPi) {
        this.nuMultaPreUITPi = nuMultaPreUITPi;
    }

    public double getNuMultaPreUITPi() {
        return nuMultaPreUITPi;
    }

    public void setNuGravedadTopePi(double nuGravedadTopePi) {
        this.nuGravedadTopePi = nuGravedadTopePi;
    }

    public double getNuGravedadTopePi() {
        return nuGravedadTopePi;
    }

    public void setLstGravedadTopePi(Map<String, String> lstGravedadTopePi) {
        this.lstGravedadTopePi = lstGravedadTopePi;
    }

    public Map<String, String> getLstGravedadTopePi() {
        return lstGravedadTopePi;
    }

    public void setVcInstanciaCcd(String vcInstanciaCcd) {
        this.vcInstanciaCcd = vcInstanciaCcd;
    }

    public String getVcInstanciaCcd() {
        return vcInstanciaCcd;
    }

    public void setVcRucCcd(String vcRucCcd) {
        this.vcRucCcd = vcRucCcd;
    }

    public String getVcRucCcd() {
        return vcRucCcd;
    }

    public void setVcAnioResolucionCcd(String vcAnioResolucionCcd) {
        this.vcAnioResolucionCcd = vcAnioResolucionCcd;
    }

    public String getVcAnioResolucionCcd() {
        return vcAnioResolucionCcd;
    }

    public void setVcInfraccionCcd(String vcInfraccionCcd) {
        this.vcInfraccionCcd = vcInfraccionCcd;
    }

    public String getVcInfraccionCcd() {
        return vcInfraccionCcd;
    }

    public void setVcOrgano1raInstanciaCcd(String vcOrgano1raInstanciaCcd) {
        this.vcOrgano1raInstanciaCcd = vcOrgano1raInstanciaCcd;
    }

    public String getVcOrgano1raInstanciaCcd() {
        return vcOrgano1raInstanciaCcd;
    }

    public void setVcTipoAfectacionCcd(String vcTipoAfectacionCcd) {
        this.vcTipoAfectacionCcd = vcTipoAfectacionCcd;
    }

    public String getVcTipoAfectacionCcd() {
        return vcTipoAfectacionCcd;
    }

    public void setVcIdNivelAfectacionCcd(String vcIdNivelAfectacionCcd) {
        this.vcIdNivelAfectacionCcd = vcIdNivelAfectacionCcd;
    }

    public String getVcIdNivelAfectacionCcd() {
        return vcIdNivelAfectacionCcd;
    }

    public void setVcNivelAfectacionCcd(String vcNivelAfectacionCcd) {
        this.vcNivelAfectacionCcd = vcNivelAfectacionCcd;
    }

    public String getVcNivelAfectacionCcd() {
        return vcNivelAfectacionCcd;
    }

    public void setNuMultaBaseCcd(double nuMultaBaseCcd) {
        this.nuMultaBaseCcd = nuMultaBaseCcd;
    }

    public double getNuMultaBaseCcd() {
        return nuMultaBaseCcd;
    }

    public void setNuMultaPreUITCcd(double nuMultaPreUITCcd) {
        this.nuMultaPreUITCcd = nuMultaPreUITCcd;
    }

    public double getNuMultaPreUITCcd() {
        return nuMultaPreUITCcd;
    }

    public void setNuGravedadTopeCcd(double nuGravedadTopeCcd) {
        this.nuGravedadTopeCcd = nuGravedadTopeCcd;
    }

    public double getNuGravedadTopeCcd() {
        return nuGravedadTopeCcd;
    }

    public void setDtFechaInicioCcd(Date dtFechaInicioCcd) {
        this.dtFechaInicioCcd = dtFechaInicioCcd;
    }

    public Date getDtFechaInicioCcd() {
        return dtFechaInicioCcd;
    }

    public void setDtFechaFinCcd(Date dtFechaFinCcd) {
        this.dtFechaFinCcd = dtFechaFinCcd;
    }

    public Date getDtFechaFinCcd() {
        return dtFechaFinCcd;
    }

    public void setNuFactorDuracionCcd(double nuFactorDuracionCcd) {
        this.nuFactorDuracionCcd = nuFactorDuracionCcd;
    }

    public double getNuFactorDuracionCcd() {
        return nuFactorDuracionCcd;
    }

    public void setVcGravedadMultaCcd(String vcGravedadMultaCcd) {
        this.vcGravedadMultaCcd = vcGravedadMultaCcd;
    }

    public String getVcGravedadMultaCcd() {
        return vcGravedadMultaCcd;
    }

    public void setNuIngresosTopeCcd(double nuIngresosTopeCcd) {
        this.nuIngresosTopeCcd = nuIngresosTopeCcd;
    }

    public double getNuIngresosTopeCcd() {
        return nuIngresosTopeCcd;
    }

    public void setNuPorcVtasCcd(double nuPorcVtasCcd) {
        this.nuPorcVtasCcd = nuPorcVtasCcd;
    }

    public double getNuPorcVtasCcd() {
        return nuPorcVtasCcd;
    }

    public void setNuFacturacionAnual(double nuFacturacionAnual) {
        this.nuFacturacionAnual = nuFacturacionAnual;
    }

    public double getNuFacturacionAnual() {
        return nuFacturacionAnual;
    }

    public void setNuFacturacionAnualLibro(double nuFacturacionAnualLibro) {
        this.nuFacturacionAnualLibro = nuFacturacionAnualLibro;
    }

    public double getNuFacturacionAnualLibro() {
        return nuFacturacionAnualLibro;
    }

    public void setNuFacturacionAnualBarrera(double nuFacturacionAnualBarrera) {
        this.nuFacturacionAnualBarrera = nuFacturacionAnualBarrera;
    }

    public double getNuFacturacionAnualBarrera() {
        return nuFacturacionAnualBarrera;
    }

    public void setNuFacturacionAnualPi(double nuFacturacionAnualPi) {
        this.nuFacturacionAnualPi = nuFacturacionAnualPi;
    }

    public double getNuFacturacionAnualPi() {
        return nuFacturacionAnualPi;
    }

    public void setNuFacturacionAnualCcd(double nuFacturacionAnualCcd) {
        this.nuFacturacionAnualCcd = nuFacturacionAnualCcd;
    }

    public double getNuFacturacionAnualCcd() {
        return nuFacturacionAnualCcd;
    }

    public void setNuFactorFco(double nuFactorFco) {
        this.nuFactorFco = nuFactorFco;
    }

    public double getNuFactorFco() {
        return nuFactorFco;
    }

    public void setNuFactorAo(double nuFactorAo) {
        this.nuFactorAo = nuFactorAo;
    }

    public double getNuFactorAo() {
        return nuFactorAo;
    }

    public void setNuFactorAi(double nuFactorAi) {
        this.nuFactorAi = nuFactorAi;
    }

    public double getNuFactorAi() {
        return nuFactorAi;
    }

    public void setNuAlcanceBarrera(double nuAlcanceBarrera) {
        this.nuAlcanceBarrera = nuAlcanceBarrera;
    }

    public double getNuAlcanceBarrera() {
        return nuAlcanceBarrera;
    }

    public void setVcRucBarreras(String vcRucBarreras) {
        this.vcRucBarreras = vcRucBarreras;
    }

    public String getVcRucBarreras() {
        return vcRucBarreras;
    }

    public void setLstServicioProductoAcreditadoFirma(Map<String, String> lstServicioProductoAcreditadoFirma) {
        this.lstServicioProductoAcreditadoFirma = lstServicioProductoAcreditadoFirma;
    }

    public Map<String, String> getLstServicioProductoAcreditadoFirma() {
        return lstServicioProductoAcreditadoFirma;
    }

    public void setVcRucFirma(String vcRucFirma) {
        this.vcRucFirma = vcRucFirma;
    }

    public String getVcRucFirma() {
        return vcRucFirma;
    }

    public void setVcIdTipoServicioFirma(String vcIdTipoServicioFirma) {
        this.vcIdTipoServicioFirma = vcIdTipoServicioFirma;
    }

    public String getVcIdTipoServicioFirma() {
        return vcIdTipoServicioFirma;
    }

    public void setVcTipoServicioFirma(String vcTipoServicioFirma) {
        this.vcTipoServicioFirma = vcTipoServicioFirma;
    }

    public String getVcTipoServicioFirma() {
        return vcTipoServicioFirma;
    }

    public void setNuFacturacionAnualFirma(double nuFacturacionAnualFirma) {
        this.nuFacturacionAnualFirma = nuFacturacionAnualFirma;
    }

    public double getNuFacturacionAnualFirma() {
        return nuFacturacionAnualFirma;
    }

    public void setVcAnioResolucionFirma(String vcAnioResolucionFirma) {
        this.vcAnioResolucionFirma = vcAnioResolucionFirma;
    }

    public String getVcAnioResolucionFirma() {
        return vcAnioResolucionFirma;
    }

    public void setVcInfraccionFirma(String vcInfraccionFirma) {
        this.vcInfraccionFirma = vcInfraccionFirma;
    }

    public String getVcInfraccionFirma() {
        return vcInfraccionFirma;
    }

    public void setVcTipoAfectacionFirma(String vcTipoAfectacionFirma) {
        this.vcTipoAfectacionFirma = vcTipoAfectacionFirma;
    }

    public String getVcTipoAfectacionFirma() {
        return vcTipoAfectacionFirma;
    }

    public void setNuDanioBaseFirma(int nuDanioBaseFirma) {
        this.nuDanioBaseFirma = nuDanioBaseFirma;
    }

    public int getNuDanioBaseFirma() {
        return nuDanioBaseFirma;
    }

    public void setVcIdNivelAfectacionFirma(String vcIdNivelAfectacionFirma) {
        this.vcIdNivelAfectacionFirma = vcIdNivelAfectacionFirma;
    }

    public String getVcIdNivelAfectacionFirma() {
        return vcIdNivelAfectacionFirma;
    }

    public void setVcNivelAfectacionFirma(String vcNivelAfectacionFirma) {
        this.vcNivelAfectacionFirma = vcNivelAfectacionFirma;
    }

    public String getVcNivelAfectacionFirma() {
        return vcNivelAfectacionFirma;
    }

    public void setNuFactorFirma(double nuFactorFirma) {
        this.nuFactorFirma = nuFactorFirma;
    }

    public double getNuFactorFirma() {
        return nuFactorFirma;
    }

    public void setNuMultaBaseFirma(double nuMultaBaseFirma) {
        this.nuMultaBaseFirma = nuMultaBaseFirma;
    }

    public double getNuMultaBaseFirma() {
        return nuMultaBaseFirma;
    }

    public void setNuFactorPFirma(int nuFactorPFirma) {
        this.nuFactorPFirma = nuFactorPFirma;
    }

    public int getNuFactorPFirma() {
        return nuFactorPFirma;
    }

    public void setNuMultaAAFirma(double nuMultaAAFirma) {
        this.nuMultaAAFirma = nuMultaAAFirma;
    }

    public double getNuMultaAAFirma() {
        return nuMultaAAFirma;
    }

    public void setNuTopeLegalFirma(double nuTopeLegalFirma) {
        this.nuTopeLegalFirma = nuTopeLegalFirma;
    }

    public double getNuTopeLegalFirma() {
        return nuTopeLegalFirma;
    }

    public void setVcRucVentas(String vcRucVentas) {
        this.vcRucVentas = vcRucVentas;
    }

    public String getVcRucVentas() {
        return vcRucVentas;
    }

    public void setNuFacturacionAnualVentas(double nuFacturacionAnualVentas) {
        this.nuFacturacionAnualVentas = nuFacturacionAnualVentas;
    }

    public double getNuFacturacionAnualVentas() {
        return nuFacturacionAnualVentas;
    }

    public void setNuFacturacionAnualProductoVentas(double nuFacturacionAnualProductoVentas) {
        this.nuFacturacionAnualProductoVentas = nuFacturacionAnualProductoVentas;
    }

    public double getNuFacturacionAnualProductoVentas() {
        return nuFacturacionAnualProductoVentas;
    }

    public void setVcAnioResolucionVentas(String vcAnioResolucionVentas) {
        this.vcAnioResolucionVentas = vcAnioResolucionVentas;
    }

    public String getVcAnioResolucionVentas() {
        return vcAnioResolucionVentas;
    }

    public void setVcInfraccionVentas(String vcInfraccionVentas) {
        this.vcInfraccionVentas = vcInfraccionVentas;
    }

    public String getVcInfraccionVentas() {
        return vcInfraccionVentas;
    }

    public void setVcTipoAfectacionVentas(String vcTipoAfectacionVentas) {
        this.vcTipoAfectacionVentas = vcTipoAfectacionVentas;
    }

    public String getVcTipoAfectacionVentas() {
        return vcTipoAfectacionVentas;
    }

    public void setVcIdNivelAfectacionVentas(String vcIdNivelAfectacionVentas) {
        this.vcIdNivelAfectacionVentas = vcIdNivelAfectacionVentas;
    }

    public String getVcIdNivelAfectacionVentas() {
        return vcIdNivelAfectacionVentas;
    }

    public void setVcNivelAfectacionVentas(String vcNivelAfectacionVentas) {
        this.vcNivelAfectacionVentas = vcNivelAfectacionVentas;
    }

    public String getVcNivelAfectacionVentas() {
        return vcNivelAfectacionVentas;
    }

    public void setNuFactorAVentas(double nuFactorAVentas) {
        this.nuFactorAVentas = nuFactorAVentas;
    }

    public double getNuFactorAVentas() {
        return nuFactorAVentas;
    }

    public void setNuFactorGVentas(double nuFactorGVentas) {
        this.nuFactorGVentas = nuFactorGVentas;
    }

    public double getNuFactorGVentas() {
        return nuFactorGVentas;
    }

    public void setNuMultaBaseVentas(double nuMultaBaseVentas) {
        this.nuMultaBaseVentas = nuMultaBaseVentas;
    }

    public double getNuMultaBaseVentas() {
        return nuMultaBaseVentas;
    }

    public void setNuMultaBaseUITVentas(double nuMultaBaseUITVentas) {
        this.nuMultaBaseUITVentas = nuMultaBaseUITVentas;
    }

    public double getNuMultaBaseUITVentas() {
        return nuMultaBaseUITVentas;
    }

    public void setNuMultaAAVentas(double nuMultaAAVentas) {
        this.nuMultaAAVentas = nuMultaAAVentas;
    }

    public double getNuMultaAAVentas() {
        return nuMultaAAVentas;
    }

    public void setNuMultaPreUITVentas(double nuMultaPreUITVentas) {
        this.nuMultaPreUITVentas = nuMultaPreUITVentas;
    }

    public double getNuMultaPreUITVentas() {
        return nuMultaPreUITVentas;
    }

    public void setNuIdGravedadVentas(int nuIdGravedadVentas) {
        this.nuIdGravedadVentas = nuIdGravedadVentas;
    }

    public int getNuIdGravedadVentas() {
        return nuIdGravedadVentas;
    }

    public void setNuGravedadTopeVentas(double nuGravedadTopeVentas) {
        this.nuGravedadTopeVentas = nuGravedadTopeVentas;
    }

    public double getNuGravedadTopeVentas() {
        return nuGravedadTopeVentas;
    }

    public void setNuGravedadPorcTopeVentas(double nuGravedadPorcTopeVentas) {
        this.nuGravedadPorcTopeVentas = nuGravedadPorcTopeVentas;
    }

    public double getNuGravedadPorcTopeVentas() {
        return nuGravedadPorcTopeVentas;
    }

    public void setNuAjusteIngVentas(double nuAjusteIngVentas) {
        this.nuAjusteIngVentas = nuAjusteIngVentas;
    }

    public double getNuAjusteIngVentas() {
        return nuAjusteIngVentas;
    }

    public void setVcGravedadTopeVentas(String vcGravedadTopeVentas) {
        this.vcGravedadTopeVentas = vcGravedadTopeVentas;
    }

    public String getVcGravedadTopeVentas() {
        return vcGravedadTopeVentas;
    }

    public void setNuUmbralFactorAVentas(double nuUmbralFactorAVentas) {
        this.nuUmbralFactorAVentas = nuUmbralFactorAVentas;
    }

    public double getNuUmbralFactorAVentas() {
        return nuUmbralFactorAVentas;
    }

    public void setVcRucAdhoc(String vcRucAdhoc) {
        this.vcRucAdhoc = vcRucAdhoc;
    }

    public String getVcRucAdhoc() {
        return vcRucAdhoc;
    }

    public void setNuFacturacionAnualAdhoc(double nuFacturacionAnualAdhoc) {
        this.nuFacturacionAnualAdhoc = nuFacturacionAnualAdhoc;
    }

    public double getNuFacturacionAnualAdhoc() {
        return nuFacturacionAnualAdhoc;
    }

    public void setVcAnioResolucionAdhoc(String vcAnioResolucionAdhoc) {
        this.vcAnioResolucionAdhoc = vcAnioResolucionAdhoc;
    }

    public String getVcAnioResolucionAdhoc() {
        return vcAnioResolucionAdhoc;
    }

    public void setVcInfraccionAdhoc(String vcInfraccionAdhoc) {
        this.vcInfraccionAdhoc = vcInfraccionAdhoc;
    }

    public String getVcInfraccionAdhoc() {
        return vcInfraccionAdhoc;
    }

    public void setVcTipoAfectacionAdhoc(String vcTipoAfectacionAdhoc) {
        this.vcTipoAfectacionAdhoc = vcTipoAfectacionAdhoc;
    }

    public String getVcTipoAfectacionAdhoc() {
        return vcTipoAfectacionAdhoc;
    }

    public void setVcIdNivelAfectacionAdhoc(String vcIdNivelAfectacionAdhoc) {
        this.vcIdNivelAfectacionAdhoc = vcIdNivelAfectacionAdhoc;
    }

    public String getVcIdNivelAfectacionAdhoc() {
        return vcIdNivelAfectacionAdhoc;
    }

    public void setVcNivelAfectacionAdhoc(String vcNivelAfectacionAdhoc) {
        this.vcNivelAfectacionAdhoc = vcNivelAfectacionAdhoc;
    }

    public String getVcNivelAfectacionAdhoc() {
        return vcNivelAfectacionAdhoc;
    }

    public void setNuFactorBAdhoc(double nuFactorBAdhoc) {
        this.nuFactorBAdhoc = nuFactorBAdhoc;
    }

    public double getNuFactorBAdhoc() {
        return nuFactorBAdhoc;
    }

    public void setNuFactorPAdhoc(double nuFactorPAdhoc) {
        this.nuFactorPAdhoc = nuFactorPAdhoc;
    }

    public double getNuFactorPAdhoc() {
        return nuFactorPAdhoc;
    }

    public void setNuMultaBaseAdhoc(double nuMultaBaseAdhoc) {
        this.nuMultaBaseAdhoc = nuMultaBaseAdhoc;
    }

    public double getNuMultaBaseAdhoc() {
        return nuMultaBaseAdhoc;
    }

    public void setNuMultaBaseUITAdhoc(double nuMultaBaseUITAdhoc) {
        this.nuMultaBaseUITAdhoc = nuMultaBaseUITAdhoc;
    }

    public double getNuMultaBaseUITAdhoc() {
        return nuMultaBaseUITAdhoc;
    }

    public void setNuMultaAAAdhoc(double nuMultaAAAdhoc) {
        this.nuMultaAAAdhoc = nuMultaAAAdhoc;
    }

    public double getNuMultaAAAdhoc() {
        return nuMultaAAAdhoc;
    }

    public void setNuMultaPreUITAdhoc(double nuMultaPreUITAdhoc) {
        this.nuMultaPreUITAdhoc = nuMultaPreUITAdhoc;
    }

    public double getNuMultaPreUITAdhoc() {
        return nuMultaPreUITAdhoc;
    }

    public void setNuIdGravedadAdhoc(int nuIdGravedadAdhoc) {
        this.nuIdGravedadAdhoc = nuIdGravedadAdhoc;
    }

    public int getNuIdGravedadAdhoc() {
        return nuIdGravedadAdhoc;
    }

    public void setNuGravedadTopeAdhoc(double nuGravedadTopeAdhoc) {
        this.nuGravedadTopeAdhoc = nuGravedadTopeAdhoc;
    }

    public double getNuGravedadTopeAdhoc() {
        return nuGravedadTopeAdhoc;
    }

    public void setVcGravedadTopeAdhoc(String vcGravedadTopeAdhoc) {
        this.vcGravedadTopeAdhoc = vcGravedadTopeAdhoc;
    }

    public String getVcGravedadTopeAdhoc() {
        return vcGravedadTopeAdhoc;
    }

    public void setNuGravedadPorcTopeAdhoc(double nuGravedadPorcTopeAdhoc) {
        this.nuGravedadPorcTopeAdhoc = nuGravedadPorcTopeAdhoc;
    }

    public double getNuGravedadPorcTopeAdhoc() {
        return nuGravedadPorcTopeAdhoc;
    }

    public void setNuAjusteIngAdhoc(double nuAjusteIngAdhoc) {
        this.nuAjusteIngAdhoc = nuAjusteIngAdhoc;
    }

    public double getNuAjusteIngAdhoc() {
        return nuAjusteIngAdhoc;
    }

    public void setVcCategoriaAdhoc(String vcCategoriaAdhoc) {
        this.vcCategoriaAdhoc = vcCategoriaAdhoc;
    }

    public String getVcCategoriaAdhoc() {
        return vcCategoriaAdhoc;
    }

    public void setLstTamEmpresaAdhoc(Map<String, String> lstTamEmpresaAdhoc) {
        this.lstTamEmpresaAdhoc = lstTamEmpresaAdhoc;
    }

    public Map<String, String> getLstTamEmpresaAdhoc() {
        return lstTamEmpresaAdhoc;
    }

    public void setLstGravedadTopeAdhoc(Map<String, String> lstGravedadTopeAdhoc) {
        this.lstGravedadTopeAdhoc = lstGravedadTopeAdhoc;
    }

    public Map<String, String> getLstGravedadTopeAdhoc() {
        return lstGravedadTopeAdhoc;
    }

    /*public void setVcIdTamEmpresa(int vcIdTamEmpresa) {
        this.vcIdTamEmpresa = vcIdTamEmpresa;
    }

    public int getVcIdTamEmpresa() {
        return vcIdTamEmpresa;
    }*/

    public void setVcInstanciaVentas(String vcInstanciaVentas) {
        this.vcInstanciaVentas = vcInstanciaVentas;
    }

    public String getVcInstanciaVentas() {
        return vcInstanciaVentas;
    }

    public void setVcOrgano1raInstanciaVentas(String vcOrgano1raInstanciaVentas) {
        this.vcOrgano1raInstanciaVentas = vcOrgano1raInstanciaVentas;
    }

    public String getVcOrgano1raInstanciaVentas() {
        return vcOrgano1raInstanciaVentas;
    }

    public void setVcInstanciaAdhoc(String vcInstanciaAdhoc) {
        this.vcInstanciaAdhoc = vcInstanciaAdhoc;
    }

    public String getVcInstanciaAdhoc() {
        return vcInstanciaAdhoc;
    }

    public void setVcOrgano1raInstanciaAdhoc(String vcOrgano1raInstanciaAdhoc) {
        this.vcOrgano1raInstanciaAdhoc = vcOrgano1raInstanciaAdhoc;
    }

    public String getVcOrgano1raInstanciaAdhoc() {
        return vcOrgano1raInstanciaAdhoc;
    }

    public void setVcFactorPAdhoc(String vcFactorPAdhoc) {
        this.vcFactorPAdhoc = vcFactorPAdhoc;
    }

    public String getVcFactorPAdhoc() {
        return vcFactorPAdhoc;
    }

    public void setNuMultaBaseCompleto(double nuMultaBaseCompleto) {
        this.nuMultaBaseCompleto = nuMultaBaseCompleto;
    }

    public double getNuMultaBaseCompleto() {
        return nuMultaBaseCompleto;
    }

    public void setNuMultaBaseLibroCompleto(double nuMultaBaseLibroCompleto) {
        this.nuMultaBaseLibroCompleto = nuMultaBaseLibroCompleto;
    }

    public double getNuMultaBaseLibroCompleto() {
        return nuMultaBaseLibroCompleto;
    }

    public void setNuMultaBaseBarreraCompleto(double nuMultaBaseBarreraCompleto) {
        this.nuMultaBaseBarreraCompleto = nuMultaBaseBarreraCompleto;
    }

    public double getNuMultaBaseBarreraCompleto() {
        return nuMultaBaseBarreraCompleto;
    }

    public void setNuMultaBasePiCompleto(double nuMultaBasePiCompleto) {
        this.nuMultaBasePiCompleto = nuMultaBasePiCompleto;
    }

    public double getNuMultaBasePiCompleto() {
        return nuMultaBasePiCompleto;
    }

    public void setNuMultaBaseCcdCompleto(double nuMultaBaseCcdCompleto) {
        this.nuMultaBaseCcdCompleto = nuMultaBaseCcdCompleto;
    }

    public double getNuMultaBaseCcdCompleto() {
        return nuMultaBaseCcdCompleto;
    }

    public void setNuMultaBaseFirmaCompleto(double nuMultaBaseFirmaCompleto) {
        this.nuMultaBaseFirmaCompleto = nuMultaBaseFirmaCompleto;
    }

    public double getNuMultaBaseFirmaCompleto() {
        return nuMultaBaseFirmaCompleto;
    }

    public void setNuMultaBaseVentasCompleto(double nuMultaBaseVentasCompleto) {
        this.nuMultaBaseVentasCompleto = nuMultaBaseVentasCompleto;
    }

    public double getNuMultaBaseVentasCompleto() {
        return nuMultaBaseVentasCompleto;
    }

    public void setNuMultaBaseAdhocCompleto(double nuMultaBaseAdhocCompleto) {
        this.nuMultaBaseAdhocCompleto = nuMultaBaseAdhocCompleto;
    }

    public double getNuMultaBaseAdhocCompleto() {
        return nuMultaBaseAdhocCompleto;
    }

    public void setNuValorSecEconomico(double nuValorSecEconomico) {
        this.nuValorSecEconomico = nuValorSecEconomico;
    }

    public double getNuValorSecEconomico() {
        return nuValorSecEconomico;
    }

    public void setIsBlMultaBase(boolean isBlMultaBase) {
        this.isBlMultaBase = isBlMultaBase;
    }

    public boolean isIsBlMultaBase() {
        return isBlMultaBase;
    }

    public void setIsBlMultaBaseLibro(boolean isBlMultaBaseLibro) {
        this.isBlMultaBaseLibro = isBlMultaBaseLibro;
    }

    public boolean isIsBlMultaBaseLibro() {
        return isBlMultaBaseLibro;
    }

    public void setIsBlMultaBasePi(boolean isBlMultaBasePi) {
        this.isBlMultaBasePi = isBlMultaBasePi;
    }

    public boolean isIsBlMultaBasePi() {
        return isBlMultaBasePi;
    }

    public void setIsBlMultaBaseBarrera(boolean isBlMultaBaseBarrera) {
        this.isBlMultaBaseBarrera = isBlMultaBaseBarrera;
    }

    public boolean isIsBlMultaBaseBarrera() {
        return isBlMultaBaseBarrera;
    }

    public void setIsBlMultaBaseCcd(boolean isBlMultaBaseCcd) {
        this.isBlMultaBaseCcd = isBlMultaBaseCcd;
    }

    public boolean isIsBlMultaBaseCcd() {
        return isBlMultaBaseCcd;
    }

    public void setIsBlMultaBaseVentas(boolean isBlMultaBaseVentas) {
        this.isBlMultaBaseVentas = isBlMultaBaseVentas;
    }

    public boolean isIsBlMultaBaseVentas() {
        return isBlMultaBaseVentas;
    }

    public void setIsBlMultaBaseAdhoc(boolean isBlMultaBaseAdhoc) {
        this.isBlMultaBaseAdhoc = isBlMultaBaseAdhoc;
    }

    public boolean isIsBlMultaBaseAdhoc() {
        return isBlMultaBaseAdhoc;
    }

    public void setIsBlMultaBaseFirma(boolean isBlMultaBaseFirma) {
        this.isBlMultaBaseFirma = isBlMultaBaseFirma;
    }

    public boolean isIsBlMultaBaseFirma() {
        return isBlMultaBaseFirma;
    }

    public void setNuMultaBaseUITVentasCompleto(double nuMultaBaseUITVentasCompleto) {
        this.nuMultaBaseUITVentasCompleto = nuMultaBaseUITVentasCompleto;
    }

    public double getNuMultaBaseUITVentasCompleto() {
        return nuMultaBaseUITVentasCompleto;
    }

    public void setNuMultaBaseUITAdhocCompleto(double nuMultaBaseUITAdhocCompleto) {
        this.nuMultaBaseUITAdhocCompleto = nuMultaBaseUITAdhocCompleto;
    }

    public double getNuMultaBaseUITAdhocCompleto() {
        return nuMultaBaseUITAdhocCompleto;
    }

    public void setLstMetodosCalculo(List<SelectItem> lstMetodosCalculo) {
        this.lstMetodosCalculo = lstMetodosCalculo;
    }

    public List<SelectItem> getLstMetodosCalculo() {
        return lstMetodosCalculo;
    }

    public void setLstInstanciasCalculo(List<SelectItem> lstInstanciasCalculo) {
        this.lstInstanciasCalculo = lstInstanciasCalculo;
    }

    public List<SelectItem> getLstInstanciasCalculo() {
        return lstInstanciasCalculo;
    }

    public void setNuIdTamEmpresa(int nuIdTamEmpresa) {
        this.nuIdTamEmpresa = nuIdTamEmpresa;
    }

    public int getNuIdTamEmpresa() {
        return nuIdTamEmpresa;
    }
    
    public void setIsBlLstAfectacion(boolean isBlLstAfectacion) {
        this.isBlLstAfectacion = isBlLstAfectacion;
    }

    public boolean isIsBlLstAfectacion() {
        return isBlLstAfectacion;
    }

    public void setNuFactorPDifAdhoc(double nuFactorPDifAdhoc) {
        this.nuFactorPDifAdhoc = nuFactorPDifAdhoc;
    }

    public double getNuFactorPDifAdhoc() {
        return nuFactorPDifAdhoc;
    }

    public void setNuUmbralFactorPDifAdhoc(double nuUmbralFactorPDifAdhoc) {
        this.nuUmbralFactorPDifAdhoc = nuUmbralFactorPDifAdhoc;
    }

    public double getNuUmbralFactorPDifAdhoc() {
        return nuUmbralFactorPDifAdhoc;
    }

    public void setVcFactorPDifAccion(String vcFactorPDifAccion) {
        this.vcFactorPDifAccion = vcFactorPDifAccion;
    }

    public String getVcFactorPDifAccion() {
        return vcFactorPDifAccion;
    }

    public void setNuFactorGDifVentas(double nuFactorGDifVentas) {
        this.nuFactorGDifVentas = nuFactorGDifVentas;
    }

    public double getNuFactorGDifVentas() {
        return nuFactorGDifVentas;
    }

    public void setNuUmbralFactorGDifVentas(double nuUmbralFactorGDifVentas) {
        this.nuUmbralFactorGDifVentas = nuUmbralFactorGDifVentas;
    }

    public double getNuUmbralFactorGDifVentas() {
        return nuUmbralFactorGDifVentas;
    }

    public void setVcFactorGDifAccion(String vcFactorGDifAccion) {
        this.vcFactorGDifAccion = vcFactorGDifAccion;
    }

    public String getVcFactorGDifAccion() {
        return vcFactorGDifAccion;
    }

    public void setLstTamanoEmpresa(List<SelectItem> lstTamanoEmpresa) {
        this.lstTamanoEmpresa = lstTamanoEmpresa;
    }

    public List<SelectItem> getLstTamanoEmpresa() {
        return lstTamanoEmpresa;
    }

    public void setVcTamEmpresaSeleccion(String vcTamEmpresaSeleccion) {
        this.vcTamEmpresaSeleccion = vcTamEmpresaSeleccion;
    }

    public String getVcTamEmpresaSeleccion() {
        return vcTamEmpresaSeleccion;
    }

    public void setNuMinUIT(double nuMinUIT) {
        this.nuMinUIT = nuMinUIT;
    }

    public double getNuMinUIT() {
        return nuMinUIT;
    }

    public void setNuMaxUIT(double nuMaxUIT) {
        this.nuMaxUIT = nuMaxUIT;
    }

    public double getNuMaxUIT() {
        return nuMaxUIT;
    }

    public void setIsBlCheckUIT(boolean isBlCheckUIT) {
        this.isBlCheckUIT = isBlCheckUIT;
    }

    public boolean isIsBlCheckUIT() {
        return isBlCheckUIT;
    }

    public void setIsBlCheckUITCcd(boolean isBlCheckUITCcd) {
        this.isBlCheckUITCcd = isBlCheckUITCcd;
    }

    public boolean isIsBlCheckUITCcd() {
        return isBlCheckUITCcd;
    }

    public void setVcTamEmpresaSeleccionCcd(String vcTamEmpresaSeleccionCcd) {
        this.vcTamEmpresaSeleccionCcd = vcTamEmpresaSeleccionCcd;
    }

    public String getVcTamEmpresaSeleccionCcd() {
        return vcTamEmpresaSeleccionCcd;
    }
}
