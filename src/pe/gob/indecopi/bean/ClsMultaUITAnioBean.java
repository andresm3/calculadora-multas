package pe.gob.indecopi.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

public class ClsMultaUITAnioBean implements Serializable {
    @SuppressWarnings("compatibility:-188392927301346817")
    private static final long serialVersionUID = 1L;

    private String vcIdMultaUIT;
    private String vcMultaUIT;
    private int nuUIT;
        
    public ClsMultaUITAnioBean() {
        super();
    }


    public void setVcIdMultaUIT(String vcIdMultaUIT) {
        this.vcIdMultaUIT = vcIdMultaUIT;
    }

    public String getVcIdMultaUIT() {
        return vcIdMultaUIT;
    }

    public void setVcMultaUIT(String vcMultaUIT) {
        this.vcMultaUIT = vcMultaUIT;
    }

    public String getVcMultaUIT() {
        return vcMultaUIT;
    }

    public void setNuUIT(int nuUIT) {
        this.nuUIT = nuUIT;
    }

    public int getNuUIT() {
        return nuUIT;
    }
}
