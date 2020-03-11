package clientes.generadores;

import clientes.ClienteEmpresa;

public class GeneradorClienteEmpresa extends GeneradorCliente {
    public final ClienteEmpresa nextClienteEmpresa() {
        return new ClienteEmpresa(nextNIF(), nextNombre(), nextDireccion(), nextCorreo(), nextFecha(), nextTarifa());
    }
}
