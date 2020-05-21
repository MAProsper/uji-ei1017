package app.helpers.estaticos;

import clientes.Cliente;
import clientes.ClienteEmpresa;
import clientes.ClientePaticular;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.clases.Llamada;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static helpers.estaticos.Arguments.referenceNotNull;

final public class Formatter {
    private Formatter() {
    }

    protected final static DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static List<String> format(final Llamada llamada) {
        referenceNotNull("Llamada", llamada);
        return Arrays.asList(
                format(llamada.getFecha()),
                llamada.getTelefono(),
                Double.toString(llamada.getDuracion())
        );
    }

    public static List<String> format(final Factura factura) {
        referenceNotNull("Factura", factura);
        return Arrays.asList(Integer.toString(
                factura.getCodigo()),
                format(factura.getFecha()),
                format(factura.getPeriodo()),
                Double.toString(factura.getImporte()),
                Double.toString(factura.getTarifa().getPrecio())
        );
    }

    public static List<String> format(final Cliente cliente) {
        referenceNotNull("Cliente", cliente);
        String tipo = "Otros";
        String nombre = cliente.getNombre();

        if (cliente instanceof ClientePaticular) {
            final ClientePaticular particular = (ClientePaticular) cliente;
            tipo = "Particular";
            nombre = String.format("%s, %s", particular.getApellidos(), particular.getNombre());
        } else if (cliente instanceof ClienteEmpresa) {
            tipo = "Empresa";
        }

        return Arrays.asList(cliente.getNIF(), tipo, nombre);
    }

    public static String format(final Range<LocalDateTime> periodo) {
        referenceNotNull("Periodo", periodo);
        return String.format("%s-%s", format(periodo.lowerEndpoint()), format(periodo.upperEndpoint()));
    }

    public static String format(final LocalDateTime fecha) {
        return referenceNotNull("Fecha", fecha).format(fechaFormat);
    }
}
