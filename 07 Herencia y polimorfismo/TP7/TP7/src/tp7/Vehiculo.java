
package tp7;

public class Vehiculo {
    private String marca;
    private String modelo;

    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if(!marca.isEmpty()) this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if(!modelo.isEmpty()) this.modelo = modelo;
    }
    
    
    public String mostrarInfo(){
        return "Marca: " + marca + ". Modelo: " + modelo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "marca=" + marca + ", modelo=" + modelo + '}';
    }
    
    
}
