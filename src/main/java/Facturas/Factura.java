package Facturas;

import Tarifas.Tarifa;

public class Factura {

    int codigo;
    Tarifa tarifa;
    String fecha;
    String periodo;
    float importe;

    public Factura(int codigo, Tarifa tarifa, String fecha, String periodo, float importe) {
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.fecha = fecha;
        this.periodo = periodo;
        this.importe = importe;
    }
}
