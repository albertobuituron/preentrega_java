import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Articulo> listaArticulos = new ArrayList<>();
    static ArrayList<Pedido> listaPedidos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de Artículos");
            System.out.println("2. Gestión de Pedidos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuArticulos();
                case 2 -> menuPedidos();
                case 3 -> System.out.println("Muchas gracias por utilizar la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 3);
    }

    // ===== SUBMENÚ ARTÍCULOS =====
    public static void menuArticulos() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Artículos ---");
            System.out.println("1. Crear un nuevo artículo");
            System.out.println("2. Listado de artículos");
            System.out.println("3. Modificar un artículo por su ID");
            System.out.println("4. Eliminar un artículo por su ID");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> listarArticulos();
                case 3 -> modificarArticulo();
                case 4 -> eliminarArticulo();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }

    // ===== SUBMENÚ PEDIDOS =====
    public static void menuPedidos() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Pedidos ---");
            System.out.println("1. Crear un pedido");
            System.out.println("2. Listar pedidos creados");
            System.out.println("3. Modificar un pedido por su ID");
            System.out.println("4. Eliminar un pedido por su ID");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearPedido();
                case 2 -> listarPedidos();
                case 3 -> modificarPedido();
                case 4 -> eliminarPedido();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }

    // ===== FUNCIONES ARTÍCULOS =====
    public static void crearArticulo() {
        int id;
        do {
            System.out.print("ID: ");
            id = leerEntero();
            if (buscarArticuloPorId(id) != null) {
                System.out.println("El ID Nro " + id + " ya existe. Intente con otro.");
            } else {
                break;
            }
        } while (true);

        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = leerTexto();
            if (nombre.isEmpty()) {
                System.out.println("Nombre no válido. Ingrese nuevamente.");
            } else {
                break;
            }
        } while (true);

        System.out.print("Precio: ");
        double precio = leerDoublePositivo();

        Articulo nuevo = new Articulo(id, nombre, precio);
        listaArticulos.add(nuevo);
        System.out.println("el Artículo con ID " + id + " fue agregado.");
    }

    public static void listarArticulos() {
        if (listaArticulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
        } else {
            for (Articulo a : listaArticulos) {
                a.mostrar();
            }
        }
    }

    public static void modificarArticulo() {
        System.out.print("Ingrese el ID del artículo a modificar: ");
        int id = leerEntero();
        Articulo articulo = buscarArticuloPorId(id);
        if (articulo != null) {
            String nombre;
            do {
                System.out.print("ingrese Nuevo Nombre para el artículo: ()" + articulo.getNombre() + "): ");
                nombre = leerTexto();
                if (nombre.isEmpty()) {
                    System.out.println("El Nombre no puede quedar vacío, ingrese nuevamente.");
                } else {
                    articulo.setNombre(nombre);
                    break;
                }
            } while (true);

            System.out.print("Ingrese Nuevo precio para el artículo con precio anterior ($" + articulo.getPrecio() + "): ");
            double precio = leerDoublePositivo();
            articulo.setPrecio(precio);

            System.out.println("El Artículo con ID " + id + " fue actualizado.");
        } else {
            System.out.println("El Artículo con ID " + id + " no fue encontrado.");
        }
    }

    public static void eliminarArticulo() {
        System.out.print("Ingrese elID del artículo a eliminar: ");
        int id = leerEntero();
        Articulo articulo = buscarArticuloPorId(id);
        if (articulo != null) {
            listaArticulos.remove(articulo);
            System.out.println("El artículo con ID " + id + " fue eliminado.");
        } else {
            System.out.println("Artículo con ID " + id + " no existe.");
        }
    }

    public static Articulo buscarArticuloPorId(int id) {
        for (Articulo a : listaArticulos) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    // ===== FUNCIONES PEDIDOS =====
    public static void crearPedido() {
        System.out.print("Ingrese el ID del nuevo pedido: ");
        int id = leerEntero();
        if (buscarPedidoPorId(id) != null) {
            System.out.println("El ID Nro " + id + " del pedido ya existe.");
            return;
        }

        Pedido pedido = new Pedido(id);

        char seguir;
        do {
            listarArticulos();
            System.out.print("Ingrese el ID del artículo a agregar: ");
            int idArticulo = leerEntero();
            Articulo articulo = buscarArticuloPorId(idArticulo);

            if (articulo != null) {
                System.out.print("Ingrese la Cantidad: ");
                int cantidad = leerEnteroPositivo();
                pedido.agregarArticulo(articulo, cantidad);
                System.out.println("El Artículo conmo ID " + idArticulo + " fue agregado al pedido.");
            } else {
                System.out.println("Artículo no encontrado.");
            }

            System.out.print("¿Desea agregar otro artículo? (s/n): ");
            seguir = sc.nextLine().toLowerCase().charAt(0);
        } while (seguir == 's');

        listaPedidos.add(pedido);
        System.out.println("El Pedido fue creado exitosamente.");
    }

    public static void listarPedidos() {
        if (listaPedidos.isEmpty()) {
            System.out.println("No hay pedidos cargados.");
        } else {
            for (Pedido p : listaPedidos) {
                p.mostrar();
            }
        }
    }

    public static void modificarPedido() {
        System.out.print("ID del pedido a modificar: ");
        int id = leerEntero();
        Pedido pedido = buscarPedidoPorId(id);

        if (pedido != null) {
            pedido.mostrar();
            System.out.println("1. Agregar artículo");
            System.out.println("2. Eliminar artículo");
            System.out.println("3. Cambiar cantidad");
            System.out.print("Seleccione una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> {
                    listarArticulos();
                    System.out.print("ID del artículo a agregar: ");
                    int idArticulo = leerEntero();
                    Articulo articulo = buscarArticuloPorId(idArticulo);
                    if (articulo != null) {
                        System.out.print("Cantidad: ");
                        int cantidad = leerEnteroPositivo();
                        pedido.agregarArticulo(articulo, cantidad);
                        System.out.println("Artículo agregado.");
                    } else {
                        System.out.println("Artículo no encontrado.");
                    }
                }
                case 2 -> {
                    if (pedido.getArticulos().isEmpty()) {
                        System.out.println("El pedido no tiene artículos para eliminar.");
                    } else {
                        for (int i = 0; i < pedido.getArticulos().size(); i++) {
                            System.out.println(i + ": " + pedido.getArticulos().get(i).getNombre());
                        }
                        System.out.print("Índice del artículo a eliminar: ");
                        int indice = leerEntero();
                        if (indice >= 0 && indice < pedido.getArticulos().size()) {
                            pedido.eliminarArticuloPorIndice(indice);
                            System.out.println("Artículo eliminado del pedido.");
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }
                }
                case 3 -> {
                    if (pedido.getCantidades().isEmpty()) {
                        System.out.println("El pedido no tiene artículos.");
                    } else {
                        for (int i = 0; i < pedido.getArticulos().size(); i++) {
                            System.out.println(i + ": " + pedido.getArticulos().get(i).getNombre() + " - Cantidad actual: " + pedido.getCantidades().get(i));
                        }
                        System.out.print("Índice del artículo para modificar cantidad: ");
                        int indice = leerEntero();
                        if (indice >= 0 && indice < pedido.getCantidades().size()) {
                            System.out.print("Nueva cantidad: ");
                            int nuevaCantidad = leerEnteroPositivo();
                            pedido.modificarCantidad(indice, nuevaCantidad);
                            System.out.println("Cantidad actualizada.");
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }
                }
                default -> System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }

    public static void eliminarPedido() {
        System.out.print("ID del pedido a eliminar: ");
        int id = leerEntero();
        Pedido pedido = buscarPedidoPorId(id);
        if (pedido != null) {
            listaPedidos.remove(pedido);
            System.out.println("El pedido con ID " + id + " fue eliminado.");
        } else {
            System.out.println("Pedido con ID " + id + " no existe.");
        }
    }

    public static Pedido buscarPedidoPorId(int id) {
        for (Pedido p : listaPedidos) {
            if (p.getIdPedido() == id) return p;
        }
        return null;
    }

    // ===== MÉTODOS DE LECTURA SEGURA =====

    public static int leerEntero() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Entrada vacía. Ingrese nuevamente: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número entero: ");
            }
        }
    }

    public static int leerEnteroPositivo() {
        int numero;
        do {
            numero = leerEntero();
            if (numero <= 0) {
                System.out.print("Debe ser un número entero positivo. Ingrese nuevamente: ");
            }
        } while (numero <= 0);
        return numero;
    }

    public static double leerDoublePositivo() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Entrada vacía. Ingrese nuevamente: ");
                    continue;
                }
                double val = Double.parseDouble(input);
                if (val <= 0) {
                    System.out.print("Debe ser un número positivo. Ingrese nuevamente: ");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número decimal válido: ");
            }
        }
    }

    public static String leerTexto() {
        String texto;
        do {
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.print("Entrada vacía. Ingrese nuevamente: ");
            }
        } while (texto.isEmpty());
        return texto;
    }
}