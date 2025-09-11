package tp5;

public class Vehiculo {
    private String patente;
    private String modelo;
    private Motor motor;
    private Conductor conductor;
    
    public Vehiculo(String patente, String modelo, Motor motor){
        this.patente = patente;
        this.modelo = modelo;
        this.motor = motor;
    }
    
    public void setConductor(Conductor conductor){
        this.conductor = conductor;
        if (conductor != null && conductor.GetVehiculo() != this)
            conductor.setVehiculo(this);
    }
    
    public Conductor getConductor(){
        return conductor;
    }
    
    public void mostrarMotor(){
        if (motor != null){
            System.out.println("El motor del vehiculo " + patente + " es el " + motor.getTipo());
        }else{
            System.out.println("El vehiculo " + patente  + " no tiene un motor asignado.");
        }
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente!="")
            this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo!="")
            this.modelo = modelo;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "patente=" + patente + ", modelo=" + modelo + ", motor=" + motor + ", conductor=" + conductor + '}';
    }
    
    
    
    
}
