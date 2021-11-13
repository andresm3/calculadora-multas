package pe.gob.indecopi.bean;

import java.io.Serializable;


public class ClsPermisoUsuarioBean implements Serializable {
    @SuppressWarnings("compatibility:1959894149498258038")
    private static final long serialVersionUID = 1L;
    /* //bloque A comentado por mgutierrezr por cambio en los perfiles
    private Integer nuPrivilegioConsultarExpedientes = 0; //1 y 11
    private Integer nuPrivilegioConsultarGenerarDocWords = 0;//2 y 12
    private Integer nuPrivilegioExportarExcel = 0;//3 y 13
    private Integer nuPrivilegioBuscarExpedientes = 0;//4 y 14
    private Integer nuPrivilegioCargarWord = 0;//5 y 15
    private Integer nuPrivilegioConvertirWord = 0;//6 y 16
    private Integer nuPrivilegioFirmarDigital = 0; //7 y 17
    private Integer nuPrivilegioDescargarPDFFirma = 0;//8 y 18
    private Integer nuPrivilegioEliminarDocs = 0;// 9 y 19
    private Integer nuPrivilegioPestanaReporte = 0;//10 y 20
    */ //bloque A comentado por mgutierrezr por cambio en los perfiles
        
    private boolean blPrivilegioBuscarExpedienteRES = false;
    private boolean blPrivilegioCargarWorPdfRES = false;
    private boolean blPrivilegioFirmarDigitalmenteRES = false;
    private boolean blPrivilegioReiniciarProcesoRES = false;
    
    private boolean blPrivilegioBuscarExpedienteCER = false;
    private boolean blPrivilegioCargarWorPdfCER = false;
    private boolean blPrivilegioFirmarDigitalmenteCER = false;
    private boolean blPrivilegioReiniciarProcesoCER = false;
    
    private boolean blPrivilegioBuscarExpedienteCNO = false;
    private boolean blPrivilegioCargarWorPdfCNO = false;
    private boolean blPrivilegioFirmarDigitalmenteCNO = false;
    private boolean blPrivilegioEnviarCasillaCNO = false;
    private boolean blPrivilegioReiniciarProcesoCNO = false;
    
    private boolean blPrivilegioBuscarExpedienteRDM = false;
    private boolean blPrivilegioCargarWorPdfRDM = false;
    private boolean blPrivilegioFirmarDigitalmenteRDM = false;
    private boolean blPrivilegioEnviarCasillaRDM = false;
    private boolean blPrivilegioReiniciarProcesoRDM = false;
    
    //Privilegios DDA
    private boolean blPrivilegioBuscarDDASOB = false;
    private boolean blPrivilegioCargarWorPdfDDASOB = false;
    private boolean blPrivilegioFirmarDigitalmenteDDASOB = false;
    private boolean blPrivilegioEnviarCasillaDDASOB = false;
    private boolean blPrivilegioReiniciarProcesoDDASOB = false;
    
    private boolean blPrivilegioBuscarDDAROB = false;
    private boolean blPrivilegioCargarWorPdfDDAROB = false;
    private boolean blPrivilegioFirmarDigitalmenteDDAROB = false;
    private boolean blPrivilegioEnviarCasillaDDAROB = false;
    private boolean blPrivilegioReiniciarProcesoDDAROB = false;
    
    private boolean blPrivilegioBuscarDDACER = false;
    private boolean blPrivilegioCargarWorPdfDDACER = false;
    private boolean blPrivilegioFirmarDigitalmenteDDACER = false;
    private boolean blPrivilegioEnviarCasillaDDACER = false;
    private boolean blPrivilegioReiniciarProcesoDDACER = false;
    
    public ClsPermisoUsuarioBean() {
        super();
    }


    public void setBlPrivilegioBuscarDDASOB(boolean blPrivilegioBuscarDDASOB) {
        this.blPrivilegioBuscarDDASOB = blPrivilegioBuscarDDASOB;
    }

    public boolean isBlPrivilegioBuscarDDASOB() {
        return blPrivilegioBuscarDDASOB;
    }

