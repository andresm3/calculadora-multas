package pe.gob.indecopi.bean;

import java.io.Serializable;


public class ClsConstantesBean implements Serializable {
    @SuppressWarnings("compatibility:-2197722396513585802")
    private static final long serialVersionUID = 1L;

    private String URL_DOWNLOAD_DOC;
    private String URL_UPLOAD_DOC;
    
    private String WS_URL_WSDL;
    private String WS_PKG;
    private String WS_SERVICE_PORT;
    
    private String WS_CE_URL_WSD;
    private String WS_CE_PKG;
    private String WS_CE_SERVICE_PORT;
    
    private String NU_ID_APLICATIVO;
    private String URL_JNLP;
    private String CLSPLANTILLA_URL;
    private String WS_SERVICE_SERV;
    private String SIGN_TSA_URL;
    private String SIGN_TSL_CK;
    private String SIGN_TSA_OID;
    private String SIGN_TSA_USER;
    private String SIGN_TSA_PASS;
    private String SIGN_VISIBLE_CK;
    private String SIGN_TSA_CK;
    private String M_URL_SERVLET;
    private String URL_UPLOAD_DOC_NOT;
    private String IDENTIFICADOR_URL;
    private String WS_IDENT_OBTENER;
    private String WS_IDENT_REGISTRAR;
    private String VERIFICADOR_URL;
    private String WS_POST;
    private String WS_GET;
    
    //Seguridad de los servicios REST
    private String vcWS_SG_URL;
    private String vcWS_SG_GRANT_TYPE;
    private String vcWS_SG_USERNAME;
    private String vcWS_SG_PASSWORD;
    private String vcWS_SG_CLIENT_SECRET;
    private String vcWS_SG_CLIENT_ID;
    //tsalinas fin
    
    public ClsConstantesBean() {
        super();
    }

    public void setURL_DOWNLOAD_DOC(String URL_DOWNLOAD_DOC) {
        this.URL_DOWNLOAD_DOC = URL_DOWNLOAD_DOC;
    }

    public String getURL_DOWNLOAD_DOC() {
        return URL_DOWNLOAD_DOC;
    }

    public void setURL_UPLOAD_DOC(String URL_UPLOAD_DOC) {
        this.URL_UPLOAD_DOC = URL_UPLOAD_DOC;
    }

    public String getURL_UPLOAD_DOC() {
        return URL_UPLOAD_DOC;
    }

    public void setWS_URL_WSDL(String WS_URL_WSDL) {
        this.WS_URL_WSDL = WS_URL_WSDL;
    }

    public String getWS_URL_WSDL() {
        return WS_URL_WSDL;
    }

    public void setWS_PKG(String WS_PKG) {
        this.WS_PKG = WS_PKG;
    }

    public String getWS_PKG() {
        return WS_PKG;
    }

    public void setWS_SERVICE_PORT(String WS_SERVICE_PORT) {
        this.WS_SERVICE_PORT = WS_SERVICE_PORT;
    }

    public String getWS_SERVICE_PORT() {
        return WS_SERVICE_PORT;
    }

    public void setNU_ID_APLICATIVO(String NU_ID_APLICATIVO) {
        this.NU_ID_APLICATIVO = NU_ID_APLICATIVO;
    }

    public String getNU_ID_APLICATIVO() {
        return NU_ID_APLICATIVO;
    }

    public void setURL_JNLP(String URL_JNLP) {
        this.URL_JNLP = URL_JNLP;
    }

    public String getURL_JNLP() {
        return URL_JNLP;
    }

    public void setCLSPLANTILLA_URL(String CLSPLANTILLA_URL) {
        this.CLSPLANTILLA_URL = CLSPLANTILLA_URL;
    }

    public String getCLSPLANTILLA_URL() {
        return CLSPLANTILLA_URL;
    }

    public void setWS_SERVICE_SERV(String WS_SERVICE_SERV) {
        this.WS_SERVICE_SERV = WS_SERVICE_SERV;
    }

    public String getWS_SERVICE_SERV() {
        return WS_SERVICE_SERV;
    }

    public void setSIGN_TSA_URL(String SIGN_TSA_URL) {
        this.SIGN_TSA_URL = SIGN_TSA_URL;
    }

    public String getSIGN_TSA_URL() {
        return SIGN_TSA_URL;
    }

    public void setSIGN_TSL_CK(String SIGN_TSL_CK) {
        this.SIGN_TSL_CK = SIGN_TSL_CK;
    }

    public String getSIGN_TSL_CK() {
        return SIGN_TSL_CK;
    }

    public void setSIGN_TSA_OID(String SIGN_TSA_OID) {
        this.SIGN_TSA_OID = SIGN_TSA_OID;
    }

    public String getSIGN_TSA_OID() {
        return SIGN_TSA_OID;
    }

    public void setSIGN_TSA_USER(String SIGN_TSA_USER) {
        this.SIGN_TSA_USER = SIGN_TSA_USER;
    }

    public String getSIGN_TSA_USER() {
        return SIGN_TSA_USER;
    }

    public void setSIGN_TSA_PASS(String SIGN_TSA_PASS) {
        this.SIGN_TSA_PASS = SIGN_TSA_PASS;
    }

