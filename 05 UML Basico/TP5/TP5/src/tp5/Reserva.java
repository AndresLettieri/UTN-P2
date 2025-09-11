package tp5;

public class Reserva {
    private String fecha;
    private String hora;
    private ClienteReserva clienteReserva;
    private Mesa mesa;
    
    public Reserva(String fecha, String hora, ClienteReserva clienteReserva){
        this.fecha = fecha;
        this.hora = hora;
        this.clienteReserva = clienteReserva;
    }
    
    public void mostrarMesa(){
        if (mesa != null){
            System.out.println("La reserva de la fecha " + fecha + " a las " + hora + " pertenece a la mesa " + mesa.getNumero());
        }else{
            System.out.println("El reserva de la fecha "+ fecha + " a las " + hora + " no tiene una mesa asignada.");
        }
    }
    
    public void mostrarCliente(){
        System.out.println("La reserva de la fecha " + fecha + " a las " + hora + "pertenece al cliente " + clienteReserva.getNombre());
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public ClienteReserva getClienteReserva() {
        return clienteReserva;
    }

    public void setClienteReserva(ClienteReserva clienteReserva) {
        this.clienteReserva = clienteReserva;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        return "Reserva{" + "fecha=" + fecha + ", hora=" + hora + ", clienteReserva=" + clienteReserva + ", mesa=" + mesa + '}';
    }

    
    
    
}
