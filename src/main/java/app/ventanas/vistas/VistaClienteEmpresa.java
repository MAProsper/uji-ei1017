package app.ventanas.vistas;

import clientes.ClienteEmpresa;

// Relacion Vista-Controlador
public class VistaClienteEmpresa extends VistaCliente {

    // Vista (define la vista contreta)
    public VistaClienteEmpresa(final ClienteEmpresa cliente) {
        super(cliente);
    }
}
