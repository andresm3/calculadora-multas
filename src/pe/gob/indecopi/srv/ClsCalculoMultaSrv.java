package pe.gob.indecopi.srv;

import pe.gob.indecopi.bean.ClsConstantesBean;
import indecopi.gob.pe.bean.ClsUsuarioIndBean;
import pe.gob.indecopi.util.ClsProperties;
import pe.gob.indecopi.util.ClsConstantes;
import indecopi.gob.pe.utils.ClsResultDAO;

import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.dao.ClsCalculoMultaIDAO;
import pe.gob.indecopi.dao.ClsCalculoMultaDAO;
import pe.gob.indecopi.dao.ClsUsuarioDAO;

import pe.gob.indecopi.bean.ClsPermisoUsuarioBean;
import pe.gob.indecopi.bean.ClsMetodoCalculoBean;
import pe.gob.indecopi.bean.ClsInstanciasBean;
import pe.gob.indecopi.bean.ClsTipoAfectacionBean;
import pe.gob.indecopi.bean.ClsCalculoBean;
import pe.gob.indecopi.bean.ClsMultaUITAnioBean;
import pe.gob.indecopi.bean.ClsTamanioEmpresaBean;
import pe.gob.indecopi.bean.ClsProbabilidadBarrerasBean;

import java.io.File;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;
import pe.gob.indecopi.util.ClsUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.context.ExternalContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pe.gob.indecopi.dao.ClsUsuarioIDAO;

public class ClsCalculoMultaSrv {
  static Logger logger = Logger.getLogger(ClsCalculoMultaSrv.class);

  private String vcMuestraInicio;
  private String vcMuestraFormPrincipal;

  private String vcMuestraPreestablecido;
  private String vcMuestraLibro;
  private String vcMuestraBarreras;
    private String vcMuestraFacSecEconBarreras;
  private String vcMuestraPreestablecidoPi;
    private String vcMuestraOrgano1raInstanciaPi;
  private String vcMuestraPreestablecidoCcd;
    private String vcMuestraOrgano1raInstanciaCcd;
  private String vcMuestraPreestablecidoFirma;
  private String vcMuestraPorcentajeVentas;
    private String vcMuestraOrgano1raInstanciaVentas;
    private String vcMuestraCLCVentas;
  private String vcMuestraAdHoc;
    private String vcMuestraOrgano1raInstanciaAdhoc;
    private String vcMuestraTamEmpresaAdHoc;
    private String vcMuestraGravedad1AdHoc;//output text
    private String vcMuestraGravedad2AdHoc;//cb
    private String vcMuestraTopelegalNoPIAdHoc;//cb
    private String vcMuestraFactorPVariacion;
  private String vcMuestraAtenuanteF8;
  private String vcMuestraAtenuanteF10;
  private String vcMuestraAtenuanteF7;
  
    private String vcMuestraFactorGVariacion;

  private String vcError;
  private String vcMensaje;

  private ClsUsuarioIndBean objUsuario;
  private ClsUsuarioBean objUsuarioBean;
  private ClsPermisoUsuarioBean objPermiso;

  private ClsResultDAO objResultDAO;
  
  private ClsCalculoBean objFiltroBean;

  private List<ClsMetodoCalculoBean> lstMetodoCalculoBean;
  private List<ClsInstanciasBean> lstInstanciaBean;
  
  private List<ClsMultaUITAnioBean> lstMultaUITAnioBean;
  private List<ClsTamanioEmpresaBean> lstTamanoEmpresaBean;
  private List<ClsTipoAfectacionBean> lstAfectacionBean;

  private List<SelectItem> lstOrgano;
  private String vcAreaSeleccion;

  private Map<String, String> lstAreas;

  private Map<String, String> lstAgravF1;
  private Map<String, String> lstAgravF2;
  private Map<String, String> lstAgravF3;
  private Map<String, String> lstAgravF4;
  private Map<String, String> lstAgravF5;
  private Map<String, String> lstAgravF6;
  private List<SelectItem> lstAgravF01;
  private List<SelectItem> lstAgravF02;
  private List<SelectItem> lstAgravF03;


  private Map<String, String> lstAtenF7;
  private Map<String, String> lstAtenF8;
  private Map<String, String> lstAtenF9;
  private Map<String, String> lstAtenF10;
  private Map<String, String> lstAtenF11;
  private List<SelectItem> lstAtenF01;
  private List<SelectItem> lstAtenF02;
  private List<SelectItem> lstAtenF03;  
  
  private int nuIdTamEmpresa;
  
  private static DecimalFormat df = new DecimalFormat("0.00");

  private List<ClsProbabilidadBarrerasBean> lstProbabilidadBarrerasBean;

  private int nuIDTipoAplicacion;


    private String vcTituloModalInfraccion;

  public ClsCalculoMultaSrv() {
  }

/*   public ClsCalculoMultaSrv(ClsUsuarioIndBean objUsuario, String vcOrgResolutivo) {//, ClsUsuarioBean objUsuarioBean, ClsPermisoUsuarioBean objPermisos) {
    //this.setObjPermiso(objPermisos);
    inicializar(objUsuario, vcOrgResolutivo);
  } */

  public ClsCalculoMultaSrv(ClsUsuarioIndBean objUsuario, ClsUsuarioBean objUsuarioBean){//, ClsPermisoUsuarioBean objPermisos) {
    logger.info(">>ClsCalculoMultaSrv");
    //this.setObjPermiso(objPermisos);
    this.setObjUsuarioBean(objUsuarioBean);
    logger.debug("objUsuarioBean.getVcArea(): " + objUsuarioBean.getVcArea());
    /* logger.debug("this.getObjUsuarioBean().getVcNombre(): " + this.getObjUsuarioBean().getVcNombre());
    logger.debug("objUsuarioBean.getVcArea(): " + objUsuarioBean.getVcArea());
    logger.debug("this.getObjUsuarioBean().getNuIdPerfil(): " + this.getObjUsuarioBean().getNuIdPerfil());
    logger.debug("this.getObjUsuarioBean().getNuIdRRHH(): " + this.getObjUsuarioBean().getNuIdRRHH()); */
    //inicializar(objUsuario, objUsuarioBean.getVcArea());
    //inicializaAreas();

    objFiltroBean = new ClsCalculoBean();

    SelectItem data = new SelectItem();
    List<SelectItem> lstMetodoCalc = new ArrayList<SelectItem>();
    data.setValue(-1);
    data.setLabel("Seleccionar Area");
    lstMetodoCalc.add(data);
    this.getObjFiltroBean().setLstMetodosCalculo(lstMetodoCalc);
    this.getObjFiltroBean().setLstInstanciasCalculo(lstMetodoCalc);


    this.lstMetodoCalculoBean = new ArrayList<ClsMetodoCalculoBean>();
    this.lstInstanciaBean = new ArrayList<ClsInstanciasBean>();
    this.lstMultaUITAnioBean = new ArrayList<ClsMultaUITAnioBean>();
    this.lstAfectacionBean = new ArrayList<ClsTipoAfectacionBean>();
    this.lstProbabilidadBarrerasBean = new ArrayList<ClsProbabilidadBarrerasBean>();

    this.lstAreas= new LinkedHashMap<String, String>();
    this.lstAgravF1= new LinkedHashMap<String, String>();
    this.lstAgravF2= new LinkedHashMap<String, String>();
    this.lstAgravF3= new LinkedHashMap<String, String>();
    this.lstAgravF4= new LinkedHashMap<String, String>();
    this.lstAgravF5= new LinkedHashMap<String, String>();
    this.lstAgravF6= new LinkedHashMap<String, String>();
    this.lstAtenF7= new LinkedHashMap<String, String>();
    this.lstAtenF8= new LinkedHashMap<String, String>();
    this.lstAtenF9= new LinkedHashMap<String, String>();
    this.lstAtenF10= new LinkedHashMap<String, String>();
    this.lstAtenF11= new LinkedHashMap<String, String>();

    this.doListarAgravF01();
    this.doListarAgravF02();
    this.doListarAgravF03();
    this.doListarAtenF01();
    this.doListarAtenF02();
    this.doListarAtenF03();


    this.doListarAreas();
    this.setVcMuestraInicio(" block;");
    this.setVcMuestraFormPrincipal(" none;");
    logger.info(">>FIN ClsCalculoMultaSrv");
  }

  public void doVolverInicio() {
    logger.info(">>doVolverInicio");

    this.doLimpiar();
    this.getObjFiltroBean().setVcMetodo("-1");
    this.setVcMuestraInicio(" block;");
    this.setVcMuestraFormPrincipal(" none;");
    logger.info(">>FIN doVolverInicio");
  }

  public void doIniciar() {
    logger.info(">>doIniciar");
    //inicializar(objUsuario, objUsuarioBean.getVcArea());
    try{
      /* FacesContext context = FacesContext.getCurrentInstance();
      HttpSession s = (HttpSession) context.getExternalContext().getSession(true);
      HttpServletRequest sr = (HttpServletRequest) context.getExternalContext().getRequest();
      String valor  = s.getAttribute("VS_IND_PAYMENT_ID"); */
      //inicializar(this.getVcAreaSeleccion());// otro srvlet

      /* FacesContext context = FacesContext.getCurrentInstance();
      HttpSession s = (HttpSession) context.getExternalContext().getSession(true);
      s.setAttribute("VS_AREA", this.getVcAreaSeleccion()); */

      //inicializar(s.getAttribute("VS_AREA").toString());

      //ExternalContext e = context.getExternalContext();
      //e.redirect(e.encodeResourceURL("pgw_calculoMulta_bienvenido.seam"));

      this.getObjFiltroBean().setVcOrgResolutivo(this.getVcAreaSeleccion());
      this.setVcMuestraInicio(" none;");
      this.setVcMuestraFormPrincipal(" block;");
      
      inicializar();
    }catch(Exception e){
      e.printStackTrace();
    }
    logger.info(">>FIN doIniciar");
  }

  //public void inicializar(ClsUsuarioIndBean objUsuario, String vcOrgResolutivo) {
  public void inicializar() {
    logger.info(">>inicializar: "+ this.getObjFiltroBean().getVcOrgResolutivo());
    df.setRoundingMode(RoundingMode.UP);
    
    objResultDAO = new ClsResultDAO();
    //objFiltroBean = new ClsCalculoBean();
    objFiltroBean.setDtFechaInicio(new Date());
    objFiltroBean.setDtFechaFin(new Date());
    objFiltroBean.setDtFechaInicioCcd(new Date());
    objFiltroBean.setDtFechaFinCcd(new Date());

    
    /* this.lstMetodoCalculoBean = new ArrayList<ClsMetodoCalculoBean>();
    this.lstInstanciaBean = new ArrayList<ClsInstanciasBean>();
    this.lstMultaUITAnioBean = new ArrayList<ClsMultaUITAnioBean>();
    this.lstAfectacionBean = new ArrayList<ClsTipoAfectacionBean>();
    this.lstProbabilidadBarrerasBean = new ArrayList<ClsProbabilidadBarrerasBean>(); */


    /* FacesContext context = FacesContext.getCurrentInstance();
    HttpSession s = (HttpSession) context.getExternalContext().getSession(true);
    String v_area = s.getAttribute("VS_AREA").toString();
    logger.info("v_area: "+v_area); */
    this.getObjFiltroBean().setVcOrgResolutivo(this.getObjFiltroBean().getVcOrgResolutivo());
    this.doListarMetodosXOrgInstancia();
    this.doListarMultaUITAnios();
    this.doListarProbabilidadBarreras();
    this.calculaFD();
    
    List<SelectItem> lstAfec = new ArrayList<SelectItem>();
    this.getObjFiltroBean().setLstTipoAfectacion(lstAfec);

    /* this.lstAreas= new LinkedHashMap<String, String>();
    this.lstAgravF1= new LinkedHashMap<String, String>();
    this.lstAgravF2= new LinkedHashMap<String, String>();
    this.lstAgravF3= new LinkedHashMap<String, String>();
    this.lstAgravF4= new LinkedHashMap<String, String>();
    this.lstAgravF5= new LinkedHashMap<String, String>();
    this.lstAgravF6= new LinkedHashMap<String, String>();
    this.lstAtenF7= new LinkedHashMap<String, String>();
    this.lstAtenF8= new LinkedHashMap<String, String>();
    this.lstAtenF9= new LinkedHashMap<String, String>();
    this.lstAtenF10= new LinkedHashMap<String, String>();
    this.lstAtenF11= new LinkedHashMap<String, String>(); */
    /* this.doListarAgravF01();
    this.doListarAgravF02();
    this.doListarAgravF03();
    this.doListarAtenF01();
    this.doListarAtenF02();
    this.doListarAtenF03(); */

    //////////////////////
    this.setVcMuestraPreestablecido(" none;");
    this.setVcMuestraLibro(" none;");
    this.setVcMuestraBarreras(" none;");
    this.setVcMuestraFacSecEconBarreras(" none;");
    this.setVcMuestraPreestablecidoPi(" none;");
    this.setVcMuestraOrgano1raInstanciaPi(" none;");
    this.setVcMuestraPreestablecidoCcd(" none;");
    this.setVcMuestraOrgano1raInstanciaCcd(" none;");
    this.setVcMuestraPreestablecidoFirma(" none;");
    this.setVcMuestraPorcentajeVentas(" none;");
    this.setVcMuestraOrgano1raInstanciaVentas(" none;");
    this.setVcMuestraCLCVentas(" none;");
    this.setVcMuestraOrgano1raInstanciaAdhoc(" none;");
    this.setVcMuestraAdHoc(" none;");
    this.setVcMuestraTamEmpresaAdHoc(" none;");
    this.setVcMuestraGravedad1AdHoc(" none;");
    this.setVcMuestraGravedad2AdHoc(" none;");
    this.setVcMuestraTopelegalNoPIAdHoc(" none;");
    
    this.setVcMuestraAtenuanteF8(" none;");
    this.setVcMuestraAtenuanteF10(" none;");
    this.setVcMuestraAtenuanteF7(" block;");

    this.setVcMuestraFactorGVariacion(" none;");
    this.setVcMuestraFactorPVariacion(" none;");
    /////
    this.getObjFiltroBean().setIsBlMultaBase(false);
    this.getObjFiltroBean().setIsBlMultaBaseLibro(false);
    this.getObjFiltroBean().setIsBlMultaBaseBarrera(false);
    this.getObjFiltroBean().setIsBlMultaBasePi(false);
    this.getObjFiltroBean().setIsBlMultaBaseCcd(false);
    this.getObjFiltroBean().setIsBlMultaBaseVentas(false);
    this.getObjFiltroBean().setIsBlMultaBaseAdhoc(false);
    this.getObjFiltroBean().setIsBlMultaBaseFirma(false);

    
    this.getObjFiltroBean().setIsBlLstAfectacion(false);

    this.getObjFiltroBean().setIsBlCheckUIT(false);
    this.getObjFiltroBean().setIsBlCheckUITCcd(false);
    this.getObjFiltroBean().setIsBlCheckUITAdhoc(false);
    //////////////////////////////////////////OS-5
    /* SelectItem data = new SelectItem();
    List<SelectItem> lstMetodoCal = new ArrayList<SelectItem>();

    data.setValue(-1);
    data.setLabel("Seleccionar");
    lstMetodoCal.add(data);
    this.getObjFiltroBean().setLstInstanciasCalculo(lstMetodoCal); */

    /* try{
    ExternalContext e = context.getExternalContext();
    e.redirect(e.encodeResourceURL("pgw_calculoMulta.seam"));
    }catch(Exception e){
      e.printStackTrace();
    } */
  }

  /* public void doListarParametros() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(12);
      if (objResult != null) {
          this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  } */

