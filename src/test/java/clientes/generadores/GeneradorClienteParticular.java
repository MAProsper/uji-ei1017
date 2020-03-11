package clientes.generadores;

import clientes.ClientePaticular;

public class GeneradorClienteParticular extends GeneradorCliente {
    public final String nextApellidos() {
        return genINE.getApellido() + " " + genINE.getApellido();
    }

    public final ClientePaticular nextClienteParticular() {
        return new ClientePaticular(nextNIF(), nextNombre(), nextApellidos(), nextDireccion(), nextCorreo(), nextFecha(), nextTarifa());
    }
}
