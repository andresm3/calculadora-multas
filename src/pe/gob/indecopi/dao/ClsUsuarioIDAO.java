package pe.gob.indecopi.dao;

import indecopi.gob.pe.bean.ClsUsuarioIndBean;

import indecopi.gob.pe.bean.ClsUsuarioIndBean;
//import indecopi.gob.pe.util.ResultDAO;
import indecopi.gob.pe.utils.ClsResultDAO;

public interface ClsUsuarioIDAO {
    public ClsResultDAO getUsuarioGlobal(ClsUsuarioIndBean objUsuarioInd);
    public ClsResultDAO doObtenerPrivilegiosUsuario(ClsUsuarioIndBean objUsuario, Integer nuIdAplicativo);
}