  public void doListarAgravF1() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(39);
      if (objResult != null) {
          this.setLstAgravF1((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAgravF2() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(40);
      if (objResult != null) {
          this.setLstAgravF2((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAgravF3() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(41);
      if (objResult != null) {
          this.setLstAgravF3((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAgravF4() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(42);
      if (objResult != null) {
          this.setLstAgravF4((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAgravF5() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(43);
      if (objResult != null) {
          this.setLstAgravF5((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAgravF6() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(44);
      if (objResult != null) {
          this.setLstAgravF6((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }

  public List<SelectItem> getAgravF0(){
    List<SelectItem> lstO = new ArrayList<SelectItem>();
    
    SelectItem data = new SelectItem();
    SelectItem data1 = new SelectItem();
    SelectItem data2 = new SelectItem();
    SelectItem data3 = new SelectItem();
    SelectItem data4 = new SelectItem();
    SelectItem data5 = new SelectItem();
    SelectItem data6 = new SelectItem();
    SelectItem data7 = new SelectItem();
    SelectItem data8 = new SelectItem();
    SelectItem data9 = new SelectItem();
    SelectItem data10 = new SelectItem();
    SelectItem data11 = new SelectItem();
    SelectItem data12 = new SelectItem();
    SelectItem data13 = new SelectItem();
    SelectItem data14 = new SelectItem();
    SelectItem data15 = new SelectItem();
    SelectItem data16 = new SelectItem();
    SelectItem data17 = new SelectItem();
    SelectItem data18 = new SelectItem();
    SelectItem data19 = new SelectItem();

    data.setValue("1");
    data.setLabel("100%");
    lstO.add(data);
    data1.setValue("0.95");
    data1.setLabel("95%");
    lstO.add(data1);
    data2.setValue("0.9");
    data2.setLabel("90%");
    lstO.add(data2);
    data3.setValue("0.85");
    data3.setLabel("85%");
    lstO.add(data3);
    data4.setValue("0.8");
    data4.setLabel("80%");
    lstO.add(data4);
    data5.setValue("0.75");
    data5.setLabel("75%");
    lstO.add(data5);
    data6.setValue("0.70");
    data6.setLabel("70%");
    lstO.add(data6);
    data7.setValue("0.65");
    data7.setLabel("65%");
    lstO.add(data7);
    data8.setValue("0.6");
    data8.setLabel("60%");
    lstO.add(data8);
    data9.setValue("0.55");
    data9.setLabel("55%");
    lstO.add(data9);
    data10.setValue("0.5");
    data10.setLabel("50%");
    lstO.add(data10);
    data11.setValue("0.45");
    data11.setLabel("45%");
    lstO.add(data11);
    data12.setValue("0.4");
    data12.setLabel("40%");
    lstO.add(data12);
    data13.setValue("0.35");
    data13.setLabel("35%");
    lstO.add(data13);
    data14.setValue("0.3");
    data14.setLabel("30%");
    lstO.add(data14);
    data15.setValue("0.25");
    data15.setLabel("25%");
    lstO.add(data15);
    data16.setValue("0.2");
    data16.setLabel("20%");
    lstO.add(data16);
    data17.setValue("0.15");
    data17.setLabel("15%");
    lstO.add(data17);
    data18.setValue("0.1");
    data18.setLabel("10%");
    lstO.add(data18);
    data19.setValue("0.5");
    data19.setLabel("5%");
    lstO.add(data19);
    return lstO;
  }

public List<SelectItem> getAtenF0(){
    List<SelectItem> lstO = new ArrayList<SelectItem>();
    
    SelectItem data = new SelectItem();
    SelectItem data1 = new SelectItem();
    SelectItem data2 = new SelectItem();
    SelectItem data3 = new SelectItem();
    SelectItem data4 = new SelectItem();
    SelectItem data5 = new SelectItem();
    SelectItem data6 = new SelectItem();
    SelectItem data7 = new SelectItem();
    SelectItem data8 = new SelectItem();
    SelectItem data9 = new SelectItem();

    data.setValue("-0.05");
    data.setLabel("-5%");
    lstO.add(data);
    data1.setValue("-0.1");
    data1.setLabel("-10%");
    lstO.add(data1);
    data2.setValue("-0.15");
    data2.setLabel("-15%");
    lstO.add(data2);
    data3.setValue("-0.2");
    data3.setLabel("-20%");
    lstO.add(data3);
    data4.setValue("-0.25");
    data4.setLabel("-25%");
    lstO.add(data4);
    data5.setValue("-0.3");
    data5.setLabel("-30%");
    lstO.add(data5);
    data6.setValue("-0.35");
    data6.setLabel("-35%");
    lstO.add(data6);
    data7.setValue("-0.4");
    data7.setLabel("-40%");
    lstO.add(data7);
    data8.setValue("-0.45");
    data8.setLabel("-45%");
    lstO.add(data8);
    data9.setValue("-0.5");
    data9.setLabel("-50%");
    lstO.add(data9);
    return lstO;
  }

  public void doListarAgravF01() {
    logger.debug(">>doListarAgravF01");
    this.lstAgravF01 = new ArrayList<SelectItem>();
    this.setLstAgravF01(this.getAgravF0());
  }
  public void doListarAgravF02() {
    logger.debug(">>doListarAgravF02");
    this.lstAgravF02 = new ArrayList<SelectItem>();
    this.setLstAgravF02(this.getAgravF0());
  }
  public void doListarAgravF03() {
    logger.debug(">>doListarAgravF03");
    this.lstAgravF03 = new ArrayList<SelectItem>();
    this.setLstAgravF03(this.getAgravF0());
  }

  public void doListarAtenF01() {
    logger.debug(">>doListarAtenF01");
    this.lstAtenF01 = new ArrayList<SelectItem>();
    this.setLstAtenF01(this.getAtenF0());
  }
  public void doListarAtenF02() {
    logger.debug(">>doListarAtenF02");
    this.lstAtenF02 = new ArrayList<SelectItem>();
    this.setLstAtenF02(this.getAtenF0());
  }
  public void doListarAtenF03() {
    logger.debug(">>doListarAtenF03");
    this.lstAtenF03 = new ArrayList<SelectItem>();
    this.setLstAtenF03(this.getAtenF0());
  }

  public void doListarAtenF7() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(45);
      if (objResult != null) {
          this.setLstAtenF7((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAtenF8() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(46);
      if (objResult != null) {
          this.setLstAtenF8((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAtenF9() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(47);
      if (objResult != null) {
          this.setLstAtenF9((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAtenF10() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(48);
      if (objResult != null) {
          this.setLstAtenF10((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }
  public void doListarAtenF11() {
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarParametros(49);
      if (objResult != null) {
          this.setLstAtenF11((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
  }

  public void doBuscarRuc() {
      logger.info(">>doBuscarRuc");
      logger.debug("this.getObjFiltroBean().getVcRuc(): " + this.getObjFiltroBean().getVcRuc());
      if(this.getObjFiltroBean().getVcRuc().length()!=0){
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doBuscarRuc(this.getObjFiltroBean().getVcRuc());
      if (objResult != null) {
            this.getObjFiltroBean().setVcRazonSocial(objResult.get("VC_RAZONSOC")+"");
      }
      this.doLimpiarRUC();
      }
      logger.info(">>FIN doBuscarRuc");
  }
  public void doBuscarRucLibro() {
      logger.info(">>doBuscarRucLibro");
      logger.debug("this.getObjFiltroBean().getVcRucLibro(): " + this.getObjFiltroBean().getVcRucLibro());
      logger.debug("this.getObjFiltroBean().getVcRucLibro().length(): " + this.getObjFiltroBean().getVcRucLibro().length());
      //
      if(this.getObjFiltroBean().getVcRucLibro().length()!=0){
      this.getObjFiltroBean().setVcRuc(this.getObjFiltroBean().getVcRucLibro());
      logger.debug("this.getObjFiltroBean().getVcRuc(): " + this.getObjFiltroBean().getVcRuc());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doBuscarRuc(this.getObjFiltroBean().getVcRucLibro());
      if (objResult != null) {
            this.getObjFiltroBean().setVcRazonSocial(objResult.get("VC_RAZONSOC")+"");
      }
      this.doLimpiarRUC();
      }
      logger.info(">>FIN doBuscarRucLibro");
  }
  
  public void doBuscarRucBarreras() {
      logger.info(">>doBuscarRucBarreras");
      logger.debug("this.getObjFiltroBean().getVcRucBarreras(): " + this.getObjFiltroBean().getVcRucBarreras());
      logger.debug("this.getObjFiltroBean().getVcRucBarreras().length(): " + this.getObjFiltroBean().getVcRucBarreras().length());
      //
      if(this.getObjFiltroBean().getVcRucBarreras().length()!=0){
        this.getObjFiltroBean().setVcRuc(this.getObjFiltroBean().getVcRucBarreras());
        logger.debug("this.getObjFiltroBean().getVcRuc(): " + this.getObjFiltroBean().getVcRuc());
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doBuscarRuc(this.getObjFiltroBean().getVcRucBarreras());
        if (objResult != null) {
              this.getObjFiltroBean().setVcRazonSocial(objResult.get("VC_RAZONSOC")+"");
        }
        this.doLimpiarRUC();
      }
      logger.info(">>FIN doBuscarRucBarreras");
  }
  public void doBuscarRucPi() {
      logger.info(">>doBuscarRucPi");
      logger.debug("this.getObjFiltroBean().getVcRucPi(): " + this.getObjFiltroBean().getVcRucPi());
      //
      if(this.getObjFiltroBean().getVcRucPi().length()!=0){
      this.getObjFiltroBean().setVcRuc(this.getObjFiltroBean().getVcRucPi());
      logger.debug("this.getObjFiltroBean().getVcRuc(): " + this.getObjFiltroBean().getVcRuc());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doBuscarRuc(this.getObjFiltroBean().getVcRucPi());
      if (objResult != null) {
            this.getObjFiltroBean().setVcRazonSocial(objResult.get("VC_RAZONSOC")+"");
      }
      this.doLimpiarRUC();
      }
      logger.info(">>FIN doBuscarRucPi");
  }
  public void doBuscarRucCcd() {
      logger.info(">>doBuscarRucCcd");
      logger.debug("this.getObjFiltroBean().getVcRucCcd(): " + this.getObjFiltroBean().getVcRucCcd());
      //
      if(this.getObjFiltroBean().getVcRucCcd().length()!=0){
      this.getObjFiltroBean().setVcRuc(this.getObjFiltroBean().getVcRucCcd());
      logger.debug("this.getObjFiltroBean().getVcRuc(): " + this.getObjFiltroBean().getVcRuc());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doBuscarRuc(this.getObjFiltroBean().getVcRucCcd());
      if (objResult != null) {
            this.getObjFiltroBean().setVcRazonSocial(objResult.get("VC_RAZONSOC")+"");
      }
      this.doLimpiarRUC();
      }
      logger.info(">>FIN doBuscarRucCcd");
  }
  public void doBuscarRucFirma() {
      logger.info(">>doBuscarRucFirma");
      logger.debug("this.getObjFiltroBean().getVcRucFirma(): " + this.getObjFiltroBean().getVcRucFirma());
      //
      if(this.getObjFiltroBean().getVcRucFirma().length()!=0){
      this.getObjFiltroBean().setVcRuc(this.getObjFiltroBean().getVcRucFirma());
      logger.debug("this.getObjFiltroBean().getVcRuc(): " + this.getObjFiltroBean().getVcRuc());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doBuscarRuc(this.getObjFiltroBean().getVcRucFirma());
      if (objResult != null) {
            this.getObjFiltroBean().setVcRazonSocial(objResult.get("VC_RAZONSOC")+"");
      }
      this.doLimpiarRUC();
      }
      logger.info(">>FIN doBuscarRucFirma");
  }
  public void doBuscarRucVentas() {
      logger.info(">>doBuscarRucVentas");
      logger.debug("this.getObjFiltroBean().getVcRucVentas(): " + this.getObjFiltroBean().getVcRucVentas());
      //
      if(this.getObjFiltroBean().getVcRucVentas().length()!=0){
      this.getObjFiltroBean().setVcRuc(this.getObjFiltroBean().getVcRucVentas());
      logger.debug("this.getObjFiltroBean().getVcRuc(): " + this.getObjFiltroBean().getVcRuc());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doBuscarRuc(this.getObjFiltroBean().getVcRucVentas());
      if (objResult != null) {
            this.getObjFiltroBean().setVcRazonSocial(objResult.get("VC_RAZONSOC")+"");
      }
      this.doLimpiarRUC();
      }
      logger.info(">>FIN doBuscarRucVentas");
  }
  public void doBuscarRucAdhoc() {
      logger.info(">>doBuscarRucAdhoc");
      logger.debug("this.getObjFiltroBean().getVcRucAdhoc(): " + this.getObjFiltroBean().getVcRucAdhoc());
      //
      if(this.getObjFiltroBean().getVcRucAdhoc().length()!=0){
      this.getObjFiltroBean().setVcRuc(this.getObjFiltroBean().getVcRucAdhoc());
      logger.debug("this.getObjFiltroBean().getVcRuc(): " + this.getObjFiltroBean().getVcRuc());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doBuscarRuc(this.getObjFiltroBean().getVcRucAdhoc());
      if (objResult != null) {
            this.getObjFiltroBean().setVcRazonSocial(objResult.get("VC_RAZONSOC")+"");
      }
      this.doLimpiarRUC();
      }
      logger.info(">>FIN doBuscarRucAdhoc");
  }
  public void doCalcularMbase() {
      logger.info(">>doCalcularMbase");
      logger.debug("this.getObjFiltroBean().getVcMetodo() " + this.getObjFiltroBean().getVcMetodo());
      logger.debug("this.getObjFiltroBean().getVcOrgResolutivo() " + this.getObjFiltroBean().getVcOrgResolutivo());
      logger.debug("this.getObjFiltroBean().getVcInstancia() " + this.getObjFiltroBean().getVcInstancia());
      logger.debug("this.getObjFiltroBean().getVcTamEmpresa() " + this.getObjFiltroBean().getVcTamEmpresa());
      //logger.info("this.getObjFiltroBean().getVcIdTamEmpresa() " + this.getObjFiltroBean().getVcIdTamEmpresa());
      logger.debug("this.getNuIdTamEmpresa() " + this.getNuIdTamEmpresa());
      logger.debug("this.getObjFiltroBean().getVcTamEmpresaSeleccion() " + this.getObjFiltroBean().getVcTamEmpresaSeleccion());
      ///this.setNuIdTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcTamEmpresaSeleccion()));
      //logger.debug("this.getNuIdTamEmpresa() " + this.getNuIdTamEmpresa());
      logger.debug("this.getObjFiltroBean().getVcIdNivelAfectacion() " + this.getObjFiltroBean().getVcIdNivelAfectacion());
      if(!this.getObjFiltroBean().getVcInstancia().equals("-1") && !this.getObjFiltroBean().getVcRuc().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("RUC no encontrado")
          //&& this.getObjFiltroBean().getNuFacturacionAnual()!=0 && !this.getObjFiltroBean().getVcAnioResolucion().equals("-1") 
          && !this.getObjFiltroBean().getVcTipoAfectacion().equals("") && this.getObjFiltroBean().getNuFactorDuracion()!=0){
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetValorMatriz(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getVcOrgResolutivo(), Integer.parseInt(this.getObjFiltroBean().getVcInstancia()), Integer.parseInt(this.getObjFiltroBean().getVcTamEmpresaSeleccion()), Integer.parseInt(this.getObjFiltroBean().getVcIdNivelAfectacion()));
        if (objResult != null) {
              this.getObjFiltroBean().setNuValorMatriz((Double) objResult.get("GET_VALOR_MATRIZ"));
              this.getObjFiltroBean().setNuMultaBase((Double) round(this.getObjFiltroBean().getNuValorMatriz()*this.getObjFiltroBean().getNuFactorDuracion(),2));
              this.getObjFiltroBean().setNuMultaBaseCompleto((Double) this.getObjFiltroBean().getNuValorMatriz()*this.getObjFiltroBean().getNuFactorDuracion());

              this.getObjFiltroBean().setIsBlMultaBase(true);
        }
      }
      
      logger.info(">>FIN doCalcularMbase");
  }

  public void doCalcularMbasePi() {
      logger.info(">>doCalcularMbasePi");
      logger.debug("this.getObjFiltroBean().getVcMetodo() " + this.getObjFiltroBean().getVcMetodo());
      logger.debug("this.getObjFiltroBean().getVcOrgResolutivo() " + this.getObjFiltroBean().getVcOrgResolutivo());
      logger.debug("this.getObjFiltroBean().getVcInstancia() " + this.getObjFiltroBean().getVcInstanciaPi());
      logger.debug("this.getNuIdTamEmpresa() " + this.getNuIdTamEmpresa());
      logger.debug("this.getObjFiltroBean().getVcTamEmpresaSeleccionPi() " + this.getObjFiltroBean().getVcTamEmpresaSeleccionPi());
      logger.debug("this.getObjFiltroBean().getVcIdNivelAfectacion() " + this.getObjFiltroBean().getVcIdNivelAfectacionPi());
      if(!this.getObjFiltroBean().getVcInstanciaPi().equals("-1") && !this.getObjFiltroBean().getVcRucPi().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("RUC no encontrado")
          //&& this.getObjFiltroBean().getNuFacturacionAnualPi()!=0 && !this.getObjFiltroBean().getVcAnioResolucionPi().equals("-1") 
          && !this.getObjFiltroBean().getVcTipoAfectacionPi().equals("")){
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetValorMatriz(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getVcOrgResolutivo(), Integer.parseInt(this.getObjFiltroBean().getVcInstanciaPi()), Integer.parseInt(this.getObjFiltroBean().getVcTamEmpresaSeleccionPi()), Integer.parseInt(this.getObjFiltroBean().getVcIdNivelAfectacionPi()));
        if (objResult != null) {
              this.getObjFiltroBean().setNuMultaBasePi((Double) round((Double) objResult.get("GET_VALOR_MATRIZ")*1,2));
              this.getObjFiltroBean().setNuMultaBasePiCompleto((Double) objResult.get("GET_VALOR_MATRIZ")*1);
        
              this.getObjFiltroBean().setIsBlMultaBasePi(true);
        }
      }
      
      logger.debug("this.getVcMuestraOrgano1raInstanciaPi() " + this.getVcMuestraOrgano1raInstanciaPi());
      logger.info(">>FIN doCalcularMbasePi");
  }

  public void doCalcularMbaseCcd() {
      logger.info(">>doCalcularMbaseCcd");
      logger.debug("this.getObjFiltroBean().getVcMetodo() " + this.getObjFiltroBean().getVcMetodo());
      logger.debug("this.getObjFiltroBean().getVcOrgResolutivo() " + this.getObjFiltroBean().getVcOrgResolutivo());
      logger.debug("this.getObjFiltroBean().getVcInstancia() " + this.getObjFiltroBean().getVcInstanciaCcd());
      logger.debug("this.getNuIdTamEmpresa() " + this.getNuIdTamEmpresa());
      logger.debug("this.getObjFiltroBean().getVcIdNivelAfectacion() " + this.getObjFiltroBean().getVcIdNivelAfectacionCcd());
      logger.debug("this.getObjFiltroBean().getVcTamEmpresaSeleccionCcd() " + this.getObjFiltroBean().getVcTamEmpresaSeleccionCcd());

      if(!this.getObjFiltroBean().getVcInstanciaCcd().equals("-1") && !this.getObjFiltroBean().getVcRucCcd().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("RUC no encontrado")
          //&& this.getObjFiltroBean().getNuFacturacionAnualCcd()!=0 && !this.getObjFiltroBean().getVcAnioResolucionCcd().equals("-1") 
          && !this.getObjFiltroBean().getVcTipoAfectacionCcd().equals("") && this.getObjFiltroBean().getNuFactorDuracion()!=0){
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetValorMatriz(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getVcOrgResolutivo(), Integer.parseInt(this.getObjFiltroBean().getVcInstanciaCcd()), Integer.parseInt(this.getObjFiltroBean().getVcTamEmpresaSeleccionCcd()), Integer.parseInt(this.getObjFiltroBean().getVcIdNivelAfectacionCcd()));
        if (objResult != null) {
              this.getObjFiltroBean().setNuValorMatriz((Double) objResult.get("GET_VALOR_MATRIZ"));
              this.getObjFiltroBean().setNuMultaBaseCcd((Double) round((Double) objResult.get("GET_VALOR_MATRIZ")*this.getObjFiltroBean().getNuFactorDuracion(), 2));
              this.getObjFiltroBean().setNuMultaBaseCcdCompleto((Double) objResult.get("GET_VALOR_MATRIZ")*this.getObjFiltroBean().getNuFactorDuracion());
        
              this.getObjFiltroBean().setIsBlMultaBaseCcd(true);
        }
      }
      logger.info(">>FIN doCalcularMbaseCcd");
  }

  public void doCalcularMbaseFirma() {
      logger.info(">>doCalcularMbaseFirma");
      logger.debug("this.getObjFiltroBean().getVcMetodo() " + this.getObjFiltroBean().getVcMetodo());
      logger.debug("this.getObjFiltroBean().getVcOrgResolutivo() " + this.getObjFiltroBean().getVcOrgResolutivo());
      logger.debug("this.getObjFiltroBean().getVcInstancia() " + this.getObjFiltroBean().getVcInstancia());
      logger.debug("this.getNuIdTamEmpresa() " + this.getNuIdTamEmpresa());

      if(!this.getObjFiltroBean().getVcIdTipoServicioFirma().equals("-1") && !this.getObjFiltroBean().getVcRucFirma().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("RUC no encontrado")
          && this.getObjFiltroBean().getNuFacturacionAnualFirma()!=0 && !this.getObjFiltroBean().getVcAnioResolucionFirma().equals("-1") && !this.getObjFiltroBean().getVcTipoAfectacionFirma().equals("")){

      this.getObjFiltroBean().setNuMultaBaseFirma((Double) round(this.getObjFiltroBean().getNuDanioBaseFirma()*this.getObjFiltroBean().getNuFactorFirma(), 2));
      this.getObjFiltroBean().setNuMultaBaseFirmaCompleto(this.getObjFiltroBean().getNuDanioBaseFirma()*this.getObjFiltroBean().getNuFactorFirma());
      this.getObjFiltroBean().setNuFactorPFirma(1);
      this.getObjFiltroBean().setIsBlMultaBaseFirma(true);
      }
      logger.info(">>FIN doCalcularMbaseFirma");
  }

  public void doCalcularMbaseVentas() {
      logger.info(">>doCalcularMbaseVentas");
      logger.debug("this.getObjFiltroBean().getVcMetodo() " + this.getObjFiltroBean().getVcMetodo());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualProductoVentas() " + this.getObjFiltroBean().getNuFacturacionAnualProductoVentas());
      logger.debug("this.getObjFiltroBean().getNuFactorAVentas() " + this.getObjFiltroBean().getNuFactorAVentas());
      logger.debug("this.getObjFiltroBean().getNuUmbralFactorAVentas() " + this.getObjFiltroBean().getNuUmbralFactorAVentas());
      logger.debug("this.getObjFiltroBean().getNuFactorGVentas() " + this.getObjFiltroBean().getNuFactorGVentas());
      logger.debug("this.getObjFiltroBean().getVcFactorGDifAccion() " + this.getObjFiltroBean().getVcFactorGDifAccion());
      logger.debug("this.getObjFiltroBean().getNuUmbralFactorGDifVentas() " + this.getObjFiltroBean().getNuUmbralFactorGDifVentas());
      logger.debug("this.getObjFiltroBean().getNuFactorGDifVentas() " + this.getObjFiltroBean().getNuFactorGDifVentas());

      double nuFactorP = 0;
      if(!this.getObjFiltroBean().getVcInstanciaVentas().equals("-1") && !this.getObjFiltroBean().getVcRucVentas().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("RUC no encontrado")
          && this.getObjFiltroBean().getNuFacturacionAnualVentas()!=0 && this.getObjFiltroBean().getNuFacturacionAnualProductoVentas()!=0 && !this.getObjFiltroBean().getVcAnioResolucionVentas().equals("-1") && !this.getObjFiltroBean().getVcTipoAfectacionVentas().equals("") && this.getObjFiltroBean().getNuFactorAVentas()!=0){
      
          //if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CCD")){
            if(this.getObjFiltroBean().getVcFactorGDifAccion()!=null && this.getObjFiltroBean().getVcFactorGDifAccion().equals("restar")){
              nuFactorP = this.getObjFiltroBean().getNuFactorGVentas() - this.getObjFiltroBean().getNuFactorGDifVentas();
            } else {
              nuFactorP = this.getObjFiltroBean().getNuFactorGVentas() + this.getObjFiltroBean().getNuFactorGDifVentas();
            }
          /* } else {
            nuFactorP = this.getObjFiltroBean().getNuFactorGVentas();
          } */
      logger.debug("ACTUAL-nuFactorP : " + nuFactorP);
      this.getObjFiltroBean().setNuMultaBaseVentas(round(this.getObjFiltroBean().getNuFacturacionAnualProductoVentas()*(this.getObjFiltroBean().getNuFactorAVentas()/100)*nuFactorP, 2));
      this.getObjFiltroBean().setNuMultaBaseVentasCompleto(this.getObjFiltroBean().getNuFacturacionAnualProductoVentas()*(this.getObjFiltroBean().getNuFactorAVentas()/100)*nuFactorP);
      logger.debug("this.getObjFiltroBean().getNuMultaBaseVentas() " + this.getObjFiltroBean().getNuMultaBaseVentas());
      this.getObjFiltroBean().setNuMultaBaseUITVentas(round(this.getObjFiltroBean().getNuMultaBaseVentasCompleto()/this.getObjFiltroBean().getNuAnioUIT(), 2));
      this.getObjFiltroBean().setNuMultaBaseUITVentasCompleto(this.getObjFiltroBean().getNuMultaBaseVentasCompleto()/this.getObjFiltroBean().getNuAnioUIT());
      this.getObjFiltroBean().setIsBlMultaBaseVentas(true);
      }
      logger.info(">>FIN doCalcularMbaseVentas");
  }

  public void doCalcularMbaseAdhoc() {
      logger.info(">>doCalcularMbaseAdhoc");
      logger.debug("this.getObjFiltroBean().getVcMetodo() " + this.getObjFiltroBean().getVcMetodo());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualAdhoc() " + this.getObjFiltroBean().getNuFacturacionAnualAdhoc());
      logger.debug("this.getObjFiltroBean().getNuFactorBAdhoc() " + this.getObjFiltroBean().getNuFactorBAdhoc());
      logger.debug("this.getObjFiltroBean().getNuFactorPAdhoc() " + this.getObjFiltroBean().getNuFactorPAdhoc());
      logger.debug("this.getObjFiltroBean().getVcFactorPDifAccion() " + this.getObjFiltroBean().getVcFactorPDifAccion());
      logger.debug("this.getObjFiltroBean().getNuUmbralFactorPDifAdhoc() " + this.getObjFiltroBean().getNuUmbralFactorPDifAdhoc());
      logger.debug("this.getObjFiltroBean().getNuFactorPDifAdhoc() " + this.getObjFiltroBean().getNuFactorPDifAdhoc());

      double nuFactorP = 0;
      if(!this.getObjFiltroBean().getVcInstanciaAdhoc().equals("-1") && !this.getObjFiltroBean().getVcRucAdhoc().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("RUC no encontrado")
          //&& this.getObjFiltroBean().getNuFacturacionAnualAdhoc()!=0 && !this.getObjFiltroBean().getVcAnioResolucionAdhoc().equals("-1") 
          && !this.getObjFiltroBean().getVcTipoAfectacionAdhoc().equals("") && this.getObjFiltroBean().getNuFactorBAdhoc()!=0){

            if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC") || this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
              if(this.getObjFiltroBean().getVcFactorPDifAccion() != null && this.getObjFiltroBean().getVcFactorPDifAccion().equals("restar")){
                nuFactorP = this.getObjFiltroBean().getNuFactorPAdhoc() - this.getObjFiltroBean().getNuFactorPDifAdhoc();
              } else {
                nuFactorP = this.getObjFiltroBean().getNuFactorPAdhoc() + this.getObjFiltroBean().getNuFactorPDifAdhoc();
              }
            } else {
              nuFactorP = this.getObjFiltroBean().getNuFactorPAdhoc();
            }
      logger.debug("ACTUAL-nuFactorP : " + nuFactorP);
      ///this.getObjFiltroBean().setNuMultaBaseAdhoc(round(this.getObjFiltroBean().getNuFactorBAdhoc()/(nuFactorP/100), 2));
      ///this.getObjFiltroBean().setNuMultaBaseAdhocCompleto(this.getObjFiltroBean().getNuFactorBAdhoc()/(nuFactorP/100));
      this.getObjFiltroBean().setNuMultaBaseAdhoc(round(this.getObjFiltroBean().getNuFactorBAdhoc()/(nuFactorP), 2));
      this.getObjFiltroBean().setNuMultaBaseAdhocCompleto(this.getObjFiltroBean().getNuFactorBAdhoc()/(nuFactorP));
      //this.getObjFiltroBean().setNuMultaBaseUITAdhoc(round(this.getObjFiltroBean().getNuMultaBaseAdhocCompleto()/this.getObjFiltroBean().getNuAnioUIT(), 2));
      //this.getObjFiltroBean().setNuMultaBaseUITAdhocCompleto(this.getObjFiltroBean().getNuMultaBaseAdhocCompleto()/this.getObjFiltroBean().getNuAnioUIT());
      logger.debug("ACTUAL-this.getObjFiltroBean().getNuFactorBAdhoc() : " + this.getObjFiltroBean().getNuFactorBAdhoc());
      logger.debug("ACTUAL-getNuMultaBaseAdhoc() : " + this.getObjFiltroBean().getNuMultaBaseAdhoc());
      logger.debug("ACTUAL-getNuMultaBaseAdhocCompleto() : " + this.getObjFiltroBean().getNuMultaBaseAdhocCompleto());
      this.getObjFiltroBean().setNuMultaBaseUITAdhoc(this.getObjFiltroBean().getNuMultaBaseAdhoc());
      this.getObjFiltroBean().setNuMultaAAAdhoc(this.getObjFiltroBean().getNuMultaBaseAdhoc());
      this.getObjFiltroBean().setIsBlMultaBaseAdhoc(true);
      }
      logger.info(">>FIN doCalcularMbaseAdhoc");
  }

  public void doCalcularMRefLibro() {
      logger.info(">>doCalcularMRefLibro");
      logger.debug("this.getObjFiltroBean().getVcOrgResolutivo() " + this.getObjFiltroBean().getVcOrgResolutivo());
      logger.debug("this.getNuIdTamEmpresa() " + this.getNuIdTamEmpresa());
      logger.debug("this.getObjFiltroBean().getVcClasificacion() " + this.getObjFiltroBean().getVcClasificacion());
      logger.debug("this.getObjFiltroBean().getIsBlMultaBaseLibro() "+this.getObjFiltroBean().isIsBlMultaBaseLibro());
      
      if(!this.getObjFiltroBean().getVcRucLibro().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("RUC no encontrado")
          && this.getObjFiltroBean().getNuFacturacionAnualLibro()!=0 && !this.getObjFiltroBean().getVcAnioResolucionLibro().equals("-1") && !this.getObjFiltroBean().getVcTipoAfectacionLibro().equals("")){
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetMultaLibro(this.getNuIdTamEmpresa(), this.getObjFiltroBean().getVcClasificacion());
      if (objResult != null) {
            this.getObjFiltroBean().setNuMultaRefLibro((Double) objResult.get("GET_MULTA_REF"));
            double nuMaxFact = (Double) objResult.get("GET_MULTA_MAXUIT");
            double nuMultaCoc = (Double) objResult.get("GET_MULTA_COC");
            double nuFactMin = (Double) objResult.get("GET_MULTA_FACTMIN");
            double nuFactMax = (Double) objResult.get("GET_MULTA_FACTMAX");
            

            double nuAi = this.getObjFiltroBean().getNuValorUIT()/nuMaxFact > 1.00 ? 1.00 : this.getObjFiltroBean().getNuValorUIT()/nuMaxFact;
            double nuA0 = nuFactMin/nuMaxFact;
            
            logger.debug(">>nuMaxFact: "+nuMaxFact);
            logger.debug(">>nuMultaCoc: "+nuMultaCoc);
            logger.debug(">>nuFactMin: "+nuFactMin);
            logger.debug(">>nuFactMax: "+nuFactMax);

            logger.debug(">>this.getObjFiltroBean().getNuValorUIT(): "+this.getObjFiltroBean().getNuValorUIT());
            logger.debug(">>ai: "+ nuAi);
            logger.debug(">>fco: "+ nuMultaCoc);
            //logger.debug(">>nuFactMin: "+ nuFactMin);
            //logger.debug(">>nuFactMax: "+ nuFactMax);
            logger.debug(">>ao: "+ nuA0);
            double nuFci1 = 1-nuMultaCoc;
            double nuFci2 = 1-nuA0;
            double nuFci3 = nuFci1/nuFci2;
            double nuFci4 = nuAi-nuA0;
            double nuFci5 = nuFci3*nuFci4;
            double nuFci = nuFci5+nuMultaCoc;
            logger.debug(">>fci: "+ nuFci);
            this.getObjFiltroBean().setNuFactorAi(round(nuAi,4));
            this.getObjFiltroBean().setNuFactorFco(round(nuMultaCoc,4));
            this.getObjFiltroBean().setNuFactorAo(round(nuA0,4));
            this.getObjFiltroBean().setNuFactorFci(round(nuFci,4));
            this.getObjFiltroBean().setNuMultaBaseLibro(round(nuFci*(Double) objResult.get("GET_MULTA_REF"),2));
            this.getObjFiltroBean().setNuMultaBaseLibroCompleto(nuFci*(Double) objResult.get("GET_MULTA_REF"));
            //OJO
            this.getObjFiltroBean().setNuMultaBase(this.getObjFiltroBean().getNuMultaBaseLibro());
      
            //REQ ID90 de la 3ra pantalla
            //BigDecimal nuMultaAA = new BigDecimal(this.getObjFiltroBean().getNuMultaRefLibro() * this.getObjFiltroBean().getNuFactorFci() ).setScale(2, RoundingMode.HALF_UP);
            //this.getObjFiltroBean().setNuMultaAALibro(nuMultaAA.doubleValue());

            this.getObjFiltroBean().setIsBlMultaBaseLibro(true);
      }
      }
      logger.info(">>FIN doCalcularMRefLibro");
  }

  public void doCalculaTopeYMultaBase() {
    logger.info(">>doCalculaTopeYMultaBase");
    double nuAuxIngTope = getTopeXIngresos(this.getObjFiltroBean().getNuPorcTope(), this.getObjFiltroBean().getNuFacturacionAnual());
    this.getObjFiltroBean().setNuIngresosTope(round(nuAuxIngTope, 2));
  
    List<Double> list = new ArrayList<>();

    // add element in ArrayList object list
    list.add(this.getObjFiltroBean().getNuMultaPreUIT());
    list.add(this.getObjFiltroBean().getNuGravedadTope());
    list.add(nuAuxIngTope);

    logger.debug("Min: " + findMin(list));    
    
    logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());
    this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
    logger.debug("this.getObjFiltroBean().getNuMultaFinal(): "+ this.getObjFiltroBean().getNuMultaFinal());
    this.getObjFiltroBean().setNuMultaFinalSoles(round(findMin(list)*this.getObjFiltroBean().getNuAnioUIT(), 2));
    logger.info(">>FIN doCalculaTopeYMultaBase");
  }

  public void doCalculaNoTopeYMultaBase() {
    logger.info(">>doCalculaNoTopeYMultaBase");
    List<Double> list = new ArrayList<>();

    // add element in ArrayList object list
    list.add(this.getObjFiltroBean().getNuMultaPreUIT());
    list.add(this.getObjFiltroBean().getNuGravedadTope());

    logger.debug("Min: " + findMin(list));    
    
    logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());
    this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
    logger.debug("this.getObjFiltroBean().getNuMultaFinal(): "+ this.getObjFiltroBean().getNuMultaFinal());
    this.getObjFiltroBean().setNuMultaFinalSoles(round(findMin(list)*this.getObjFiltroBean().getNuAnioUIT(), 2));
    logger.info(">>FIN doCalculaNoTopeYMultaBase");
  }

  public void doClickCheckUIT() {
    logger.info(">>doClickCheckUIT");
    logger.debug("this.getObjFiltroBean().isIsBlCheckUIT():" + this.getObjFiltroBean().isIsBlCheckUIT());
    if(this.getObjFiltroBean().isIsBlCheckUIT()){
      this.doCalculaNoTopeYMultaBase();
      this.getObjFiltroBean().setNuFacturacionAnual(0);
      this.getObjFiltroBean().setNuIngresosTope(0);
    }else{
      this.getObjFiltroBean().setNuMultaFinal(0);
    }
    logger.info(">>FIN doClickCheckUIT");
  }

public void doCalculaTopeYMultaBaseCcd() {
    logger.info(">>doCalculaTopeYMultaBaseCcd");
    double nuAuxIngTope = getTopeXIngresos(this.getObjFiltroBean().getNuPorcTope(), this.getObjFiltroBean().getNuFacturacionAnual());
    this.getObjFiltroBean().setNuIngresosTope(round(nuAuxIngTope, 2));
  
    List<Double> list = new ArrayList<>();

    // add element in ArrayList object list
    list.add(this.getObjFiltroBean().getNuMultaPreUIT());
    list.add(this.getObjFiltroBean().getNuGravedadTope());
    list.add(nuAuxIngTope);

    logger.debug("Min: " + findMin(list));    
    
    logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());
    this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
    logger.debug("this.getObjFiltroBean().getNuMultaFinal(): "+ this.getObjFiltroBean().getNuMultaFinal());
    this.getObjFiltroBean().setNuMultaFinalSoles(round(findMin(list)*this.getObjFiltroBean().getNuAnioUIT(), 2));
    logger.info(">>FIN doCalculaTopeYMultaBaseCcd");
  }

  public void doCalculaNoTopeYMultaBaseCcd() {
    logger.info(">>doCalculaNoTopeYMultaBaseCcd");
    List<Double> list = new ArrayList<>();

    // add element in ArrayList object list
    list.add(this.getObjFiltroBean().getNuMultaPreUIT());
    list.add(this.getObjFiltroBean().getNuGravedadTope());

    logger.debug("Min: " + findMin(list));    
    
    logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());
    this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
    logger.debug("this.getObjFiltroBean().getNuMultaFinal(): "+ this.getObjFiltroBean().getNuMultaFinal());
    this.getObjFiltroBean().setNuMultaFinalSoles(round(findMin(list)*this.getObjFiltroBean().getNuAnioUIT(), 2));
    logger.info(">>FIN doCalculaNoTopeYMultaBaseCcd");
  }

  public void doClickCheckUITCcd() {
    logger.info(">>doClickCheckUITCcd");
    logger.debug("this.getObjFiltroBean().isIsBlCheckUITCcd():" + this.getObjFiltroBean().isIsBlCheckUITCcd());
    if(this.getObjFiltroBean().isIsBlCheckUITCcd()){
      this.doCalculaNoTopeYMultaBaseCcd();
      this.getObjFiltroBean().setNuFacturacionAnual(0);
      this.getObjFiltroBean().setNuIngresosTope(0);
    }else{
      this.getObjFiltroBean().setNuMultaFinal(0);
    }
    logger.info(">>FIN doClickCheckUITCcd");
  }

public void doCalculaTopeYMultaBaseAdhoc() {
    logger.info(">>doCalculaTopeYMultaBaseAdhoc");
    logger.debug("this.getObjFiltroBean().getNuPorcVtasCcd(): "+ this.getObjFiltroBean().getNuPorcVtasCcd());
    double nuAuxIngTope = getTopeXIngresos(this.getObjFiltroBean().getNuPorcVtasCcd(), this.getObjFiltroBean().getNuFacturacionAnual());
    this.getObjFiltroBean().setNuIngresosTope(round(nuAuxIngTope, 2));
  
    List<Double> list = new ArrayList<>();

    // add element in ArrayList object list
    list.add(this.getObjFiltroBean().getNuMultaPreUIT());
    list.add(this.getObjFiltroBean().getNuGravedadTope());
    list.add(nuAuxIngTope);

    logger.debug("Min: " + findMin(list));    
    
    logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());
    this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
    logger.debug("this.getObjFiltroBean().getNuMultaFinal(): "+ this.getObjFiltroBean().getNuMultaFinal());
    this.getObjFiltroBean().setNuMultaFinalSoles(round(findMin(list)*this.getObjFiltroBean().getNuAnioUIT(), 2));
    logger.info(">>FIN doCalculaTopeYMultaBaseAdhoc");
  }

  public void doCalculaNoTopeYMultaBaseAdhoc() {
    logger.info(">>doCalculaNoTopeYMultaBaseAdhoc");
    List<Double> list = new ArrayList<>();

    // add element in ArrayList object list
    list.add(this.getObjFiltroBean().getNuMultaPreUIT());
    list.add(this.getObjFiltroBean().getNuGravedadTope());

    logger.debug("Min: " + findMin(list));    
    
    logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());
    this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
    logger.debug("this.getObjFiltroBean().getNuMultaFinal(): "+ this.getObjFiltroBean().getNuMultaFinal());
    this.getObjFiltroBean().setNuMultaFinalSoles(round(findMin(list)*this.getObjFiltroBean().getNuAnioUIT(), 2));
    logger.info(">>FIN doCalculaNoTopeYMultaBaseAdhoc");
  }

  public void doClickCheckUITAdhoc() {
    logger.info(">>doClickCheckUITAdhoc");
    logger.debug("this.getObjFiltroBean().isIsBlCheckUITAdhoc():" + this.getObjFiltroBean().isIsBlCheckUITAdhoc());
    if(this.getObjFiltroBean().isIsBlCheckUITAdhoc()){
      this.doCalculaNoTopeYMultaBaseAdhoc();
      this.getObjFiltroBean().setNuFacturacionAnual(0);
      this.getObjFiltroBean().setNuIngresosTope(0);
    }else{
      this.getObjFiltroBean().setNuMultaFinal(0);
    }
    logger.info(">>FIN doClickCheckUITAdhoc");
  }

  public void doCalcularMpreliminar() { // Abre 3ra pantalla
      logger.info(">>doCalcularMpreliminar");
      BigDecimal bdMPrelimUIT;
      if(this.getObjFiltroBean().getVcMetodo().equals("1")){ //PREESTABLECIDO

      this.getObjFiltroBean().setNuMultaFinal(0);
      this.getObjFiltroBean().setNuIngresosTope(0);
      this.getObjFiltroBean().setNuFacturacionAnual(0);

        bdMPrelimUIT = new BigDecimal(this.getObjFiltroBean().getNuMultaBaseCompleto() * this.getObjFiltroBean().getNuValorFactorAA()).setScale(2, RoundingMode.HALF_UP);
        logger.debug("multa preliminar: "+ bdMPrelimUIT.doubleValue());

      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), bdMPrelimUIT.doubleValue(), this.getObjFiltroBean().getVcOrgResolutivo());
      if (objResult != null) {
        this.getObjFiltroBean().setNuMultaPreUIT(bdMPrelimUIT.doubleValue());
        this.getObjFiltroBean().setVcGravedadMulta(objResult.get("GET_GRAVEDAD")+"");
        this.getObjFiltroBean().setNuGravedadTope((Double) objResult.get("GET_GRAVEDAD_TOPE"));


        ClsTamanioEmpresaBean obj = this.lstTamanoEmpresaBean.stream()
                                    .filter(p -> this.getObjFiltroBean().getVcTamEmpresaSeleccion().equals(p.getVcIdTamanioEmpresa()))
                                    .findAny()
                                    .orElse(null);
        if(obj != null){
          logger.debug("obj.getVcTamanioEmpresa(): " + obj.getVcTamanioEmpresa());
          logger.debug("obj.getNuMinUIT(): " + obj.getNuMinUIT());
          logger.debug("obj.getNuMaxUIT(): " + obj.getNuMaxUIT());
          logger.debug("obj.getNuPorcTope(): " + obj.getNuPorcTope());

          this.getObjFiltroBean().setNuPorcTope((Double) obj.getNuPorcTope());
          this.getObjFiltroBean().setNuMaxUIT(obj.getNuMaxUIT());
          this.getObjFiltroBean().setNuMinUIT(obj.getNuMinUIT());


        /* double nuAuxIngTope = getTopeXIngresos(this.getObjFiltroBean().getNuPorcTope(), this.getObjFiltroBean().getNuValorUIT());
        this.getObjFiltroBean().setNuIngresosTope(round(nuAuxIngTope, 2));
      
        List<Double> list = new ArrayList<>();
  
        // add element in ArrayList object list
        list.add(this.getObjFiltroBean().getNuMultaPreUIT());
        list.add(this.getObjFiltroBean().getNuGravedadTope());
        list.add(nuAuxIngTope);

        logger.debug("Min: " + findMin(list));    
        
        logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());
        this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
        logger.debug("this.getObjFiltroBean().getNuMultaFinal(): "+ this.getObjFiltroBean().getNuMultaFinal());
        this.getObjFiltroBean().setNuMultaFinalSoles(round(findMin(list)*this.getObjFiltroBean().getNuAnioUIT(), 2)); */

        }

      }

      } else if(this.getObjFiltroBean().getVcMetodo().equals("2")){ // LIBRO RECLAM
      
        BigDecimal nuMultaAA = new BigDecimal(this.getObjFiltroBean().getNuMultaRefLibro() * this.getObjFiltroBean().getNuFactorFci() * this.getObjFiltroBean().getNuValorFactorAA()).setScale(2, RoundingMode.HALF_UP);
        this.getObjFiltroBean().setNuMultaAALibro(nuMultaAA.doubleValue());
        double nuAjusIng = getTopeXIngresos(this.getObjFiltroBean().getNuPorcTopeLibro(), this.getObjFiltroBean().getNuValorUIT());
        this.getObjFiltroBean().setNuAjusIngLibro(round(nuAjusIng, 2));

        /* ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), nuMultaAA.doubleValue());
        if (objResult != null) {
          this.getObjFiltroBean().setVcGravedadMulta(objResult.get("GET_GRAVEDAD")+"");
        } */
        List<Double> list = new ArrayList<>();
  
        // add element in ArrayList object list
        list.add(this.getObjFiltroBean().getNuMultaAALibro());
        list.add(this.getObjFiltroBean().getNuAjusIngLibro());
        logger.debug("Min: " + findMin(list));    
        logger.debug("this.getObjFiltroBean().getNuAnioUIT(): " + this.getObjFiltroBean().getNuAnioUIT()); 
        this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
        this.getObjFiltroBean().setNuMultaFinalSoles(round((double) findMin(list)*(double) this.getObjFiltroBean().getNuAnioUIT(), 2));

      } else if(this.getObjFiltroBean().getVcMetodo().equals("5")){ // BARRERAS
      
        BigDecimal nuMultaAA = new BigDecimal(this.getObjFiltroBean().getNuMultaBaseBarreraCompleto() * this.getObjFiltroBean().getNuValorFactorAA()).setScale(2, RoundingMode.HALF_UP);
        this.getObjFiltroBean().setNuMultaAABarrera(nuMultaAA.doubleValue());

        if(this.getObjFiltroBean().getNuMultaAABarrera() < this.getObjFiltroBean().getNuAjusteMinBarrera()){
          this.getObjFiltroBean().setNuMultaAjustadaBarrera(this.getObjFiltroBean().getNuAjusteMinBarrera());
        }else{
          List<Double> list = new ArrayList<>();
          // add element in ArrayList object list
          list.add(this.getObjFiltroBean().getNuMultaAABarrera());
          list.add(this.getObjFiltroBean().getNuAjusteMaxBarrera());
          logger.debug("Min: " + findMin(list));  
          this.getObjFiltroBean().setNuMultaAjustadaBarrera(findMin(list));
        }

        this.getObjFiltroBean().setNuAjusteIngBarrera(round(0.3*this.getObjFiltroBean().getNuValorUIT(),2));
        List<Double> list2 = new ArrayList<>();
        // add element in ArrayList object list
        list2.add(this.getObjFiltroBean().getNuMultaAjustadaBarrera());
        list2.add(0.3*this.getObjFiltroBean().getNuValorUIT());
        logger.debug("Min: " + findMin(list2)); 
           
        this.getObjFiltroBean().setNuMultaFinal(round(findMin(list2), 2));
        logger.debug("this.getObjFiltroBean().getNuAnioUIT(): " + this.getObjFiltroBean().getNuAnioUIT()); 
        this.getObjFiltroBean().setNuMultaFinalSoles(round((double) findMin(list2)*(double) this.getObjFiltroBean().getNuAnioUIT(), 2));

      } else if(this.getObjFiltroBean().getVcMetodo().equals("6")){ //PREESTABLECIDO PI
        //bdMPrelimUIT = new BigDecimal(round(this.getObjFiltroBean().getNuMultaBasePi(),2) * round(this.getObjFiltroBean().getNuValorFactorAA(),2)).setScale(2, RoundingMode.HALF_UP);
        //logger.debug(">>multa preliminar: "+ bdMPrelimUIT.doubleValue());

        /* ClsTamanioEmpresaBean obj = this.lstTamanoEmpresaBean.stream()
                                    .filter(p -> this.getObjFiltroBean().getVcTamEmpresaSeleccionCcd().equals(p.getVcIdTamanioEmpresa()))
                                    .findAny()
                                    .orElse(null);
        if(obj != null){
          logger.debug("obj.getVcTamanioEmpresa(): " + obj.getVcTamanioEmpresa());
          logger.debug("obj.getNuMinUIT(): " + obj.getNuMinUIT());
          logger.debug("obj.getNuMaxUIT(): " + obj.getNuMaxUIT());
          logger.debug("obj.getNuPorcTope(): " + obj.getNuPorcTope());

          this.getObjFiltroBean().setNuPorcTope((Double) obj.getNuPorcTope());
          this.getObjFiltroBean().setNuMaxUIT(obj.getNuMaxUIT());
          this.getObjFiltroBean().setNuMinUIT(obj.getNuMinUIT());
        } */

        this.getObjFiltroBean().setNuMultaPreUITPi(round(this.getObjFiltroBean().getNuMultaBasePiCompleto() * this.getObjFiltroBean().getNuValorFactorAA(),2));
        
      } else if(this.getObjFiltroBean().getVcMetodo().equals("7")){ //PREESTABLECIDO CCD
        bdMPrelimUIT = new BigDecimal(this.getObjFiltroBean().getNuMultaBaseCcdCompleto() * this.getObjFiltroBean().getNuValorFactorAA()).setScale(2, RoundingMode.HALF_UP);
        logger.debug("multa preliminar: "+ bdMPrelimUIT.doubleValue());

        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), bdMPrelimUIT.doubleValue(), this.getObjFiltroBean().getVcOrgResolutivo());
        if (objResult != null) {
        this.getObjFiltroBean().setNuMultaPreUIT(bdMPrelimUIT.doubleValue());
        this.getObjFiltroBean().setVcGravedadMulta(objResult.get("GET_GRAVEDAD")+"");
        this.getObjFiltroBean().setNuGravedadTope((Double) objResult.get("GET_GRAVEDAD_TOPE"));
        this.getObjFiltroBean().setNuPorcVtasCcd((Double) objResult.get("GET_GRAVEDAD_PORC_VTAS"));

        logger.debug("this.getObjFiltroBean().getNuPorcTope(): "+ this.getObjFiltroBean().getNuPorcTope());
        logger.debug("this.getObjFiltroBean().getNuPorcVtasCcd(): "+ this.getObjFiltroBean().getNuPorcVtasCcd());

        ClsTamanioEmpresaBean obj = this.lstTamanoEmpresaBean.stream()
                                    .filter(p -> this.getObjFiltroBean().getVcTamEmpresaSeleccionCcd().equals(p.getVcIdTamanioEmpresa()))
                                    .findAny()
                                    .orElse(null);
        if(obj != null){
          logger.debug("obj.getVcTamanioEmpresa(): " + obj.getVcTamanioEmpresa());
          logger.debug("obj.getNuMinUIT(): " + obj.getNuMinUIT());
          logger.debug("obj.getNuMaxUIT(): " + obj.getNuMaxUIT());
          logger.debug("obj.getNuPorcTope(): " + obj.getNuPorcTope());

          this.getObjFiltroBean().setNuPorcTope((Double) obj.getNuPorcTope());
          this.getObjFiltroBean().setNuMaxUIT(obj.getNuMaxUIT());
          this.getObjFiltroBean().setNuMinUIT(obj.getNuMinUIT());
        }

        /* double nuAuxIngTope = getTopeXIngresos(this.getObjFiltroBean().getNuPorcVtasCcd(), this.getObjFiltroBean().getNuValorUIT());
        this.getObjFiltroBean().setNuIngresosTope(round(nuAuxIngTope, 2));
      
        List<Double> list = new ArrayList<>();
  
        // add element in ArrayList object list
        list.add(this.getObjFiltroBean().getNuMultaPreUIT());
        list.add(this.getObjFiltroBean().getNuGravedadTope());
        list.add(nuAuxIngTope);

        logger.debug("Min: " + findMin(list));    
        this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
        this.getObjFiltroBean().setNuMultaFinalSoles(round(findMin(list)*this.getObjFiltroBean().getNuAnioUIT(), 2)); */

        }
        
      } else if(this.getObjFiltroBean().getVcMetodo().equals("8")){ // PREESTABLECIDO FIRMA
      
        BigDecimal nuMultaAA = new BigDecimal(this.getObjFiltroBean().getNuMultaBaseFirmaCompleto() * this.getObjFiltroBean().getNuValorFactorAA()).setScale(2, RoundingMode.HALF_UP);
        this.getObjFiltroBean().setNuMultaAAFirma(nuMultaAA.doubleValue());
        this.getObjFiltroBean().setNuTopeLegalFirma(50);

        
        List<Double> list = new ArrayList<>();
  
        // add element in ArrayList object list
        list.add(this.getObjFiltroBean().getNuMultaAAFirma());
        list.add(this.getObjFiltroBean().getNuTopeLegalFirma());
        logger.debug("Min: " + findMin(list));    
        logger.debug("this.getObjFiltroBean().getNuAnioUIT(): " + this.getObjFiltroBean().getNuAnioUIT()); 
        this.getObjFiltroBean().setNuMultaFinal(round(findMin(list), 2));
        this.getObjFiltroBean().setNuMultaFinalSoles(round((double) findMin(list)*(double) this.getObjFiltroBean().getNuAnioUIT(), 2));

      } else if(this.getObjFiltroBean().getVcMetodo().equals("3")){ // %VENTAS
      
        BigDecimal nuMultaAA = new BigDecimal(this.getObjFiltroBean().getNuMultaBaseUITVentasCompleto() * this.getObjFiltroBean().getNuValorFactorAA()).setScale(2, RoundingMode.HALF_UP);
        this.getObjFiltroBean().setNuMultaAAVentas(nuMultaAA.doubleValue());
        //this.doListarGravedadTopeVentas();

        BigDecimal bdajingreso;
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = null;
        if(this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
          objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), nuMultaAA.doubleValue(), this.getObjFiltroBean().getVcOrgano1raInstanciaVentas());
        }else{
          objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), nuMultaAA.doubleValue(), this.getObjFiltroBean().getVcOrgResolutivo());
        }

        if (objResult != null) {
        this.getObjFiltroBean().setNuMultaPreUIT(nuMultaAA.doubleValue());
        this.getObjFiltroBean().setVcGravedadMulta(objResult.get("GET_GRAVEDAD")+"");
        this.getObjFiltroBean().setNuGravedadTope((Double) objResult.get("GET_GRAVEDAD_TOPE"));
        this.getObjFiltroBean().setNuPorcVtasCcd((Double) objResult.get("GET_GRAVEDAD_PORC_VTAS"));
        this.getObjFiltroBean().setVcGravedadTopeVentas((Double)objResult.get("GET_GRAVEDAD_TOPE") ==9999 ? "Mayor a 1000" : objResult.get("GET_GRAVEDAD_TOPE")+"");
        this.getObjFiltroBean().setNuGravedadTopeVentas((Double)objResult.get("GET_GRAVEDAD_TOPE"));

        logger.debug("this.getObjFiltroBean().getNuFacturacionAnualVentas(): "+ this.getObjFiltroBean().getNuFacturacionAnualVentas());
        logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());

        bdajingreso = new BigDecimal((Double)this.getObjFiltroBean().getNuPorcVtasCcd()*this.getObjFiltroBean().getNuFacturacionAnualVentas()/this.getObjFiltroBean().getNuAnioUIT());
        logger.debug("bdajingreso: "+ bdajingreso.doubleValue());
        this.getObjFiltroBean().setNuAjusteIngVentas(round(bdajingreso.doubleValue(), 2));

        List<Double> list2 = new ArrayList<>();
        list2.add(this.getObjFiltroBean().getNuMultaAAVentas());
        list2.add(this.getObjFiltroBean().getNuGravedadTopeVentas());
        list2.add(bdajingreso.doubleValue());
        logger.debug("Min: " + findMin(list2)); 

        this.getObjFiltroBean().setNuMultaFinal(round(findMin(list2), 2));
        this.getObjFiltroBean().setNuMultaFinalSoles(round((double) findMin(list2)*(double) this.getObjFiltroBean().getNuAnioUIT(), 2));
        }

      } else if(this.getObjFiltroBean().getVcMetodo().equals("4")){ // ADHOC
      
        ///BigDecimal nuMultaAA = new BigDecimal(this.getObjFiltroBean().getNuMultaBaseUITAdhocCompleto() * this.getObjFiltroBean().getNuValorFactorAA()).setScale(2, RoundingMode.HALF_UP);
        ///this.getObjFiltroBean().setNuMultaAAAdhoc(nuMultaAA.doubleValue());
        //this.doListarGravedadTopeVentas();

        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = null;
        if(this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
          ///objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), nuMultaAA.doubleValue(), "CLC");
          objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getNuMultaBaseAdhoc(), "CLC");

        }else if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CC1") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CC2") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CC3") || this.getObjFiltroBean().getVcOrgResolutivo().equals("SPC")){
          ///objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), nuMultaAA.doubleValue(), "CPC");
          objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getNuMultaBaseAdhoc(), "CPC");

        }else if(this.getObjFiltroBean().getVcOrgResolutivo().equals("SPI")){
          //objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), nuMultaAA.doubleValue(), this.getObjFiltroBean().getVcOrgano1raInstanciaAdhoc());//TO DO:quitar
objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getNuMultaBaseAdhoc(), this.getObjFiltroBean().getVcOrgano1raInstanciaAdhoc());//TO DO:quitar
        }else{
          //objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), nuMultaAA.doubleValue(), this.getObjFiltroBean().getVcOrgResolutivo());
          objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getNuMultaBaseAdhoc(), this.getObjFiltroBean().getVcOrgResolutivo());

        }

        BigDecimal bdajingreso;
        List<Double> list2 = new ArrayList<>();
        //ClsResultDAO objResult = objDAO.doGetGravedad(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), nuMultaAA.doubleValue(), this.getObjFiltroBean().getVcOrgResolutivo());
        if (objResult != null) {
        ///this.getObjFiltroBean().setNuMultaPreUIT(nuMultaAA.doubleValue());
        this.getObjFiltroBean().setNuMultaPreUIT(this.getObjFiltroBean().getNuMultaBaseAdhoc());
        this.getObjFiltroBean().setVcGravedadMulta(objResult.get("GET_GRAVEDAD")+"");
        this.getObjFiltroBean().setNuGravedadTope((Double) objResult.get("GET_GRAVEDAD_TOPE"));
        this.getObjFiltroBean().setNuPorcVtasCcd((Double) objResult.get("GET_GRAVEDAD_PORC_VTAS"));
        this.getObjFiltroBean().setVcGravedadTopeAdhoc((Double)objResult.get("GET_GRAVEDAD_TOPE") ==9999 ? "Mayor a 1000" : objResult.get("GET_GRAVEDAD_TOPE")+"");
        this.getObjFiltroBean().setNuGravedadTopeAdhoc((Double)objResult.get("GET_GRAVEDAD_TOPE"));

        if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CC1") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CC2") 
            || this.getObjFiltroBean().getVcOrgResolutivo().equals("CC3") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CPC")
            || this.getObjFiltroBean().getVcOrgResolutivo().equals("SPC")){
          //bdajingreso = new BigDecimal(this.getNuIdTamEmpresa()==1 ? 0.1 : 1 * this.getObjFiltroBean().getNuFacturacionAnualAdhoc() / this.getObjFiltroBean().getNuAnioUIT());
          bdajingreso = new BigDecimal(this.getObjFiltroBean().getNuPorcTope() * this.getObjFiltroBean().getNuFacturacionAnualAdhoc() / this.getObjFiltroBean().getNuAnioUIT());
          //OJO
          list2.add(this.getObjFiltroBean().getNuMultaAAAdhoc());
          list2.add(this.getObjFiltroBean().getNuGravedadTopeAdhoc());
          list2.add(bdajingreso.doubleValue());
          this.getObjFiltroBean().setNuAjusteIngAdhoc(round(bdajingreso.doubleValue(), 2));

        }else if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC") || this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
          logger.debug("this.getObjFiltroBean().getNuFacturacionAnualAdhoc(): "+ this.getObjFiltroBean().getNuFacturacionAnualAdhoc());
          logger.debug("this.getObjFiltroBean().getNuAnioUIT(): "+ this.getObjFiltroBean().getNuAnioUIT());
          //bdajingreso = new BigDecimal((Double)this.getObjFiltroBean().getNuPorcVtasCcd()*this.getObjFiltroBean().getNuFacturacionAnualAdhoc()/this.getObjFiltroBean().getNuAnioUIT());
          list2.add(this.getObjFiltroBean().getNuMultaAAAdhoc());
          list2.add(this.getObjFiltroBean().getNuGravedadTopeAdhoc());
          //list2.add(bdajingreso.doubleValue());
          //this.getObjFiltroBean().setNuAjusteIngAdhoc(round(bdajingreso.doubleValue(), 2));

        }else if(this.getObjFiltroBean().getVcOrgResolutivo().equals("DDA") || this.getObjFiltroBean().getVcOrgResolutivo().equals("DIN") || this.getObjFiltroBean().getVcOrgResolutivo().equals("DSD") 
                || this.getObjFiltroBean().getVcOrgResolutivo().equals("CDA") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CIN") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CSD") 
                || this.getObjFiltroBean().getVcOrgResolutivo().equals("SPI")){
          this.getObjFiltroBean().setNuAjusteIngAdhoc(this.getObjFiltroBean().getNuGravedadTopeAdhoc());
          list2.add(this.getObjFiltroBean().getNuMultaAAAdhoc());
          list2.add(this.getObjFiltroBean().getNuAjusteIngAdhoc());
        }

        /* logger.debug("bdajingreso: "+ bdajingreso.doubleValue());
        this.getObjFiltroBean().setNuAjusteIngAdhoc(round(bdajingreso.doubleValue(), 2));

        List<Double> list2 = new ArrayList<>();
        list2.add(this.getObjFiltroBean().getNuMultaAAAdhoc());
        list2.add(this.getObjFiltroBean().getNuGravedadTopeAdhoc());
        list2.add(bdajingreso.doubleValue()); */
        logger.debug("Min: " + findMin(list2)); 

        this.getObjFiltroBean().setNuMultaFinal(round(findMin(list2), 2));
        ///this.getObjFiltroBean().setNuMultaFinalSoles(round((double) findMin(list2)*(double) this.getObjFiltroBean().getNuAnioUIT(), 2));
        }

      }
      
    logger.info(">>FIN doCalcularMpreliminar");
  }

  public void doCalcularMbaseBarreras() { 
    logger.info(">>doCalcularMbaseBarreras");
    if(!this.getObjFiltroBean().getVcIdTipoBarrera().equals("-1") && !this.getObjFiltroBean().getVcRucBarreras().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("")&&!this.getObjFiltroBean().getVcRazonSocial().equals("RUC no encontrado")
          && this.getObjFiltroBean().getNuFacturacionAnualBarrera()!=0 && !this.getObjFiltroBean().getVcAnioResolucionBarrera().equals("-1") && !this.getObjFiltroBean().getVcTipoAfectacionBarrera().equals("") 
          && this.getObjFiltroBean().getNuFactorPBarrera()!=0 && !this.getObjFiltroBean().getVcIdTipoAlcanceBarrera().equals("-1") && !this.getObjFiltroBean().getVcIdFactPoblacionBarrera().equals("-1")){

    BigDecimal bdMPrelimUIT;
    bdMPrelimUIT = new BigDecimal(this.getObjFiltroBean().getNuMultaDBarrera() / this.getObjFiltroBean().getNuFactorPBarrera()).setScale(2, RoundingMode.HALF_UP);
    logger.debug("multa base barrera: "+ bdMPrelimUIT.doubleValue());
    this.getObjFiltroBean().setNuMultaBaseBarrera(bdMPrelimUIT.doubleValue());
    this.getObjFiltroBean().setNuMultaBaseBarreraCompleto(this.getObjFiltroBean().getNuMultaDBarrera() / this.getObjFiltroBean().getNuFactorPBarrera());
    this.getObjFiltroBean().setIsBlMultaBaseBarrera(true);
    }
    logger.info(">>FIN doCalcularMbaseBarreras");
  }
  public double getTopeXIngresos(double nuPorcTope, double nuUITFacturado) {
      logger.info(">>getTopeXIngresos()");
      double var;
      logger.debug("nuPorcTope(): "+ nuPorcTope);
      logger.debug("nuUITFacturado(): "+ nuUITFacturado);
      var = nuPorcTope * nuUITFacturado;
      return var;
      //return round(var,2);
  }

  public static double round(double value, int places) {
    logger.info(">>round(value, 2): " + value);
      if (places < 0) throw new IllegalArgumentException();

      BigDecimal bd = BigDecimal.valueOf(value);
      bd = bd.setScale(places, RoundingMode.HALF_UP);
      return bd.doubleValue();
  }
    
  //REFACTORING DEPRECATED
  public void doSeleccionarO(){
      logger.info(">>doSeleccionarO ");
      //this.doListarInstanciasXOrg();
      //this.doListarMetodosXOrgInstancia();
      logger.debug("this.getVcAreaSeleccion(): "+ this.getVcAreaSeleccion());
      logger.info(">>FIN doSeleccionarO");
  }
  /////////////////

  public void doListarMetodosXOrgInstancia() {
    logger.info(">>doListarMetodosXOrgInstancia");
    //logger.info(">>this.getObjFiltroBean().getVcInstancia(): " + this.getObjFiltroBean().getVcInstancia());
    logger.debug(">>this.getObjFiltroBean().getVcOrgResolutivo(): " + this.getObjFiltroBean().getVcOrgResolutivo());
    ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
    ClsResultDAO objResult = objDAO.doListarMetodos(this.getObjFiltroBean().getVcInstancia(), this.getObjFiltroBean().getVcOrgResolutivo());
    if (objResult != null) {
          //this.getObjFiltroBean().setLstMetodos((Map<String, String>) objResult.get("SP_LST_METODOS"));

        this.setLstMetodoCalculoBean((List<ClsMetodoCalculoBean>) objResult.get("SP_LST_METODOS"));
        logger.debug(">>this.lstMetodoCalculoBean.size(): " + this.lstMetodoCalculoBean.size());
        SelectItem data = new SelectItem();
        List<SelectItem> lstMetodoCal = new ArrayList<SelectItem>();

          /* if(this.lstMetodoCalculoBean.size() > 1){
            data.setValue(-1);
            data.setLabel("Seleccionar");
            lstMetodoCal.add(data);

            for (ClsMetodoCalculoBean objSerie : lstMetodoCalculoBean) {
              data = new SelectItem();
              data.setLabel("" + objSerie.getVcMetodoCalculo());
              data.setValue("" + objSerie.getNuIdMetodoCalculo());
              logger.debug(">>objSerie.getVcMetodoCalculo(): " + objSerie.getVcMetodoCalculo());
              logger.debug(">>objSerie.getNuIdMetodoCalculo(): " + objSerie.getNuIdMetodoCalculo());
              lstMetodoCal.add(data);
            }
            this.getObjFiltroBean().setLstMetodosCalculo(lstMetodoCal);

          }else if(this.lstMetodoCalculoBean.size() == 1){
            for (ClsMetodoCalculoBean objSerie : lstMetodoCalculoBean) {
              data = new SelectItem();
              data.setLabel("" + objSerie.getVcMetodoCalculo());
              data.setValue("" + objSerie.getNuIdMetodoCalculo());
              logger.debug(">>objSerie.getVcMetodoCalculo(): " + objSerie.getVcMetodoCalculo());
              logger.debug(">>objSerie.getNuIdMetodoCalculo(): " + objSerie.getNuIdMetodoCalculo());
              lstMetodoCal.add(data);
            }
            this.getObjFiltroBean().setLstMetodosCalculo(lstMetodoCal);
            this.getObjFiltroBean().setVcMetodo(String.valueOf(data.getValue()));
            this.doSeleccionarMetodo();
          }else{
            for (ClsMetodoCalculoBean objSerie : lstMetodoCalculoBean) {
              data = new SelectItem();
              data.setLabel("" + objSerie.getVcMetodoCalculo());
              data.setValue("" + objSerie.getNuIdMetodoCalculo());
              logger.debug(">>objSerie.getVcMetodoCalculo(): " + objSerie.getVcMetodoCalculo());
              logger.debug(">>objSerie.getNuIdMetodoCalculo(): " + objSerie.getNuIdMetodoCalculo());
              lstMetodoCal.add(data);
            }
            this.getObjFiltroBean().setLstMetodosCalculo(lstMetodoCal);
            this.getObjFiltroBean().setVcMetodo(String.valueOf(data.getValue()));
          } */
            data.setValue(-1);
            data.setLabel("Seleccionar");
            lstMetodoCal.add(data);

            for (ClsMetodoCalculoBean objSerie : lstMetodoCalculoBean) {
              data = new SelectItem();
              data.setLabel("" + objSerie.getVcMetodoCalculo());
              data.setValue("" + objSerie.getNuIdMetodoCalculo());
              logger.debug(">>objSerie.getVcMetodoCalculo(): " + objSerie.getVcMetodoCalculo());
              logger.debug(">>objSerie.getNuIdMetodoCalculo(): " + objSerie.getNuIdMetodoCalculo());
              lstMetodoCal.add(data);
            }
            this.getObjFiltroBean().setLstMetodosCalculo(lstMetodoCal);

    }else{
      SelectItem data = new SelectItem();
      List<SelectItem> lstMetodoCal = new ArrayList<SelectItem>();
      data.setValue(-1);
      data.setLabel("Seleccionar Area");
      lstMetodoCal.add(data);
      this.getObjFiltroBean().setLstMetodosCalculo(lstMetodoCal);
    }
    logger.info(">>FIN doListarMetodosXOrgInstancia");
  }

public void doLimpiarRUC() {
    logger.info(">>doLimpiarRUC");
    /* this.getObjFiltroBean().setVcRuc("");
    this.getObjFiltroBean().setVcRazonSocial("");
    this.getObjFiltroBean().setVcRucLibro("");
    this.getObjFiltroBean().setVcRucBarreras("");
    this.getObjFiltroBean().setVcRucPi("");
    this.getObjFiltroBean().setVcRucCcd("");
    this.getObjFiltroBean().setVcRucFirma("");
    this.getObjFiltroBean().setVcRucVentas("");
    this.getObjFiltroBean().setVcRucAdhoc(""); */
    
    this.getObjFiltroBean().setVcTamEmpresa("");
    this.getObjFiltroBean().setVcTipoAfectacion("");
    this.getObjFiltroBean().setVcTipoAfectacionLibro("");
    this.getObjFiltroBean().setVcTipoAfectacionBarrera("");
    this.getObjFiltroBean().setVcTipoAfectacionPi("");
    this.getObjFiltroBean().setVcTipoAfectacionCcd("");
    this.getObjFiltroBean().setVcTipoAfectacionFirma("");
    this.getObjFiltroBean().setVcTipoAfectacionVentas("");
    this.getObjFiltroBean().setVcTipoAfectacionAdhoc("");
    this.getObjFiltroBean().setVcNivelAfectacion("");
    this.getObjFiltroBean().setVcNivelAfectacionPi("");
    this.getObjFiltroBean().setVcNivelAfectacionCcd("");
    this.getObjFiltroBean().setVcNivelAfectacionFirma("");
    this.getObjFiltroBean().setVcNivelAfectacionVentas("");
    this.getObjFiltroBean().setVcNivelAfectacionAdhoc("");

    this.getObjFiltroBean().setVcInfraccion("-1");
    this.getObjFiltroBean().setVcInfraccionLibro("-1");
    this.getObjFiltroBean().setVcInfraccionBarrera("-1");
    this.getObjFiltroBean().setVcInfraccionPi("-1");
    this.getObjFiltroBean().setVcInfraccionCcd("-1");
    this.getObjFiltroBean().setVcInfraccionFirma("-1");
    this.getObjFiltroBean().setVcInfraccionVentas("-1");
    this.getObjFiltroBean().setVcInfraccionAdhoc("-1");

    this.getObjFiltroBean().setVcClasificacion("");
    this.getObjFiltroBean().setVcClasificacionBarrera("");

    /* this.getObjFiltroBean().setVcInstancia("-1");
    this.getObjFiltroBean().setVcInstanciaPi("-1");
    this.getObjFiltroBean().setVcInstanciaCcd("-1");
    this.getObjFiltroBean().setVcInstanciaVentas("-1");
    this.getObjFiltroBean().setVcInstanciaAdhoc("-1"); */
    
    this.getObjFiltroBean().setVcAnioResolucion("-1");
    this.getObjFiltroBean().setVcAnioResolucionLibro("-1");
    this.getObjFiltroBean().setVcAnioResolucionBarrera("-1");
    this.getObjFiltroBean().setVcAnioResolucionPi("-1");
    this.getObjFiltroBean().setVcAnioResolucionCcd("-1");
    this.getObjFiltroBean().setVcAnioResolucionFirma("-1");
    this.getObjFiltroBean().setVcAnioResolucionVentas("-1");
    this.getObjFiltroBean().setVcAnioResolucionAdhoc("-1");
    
    this.getObjFiltroBean().setVcIdTipoServicioFirma("-1");

    this.getObjFiltroBean().setNuFacturacionAnual(0);
    this.getObjFiltroBean().setNuFacturacionAnualLibro(0);
    this.getObjFiltroBean().setNuFacturacionAnualBarrera(0);
    this.getObjFiltroBean().setNuFacturacionAnualPi(0);
    this.getObjFiltroBean().setNuFacturacionAnualCcd(0);
    this.getObjFiltroBean().setNuFacturacionAnualFirma(0);
    this.getObjFiltroBean().setNuFacturacionAnualVentas(0);
    this.getObjFiltroBean().setNuFacturacionAnualProductoVentas(0);
    this.getObjFiltroBean().setNuFacturacionAnualAdhoc(0);

    this.getObjFiltroBean().setNuValorUIT(0);
    this.getObjFiltroBean().setNuAnioUIT(0);
    //
    this.getObjFiltroBean().setNuFactorAVentas(0);
    this.getObjFiltroBean().setNuFactorGVentas(0);
    this.getObjFiltroBean().setNuFactorBAdhoc(0);
    this.getObjFiltroBean().setNuFactorPAdhoc(0);
    this.getObjFiltroBean().setVcFactorPAdhoc("");
    this.getObjFiltroBean().setNuMultaRefLibro(0);
    this.getObjFiltroBean().setNuFactorAi(0);
    this.getObjFiltroBean().setNuFactorAo(0);
    this.getObjFiltroBean().setNuFactorFco(0);
    this.getObjFiltroBean().setNuFactorFci(0);
    this.getObjFiltroBean().setNuValorMatriz(0);

    this.getObjFiltroBean().setNuMultaBase(0);
    this.getObjFiltroBean().setNuMultaBaseCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseLibro(0);
    this.getObjFiltroBean().setNuMultaBaseLibroCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseBarrera(0);
    this.getObjFiltroBean().setNuMultaBaseBarreraCompleto(0);
    this.getObjFiltroBean().setNuMultaBasePi(0);
    this.getObjFiltroBean().setNuMultaBasePiCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseCcd(0);
    this.getObjFiltroBean().setNuMultaBaseCcdCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseFirma(0);
    this.getObjFiltroBean().setNuMultaBaseFirmaCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseVentas(0);
    this.getObjFiltroBean().setNuMultaBaseVentasCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseUITVentas(0);
    this.getObjFiltroBean().setNuMultaBaseAdhoc(0);
    this.getObjFiltroBean().setNuMultaBaseAdhocCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseUITAdhoc(0);
    this.getObjFiltroBean().setNuDanioBaseBarrera(0);
    this.getObjFiltroBean().setNuDanioBaseFirma(0);
    this.getObjFiltroBean().setNuFactorFirma(0);
    this.getObjFiltroBean().setNuFactorPFirma(0);

    //
    this.getObjFiltroBean().setIsBlMultaBase(false);
    this.getObjFiltroBean().setIsBlMultaBaseLibro(false);
    this.getObjFiltroBean().setIsBlMultaBaseBarrera(false);
    this.getObjFiltroBean().setIsBlMultaBasePi(false);
    this.getObjFiltroBean().setIsBlMultaBaseCcd(false);
    this.getObjFiltroBean().setIsBlMultaBaseVentas(false);
    this.getObjFiltroBean().setIsBlMultaBaseAdhoc(false);
    this.getObjFiltroBean().setIsBlMultaBaseFirma(false);

    this.getObjFiltroBean().setVcIdTipoAlcanceBarrera("-1");
    this.getObjFiltroBean().setVcIdFactPoblacionBarrera("-1");
    this.getObjFiltroBean().setVcIdFactSecEconBarrera("-1");
    this.getObjFiltroBean().setNuAlcanceBarrera(0);
    this.getObjFiltroBean().setNuMultaDBarrera(0);
    //
    this.getObjFiltroBean().setVcIdTipoBarrera("-1");
    this.getObjFiltroBean().setVcCaso("-1");
    this.getObjFiltroBean().setNuFactorPBarrera(0);


    logger.info(">>FIN doLimpiarRUC");
  }

  public void doLimpiar() {
    logger.info(">>doLimpiar");
    this.getObjFiltroBean().setVcRuc("");
    this.getObjFiltroBean().setVcRazonSocial("");
    this.getObjFiltroBean().setVcRucLibro("");
    this.getObjFiltroBean().setVcRucBarreras("");
    this.getObjFiltroBean().setVcRucPi("");
    this.getObjFiltroBean().setVcRucCcd("");
    this.getObjFiltroBean().setVcRucFirma("");
    this.getObjFiltroBean().setVcRucVentas("");
    this.getObjFiltroBean().setVcRucAdhoc("");
    
    this.getObjFiltroBean().setVcTamEmpresa("");
    this.getObjFiltroBean().setVcTipoAfectacion("");
    this.getObjFiltroBean().setVcTipoAfectacionLibro("");
    this.getObjFiltroBean().setVcTipoAfectacionBarrera("");
    this.getObjFiltroBean().setVcTipoAfectacionPi("");
    this.getObjFiltroBean().setVcTipoAfectacionCcd("");
    this.getObjFiltroBean().setVcTipoAfectacionFirma("");
    this.getObjFiltroBean().setVcTipoAfectacionVentas("");
    this.getObjFiltroBean().setVcTipoAfectacionAdhoc("");
    this.getObjFiltroBean().setVcNivelAfectacion("");
    this.getObjFiltroBean().setVcNivelAfectacionPi("");
    this.getObjFiltroBean().setVcNivelAfectacionCcd("");
    this.getObjFiltroBean().setVcNivelAfectacionFirma("");
    this.getObjFiltroBean().setVcNivelAfectacionVentas("");
    this.getObjFiltroBean().setVcNivelAfectacionAdhoc("");

    this.getObjFiltroBean().setVcInfraccion("-1");
    this.getObjFiltroBean().setVcInfraccionLibro("-1");
    this.getObjFiltroBean().setVcInfraccionBarrera("-1");
    this.getObjFiltroBean().setVcInfraccionPi("-1");
    this.getObjFiltroBean().setVcInfraccionCcd("-1");
    this.getObjFiltroBean().setVcInfraccionFirma("-1");
    this.getObjFiltroBean().setVcInfraccionVentas("-1");
    this.getObjFiltroBean().setVcInfraccionAdhoc("-1");

    this.getObjFiltroBean().setVcClasificacion("");
    this.getObjFiltroBean().setVcClasificacionBarrera("");

    this.getObjFiltroBean().setVcInstancia("-1");
    this.getObjFiltroBean().setVcInstanciaPi("-1");
    this.getObjFiltroBean().setVcInstanciaCcd("-1");
    this.getObjFiltroBean().setVcInstanciaVentas("-1");
    this.getObjFiltroBean().setVcInstanciaAdhoc("-1");
    
    this.getObjFiltroBean().setVcAnioResolucion("-1");
    this.getObjFiltroBean().setVcAnioResolucionLibro("-1");
    this.getObjFiltroBean().setVcAnioResolucionBarrera("-1");
    this.getObjFiltroBean().setVcAnioResolucionPi("-1");
    this.getObjFiltroBean().setVcAnioResolucionCcd("-1");
    this.getObjFiltroBean().setVcAnioResolucionFirma("-1");
    this.getObjFiltroBean().setVcAnioResolucionVentas("-1");
    this.getObjFiltroBean().setVcAnioResolucionAdhoc("-1");
    
    this.getObjFiltroBean().setVcIdTipoServicioFirma("-1");

    this.getObjFiltroBean().setNuFacturacionAnual(0);
    this.getObjFiltroBean().setNuFacturacionAnualLibro(0);
    this.getObjFiltroBean().setNuFacturacionAnualBarrera(0);
    this.getObjFiltroBean().setNuFacturacionAnualPi(0);
    this.getObjFiltroBean().setNuFacturacionAnualCcd(0);
    this.getObjFiltroBean().setNuFacturacionAnualFirma(0);
    this.getObjFiltroBean().setNuFacturacionAnualVentas(0);
    this.getObjFiltroBean().setNuFacturacionAnualProductoVentas(0);
    this.getObjFiltroBean().setNuFacturacionAnualAdhoc(0);

    this.getObjFiltroBean().setNuValorUIT(0);
    this.getObjFiltroBean().setNuAnioUIT(0);
    //
    this.getObjFiltroBean().setNuFactorAVentas(0);
    this.getObjFiltroBean().setNuFactorGVentas(0);
    this.getObjFiltroBean().setNuFactorBAdhoc(0);
    this.getObjFiltroBean().setNuFactorPAdhoc(0);
    this.getObjFiltroBean().setVcFactorPAdhoc("");
    this.getObjFiltroBean().setNuMultaRefLibro(0);
    this.getObjFiltroBean().setNuFactorAi(0);
    this.getObjFiltroBean().setNuFactorAo(0);
    this.getObjFiltroBean().setNuFactorFco(0);
    this.getObjFiltroBean().setNuFactorFci(0);
    this.getObjFiltroBean().setNuValorMatriz(0);

    this.getObjFiltroBean().setNuMultaBase(0);
    this.getObjFiltroBean().setNuMultaBaseCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseLibro(0);
    this.getObjFiltroBean().setNuMultaBaseLibroCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseBarrera(0);
    this.getObjFiltroBean().setNuMultaBaseBarreraCompleto(0);
    this.getObjFiltroBean().setNuMultaBasePi(0);
    this.getObjFiltroBean().setNuMultaBasePiCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseCcd(0);
    this.getObjFiltroBean().setNuMultaBaseCcdCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseFirma(0);
    this.getObjFiltroBean().setNuMultaBaseFirmaCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseVentas(0);
    this.getObjFiltroBean().setNuMultaBaseVentasCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseUITVentas(0);
    this.getObjFiltroBean().setNuMultaBaseAdhoc(0);
    this.getObjFiltroBean().setNuMultaBaseAdhocCompleto(0);
    this.getObjFiltroBean().setNuMultaBaseUITAdhoc(0);
    this.getObjFiltroBean().setNuDanioBaseBarrera(0);
    this.getObjFiltroBean().setNuDanioBaseFirma(0);
    this.getObjFiltroBean().setNuFactorFirma(0);
    this.getObjFiltroBean().setNuFactorPFirma(0);

    this.getObjFiltroBean().setNuFactorPDifAdhoc(0);
    this.getObjFiltroBean().setVcFactorPDifAccion("0");
    this.getObjFiltroBean().setNuFactorGDifVentas(0);
    this.getObjFiltroBean().setVcFactorGDifAccion("0");

    this.getObjFiltroBean().setNuIngresosTope(0);
    this.getObjFiltroBean().setNuMultaFinal(0);
    this.getObjFiltroBean().setNuGravedadTopePi(0);
    //
    this.getObjFiltroBean().setIsBlMultaBase(false);
    this.getObjFiltroBean().setIsBlMultaBaseLibro(false);
    this.getObjFiltroBean().setIsBlMultaBaseBarrera(false);
    this.getObjFiltroBean().setIsBlMultaBasePi(false);
    this.getObjFiltroBean().setIsBlMultaBaseCcd(false);
    this.getObjFiltroBean().setIsBlMultaBaseVentas(false);
    this.getObjFiltroBean().setIsBlMultaBaseAdhoc(false);
    this.getObjFiltroBean().setIsBlMultaBaseFirma(false);

    //
    this.setLstAfectacionBean(null);// manejar con IsBlLstAfectacion
    this.getObjFiltroBean().setIsBlLstAfectacion(false);

    this.getObjFiltroBean().setIsBlCheckUIT(false);
    this.getObjFiltroBean().setIsBlCheckUITCcd(false);
    this.getObjFiltroBean().setIsBlCheckUITAdhoc(false);
    this.getObjFiltroBean().setVcTamEmpresaSeleccion("-1");
    this.getObjFiltroBean().setVcTamEmpresaSeleccionCcd("-1");
    this.getObjFiltroBean().setVcTamEmpresaSeleccionPi("-1");
    logger.info(">>FIN doLimpiar");
  }

  public void doSeleccionarMetodo() {
    logger.info(">>doSeleccionarMetodo(): "+ this.getObjFiltroBean().getVcMetodo());
    this.doLimpiar();
    
    if(this.getObjFiltroBean().getVcMetodo().equals(ClsConstantes.VC_ID_METODO_PREESTABLECIDO)){ //PREESTABLECIDO
      this.doListarInstanciasXOrg();
      this.calculaFD();
      this.doListarTamanoEmpresa();
      //this.doListarInfraccionesXOrgInstancia(); //se cargara al seleccionar INSTANCIA
      this.setVcMuestraPreestablecido(" block;");
      this.setVcMuestraLibro(" none;");
      this.setVcMuestraBarreras(" none;");
      this.setVcMuestraPreestablecidoCcd(" none;");
      this.setVcMuestraPreestablecidoPi(" none;");
      this.setVcMuestraPreestablecidoFirma(" none;");
      this.setVcMuestraPorcentajeVentas(" none;");
      this.setVcMuestraAdHoc(" none;");
      
      this.setVcMuestraAtenuanteF8(" block;");
      if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CC3")){
        this.setVcMuestraAtenuanteF10(" block;");
      }
      

    }else if(this.getObjFiltroBean().getVcMetodo().equals("2")){ //LIBRO RECLAM
      this.doListarInfraccionesXOrgInstancia();
      this.setVcMuestraLibro(" block;");
      this.setVcMuestraPreestablecido(" none;");
      this.setVcMuestraBarreras(" none;");
      this.setVcMuestraPreestablecidoCcd(" none;");
      this.setVcMuestraPreestablecidoPi(" none;");
      this.setVcMuestraPreestablecidoFirma(" none;");
      this.setVcMuestraPorcentajeVentas(" none;");
      this.setVcMuestraAdHoc(" none;");
      
      this.setVcMuestraAtenuanteF8(" block;");
      if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CC3")){
        this.setVcMuestraAtenuanteF10(" block;");
      }

    }else if(this.getObjFiltroBean().getVcMetodo().equals("3")){ //%VENTAS
      this.doListarInstanciasXOrg();
      this.doListarInfraccionesXOrgInstancia(); //INFRACCIONES CCD Y CLC SON IGUALES

    this.setVcMuestraFactorGVariacion(" none;");

      if(!this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetValorDescripcion(248, this.getObjFiltroBean().getVcOrgResolutivo(), 0);
        if (objResult != null) {
            this.getObjFiltroBean().setNuUmbralFactorAVentas(Double.parseDouble((String) objResult.get("GET_VALOR_PARAMETRO")));
            logger.info("----------->>this.getObjFiltroBean().getNuUmbralFactorAVentas(): "+ this.getObjFiltroBean().getNuUmbralFactorAVentas());
        }
      }
      if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CCD")){
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetValorDescripcion(253, this.getObjFiltroBean().getVcOrgResolutivo(), 0);
        if (objResult != null) {
            this.getObjFiltroBean().setNuUmbralFactorGDifVentas(Double.parseDouble((String) objResult.get("GET_VALOR_PARAMETRO")));
            logger.info("----------->>this.getObjFiltroBean().getNuUmbralFactorGDifVentas(): "+ this.getObjFiltroBean().getNuUmbralFactorGDifVentas());
        }
      }
      if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CCD")
      || this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
        this.setVcMuestraFactorGVariacion(" block;");
      }

      /*if(this.getObjFiltroBean().getVcInstanciaVentas().equals("2") && this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
        this.doListarOrganosMetodo(this.getObjFiltroBean().getVcMetodo(), Integer.parseInt(this.getObjFiltroBean().getVcInstanciaVentas()));
        this.setVcMuestraOrgano1raInstanciaVentas(" block;");
      }else{
        this.setVcMuestraOrgano1raInstanciaVentas(" none;");
      }*/
      this.doSeleccionarInstanciaVentas();

      this.setVcMuestraBarreras(" none;");
      this.setVcMuestraPreestablecido(" none;");
      this.setVcMuestraLibro(" none;");
      this.setVcMuestraPreestablecidoCcd(" none;");
      this.setVcMuestraPreestablecidoPi(" none;");
      this.setVcMuestraPreestablecidoFirma(" none;");
      this.setVcMuestraPorcentajeVentas(" block;");
      this.setVcMuestraAdHoc(" none;");

      if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC")){
        this.setVcMuestraCLCVentas(" block;");
      }else{
        this.setVcMuestraCLCVentas(" none;");
      }

    }else if(this.getObjFiltroBean().getVcMetodo().equals("4")){ //ADHOC
    this.setVcMuestraFactorPVariacion(" none;");
    if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CC1") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CC2") 
    || this.getObjFiltroBean().getVcOrgResolutivo().equals("CC3") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CPC")
    || this.getObjFiltroBean().getVcOrgResolutivo().equals("SPC")){
      this.getObjFiltroBean().setVcCategoriaAdhoc("Consumidor");
      this.setVcMuestraTamEmpresaAdHoc(" none;");//era block
      this.setVcMuestraGravedad1AdHoc(" block;");
      this.setVcMuestraGravedad2AdHoc(" none;");
      this.setVcMuestraTopelegalNoPIAdHoc(" block;");
      this.doListarTamEmpresaAdhoc();
      this.setVcMuestraAtenuanteF8(" block;");
      if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CC3")){
        this.setVcMuestraAtenuanteF10(" block;");
      }

    }else if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC") || this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetValorDescripcion(251, "CLC", 0);
      if (objResult != null) {
          this.getObjFiltroBean().setNuUmbralFactorPDifAdhoc(Double.parseDouble((String) objResult.get("GET_VALOR_PARAMETRO")));
          logger.info("----------->>this.getObjFiltroBean().getNuUmbralFactorPDifAdhoc(): "+ this.getObjFiltroBean().getNuUmbralFactorPDifAdhoc());
      }
      
      this.setVcMuestraFactorPVariacion(" block;");

      this.getObjFiltroBean().setVcCategoriaAdhoc("CLC");
      this.setVcMuestraTamEmpresaAdHoc(" none;");
      this.setVcMuestraGravedad1AdHoc(" block;");
      this.setVcMuestraGravedad2AdHoc(" none;");
      this.setVcMuestraTopelegalNoPIAdHoc(" block;");

    }else if(this.getObjFiltroBean().getVcOrgResolutivo().equals("DDA") || this.getObjFiltroBean().getVcOrgResolutivo().equals("DIN") || this.getObjFiltroBean().getVcOrgResolutivo().equals("DSD") 
            || this.getObjFiltroBean().getVcOrgResolutivo().equals("CDA") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CIN") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CSD") 
            || this.getObjFiltroBean().getVcOrgResolutivo().equals("SPI")){
      
      this.setVcMuestraTopelegalNoPIAdHoc(" none;");
      if(!this.getObjFiltroBean().getVcOrgResolutivo().equals("SPI")){
        this.getObjFiltroBean().setVcCategoriaAdhoc("PI");
        this.setVcMuestraTamEmpresaAdHoc(" none;");
        this.setVcMuestraGravedad1AdHoc(" none;");
        this.setVcMuestraGravedad2AdHoc(" block;");
        if(this.getObjFiltroBean().getVcOrgResolutivo().equals("DDA") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CDA")){
          this.getObjFiltroBean().setNuSubTipoPi(1);
        }else{ //DIN y DSD
          this.getObjFiltroBean().setNuSubTipoPi(2);
          this.setVcMuestraAtenuanteF7(" none;");
        }
        this.doListarGravedadTopeAdhoc();
      }
    }

      this.doListarInstanciasXOrg();
      this.doSeleccionarInstanciaAdhoc();
      //this.doListarInfraccionesXOrgInstancia();
      this.setVcMuestraBarreras(" none;");
      this.setVcMuestraPreestablecido(" none;");
      this.setVcMuestraLibro(" none;");
      this.setVcMuestraPreestablecidoCcd(" none;");
      this.setVcMuestraPreestablecidoPi(" none;");
      this.setVcMuestraPreestablecidoFirma(" none;");
      this.setVcMuestraPorcentajeVentas(" none;");
      this.setVcMuestraAdHoc(" block;");

    }else if(this.getObjFiltroBean().getVcMetodo().equals("5")){ //BARRERAS
      this.doListarTipoBarreras();
      this.doListarTipoAlcanceBarreras();
      this.doListarFactorSecEconomicoBarreras();
      this.doListarFactorPoblacionBarreras();
      this.setVcMuestraBarreras(" block;");
      this.setVcMuestraPreestablecido(" none;");
      this.setVcMuestraLibro(" none;");
      this.setVcMuestraPreestablecidoCcd(" none;");
      this.setVcMuestraPreestablecidoPi(" none;");
      this.setVcMuestraPreestablecidoFirma(" none;");
      this.setVcMuestraPorcentajeVentas(" none;");
      this.setVcMuestraAdHoc(" none;");

    }else if(this.getObjFiltroBean().getVcMetodo().equals("6")){ //PREESTABLECIDO PI
      this.doListarInstanciasXOrg();
      this.doSeleccionarInstanciaPi();
      this.doListarTamanoEmpresa();
      //this.doListarInfraccionesXOrgInstancia();
      ///this.setVcMuestraOrgano1raInstanciaPi(" none;");
      this.setVcMuestraPreestablecidoPi(" block;");
      this.setVcMuestraBarreras(" none;");
      this.setVcMuestraPreestablecido(" none;");
      this.setVcMuestraLibro(" none;");
      this.setVcMuestraPreestablecidoCcd(" none;");
      this.setVcMuestraPreestablecidoFirma(" none;");
      this.setVcMuestraPorcentajeVentas(" none;");
      this.setVcMuestraAdHoc(" none;");

      if(!this.getObjFiltroBean().getVcOrgResolutivo().equals("SPI")){
        if(this.getObjFiltroBean().getVcOrgResolutivo().equals("DDA") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CDA")){
          this.getObjFiltroBean().setNuSubTipoPi(1);
        }else{//DIN y DSD
          this.getObjFiltroBean().setNuSubTipoPi(2);
          this.setVcMuestraAtenuanteF7(" none;");
        }
        this.doListarGravedadTopePi();
      }
logger.debug("this.getVcMuestraOrgano1raInstanciaPi() " + this.getVcMuestraOrgano1raInstanciaPi());
    }else if(this.getObjFiltroBean().getVcMetodo().equals("7")){ //PREESTABLECIDO CCD
      this.doListarInstanciasXOrg();
      this.doSeleccionarInstanciaCcd();
      this.calculaFD();
      this.doListarTamanoEmpresa();
      //this.doListarInfraccionesXOrgInstancia();
      this.setVcMuestraOrgano1raInstanciaCcd(" none;");
      this.setVcMuestraPreestablecidoCcd(" block;");
      this.setVcMuestraBarreras(" none;");
      this.setVcMuestraPreestablecido(" none;");
      this.setVcMuestraLibro(" none;");
      this.setVcMuestraPreestablecidoPi(" none;");
      this.setVcMuestraPreestablecidoFirma(" none;");
      this.setVcMuestraPorcentajeVentas(" none;");
      this.setVcMuestraAdHoc(" none;");

    }else if(this.getObjFiltroBean().getVcMetodo().equals("8")){ //Firma
      this.doListarServicioProductoAcredFirma();
      //this.doListarInfraccionesXOrgInstancia();
      this.setVcMuestraOrgano1raInstanciaCcd(" none;");
      this.setVcMuestraPreestablecidoCcd(" none;");
      this.setVcMuestraBarreras(" none;");
      this.setVcMuestraPreestablecido(" none;");
      this.setVcMuestraLibro(" none;");
      this.setVcMuestraPreestablecidoPi(" none;");
      this.setVcMuestraPreestablecidoFirma(" block;");
      this.setVcMuestraPorcentajeVentas(" none;");
      this.setVcMuestraAdHoc(" none;");
    }
    logger.info(">>FIN doSeleccionarMetodo");
  }

  public void doListarInstanciasXOrg() { // PARA PRESTABLECIDO, PRESTABLECIDO PI, PRESTABLECIDO CCD Y %VENTAS, ADHOC
    logger.info("1---->>doListarInstanciasXOrg() " + this.getObjFiltroBean().getVcOrgResolutivo());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarInstancias(this.getObjFiltroBean().getVcMetodo(), this.getObjFiltroBean().getVcOrgResolutivo());
      if (objResult != null) {
        //this.getObjFiltroBean().setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
        //logger.debug("this.getObjFiltroBean().getLstInstancias().size(): " + this.getObjFiltroBean().getLstInstancias().size());

        this.setLstInstanciaBean((List<ClsInstanciasBean>) objResult.get("SP_LST_INSTANCIAS"));
        logger.debug(">>this.lstIntanciaBean.size(): " + this.lstInstanciaBean.size());
        SelectItem data = new SelectItem();
        List<SelectItem> lstMetodoCal = new ArrayList<SelectItem>();

          if(this.lstInstanciaBean.size() > 1){
            //Item 1 Otorgado
            data.setValue(-1);
            data.setLabel("Seleccionar");
            lstMetodoCal.add(data);

            for (ClsInstanciasBean objSerie : lstInstanciaBean) {
              data = new SelectItem();
              data.setLabel("" + objSerie.getVcInstancia());
              data.setValue("" + objSerie.getNuIdInstancia());
              logger.debug(">>objSerie.getVcInstancia(): " + objSerie.getVcInstancia());
              logger.debug(">>objSerie.getNuIdInstancia(): " + objSerie.getNuIdInstancia());
              lstMetodoCal.add(data);
            }
            this.getObjFiltroBean().setLstInstanciasCalculo(lstMetodoCal);

          }else{
            for (ClsInstanciasBean objSerie : lstInstanciaBean) {
              data = new SelectItem();
              data.setLabel("" + objSerie.getVcInstancia());
              data.setValue("" + objSerie.getNuIdInstancia());
              logger.debug(">>objSerie.getVcInstancia(): " + objSerie.getVcInstancia());
              logger.debug(">>objSerie.getNuIdInstancia(): " + objSerie.getNuIdInstancia());
              lstMetodoCal.add(data);
            }
            this.getObjFiltroBean().setLstInstanciasCalculo(lstMetodoCal);

            if (this.getObjFiltroBean().getVcMetodo().equals("1")){//PREESTABLECIDO
              this.getObjFiltroBean().setVcInstancia(String.valueOf(data.getValue()));
              this.doListarInfraccionesXOrgInstancia();

            }else if (this.getObjFiltroBean().getVcMetodo().equals("3")){//% VENTAS
              this.getObjFiltroBean().setVcInstanciaVentas(String.valueOf(data.getValue()));
              this.setVcMuestraOrgano1raInstanciaVentas(" none;");

            }else if (this.getObjFiltroBean().getVcMetodo().equals("4")){//ADHOC
              this.getObjFiltroBean().setVcInstanciaAdhoc(String.valueOf(data.getValue()));
              this.setVcMuestraOrgano1raInstanciaAdhoc(" none;");
              this.doListarInfraccionesXOrgInstancia();

            }else if (this.getObjFiltroBean().getVcMetodo().equals("6")){//PI
              this.getObjFiltroBean().setVcInstanciaPi(String.valueOf(data.getValue()));
              this.setVcMuestraOrgano1raInstanciaPi(" none;");
              this.doListarInfraccionesXOrgInstancia();

            }else if (this.getObjFiltroBean().getVcMetodo().equals("7")){//CCD
              this.getObjFiltroBean().setVcInstanciaCcd(String.valueOf(data.getValue()));
              this.setVcMuestraOrgano1raInstanciaCcd(" none;");
              this.doListarInfraccionesXOrgInstancia();
              
            }
            
          }
      }
      logger.debug(">>this.getObjFiltroBean().getVcMetodo(): " + this.getObjFiltroBean().getVcMetodo());
      logger.debug(">>this.getObjFiltroBean().getLstInstanciasCalculo().size(): " + this.getObjFiltroBean().getLstInstanciasCalculo().size());
      logger.debug("this.getVcMuestraOrgano1raInstanciaPi() " + this.getVcMuestraOrgano1raInstanciaPi());
    logger.info("1----->>FIN doListarInstanciasXOrg");
  }

  public void doListarOrganosMetodo(String nuIdMetodo, int nuInstancia) { // PARA PREESTABLECIDO PI
    logger.info(">>doListarOrganosMetodo");
    logger.debug("nuIdMetodo: " + nuIdMetodo);
    logger.debug("nuInstancia: " + nuInstancia);
    ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
    ClsResultDAO objResult = objDAO.doListarOrganosMetodo(nuIdMetodo, nuInstancia);
    if (objResult != null) {
          this.getObjFiltroBean().setLstOrganosMetodo((Map<String, String>) objResult.get("SP_LST_ORGANOS_METODO"));
    }
    logger.info(">>FIN doListarOrganosMetodo");
  }

  public void doListarTipoBarreras() { //Tipo Infractor
    logger.info(">>doListarTipoBarreras");
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();

      ClsResultDAO objResult = objDAO.doListarParametros(89);
      if (objResult != null) {
          this.getObjFiltroBean().setLstTipoBarreras((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
      logger.info(">>FIN doListarTipoBarreras");
  }

  public void doListarTipoAlcanceBarreras() {//Tipo Alcance
    logger.info(">>doListarTipoAlcanceBarreras");
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();

      ClsResultDAO objResult = objDAO.doListarParametros(245);
      if (objResult != null) {
          this.getObjFiltroBean().setLstTipoAlcanceBarreras((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
      logger.info(">>FIN doListarTipoAlcanceBarreras");
  }

  public void doListarFactorPoblacionBarreras() {//Factor Poblacion Barreras
    logger.info(">>doListarFactorPoblacionBarreras");
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();

      ClsResultDAO objResult = objDAO.doListarParametros(155);
      if (objResult != null) {
          this.getObjFiltroBean().setLstFactorPoblacionBarreras((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
      logger.info(">>FIN doListarFactorPoblacionBarreras");
  }

  public void doListarFactorSecEconomicoBarreras() {//Factor Sector Economico
    logger.info(">>doListarFactorSecEconomicoBarreras");
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();

      ClsResultDAO objResult = objDAO.doListarParametros(161);
      if (objResult != null) {
          this.getObjFiltroBean().setLstFactorSecEconomicoBarreras((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
      logger.info(">>FIN doListarFactorSecEconomicoBarreras");
  }

  public void doListarServicioProductoAcredFirma() {//PREESTABLECIDO FIRMA
    logger.info(">>doListarServicioProductoAcredFirma");
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();

      ClsResultDAO objResult = objDAO.doListarParametros(183);
      if (objResult != null) {
          this.getObjFiltroBean().setLstServicioProductoAcreditadoFirma((Map<String, String>) objResult.get("SP_LST_PARAMETROS"));
      }
      logger.info(">>FIN doListarServicioProductoAcredFirma");
  }

  public void doListarMultaUITAnios() {
    logger.info(">>doListarMultaUITAnios");
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarMultaUITAnios();
      if (objResult != null) {
            this.setLstMultaUITAnioBean((List<ClsMultaUITAnioBean>) objResult.get("SP_LST_MULTA_UIT_ANIOS"));
            logger.debug(">this.lstMultaUITAnioBean.size():" + this.lstMultaUITAnioBean.size());
            List<SelectItem> lstAniosUIT = new ArrayList<SelectItem>();
            SelectItem data = new SelectItem();
            /* data.setValue("-1");
            data.setLabel("Seleccione");
            lstAniosUIT.add(data); */
            for (ClsMultaUITAnioBean objCoord : lstMultaUITAnioBean) {
              /* logger.debug(">objCoord.getVcIdMultaUIT():" + objCoord.getVcIdMultaUIT());
              logger.debug(">objCoord.getVcMultaUIT():" + objCoord.getVcMultaUIT());
              logger.debug(">objCoord.getNuUIT():" + objCoord.getNuUIT()); */
                data = new SelectItem();
                data.setLabel("" + objCoord.getVcMultaUIT());
                data.setValue("" + objCoord.getVcIdMultaUIT());
                lstAniosUIT.add(data);
            }
            this.getObjFiltroBean().setLstAniosUIT(lstAniosUIT);
      }
      logger.info(">>FIN doListarMultaUITAnios");
  }

  public void doListarTamanoEmpresa() {
    logger.info(">>doListarTamanoEmpresa");
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarTamanoEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()));
      if (objResult != null) {
            this.setLstTamanoEmpresaBean((List<ClsTamanioEmpresaBean>) objResult.get("SP_LST_TAM_EMPRESA"));
            logger.debug(">this.lstTamanoEmpresaBean.size():" + this.lstTamanoEmpresaBean.size());
            List<SelectItem> lstTamanoEmpresa = new ArrayList<SelectItem>();
            SelectItem data = new SelectItem();
            /* data.setValue("-1");
            data.setLabel("Seleccione");
            lstTamanoEmpresa.add(data); */
            for (ClsTamanioEmpresaBean objCoord : lstTamanoEmpresaBean) {
                data = new SelectItem();
                data.setLabel("" + objCoord.getVcTamanioEmpresa());
                data.setValue("" + objCoord.getVcIdTamanioEmpresa());
                lstTamanoEmpresa.add(data);
            }
            this.getObjFiltroBean().setLstTamanoEmpresa(lstTamanoEmpresa);
      }
      logger.info(">>FIN doListarTamanoEmpresa");
  }

  public void doListarAreas() {
    logger.info(">>doListarAreas");
      ClsUsuarioIDAO objDAO = new ClsUsuarioDAO();
      ClsResultDAO objResult = objDAO.doListarAreas();
      if (objResult != null) {
            //this.setLstMultaUITAnioBean((List<ClsMultaUITAnioBean>) objResult.get("SP_LST_MULTA_UIT_ANIOS"));
            this.setLstAreas((Map<String, String>) objResult.get("SP_LST_AREAS"));
            logger.debug(">this.lstAreas.size():" + this.lstAreas.size());
      }
      logger.info(">>FIN doListarAreas");
  }

  public void doListarProbabilidadBarreras() {
    logger.info(">>doListarProbabilidadBarreras");
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doListarProbabilidadBarreras();
      if (objResult != null) {
            this.setLstProbabilidadBarrerasBean((List<ClsProbabilidadBarrerasBean>) objResult.get("SP_LST_PROBABILIDAD_BARRERAS"));
            logger.debug(">CARGA lstProbabilidadBarrerasBean:" + this.lstProbabilidadBarrerasBean.size());
            List<SelectItem> lstAniosUIT = new ArrayList<SelectItem>();
            SelectItem data = new SelectItem();
            for (ClsProbabilidadBarrerasBean objCoord : lstProbabilidadBarrerasBean) {
              //logger.debug("-------->objCoord.getVcCaso():" + objCoord.getVcCaso());
              //logger.debug("------->objCoord.getVcDefinicion():" + objCoord.getVcDefinicion());
              //logger.debug("-------->objCoord.getNuFactorProbabilidad():" + objCoord.getNuFactorProbabilidad());
                data = new SelectItem();
                data.setLabel("" + objCoord.getVcCaso());
                data.setValue("" + objCoord.getVcCaso());
                lstAniosUIT.add(data);
            }
            this.getObjFiltroBean().setLstProbabilidadBarreras(lstAniosUIT);
      }
      logger.info(">>FIN doListarProbabilidadBarreras");
  }

  public void doListarInfraccionesXOrgInstancia() { 
    logger.info(">>doListarInfraccionesXOrgInstancia");
    logger.debug("getVcMetodo(): " + this.getObjFiltroBean().getVcMetodo());
    logger.debug("getVcInstancia(): " + this.getObjFiltroBean().getVcInstancia());
    logger.debug("getVcInstanciaAdhoc(): " + this.getObjFiltroBean().getVcInstanciaAdhoc());
    logger.debug("getVcInstanciaVentas(): " + this.getObjFiltroBean().getVcInstanciaVentas());
    logger.debug("getVcInstanciaPi(): " + this.getObjFiltroBean().getVcInstanciaPi());
    logger.debug("getVcInstanciaCcd(): " + this.getObjFiltroBean().getVcInstanciaCcd());
    logger.debug("getVcOrgResolutivo(): " + this.getObjFiltroBean().getVcOrgResolutivo());
    ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();

    if(this.getObjFiltroBean().getVcMetodo().equals("1") || this.getObjFiltroBean().getVcMetodo().equals("2") 
        || this.getObjFiltroBean().getVcMetodo().equals("3") 
        || this.getObjFiltroBean().getVcMetodo().equals("6") || this.getObjFiltroBean().getVcMetodo().equals("7") 
        || this.getObjFiltroBean().getVcMetodo().equals("8")){
          
      ClsResultDAO objResult = objDAO.doListarInfracciones(this.getObjFiltroBean().getVcMetodo(), this.getObjFiltroBean().getVcInstancia(), this.getObjFiltroBean().getVcOrgResolutivo());
      if (objResult != null) {
            this.setLstAfectacionBean((List<ClsTipoAfectacionBean>) objResult.get("SP_LST_INFRACCIONES"));
            this.getObjFiltroBean().setIsBlLstAfectacion(true);
            logger.debug(">CARGA de TODAS - this.lstAfectacionBean.size():" + this.lstAfectacionBean.size());
            List<SelectItem> lstAfectacion = new ArrayList<SelectItem>();
            SelectItem data = new SelectItem();
            for (ClsTipoAfectacionBean objCoord : lstAfectacionBean) {
                data = new SelectItem();
                data.setLabel("" + objCoord.getVcTipoAfectacion());
                data.setValue("" + objCoord.getVcIdTipoAfectacion());
                lstAfectacion.add(data);
            }
            this.getObjFiltroBean().setLstTipoAfectacion(lstAfectacion);
      }

    }else if (this.getObjFiltroBean().getVcMetodo().equals("5")){ //BARRERAS
      ClsResultDAO objResult = objDAO.doListarInfracciones(this.getObjFiltroBean().getVcMetodo(), this.getObjFiltroBean().getVcIdTipoBarrera(), this.getObjFiltroBean().getVcOrgResolutivo());
      if (objResult != null) {
            this.setLstAfectacionBean((List<ClsTipoAfectacionBean>) objResult.get("SP_LST_INFRACCIONES"));
            this.getObjFiltroBean().setIsBlLstAfectacion(true);
            logger.debug(">CARGA de TODAS - this.lstAfectacionBean.size():" + this.lstAfectacionBean.size());
            List<SelectItem> lstAfectacion = new ArrayList<SelectItem>();
            SelectItem data = new SelectItem();
            for (ClsTipoAfectacionBean objCoord : lstAfectacionBean) {
                data = new SelectItem();
                data.setLabel("" + objCoord.getVcTipoAfectacion());
                data.setValue("" + objCoord.getVcIdTipoAfectacion());
                lstAfectacion.add(data);
            }
            this.getObjFiltroBean().setLstTipoAfectacion(lstAfectacion);
      }
    }if(this.getObjFiltroBean().getVcMetodo().equals("4")){ //AD-HOC
          
      ClsResultDAO objResult = null;
      if(this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
        objResult = objDAO.doListarInfracciones(this.getObjFiltroBean().getVcMetodo(), this.getObjFiltroBean().getVcInstancia(), "CLC");

      }else if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CC1") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CC2") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CC3") || this.getObjFiltroBean().getVcOrgResolutivo().equals("SPC")){
        objResult = objDAO.doListarInfracciones(this.getObjFiltroBean().getVcMetodo(), this.getObjFiltroBean().getVcInstancia(), "CPC");

      }else if(this.getObjFiltroBean().getVcOrgResolutivo().equals("SPI")){
        objResult = objDAO.doListarInfracciones(this.getObjFiltroBean().getVcMetodo(), this.getObjFiltroBean().getVcInstancia(), this.getObjFiltroBean().getVcOrgano1raInstanciaAdhoc());

      }else{
        objResult = objDAO.doListarInfracciones(this.getObjFiltroBean().getVcMetodo(), this.getObjFiltroBean().getVcInstancia(), this.getObjFiltroBean().getVcOrgResolutivo());

      }
            
      if (objResult != null) {
            this.setLstAfectacionBean((List<ClsTipoAfectacionBean>) objResult.get("SP_LST_INFRACCIONES"));
            this.getObjFiltroBean().setIsBlLstAfectacion(true);
            logger.debug(">CARGA de TODAS - this.lstAfectacionBean.size():" + this.lstAfectacionBean.size());
            List<SelectItem> lstAfectacion = new ArrayList<SelectItem>();
            SelectItem data = new SelectItem();
            for (ClsTipoAfectacionBean objCoord : lstAfectacionBean) {
                data = new SelectItem();
                data.setLabel("" + objCoord.getVcTipoAfectacion());
                data.setValue("" + objCoord.getVcIdTipoAfectacion());
                lstAfectacion.add(data);
            }
            this.getObjFiltroBean().setLstTipoAfectacion(lstAfectacion);
      }

    }

    logger.info(">>FIN doListarInfraccionesXOrgInstancia");
  }

  public void doSeleccionarInstancia(){ // SOLO PARA PREESTABLECIDO
      logger.info(">>doSeleccionarInstancia ");
      logger.debug("this.getObjFiltroBean().getVcInstancia(): " + this.getObjFiltroBean().getVcInstancia());
      //this.doListarMetodosXOrgInstancia();
      this.doListarInfraccionesXOrgInstancia();
      logger.info(">>Fin doSeleccionarInstancia");
  }

  public void doSeleccionarInstanciaPi(){ // SOLO PARA PREESTABLECIDO PI
      logger.info(">>doSeleccionarInstanciaPi ");
      logger.debug("this.getObjFiltroBean().getVcInstanciaPi(): " + this.getObjFiltroBean().getVcInstanciaPi());
      if(this.getObjFiltroBean().getVcInstanciaPi().equals("2") && this.getObjFiltroBean().getVcOrgResolutivo().equals("SPI")){
        this.doListarOrganosMetodo(this.getObjFiltroBean().getVcMetodo(), Integer.parseInt(this.getObjFiltroBean().getVcInstanciaPi()));
        this.setVcMuestraOrgano1raInstanciaPi(" block;");
      }else{
        this.setVcMuestraOrgano1raInstanciaPi(" none;");
      }
      this.doListarInfraccionesXOrgInstancia();
      logger.debug("this.getVcMuestraOrgano1raInstanciaPi() " + this.getVcMuestraOrgano1raInstanciaPi());
      logger.info(">>Fin doSeleccionarInstanciaPi");
  }

  public void doSeleccionarInstanciaCcd(){ // SOLO PARA PREESTABLECIDO CCD
      logger.info(">>doSeleccionarInstanciaCcd ");
      logger.debug("this.getObjFiltroBean().getVcInstanciaCcd(): " + this.getObjFiltroBean().getVcInstanciaCcd());
      if(this.getObjFiltroBean().getVcInstanciaCcd().equals("2") && this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
        this.doListarOrganosMetodo(this.getObjFiltroBean().getVcMetodo(), Integer.parseInt(this.getObjFiltroBean().getVcInstanciaCcd()));
        this.setVcMuestraOrgano1raInstanciaCcd(" block;");
      }else{
        this.setVcMuestraOrgano1raInstanciaCcd(" none;");
      }
      this.doListarInfraccionesXOrgInstancia();
      logger.info(">>Fin doSeleccionarInstanciaCcd");
  }

  public void doSeleccionarInstanciaVentas(){ // SOLO PARA %VENTAS
      logger.info("2------------>>doSeleccionarInstanciaVentas ");
      logger.debug("this.getObjFiltroBean().getVcInstanciaVentas(): " + this.getObjFiltroBean().getVcInstanciaVentas());
      if(this.getObjFiltroBean().getVcInstanciaVentas().equals("2") && this.getObjFiltroBean().getVcOrgResolutivo().equals("SDC")){
        this.doListarOrganosMetodo(this.getObjFiltroBean().getVcMetodo(), Integer.parseInt(this.getObjFiltroBean().getVcInstanciaVentas()));
        this.setVcMuestraOrgano1raInstanciaVentas(" block;");
      }else{
        this.setVcMuestraOrgano1raInstanciaVentas(" none;");
      }
      logger.info("2------------>>Fin doSeleccionarInstanciaVentas");
  }

  public void doSeleccionarInstanciaAdhoc(){ // SOLO PARA ADHOC
      logger.info(">>doSeleccionarInstanciaAdhoc ");
      logger.debug("this.getObjFiltroBean().getVcInstanciaAdhoc(): " + this.getObjFiltroBean().getVcInstanciaAdhoc());
      if(this.getObjFiltroBean().getVcInstanciaAdhoc().equals("2") && this.getObjFiltroBean().getVcOrgResolutivo().equals("SPI")){
        this.doListarOrganosMetodo(this.getObjFiltroBean().getVcMetodo(), Integer.parseInt(this.getObjFiltroBean().getVcInstanciaAdhoc()));
        this.setVcMuestraOrgano1raInstanciaAdhoc(" block;");
      }else{
        this.setVcMuestraOrgano1raInstanciaAdhoc(" none;");
        this.doListarInfraccionesXOrgInstancia();
      }
      logger.info(">>Fin doSeleccionarInstanciaAdhoc");
  }

  public void doSeleccionarOrgano1raInstanciaPi(){ // SOLO PARA PREESTABLECIDO PI -> PI
      logger.info(">>doSeleccionarOrgano1raInstanciaPi ");
      logger.debug("this.getObjFiltroBean().getVcOrgano1raInstancia(): " + this.getObjFiltroBean().getVcOrgano1raInstancia());
      if(this.getObjFiltroBean().getVcOrgano1raInstancia().equals("DDA") || this.getObjFiltroBean().getVcOrgano1raInstancia().equals("CDA")){
        this.getObjFiltroBean().setNuSubTipoPi(1);
      }else{
        this.getObjFiltroBean().setNuSubTipoPi(2);
      }
      this.doListarGravedadTopePi();
      logger.info(">>Fin doSeleccionarOrgano1raInstanciaPi");
  }

  public void doSeleccionarOrgano1raInstanciaCcd(){ // SOLO PARA PREESTABLECIDO PI
      logger.info(">>doSeleccionarOrgano1raInstanciaCcd ");
      logger.info(">>Fin doSeleccionarOrgano1raInstanciaCcd");
  }

  public void doSeleccionarOrgano1raInstanciaVentas(){ // SOLO PARA %VENTAS
      logger.info(">>doSeleccionarOrgano1raInstanciaVentas ");
      logger.debug("this.getObjFiltroBean().getVcOrgano1raInstanciaVentas(): " + this.getObjFiltroBean().getVcOrgano1raInstanciaVentas());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetValorDescripcion(248, this.getObjFiltroBean().getVcOrgano1raInstanciaVentas(), 0);
      if (objResult != null) {
          this.getObjFiltroBean().setNuUmbralFactorAVentas(Double.parseDouble((String) objResult.get("GET_VALOR_PARAMETRO")));
      }

      //if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC") || this.getObjFiltroBean().getVcOrgResolutivo().equals("CCD")){
      //ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      objResult = objDAO.doGetValorDescripcion(253, this.getObjFiltroBean().getVcOrgano1raInstanciaVentas(), 0);
        if (objResult != null) {
            this.getObjFiltroBean().setNuUmbralFactorGDifVentas(Double.parseDouble((String) objResult.get("GET_VALOR_PARAMETRO")));
            logger.info("----------->>this.getObjFiltroBean().getNuUmbralFactorGDifVentas(): "+ this.getObjFiltroBean().getNuUmbralFactorGDifVentas());
        }
      //}

      if(this.getObjFiltroBean().getVcOrgano1raInstanciaVentas().equals("CLC")){
        this.setVcMuestraCLCVentas(" block;");
      }else{
        this.setVcMuestraCLCVentas(" none;");
      }
      logger.info(">>Fin doSeleccionarOrgano1raInstanciaVentas");
  }

  public void doSeleccionarOrgano1raInstanciaAdhoc(){ // SOLO PARA ADHOC
      logger.info(">>doSeleccionarOrgano1raInstanciaAdhoc ");
      logger.debug("this.getObjFiltroBean().getVcOrgano1raInstanciaAdhoc(): " + this.getObjFiltroBean().getVcOrgano1raInstanciaAdhoc());

      this.setVcMuestraTamEmpresaAdHoc(" none;");
      this.setVcMuestraGravedad1AdHoc(" none;");
      this.setVcMuestraGravedad2AdHoc(" block;");
      if(this.getObjFiltroBean().getVcOrgano1raInstanciaAdhoc().equals("DDA") || this.getObjFiltroBean().getVcOrgano1raInstanciaAdhoc().equals("CDA")){
        this.getObjFiltroBean().setNuSubTipoPi(1);
      }else{
        this.getObjFiltroBean().setNuSubTipoPi(2);
      }
      this.doListarGravedadTopeAdhoc();
      this.doListarInfraccionesXOrgInstancia();
      logger.info(">>Fin doSeleccionarOrgano1raInstanciaAdhoc");
  }

  public void doSeleccionarGravedadSubtipoPi(){
      logger.info(">>doSeleccionarGravedadSubtipoPi ");
      logger.debug("this.getObjFiltroBean().getNuGravedadTopePi(): " + this.getObjFiltroBean().getNuGravedadTopePi());
      logger.debug("this.getObjFiltroBean().getNuMultaPreUITPi(): " + this.getObjFiltroBean().getNuMultaPreUITPi());
      
      //this.getObjFiltroBean().getNuMultaPreUITPi();
      List<Double> list2 = new ArrayList<>();
      // add element in ArrayList object list
      list2.add(this.getObjFiltroBean().getNuMultaPreUITPi());
      list2.add(this.getObjFiltroBean().getNuGravedadTopePi());
      logger.debug("Min: " + findMin(list2)); 
          
      this.getObjFiltroBean().setNuMultaFinal(round(findMin(list2), 2));
      this.getObjFiltroBean().setNuMultaFinalSoles(round((double) findMin(list2)*(double) this.getObjFiltroBean().getNuAnioUIT(), 2));

      logger.info(">>Fin doSeleccionarGravedadSubtipoPi");
  }

  public void doSeleccionarGravedadSubtipoVentas(){
      logger.info(">>doSeleccionarGravedadSubtipoVentas ");
      logger.debug("this.getObjFiltroBean().getNuIdGravedadVentas(): " + this.getObjFiltroBean().getNuIdGravedadVentas());
      logger.debug("this.getObjFiltroBean().getNuMultaPreUITVentas(): " + this.getObjFiltroBean().getNuMultaPreUITVentas());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetGravedadVentas(this.getObjFiltroBean().getNuIdGravedadVentas());

      BigDecimal bdajingreso;

      if (objResult != null) {
          this.getObjFiltroBean().setVcGravedadTopeVentas((Double)objResult.get("GET_GRAVEDAD_TOPE") ==9999 ? "Mayor a 1000" : objResult.get("GET_GRAVEDAD_TOPE")+"");
          this.getObjFiltroBean().setNuGravedadTopeVentas((Double)objResult.get("GET_GRAVEDAD_TOPE"));
          bdajingreso = new BigDecimal((Double)objResult.get("GET_GRAVEDAD_PORCTOPE")*this.getObjFiltroBean().getNuFacturacionAnualVentas()/this.getObjFiltroBean().getNuAnioUIT());
          logger.debug("bdajingreso: "+ bdajingreso.doubleValue());
          this.getObjFiltroBean().setNuAjusteIngVentas(round(bdajingreso.doubleValue(), 2));

          List<Double> list2 = new ArrayList<>();
          list2.add(this.getObjFiltroBean().getNuMultaAAVentas());
          list2.add(this.getObjFiltroBean().getNuGravedadTopeVentas());
          list2.add(bdajingreso.doubleValue());
          logger.debug("Min: " + findMin(list2)); 

          this.getObjFiltroBean().setNuMultaFinal(round(findMin(list2), 2));
          this.getObjFiltroBean().setNuMultaFinalSoles(round((double) findMin(list2)*(double) this.getObjFiltroBean().getNuAnioUIT(), 2));
      }
      
      logger.debug("this.getObjFiltroBean().getNuMultaFinal(): " + this.getObjFiltroBean().getNuMultaFinal());
      logger.debug("this.getObjFiltroBean().getNuMultaFinalSoles(): " + this.getObjFiltroBean().getNuMultaFinalSoles());
      logger.info(">>Fin doSeleccionarGravedadSubtipoVentas");
  }

  public void doSeleccionarGravedadSubtipoAdhoc(){
      logger.info(">>doSeleccionarGravedadSubtipoAdhoc ");
      logger.debug("this.getObjFiltroBean().getNuGravedadTopeAdhoc(): " + this.getObjFiltroBean().getNuGravedadTopeAdhoc());
      
      List<Double> list2 = new ArrayList<>();
      this.getObjFiltroBean().setNuAjusteIngAdhoc(this.getObjFiltroBean().getNuGravedadTopeAdhoc());
      this.getObjFiltroBean().setVcGravedadTopeAdhoc(this.getObjFiltroBean().getNuGravedadTopeAdhoc()+"");
      list2.add(this.getObjFiltroBean().getNuMultaAAAdhoc());
      list2.add(this.getObjFiltroBean().getNuAjusteIngAdhoc());
      logger.debug("Min: " + findMin(list2)); 

      this.getObjFiltroBean().setNuMultaFinal(round(findMin(list2), 2));
      this.getObjFiltroBean().setNuMultaFinalSoles(round((double) findMin(list2)*(double) this.getObjFiltroBean().getNuAnioUIT(), 2));

      logger.info(">>Fin doSeleccionarGravedadSubtipoAdhoc");
  }

  public void doSeleccionarTamEmpresaAdhoc(){
      logger.info(">>doSeleccionarTamEmpresaAdhoc ");
      //logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
      logger.debug("this.getObjFiltroBean().getNuIdTamEmpresa(): " + this.getObjFiltroBean().getNuIdTamEmpresa());


      logger.info(">>Fin doSeleccionarTamEmpresaAdhoc");
  }

  public void doListarGravedadTopePi() {//Factor Sector Economico
    logger.info(">>doListarGravedadTopePi() ");
    logger.debug("this.getObjFiltroBean().getVcMetodo() "+this.getObjFiltroBean().getVcMetodo());
    logger.debug("this.getObjFiltroBean().getNuSubTipoPi() "+this.getObjFiltroBean().getNuSubTipoPi());
    ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
    ClsResultDAO objResult = objDAO.doComboGravedadTope(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getNuSubTipoPi());
    if (objResult != null) {
        this.getObjFiltroBean().setLstGravedadTopePi((Map<String, String>) objResult.get("SP_CB_GRAVEDAD_TOPE"));
    }
    logger.info(">>FIN doListarGravedadTopePi() ");
  }

  public void doListarGravedadTopeAdhoc() {
    logger.info(">>doListarGravedadTopeAdhoc() ");
    logger.debug("this.getObjFiltroBean().getVcMetodo() "+this.getObjFiltroBean().getVcMetodo());
    logger.debug("this.getObjFiltroBean().getNuSubTipoPi() "+this.getObjFiltroBean().getNuSubTipoPi());
    ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
    ClsResultDAO objResult = objDAO.doComboGravedadTope(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getNuSubTipoPi());
    if (objResult != null) {
        this.getObjFiltroBean().setLstGravedadTopeAdhoc((Map<String, String>) objResult.get("SP_CB_GRAVEDAD_TOPE"));
    }
    logger.info(">>FIN doListarGravedadTopeAdhoc() ");
  }

  public void doSeleccionarTipoBarreras(){ // SOLO PARA BARRERAS
      logger.info(">>doSeleccionarTipoBarreras ");
      logger.debug("this.getObjFiltroBean().getVcIdTipoBarrera(): " + this.getObjFiltroBean().getVcIdTipoBarrera());
      //this.doListarMetodosXOrgInstancia();
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetValorDescripcion(89, null, Integer.parseInt(this.getObjFiltroBean().getVcIdTipoBarrera()));
      if (objResult != null) {
          this.getObjFiltroBean().setVcTipoBarrera(objResult.get("GET_DESCRIPCION_PARAMETRO")+"");
      }
      logger.debug("this.getObjFiltroBean().getVcTipoBarrera(): " + this.getObjFiltroBean().getVcTipoBarrera());
      this.doListarInfraccionesXOrgInstancia();
      logger.info(">>Fin doSeleccionarTipoBarreras");
  }

  public void doSeleccionarTipoAlcanceBarreras(){ // SOLO PARA BARRERAS
      logger.info(">>doSeleccionarTipoAlcanceBarreras ");
      logger.debug("this.getObjFiltroBean().getVcIdTipoAlcanceBarrera(): " + this.getObjFiltroBean().getVcIdTipoAlcanceBarrera());
      if(!this.getObjFiltroBean().getVcIdTipoAlcanceBarrera().equals("1")){
        this.setVcMuestraFacSecEconBarreras(" none;");
      }else{
        this.setVcMuestraFacSecEconBarreras(" block;");
      }

      if(!this.getObjFiltroBean().getVcIdTipoAlcanceBarrera().equals("-1")){
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetValorDescripcion(245, null, Integer.parseInt(this.getObjFiltroBean().getVcIdTipoAlcanceBarrera()));
        if (objResult != null) {
            this.getObjFiltroBean().setVcTipoAlcanceBarrera(objResult.get("GET_DESCRIPCION_PARAMETRO")+"");
        }
        this.calculaMultaDBarreras();
      }
      logger.info(">>Fin doSeleccionarTipoAlcanceBarreras");
  }

  public void doSeleccionarFactorPoblacionBarreras(){ // SOLO PARA BARRERAS
      logger.info(">>doSeleccionarFactorPoblacionBarreras ");
      logger.debug("this.getObjFiltroBean().getVcIdFactPoblacionBarrera(): " + this.getObjFiltroBean().getVcIdFactPoblacionBarrera());
      if(!this.getObjFiltroBean().getVcIdFactPoblacionBarrera().equals("-1")){
        this.calculaMultaDBarreras();
      }
      logger.info(">>Fin doSeleccionarFactorPoblacionBarreras");
  }

  public void doSeleccionarFactorSecEconBarreras(){ // SOLO PARA BARRERAS
      logger.info(">>doSeleccionarFactorSecEconBarreras ");
      logger.debug("this.getObjFiltroBean().getVcIdFactSecEconBarrera() ID: " + this.getObjFiltroBean().getVcIdFactSecEconBarrera());
      if(!this.getObjFiltroBean().getVcIdFactSecEconBarrera().equals("-1")){
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetValorDescripcion(161, null, Integer.parseInt(this.getObjFiltroBean().getVcIdFactSecEconBarrera()));
        if (objResult != null) {
            this.getObjFiltroBean().setNuValorSecEconomico(Double.parseDouble((String) objResult.get("GET_VALOR_SECUN_PARAMETRO")));
        }
        this.calculaMultaDBarreras();
      }
      logger.info(">>Fin doSeleccionarFactorSecEconBarreras");
  }

  public void doSeleccionarServicioProductoAcreditadoFirma(){ // SOLO PARA FIRMA
      logger.info(">>doSeleccionarServicioProductoAcreditadoFirma ");
      logger.debug("this.getObjFiltroBean().getVcIdTipoServicioFirma(): " + this.getObjFiltroBean().getVcIdTipoServicioFirma());
      this.getObjFiltroBean().setVcInstancia(this.getObjFiltroBean().getVcIdTipoServicioFirma());
      this.doListarInfraccionesXOrgInstancia();
      logger.info(">>Fin doSeleccionarServicioProductoAcreditadoFirma");
  }

  public void doListarGravedadTopeVentas() {
    logger.info(">>doListarGravedadTopeVentas() ");
    logger.debug("this.getObjFiltroBean().getVcMetodo() "+this.getObjFiltroBean().getVcMetodo());
    logger.debug("this.getObjFiltroBean().getVcOrgResolutivo() "+this.getObjFiltroBean().getVcOrgResolutivo());
    if(this.getObjFiltroBean().getVcOrgResolutivo().equals("CLC")){
      this.getObjFiltroBean().setNuSubTipoPi(1);
    }else{
      this.getObjFiltroBean().setNuSubTipoPi(2);
    }
    
    logger.debug("this.getObjFiltroBean().getNuSubTipoPi() "+this.getObjFiltroBean().getNuSubTipoPi());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doComboGravedadTope(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), this.getObjFiltroBean().getNuSubTipoPi());
      if (objResult != null) {
          this.getObjFiltroBean().setLstGravedadTopePi((Map<String, String>) objResult.get("SP_CB_GRAVEDAD_TOPE"));
      }
      logger.info(">>FIN doListarGravedadTopeVentas() ");
  }

  public void doListarTamEmpresaAdhoc() {
    logger.info(">>doListarTamEmpresaAdhoc() ");
    logger.debug("this.getObjFiltroBean().getVcMetodo() "+this.getObjFiltroBean().getVcMetodo());
    logger.debug("this.getObjFiltroBean().getVcOrgResolutivo() "+this.getObjFiltroBean().getVcOrgResolutivo());

    logger.debug("this.getObjFiltroBean().getNuSubTipoPi() "+this.getObjFiltroBean().getNuSubTipoPi());
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doComboGravedadTope(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), 0);
      if (objResult != null) {
          this.getObjFiltroBean().setLstTamEmpresaAdhoc((Map<String, String>) objResult.get("SP_CB_GRAVEDAD_TOPE"));
      }
      logger.info(">>FIN doListarTamEmpresaAdhoc() ");
  }

  public void calculaMultaDBarreras(){
    //TODO: revisar
    logger.info(">>calculaMultaDBarreras ");
    logger.info("this.getObjFiltroBean().getVcIdTipoAlcanceBarrera(): "+this.getObjFiltroBean().getVcIdTipoAlcanceBarrera());
    logger.info("this.getObjFiltroBean().getVcIdFactPoblacionBarrera(): "+this.getObjFiltroBean().getVcIdFactPoblacionBarrera());
    logger.info("this.getObjFiltroBean().getVcIdFactSecEconBarrera(): "+this.getObjFiltroBean().getVcIdFactSecEconBarrera());
    logger.info("this.getObjFiltroBean().getNuValorSecEconomico(): "+this.getObjFiltroBean().getNuValorSecEconomico());
    logger.info("this.getObjFiltroBean().getNuDanioBaseBarrera(): "+this.getObjFiltroBean().getNuDanioBaseBarrera());
    double nuMultaD=0.0;

    if(!this.getObjFiltroBean().getVcIdTipoAlcanceBarrera().equals("-1") && !this.getObjFiltroBean().getVcIdFactPoblacionBarrera().equals("-1")){
      if(this.getObjFiltroBean().getVcIdTipoAlcanceBarrera().equals("2")){//Persona sin negocio
        nuMultaD = Double.parseDouble(this.getObjFiltroBean().getVcIdFactPoblacionBarrera());

      }else if(this.getObjFiltroBean().getVcIdTipoAlcanceBarrera().equals("1")){//Persona con negocio
        nuMultaD = (0.5*Double.parseDouble(this.getObjFiltroBean().getVcIdFactPoblacionBarrera()))+(0.5*this.getObjFiltroBean().getNuValorSecEconomico());
      }

      logger.debug("calc> nuMultaD: "+nuMultaD);
      this.getObjFiltroBean().setNuAlcanceBarrera(round(nuMultaD, 2));
      this.getObjFiltroBean().setNuMultaDBarrera(round(nuMultaD*this.getObjFiltroBean().getNuDanioBaseBarrera(), 2));
    }
    logger.info(">>FIN calculaMultaDBarreras ");
  }

  public void doSeleccionarAA(){
      logger.info(">>doSeleccionarAA ");
      logger.debug("this.getObjFiltroBean().getIsBlMultaBase() "+this.getObjFiltroBean().isIsBlMultaBase());
      logger.debug("this.getObjFiltroBean().getIsBlMultaBaseLibro() "+this.getObjFiltroBean().isIsBlMultaBaseLibro());
      logger.debug("this.getObjFiltroBean().getIsBlMultaBaseBarrera() "+this.getObjFiltroBean().isIsBlMultaBaseBarrera());
      logger.debug("this.getObjFiltroBean().getIsBlMultaBasePi() "+this.getObjFiltroBean().isIsBlMultaBasePi());
      logger.debug("this.getObjFiltroBean().getIsBlMultaBaseCcd() "+this.getObjFiltroBean().isIsBlMultaBaseCcd());
      logger.debug("this.getObjFiltroBean().isIsBlMultaBaseFirma() "+this.getObjFiltroBean().isIsBlMultaBaseFirma());
      logger.debug("this.getObjFiltroBean().isIsBlMultaBaseVentas() "+this.getObjFiltroBean().isIsBlMultaBaseVentas());
      logger.debug("this.getObjFiltroBean().isIsBlMultaBaseAdhoc() "+this.getObjFiltroBean().isIsBlMultaBaseAdhoc());
      //this.doListarMetodosXOrgInstancia();
      if(this.getObjFiltroBean().isIsBlMultaBase()||this.getObjFiltroBean().isIsBlMultaBaseLibro()||this.getObjFiltroBean().isIsBlMultaBaseBarrera()||
          this.getObjFiltroBean().isIsBlMultaBasePi()||this.getObjFiltroBean().isIsBlMultaBaseCcd()||this.getObjFiltroBean().isIsBlMultaBaseFirma()||this.getObjFiltroBean().isIsBlMultaBaseVentas()||
          this.getObjFiltroBean().isIsBlMultaBaseAdhoc()){
      this.doListarAgravF1();
      this.doListarAgravF2();
      this.doListarAgravF3();
      this.doListarAgravF4();
      this.doListarAgravF5();
      this.doListarAgravF6();
      this.doListarAgravF01();
      this.doListarAgravF02();
      this.doListarAgravF03();
      this.doListarAtenF01();
      this.doListarAtenF02();
      this.doListarAtenF03();

      this.doListarAtenF7();
      this.doListarAtenF8();
      this.doListarAtenF9();
      this.doListarAtenF10();
      this.doListarAtenF11();
      
      this.getObjFiltroBean().setVcF1Val("No");
      this.getObjFiltroBean().setVcF2Val("No");
      this.getObjFiltroBean().setVcF3Val("No");
      this.getObjFiltroBean().setVcF4Val("No");
      this.getObjFiltroBean().setVcF5Val("No");
      this.getObjFiltroBean().setVcF6Val("No");
      this.getObjFiltroBean().setVcF7Val("No");
      this.getObjFiltroBean().setVcF8Val("No");
      this.getObjFiltroBean().setVcF9Val("No");
      this.getObjFiltroBean().setVcF10Val("No");
      this.getObjFiltroBean().setVcF11Val("No");
      this.getObjFiltroBean().setVcF01Val("No");
      this.getObjFiltroBean().setVcF02Val("No");
      this.getObjFiltroBean().setVcF03Val("No");
      this.getObjFiltroBean().setVcF01DctoVal("No");
      this.getObjFiltroBean().setVcF02DctoVal("No");
      this.getObjFiltroBean().setVcF03DctoVal("No");

      this.getObjFiltroBean().setNuValorIncrementoAA(0);
      this.getObjFiltroBean().setNuValorDescuentoAA(0);
      this.getObjFiltroBean().setNuValorIncrementoAATemp(0);
      this.getObjFiltroBean().setNuValorDescuentoAATemp(0);
      this.getObjFiltroBean().setNuValorFactorAA(1);
      }
      
      logger.info(">>Fin doSeleccionarAA");
  }

  public static Double findMin(List<Double> list){
      if (list == null || list.size() == 0) {
          return Double.MAX_VALUE;
      }

      List<Double> sortedlist = new ArrayList<>(list);
      Collections.sort(sortedlist);

      return sortedlist.get(0);
  }

  public void doSeleccionarF(){
      logger.info(">>doSeleccionarF ");
      //agravantes:
      double nuAgravanteCalculado, nuVc1, nuVc2, nuVc3, nuVc4, nuVc5, nuVc6, nuVc7, nuVc8, nuVc9, nuVc10, nuVc11, nuVcf01, nuVcf02, nuVcf03, nuVcf01Dcto, nuVcf02Dcto, nuVcf03Dcto = 0;
      nuVc1 = this.getObjFiltroBean().getVcF1Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF1Val());
      nuVc2 = this.getObjFiltroBean().getVcF2Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF2Val());
      nuVc3 = this.getObjFiltroBean().getVcF3Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF3Val());
      nuVc4 = this.getObjFiltroBean().getVcF4Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF4Val());
      nuVc5 = this.getObjFiltroBean().getVcF5Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF5Val());
      nuVc6 = this.getObjFiltroBean().getVcF6Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF6Val());
      nuVcf01 = this.getObjFiltroBean().getVcF01Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF01Val());
      nuVcf02 = this.getObjFiltroBean().getVcF02Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF02Val());
      nuVcf03 = this.getObjFiltroBean().getVcF03Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF03Val());
      nuAgravanteCalculado = nuVc1+ nuVc2+ nuVc3+ nuVc4+ nuVc5+ nuVc6 +nuVcf01 +nuVcf02 +nuVcf03;
      BigDecimal bdIncremento = new BigDecimal(nuAgravanteCalculado).setScale(2, RoundingMode.HALF_UP);
      logger.debug("1 incremento: "+ bdIncremento.doubleValue());
      /* if(nuAgravanteCalculado > 1.0){
        this.getObjFiltroBean().setNuValorIncrementoAATemp(1.0);
      }else{
        this.getObjFiltroBean().setNuValorIncrementoAATemp(bdIncremento.doubleValue());
      } */

      //atenuantes:
      double nuAtenuanteCalculado = 0;
      nuVc7 = this.getObjFiltroBean().getVcF7Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF7Val());
      nuVc8 = this.getObjFiltroBean().getVcF8Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF8Val());
      nuVc9 = this.getObjFiltroBean().getVcF9Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF9Val());
      nuVc10 = this.getObjFiltroBean().getVcF10Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF10Val());
      nuVc11 = this.getObjFiltroBean().getVcF11Val().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF11Val());
      nuVcf01Dcto = this.getObjFiltroBean().getVcF01DctoVal().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF01DctoVal());
      nuVcf02Dcto = this.getObjFiltroBean().getVcF02DctoVal().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF02DctoVal());
      nuVcf03Dcto = this.getObjFiltroBean().getVcF03DctoVal().equals("No") ? 0 : Double.parseDouble(this.getObjFiltroBean().getVcF03DctoVal());
      nuAtenuanteCalculado = nuVc7+ nuVc8+ nuVc9+ nuVc10+ nuVc11 +nuVcf01Dcto +nuVcf02Dcto +nuVcf03Dcto;
      BigDecimal bdDescuento = new BigDecimal(nuAtenuanteCalculado).setScale(3, RoundingMode.HALF_UP);
      logger.debug("2 descto: "+ bdDescuento.doubleValue());
      /* if(nuAtenuanteCalculado < -0.5){
        this.getObjFiltroBean().setNuValorDescuentoAATemp(-0.5);
      }else{
        this.getObjFiltroBean().setNuValorDescuentoAATemp(bdDescuento.doubleValue());
      } */

      this.getObjFiltroBean().setNuValorIncrementoAA(bdIncremento.doubleValue());
      this.getObjFiltroBean().setNuValorDescuentoAA(bdDescuento.doubleValue());

      logger.debug("3 incremento a sumar: "+ bdIncremento.doubleValue());
      logger.debug("3 dscto a sumar: "+ bdDescuento.doubleValue());
      //BigDecimal bdIncrementoSumar = new BigDecimal(this.getObjFiltroBean().getNuValorIncrementoAATemp()).setScale(2, RoundingMode.HALF_UP);
      //BigDecimal bdDescuentoSumar = new BigDecimal(this.getObjFiltroBean().getNuValorDescuentoAATemp()).setScale(2, RoundingMode.HALF_UP);
      //this.getObjFiltroBean().setNuValorFactorAA(1 + bdIncrementoSumar.doubleValue() + bdDescuentoSumar.doubleValue());
      
      BigDecimal bdtotal = new BigDecimal(bdIncremento.doubleValue()+bdDescuento.doubleValue()+1).setScale(3, RoundingMode.HALF_UP);
      logger.debug("RESUL: "+ bdtotal.doubleValue());
      if(bdtotal.doubleValue() < 0.5){
        this.getObjFiltroBean().setNuValorFactorAA(0.5);

      }else if(bdtotal.doubleValue() >= 0.5 && bdtotal.doubleValue() <= 2){
        this.getObjFiltroBean().setNuValorFactorAA(bdtotal.doubleValue());

      }else if(bdtotal.doubleValue() > 2.0){
        this.getObjFiltroBean().setNuValorFactorAA(2.0);
      }

      logger.debug("4 NuValorFactorAA DESPUES: "+ this.getObjFiltroBean().getNuValorFactorAA());

      logger.info(">>Fin doSeleccionarF");
  }

  public void doSeleccionarF01(){
      logger.info(">>doSeleccionarF01 ");
      logger.info(">>Fin doSeleccionarF01");
  }

  public void doSeleccionarTamanoEmpresa(){
      logger.info(">>doSeleccionarTamanoEmpresa ");
      logger.debug("this.getObjFiltroBean().getVcTamEmpresaSeleccion(): " + this.getObjFiltroBean().getVcTamEmpresaSeleccion());
      logger.info(">>Fin doSeleccionarTamanoEmpresa");
  }
  public void doSeleccionarTamanoEmpresaCcd(){
      logger.info(">>doSeleccionarTamanoEmpresaCcd ");
      logger.debug("this.getObjFiltroBean().getVcTamEmpresaSeleccionCcd(): " + this.getObjFiltroBean().getVcTamEmpresaSeleccionCcd());
      logger.info(">>Fin doSeleccionarTamanoEmpresaCcd");
  }
  public void doSeleccionarTamanoEmpresaPi(){
      logger.info(">>doSeleccionarTamanoEmpresaPi ");
      logger.debug("this.getObjFiltroBean().getVcTamEmpresaSeleccionPi(): " + this.getObjFiltroBean().getVcTamEmpresaSeleccionPi());
      logger.info(">>Fin doSeleccionarTamanoEmpresaPi");
  }

  public void doSeleccionarAnioUITMulta(){
      logger.info(">>doSeleccionarAnioUITMulta ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucion(): " + this.getObjFiltroBean().getVcAnioResolucion());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnual(): " + this.getObjFiltroBean().getNuFacturacionAnual());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucion().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());//valor de UIT de ese Anio
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnual() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
        }
      }                          
      logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
      logger.info(">>Fin doSeleccionarAnioUITMulta");
  }

  public void doSeleccionarAnioUITMultaYMultaBase(){
      logger.info(">>doSeleccionarAnioUITMultaYMultaBase ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucion(): " + this.getObjFiltroBean().getVcAnioResolucion());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnual(): " + this.getObjFiltroBean().getNuFacturacionAnual());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucion().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnual() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
        }
      }                          
      logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
      this.doCalcularMbase();
      logger.info(">>Fin doSeleccionarAnioUITMultaYMultaBase");
  }

  public void doSeleccionarAnioUITMultaLibro(){
      logger.info(">>doSeleccionarAnioUITMultaLibro ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionLibro(): " + this.getObjFiltroBean().getVcAnioResolucionLibro());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualLibro(): " + this.getObjFiltroBean().getNuFacturacionAnualLibro());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionLibro().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualLibro() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTopeLibro((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
        }
      }                          
      
      logger.info(">>Fin doSeleccionarAnioUITMultaLibro");
  }

  public void doSeleccionarAnioUITMultaLibroYMultaBase(){
      logger.info(">>doSeleccionarAnioUITMultaLibroYMultaBase ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionLibro(): " + this.getObjFiltroBean().getVcAnioResolucionLibro());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualLibro(): " + this.getObjFiltroBean().getNuFacturacionAnualLibro());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionLibro().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualLibro() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTopeLibro((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
        }
      }                          
      this.doCalcularMRefLibro();
      logger.info(">>Fin doSeleccionarAnioUITMultaLibroYMultaBase");
  }

