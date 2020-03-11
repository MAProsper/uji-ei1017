package app.ventanas.claeses;

import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;

import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaPrincipal extends Ventana {
    public VentanaPrincipal() {
        super(
                "Menu principal",
                "Bienvenido a gestor de clientes AkiCode",
                false, Textbox.empty(), Button.values());
    }

    @Override
    protected void update() {
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case VER_CLIENTES:
                ventana = new VentanaClientes();
                break;
            case BUSCAR_CLIENTE_NIF:
                ventana = new VentanaClienteBuscar();
                break;
            case BUSCAR_CLIENTE_FACTURA:
                ventana = new VentanaFacturaBuscar();
                break;
            case BUSCAR_CLIENTE_RANGO:
                ventana = new VentanaRangoBuscar(VentanaRangoBuscar.Tipo.CLIENTES);
                break;
            case BUSCAR_FACTURA_RANGO:
                ventana = new VentanaRangoBuscar(VentanaRangoBuscar.Tipo.FACTURAS);
                break;
            case BUSCAR_LLAMADAS_RANGO:
                ventana = new VentanaRangoBuscar(VentanaRangoBuscar.Tipo.LLAMADAS);
                break;
            case CARGAR:
                ventana = new VentanaLoad();
                break;
            case GUARDAR:
                ventana = new VentanaSave();
                break;
            case CERRAR:
                break;
        }

        return ventana;
    }

    private enum Button implements app.ventanas.interfaces.Button {
        VER_CLIENTES("Ver clientes"),
        BUSCAR_CLIENTE_NIF("Buscar cliente (NIF)"),
        BUSCAR_CLIENTE_FACTURA("Buscar cliente (factura)"),
        BUSCAR_CLIENTE_RANGO("Buscar clientes (rango)"),
        BUSCAR_FACTURA_RANGO("Buscar facturas (rango)"),
        BUSCAR_LLAMADAS_RANGO("Buscar llamadas (rango)"),
        CARGAR("Cargar clientes"),
        GUARDAR("Guardar clientes"),
        CERRAR("Cerrar");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}