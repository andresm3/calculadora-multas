package pe.gob.indecopi.bean;

import java.io.Serializable;


public class ClsMetodoCalculoBean implements Serializable {
    @SuppressWarnings({ "compatibility:-2392523180371135492", "oracle.jdeveloper.java.serialversionuid-stale" })
    private static final long serialVersionUID = 1L;

    private int nuIdMetodoCalculo;
    private String vcMetodoCalculo;
      
    @SuppressWarnings("oracle.jdeveloper.java.tag-is-missing")  
    public ClsMetodoCalculoBean() {
        super();
    }


    /*public void setVcIdMetodoCalculo(String nuIdMetodoCalculo) {
        this.nuIdMetodoCalculo = nuIdMetodoCalculo;
    }

    public String getVcIdMetodoCalculo() {
        return nuIdMetodoCalculo;
    }*/

    @SuppressWarnings("oracle.jdeveloper.java.tag-is-missing")
    public void setVcMetodoCalculo(String vcMetodoCalculo) {
        this.vcMetodoCalculo = vcMetodoCalculo;
    }

    @SuppressWarnings("oracle.jdeveloper.java.tag-is-missing")
    public String getVcMetodoCalculo() {
        return vcMetodoCalculo;
    }

    @SuppressWarnings("oracle.jdeveloper.java.tag-is-missing")
    public void setNuIdMetodoCalculo(int nuIdMetodoCalculo) {
        this.nuIdMetodoCalculo = nuIdMetodoCalculo;
    }

    @SuppressWarnings("oracle.jdeveloper.java.tag-is-missing")
    public int getNuIdMetodoCalculo() {
        return nuIdMetodoCalculo;
    }
}