    public String getSIGN_TSA_PASS() {
        return SIGN_TSA_PASS;
    }

    public void setSIGN_VISIBLE_CK(String SIGN_VISIBLE_CK) {
        this.SIGN_VISIBLE_CK = SIGN_VISIBLE_CK;
    }

    public String getSIGN_VISIBLE_CK() {
        return SIGN_VISIBLE_CK;
    }

    public void setSIGN_TSA_CK(String SIGN_TSA_CK) {
        this.SIGN_TSA_CK = SIGN_TSA_CK;
    }

    public String getSIGN_TSA_CK() {
        return SIGN_TSA_CK;
    }

    public void setM_URL_SERVLET(String M_URL_SERVLET) {
        this.M_URL_SERVLET = M_URL_SERVLET;
    }

    public String getM_URL_SERVLET() {
        return M_URL_SERVLET;
    }


    public void setURL_UPLOAD_DOC_NOT(String URL_UPLOAD_DOC_NOT) {
        this.URL_UPLOAD_DOC_NOT = URL_UPLOAD_DOC_NOT;
    }

    public String getURL_UPLOAD_DOC_NOT() {
        return URL_UPLOAD_DOC_NOT;
    }

    public void setIDENTIFICADOR_URL(String IDENTIFICADOR_URL) {
        this.IDENTIFICADOR_URL = IDENTIFICADOR_URL;
    }

    public String getIDENTIFICADOR_URL() {
        return IDENTIFICADOR_URL;
    }

    public void setWS_IDENT_OBTENER(String WS_IDENT_OBTENER) {
        this.WS_IDENT_OBTENER = WS_IDENT_OBTENER;
    }

    public String getWS_IDENT_OBTENER() {
        return WS_IDENT_OBTENER;
    }

    public void setWS_IDENT_REGISTRAR(String WS_IDENT_REGISTRAR) {
        this.WS_IDENT_REGISTRAR = WS_IDENT_REGISTRAR;
    }

    public String getWS_IDENT_REGISTRAR() {
        return WS_IDENT_REGISTRAR;
    }

    public void setVERIFICADOR_URL(String VERIFICADOR_URL) {
        this.VERIFICADOR_URL = VERIFICADOR_URL;
    }

    public String getVERIFICADOR_URL() {
        return VERIFICADOR_URL;
    }

    public void setWS_POST(String WS_POST) {
        this.WS_POST = WS_POST;
    }

    public String getWS_POST() {
        return WS_POST;
    }

    public void setWS_GET(String WS_GET) {
        this.WS_GET = WS_GET;
    }

    public String getWS_GET() {
        return WS_GET;
    }

    public void setWS_CE_URL_WSD(String WS_CE_URL_WSD) {
        this.WS_CE_URL_WSD = WS_CE_URL_WSD;
    }

    public String getWS_CE_URL_WSD() {
        return WS_CE_URL_WSD;
    }

    public void setWS_CE_PKG(String WS_CE_PKG) {
        this.WS_CE_PKG = WS_CE_PKG;
    }

    public String getWS_CE_PKG() {
        return WS_CE_PKG;
    }

    public void setWS_CE_SERVICE_PORT(String WS_CE_SERVICE_PORT) {
        this.WS_CE_SERVICE_PORT = WS_CE_SERVICE_PORT;
    }

    public String getWS_CE_SERVICE_PORT() {
        return WS_CE_SERVICE_PORT;
    }

    public void setVcWS_SG_URL(String vcWS_SG_URL) {
        this.vcWS_SG_URL = vcWS_SG_URL;
    }

    public String getVcWS_SG_URL() {
        return vcWS_SG_URL;
    }

    public void setVcWS_SG_GRANT_TYPE(String vcWS_SG_GRANT_TYPE) {
        this.vcWS_SG_GRANT_TYPE = vcWS_SG_GRANT_TYPE;
    }

    public String getVcWS_SG_GRANT_TYPE() {
        return vcWS_SG_GRANT_TYPE;
    }

    public void setVcWS_SG_USERNAME(String vcWS_SG_USERNAME) {
        this.vcWS_SG_USERNAME = vcWS_SG_USERNAME;
    }

    public String getVcWS_SG_USERNAME() {
        return vcWS_SG_USERNAME;
    }

    public void setVcWS_SG_PASSWORD(String vcWS_SG_PASSWORD) {
        this.vcWS_SG_PASSWORD = vcWS_SG_PASSWORD;
    }

    public String getVcWS_SG_PASSWORD() {
        return vcWS_SG_PASSWORD;
    }

    public void setVcWS_SG_CLIENT_SECRET(String vcWS_SG_CLIENT_SECRET) {
        this.vcWS_SG_CLIENT_SECRET = vcWS_SG_CLIENT_SECRET;
    }

    public String getVcWS_SG_CLIENT_SECRET() {
        return vcWS_SG_CLIENT_SECRET;
    }

    public void setVcWS_SG_CLIENT_ID(String vcWS_SG_CLIENT_ID) {
        this.vcWS_SG_CLIENT_ID = vcWS_SG_CLIENT_ID;
    }

    public String getVcWS_SG_CLIENT_ID() {
        return vcWS_SG_CLIENT_ID;
    }
}
