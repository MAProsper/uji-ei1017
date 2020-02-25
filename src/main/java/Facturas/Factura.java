package Facturas;

import Tarifas.Tarifa;

import java.util.Date;

import static Helpers.HelperArgument.numberNotNegative;

public class Factura {

    int codigo;
    Tarifa tarifa;
    Date fecha;
    String periodo;
    double importe;

    public Factura(int codigo, Tarifa tarifa, Date fecha, String periodo, double importe) {
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.fecha = fecha;
        this.periodo = periodo;
        this.importe = numberNotNegative("Importe", importe);
    }
}
