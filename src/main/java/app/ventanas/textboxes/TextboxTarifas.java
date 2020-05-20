package app.ventanas.textboxes;

import app.ventanas.interfaces.Textbox;

import static helpers.estaticos.Arguments.stringNotEmpty;

public enum TextboxTarifas implements Textbox {
    PRECIO("Precio");

    private final String desciption;

    TextboxTarifas(String desciption) {
        this.desciption = stringNotEmpty("Desciption", desciption);
    }

    @Override
    public String getDescription() {
        return desciption;
    }
}