import java.util.ArrayList;

public class Pedido {
    private int idPedido;
    private ArrayList<Articulo> articulos;
    private ArrayList<Integer> cantidades;

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
        this.articulos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public ArrayList<Integer> getCantidades() {
        return cantidades;
    }

    public void agregarArticulo(Articulo articulo, int cantidad) {
        articulos.add(articulo);
        cantidades.add(cantidad);
    }

    public void eliminarArticuloPorIndice(int indice) {
        if (indice >= 0 && indice < articulos.size()) {
            articulos.remove(indice);
            cantidades.remove(indice);
        }
    }

    public void modificarCantidad(int indice, int nuevaCantidad) {
        if (indice >= 0 && indice < cantidades.size()) {
            cantidades.set(indice, nuevaCantidad);
        }
    }

    public void mostrar() {
        System.out.println("Pedido ID: " + idPedido);
        double total = 0;
        for (int i = 0; i < articulos.size(); i++) {
            Articulo a = articulos.get(i);
            int cantidad = cantidades.get(i);
            double subtotal = a.getPrecio() * cantidad;
            System.out.println("- " + a.getNombre() + " | Cantidad: " + cantidad + " | Subtotal: $" + subtotal);
            total += subtotal;
        }
        System.out.println("Total del pedido: $" + total);
    }
}
