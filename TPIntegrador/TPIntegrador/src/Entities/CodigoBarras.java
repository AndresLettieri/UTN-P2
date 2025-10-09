package Entities;

import java.time.LocalDate;

public class CodigoBarras extends Base {
    

    private TipoCodigoBarras tipo;
    private String valor;
    private LocalDate fechaAsignacion;
    private String observaciones;

    
    private static final int MAX_VALOR_LENGTH = 20;
    private static final int MAX_OBSERVACIONES_LENGTH = 255;
    
    //constructor para nuevos codigos de barras
    public CodigoBarras(TipoCodigoBarras tipo, String valor, LocalDate fechaAsignacion, String observaciones) {
        super();
        this.tipo = tipo;
        this.valor = valor;
        this.fechaAsignacion = fechaAsignacion;
        this.observaciones = observaciones;
    }
      
    //constructor para recuperar codigos de barras ya creados en base    
    public CodigoBarras(long id, TipoCodigoBarras tipo, String valor, boolean eliminado, LocalDate fechaAsignacion, String observaciones) {
        super(id, eliminado);
        this.tipo = tipo;
        this.valor = valor;
        this.fechaAsignacion = fechaAsignacion;
        this.observaciones = observaciones;
    }
    
    public CodigoBarras() {
        super();
    }

    public TipoCodigoBarras getTipo() {
        return tipo;
    }

    public void setTipo(TipoCodigoBarras tipo) {
        if (tipo != null) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("El tipo no puede ser vacio.");
        }
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        if (valor != null && !valor.isEmpty() && valor.length() <= MAX_VALOR_LENGTH) {
            this.valor = valor;
        } else {
            throw new IllegalArgumentException("El valor no puede ser vacio y debe tener máximo " + MAX_VALOR_LENGTH + " caracteres.");
        }
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        if (observaciones.length() <= MAX_OBSERVACIONES_LENGTH) {
            this.observaciones = observaciones;
        } else {
            throw new IllegalArgumentException("Obsevaciones debe tener máximo " + MAX_VALOR_LENGTH + " caracteres.");
        }
    }

    @Override
    public String toString() {
        return "CodigoBarras: " 
                + "id=" + getId()  
                + ", eliminado=" + isEliminado() 
                + ", tipo=" + tipo 
                + ", valor=" + valor 
                + ", fechaAsignacion=" + fechaAsignacion
                + ", observaciones=" + observaciones;
    }
    
    

    
}