public void doSeleccionarAnioUITMultaBarreras(){
      logger.info(">>doSeleccionarAnioUITMultaBarreras ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionBarrera(): " + this.getObjFiltroBean().getVcAnioResolucionBarrera());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualBarrera(): " + this.getObjFiltroBean().getNuFacturacionAnualBarrera());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionBarrera().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualBarrera() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);

        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));

      }                          
      
      logger.info(">>Fin doSeleccionarAnioUITMultaBarreras");
  }

public void doSeleccionarAnioUITMultaBarrerasYMultaBase(){
    logger.info(">>doSeleccionarAnioUITMultaBarrerasYMultaBase ");
    logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
    logger.debug("this.getObjFiltroBean().getVcAnioResolucionBarrera(): " + this.getObjFiltroBean().getVcAnioResolucionBarrera());
    logger.debug("this.getObjFiltroBean().getNuFacturacionAnualBarrera(): " + this.getObjFiltroBean().getNuFacturacionAnualBarrera());
    
    //hacer calcula tam empresa
    //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
    ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                .filter(p -> this.getObjFiltroBean().getVcAnioResolucionBarrera().equals(p.getVcIdMultaUIT()))
                                .findAny()
                                .orElse(null);
    if(obj != null){
      logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
      logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
      
      //obtengo UIT del anio seleccionado
      //con la facturacion ingresada / uit = uitD  
      double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualBarrera() / (double) obj.getNuUIT();
      logger.debug("facturacionUIT: " + facturacionUIT);

      this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
      this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));

    }                          
    this.doCalcularMbaseBarreras();
    logger.info(">>Fin doSeleccionarAnioUITMultaBarrerasYMultaBase");
}

