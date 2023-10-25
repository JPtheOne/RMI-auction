import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class SubastaControlador implements ActionListener {

    SubastaVista vista;
    SubastaModeloInterface modelo;

    public SubastaControlador(SubastaVista v, SubastaModeloInterface m) {
        vista = v;
        modelo = m;
    }

    public void actionPerformed(ActionEvent evento) {
        String usuario;
        String producto;
        float monto;

        if (evento.getActionCommand().equals("Salir")) {
            System.exit(1);
        } else if (evento.getActionCommand().equals("Conectar")) {
            usuario = vista.getUsuario();
            System.out.println("Registrando usuario: " + usuario);
            try {
                modelo.registraUsuario(usuario);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (evento.getActionCommand().equals("Poner a la venta")) {
            usuario = vista.getUsuario();
            producto = vista.getProducto();
            monto = vista.getPrecioInicial();
            System.out.println(usuario+ " esta haciendo oferta del producto: " + producto + " de precio inicial: " + monto);
            try {
                modelo.agregaProductoALaVenta(usuario, producto, monto);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (evento.getActionCommand().equals("Obtener lista")) {
            try {
                vista.reinicializaListaProductos();
                vista.agregaProducto(modelo.obtieneCatalogo());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (evento.getActionCommand().equals("Ofrecer")) {
            producto = vista.getProductoSeleccionado();
            monto = vista.getMontoOfrecido();
            usuario = vista.getUsuario();
            try {
                modelo.agregaOferta(usuario, producto, monto);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
