package pe.gob.indecopi.bean;

import java.io.Serializable;


public class ClsInstanciasBean implements Serializable {
    @SuppressWarnings("compatibility:-2392523180371135492")
    private static final long serialVersionUID = 1L;

    private int nuIdInstancia;
    private String vcInstancia;
        
    public ClsInstanciasBean() {
        super();
    }

    public void setNuIdInstancia(int nuIdInstancia) {
        this.nuIdInstancia = nuIdInstancia;
    }

    public int getNuIdInstancia() {
        return nuIdInstancia;
    }

    public void setVcInstancia(String vcInstancia) {
        this.vcInstancia = vcInstancia;
    }

    public String getVcInstancia() {
        return vcInstancia;
    }
}