    public void setBlPrivilegioCargarWorPdfDDASOB(boolean blPrivilegioCargarWorPdfDDASOB) {
        this.blPrivilegioCargarWorPdfDDASOB = blPrivilegioCargarWorPdfDDASOB;
    }

    public boolean isBlPrivilegioCargarWorPdfDDASOB() {
        return blPrivilegioCargarWorPdfDDASOB;
    }

    public void setBlPrivilegioFirmarDigitalmenteDDASOB(boolean blPrivilegioFirmarDigitalmenteDDASOB) {
        this.blPrivilegioFirmarDigitalmenteDDASOB = blPrivilegioFirmarDigitalmenteDDASOB;
    }

    public boolean isBlPrivilegioFirmarDigitalmenteDDASOB() {
        return blPrivilegioFirmarDigitalmenteDDASOB;
    }

    public void setBlPrivilegioEnviarCasillaDDASOB(boolean blPrivilegioEnviarCasillaDDASOB) {
        this.blPrivilegioEnviarCasillaDDASOB = blPrivilegioEnviarCasillaDDASOB;
    }

    public boolean isBlPrivilegioEnviarCasillaDDASOB() {
        return blPrivilegioEnviarCasillaDDASOB;
    }

    public void setBlPrivilegioReiniciarProcesoDDASOB(boolean blPrivilegioReiniciarProcesoDDASOB) {
        this.blPrivilegioReiniciarProcesoDDASOB = blPrivilegioReiniciarProcesoDDASOB;
    }

    public boolean isBlPrivilegioReiniciarProcesoDDASOB() {
        return blPrivilegioReiniciarProcesoDDASOB;
    }

    public void setBlPrivilegioBuscarDDAROB(boolean blPrivilegioBuscarDDAROB) {
        this.blPrivilegioBuscarDDAROB = blPrivilegioBuscarDDAROB;
    }

    public boolean isBlPrivilegioBuscarDDAROB() {
        return blPrivilegioBuscarDDAROB;
    }

    public void setBlPrivilegioCargarWorPdfDDAROB(boolean blPrivilegioCargarWorPdfDDAROB) {
        this.blPrivilegioCargarWorPdfDDAROB = blPrivilegioCargarWorPdfDDAROB;
    }

    public boolean isBlPrivilegioCargarWorPdfDDAROB() {
        return blPrivilegioCargarWorPdfDDAROB;
    }

    public void setBlPrivilegioFirmarDigitalmenteDDAROB(boolean blPrivilegioFirmarDigitalmenteDDAROB) {
        this.blPrivilegioFirmarDigitalmenteDDAROB = blPrivilegioFirmarDigitalmenteDDAROB;
    }

    public boolean isBlPrivilegioFirmarDigitalmenteDDAROB() {
        return blPrivilegioFirmarDigitalmenteDDAROB;
    }

    public void setBlPrivilegioEnviarCasillaDDAROB(boolean blPrivilegioEnviarCasillaDDAROB) {
        this.blPrivilegioEnviarCasillaDDAROB = blPrivilegioEnviarCasillaDDAROB;
    }

    public boolean isBlPrivilegioEnviarCasillaDDAROB() {
        return blPrivilegioEnviarCasillaDDAROB;
    }

    public void setBlPrivilegioReiniciarProcesoDDAROB(boolean blPrivilegioReiniciarProcesoDDAROB) {
        this.blPrivilegioReiniciarProcesoDDAROB = blPrivilegioReiniciarProcesoDDAROB;
    }

    public boolean isBlPrivilegioReiniciarProcesoDDAROB() {
        return blPrivilegioReiniciarProcesoDDAROB;
    }

    public void setBlPrivilegioBuscarDDACER(boolean blPrivilegioBuscarDDACER) {
        this.blPrivilegioBuscarDDACER = blPrivilegioBuscarDDACER;
    }

    public boolean isBlPrivilegioBuscarDDACER() {
        return blPrivilegioBuscarDDACER;
    }

    public void setBlPrivilegioCargarWorPdfDDACER(boolean blPrivilegioCargarWorPdfDDACER) {
        this.blPrivilegioCargarWorPdfDDACER = blPrivilegioCargarWorPdfDDACER;
    }

