package Clientes;

import java.util.*;

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

    public Cliente(final String NIF, final String nombre, final Direccion direccion, final String correo, final Date fechaAlta, final Tarifa tarifa) {
        this.nombre = stringNotEmpty("nombre", nombre);
        this.NIF = stringMatchesPattern("NIF", NIF, "\\d+[TRWAGMYFPDXBNJZSQVHLCKE]");
        this.direccion = referenceNotNull("direccion", direccion);
        this.correo = stringMatchesPattern("correo", correo, "[\\w.]+@[\\w.]{2,}");
        this.fechaAlta = referenceNotNull("fechaAlta", fechaAlta);
        this.tarifa = referenceNotNull("tarifa", tarifa);
    }

    public String getNombre() {
        return nombre;
    }

    public String getNIF() {
        return NIF;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFecha() {
        return fechaAlta;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(final Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) &&
                Objects.equals(NIF, cliente.NIF) &&
                Objects.equals(direccion, cliente.direccion) &&
                Objects.equals(correo, cliente.correo) &&
                Objects.equals(fechaAlta, cliente.fechaAlta) &&
                Objects.equals(tarifa, cliente.tarifa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, NIF, direccion, correo, fechaAlta, tarifa);
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
