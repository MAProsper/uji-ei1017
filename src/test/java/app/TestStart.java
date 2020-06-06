package app;

import app.generadores.GeneradorModelo;
import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
import clientes.Cliente;

public class TestStart {
    public static final int size = 10;
    protected static final GeneradorModelo generador = new GeneradorModelo();

    public static void main(final String[] args) {
        final Manejador manejador = new Manejador();
        final Modelo modelo = new Modelo();
        modelo.setManejador(manejador);

        for (Cliente cliente : generador.nextClientes(size))
            modelo.addCliente(cliente);

        manejador.vistaNext(manejador.connectMVC(modelo));
    }
}