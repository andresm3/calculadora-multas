package pe.gob.indecopi.dao;

import pe.gob.indecopi.bean.ClsPermisoUsuarioBean;
import pe.gob.indecopi.bean.ClsUsuarioBean;
import indecopi.gob.pe.bean.ClsUsuarioIndBean;
//import indecopi.gob.pe.util.ResultDAO;
//import pe.gob.indecopi.util.SQLUtils;

import indecopi.gob.pe.utils.ClsResultDAO;
import indecopi.gob.pe.utils.ClsSQLUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import pe.gob.indecopi.dao.ClsConectionDB;

public class ClsUsuarioDAO implements ClsUsuarioIDAO{
    static Logger logger = Logger.getLogger(ClsUsuarioDAO.class);
    
    private static final String SP_GET_USR_GLOBALES  =
            "{call PKG_GENERAL.SP_GET_USR_GLOBALES(" + ClsSQLUtils.sqlParams(5) + ")}";
    
    private static final String S_GET_PERMISOS = 
        "{call PKG_UTIL.SP_GET_PERMISOS(" + ClsSQLUtils.sqlParams(4) + ")}";
    public ClsUsuarioDAO() {
        super();
    }
    
    public ClsResultDAO getUsuarioGlobal(ClsUsuarioIndBean objUsuarioInd) {
        logger.info("getUsuarioGlobal()");
        ClsResultDAO response = null;
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        ClsUsuarioBean objUsuario=null;
        try {
            conne = new ClsConectionDB(); 
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_USR_GLOBALES);
            int i = 0;
            logger.info("en DAO: "+objUsuarioInd.getNuIdRRHH());
            stmt.setString(++i, (objUsuarioInd.getNuIdRRHH()+""));
            stmt.setString(++i, objUsuarioInd.getVcUsuario());
            logger.info("Filtros: "+objUsuarioInd.getNuIdRRHH()+"/"+objUsuarioInd.getVcUsuario());
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMBER);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i-2); // Resultado
            
            if (rs != null) {
                objUsuario=new ClsUsuarioBean();
                while (rs.next()) {
                    logger.info("Recupera datos globales: "+rs.getString("VC_AREA"));
                    objUsuario.setNuIdRRHH(rs.getInt("NU_ID_RRHH"));
                    objUsuario.setVcNombre(rs.getString("VC_NOMBRE"));
                    objUsuario.setVcArea(rs.getString("VC_AREA"));
                    objUsuario.setVcUsuario(objUsuarioInd.getVcUsuario());
                    objUsuario.setVcEmail(rs.getString("VC_EMAIL"));
                    logger.debug("objUsuario.getVcNombre(): " + objUsuario.getVcNombre());
                    logger.debug("objUsuario.getVcArea(): " + objUsuario.getVcArea());
                    logger.debug("objUsuario.getNuIdPerfil(): " + objUsuario.getNuIdPerfil());
                    logger.debug("objUsuario.getNuIdRRHH(): " + objUsuario.getNuIdRRHH());
                }
            }
            response = new ClsResultDAO();
            logger.info("Codigo de error: "+stmt.getString(i-1)+"");
            response.put("OBJ_USR_GLOBAL", objUsuario);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getString(i-1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
            
        } catch (Throwable e) {
        } finally {
            ClsSQLUtils.closeStatement(stmt);
            ClsSQLUtils.closeConnection(conn);
        }
        return response;
    }
    
    @Override
    public ClsResultDAO doObtenerPrivilegiosUsuario(ClsUsuarioIndBean objUsuario, Integer nuIdAplicativo) {

        logger.info("En el DAO doObtenerPrivilegiosUsuario");
        ClsResultDAO objResult = new ClsResultDAO();
        ClsConectionDB conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        ClsPermisoUsuarioBean objPermisoUsuario = null;

        try {
            conne = new ClsConectionDB();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(S_GET_PERMISOS);
            int i = 0;
            stmt.setString(++i, objUsuario.getVcUsuario());
            stmt.setInt(++i, objUsuario.getNuIdPerfil());
            stmt.setInt(++i, nuIdAplicativo);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(i);

            if (rs != null) {
                objPermisoUsuario = new ClsPermisoUsuarioBean();
                while (rs.next()) {
                    switch (rs.getInt("NU_PRIVILEGIO")) {
                    
                    //*******************PER RESOLUCION INICIO******************/
                    
                    case 1:
                        {
                            logger.info("*******************PER RESOLUCION INICIO******************");
                            objPermisoUsuario.setBlPrivilegioBuscarExpedienteRES((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioBuscarExpedienteRES(): "+objPermisoUsuario.isBlPrivilegioBuscarExpedienteRES());
                            break;
                        }
                    case 2:
                        {
                            objPermisoUsuario.setBlPrivilegioCargarWorPdfRES((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioCargarWorPdfRES(): "+objPermisoUsuario.isBlPrivilegioCargarWorPdfRES());
                            break;
                        }
                    case 3:
                        {
                            objPermisoUsuario.setBlPrivilegioFirmarDigitalmenteRES((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteRES(): "+objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteRES());
                            break;
                        }
                    case 4:
                        {
                            objPermisoUsuario.setBlPrivilegioReiniciarProcesoRES((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioReiniciarProcesoRES(): "+objPermisoUsuario.isBlPrivilegioReiniciarProcesoRES());
                            logger.info("*******************PER RESOLUCION FIN******************");
                            break;
                        }
                        /*******************PER RESOLUCION FIN******************/
                    case 5:
                        {
                            logger.info("*******************PER CERTIFICADO INICIO******************");
                            objPermisoUsuario.setBlPrivilegioBuscarExpedienteCER((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioBuscarExpedienteCER(): "+objPermisoUsuario.isBlPrivilegioBuscarExpedienteCER());
                            break;
                        }
                    case 6:
                        {
                            objPermisoUsuario.setBlPrivilegioCargarWorPdfCER((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioCargarWorPdfCER(): "+objPermisoUsuario.isBlPrivilegioCargarWorPdfCER());
                            break;
                        }
                    case 7:
                        {
                            objPermisoUsuario.setBlPrivilegioFirmarDigitalmenteCER((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteCER(): "+objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteCER());
                            break;
                        }
                    case 8:
                        {
                            objPermisoUsuario.setBlPrivilegioReiniciarProcesoCER((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioReiniciarProcesoCER(): "+objPermisoUsuario.isBlPrivilegioReiniciarProcesoCER());
                            logger.info("*******************PER CERTIFICADO FIN******************");
                            break;
                        }
                    case 9:
                        {
                            logger.info("*******************PER CEDULA DE NOTIFICACION INICIO******************");
                            objPermisoUsuario.setBlPrivilegioBuscarExpedienteCNO((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioBuscarExpedienteCNO(): "+objPermisoUsuario.isBlPrivilegioBuscarExpedienteCNO());
                            break;
                        }
                    case 10:
                        {
                            objPermisoUsuario.setBlPrivilegioCargarWorPdfCNO((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioCargarWorPdfCNO(): "+objPermisoUsuario.isBlPrivilegioCargarWorPdfCNO());
                            break;
                        }
                    case 11:
                        {
                            objPermisoUsuario.setBlPrivilegioFirmarDigitalmenteCNO((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteCNO(): "+objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteCNO());
                            break;
                        }
                    case 12:
                        {
                            objPermisoUsuario.setBlPrivilegioEnviarCasillaCNO((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioEnviarCasillaCNO(): "+objPermisoUsuario.isBlPrivilegioEnviarCasillaCNO());
                            break;
                        }
                    case 13:
                        {
                            objPermisoUsuario.setBlPrivilegioReiniciarProcesoCNO((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioEnviarCasillaCNO(): "+objPermisoUsuario.isBlPrivilegioEnviarCasillaCNO());
                            logger.info("*******************PER CEDULA DE NOTIFICACION FIN******************");
                            break;
                        }
                    case 14:
                        {
                            logger.info("*******************PER RNM INICIO******************");
                            objPermisoUsuario.setBlPrivilegioBuscarExpedienteRDM((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioBuscarExpedienteRDM(): "+objPermisoUsuario.isBlPrivilegioBuscarExpedienteRDM());
                            break;
                        }
                    case 15:
                        {
                            objPermisoUsuario.setBlPrivilegioCargarWorPdfRDM((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioCargarWorPdfRDM(): "+objPermisoUsuario.isBlPrivilegioCargarWorPdfRDM());
                            break;
                        }
                    case 16:
                        {
                            objPermisoUsuario.setBlPrivilegioFirmarDigitalmenteRDM((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info(""+objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteRDM());
                            break;
                        }
                    case 17:
                        {
                            objPermisoUsuario.setBlPrivilegioEnviarCasillaRDM((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioEnviarCasillaRDM(): "+objPermisoUsuario.isBlPrivilegioEnviarCasillaRDM());
                            break;
                        }
                    case 18:
                        {
                            objPermisoUsuario.setBlPrivilegioReiniciarProcesoRDM((rs.getInt("NU_PERMISO")==1)?true:false);
                            logger.info("objPermisoUsuario.isBlPrivilegioReiniciarProcesoRDM(): "+objPermisoUsuario.isBlPrivilegioReiniciarProcesoRDM());
                            logger.info("*******************PER RNM INICIO******************");
                            break;
                        }
                    //DDA
                    //*********SOLICITUDES A SUBSANAR
                       
                        case 19:
                            {
                                logger.debug("*****ONLY_DDA INICIO*****");
                                objPermisoUsuario.setBlPrivilegioBuscarDDASOB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioBuscarDDASOB(): "+objPermisoUsuario.isBlPrivilegioBuscarDDASOB());
                                logger.info("*******************SUBSANACION******************");
                                break;
                            }
                        case 20:
                            {
                                objPermisoUsuario.setBlPrivilegioCargarWorPdfDDASOB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioCargarWorPdfDDASOB(): "+objPermisoUsuario.isBlPrivilegioCargarWorPdfDDASOB());
                                logger.info("*******************SUBSANACION******************");
                                break;
                            }
                        case 21:
                            {
                                objPermisoUsuario.setBlPrivilegioFirmarDigitalmenteDDASOB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteDDASOB(): "+objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteDDASOB());
                                logger.info("*******************SUBSANACION******************");
                                break;
                            }
                        case 22:
                            {
                                objPermisoUsuario.setBlPrivilegioEnviarCasillaDDASOB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioEnviarCasillaDDASOB(): "+objPermisoUsuario.isBlPrivilegioEnviarCasillaDDASOB());
                                logger.info("*******************SUBSANACION******************");
                                break;
                            }
                        case 23:
                            {
                                objPermisoUsuario.setBlPrivilegioReiniciarProcesoDDASOB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioReiniciarProcesoDDASOB(): "+objPermisoUsuario.isBlPrivilegioReiniciarProcesoDDASOB());
                                logger.info("*******************SUBSANACION******************");
                                break;
                            }
                    
                    //********REGISTRO DE OBRAS
                        case 24:
                            {
                                objPermisoUsuario.setBlPrivilegioBuscarDDAROB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioBuscarDDAROB(): "+objPermisoUsuario.isBlPrivilegioBuscarDDAROB());
                                logger.info("*******************REG OBRAS******************");
                                break;
                            }
                        case 25:
                            {
                                objPermisoUsuario.setBlPrivilegioCargarWorPdfDDAROB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioCargarWorPdfDDAROB(): "+objPermisoUsuario.isBlPrivilegioCargarWorPdfDDAROB());
                                logger.info("*******************REG OBRAS******************");
                                break;
                            }
                        case 26:
                            {
                                objPermisoUsuario.setBlPrivilegioFirmarDigitalmenteDDAROB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteDDAROB(): "+objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteDDAROB());
                                logger.info("*******************REG OBRAS******************");
                                break;
                            }
                        case 27:
                            {
                                objPermisoUsuario.setBlPrivilegioEnviarCasillaDDAROB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioEnviarCasillaDDAROB(): "+objPermisoUsuario.isBlPrivilegioEnviarCasillaDDAROB());
                                logger.info("*******************REG OBRAS******************");
                                break;
                            }
                    
                        case 28:
                            {
                                objPermisoUsuario.setBlPrivilegioReiniciarProcesoDDAROB((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioReiniciarProcesoDDAROB(): "+objPermisoUsuario.isBlPrivilegioReiniciarProcesoDDAROB());
                                logger.info("*******************REG OBRAS******************");
                                break;
                            }
                    //*******CERTIFICADOS
                        case 29:
                            {
                                objPermisoUsuario.setBlPrivilegioBuscarDDACER((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioBuscarDDACER(): "+objPermisoUsuario.isBlPrivilegioBuscarDDACER());
                                logger.info("*******************CERTIFICADO******************");
                                break;
                            }
                        case 30:
                            {
                                objPermisoUsuario.setBlPrivilegioCargarWorPdfDDACER((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioCargarWorPdfDDACER(): "+objPermisoUsuario.isBlPrivilegioCargarWorPdfDDACER());
                                logger.info("*******************CERTIFICADO******************");
                                break;
                            }
                        case 31:
                            {
                                objPermisoUsuario.setBlPrivilegioFirmarDigitalmenteDDACER((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteDDACER(): "+objPermisoUsuario.isBlPrivilegioFirmarDigitalmenteDDACER());
                                logger.info("*******************CERTIFICADO******************");
                                break;
                            }
                        case 32:
                            {
                                objPermisoUsuario.setBlPrivilegioEnviarCasillaDDACER((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioEnviarCasillaDDACER(): "+objPermisoUsuario.isBlPrivilegioEnviarCasillaDDACER());
                                logger.info("*******************CERTIFICADO******************");
                                break;
                            }
                        case 33:
                            {
                                objPermisoUsuario.setBlPrivilegioReiniciarProcesoDDACER((rs.getInt("NU_PERMISO")==1)?true:false);
                                logger.info("objPermisoUsuario.isBlPrivilegioReiniciarProcesoDDACER(): "+objPermisoUsuario.isBlPrivilegioReiniciarProcesoDDACER());
                                logger.info("*******************CERTIFICADO******************");
                                break;
                            }
                    default:
                        {
                            break;
                        }
                    }
                }
            }
            objResult.put("OBJ_PERMISOS", objPermisoUsuario);
        } catch (SQLException e) {
            logger.error(Priority.ERROR_INT, e);
        } catch (Exception e) {
            logger.error(Priority.ERROR_INT, e);
        } finally {
            ClsSQLUtils.closeStatement(stmt);
            ClsSQLUtils.closeConnection(conn);
        }
        
        return objResult;
    }

    
}
