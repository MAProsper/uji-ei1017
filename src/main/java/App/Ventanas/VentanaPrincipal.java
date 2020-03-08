package App.Ventanas;

import App.Textbox;

import static Helpers.ValidatorArguments.stringNotEmpty;

public class VentanaPrincipal extends Ventana {
    public VentanaPrincipal() {
        super("Menu principal", "Bienvenido a gestor de clientes AkiCode", false, Textbox.empty(), Button.values());
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final App.Button button) {
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
                ventana = new VentanaRangoBuscar("clientes");
                break;
            case BUSCAR_FACTURA_RANGO:
                ventana = new VentanaRangoBuscar("facturas");
                break;
            case BUSCAR_LLAMADAS_RANGO:
                ventana = new VentanaRangoBuscar("llamadas");
                break;
            case CERRAR:
                break;
        }

        return ventana;
    }

    enum Button implements App.Button {
        VER_CLIENTES("Ver clientes"),
        BUSCAR_CLIENTE_NIF("Buscar cliente (NIF)"),
        BUSCAR_CLIENTE_FACTURA("Buscar cliente (factura)"),
        BUSCAR_CLIENTE_RANGO("Buscar clientes (rango)"),
        BUSCAR_FACTURA_RANGO("Buscar facturas (rango)"),
        BUSCAR_LLAMADAS_RANGO("Buscar llamadas (rango)"),
        CERRAR("Cerrar");

        final String descripcion;

        Button(final String descripcion) {
            this.descripcion = stringNotEmpty("descripcion", descripcion);
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
