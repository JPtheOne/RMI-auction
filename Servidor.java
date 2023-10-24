import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {

    public static void main(String[] args) {
        try {
            SubastaModeloRemoto modelo = new SubastaModeloRemoto();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/SubastaModelo", modelo);

            System.out.println("Servidor listo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
