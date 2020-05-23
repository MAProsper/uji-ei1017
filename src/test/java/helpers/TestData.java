package helpers;

import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
import clientes.Cliente;
import helpers.generadores.GeneradorData;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestData {
    public static final int size = 10;
    public static final Path path = Paths.get("target/clientes.bin");

    protected static final GeneradorData generador = new GeneradorData();

    public static void main(final String[] args) {
        final Modelo modelo = new Modelo();
        modelo.setManejador(new Manejador());

        for (Cliente cliente : generador.nextClientes(size))
            modelo.addCliente(cliente);

        modelo.save(path);
    }
}