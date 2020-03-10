package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;
import clientes.Cliente;
import helpers.clases.Direccion;

import java.util.Arrays;

import static helpers.estaticos.ValidatorArguments.referenceNotNull;
import static helpers.estaticos.ValidatorArguments.stringNotEmpty;

public class VentanaCliente extends Ventana {
    private final Cliente cliente;

    public VentanaCliente(final Cliente cliente) {
        super(
                "Cliente",
                "Gestion del cliente",
                true, Textbox.empty(), Button.values());

        this.cliente = referenceNotNull("cliente", cliente);
    }

    @Override
    protected void update() {
        final Direccion direccion = cliente.getDireccion();

        setList(Arrays.asList(
                "NIF: " + cliente.getNIF(),
                "Nombre: " + cliente.getNombre(),
                "Codigo postal: " + direccion.getCodigoPostal(),
                "Provincia: " + direccion.getProvincia(),
                "Poblacion: " + direccion.getPoblacion(),
                "Correo electronico : " + cliente.getCorreo(),
                "Fecha de alta: " + cliente.getFecha(),
                "Tarifa base: " + cliente.getTarifa().getPrecio()
        ));
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch ((Button) button) {
            case VER_LLAMADAS:
                ventana = new VentanaLlamadas(cliente);
                break;
            case VER_FACTURAS:
                ventana = new VentanaFacturas(cliente);
                break;
            case BORRAR_CLIENTE:
                gestor.removeCliente(cliente);
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private enum Button implements app.ventanas.interfaces.Button {
        VER_LLAMADAS("Ver llamadas"),
        VER_FACTURAS("Ver facturas"),
        BORRAR_CLIENTE("Buscar cliente"),
        VOLVER("Volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