public void doSeleccionarAnioUITMultaPi(){
      logger.info(">>doSeleccionarAnioUITMultaPi ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionPi(): " + this.getObjFiltroBean().getVcAnioResolucionPi());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualPi(): " + this.getObjFiltroBean().getNuFacturacionAnualPi());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionPi().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualPi() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
        }

      }                          
      
      logger.info(">>Fin doSeleccionarAnioUITMultaPi");
  }

public void doSeleccionarAnioUITMultaPiYMultaBase(){
    logger.info(">>doSeleccionarAnioUITMultaPiYMultaBase ");
    logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
    logger.debug("this.getObjFiltroBean().getVcAnioResolucionPi(): " + this.getObjFiltroBean().getVcAnioResolucionPi());
    logger.debug("this.getObjFiltroBean().getNuFacturacionAnualPi(): " + this.getObjFiltroBean().getNuFacturacionAnualPi());
    
    //hacer calcula tam empresa
    //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
    ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                .filter(p -> this.getObjFiltroBean().getVcAnioResolucionPi().equals(p.getVcIdMultaUIT()))
                                .findAny()
                                .orElse(null);
    if(obj != null){
      logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
      logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
      this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
      //obtengo UIT del anio seleccionado
      //con la facturacion ingresada / uit = uitD  
      double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualPi() / (double) obj.getNuUIT();
      logger.debug("facturacionUIT: " + facturacionUIT);
      int IntFacturacionEmpresa = (int) facturacionUIT;
      //
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
      if (objResult != null) {
            //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
            //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
            this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
            this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
            this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
            this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
            logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
      }

    }                          
    this.doCalcularMbasePi();
    logger.info(">>Fin doSeleccionarAnioUITMultaPiYMultaBase");
}

