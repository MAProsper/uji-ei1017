package app;

import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.clases.Llamada;

import java.time.LocalDate;

import static helpers.estaticos.Arguments.referenceNotNull;

final public class Formato {
    public static String llamda(final Llamada llamada) {
        referenceNotNull("llamada", llamada);
        return String.format("[%s] %s (%s min)", llamada.getFecha(), llamada.getTelefono(), llamada.getDuracion());
    }

    public static String factura(final Factura factura) {
        referenceNotNull("factura", factura);
        return String.format("[%s-%s] [%s] %s (%s euros/min)", factura.getCodigo(), factura.getFecha(), periodo(factura.getPeriodo()), factura.getImporte(), factura.getTarifa().getPrecio());
    }

    public static String cliente(final Cliente cliente) {
        referenceNotNull("cliente", cliente);
        String tipo = "Otros";
        String nombre = cliente.getNombre();

        if (cliente instanceof ClientePaticular) {
            final ClientePaticular particular = (ClientePaticular) cliente;
            tipo = "Particular";
            nombre = String.format("%s, %s", particular.getApellidos(), particular.getNombre());
        } else if (cliente instanceof ClienteEmpresa) {
            final ClienteEmpresa empresa = (ClienteEmpresa) cliente;
            tipo = "Empresa";
        }

        return String.format("[%s] [%s] %s", cliente.getNIF(), tipo, nombre);
    }

    public static String periodo(final Range<LocalDate> periodo) {
        return String.format("%s-%s", periodo.lowerEndpoint(), periodo.upperEndpoint());
    }
}
