package pe.gob.indecopi.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

public class ClsProbabilidadBarrerasBean implements Serializable {
    @SuppressWarnings("compatibility:-5966657446455117186")
    private static final long serialVersionUID = 1L;

    private String vcCaso;
    private String vcDefinicion;
    private double nuFactorProbabilidad;
        
    public ClsProbabilidadBarrerasBean() {
        super();
    }


    public void setVcCaso(String vcCaso) {
        this.vcCaso = vcCaso;
    }

    public String getVcCaso() {
        return vcCaso;
    }

    public void setVcDefinicion(String vcDefinicion) {
        this.vcDefinicion = vcDefinicion;
    }

    public String getVcDefinicion() {
        return vcDefinicion;
    }

    public void setNuFactorProbabilidad(double nuFactorProbabilidad) {
        this.nuFactorProbabilidad = nuFactorProbabilidad;
    }

    public double getNuFactorProbabilidad() {
        return nuFactorProbabilidad;
    }
}
