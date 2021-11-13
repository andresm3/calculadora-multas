package pe.gob.indecopi.bean;

import java.io.Serializable;


public class ClsTipoAfectacionBean implements Serializable {
    @SuppressWarnings("compatibility:5589067065652504369")
    private static final long serialVersionUID = 1L;

    private String vcIdTipoAfectacion;
    private String vcTipoAfectacion;
    private String vcIdNivelAfectacion;
    private String vcNivelAfectacion;
    private String vcClasificacion;
    private String vcClasificacionBarrera;
    private int nuDanioBase;
    private double nuFactorG;
        
    public ClsTipoAfectacionBean() {
        super();
    }


    public void setVcIdTipoAfectacion(String vcIdTipoAfectacion) {
        this.vcIdTipoAfectacion = vcIdTipoAfectacion;
    }

    public String getVcIdTipoAfectacion() {
        return vcIdTipoAfectacion;
    }

    public void setVcTipoAfectacion(String vcTipoAfectacion) {
        this.vcTipoAfectacion = vcTipoAfectacion;
    }

    public String getVcTipoAfectacion() {
        return vcTipoAfectacion;
    }

    public void setVcNivelAfectacion(String vcNivelAfectacion) {
        this.vcNivelAfectacion = vcNivelAfectacion;
    }

    public String getVcNivelAfectacion() {
        return vcNivelAfectacion;
    }

    public void setVcIdNivelAfectacion(String vcIdNivelAfectacion) {
        this.vcIdNivelAfectacion = vcIdNivelAfectacion;
    }

    public String getVcIdNivelAfectacion() {
        return vcIdNivelAfectacion;
    }

    public void setVcClasificacion(String vcClasificacion) {
        this.vcClasificacion = vcClasificacion;
    }

    public String getVcClasificacion() {
        return vcClasificacion;
    }

    public void setVcClasificacionBarrera(String vcClasificacionBarrera) {
        this.vcClasificacionBarrera = vcClasificacionBarrera;
    }

    public String getVcClasificacionBarrera() {
        return vcClasificacionBarrera;
    }

    public void setNuDanioBase(int nuDanioBase) {
        this.nuDanioBase = nuDanioBase;
    }

    public int getNuDanioBase() {
        return nuDanioBase;
    }

    public void setNuFactorG(double nuFactorG) {
        this.nuFactorG = nuFactorG;
    }

    public double getNuFactorG() {
        return nuFactorG;
    }
    public String getVcValorNivelClasif() {
        return getVcClasificacion()!=null?getVcClasificacion():getVcNivelAfectacion()!=null?getVcNivelAfectacion():getVcClasificacionBarrera()!=null?getVcClasificacionBarrera():null;
    }
}
