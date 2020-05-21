package app.ventanas.vistas.abstractas;

import app.componentes.Table;
import app.componentes.Textbox;
import app.componentes.acciones.AccionNuevo;

public abstract class VistaNuevo extends VistaPropia {
    public VistaNuevo(final Textbox[] textboxes) {
        super(
                "Nuevo",
                "Rellena los attributos de la nueva entrada",
                Table.empty(), textboxes, AccionNuevo.values());
    }
}
