package app.ventanas;

import app.Gestor;
import app.Textbox;
import clientes.Cliente;
import helpers.Direccion;

import java.util.Arrays;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaCliente extends Ventana {
    public VentanaCliente() {
        super(
                "Cliente (otros)",
                "Gestion del cliente",
                true, Textbox.empty(), Button.values());
    }

    @Override
    public void update() {
        final Cliente cliente = getGestor().getClienteSelecionado();
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
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch ((Button) button) {
            case VER_LLAMADAS:
                ventana = new VentanaLlamadas();
                break;
            case VER_FACTURAS:
                ventana = new VentanaFacturas();
                break;
            case BORRAR_CLIENTE:
                gestor.removeCliente();
                break;
            case VOLVER:
                gestor.setClienteSelecionado(null);
                break;
        }

        return ventana;
    }

    enum Button implements app.Button {
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
