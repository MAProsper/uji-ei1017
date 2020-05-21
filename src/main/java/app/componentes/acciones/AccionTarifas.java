package app.componentes.acciones;

import app.componentes.Accion;
import app.helpers.interfaces.Factory;
import app.helpers.interfaces.FactoryTarifas;
import tarifas.Tarifa;
import tarifas.TarifaDomingo;
import tarifas.TarifaExtra;
import tarifas.TarifaTarde;

import java.lang.reflect.InvocationTargetException;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public enum AccionTarifas implements Accion, FactoryTarifas {
    DOMINGO("Domingo", TarifaDomingo.class),
    TARDES("Tardes", TarifaTarde.class),
    VOLVER("Volver");

    private final String desciption;
    private final Class<? extends TarifaExtra> clase;

    AccionTarifas(final String desciption, final Class<? extends TarifaExtra> clase) {
        this.desciption = stringNotEmpty("Desciption", desciption);
        this.clase = clase;
    }

    AccionTarifas(final String desciption) {
        this(desciption, null);
    }

    @Override
    public String getDescription() {
        return desciption;
    }

    // Metodos para la fabrica de tarifas
    @Override
    public TarifaExtra getTarifa(final Tarifa tarifa, final double precio) {
        final Class<? extends TarifaExtra> clase = referenceNotNull("Clase", this.clase);
        try {
            return clase.getConstructor(Tarifa.class, Double.TYPE).newInstance(tarifa, precio);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            throw Factory.error(clase.getName());
        }
    }
}
