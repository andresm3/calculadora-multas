package pe.gob.indecopi.servlet;

import indecopi.gob.pe.bean.ClsUsuarioIndBean;

//import pe.gob.indecopi.bean.ClsPermisoUsuarioBean;
import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.dao.ClsUsuarioDAO;
import pe.gob.indecopi.dao.ClsUsuarioIDAO;
import pe.gob.indecopi.srv.ClsCalculoMultaSrv;

//import pe.gob.indecopi.util.ClsUtils;
//import indecopi.gob.pe.util.ResultDAO;
import indecopi.gob.pe.utils.ClsConstantes;
import indecopi.gob.pe.utils.ClsCripto;

import indecopi.gob.pe.utils.ClsResultDAO;

import java.io.IOException;

import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ClsCalculoMultaServlet extends HttpServlet implements Serializable {
    @SuppressWarnings("compatibility:4159639979006094555")
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(ClsCalculoMultaServlet.class);
        
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String jgapps = request.getParameter(ClsConstantes.PARAMETRO_GET);
        
        //jgapps = "2d5d5a4d4f40384054695d04701e0d09";              //mparedes  PS1
        //jgapps = "2a495e534d45395c4a2415417418030f1a1d6f05";      //jdelgadob  PS2
        //jgapps = "2a4f495e4945305c462c44417251020e1a1064";        //jbracamonte  PS3
        //jgapps = "27575a4f4b503c4f1c24100c711b03";                //gzapata  CC1
        //jgapps = "2a4a4e5a5c452f5254695d0c71190a0f";              //jguevara  CC2
        //jgapps = "235e5a51494c38494e241041711c0a071d";            //csanchezf  SPC
        //jgapps = "2a4a4e4b43412f414d225d0f3c1c0b061d16";          //jgutierrez  CC3
        //jgapps = "234c49524b57210254611008701b";                  //carmas  CEB
        //jgapps = "254e5a4d4845375244241041791c080812";            //ecarbajal  SEL
        //jgapps = "265b525a4456345040241041711d080a13";            //fvienrich  DDA
        //jgapps = "2d4e5a4c5e56324f1a24100d741a0a";                //mcastro  DIN
        //jgapps = "2a435a494b562f5c546a5d0c70190a07";              //jnavarro  DSD
        //jgapps = "274f5e5143503840546d5d0c711d0d08";              //gbenites  SPI
        //jgapps = "214c495e4745245c54695d0c701a0a09";              //aaramayo  CCD
        //jgapps = "214e534a4746384f1924100d791c0a";                //achumbe  CLC
        //jgapps = "23405a524b4a344f1924100c751d03";                //cmamani  SDC
        //jgapps = "304e5a4c5e4d315f49241041711d0d0618";                //pcastilla  IOFE

        //jgapps = "000";      //xxx  PS0
        //jgapps = "000";      //yyy  CPC

        jgapps="2a495e534d45395c4a2415417418030f1a1d6f05";


        ClsCripto ClsCripto = new ClsCripto();
        String datosUsuario = ClsCripto.desencripta(jgapps);
        request.getSession().invalidate();
        String[] variables = datosUsuario.split(ClsConstantes.SEPARADOR);
        ClsUsuarioIndBean objUsuario = new ClsUsuarioIndBean();
        objUsuario.setVcUsuario(variables[0]);
        objUsuario.setNuIdPerfil(Integer.parseInt(variables[1].toString()));
        objUsuario.setNuIdRRHH(Integer.parseInt(variables[2].toString()));
        //Obtener el tipo de aplicacion
        logger.info(">>>>>>>>>>>>>>>>>>>: " + datosUsuario);
        logger.info("... Start Aplication ..." );
        logger.info(">>Usuario: " + objUsuario.getVcUsuario());
        logger.info(">>Id RRHH: " + objUsuario.getNuIdRRHH());
        logger.info(">>Perfil: " + objUsuario.getNuIdPerfil());

        ClsCalculoMultaSrv objCalculoMultaSrv = (ClsCalculoMultaSrv) request.getSession().getAttribute("clsCalculoMultaSrv");
        ClsUsuarioBean objUsuarioBean = (ClsUsuarioBean) request.getSession().getAttribute("clsUsuarioBean");
        //ClsPermisoUsuarioBean objPermiso=(ClsPermisoUsuarioBean)request.getSession().getAttribute("clsPermisoUsuarioBean");
    
        if (!request.getSession().isNew())
                request.getSession(false).invalidate();

        if(objUsuarioBean==null){
            ClsUsuarioIDAO objUsuarioDAO=new ClsUsuarioDAO();
            ClsResultDAO objResultadoDAO=objUsuarioDAO.getUsuarioGlobal(objUsuario);
            objUsuarioBean=(ClsUsuarioBean)objResultadoDAO.get("OBJ_USR_GLOBAL");
        }

        /* if(objPermiso==null){
            //Permisos
            ClsUsuarioIDAO objUsuarioDAO=new ClsUsuarioDAO();
            ResultDAO objResultPermisosDAO =objUsuarioDAO.doObtenerPrivilegiosUsuario(objUsuario, 149); //7 el numero del plicativo
            objPermiso=(ClsPermisoUsuarioBean)objResultPermisosDAO.get("OBJ_PERMISOS");
        } */
        
        if (objCalculoMultaSrv == null) {
            //objCalculoMultaSrv = new ClsCalculoMultaSrv(objUsuario,vcOrgResolutivo); //, objUsuarioBean, objPermiso);
            objCalculoMultaSrv = new ClsCalculoMultaSrv(objUsuario, objUsuarioBean); //, objPermiso);

        }
        
        request.getSession().setAttribute("clsCalculoMultaSrv", objCalculoMultaSrv);
        response.sendRedirect(request.getContextPath() + "/procesos/pgw_calculoMulta.seam");//@amolero  
        //response.sendRedirect(request.getContextPath() + "/procesos/pgw_inicio.seam");//@amolero  

        /* } else {
            
        response.sendRedirect(request.getContextPath() + "/pgw_error.seam");
        } */
            
    }   
}       
