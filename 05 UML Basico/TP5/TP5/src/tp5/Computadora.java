package tp5;

public class Computadora {
    private String marca;
    private String numeroDeSerie;
    private Propietario propietario;
    private PlacaMadre placaMadre;
    
    public Computadora(String marca, String numeroDeSerie, String modelo, String chipset){
        this.marca = marca;
        this.numeroDeSerie = numeroDeSerie;
        this.placaMadre = new PlacaMadre(modelo,chipset);
    }
    
    public void SetPropietario(Propietario propietario){
        this.propietario = propietario;
        if (propietario != null && propietario.getComputadora() != this)
            propietario.setComputadora(this);
    }

    
    public Propietario GetPropietario(){
        return propietario;
    }
    
    public void mostrarPlacaMadre(){
        System.out.println("La computadora " + marca + " tiene una placa madre " + placaMadre.toString());
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    @Override
    public String toString() {
        return "Computadora{" + "marca=" + marca + ", numeroDeSerie=" + numeroDeSerie + ", propietario=" + propietario + ", placaMadre=" + placaMadre + '}';
    }


    
    
}
