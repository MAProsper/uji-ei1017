package Clientes.Generadores;

import Clientes.ClienteEmpresa;

public class GeneradorClienteEmpresa extends GeneradorCliente {
    public ClienteEmpresa nextClienteEmpresa() {
        return new ClienteEmpresa(nextNIF(), nextNombre(), nextDireccion(), nextCorreo(), nextFecha(), nextServicio());
    }
}
