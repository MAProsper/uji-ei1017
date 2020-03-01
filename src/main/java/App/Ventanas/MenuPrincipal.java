package App.Ventanas;

import java.util.Arrays;
import java.util.Collections;

public class MenuPrincipal extends Ventana {
    public MenuPrincipal() {
        super(
                "Menu principal",
                "Bienvenido a gestor de clientes AkiCode",
                false,
                Collections.emptyList(),
                Arrays.asList("Ver clientes", "Ver facturas", "Buscar cliente", "Buscar factura", "Nuevo cliente", "Cerrar"));
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Ver clientes":
                ventana = new ListaClientes();
            case "Ver facturas":
                break;
            case "Buscar cliente":
                break;
            case "Buscar factura":
                break;
            case "Nuevo cliente":
                break;
            case "Cerrar":
                break;
        }

        return ventana;
    }
}