public void doSeleccionarAnioUITMultaCcd(){
      logger.info(">>doSeleccionarAnioUITMultaCcd ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionCcd(): " + this.getObjFiltroBean().getVcAnioResolucionCcd());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualCcd(): " + this.getObjFiltroBean().getNuFacturacionAnualCcd());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionCcd().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualCcd() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              //logger.debug("getVcIdTamEmpresa(): " + this.getObjFiltroBean().getVcIdTamEmpresa());
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
        }

      }                          
      
      logger.info(">>Fin doSeleccionarAnioUITMultaCcd");
  }

public void doSeleccionarAnioUITMultaCcdYMultaBase(){
  logger.info(">>doSeleccionarAnioUITMultaCcdYMultaBase ");
  logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
  logger.debug("this.getObjFiltroBean().getVcAnioResolucionCcd(): " + this.getObjFiltroBean().getVcAnioResolucionCcd());
  logger.debug("this.getObjFiltroBean().getNuFacturacionAnualCcd(): " + this.getObjFiltroBean().getNuFacturacionAnualCcd());
  
  //hacer calcula tam empresa
  //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
  ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                              .filter(p -> this.getObjFiltroBean().getVcAnioResolucionCcd().equals(p.getVcIdMultaUIT()))
                              .findAny()
                              .orElse(null);
  if(obj != null){
    logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
    logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
    this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
    //obtengo UIT del anio seleccionado
    //con la facturacion ingresada / uit = uitD  
    double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualCcd() / (double) obj.getNuUIT();
    logger.debug("facturacionUIT: " + facturacionUIT);
    int IntFacturacionEmpresa = (int) facturacionUIT;
    //
    ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
    ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
    if (objResult != null) {
          //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
          //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
          this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
          this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
          this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
          this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
          //logger.debug("getVcIdTamEmpresa(): " + this.getObjFiltroBean().getVcIdTamEmpresa());
          logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
    }

  }                          
  this.doCalcularMbaseCcd();
  logger.info(">>Fin doSeleccionarAnioUITMultaCcdYMultaBase");
}

  public void doSeleccionarAnioUITMultaFirma(){
      logger.info(">>doSeleccionarAnioUITMultaFirma ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionFirma(): " + this.getObjFiltroBean().getVcAnioResolucionFirma());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualFirma(): " + this.getObjFiltroBean().getNuFacturacionAnualFirma());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionFirma().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualFirma() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuFactorFirma((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
        }

      }                          
      
      logger.info(">>Fin doSeleccionarAnioUITMultaFirma");
  }

  public void doSeleccionarAnioUITMultaFirmaYMultaBase(){
      logger.info(">>doSeleccionarAnioUITMultaFirmaYMultaBase ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionFirma(): " + this.getObjFiltroBean().getVcAnioResolucionFirma());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualFirma(): " + this.getObjFiltroBean().getNuFacturacionAnualFirma());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionFirma().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualFirma() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuFactorFirma((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
        }

      }                          
      
      this.doCalcularMbaseFirma();
      logger.info(">>Fin doSeleccionarAnioUITMultaFirmaYMultaBase");
  }

  public void doSeleccionarAnioUITMultaVentas(){
      logger.info(">>doSeleccionarAnioUITMultaVentas ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionVentas(): " + this.getObjFiltroBean().getVcAnioResolucionVentas());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualVentas(): " + this.getObjFiltroBean().getNuFacturacionAnualVentas());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionVentas().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualVentas() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
        }

      }                          
      
      logger.info(">>Fin doSeleccionarAnioUITMultaVentas");
  }

  public void doSeleccionarAnioUITMultaVentasYMultaBase(){
      logger.info(">>doSeleccionarAnioUITMultaVentasYMultaBase ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionVentas(): " + this.getObjFiltroBean().getVcAnioResolucionVentas());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualVentas(): " + this.getObjFiltroBean().getNuFacturacionAnualVentas());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionVentas().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualVentas() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
        }

      }                          
      this.doCalcularMbaseVentas();
      logger.info(">>Fin doSeleccionarAnioUITMultaVentasYMultaBase");
  }

  public void doSeleccionarAnioUITMultaAdhoc(){
      logger.info(">>doSeleccionarAnioUITMultaAdhoc ");
      logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
      logger.debug("this.getObjFiltroBean().getVcAnioResolucionAdhoc(): " + this.getObjFiltroBean().getVcAnioResolucionAdhoc());
      logger.debug("this.getObjFiltroBean().getNuFacturacionAnualAdhoc(): " + this.getObjFiltroBean().getNuFacturacionAnualAdhoc());
      
      //hacer calcula tam empresa
      //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
      ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcAnioResolucionAdhoc().equals(p.getVcIdMultaUIT()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
        logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
        this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
        //obtengo UIT del anio seleccionado
        //con la facturacion ingresada / uit = uitD  
        double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualAdhoc() / (double) obj.getNuUIT();
        logger.debug("facturacionUIT: " + facturacionUIT);
        int IntFacturacionEmpresa = (int) facturacionUIT;
        //
        ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
        logger.debug("this.getObjFiltroBean().getVcMetodo(): " + this.getObjFiltroBean().getVcMetodo());
        ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
        if (objResult != null) {
              //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
              //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
              this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
              this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
              this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
              logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
              logger.debug("this.getObjFiltroBean().getNuPorcTope(): " + this.getObjFiltroBean().getNuPorcTope());
        }

      }                          
      
      logger.info(">>Fin doSeleccionarAnioUITMultaAdhoc");
  }

