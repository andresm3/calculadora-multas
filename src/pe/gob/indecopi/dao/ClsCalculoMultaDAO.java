package pe.gob.indecopi.dao;

import pe.gob.indecopi.bean.ClsPermisoUsuarioBean;
import pe.gob.indecopi.bean.ClsUsuarioBean;
import indecopi.gob.pe.bean.ClsUsuarioIndBean;
import pe.gob.indecopi.bean.ClsMultaUITAnioBean;
import pe.gob.indecopi.bean.ClsProbabilidadBarrerasBean;
//import indecopi.gob.pe.util.ResultDAO;
//import pe.gob.indecopi.util.SQLUtils;

import indecopi.gob.pe.utils.ClsResultDAO;
import indecopi.gob.pe.utils.ClsSQLUtils;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import pe.gob.indecopi.bean.ClsInstanciasBean;
import pe.gob.indecopi.bean.ClsMetodoCalculoBean;
import pe.gob.indecopi.bean.ClsTamanioEmpresaBean;
import pe.gob.indecopi.bean.ClsTipoAfectacionBean;
import pe.gob.indecopi.dao.ClsConectionDB;
import pe.gob.indecopi.dao.ClsCalculoMultaIDAO;


public class ClsCalculoMultaDAO implements ClsCalculoMultaIDAO {
    static Logger logger = Logger.getLogger(ClsCalculoMultaDAO.class);