    public boolean isBlPrivilegioCargarWorPdfDDACER() {
        return blPrivilegioCargarWorPdfDDACER;
    }

    public void setBlPrivilegioFirmarDigitalmenteDDACER(boolean blPrivilegioFirmarDigitalmenteDDACER) {
        this.blPrivilegioFirmarDigitalmenteDDACER = blPrivilegioFirmarDigitalmenteDDACER;
    }

    public boolean isBlPrivilegioFirmarDigitalmenteDDACER() {
        return blPrivilegioFirmarDigitalmenteDDACER;
    }

    public void setBlPrivilegioEnviarCasillaDDACER(boolean blPrivilegioEnviarCasillaDDACER) {
        this.blPrivilegioEnviarCasillaDDACER = blPrivilegioEnviarCasillaDDACER;
    }

    public boolean isBlPrivilegioEnviarCasillaDDACER() {
        return blPrivilegioEnviarCasillaDDACER;
    }

    public void setBlPrivilegioReiniciarProcesoDDACER(boolean blPrivilegioReiniciarProcesoDDACER) {
        this.blPrivilegioReiniciarProcesoDDACER = blPrivilegioReiniciarProcesoDDACER;
    }

    public boolean isBlPrivilegioReiniciarProcesoDDACER() {
        return blPrivilegioReiniciarProcesoDDACER;
    }

    public void setBlPrivilegioBuscarExpedienteRES(boolean blPrivilegioBuscarExpedienteRES) {
        this.blPrivilegioBuscarExpedienteRES = blPrivilegioBuscarExpedienteRES;
    }

    public boolean isBlPrivilegioBuscarExpedienteRES() {
        return blPrivilegioBuscarExpedienteRES;
    }

    public void setBlPrivilegioCargarWorPdfRES(boolean blPrivilegioCargarWorPdfRES) {
        this.blPrivilegioCargarWorPdfRES = blPrivilegioCargarWorPdfRES;
    }

    public boolean isBlPrivilegioCargarWorPdfRES() {
        return blPrivilegioCargarWorPdfRES;
    }

    public void setBlPrivilegioFirmarDigitalmenteRES(boolean blPrivilegioFirmarDigitalmenteRES) {
        this.blPrivilegioFirmarDigitalmenteRES = blPrivilegioFirmarDigitalmenteRES;
    }

    public boolean isBlPrivilegioFirmarDigitalmenteRES() {
        return blPrivilegioFirmarDigitalmenteRES;
    }

    public void setBlPrivilegioReiniciarProcesoRES(boolean blPrivilegioReiniciarProcesoRES) {
        this.blPrivilegioReiniciarProcesoRES = blPrivilegioReiniciarProcesoRES;
    }

    public boolean isBlPrivilegioReiniciarProcesoRES() {
        return blPrivilegioReiniciarProcesoRES;
    }

    public void setBlPrivilegioBuscarExpedienteCER(boolean blPrivilegioBuscarExpedienteCER) {
        this.blPrivilegioBuscarExpedienteCER = blPrivilegioBuscarExpedienteCER;
    }

    public boolean isBlPrivilegioBuscarExpedienteCER() {
        return blPrivilegioBuscarExpedienteCER;
    }

    public void setBlPrivilegioCargarWorPdfCER(boolean blPrivilegioCargarWorPdfCER) {
        this.blPrivilegioCargarWorPdfCER = blPrivilegioCargarWorPdfCER;
    }

    public boolean isBlPrivilegioCargarWorPdfCER() {
        return blPrivilegioCargarWorPdfCER;
    }

    public void setBlPrivilegioFirmarDigitalmenteCER(boolean blPrivilegioFirmarDigitalmenteCER) {
        this.blPrivilegioFirmarDigitalmenteCER = blPrivilegioFirmarDigitalmenteCER;
    }

    public boolean isBlPrivilegioFirmarDigitalmenteCER() {
        return blPrivilegioFirmarDigitalmenteCER;
    }

