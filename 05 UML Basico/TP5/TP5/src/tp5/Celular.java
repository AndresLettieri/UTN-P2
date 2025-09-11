package tp5;

public class Celular {
    private String imei;
    private String marca;    
    private String modelo;
    private Usuario usuario;
    private Bateria bateria;
    
    public Celular(String imei, String marca,String modelo, Bateria bateria){
        this.imei = imei;
        this.marca = marca;
        this.modelo = modelo;
        this.bateria = bateria;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        if (usuario != null && usuario.getCelular() != this)
            usuario.setCelular(this);
    }

    
    public void mostrarBateria(){
        if (bateria != null){
            System.out.println("El celular " + marca + " " + modelo + " tiene una bateria " + bateria.getMarca() +
            " con una capacidad de " + bateria.getCapacidad());
        }else{
            System.out.println("El celular " + marca + " " + modelo + " no tiene una bateria asignada.");
        }
    }
  
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca != "")
            this.marca = marca;
    }

    public void setBateria(Bateria bateria) {
        this.bateria = bateria;
    }
    
    

    @Override
    public String toString() {
        return "Celular{" + "imei=" + imei + ", marca=" + marca + ", modelo=" + modelo + ", usuario=" + usuario + ", bateria=" + bateria + '}';
    }

    
    
    
}
