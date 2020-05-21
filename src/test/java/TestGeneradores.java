import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
import clientes.Cliente;
import clientes.generadores.GeneradorCliente;
import clientes.generadores.GeneradorClienteEmpresa;
import clientes.generadores.GeneradorClienteParticular;
import org.junit.jupiter.api.Test;
import tarifas.Tarifa;
import tarifas.generadores.GeneradorTarifaBase;
import tarifas.generadores.GeneradorTarifaDomingo;
import tarifas.generadores.GeneradorTarifaTarde;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class TestGeneradores {
    protected static final int size = 10;
    protected static final Path path = Paths.get("target/clientes.bin");

    protected static final Random genBase = new Random();
    protected static final GeneradorCliente[] genClientes = {
            new GeneradorCliente(), new GeneradorClienteParticular(), new GeneradorClienteEmpresa()
    };
    protected static final GeneradorTarifaBase[] genTarifas = {
            new GeneradorTarifaBase(), new GeneradorTarifaDomingo(), new GeneradorTarifaTarde()
    };

    @Test
    public void testGeneradores() {
        final Modelo modelo = new Modelo();
        modelo.setManejador(new Manejador());

        for (int i = 0; i < size; i++)
            modelo.addCliente(nextCliente(getRandom(genClientes), getRandom(genTarifas)));

        modelo.save(path);
    }

    private <T> T getRandom(final T[] array) {
        return array[genBase.nextInt(array.length)];
    }

    private Cliente gestionarGenerador(final GeneradorCliente generador) {
        if (generador instanceof GeneradorClienteParticular)
            return ((GeneradorClienteParticular) generador).nextClienteParticular();
        else if (generador instanceof GeneradorClienteEmpresa)
            return ((GeneradorClienteEmpresa) generador).nextClienteEmpresa();
        else return generador.nextCliente();
    }

    private Tarifa gestionarGenerador(final GeneradorTarifaBase generador) {
        if (generador instanceof GeneradorTarifaDomingo)
            return ((GeneradorTarifaDomingo) generador).nextTarifaDomingo();
        else if (generador instanceof GeneradorTarifaTarde)
            return ((GeneradorTarifaTarde) generador).nextTarifaTarde();
        else return generador.nextTarifa();
    }

    protected Cliente nextCliente(final GeneradorCliente genCliente, final GeneradorTarifaBase genTarifa) {
        final Cliente cliente = gestionarGenerador(genCliente);
        cliente.setTarifa(gestionarGenerador(genTarifa));
        for (int i = 0; i < size; i++) cliente.addLlamada(genCliente.nextLlamada());
        for (int i = 0; i < size; i++) cliente.addFactura(genCliente.nextFactura());
        return cliente;
    }
}
