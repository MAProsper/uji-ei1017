package tarifas;

import helpers.clases.Llamada;

import static helpers.estaticos.Arguments.referenceNotNull;

public class TarifaBase extends Tarifa {
    private static final long serialVersionUID = 6603525423030174214L;

    public TarifaBase(double precio) {
        super(precio);
    }

    public double getImporte(final Llamada llamada) {
        referenceNotNull("Llamada", llamada);
        return llamada.getDuracion() * precio;
    }

    @Override
    public String toString() {
        return "TarifaBase{" +
                "precio=" + precio +
                '}';
    }
}
