package Entities;

public abstract class Base {
    private long id;
    private Boolean eliminado;

    public Base(long id, Boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }
    
    public Base() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    
}
