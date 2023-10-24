import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
public class Cliente {

    public static void main(String[] args) {
        try {
            SubastaVista vista = new SubastaVista();
            SubastaModeloInterface modelo = (SubastaModeloInterface) Naming.lookup("rmi://localhost/SubastaModelo");
            SubastaControlador controlador = new SubastaControlador(vista, modelo);

            vista.asignarActionListener(controlador);

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