public void doSeleccionarAnioUITMultaAdhocYMultaBase(){
    logger.info(">>doSeleccionarAnioUITMultaAdhocYMultaBase ");
    logger.debug("this.lstMultaUITAnioBean.size(): " + this.lstMultaUITAnioBean.size());
    logger.debug("this.getObjFiltroBean().getVcAnioResolucionAdhoc(): " + this.getObjFiltroBean().getVcAnioResolucionAdhoc());
    logger.debug("this.getObjFiltroBean().getNuFacturacionAnualAdhoc(): " + this.getObjFiltroBean().getNuFacturacionAnualAdhoc());
    
    //hacer calcula tam empresa
    //String idAnio = (String) this.getObjFiltroBean().getVcAnioResolucion();
    ClsMultaUITAnioBean obj = this.lstMultaUITAnioBean.stream()
                                .filter(p -> this.getObjFiltroBean().getVcAnioResolucionAdhoc().equals(p.getVcIdMultaUIT()))
                                .findAny()
                                .orElse(null);
    if(obj != null){
      logger.debug("obj.getVcMultaUIT(): " + obj.getVcMultaUIT());
      logger.debug("obj.getNuUIT(): " + obj.getNuUIT());
      this.getObjFiltroBean().setNuAnioUIT(obj.getNuUIT());
      //obtengo UIT del anio seleccionado
      //con la facturacion ingresada / uit = uitD  
      double facturacionUIT = (double) this.getObjFiltroBean().getNuFacturacionAnualAdhoc() / (double) obj.getNuUIT();
      logger.debug("facturacionUIT: " + facturacionUIT);
      int IntFacturacionEmpresa = (int) facturacionUIT;
      //
      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      logger.debug("this.getObjFiltroBean().getVcMetodo(): " + this.getObjFiltroBean().getVcMetodo());
      ClsResultDAO objResult = objDAO.doGetTamEmpresa(Integer.parseInt(this.getObjFiltroBean().getVcMetodo()), IntFacturacionEmpresa);
      if (objResult != null) {
            //this.setLstInstancias((Map<String, String>) objResult.get("SP_LST_INSTANCIAS"));
            //this.getObjFiltroBean().setVcIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
            this.setNuIdTamEmpresa((Integer) objResult.get("GET_ID_TAM_EMPRESA"));
            this.getObjFiltroBean().setVcTamEmpresa(objResult.get("GET_TAM_EMPRESA")+"");
            this.getObjFiltroBean().setNuPorcTope((Double) objResult.get("GET_PORC_TOPE"));
            this.getObjFiltroBean().setNuValorUIT(round(facturacionUIT,2));
            logger.debug("this.getNuIdTamEmpresa(): " + this.getNuIdTamEmpresa());
            logger.debug("this.getObjFiltroBean().getNuPorcTope(): " + this.getObjFiltroBean().getNuPorcTope());
      }

    }                          
    this.doCalcularMbaseAdhoc();
    logger.info(">>Fin doSeleccionarAnioUITMultaAdhocYMultaBase");
}

