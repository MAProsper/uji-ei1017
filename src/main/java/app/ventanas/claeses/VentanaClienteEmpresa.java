package app.ventanas.claeses;

import clientes.ClienteEmpresa;
// Relacion Vista-Controlador
public class VentanaClienteEmpresa extends VentanaCliente {

    // Vista (define la vista contreta)
    public VentanaClienteEmpresa(final ClienteEmpresa cliente) {
        super(cliente);
    }
}