    public void setBlPrivilegioReiniciarProcesoCER(boolean blPrivilegioReiniciarProcesoCER) {
        this.blPrivilegioReiniciarProcesoCER = blPrivilegioReiniciarProcesoCER;
    }

    public boolean isBlPrivilegioReiniciarProcesoCER() {
        return blPrivilegioReiniciarProcesoCER;
    }

    public void setBlPrivilegioBuscarExpedienteCNO(boolean blPrivilegioBuscarExpedienteCNO) {
        this.blPrivilegioBuscarExpedienteCNO = blPrivilegioBuscarExpedienteCNO;
    }

    public boolean isBlPrivilegioBuscarExpedienteCNO() {
        return blPrivilegioBuscarExpedienteCNO;
    }

    public void setBlPrivilegioCargarWorPdfCNO(boolean blPrivilegioCargarWorPdfCNO) {
        this.blPrivilegioCargarWorPdfCNO = blPrivilegioCargarWorPdfCNO;
    }

    public boolean isBlPrivilegioCargarWorPdfCNO() {
        return blPrivilegioCargarWorPdfCNO;
    }

    public void setBlPrivilegioFirmarDigitalmenteCNO(boolean blPrivilegioFirmarDigitalmenteCNO) {
        this.blPrivilegioFirmarDigitalmenteCNO = blPrivilegioFirmarDigitalmenteCNO;
    }

    public boolean isBlPrivilegioFirmarDigitalmenteCNO() {
        return blPrivilegioFirmarDigitalmenteCNO;
    }

    public void setBlPrivilegioEnviarCasillaCNO(boolean blPrivilegioEnviarCasillaCNO) {
        this.blPrivilegioEnviarCasillaCNO = blPrivilegioEnviarCasillaCNO;
    }

    public boolean isBlPrivilegioEnviarCasillaCNO() {
        return blPrivilegioEnviarCasillaCNO;
    }

    public void setBlPrivilegioReiniciarProcesoCNO(boolean blPrivilegioReiniciarProcesoCNO) {
        this.blPrivilegioReiniciarProcesoCNO = blPrivilegioReiniciarProcesoCNO;
    }

    public boolean isBlPrivilegioReiniciarProcesoCNO() {
        return blPrivilegioReiniciarProcesoCNO;
    }

    public void setBlPrivilegioBuscarExpedienteRDM(boolean blPrivilegioBuscarExpedienteRDM) {
        this.blPrivilegioBuscarExpedienteRDM = blPrivilegioBuscarExpedienteRDM;
    }

    public boolean isBlPrivilegioBuscarExpedienteRDM() {
        return blPrivilegioBuscarExpedienteRDM;
    }

    public void setBlPrivilegioCargarWorPdfRDM(boolean blPrivilegioCargarWorPdfRDM) {
        this.blPrivilegioCargarWorPdfRDM = blPrivilegioCargarWorPdfRDM;
    }

    public boolean isBlPrivilegioCargarWorPdfRDM() {
        return blPrivilegioCargarWorPdfRDM;
    }

    public void setBlPrivilegioFirmarDigitalmenteRDM(boolean blPrivilegioFirmarDigitalmenteRDM) {
        this.blPrivilegioFirmarDigitalmenteRDM = blPrivilegioFirmarDigitalmenteRDM;
    }

    public boolean isBlPrivilegioFirmarDigitalmenteRDM() {
        return blPrivilegioFirmarDigitalmenteRDM;
    }

    public void setBlPrivilegioEnviarCasillaRDM(boolean blPrivilegioEnviarCasillaRDM) {
        this.blPrivilegioEnviarCasillaRDM = blPrivilegioEnviarCasillaRDM;
    }

    public boolean isBlPrivilegioEnviarCasillaRDM() {
        return blPrivilegioEnviarCasillaRDM;
    }

    public void setBlPrivilegioReiniciarProcesoRDM(boolean blPrivilegioReiniciarProcesoRDM) {
        this.blPrivilegioReiniciarProcesoRDM = blPrivilegioReiniciarProcesoRDM;
    }

    public boolean isBlPrivilegioReiniciarProcesoRDM() {
        return blPrivilegioReiniciarProcesoRDM;
    }
}