public void doSeleccionarProbabilidadBarreras(){
      logger.info(">>doSeleccionarProbabilidadBarreras ");
      logger.debug("this.lstProbabilidadBarrerasBean.size(): " + this.lstProbabilidadBarrerasBean.size());
      logger.debug("this.getObjFiltroBean().getVcCaso(): " + this.getObjFiltroBean().getVcCaso());
      
      ClsProbabilidadBarrerasBean obj = this.lstProbabilidadBarrerasBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcCaso().equals(p.getVcCaso()))
                                  .findAny()
                                  .orElse(null);
      if(obj != null){
        logger.debug("obj.getVcCaso(): " + obj.getVcCaso());
        logger.debug("obj.getVcDefinicion(): " + obj.getVcDefinicion());
        logger.debug("obj.getNuFactorProbabilidad(): " + obj.getNuFactorProbabilidad());
        this.getObjFiltroBean().setNuFactorPBarrera(obj.getNuFactorProbabilidad());
      }                          
      
      logger.info(">>Fin doSeleccionarProbabilidadBarreras");
  }

  /*public void calculaFD(){
      logger.info(">>calculaFD()");

      try{
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
      String vcfechaini = sdf.format(this.getObjFiltroBean().getDtFechaFin().getTime());
      String vcfechafin = sdf.format(this.getObjFiltroBean().getDtFechaInicio().getTime());
        
      Date fechaInicial = sdf.parse(sdf.format(this.getObjFiltroBean().getDtFechaFin().getTime()));
      Date fechaFinal = sdf.parse(sdf.format(this.getObjFiltroBean().getDtFechaInicio().getTime()));
        
      int dias=(int) ((fechaInicial.getTime()-fechaFinal.getTime())/86400000);
      logger.debug("dias>>> "+dias);

      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetFD(dias);
      if (objResult != null) {
          this.getObjFiltroBean().setNuFactorDuracion((Double) objResult.get("GET_FACTOR_DURACION"));
      }
      }catch (Exception e ){
          e.printStackTrace();
      }
  }*/

  public void calculaFD(){ // PREESTABLECIDO y PREESTABLECIDO CCD
      logger.info(">>calculaFD()");
      
    try{
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int dias = 0;

      if(this.getObjFiltroBean().getVcMetodo().equals("1")){
      //vcfechaini = sdf.format(this.getObjFiltroBean().getDtFechaFin().getTime());
      //vcfechafin = sdf.format(this.getObjFiltroBean().getDtFechaInicio().getTime());
        
      Date fechaInicial = sdf.parse(sdf.format(this.getObjFiltroBean().getDtFechaFin().getTime()));
      Date fechaFinal = sdf.parse(sdf.format(this.getObjFiltroBean().getDtFechaInicio().getTime()));
        dias=(int) ((fechaInicial.getTime()-fechaFinal.getTime())/86400000);

      }else if(this.getObjFiltroBean().getVcMetodo().equals("7")){
      //vcfechaini = sdf.format(this.getObjFiltroBean().getDtFechaFinCcd().getTime());
      //vcfechafin = sdf.format(this.getObjFiltroBean().getDtFechaInicioCcd().getTime());
        
      Date fechaInicial = sdf.parse(sdf.format(this.getObjFiltroBean().getDtFechaFinCcd().getTime()));
      Date fechaFinal = sdf.parse(sdf.format(this.getObjFiltroBean().getDtFechaInicioCcd().getTime()));
        dias=(int) ((fechaInicial.getTime()-fechaFinal.getTime())/86400000);
      }

/*      try{
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
      String vcfechaini = sdf.format(this.getObjFiltroBean().getDtFechaFin().getTime());
      String vcfechafin = sdf.format(this.getObjFiltroBean().getDtFechaInicio().getTime());
        
      Date fechaInicial = sdf.parse(sdf.format(this.getObjFiltroBean().getDtFechaFin().getTime()));
      Date fechaFinal = sdf.parse(sdf.format(this.getObjFiltroBean().getDtFechaInicio().getTime()));
         */
      //int dias=(int) ((fechaInicial.getTime()-fechaFinal.getTime())/86400000);

      if(this.getObjFiltroBean().getVcMetodo().equals("1")||this.getObjFiltroBean().getVcMetodo().equals("7")){
      logger.debug("dias>>> "+dias);

      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetFD(dias);
      if (objResult != null) {
          this.getObjFiltroBean().setNuFactorDuracion((Double) objResult.get("GET_FACTOR_DURACION"));
      }
      }


      }catch (Exception e ){
          e.printStackTrace();
      }
      logger.info(">>FIN calculaFD()");
  }

  public void doCargarModalListaInfracciones() {
      logger.info(">>doCargarModalListaInfracciones");
      logger.debug("this.getObjFiltroBean().isIsBlLstAfectacion(): " + this.getObjFiltroBean().isIsBlLstAfectacion());
      logger.debug("this.getVcMuestraBarreras(): " + this.getVcMuestraBarreras());
      logger.info(">>Fin doCargarModalListaInfracciones");
  }

  public void doSeleccionarInfraccionModal(ClsTipoAfectacionBean obj) {
      logger.info(">>doSeleccionarInfraccionModal");
      try {
        if(this.getObjFiltroBean().getVcMetodo().equals("1")){//PRESTABLECIDO logica doSeleccionarInfraccionXYZ
          logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
          logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
          logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
          logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
          this.getObjFiltroBean().setVcIdNivelAfectacion(obj.getVcIdNivelAfectacion());
          this.getObjFiltroBean().setVcTipoAfectacion(obj.getVcTipoAfectacion());
          this.getObjFiltroBean().setVcNivelAfectacion(obj.getVcNivelAfectacion());
          //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());

        }else if(this.getObjFiltroBean().getVcMetodo().equals("2")){//LIBRO
          logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
          logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
          logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
          this.getObjFiltroBean().setVcIdNivelAfectacion(obj.getVcIdNivelAfectacion());
          this.getObjFiltroBean().setVcTipoAfectacionLibro(obj.getVcTipoAfectacion());
          //this.getObjFiltroBean().setVcNivelAfectacion(obj.getVcNivelAfectacion());
          this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());

        }else if(this.getObjFiltroBean().getVcMetodo().equals("3")){//VENTAS
          logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
          logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
          logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
          logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
          logger.debug("obj.getNuDanioBase(): " + obj.getNuDanioBase());
          logger.debug("obj.getNuFactorG(): " + obj.getNuFactorG());
          this.getObjFiltroBean().setVcIdNivelAfectacionVentas(obj.getVcIdNivelAfectacion());
          this.getObjFiltroBean().setVcTipoAfectacionVentas(obj.getVcTipoAfectacion());
          this.getObjFiltroBean().setVcNivelAfectacionVentas(obj.getVcNivelAfectacion());
          this.getObjFiltroBean().setNuFactorGVentas(obj.getNuFactorG());

        }else if(this.getObjFiltroBean().getVcMetodo().equals("4")){//ADHOC
          logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
          logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
          logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
          logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
          logger.debug("obj.getNuDanioBase(): " + obj.getNuDanioBase());
          logger.debug("obj.getNuFactorG(): " + obj.getNuFactorG());
          this.getObjFiltroBean().setVcIdNivelAfectacionAdhoc(obj.getVcIdNivelAfectacion());
          this.getObjFiltroBean().setVcTipoAfectacionAdhoc(obj.getVcTipoAfectacion());
          this.getObjFiltroBean().setVcNivelAfectacionAdhoc(obj.getVcNivelAfectacion());
          this.getObjFiltroBean().setNuFactorPAdhoc(obj.getNuFactorG());
          this.getObjFiltroBean().setVcFactorPAdhoc(obj.getNuFactorG()+"%");
          
        }else if(this.getObjFiltroBean().getVcMetodo().equals("5")){//BARRERAS
          logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
          logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
          logger.debug("obj.getVcClasificacionBarrera(): " + obj.getVcClasificacionBarrera());
          //this.getObjFiltroBean().setVcIdNivelAfectacion(obj.getVcIdNivelAfectacion());
          this.getObjFiltroBean().setVcTipoAfectacionBarrera(obj.getVcTipoAfectacion());
          //this.getObjFiltroBean().setVcNivelAfectacion(obj.getVcNivelAfectacion());
          this.getObjFiltroBean().setVcClasificacionBarrera(obj.getVcClasificacionBarrera());

          ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
          ClsResultDAO objResult = objDAO.doGetDanioBarreras(obj.getVcClasificacionBarrera());
          if (objResult != null) {
            this.getObjFiltroBean().setNuDanioBaseBarrera((Integer) objResult.get("GET_DANIO_BASE"));
            this.getObjFiltroBean().setNuAjusteMinBarrera((Double) objResult.get("GET_AJUSTE_MIN"));
            this.getObjFiltroBean().setNuAjusteMaxBarrera((Double) objResult.get("GET_AJUSTE_MAX"));
          }
        }else if(this.getObjFiltroBean().getVcMetodo().equals("6")){//PI
          logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
          logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
          logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
          logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
          this.getObjFiltroBean().setVcIdNivelAfectacionPi(obj.getVcIdNivelAfectacion());
          this.getObjFiltroBean().setVcTipoAfectacionPi(obj.getVcTipoAfectacion());
          this.getObjFiltroBean().setVcNivelAfectacionPi(obj.getVcNivelAfectacion());
          //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());

        }else if(this.getObjFiltroBean().getVcMetodo().equals("7")){//CCD
          logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
          logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
          logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
          logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
          this.getObjFiltroBean().setVcIdNivelAfectacionCcd(obj.getVcIdNivelAfectacion());
          this.getObjFiltroBean().setVcTipoAfectacionCcd(obj.getVcTipoAfectacion());
          this.getObjFiltroBean().setVcNivelAfectacionCcd(obj.getVcNivelAfectacion());
          //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());

        }else if(this.getObjFiltroBean().getVcMetodo().equals("8")){//FIRMA
          logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
          logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
          logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
          logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
          logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
          logger.debug("obj.getNuDanioBase(): " + obj.getNuDanioBase());
          this.getObjFiltroBean().setVcIdNivelAfectacionFirma(obj.getVcIdNivelAfectacion());
          this.getObjFiltroBean().setVcTipoAfectacionFirma(obj.getVcTipoAfectacion());
          this.getObjFiltroBean().setVcNivelAfectacionFirma(obj.getVcClasificacion());
          this.getObjFiltroBean().setNuDanioBaseFirma(obj.getNuDanioBase());

        }

          
      } catch (Exception e) {
          logger.error(">>Error doSeleccionarInfraccionModal>Exception>" + e.getMessage());
      }

      logger.info(">>Fin doSeleccionarInfraccionModal");
  }

  public void doSeleccionarInfraccion(){
      logger.info(">>doSeleccionarInfraccion ");
      logger.debug("this.getObjFiltroBean().getVcInfraccion(): " + this.getObjFiltroBean().getVcInfraccion());
      logger.debug("this.lstAfectacionBean.size(): " + this.lstAfectacionBean.size());
      if(!this.getObjFiltroBean().getVcInfraccion().equals("-1")){
      ClsTipoAfectacionBean obj = this.lstAfectacionBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcInfraccion().equals(p.getVcIdTipoAfectacion()))
                                  .findAny()
                                  .orElse(null);

      logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
      logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
      logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
      logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
      logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
      this.getObjFiltroBean().setVcIdNivelAfectacion(obj.getVcIdNivelAfectacion());
      this.getObjFiltroBean().setVcTipoAfectacion(obj.getVcTipoAfectacion());
      this.getObjFiltroBean().setVcNivelAfectacion(obj.getVcNivelAfectacion());
      //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());
      }
      logger.info(">>Fin doSeleccionarInfraccion");
  }

  public void doSeleccionarInfraccionLibro(){
      logger.info(">>doSeleccionarInfraccionLibro ");
      logger.debug("this.getObjFiltroBean().getVcInfraccionLibro(): " + this.getObjFiltroBean().getVcInfraccionLibro());
      logger.debug("this.lstAfectacionBean.size(): " + this.lstAfectacionBean.size());

      if(!this.getObjFiltroBean().getVcInfraccionLibro().equals("-1")){
        ClsTipoAfectacionBean obj = this.lstAfectacionBean.stream()
                                    .filter(p -> this.getObjFiltroBean().getVcInfraccionLibro().equals(p.getVcIdTipoAfectacion()))
                                    .findAny()
                                    .orElse(null);

        logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
        logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
        logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
        logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
        logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
        this.getObjFiltroBean().setVcIdNivelAfectacion(obj.getVcIdNivelAfectacion());
        this.getObjFiltroBean().setVcTipoAfectacionLibro(obj.getVcTipoAfectacion());
        //this.getObjFiltroBean().setVcNivelAfectacion(obj.getVcNivelAfectacion());
        this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());
      }
      

      logger.info(">>Fin doSeleccionarInfraccionLibro");
  }
  
  public void doSeleccionarInfraccionBarreras(){
      logger.info(">>doSeleccionarInfraccionBarreras ");
      logger.debug("this.getObjFiltroBean().getVcInfraccionBarrera(): " + this.getObjFiltroBean().getVcInfraccionBarrera());
      logger.debug("this.lstAfectacionBean.size(): " + this.lstAfectacionBean.size());

      if(!this.getObjFiltroBean().getVcInfraccionBarrera().equals("-1")){
      ClsTipoAfectacionBean obj = this.lstAfectacionBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcInfraccionBarrera().equals(p.getVcIdTipoAfectacion()))
                                  .findAny()
                                  .orElse(null);

      logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
      logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
      logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
      logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
      logger.debug("obj.getVcClasificacionBarrera(): " + obj.getVcClasificacionBarrera());
      //this.getObjFiltroBean().setVcIdNivelAfectacion(obj.getVcIdNivelAfectacion());
      this.getObjFiltroBean().setVcTipoAfectacionBarrera(obj.getVcTipoAfectacion());
      //this.getObjFiltroBean().setVcNivelAfectacion(obj.getVcNivelAfectacion());
      this.getObjFiltroBean().setVcClasificacionBarrera(obj.getVcClasificacionBarrera());

      ClsCalculoMultaIDAO objDAO = new ClsCalculoMultaDAO();
      ClsResultDAO objResult = objDAO.doGetDanioBarreras(obj.getVcClasificacionBarrera());
      if (objResult != null) {
        this.getObjFiltroBean().setNuDanioBaseBarrera((Integer) objResult.get("GET_DANIO_BASE"));
        this.getObjFiltroBean().setNuAjusteMinBarrera((Double) objResult.get("GET_AJUSTE_MIN"));
        this.getObjFiltroBean().setNuAjusteMaxBarrera((Double) objResult.get("GET_AJUSTE_MAX"));
      }
      }
      logger.info(">>Fin doSeleccionarInfraccionBarreras");
  }

  public void doSeleccionarInfraccionPi(){
      logger.info(">>doSeleccionarInfraccionPi ");
      logger.debug("this.getObjFiltroBean().getVcInfraccionPi(): " + this.getObjFiltroBean().getVcInfraccionPi());
      logger.debug("this.lstAfectacionBean.size(): " + this.lstAfectacionBean.size());
      if(!this.getObjFiltroBean().getVcInfraccionPi().equals("-1")){
      ClsTipoAfectacionBean obj = this.lstAfectacionBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcInfraccionPi().equals(p.getVcIdTipoAfectacion()))
                                  .findAny()
                                  .orElse(null);

      logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
      logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
      logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
      logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
      logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
      this.getObjFiltroBean().setVcIdNivelAfectacionPi(obj.getVcIdNivelAfectacion());
      this.getObjFiltroBean().setVcTipoAfectacionPi(obj.getVcTipoAfectacion());
      this.getObjFiltroBean().setVcNivelAfectacionPi(obj.getVcNivelAfectacion());
      //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());
      }
      logger.info(">>Fin doSeleccionarInfraccionPi");
  }

  public void doSeleccionarInfraccionCcd(){
      logger.info(">>doSeleccionarInfraccionCcd ");
      logger.debug("this.getObjFiltroBean().getVcInfraccionCcd(): " + this.getObjFiltroBean().getVcInfraccionCcd());
      logger.debug("this.lstAfectacionBean.size(): " + this.lstAfectacionBean.size());
      if(!this.getObjFiltroBean().getVcInfraccionCcd().equals("-1")){
      ClsTipoAfectacionBean obj = this.lstAfectacionBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcInfraccionCcd().equals(p.getVcIdTipoAfectacion()))
                                  .findAny()
                                  .orElse(null);

      logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
      logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
      logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
      logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
      logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
      this.getObjFiltroBean().setVcIdNivelAfectacionCcd(obj.getVcIdNivelAfectacion());
      this.getObjFiltroBean().setVcTipoAfectacionCcd(obj.getVcTipoAfectacion());
      this.getObjFiltroBean().setVcNivelAfectacionCcd(obj.getVcNivelAfectacion());
      //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());
      }
      logger.info(">>Fin doSeleccionarInfraccionCcd");
  }

  public void doSeleccionarInfraccionFirma(){
      logger.info(">>doSeleccionarInfraccionFirma ");
      logger.debug("this.getObjFiltroBean().getVcInfraccionFirma(): " + this.getObjFiltroBean().getVcInfraccionFirma());
      logger.debug("this.lstAfectacionBean.size(): " + this.lstAfectacionBean.size());
      if(!this.getObjFiltroBean().getVcInfraccionFirma().equals("-1")){
      ClsTipoAfectacionBean obj = this.lstAfectacionBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcInfraccionFirma().equals(p.getVcIdTipoAfectacion()))
                                  .findAny()
                                  .orElse(null);

      logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
      logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
      logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
      logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
      logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
      logger.debug("obj.getNuDanioBase(): " + obj.getNuDanioBase());
      this.getObjFiltroBean().setVcIdNivelAfectacionFirma(obj.getVcIdNivelAfectacion());
      this.getObjFiltroBean().setVcTipoAfectacionFirma(obj.getVcTipoAfectacion());
      this.getObjFiltroBean().setVcNivelAfectacionFirma(obj.getVcClasificacion());
      this.getObjFiltroBean().setNuDanioBaseFirma(obj.getNuDanioBase());
      //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());
      }
      logger.info(">>Fin doSeleccionarInfraccionFirma");
  }

  public void doSeleccionarInfraccionVentas(){
      logger.info(">>doSeleccionarInfraccionVentas ");
      logger.debug("this.getObjFiltroBean().getVcInfraccionVentas(): " + this.getObjFiltroBean().getVcInfraccionVentas());
      logger.debug("this.lstAfectacionBean.size(): " + this.lstAfectacionBean.size());
      if(!this.getObjFiltroBean().getVcInfraccionVentas().equals("-1")){
      ClsTipoAfectacionBean obj = this.lstAfectacionBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcInfraccionVentas().equals(p.getVcIdTipoAfectacion()))
                                  .findAny()
                                  .orElse(null);

      logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
      logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
      logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
      logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
      logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
      logger.debug("obj.getNuDanioBase(): " + obj.getNuDanioBase());
      logger.debug("obj.getNuFactorG(): " + obj.getNuFactorG());
      this.getObjFiltroBean().setVcIdNivelAfectacionVentas(obj.getVcIdNivelAfectacion());
      this.getObjFiltroBean().setVcTipoAfectacionVentas(obj.getVcTipoAfectacion());
      this.getObjFiltroBean().setVcNivelAfectacionVentas(obj.getVcNivelAfectacion());
      this.getObjFiltroBean().setNuFactorGVentas(obj.getNuFactorG());
      //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());
      }
      logger.info(">>Fin doSeleccionarInfraccionVentas");
  }

  public void doSeleccionarInfraccionAdhoc(){
      logger.info(">>doSeleccionarInfraccionAdhoc ");
      logger.debug("this.getObjFiltroBean().getVcInfraccionAdhoc(): " + this.getObjFiltroBean().getVcInfraccionAdhoc());
      logger.debug("this.lstAfectacionBean.size(): " + this.lstAfectacionBean.size());
      if(!this.getObjFiltroBean().getVcInfraccionAdhoc().equals("-1")){
      ClsTipoAfectacionBean obj = this.lstAfectacionBean.stream()
                                  .filter(p -> this.getObjFiltroBean().getVcInfraccionAdhoc().equals(p.getVcIdTipoAfectacion()))
                                  .findAny()
                                  .orElse(null);

      logger.debug("obj.getVcIdTipoAfectacion(): " + obj.getVcIdTipoAfectacion());
      logger.debug("obj.getVcTipoAfectacion(): " + obj.getVcTipoAfectacion());
      logger.debug("obj.getVcNivelAfectacion(): " + obj.getVcNivelAfectacion());
      logger.debug("obj.getVcIdNivelAfectacion(): " + obj.getVcIdNivelAfectacion());
      logger.debug("obj.getVcClasificacion(): " + obj.getVcClasificacion());
      logger.debug("obj.getNuDanioBase(): " + obj.getNuDanioBase());
      logger.debug("obj.getNuFactorG(): " + obj.getNuFactorG());
      this.getObjFiltroBean().setVcIdNivelAfectacionAdhoc(obj.getVcIdNivelAfectacion());
      this.getObjFiltroBean().setVcTipoAfectacionAdhoc(obj.getVcTipoAfectacion());
      this.getObjFiltroBean().setVcNivelAfectacionAdhoc(obj.getVcNivelAfectacion());
      this.getObjFiltroBean().setNuFactorPAdhoc(obj.getNuFactorG());
      this.getObjFiltroBean().setVcFactorPAdhoc(obj.getNuFactorG()+"%");
      //this.getObjFiltroBean().setVcClasificacion(obj.getVcClasificacion());
      }
      logger.info(">>Fin doSeleccionarInfraccionAdhoc");
  }

