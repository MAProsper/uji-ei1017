package app.ventanas.abstractas;

import app.ventanas.acciones.AccionNuevo;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

public abstract class VistaNuevo extends VistaPropia {
    public VistaNuevo(final Textbox[] textboxes) {
        super(
                "Nuevo",
                "Rellena los attributos de la nueva entrada",
                Table.empty(), textboxes, AccionNuevo.values());
    }
}
