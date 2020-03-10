package app.ventanas;

import app.Textbox;
import clientes.ClientePaticular;
import helpers.Direccion;

import java.util.Arrays;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaClienteParticular extends Ventana {
    public VentanaClienteParticular() {
        super(
                "Cliente (particular)",
                "Gestion del cliente",
                true, Textbox.empty(), Button.values());
    }

    @Override
    public void update() {
        final ClientePaticular cliente = (ClientePaticular) getGestor().getClienteSelecionado();
        final Direccion direccion = cliente.getDireccion();

        setList(Arrays.asList(
                "NIF: " + cliente.getNIF(),
                "Nombre: " + cliente.getNombre(),
                "Apellidos: " + cliente.getApellidos(),
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

        switch ((VentanaClienteEmpresa.Button) button) {
            case VER_LLAMADAS:
                ventana = new VentanaLlamadas();
                break;
            case VAR_FACTURAS:
                ventana = new VentanaFacturas();
                break;
            case BORRAR_CLIENTE:
                getGestor().removeCliente();
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    enum Button implements app.Button {
        VER_LLAMADAS("Ver llamadas"),
        VAR_FACTURAS("Ver facturas"),
        BORRAR_CLIENTE("Borrar cliente"),
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