    private static final String SP_LST_PARAMETROS =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_PARAMETROS(" + ClsSQLUtils.sqlParams(4) + ")}";
    private static final String SP_GET_PARAMETRO =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_PARAMETRO(" + ClsSQLUtils.sqlParams(8) + ")}";   
    private static final String SP_LST_MULTA_UIT_ANIOS =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_MULTA_UIT_ANIOS(" + ClsSQLUtils.sqlParams(3) + ")}";
    private static final String SP_LST_TAM_EMPRESA =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_TAM_EMPRESA(" + ClsSQLUtils.sqlParams(4) + ")}";
    private static final String SP_LST_INSTANCIAS =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_INSTANCIAS(" + ClsSQLUtils.sqlParams(5) + ")}";
    private static final String SP_LST_METODOS =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_METODOS(" + ClsSQLUtils.sqlParams(5) + ")}";
    private static final String SP_LST_INFRACCIONES =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_INFRACCIONES(" + ClsSQLUtils.sqlParams(6) + ")}";

    private static final String SP_GET_TAM_EMPRESA =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_TAM_EMPRESA(" + ClsSQLUtils.sqlParams(7) + ")}";        
    private static final String SP_GET_FACTOR_DURACION =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_FACTOR_DURACION(" + ClsSQLUtils.sqlParams(4) + ")}";        
    private static final String SP_GET_VALOR_MATRIZ =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_VALOR_MATRIZ(" + ClsSQLUtils.sqlParams(8) + ")}";  
    private static final String SP_GET_GRAVEDAD =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_GRAVEDAD(" + ClsSQLUtils.sqlParams(8) + ")}";       

    private static final String SP_CONSULTA =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_DO_CONSULTA(" + ClsSQLUtils.sqlParams(4) + ")}";     

    //PARA LIBRO RECL
    private static final String SP_GET_MULTA_LIBRO =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_MULTA_LIBRO(" + ClsSQLUtils.sqlParams(9) + ")}";  
    private static final String SP_GET_TAM_EMPRESA_LIBRO =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_TAM_EMPRESA_LIBRO(" + ClsSQLUtils.sqlParams(6) + ")}";  
    ////////////////////////

    //PARA BARRERAS
    private static final String SP_GET_DANIO_BARRERAS =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_DANIO_BARRERAS(" + ClsSQLUtils.sqlParams(6) + ")}";    
    private static final String SP_LST_PROBABILIDAD_BARRERAS =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_PROBABILIDAD_BARRERAS(" + ClsSQLUtils.sqlParams(3) + ")}";
    ////////////////////////

    //PARA PI y CCD
    private static final String SP_LST_ORGANOS_METODO =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_ORGANOS_METODO(" + ClsSQLUtils.sqlParams(5) + ")}";

    private static final String SP_CB_GRAVEDAD_TOPE =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_LST_GRAVEDAD_TOPE(" + ClsSQLUtils.sqlParams(5) + ")}";
    
    //PARA PVENTAS
    private static final String SP_GET_GRAVEDAD_VENTAS =
        "{call USR_CALMULTA.PKG_CALCULADORA_MULTAS.SP_GET_GRAVEDAD_VENTAS(" + ClsSQLUtils.sqlParams(5) + ")}";

    ////////////////////////
    public ClsCalculoMultaDAO() {
        super();
    }

    @Override
    public ClsResultDAO doListarParametros(int nuTablaPadre) {
        logger.info(">>doListarParametros ");
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Map<String, String> lstParametros = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_PARAMETROS);
            int i = 0;
            stmt.setInt(++i, nuTablaPadre);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2);

            if (rs != null) {
                lstParametros = new LinkedHashMap<String, String>();
                while (rs.next()) {
                    lstParametros.put(rs.getString("VC_NOMBRE"), rs.getString("VC_VALOR"));
                }
            }

            response = new ClsResultDAO();
            response.put("SP_LST_PARAMETROS", lstParametros);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doGetValorDescripcion(int nuTablaPadre, String vcParametro, int nuValor) {
        logger.info(">>doGetValorDescripcion ");
        logger.debug("nuTablaPadre: "+nuTablaPadre);
        logger.debug("vcParametro: "+vcParametro);
        logger.debug("nuValor: "+nuValor);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        String vcDescripcion = null;
        String vcValor = null;
        String vcValorSecun = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_PARAMETRO);
            int i = 0;
            stmt.setInt(++i, nuTablaPadre);
            stmt.setString(++i, vcParametro);
            stmt.setInt(++i, nuValor);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            vcDescripcion = stmt.getString(i - 4);
            vcValor = stmt.getString(i - 3);
            vcValorSecun = stmt.getString(i - 2);
            logger.debug("vcDescripcion>>" + vcDescripcion);
            logger.debug("vcValor>>" + vcValor);
            logger.debug("vcValorSecun>>" + vcValorSecun);

            response = new ClsResultDAO();
            response.put("GET_DESCRIPCION_PARAMETRO", vcDescripcion);
            response.put("GET_VALOR_PARAMETRO", vcValor);
            response.put("GET_VALOR_SECUN_PARAMETRO", vcValorSecun);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doListarMultaUITAnios() {
        logger.info(">>doListarMultaUITAnios ");
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        //Map<String, String> lstParametros = null;
        List<ClsMultaUITAnioBean> lstMultaAnios = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_MULTA_UIT_ANIOS);
            int i = 0;
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2);

            if (rs != null) {
                lstMultaAnios = new ArrayList<ClsMultaUITAnioBean>();
                ClsMultaUITAnioBean objBean = null;
                while (rs.next()) {
                objBean = new ClsMultaUITAnioBean();
                objBean.setVcIdMultaUIT(rs.getString("NU_ID_MULTA_UIT"));
                objBean.setVcMultaUIT(rs.getString("VC_MULTA_ANIO"));
                objBean.setNuUIT(rs.getInt("Nu_t_Uit"));

                lstMultaAnios.add(objBean);
                }
                logger.debug(">lstMultaAnios.size:" + lstMultaAnios.size());
            }

            response = new ClsResultDAO();
            response.put("SP_LST_MULTA_UIT_ANIOS", lstMultaAnios);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doListarTamanoEmpresa(int nuIdMetodo) {
        logger.info(">>doListarTamanoEmpresa "+nuIdMetodo);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        //Map<String, String> lstParametros = null;
        List<ClsTamanioEmpresaBean> lstMultaAnios = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_TAM_EMPRESA);
            int i = 0;
            stmt.setInt(++i, nuIdMetodo);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2);

            if (rs != null) {
                lstMultaAnios = new ArrayList<ClsTamanioEmpresaBean>();
                ClsTamanioEmpresaBean objBean = null;
                while (rs.next()) {
                objBean = new ClsTamanioEmpresaBean();
                objBean.setVcIdTamanioEmpresa(rs.getString("Nu_Id_Tam_Empresa"));
                objBean.setVcTamanioEmpresa(rs.getString("VC_TAM_EMPRESA"));
                objBean.setNuMinUIT(rs.getInt("NU_MIN_UIT"));
                objBean.setNuMaxUIT(rs.getInt("NU_MAX_UIT"));
                objBean.setNuPorcTope(rs.getDouble("NU_PORC_TOPE"));

                lstMultaAnios.add(objBean);
                }
                logger.debug(">lstTamEmpresa.size:" + lstMultaAnios.size());
            }

            response = new ClsResultDAO();
            response.put("SP_LST_TAM_EMPRESA", lstMultaAnios);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }
    
    /*@Override
    public ClsResultDAO doListarInstancias(String vcIdMetodo, String vcOrgResolutivo) {
        logger.info(">>doListarInstancias");
        logger.debug("vcIdMetodo: "+vcIdMetodo);
        logger.debug("vcOrgResolutivo: "+vcOrgResolutivo);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Map<String, String> lstInstancias = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_INSTANCIAS);
            int i = 0;
            stmt.setString(++i, vcIdMetodo);
            stmt.setString(++i, vcOrgResolutivo);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2); //Seguimientos

            if (rs != null) {
                lstInstancias = new LinkedHashMap<String, String>();
                while (rs.next()) {
                    lstInstancias.put(rs.getString("VC_NOMBRE"), rs.getString("VC_VALOR"));
                }
            }

            response = new ClsResultDAO();
            response.put("SP_LST_INSTANCIAS", lstInstancias);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }*/

    /*@Override
    public ClsResultDAO doListarMetodos(String vcInstancia, String vcOrgResolutivo) {
        logger.info(">>doListarMetodos ");
        logger.debug("vcInstancia: "+vcInstancia);
        logger.debug("vcOrgResolutivo: "+vcOrgResolutivo);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Map<String, String> lstMetodos = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_METODOS);
            int i = 0;
            stmt.setString(++i, vcInstancia);
            stmt.setString(++i, vcOrgResolutivo);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2); //Seguimientos

            if (rs != null) {
                lstMetodos = new LinkedHashMap<String, String>();
                while (rs.next()) {
                    lstMetodos.put(rs.getString("VC_DESCRIPCION"), String.valueOf(rs.getInt("NU_ID_METODO")));
                }
            }
            logger.debug(">lstMetodos.size:" + lstMetodos.size());
            response = new ClsResultDAO();
            response.put("SP_LST_METODOS", lstMetodos);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }*/

    @Override
    public ClsResultDAO doListarMetodos(String vcInstancia, String vcOrgResolutivo) {
        logger.info(">>doListarMetodos ");
        logger.debug("vcInstancia: "+vcInstancia);
        logger.debug("vcOrgResolutivo: "+vcOrgResolutivo);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        //Map<String, String> lstMetodos = null;
        List<ClsMetodoCalculoBean> lstMetodos = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_METODOS);
            int i = 0;
            stmt.setString(++i, vcInstancia);
            stmt.setString(++i, vcOrgResolutivo);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2); //Seguimientos

            if (rs != null) {
                lstMetodos = new ArrayList<ClsMetodoCalculoBean>();
                ClsMetodoCalculoBean obj = null;
                while (rs.next()) {
                    //lstMetodos.put(rs.getString("VC_DESCRIPCION"), String.valueOf(rs.getInt("NU_ID_METODO")));
                    obj = new ClsMetodoCalculoBean();
                    obj.setVcMetodoCalculo(rs.getString("VC_DESCRIPCION"));
                    obj.setNuIdMetodoCalculo(rs.getInt("NU_ID_METODO"));
                    lstMetodos.add(obj);
                }
            }
            logger.debug(">lstMetodos.size:" + lstMetodos.size());
            response = new ClsResultDAO();
            response.put("SP_LST_METODOS", lstMetodos);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doListarInstancias(String vcIdMetodo, String vcOrgResolutivo) {
        logger.info(">>doListarInstancias");
        logger.debug("vcIdMetodo: "+vcIdMetodo);
        logger.debug("vcOrgResolutivo: "+vcOrgResolutivo);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        //Map<String, String> lstInstancias = null;
        List<ClsInstanciasBean> lstInstancias = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_INSTANCIAS);
            int i = 0;
            stmt.setString(++i, vcIdMetodo);
            stmt.setString(++i, vcOrgResolutivo);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2); //Seguimientos

            if (rs != null) {
                lstInstancias = new ArrayList<ClsInstanciasBean>();
                ClsInstanciasBean obj = null;
                //lstInstancias = new LinkedHashMap<String, String>();
                while (rs.next()) {
                    //lstInstancias.put(rs.getString("VC_NOMBRE"), rs.getString("VC_VALOR"));
                    obj = new ClsInstanciasBean();
                    obj.setVcInstancia(rs.getString("VC_NOMBRE"));
                    obj.setNuIdInstancia(rs.getInt("VC_VALOR"));
                    lstInstancias.add(obj);
                }
            }

            response = new ClsResultDAO();
            response.put("SP_LST_INSTANCIAS", lstInstancias);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doListarInfracciones(String vcMetodo, String vcInstancia, String vcOrgResolutivo) {
        logger.info(">>doListarInfracciones ");
        logger.debug("vcMetodo: "+vcMetodo);
        logger.debug("vcInstancia: "+ vcInstancia);
        logger.debug("vcOrgResolutivo: "+vcOrgResolutivo);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        //Map<String, String> lstParametros = null;
        List<ClsTipoAfectacionBean> lstTipoAfectacion = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_INFRACCIONES);
            int i = 0;
            stmt.setString(++i, vcInstancia.equals("-1") ? "0" : vcInstancia);
            stmt.setString(++i, vcOrgResolutivo);
            stmt.setString(++i, vcMetodo);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2);

            if (rs != null) {
                lstTipoAfectacion = new ArrayList<ClsTipoAfectacionBean>();
                ClsTipoAfectacionBean objBean = null;
                while (rs.next()) {
                objBean = new ClsTipoAfectacionBean();
                objBean.setVcIdTipoAfectacion(rs.getString("NU_ID_AFECTACION"));
                objBean.setVcTipoAfectacion(rs.getString("VC_AFECTACION"));
                objBean.setVcIdNivelAfectacion(rs.getString("VC_ID_NIVEL_AFECT"));
                objBean.setVcNivelAfectacion(rs.getString("VC_NIVEL_AFECT"));
                objBean.setVcClasificacion(rs.getString("VC_CLASIFICACION"));
                objBean.setVcClasificacionBarrera(rs.getString("VC_CLASIFICACION"));
                objBean.setNuDanioBase(rs.getInt("NU_DANO"));
                objBean.setNuFactorG(rs.getDouble("NU_FACTORG"));

                lstTipoAfectacion.add(objBean);
                }
                logger.debug(">lstTipoAfectacion.size:" + lstTipoAfectacion.size());
            }

            response = new ClsResultDAO();
            response.put("SP_LST_INFRACCIONES", lstTipoAfectacion);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doGetTamEmpresa(int nuIdMetodo, int facturacionUIT) {
        logger.info(">>doGetTamEmpresa ");
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String vcTamanoEmpresa = null;
        int vcIdTamanoEmpresa;
        double nuPorcTope;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_TAM_EMPRESA);
            int i = 0;
            stmt.setInt(++i, nuIdMetodo);
            stmt.setInt(++i, facturacionUIT);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            nuPorcTope = stmt.getDouble(i - 2);
            vcTamanoEmpresa = stmt.getString(i - 3);
            vcIdTamanoEmpresa = stmt.getInt(i - 4);
            logger.debug("vcIdTamanoEmpresa>>" + vcIdTamanoEmpresa);
            logger.debug("vcTamanoEmpresa>>" + vcTamanoEmpresa);
            logger.debug("nuPorcTope>>" + nuPorcTope);

            response = new ClsResultDAO();
            response.put("GET_ID_TAM_EMPRESA", vcIdTamanoEmpresa);
            response.put("GET_TAM_EMPRESA", vcTamanoEmpresa);
            response.put("GET_PORC_TOPE", nuPorcTope);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doBuscarRuc(String vcRuc) {
        logger.info(">>INI doBuscarRuc>>" + vcRuc);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String vcRazonSocial = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_CONSULTA);
            int i = 0;
            stmt.setString(++i, vcRuc);
            stmt.setInt(++i, 0);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i); //
            if (rs != null) {
                while (rs.next()) {
                    vcRazonSocial = rs.getString("VC_DDP_RAZON_SOCIAL");
                }
            }
            logger.debug("vcRazonSocial>>" + vcRazonSocial);

            response = new ClsResultDAO();
            if(stmt.getInt(i - 1) == 0){
                response.put("VC_RAZONSOC", vcRazonSocial);
            }else{
                response.put("VC_RAZONSOC", "RUC no encontrado - RUC incompleto");
            }
            //response.put("VC_RAZONSOC", vcRazonSocial);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            //response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doGetFD(int dias) {
        logger.info(">>doGetFD ");
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        double nuFd;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_FACTOR_DURACION);
            int i = 0;
            stmt.setInt(++i, dias);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            nuFd = stmt.getDouble(i - 2); //Seguimientos
            logger.debug("nuFd " + nuFd);

            response = new ClsResultDAO();
            response.put("GET_FACTOR_DURACION", nuFd);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }    

    @Override
    public ClsResultDAO doGetGravedad(int nuIdMetodo, double multaPreliminar, String vcOrgResolutivo) {
        logger.info(">>doGetGravedad");
        logger.debug("nuIdMetodo>>" + nuIdMetodo);
        logger.debug("multaPreliminar>>" + multaPreliminar);
        logger.debug("vcOrgResolutivo>>" + vcOrgResolutivo);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String vcGravedad;
        double nuTope;
        double nuPorcVtas;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_GRAVEDAD);
            int i = 0;
            stmt.setInt(++i, nuIdMetodo);
            stmt.setDouble(++i, multaPreliminar);
            stmt.setString(++i, vcOrgResolutivo);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            vcGravedad = stmt.getString(i - 4);
            nuTope = stmt.getDouble(i - 3);
            nuPorcVtas = stmt.getDouble(i - 2);
            logger.debug("vcGravedad>>" + vcGravedad);
            logger.debug("nuTope>>" + nuTope);
            logger.debug("nuPorcVtas>>" + nuPorcVtas);

            response = new ClsResultDAO();
            response.put("GET_GRAVEDAD", vcGravedad);
            response.put("GET_GRAVEDAD_TOPE", nuTope);
            response.put("GET_GRAVEDAD_PORC_VTAS", nuPorcVtas);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }   

    @Override
    public ClsResultDAO doGetValorMatriz(int nuIdMetodo, String vcOrgano, int vcIdInstancia, int vcIdTamanoEmpresa, int vcIdNivelAfectacion) {
        logger.info(">>doGetValorMatriz ");
        logger.debug("nuIdMetodo>>" + nuIdMetodo);
        logger.debug("vcOrgano>>" + vcOrgano);
        logger.debug("vcIdInstancia>>" + vcIdInstancia);
        logger.debug("vcIdTamanoEmpresa>>" + vcIdTamanoEmpresa);
        logger.debug("vcIdNivelAfectacion>>" + vcIdNivelAfectacion);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        double nuValorMatriz;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_VALOR_MATRIZ);
            int i = 0;
            stmt.setInt(++i, nuIdMetodo);
            stmt.setString(++i, vcOrgano);
            stmt.setInt(++i, vcIdInstancia);
            stmt.setInt(++i, vcIdTamanoEmpresa);
            stmt.setInt(++i, vcIdNivelAfectacion);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            nuValorMatriz = stmt.getDouble(6); //
            logger.debug("doGetValorMatriz>>" + nuValorMatriz);

            response = new ClsResultDAO();
            response.put("GET_VALOR_MATRIZ", nuValorMatriz);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }   

    @Override
    public ClsResultDAO doGetMultaLibro(int vcIdTamanoEmpresa, String vcClasificacion) {
        logger.info(">>doGetMultaLibro ");
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        double nuMultaRef;
        double nuMultaCoc;
        double nuMaxFactUIT;
        double nuFactMin;
        double nuFactMax;
        //String vcIdTamanoEmpresa = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_MULTA_LIBRO);
            int i = 0;
            stmt.setInt(++i, vcIdTamanoEmpresa);
            stmt.setString(++i, vcClasificacion);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            nuMultaRef = stmt.getDouble(i - 6);
            nuMultaCoc = stmt.getDouble(i - 5);
            nuMaxFactUIT = stmt.getDouble(i - 4);
            nuFactMin = stmt.getDouble(i - 3);
            nuFactMax = stmt.getDouble(i - 2);

            response = new ClsResultDAO();
            response.put("GET_MULTA_REF", nuMultaRef);
            response.put("GET_MULTA_COC", nuMultaCoc);
            response.put("GET_MULTA_MAXUIT", nuMaxFactUIT);
            response.put("GET_MULTA_FACTMIN", nuFactMin);
            response.put("GET_MULTA_FACTMAX", nuFactMax);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doGetTamEmpresaLibro(int facturacionUIT) {
        logger.info(">>doGetTamEmpresaLibro");
        logger.debug("facturacionUIT>>" + facturacionUIT);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String vcTamanoEmpresa = null;
        String vcIdTamanoEmpresa = null;
        double nuPorcTopeLibro;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_TAM_EMPRESA_LIBRO);
            int i = 0;
            stmt.setInt(++i, facturacionUIT);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            vcTamanoEmpresa = stmt.getString(i - 3);
            vcIdTamanoEmpresa = stmt.getString(i - 4);
            nuPorcTopeLibro = stmt.getDouble(i - 2);
            logger.debug("vcIdTamanoEmpresa>>" + vcIdTamanoEmpresa);
            logger.debug("vcTamanoEmpresa>>" + vcTamanoEmpresa);

            response = new ClsResultDAO();
            response.put("GET_ID_TAM_EMPRESA_LIBRO", vcIdTamanoEmpresa);
            response.put("GET_TAM_EMPRESA_LIBRO", vcTamanoEmpresa);
            response.put("GET_PORC_TOPE_LIBRO", nuPorcTopeLibro);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doGetDanioBarreras(String vcClasificacion) {
        logger.info(">>doGetDanioBarreras()>> "+vcClasificacion);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        int nuDanioBase;
        double nuAjusMin;
        double nuAjusMax;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_DANIO_BARRERAS);
            int i = 0;
            stmt.setString(++i, vcClasificacion);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            nuDanioBase = stmt.getInt(i - 4);
            nuAjusMin = stmt.getDouble(i - 3);
            nuAjusMax = stmt.getDouble(i - 2);
            logger.debug("nuDanioBase>>" + nuDanioBase);

            response = new ClsResultDAO();
            response.put("GET_DANIO_BASE", nuDanioBase);
            response.put("GET_AJUSTE_MIN", nuAjusMin);
            response.put("GET_AJUSTE_MAX", nuAjusMax);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    } 

    @Override
    public ClsResultDAO doListarProbabilidadBarreras() {
        logger.info(">>doListarProbabilidadBarreras ");
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        //Map<String, String> lstParametros = null;
        List<ClsProbabilidadBarrerasBean> lstMultaAnios = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_PROBABILIDAD_BARRERAS);
            int i = 0;
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2);

            if (rs != null) {
                lstMultaAnios = new ArrayList<ClsProbabilidadBarrerasBean>();
                ClsProbabilidadBarrerasBean objBean = null;
                while (rs.next()) {
                objBean = new ClsProbabilidadBarrerasBean();
                objBean.setVcCaso(rs.getString("VC_CASO"));
                objBean.setVcDefinicion(rs.getString("VC_DEFINICION"));
                objBean.setNuFactorProbabilidad(rs.getDouble("NU_PROBABILIDAD"));
                lstMultaAnios.add(objBean);
                }
                logger.debug(">lstProbabilidad.size:" + lstMultaAnios.size());
            }

            response = new ClsResultDAO();
            response.put("SP_LST_PROBABILIDAD_BARRERAS", lstMultaAnios);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doListarOrganosMetodo(String vcIdMetodo, int nuInstancia) {
        logger.info(">>doListarOrganosMetodo ");
        logger.debug("vcIdMetodo: "+vcIdMetodo);
        logger.debug("nuInstancia: "+nuInstancia);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Map<String, String> lstInstancias = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_ORGANOS_METODO);
            int i = 0;
            stmt.setString(++i, vcIdMetodo);
            stmt.setInt(++i, nuInstancia);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2); //Seguimientos

            if (rs != null) {
                lstInstancias = new LinkedHashMap<String, String>();
                while (rs.next()) {
                    lstInstancias.put(rs.getString("VC_NOMBRE"), rs.getString("VC_VALOR"));
                }
            }
            logger.debug("lstInstancias.size(): "+lstInstancias.size());
            response = new ClsResultDAO();
            response.put("SP_LST_ORGANOS_METODO", lstInstancias);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doComboGravedadTope(int nuIdMetodo, int nuSubTipoPi) {
        logger.info(">>doComboGravedadTope ");
        logger.debug("nuIdMetodo: "+nuIdMetodo);
        logger.debug("nuSubTipoPi: "+nuSubTipoPi);
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Map<String, String> lstParametros = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_CB_GRAVEDAD_TOPE);
            int i = 0;
            stmt.setInt(++i, nuIdMetodo);
            stmt.setInt(++i, nuSubTipoPi);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i - 2); //Seguimientos

            if (rs != null) {
                lstParametros = new LinkedHashMap<String, String>();
                while (rs.next()) {
                    logger.debug("VC_NOMBRE + "+rs.getString("VC_NOMBRE"));
                    logger.debug("VC_VALOR + "+rs.getString("VC_VALOR"));
                    lstParametros.put(rs.getString("VC_NOMBRE"), rs.getString("VC_VALOR"));
                }
            }

            response = new ClsResultDAO();
            response.put("SP_CB_GRAVEDAD_TOPE", lstParametros);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO doGetGravedadVentas(int nuIdGravedad) {
        logger.info(">>doGetGravedadVentas ");
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        double nuTope;
        double nuPorcTope;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_GRAVEDAD_VENTAS);
            int i = 0;
            stmt.setInt(++i, nuIdGravedad);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            nuTope = stmt.getDouble(i - 3);
            nuPorcTope = stmt.getDouble(i - 2);
            logger.debug("nuTope>>" + nuTope);
            logger.debug("nuPorcTope>>" + nuPorcTope);

            response = new ClsResultDAO();
            response.put("GET_GRAVEDAD_TOPE", nuTope);
            response.put("GET_GRAVEDAD_PORCTOPE", nuPorcTope);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }

}
