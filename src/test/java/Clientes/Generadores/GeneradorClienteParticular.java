package Clientes.Generadores;

import Clientes.ClientePaticular;

public class GeneradorClienteParticular extends GeneradorCliente {
    public String nextApellidos() {
        return genINE.getApellido() + " " + genINE.getApellido();
    }

    public ClientePaticular nextClienteParticular() {
        return new ClientePaticular(nextNIF(), nextNombre(), nextApellidos(), nextDireccion(), nextCorreo(), nextFecha(), nextServicio());
    }
}
