package app;

import app.generadores.GeneradorModelo;
import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
import app.ventanas.vistas.abstractas.Vista;
import clientes.Cliente;

public class TestStart {
    public static final int size = 10;
    protected static final GeneradorModelo generador = new GeneradorModelo();

    public static void main(final String[] args) {
        final Modelo modelo = new Modelo();
        final Manejador manejador = new Manejador();
        final Vista vista = manejador.connectMVC(modelo);

        for (Cliente cliente : generador.nextClientes(size))
            modelo.addCliente(cliente);

        manejador.vistaNext(vista);
    }
}