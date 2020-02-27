package Clientes;

import java.util.*;

import Facturas.Factura;
import Llamadas.Llamada;
import Tarifas.Tarifa;
import Direcciones.Direccion;

import static Helpers.HelperArgument.*;

public class Cliente {
    final String nombre;
    final String NIF;
    final Direccion direccion;
    final String correo;
    final Date fechaAlta;
    Tarifa tarifa;
    final List<Llamada> llamadas;
    final List<Factura> facturas;

    public Cliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final Date fechaAlta, final Tarifa tarifa) {
        this.NIF = stringMatchesPattern("NIF", NIF, "\\d+[TRWAGMYFPDXBNJZSQVHLCKE]");
        this.nombre = stringNotEmpty("nombre", nombre);
        this.direccion = referenceNotNull("direccion", direccion);
        this.correo = stringMatchesPattern("correo", correo, "[\\w.]+@[\\w.]{2,}");
        this.fechaAlta = referenceNotNull("fechaAlta", fechaAlta);
        setTarifa(tarifa);
        llamadas = new LinkedList<>();
        facturas = new LinkedList<>();
    }

    final public String getNombre() {
        return nombre;
    }

    final public String getNIF() {
        return NIF;
    }

    final public Direccion getDireccion() {
        return direccion;
    }

    final public String getCorreo() {
        return correo;
    }

    final public Date getFecha() {
        return fechaAlta;
    }

    final public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(final Tarifa tarifa) {
        this.tarifa = referenceNotNull("tarifa", tarifa);
    }

    final public List<Llamada> getLlamadas() {
        return Collections.unmodifiableList(llamadas);
    }

    public void addLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }

    final public List<Factura> getFacturas() {
        return Collections.unmodifiableList(facturas);
    }

    public void addFactura(Factura factura) {
        facturas.add(factura);
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(NIF, cliente.NIF);
    }

    @Override
    final public int hashCode() {
        return Objects.hash(NIF);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", tarifa=" + tarifa +
                '}';
    }
}
