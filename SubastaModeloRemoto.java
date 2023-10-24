import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Vector;

public class SubastaModeloRemoto extends UnicastRemoteObject implements SubastaModeloInterface {

    private Hashtable<String, String> usuarios;
    private Hashtable<String, InformacionProducto> productos;
    private Hashtable<String, InformacionOferta> ofertas;

    public SubastaModeloRemoto() throws RemoteException {
        super();
        usuarios = new Hashtable<>();
        productos = new Hashtable<>();
        ofertas = new Hashtable<>();
    }

    public boolean registraUsuario(String nombre) {
        if (!usuarios.containsKey(nombre)) {
            System.out.println("Agregando un nuevo usuario: " + nombre);
            usuarios.put(nombre, nombre);
            return true;
        } else {
            return false;
        }
    }

    public boolean agregaProductoALaVenta(String vendedor, String producto, float precioInicial) {
        if (!productos.containsKey(producto)) {
            System.out.println(vendedor + " esta agregando un nuevo producto: " + producto + " de precio inicial: " + precioInicial);
            productos.put(producto, new InformacionProducto(vendedor, producto, precioInicial));
            return true;
        } else {
            return false;
        }
    }

    public boolean agregaOferta(String comprador, String producto, float monto) {
        if (productos.containsKey(producto)) {
            InformacionProducto infoProd = productos.get(producto);
            if (infoProd.actualizaPrecio(monto)) {
                System.out.println(comprador + " esta agregando una nueva oferta: " + monto);
                ofertas.put(producto + comprador, new InformacionOferta(comprador, producto, monto));
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Vector<InformacionProducto> obtieneCatalogo() {
        System.out.println("Catalogo solicitado");
        Vector<InformacionProducto> resultado = new Vector<>(productos.values());
        return resultado;
    }

}
