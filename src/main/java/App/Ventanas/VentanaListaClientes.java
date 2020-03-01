package App.Ventanas;

import java.util.Arrays;
import java.util.Collections;

public class VentanaListaClientes extends Ventana {
    public VentanaListaClientes() {
        super(
                "Clientes",
                "Listado de clientes",
                true,
                Collections.singletonList("NIF"),
                Arrays.asList("Selecionar", "Volver"));
    }

    @Override
    public void update() {
        setList(Arrays.asList("CLIENTE1", "CLIENTE 2"));
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Ver clientes":
                ventana = new VentanaListaClientes();
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
