package Clientes;

import Clientes.Generadores.GeneradorClienteEmpresa;
import Direcciones.Direccion;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;

public class TestClienteEmpresa extends TestCliente {
    static final GeneradorClienteEmpresa genClienteEmpresa = new GeneradorClienteEmpresa();

    @RepeatedTest(5)
    public void testClienteEmpresa() {
        final String nombre = genClienteEmpresa.nextNombre();
        final String NIF = genClienteEmpresa.nextNIF();
        final Direccion direccion = genClienteEmpresa.nextDireccion();
        final String correo = genClienteEmpresa.nextCorreo();
        final Date fechaAlta = genClienteEmpresa.nextFecha();
        final Tarifa tarifa = genClienteEmpresa.nextTarifa();
        System.out.println("ClienteEmpresa{" +
                "nombre='" + nombre + '\'' +
                ", NIF='" + NIF + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}');
        final ClienteEmpresa clienteEmpresa = new ClienteEmpresa(nombre, NIF, direccion, correo, fechaAlta, tarifa);
    }
}
