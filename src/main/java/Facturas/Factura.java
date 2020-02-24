package Facturas;

import Tarifas.Tarifa;

public class Factura {

    String codigo;
    Tarifa tarifa;
    String fecha;
    String periodo;
    float importe;

    public Factura(String codigo, Tarifa tarifa, String fecha, String periodo, float importe) {
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.fecha = fecha;
        this.periodo = periodo;
        this.importe = importe;
    }
}
