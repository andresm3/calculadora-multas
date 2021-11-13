package pe.gob.indecopi.dao;

import indecopi.gob.pe.bean.ClsUsuarioIndBean;

import indecopi.gob.pe.bean.ClsUsuarioIndBean;
//import indecopi.gob.pe.util.ResultDAO;
import indecopi.gob.pe.utils.ClsResultDAO;

public interface ClsCalculoMultaIDAO {
    public ClsResultDAO doListarParametros(int nuTablaPadre);
    public ClsResultDAO doGetValorDescripcion(int nuTablaPadre, String vcParametro, int nuValor);
    public ClsResultDAO doListarMultaUITAnios();
    public ClsResultDAO doListarInstancias(String vcIdMetodo, String vcOrgResolutivo);
    public ClsResultDAO doListarMetodos(String vcInstancia, String vcOrgResolutivo);
    public ClsResultDAO doListarInfracciones(String vcMetodo, String vcInstancia, String vcOrgResolutivo);
    public ClsResultDAO doGetTamEmpresa(int nuIdMetodo, int facturacionUIT);
    public ClsResultDAO doBuscarRuc(String vcRuc);
    public ClsResultDAO doGetFD(int dias);
    public ClsResultDAO doGetGravedad(int nuIdMetodo, double multaPreliminar, String vcOrgResolutivo);
    public ClsResultDAO doGetValorMatriz(int nuIdMetodo, String vcOrgano, int vcIdInstancia, int vcIdTamanoEmpresa, int vcIdNivelAfectacion);
    
    public ClsResultDAO doGetMultaLibro(int vcIdTamanoEmpresa, String vcClasificacion);
    public ClsResultDAO doGetTamEmpresaLibro(int facturacionUIT);

    public ClsResultDAO doGetDanioBarreras(String vcClasificacion);
    public ClsResultDAO doListarProbabilidadBarreras();

    public ClsResultDAO doListarOrganosMetodo(String vcIdMetodo, int nuInstancia);
    public ClsResultDAO doComboGravedadTope(int nuIdMetodo, int nuSubTipoPi);

    public ClsResultDAO doGetGravedadVentas(int nuIdGravedad);
}
