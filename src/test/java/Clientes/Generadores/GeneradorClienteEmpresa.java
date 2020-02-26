package Clientes.Generadores;

import Clientes.ClienteEmpresa;

public class GeneradorClienteEmpresa extends GeneradorCliente {
    public ClienteEmpresa nextClienteEmpresa() {
        return new ClienteEmpresa(nextNombre(), nextNIF(), nextDireccion(), nextCorreo(), nextFecha(), nextTarifa());
    }
}
