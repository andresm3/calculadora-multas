package pe.gob.indecopi.bean;

import java.io.Serializable;


public class ClsTamanioEmpresaBean implements Serializable {
    @SuppressWarnings("compatibility:-1472918741402031160")
    private static final long serialVersionUID = 1L;

    private String vcIdTamanioEmpresa;
    private String vcTamanioEmpresa;
    private int nuMinUIT;
    private int nuMaxUIT;
    private double nuPorcTope;
        
    public ClsTamanioEmpresaBean() {
        super();
    }


    public void setVcIdTamanioEmpresa(String vcIdTamanioEmpresa) {
        this.vcIdTamanioEmpresa = vcIdTamanioEmpresa;
    }

    public String getVcIdTamanioEmpresa() {
        return vcIdTamanioEmpresa;
    }

    public void setVcTamanioEmpresa(String vcTamanioEmpresa) {
        this.vcTamanioEmpresa = vcTamanioEmpresa;
    }

    public String getVcTamanioEmpresa() {
        return vcTamanioEmpresa;
    }

    public void setNuMinUIT(int nuMinUIT) {
        this.nuMinUIT = nuMinUIT;
    }

    public int getNuMinUIT() {
        return nuMinUIT;
    }

    public void setNuMaxUIT(int nuMaxUIT) {
        this.nuMaxUIT = nuMaxUIT;
    }

    public int getNuMaxUIT() {
        return nuMaxUIT;
    }

    public void setNuPorcTope(double nuPorcTope) {
        this.nuPorcTope = nuPorcTope;
    }

    public double getNuPorcTope() {
        return nuPorcTope;
    }
}
