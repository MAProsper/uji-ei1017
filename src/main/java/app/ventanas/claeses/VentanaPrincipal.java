package app.ventanas.claeses;

import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

import java.util.Optional;

import static helpers.estaticos.Arguments.*;

public class VentanaPrincipal extends Ventana {
    public VentanaPrincipal() {
        super(
                "Menu principal",
                "Bienvenido a gestor de clientes AkiCode",
                Table.empty(), Textbox.empty(), Button.values());
    }

    @Override
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Gestionable ventana = null;

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
            default:
                throw new ValidationException("Button no clasificado");
        }

        return Optional.ofNullable(ventana);
    }

    public enum Button implements app.ventanas.interfaces.Button {
        VER_CLIENTES("Ver clientes"),
        BUSCAR_CLIENTE_NIF("Buscar cliente (NIF)"),
        BUSCAR_CLIENTE_FACTURA("Buscar cliente (factura)"),
        BUSCAR_CLIENTE_RANGO("Buscar clientes (rango)"),
        BUSCAR_FACTURA_RANGO("Buscar facturas (rango)"),
        BUSCAR_LLAMADAS_RANGO("Buscar llamadas (rango)"),
        CARGAR("Cargar clientes"),
        GUARDAR("Guardar clientes"),
        CERRAR("Cerrar");

        private final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
