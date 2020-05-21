package app.componentes.textboxes;

import app.componentes.Textbox;

import static helpers.estaticos.Arguments.stringNotEmpty;


public enum TextboxClienteParticularNuevo implements Textbox {
    NIF("NIF"),
    NOMBRE("Nombre"),
    APELLIDOS("Apellidos"),
    CODIGO_POSTAL("Codigo postal"),
    PROVINCIA("Provincia"),
    POBLACION("Poblacion"),
    CORREO("Correo electronico"),
    FECHA_ALTA("Fecha de alta"),
    TARIFA("Tarifa");

    private final String description;

    TextboxClienteParticularNuevo(final String description) {
        this.description = stringNotEmpty("Descripcion", description);
    }

    public String getDescription() {
        return description;
    }
}

