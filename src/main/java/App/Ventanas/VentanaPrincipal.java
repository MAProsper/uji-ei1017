package App.Ventanas;

import java.util.Arrays;
import java.util.Collections;

public class VentanaPrincipal extends Ventana {
    public VentanaPrincipal() {
        super(
                "Menu principal",
                "Bienvenido a gestor de clientes AkiCode",
                false,
                Collections.emptyList(),
                Arrays.asList("Ver clientes", "Buscar cliente (NIF)", "Buscar cliente (factura)", "Cerrar"));
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Ver clientes":
                ventana = new VentanaClientes();
                break;
            case "Buscar cliente (NIF)":
                ventana = new VentanaClienteBuscar();
                break;
            case "Buscar cliente (factura)":
                ventana = new VentanaFacturaBuscar();
                break;
            case "Cerrar":
                break;
        }

        return ventana;
    }
}
