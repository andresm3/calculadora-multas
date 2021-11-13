package pe.gob.indecopi.bean;

import java.io.Serializable;


public class ClsTamanioEmpresaBean implements Serializable {
    @SuppressWarnings("compatibility:-1472918741402031160")
    private static final long serialVersionUID = 1L;

    private String vcIdTamanioEmpresa;
    private String vcTamanioEmpresa;
        
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
}