public void doReportePreliminar(){
    logger.info(">>doReportePreliminar() ");
    try{
            
    ClsProperties clsProps = new ClsProperties();
    String rutasProps;
        String rutasProps2;

    rutasProps = clsProps.doUbicacion();

    String[] vcSplitRutasProps = rutasProps.split("¬");
    String vcPath = vcSplitRutasProps[0];
    String vcPathImg  = vcSplitRutasProps[1];
    String vcSeparador  = vcSplitRutasProps[2];
    logger.debug("vcPath: " + vcPath);
    logger.debug("vcPathImg: " + vcPathImg);

    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ClsUtils.doListDataSource()); 
    File reportFile = null;
    HashMap map = new HashMap();
    //map.put("vcPathImagen", "F:/TI_JOBS/Indecopi/jasper/img/");
    map.put("vcPathImagen", vcPathImg);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    map.put("vcFecha", dtf.format(LocalDateTime.now()));
    map.put("vcHora", dtf2.format(LocalDateTime.now()));
    String vcNombreArchivo = "doc_" + ClsUtils.doGenerarNombreArchivoAleatorio();

    if(this.getObjFiltroBean().getVcMetodo().equals("1")){

      //reportFile = new File("F:/TI_JOBS/Indecopi/ireport/calmulta_jasper_consumidor_pre.jasper");
      reportFile = new File(vcPath+"calmulta_jasper_consumidor_pre.jasper");
      if(reportFile.exists()){
          logger.info("Existe el archivo.");
      }else{
          logger.info("No existe el archivo.");
      }
        
      map.put("vcOrgResolutivo", this.getObjFiltroBean().getVcOrgResolutivo());
      map.put("vcRuc", this.getObjFiltroBean().getVcRuc());
      map.put("vcRazonSocial", this.getObjFiltroBean().getVcRazonSocial());
      map.put("vcTamEmpresa", this.getObjFiltroBean().getVcTamEmpresa());
      map.put("vcTipoAfectacion", this.getObjFiltroBean().getVcTipoAfectacion());
      map.put("vcNivelAfectacion", this.getObjFiltroBean().getVcNivelAfectacion());
      map.put("nuFactorDuracion", this.getObjFiltroBean().getNuFactorDuracion());
      map.put("nuMultaBase", this.getObjFiltroBean().getNuMultaBase());
      map.put("nuValorFactorAA", this.getObjFiltroBean().getNuValorFactorAA());
      map.put("nuMultaPreUIT", this.getObjFiltroBean().getNuMultaPreUIT());
        map.put("nuGravedadTope", this.getObjFiltroBean().getNuGravedadTope());
      map.put("nuIngresosTope", this.getObjFiltroBean().getNuIngresosTope());
      map.put("nuMultaFinal", this.getObjFiltroBean().getNuMultaFinal());
      
      logger.info("reportFile.getPath(): "+ reportFile.getPath());
      logger.info("vcNombreArchivo: "+ vcNombreArchivo);

    }else if(this.getObjFiltroBean().getVcMetodo().equals("2")){

      //reportFile = new File("F:/TI_JOBS/Indecopi/ireport/calmulta_jasper_libro_reclam.jasper");
      reportFile = new File(vcPath+"calmulta_jasper_libro_reclam.jasper");
      if(reportFile.exists()){
          logger.info("Existe el archivo.");
      }else{
          logger.info("No existe el archivo.");
      }
        
      map.put("vcOrgResolutivo", this.getObjFiltroBean().getVcOrgResolutivo());
      map.put("vcRuc", this.getObjFiltroBean().getVcRucLibro());
      map.put("vcRazonSocial", this.getObjFiltroBean().getVcRazonSocial());
      map.put("vcTamEmpresa", this.getObjFiltroBean().getVcTamEmpresa());
      map.put("vcTipoAfectacion", this.getObjFiltroBean().getVcTipoAfectacionLibro());
      map.put("vcClasificacion", this.getObjFiltroBean().getVcClasificacion());
      map.put("nuFactorFci", this.getObjFiltroBean().getNuFactorFci());
      map.put("nuMultaBase", this.getObjFiltroBean().getNuMultaBaseLibro());
      map.put("nuMultaRef", this.getObjFiltroBean().getNuMultaRefLibro());
      map.put("nuValorFactorAA", this.getObjFiltroBean().getNuValorFactorAA());
      map.put("nuMultaPre", this.getObjFiltroBean().getNuMultaAALibro());
      map.put("nuAjusteLeg", this.getObjFiltroBean().getNuAjusIngLibro());
      map.put("nuMultaFinal", this.getObjFiltroBean().getNuMultaFinal());
      
      logger.info("reportFile.getPath(): "+ reportFile.getPath());
      logger.info("vcNombreArchivo: "+ vcNombreArchivo);

    } else if(this.getObjFiltroBean().getVcMetodo().equals("3")){

      //reportFile = new File("F:/TI_JOBS/Indecopi/ireport/calmulta_jasper_libro_reclam.jasper");
      reportFile = new File(vcPath+"calmulta_jasper_pventas.jasper");
      if(reportFile.exists()){
          logger.info("Existe el archivo.");
      }else{
          logger.info("No existe el archivo.");
      }
        
      map.put("vcOrgResolutivo", this.getObjFiltroBean().getVcOrgResolutivo());
      map.put("vcRuc", this.getObjFiltroBean().getVcRucVentas());
      map.put("vcRazonSocial", this.getObjFiltroBean().getVcRazonSocial());
      map.put("nuFacturacion", this.getObjFiltroBean().getNuFacturacionAnualProductoVentas());
      map.put("nuFactorA", this.getObjFiltroBean().getNuFactorAVentas());
      map.put("vcCaracteristicaNivel", this.getObjFiltroBean().getVcTipoAfectacionVentas());
      map.put("vcNivel", this.getObjFiltroBean().getVcNivelAfectacionVentas());
      map.put("nuFactorG", this.getObjFiltroBean().getNuFactorGVentas());
      map.put("nuMultaBase", this.getObjFiltroBean().getNuMultaBaseVentas());
      map.put("nuValorFactorAA", this.getObjFiltroBean().getNuValorFactorAA());
      map.put("nuMultaPre", this.getObjFiltroBean().getNuMultaAAVentas());
      
      map.put("vcAjusteGravedad", this.getObjFiltroBean().getVcGravedadTopeVentas());
      map.put("nuAjusteLeg", this.getObjFiltroBean().getNuAjusteIngVentas());
      map.put("nuMultaFinal", this.getObjFiltroBean().getNuMultaFinal());
      
      logger.info("reportFile.getPath(): "+ reportFile.getPath());
      logger.info("vcNombreArchivo: "+ vcNombreArchivo);

    } else if(this.getObjFiltroBean().getVcMetodo().equals("4")){

      //reportFile = new File("F:/TI_JOBS/Indecopi/ireport/calmulta_jasper_libro_reclam.jasper");
      reportFile = new File(vcPath+"calmulta_jasper_adhoc.jasper");
      if(reportFile.exists()){
          logger.info("Existe el archivo.");
      }else{
          logger.info("No existe el archivo.");
      }
        
      logger.debug("getVcGravedadTopeAdhoc "+ this.getObjFiltroBean().getVcGravedadTopeAdhoc());

      map.put("vcOrgResolutivo", this.getObjFiltroBean().getVcOrgResolutivo());
      map.put("vcRuc", this.getObjFiltroBean().getVcRucAdhoc());
      map.put("vcRazonSocial", this.getObjFiltroBean().getVcRazonSocial());
      map.put("nuFactorB", this.getObjFiltroBean().getNuFactorBAdhoc());
      map.put("vcCaracteristicaNivel", this.getObjFiltroBean().getVcTipoAfectacionAdhoc());
      map.put("vcNivel", this.getObjFiltroBean().getVcNivelAfectacionAdhoc());
      map.put("nuFactorP", this.getObjFiltroBean().getNuFactorPAdhoc());
      map.put("vcFactorP", this.getObjFiltroBean().getVcFactorPAdhoc());
      map.put("nuMultaBase", this.getObjFiltroBean().getNuMultaBaseUITAdhoc());
      map.put("nuValorFactorAA", this.getObjFiltroBean().getNuValorFactorAA());
      map.put("nuMultaPre", this.getObjFiltroBean().getNuMultaAAAdhoc());
      
      map.put("vcAjusteGravedad", this.getObjFiltroBean().getVcGravedadTopeAdhoc());
      map.put("nuAjusteLeg", this.getObjFiltroBean().getNuAjusteIngAdhoc());
      map.put("nuMultaFinal", this.getObjFiltroBean().getNuMultaFinal());
      
      logger.info("reportFile.getPath(): "+ reportFile.getPath());
      logger.info("vcNombreArchivo: "+ vcNombreArchivo);

    } else if(this.getObjFiltroBean().getVcMetodo().equals("5")){

      //reportFile = new File("F:/TI_JOBS/Indecopi/ireport/calmulta_jasper_consumidor_pre.jasper");
      reportFile = new File(vcPath+"calmulta_jasper_barreras.jasper");
      if(reportFile.exists()){
          logger.info("Existe el archivo.");
      }else{
          logger.info("No existe el archivo.");
      }
        
      logger.debug("vcOrgResolutivo "+ this.getObjFiltroBean().getVcOrgResolutivo());
      logger.debug("vcTipoInfractor "+ this.getObjFiltroBean().getVcTipoBarrera());
      logger.debug("vcRuc "+ this.getObjFiltroBean().getVcRucBarreras());
      logger.debug("vcRazonSocial "+ this.getObjFiltroBean().getVcRazonSocial());
      logger.debug("nuIngresosAnual "+ this.getObjFiltroBean().getNuFacturacionAnualBarrera());
      logger.debug("vcTipoAfectacion "+ this.getObjFiltroBean().getVcTipoAfectacionBarrera());
      logger.debug("vcNivelAfectacion "+ this.getObjFiltroBean().getVcClasificacionBarrera());
      logger.debug("nuDano "+ this.getObjFiltroBean().getNuDanioBaseBarrera());
      logger.debug("vcAlcanceAfectacion "+ this.getObjFiltroBean().getVcTipoAlcanceBarrera());
      logger.debug("nuBarrera "+ this.getObjFiltroBean().getNuAlcanceBarrera());
      logger.debug("nuValorFactorAA "+ this.getObjFiltroBean().getNuValorFactorAA());
      logger.debug("nuMultaPreliminar "+ this.getObjFiltroBean().getNuMultaAABarrera());
      logger.debug("nuAjusteMinimo "+ this.getObjFiltroBean().getNuAjusteMinBarrera());
      logger.debug("nuAjusteMaximo "+ this.getObjFiltroBean().getNuAjusteMaxBarrera());
      logger.debug("nuAjusteLeg "+ this.getObjFiltroBean().getNuAjusteIngBarrera());
      logger.debug("nuMultaFinal "+ this.getObjFiltroBean().getNuMultaFinal());

      map.put("vcOrgResolutivo", this.getObjFiltroBean().getVcOrgResolutivo());
      map.put("vcTipoInfractor", this.getObjFiltroBean().getVcTipoBarrera());
      map.put("vcRuc", this.getObjFiltroBean().getVcRucBarreras());
      map.put("vcRazonSocial", this.getObjFiltroBean().getVcRazonSocial());
      map.put("nuIngresosAnual", this.getObjFiltroBean().getNuFacturacionAnualBarrera());
      map.put("vcTipoAfectacion", this.getObjFiltroBean().getVcTipoAfectacionBarrera());
      map.put("vcNivelAfectacion", this.getObjFiltroBean().getVcClasificacionBarrera());
      map.put("nuDano", this.getObjFiltroBean().getNuDanioBaseBarrera());
      map.put("vcAlcanceAfectacion", this.getObjFiltroBean().getVcTipoAlcanceBarrera());
      map.put("nuBarrera", this.getObjFiltroBean().getNuAlcanceBarrera());
      map.put("nuValorFactorAA", this.getObjFiltroBean().getNuValorFactorAA());
      map.put("nuMultaPreliminar", this.getObjFiltroBean().getNuMultaAABarrera());
      map.put("nuAjusteMinimo", this.getObjFiltroBean().getNuAjusteMinBarrera());
      map.put("nuAjusteMaximo", this.getObjFiltroBean().getNuAjusteMaxBarrera());
      map.put("nuAjusteLeg", this.getObjFiltroBean().getNuAjusteIngBarrera());
      map.put("nuMultaFinal", this.getObjFiltroBean().getNuMultaFinal());
      
      logger.info("reportFile.getPath(): "+ reportFile.getPath());
      logger.info("vcNombreArchivo: "+ vcNombreArchivo);

    } else if(this.getObjFiltroBean().getVcMetodo().equals("6")){

      //reportFile = new File("F:/TI_JOBS/Indecopi/ireport/calmulta_jasper_consumidor_pre.jasper");
      reportFile = new File(vcPath+"calmulta_jasper_pi_pre.jasper");
      if(reportFile.exists()){
          logger.info("Existe el archivo.");
      }else{
          logger.info("No existe el archivo.");
      }
      
      this.getObjFiltroBean().setNuFactorDuracion(1);
      map.put("vcOrgResolutivo", this.getObjFiltroBean().getVcOrgResolutivo());
      map.put("vcRuc", this.getObjFiltroBean().getVcRucPi());
      map.put("vcRazonSocial", this.getObjFiltroBean().getVcRazonSocial());
      map.put("vcTamEmpresa", this.getObjFiltroBean().getVcTamEmpresa());
      map.put("vcTipoAfectacion", this.getObjFiltroBean().getVcTipoAfectacionPi());
      map.put("vcNivelAfectacion", this.getObjFiltroBean().getVcNivelAfectacionPi());
      map.put("nuFactorDuracion", this.getObjFiltroBean().getNuFactorDuracion());
      map.put("nuMultaBase", this.getObjFiltroBean().getNuMultaBasePi());
      map.put("nuValorFactorAA", this.getObjFiltroBean().getNuValorFactorAA());
      map.put("nuMultaPreUIT", this.getObjFiltroBean().getNuMultaPreUITPi());
      map.put("nuIngresosTope", this.getObjFiltroBean().getNuGravedadTopePi());
      map.put("nuMultaFinal", this.getObjFiltroBean().getNuMultaFinal());
      
      logger.info("reportFile.getPath(): "+ reportFile.getPath());
      logger.info("vcNombreArchivo: "+ vcNombreArchivo);

    }else if(this.getObjFiltroBean().getVcMetodo().equals("7")){

      //reportFile = new File("F:/TI_JOBS/Indecopi/ireport/calmulta_jasper_consumidor_pre.jasper");
      reportFile = new File(vcPath+"calmulta_jasper_ccd_pre.jasper");
      if(reportFile.exists()){
          logger.info("Existe el archivo.");
      }else{
          logger.info("No existe el archivo.");
      }
        
      map.put("vcOrgResolutivo", this.getObjFiltroBean().getVcOrgResolutivo());
      map.put("vcRuc", this.getObjFiltroBean().getVcRucCcd());
      map.put("vcRazonSocial", this.getObjFiltroBean().getVcRazonSocial());
      map.put("vcTamEmpresa", this.getObjFiltroBean().getVcTamEmpresa());
      map.put("vcTipoAfectacion", this.getObjFiltroBean().getVcTipoAfectacionCcd());
      map.put("vcNivelAfectacion", this.getObjFiltroBean().getVcNivelAfectacionCcd());
      map.put("nuFactorDuracion", this.getObjFiltroBean().getNuFactorDuracion());
      map.put("nuMultaBase", this.getObjFiltroBean().getNuMultaBaseCcd());
      map.put("nuValorFactorAA", this.getObjFiltroBean().getNuValorFactorAA());
      map.put("nuMultaPreUIT", this.getObjFiltroBean().getNuMultaPreUIT());
      map.put("nuIngresosTope", this.getObjFiltroBean().getNuIngresosTope());
      map.put("nuMultaFinal", this.getObjFiltroBean().getNuMultaFinal());
      
      logger.info("reportFile.getPath(): "+ reportFile.getPath());
      logger.info("vcNombreArchivo: "+ vcNombreArchivo);

    }else if(this.getObjFiltroBean().getVcMetodo().equals("8")){

      //reportFile = new File("F:/TI_JOBS/Indecopi/ireport/calmulta_jasper_consumidor_pre.jasper");
      reportFile = new File(vcPath+"calmulta_jasper_cfe_pre.jasper");
      if(reportFile.exists()){
          logger.info("Existe el archivo.");
      }else{
          logger.info("No existe el archivo.");
      }
      logger.debug("getNuDanioBaseFirma "+ this.getObjFiltroBean().getNuDanioBaseFirma());

      map.put("vcOrgResolutivo", this.getObjFiltroBean().getVcOrgResolutivo());
      map.put("vcRuc", this.getObjFiltroBean().getVcRucFirma());
      map.put("vcRazonSocial", this.getObjFiltroBean().getVcRazonSocial());
      map.put("vcTamEmpresa", this.getObjFiltroBean().getVcTamEmpresa());
      map.put("vcTipoAfectacion", this.getObjFiltroBean().getVcTipoAfectacionFirma());
      map.put("vcNivelAfectacion", this.getObjFiltroBean().getVcNivelAfectacionFirma());
      map.put("nuFactorDuracion", this.getObjFiltroBean().getNuDanioBaseFirma());
      map.put("nuMultaBase", this.getObjFiltroBean().getNuMultaBaseFirma());
      map.put("nuValorFactorAA", this.getObjFiltroBean().getNuValorFactorAA());
      map.put("nuMultaPreUIT", this.getObjFiltroBean().getNuMultaAAFirma());
      map.put("nuIngresosTope", this.getObjFiltroBean().getNuTopeLegalFirma());
      map.put("nuMultaFinal", this.getObjFiltroBean().getNuMultaFinal());
      
      logger.info("reportFile.getPath(): "+ reportFile.getPath());
      logger.info("vcNombreArchivo: "+ vcNombreArchivo);

    }
    


    JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), map, beanCollectionDataSource);
    jasperPrint.setProperty("net.sf.jasperreports.expo rt.character.encoding","ISO-8859-1");
    logger.debug("seteo fillReport ");

    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    response.setHeader("Content-Disposition", "attachment; filename=\""+vcNombreArchivo+".pdf\"");
    ServletOutputStream outputStream = response.getOutputStream();
    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    outputStream.flush();
    outputStream.close();
    FacesContext.getCurrentInstance().renderResponse();
    FacesContext.getCurrentInstance().responseComplete();

    }catch (Exception e){
      logger.info(e);
    }
    logger.info(">>FIN doReportePreliminar() ");
  }

  public void setLstMultaUITAnioBean(List<ClsMultaUITAnioBean> lstMultaUITAnioBean) {
    this.lstMultaUITAnioBean = lstMultaUITAnioBean;
  }

  public List<ClsMultaUITAnioBean> getLstMultaUITAnioBean() {
    return lstMultaUITAnioBean;
  }

  public void setLstAfectacionBean(List<ClsTipoAfectacionBean> lstAfectacionBean) {
    this.lstAfectacionBean = lstAfectacionBean;
  }

  public List<ClsTipoAfectacionBean> getLstAfectacionBean() {
    return lstAfectacionBean;
  }

  /*public void setLstMetodos(Map<String, String> lstMetodos) {
      this.lstMetodos = lstMetodos;
  }

  public Map<String, String> getLstMetodos() {
      return lstMetodos;
  }

  public void setLstInfracciones(Map<String, String> lstInfracciones) {
      this.lstInfracciones = lstInfracciones;
  }

  public Map<String, String> getLstInfracciones() {
      return lstInfracciones;
  }

  public void setLstInstancias(Map<String, String> lstInstancias) {
      this.lstInstancias = lstInstancias;
  }

  public Map<String, String> getLstInstancias() {
      return lstInstancias;
  }*/

  public void doPrueba() {
    logger.debug("doPrueba");
    logger.debug("doPrueba");
  }

  //Metodos Set & Get
  public void setVcError(String vcError) {
    this.vcError = vcError;
  }

  public String getVcError() {
    return vcError;
  }

  public void setVcMensaje(String vcMensaje) {
    this.vcMensaje = vcMensaje;
  }

  public String getVcMensaje() {
    return vcMensaje;
  }

  public void setObjUsuario(ClsUsuarioIndBean objUsuario) {
    this.objUsuario = objUsuario;
  }

  public ClsUsuarioIndBean getObjUsuario() {
    return objUsuario;
  }

  public void setObjResultDAO(ClsResultDAO objResultDAO) {
    this.objResultDAO = objResultDAO;
  }

  public ClsResultDAO getObjResultDAO() {
    return objResultDAO;
  }

  public static void setLogger(Logger logger) {
    ClsCalculoMultaSrv.logger = logger;
  }

  public static Logger getLogger() {
    return logger;
  }

  public void setObjFiltroBean(ClsCalculoBean objFiltroBean) {
    this.objFiltroBean = objFiltroBean;
  }

  public ClsCalculoBean getObjFiltroBean() {
    return objFiltroBean;
  }

  public void setLstMetodoCalculoBean(List<ClsMetodoCalculoBean> lstMetodoCalculoBean) {
    this.lstMetodoCalculoBean = lstMetodoCalculoBean;
  }

  public List<ClsMetodoCalculoBean> getLstMetodoCalculoBean() {
    return lstMetodoCalculoBean;
  }

  public void setObjPermiso(ClsPermisoUsuarioBean objPermiso) {
    this.objPermiso = objPermiso;
  }

  public ClsPermisoUsuarioBean getObjPermiso() {
    return objPermiso;
  }

    public void setLstAgravF1(Map<String, String> lstAgravF1) {
        this.lstAgravF1 = lstAgravF1;
    }
    
    public Map<String, String> getLstAgravF1() {
        return lstAgravF1;
    }
    
    public void setLstAgravF2(Map<String, String> lstAgravF2) {
        this.lstAgravF2 = lstAgravF2;
    }
    
    public Map<String, String> getLstAgravF2() {
        return lstAgravF2;
    }
    
    public void setLstAgravF3(Map<String, String> lstAgravF3) {
        this.lstAgravF3 = lstAgravF3;
    }
    
    public Map<String, String> getLstAgravF3() {
        return lstAgravF3;
    }
    
    public void setLstAgravF4(Map<String, String> lstAgravF4) {
        this.lstAgravF4 = lstAgravF4;
    }
    
    public Map<String, String> getLstAgravF4() {
        return lstAgravF4;
    }
    
    public void setLstAgravF5(Map<String, String> lstAgravF5) {
        this.lstAgravF5 = lstAgravF5;
    }
    
    public Map<String, String> getLstAgravF5() {
        return lstAgravF5;
    }
    
    public void setLstAgravF6(Map<String, String> lstAgravF6) {
        this.lstAgravF6 = lstAgravF6;
    }
    
    public Map<String, String> getLstAgravF6() {
        return lstAgravF6;
    }
    
    public void setLstAtenF7(Map<String, String> lstAtenF7) {
        this.lstAtenF7 = lstAtenF7;
    }
    
    public Map<String, String> getLstAtenF7() {
        return lstAtenF7;
    }
    
    public void setLstAtenF8(Map<String, String> lstAtenF8) {
        this.lstAtenF8 = lstAtenF8;
    }
    
    public Map<String, String> getLstAtenF8() {
        return lstAtenF8;
    }
    
    public void setLstAtenF9(Map<String, String> lstAtenF9) {
        this.lstAtenF9 = lstAtenF9;
    }
    
    public Map<String, String> getLstAtenF9() {
        return lstAtenF9;
    }
    
    public void setLstAtenF10(Map<String, String> lstAtenF10) {
        this.lstAtenF10 = lstAtenF10;
    }
    
    public Map<String, String> getLstAtenF10() {
        return lstAtenF10;
    }
    
    public void setLstAtenF11(Map<String, String> lstAtenF11) {
        this.lstAtenF11 = lstAtenF11;
    }
    
    public Map<String, String> getLstAtenF11() {
        return lstAtenF11;
    }

    public void setLstOrgano(List<SelectItem> lstOrgano) {
        this.lstOrgano = lstOrgano;
    }

    public List<SelectItem> getLstOrgano() {
        return lstOrgano;
    }

    public void setLstAgravF01(List<SelectItem> lstAgravF01) {
      this.lstAgravF01 = lstAgravF01;
    }

    public List<SelectItem> getLstAgravF01() {
        return lstAgravF01;
    }
    public void setLstAgravF02(List<SelectItem> lstAgravF02) {
      this.lstAgravF02 = lstAgravF02;
    }

    public List<SelectItem> getLstAgravF02() {
        return lstAgravF02;
    }
    public void setLstAgravF03(List<SelectItem> lstAgravF03) {
      this.lstAgravF03 = lstAgravF03;
    }

    public List<SelectItem> getLstAgravF03() {
        return lstAgravF03;
    }

    public void setLstAtenF03(List<SelectItem> lstAtenF03) {
      this.lstAtenF03 = lstAtenF03;
    }

    public List<SelectItem> getLstAtenF03() {
        return lstAtenF03;
    }
    public void setLstAtenF02(List<SelectItem> lstAtenF02) {
      this.lstAtenF02 = lstAtenF02;
    }

    public List<SelectItem> getLstAtenF02() {
        return lstAtenF02;
    }
    public void setLstAtenF01(List<SelectItem> lstAtenF01) {
      this.lstAtenF01 = lstAtenF01;
    }

    public List<SelectItem> getLstAtenF01() {
        return lstAtenF01;
    }

    public void setVcMuestraPreestablecido(String vcMuestraPreestablecido) {
        this.vcMuestraPreestablecido = vcMuestraPreestablecido;
    }

    public String getVcMuestraPreestablecido() {
        return vcMuestraPreestablecido;
    }

    public void setVcMuestraLibro(String vcMuestraLibro) {
        this.vcMuestraLibro = vcMuestraLibro;
    }

    public String getVcMuestraLibro() {
        return vcMuestraLibro;
    }

    public void setVcMuestraBarreras(String vcMuestraBarreras) {
        this.vcMuestraBarreras = vcMuestraBarreras;
    }

    public String getVcMuestraBarreras() {
        return vcMuestraBarreras;
    }

    public void setLstProbabilidadBarrerasBean(List<ClsProbabilidadBarrerasBean> lstProbabilidadBarrerasBean) {
        this.lstProbabilidadBarrerasBean = lstProbabilidadBarrerasBean;
    }

    public List<ClsProbabilidadBarrerasBean> getLstProbabilidadBarrerasBean() {
        return lstProbabilidadBarrerasBean;
    }

    public void setVcMuestraFacSecEconBarreras(String vcMuestraFacSecEconBarreras) {
        this.vcMuestraFacSecEconBarreras = vcMuestraFacSecEconBarreras;
    }

    public String getVcMuestraFacSecEconBarreras() {
        return vcMuestraFacSecEconBarreras;
    }

    public void setVcMuestraPreestablecidoPi(String vcMuestraPreestablecidoPi) {
        this.vcMuestraPreestablecidoPi = vcMuestraPreestablecidoPi;
    }

    public String getVcMuestraPreestablecidoPi() {
        return vcMuestraPreestablecidoPi;
    }

    public void setVcMuestraOrgano1raInstanciaPi(String vcMuestraOrgano1raInstanciaPi) {
        this.vcMuestraOrgano1raInstanciaPi = vcMuestraOrgano1raInstanciaPi;
    }

    public String getVcMuestraOrgano1raInstanciaPi() {
        return vcMuestraOrgano1raInstanciaPi;
    }

    public void setVcMuestraPreestablecidoCcd(String vcMuestraPreestablecidoCcd) {
        this.vcMuestraPreestablecidoCcd = vcMuestraPreestablecidoCcd;
    }

    public String getVcMuestraPreestablecidoCcd() {
        return vcMuestraPreestablecidoCcd;
    }

    public void setVcMuestraOrgano1raInstanciaCcd(String vcMuestraOrgano1raInstanciaCcd) {
        this.vcMuestraOrgano1raInstanciaCcd = vcMuestraOrgano1raInstanciaCcd;
    }

    public String getVcMuestraOrgano1raInstanciaCcd() {
        return vcMuestraOrgano1raInstanciaCcd;
    }

    public void setVcMuestraPreestablecidoFirma(String vcMuestraPreestablecidoFirma) {
        this.vcMuestraPreestablecidoFirma = vcMuestraPreestablecidoFirma;
    }

    public String getVcMuestraPreestablecidoFirma() {
        return vcMuestraPreestablecidoFirma;
    }

    public void setVcMuestraPorcentajeVentas(String vcMuestraPorcentajeVentas) {
        this.vcMuestraPorcentajeVentas = vcMuestraPorcentajeVentas;
    }

    public String getVcMuestraPorcentajeVentas() {
        return vcMuestraPorcentajeVentas;
    }

    public void setVcMuestraAdHoc(String vcMuestraAdHoc) {
        this.vcMuestraAdHoc = vcMuestraAdHoc;
    }

    public String getVcMuestraAdHoc() {
        return vcMuestraAdHoc;
    }

    public void setVcMuestraTamEmpresaAdHoc(String vcMuestraTamEmpresaAdHoc) {
        this.vcMuestraTamEmpresaAdHoc = vcMuestraTamEmpresaAdHoc;
    }

    public String getVcMuestraTamEmpresaAdHoc() {
        return vcMuestraTamEmpresaAdHoc;
    }

    public void setVcMuestraGravedad1AdHoc(String vcMuestraGravedad1AdHoc) {
        this.vcMuestraGravedad1AdHoc = vcMuestraGravedad1AdHoc;
    }

    public String getVcMuestraGravedad1AdHoc() {
        return vcMuestraGravedad1AdHoc;
    }

    public void setVcMuestraGravedad2AdHoc(String vcMuestraGravedad2AdHoc) {
        this.vcMuestraGravedad2AdHoc = vcMuestraGravedad2AdHoc;
    }

    public String getVcMuestraGravedad2AdHoc() {
        return vcMuestraGravedad2AdHoc;
    }

    public void setNuIdTamEmpresa(int nuIdTamEmpresa) {
        this.nuIdTamEmpresa = nuIdTamEmpresa;
    }

    public int getNuIdTamEmpresa() {
        return nuIdTamEmpresa;
    }

    public void setVcMuestraOrgano1raInstanciaVentas(String vcMuestraOrgano1raInstanciaVentas) {
        this.vcMuestraOrgano1raInstanciaVentas = vcMuestraOrgano1raInstanciaVentas;
    }

    public String getVcMuestraOrgano1raInstanciaVentas() {
        return vcMuestraOrgano1raInstanciaVentas;
    }

    public void setVcMuestraOrgano1raInstanciaAdhoc(String vcMuestraOrgano1raInstanciaAdhoc) {
        this.vcMuestraOrgano1raInstanciaAdhoc = vcMuestraOrgano1raInstanciaAdhoc;
    }

    public String getVcMuestraOrgano1raInstanciaAdhoc() {
        return vcMuestraOrgano1raInstanciaAdhoc;
    }

    public void setObjUsuarioBean(ClsUsuarioBean objUsuarioBean) {
        this.objUsuarioBean = objUsuarioBean;
    }

    public ClsUsuarioBean getObjUsuarioBean() {
        return objUsuarioBean;
    }

    public void setVcMuestraAtenuanteF8(String vcMuestraAtenuanteF8) {
        this.vcMuestraAtenuanteF8 = vcMuestraAtenuanteF8;
    }

    public String getVcMuestraAtenuanteF8() {
        return vcMuestraAtenuanteF8;
    }

    public void setVcMuestraAtenuanteF10(String vcMuestraAtenuanteF10) {
        this.vcMuestraAtenuanteF10 = vcMuestraAtenuanteF10;
    }

    public String getVcMuestraAtenuanteF10() {
        return vcMuestraAtenuanteF10;
    }

    public void setVcMuestraAtenuanteF7(String vcMuestraAtenuanteF7) {
        this.vcMuestraAtenuanteF7 = vcMuestraAtenuanteF7;
    }

    public String getVcMuestraAtenuanteF7() {
        return vcMuestraAtenuanteF7;
    }

    public void setVcMuestraTopelegalNoPIAdHoc(String vcMuestraTopelegalNoPIAdHoc) {
        this.vcMuestraTopelegalNoPIAdHoc = vcMuestraTopelegalNoPIAdHoc;
    }

    public String getVcMuestraTopelegalNoPIAdHoc() {
        return vcMuestraTopelegalNoPIAdHoc;
    }

    public void setLstInstanciaBean(List<ClsInstanciasBean> lstInstanciaBean) {
        this.lstInstanciaBean = lstInstanciaBean;
    }

    public List<ClsInstanciasBean> getLstInstanciaBean() {
        return lstInstanciaBean;
    }

    public void setVcTituloModalInfraccion(String vcTituloModalInfraccion) {
        this.vcTituloModalInfraccion = vcTituloModalInfraccion;
    }

    public String getVcTituloModalInfraccion() {
        return vcTituloModalInfraccion;
    }
    
    public void setLstAreas(Map<String, String> lstAreas) {
        this.lstAreas = lstAreas;
    }

    public Map<String, String> getLstAreas() {
        return lstAreas;
    }

    public void setVcMuestraCLCVentas(String vcMuestraCLCVentas) {
        this.vcMuestraCLCVentas = vcMuestraCLCVentas;
    }

    public String getVcMuestraCLCVentas() {
        return vcMuestraCLCVentas;
    }

    public void setVcMuestraInicio(String vcMuestraInicio) {
        this.vcMuestraInicio = vcMuestraInicio;
    }

    public String getVcMuestraInicio() {
        return vcMuestraInicio;
    }

    public void setVcMuestraFormPrincipal(String vcMuestraFormPrincipal) {
        this.vcMuestraFormPrincipal = vcMuestraFormPrincipal;
    }

    public String getVcMuestraFormPrincipal() {
        return vcMuestraFormPrincipal;
    }

    public void setVcAreaSeleccion(String vcAreaSeleccion) {
        this.vcAreaSeleccion = vcAreaSeleccion;
    }

    public String getVcAreaSeleccion() {
        return vcAreaSeleccion;
    }

    public void setVcMuestraFactorPVariacion(String vcMuestraFactorPVariacion) {
        this.vcMuestraFactorPVariacion = vcMuestraFactorPVariacion;
    }

    public String getVcMuestraFactorPVariacion() {
        return vcMuestraFactorPVariacion;
    }

    public void setVcMuestraFactorGVariacion(String vcMuestraFactorGVariacion) {
        this.vcMuestraFactorGVariacion = vcMuestraFactorGVariacion;
    }

    public String getVcMuestraFactorGVariacion() {
        return vcMuestraFactorGVariacion;
    }
    
    public void setLstTamanoEmpresaBean(List<ClsTamanioEmpresaBean> lstTamanoEmpresaBean) {
        this.lstTamanoEmpresaBean = lstTamanoEmpresaBean;
    }

    public List<ClsTamanioEmpresaBean> getLstTamanoEmpresaBean() {
        return lstTamanoEmpresaBean;
    }
}
