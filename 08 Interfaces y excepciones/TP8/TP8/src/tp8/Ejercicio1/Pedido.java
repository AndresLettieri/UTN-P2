package tp8.Ejercicio1;

import java.util.List;

public class Pedido implements Pagable{
    List<Producto> productos;
    private Notificable notificable;
    private String estado;

    public Pedido(List<Producto> productos,Notificable notificable, String estado) {
        this.productos = productos;
        this.notificable = notificable;
        this.estado = estado;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        notificarEstado();
    }
    
    private void notificarEstado() {
        if (notificable != null) {
            notificable.notificarCambio("cambio el estado a: " + estado);
        }
    }
    
    @Override
    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos){
            total += p.calcularTotal();
        }
       return total;
    }
    
    public void procesarPago(Pago metodoPago) {
        double total = calcularTotal();
        metodoPago.procesarPago(total);
    }
    
    
}
