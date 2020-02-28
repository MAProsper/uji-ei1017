package Clientes;

import Helpers.Direccion;
import Helpers.Factura;
import Helpers.Servicio;

import java.util.*;

import static Helpers.ValidatorArguments.*;

public class Cliente {
    final String NIF;
    final String nombre;
    final Direccion direccion;
    final String correo;
    final Date fechaAlta;
    final Servicio servicio;
    final List<Factura> facturas;

    public Cliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final Date fechaAlta, final Servicio servicio) {
        this.NIF = stringMatchesPattern("NIF", NIF, "\\d+[TRWAGMYFPDXBNJZSQVHLCKE]");
        this.nombre = stringNotEmpty("nombre", nombre);
        this.direccion = referenceNotNull("direccion", direccion);
        this.correo = stringMatchesPattern("correo", correo, "[\\w.]+@[\\w.]{2,}");
        this.fechaAlta = referenceNotNull("fechaAlta", fechaAlta);
        this.servicio = referenceNotNull("servicio", servicio);
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

    public Servicio getServicio() {
        return servicio;
    }

    final public List<Factura> getFacturas() {
        return Collections.unmodifiableList(facturas);
    }

    public void addFactura(Factura factura) {
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
                ", servicio=" + servicio +
                ", facturas=" + facturas +
                '}';
    }
}
