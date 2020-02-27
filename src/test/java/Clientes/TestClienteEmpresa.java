package Clientes;

import Clientes.Generadores.GeneradorClienteEmpresa;
import Helpers.Direccion;
import Tarifas.Tarifa;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Date;

public class TestClienteEmpresa extends TestCliente {
    static final GeneradorClienteEmpresa genClienteEmpresa = new GeneradorClienteEmpresa();

    @RepeatedTest(3)
    public void testClienteEmpresa() {
        final String NIF = genClienteEmpresa.nextNIF();
        final String nombre = genClienteEmpresa.nextNombre();
        final Direccion direccion = genClienteEmpresa.nextDireccion();
        final String correo = genClienteEmpresa.nextCorreo();
        final Date fechaAlta = genClienteEmpresa.nextFecha();
        final Tarifa tarifa = genClienteEmpresa.nextTarifa();
        System.out.println("ClienteEmpresa{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}');
        final ClienteEmpresa clienteEmpresa = new ClienteEmpresa(NIF, nombre, direccion, correo, fechaAlta, tarifa);
    }
}
