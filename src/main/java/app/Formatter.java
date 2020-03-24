package app;

import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.clases.Llamada;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static helpers.estaticos.Arguments.referenceNotNull;

final public class Formatter {
    protected final static DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String format(final Llamada llamada) {
        referenceNotNull("Llamada", llamada);
        return String.format("[%s] %s (%s min)", format(llamada.getFecha()), llamada.getTelefono(), llamada.getDuracion());
    }

    public static String format(final Factura factura) {
        referenceNotNull("Factura", factura);
        return String.format("[%s-%s] [%s] %s (%s euros/min)", factura.getCodigo(), format(factura.getFecha()), format(factura.getPeriodo()), factura.getImporte(), factura.getTarifa().getPrecio());
    }

    public static String format(final Cliente cliente) {
        referenceNotNull("Cliente", cliente);
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

    public static String format(final Range<LocalDateTime> periodo) {
        referenceNotNull("Periodo", periodo);
        return String.format("%s-%s", format(periodo.lowerEndpoint()), format(periodo.upperEndpoint()));
    }

    public static String format(final LocalDateTime fecha) {
        return referenceNotNull("Fecha", fecha).format(fechaFormat);
    }
}
