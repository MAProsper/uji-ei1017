package app.generadores;

import clientes.Cliente;
import clientes.generadores.GeneradorCliente;
import clientes.generadores.GeneradorClienteEmpresa;
import clientes.generadores.GeneradorClienteParticular;
import helpers.clases.Llamada;
import tarifas.Tarifa;
import tarifas.generadores.GeneradorTarifaBase;
import tarifas.generadores.GeneradorTarifaDomingo;
import tarifas.generadores.GeneradorTarifaTarde;

import java.util.*;

import static helpers.estaticos.Arguments.ValidationException;

public class GeneradorModelo {
    protected static final Random genBase = new Random();
    protected static final GeneradorCliente[] genClientes = {
            new GeneradorCliente(), new GeneradorClienteParticular(), new GeneradorClienteEmpresa()
    };
    protected static final GeneradorTarifaBase[] genTarifas = {
            new GeneradorTarifaBase(), new GeneradorTarifaDomingo(), new GeneradorTarifaTarde()
    };

    private <T> T getRandom(final T[] array) {
        return array[genBase.nextInt(array.length)];
    }

    protected Cliente gestionarGenerador(final GeneradorCliente generador) {
        if (generador instanceof GeneradorClienteParticular)
            return ((GeneradorClienteParticular) generador).nextClienteParticular();
        else if (generador instanceof GeneradorClienteEmpresa)
            return ((GeneradorClienteEmpresa) generador).nextClienteEmpresa();
        else return generador.nextCliente();
    }

    protected Tarifa gestionarGenerador(final GeneradorTarifaBase generador) {
        if (generador instanceof GeneradorTarifaDomingo)
            return ((GeneradorTarifaDomingo) generador).nextTarifaDomingo();
        else if (generador instanceof GeneradorTarifaTarde)
            return ((GeneradorTarifaTarde) generador).nextTarifaTarde();
        else return generador.nextTarifa();
    }

    public Cliente nextCliente(final int maxSize) {
        final GeneradorCliente genCliente = getRandom(genClientes);
        final GeneradorTarifaBase genTarifa = getRandom(genTarifas);

        final Cliente cliente = gestionarGenerador(genCliente);
        cliente.setTarifa(gestionarGenerador(genTarifa));

        // Generar sin llamadas repetidas
        final Set<Llamada> llamadas = new HashSet<>();
        for (int i = 0; i < maxSize; i++) llamadas.add(genCliente.nextLlamada());
        for (Llamada llamada : llamadas) cliente.addLlamada(llamada);

        // Garantizado sin repetidos
        for (int i = 0; i < maxSize; i++) cliente.addFactura(genCliente.nextFactura());

        return cliente;
    }

    public List<Cliente> nextClientes(final int maxSize) {
        final List<Cliente> clientes = new LinkedList<>();

        for (int i = 0; i < maxSize; i++) {
            final Cliente cliente;

            try {
                cliente = nextCliente(maxSize);
            } catch (ValidationException ignored) {
                // Podria fallar por NIF repetido
                continue;
            }

            clientes.add(cliente);
        }

        return clientes;
    }
}
