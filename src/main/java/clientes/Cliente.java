package clientes;

import helpers.Cronologico;
import helpers.Direccion;
import helpers.Factura;
import helpers.Llamada;
import tarifas.Tarifa;

import java.util.*;

import static helpers.ValidatorArguments.*;

public class Cliente implements Cronologico {
    final String NIF;
    final String nombre;
    final Direccion direccion;
    final String correo;
    final Date fechaAlta;
    final List<Llamada> llamadas;
    Tarifa tarifa;
    final List<Factura> facturas;

    final static Set<String> NIFs = new HashSet<>();

    public Cliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final Date fechaAlta, final Tarifa tarifa) {
        validate("NIF del cliente repetido", !NIFs.contains(NIF));
        this.NIF = stringMatchesPattern("NIF", NIF, "\\d+[TRWAGMYFPDXBNJZSQVHLCKE]");
        this.nombre = stringNotEmpty("nombre", nombre);
        this.direccion = referenceNotNull("direccion", direccion);
        this.correo = stringMatchesPattern("correo", correo, "[\\w.]+@[\\w.]{2,}");
        this.fechaAlta = referenceNotNull("fechaAlta", fechaAlta);
        setTarifa(tarifa);
        llamadas = new LinkedList<>();
        facturas = new LinkedList<>();
        NIFs.add(NIF);
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

    public void addLlamada(final Llamada llamada) {
        llamadas.add(referenceNotNull("llamada", llamada));
    }

    final public List<Factura> getFacturas() {
        return Collections.unmodifiableList(facturas);
    }

    public void addFactura(final Factura factura) {
        facturas.add(referenceNotNull("factura", factura));
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
                ", llamadas=" + llamadas +
                ", facturas=" + facturas +
                '}';
    }
}