package Helpers;

import Clientes.Cliente;
import Clientes.ClienteEmpresa;
import Clientes.ClientePaticular;
import Direcciones.Direccion;
import Facturas.Factura;
import Tarifas.Tarifa;
import com.google.common.collect.Range;
import es.uji.www.GeneradorDatosINE;

import java.util.*;

public class HelperGenerator extends GeneradorDatosINE {
    //Generation is guaranteed if used exclusively

    final static Random random = new Random();
    static int facturas = 0;

    public HelperGenerator() {
        super();
    }

    public int getCodigoPostal(final String provincia) {
        return Math.abs(provincia.hashCode());
    }

    public String getApellidos() {
        return getApellido() + " " + getApellido();
    }

    private static String stripNonWord(final String s) {
        return s.replaceAll("\\W", "_");
    }

    public String getCorreo() {
        final String provincia = getProvincia();
        return stripNonWord(getNombre()) + "@" + stripNonWord(getPoblacion(provincia)) + "." + stripNonWord(provincia);
    }

    public Direccion getDireccion() {
        final String provincia = getProvincia();
        return new Direccion(getCodigoPostal(provincia), provincia, getPoblacion(provincia));
    }

    public Range<Date> getPeriodo() {
        final List<Date> fechas = Arrays.asList(new Date(random.nextLong()), new Date(random.nextLong()));
        return Range.closed(Collections.min(fechas), Collections.max(fechas));
    }

    public Factura getFactura() {
        return new Factura(facturas++, getTarifa(), getFecha(), getPeriodo());
    }

    public String getTelefono() {
        return Integer.toString(100000000 + random.nextInt(899999999));
    }

    public Date getFecha() {
        return new Date(random.nextLong());
    }

    public Tarifa getTarifa() {
        return new Tarifa(random.nextDouble());
    }

    public Cliente getCliente() {
        return new Cliente(getNombre(), getNIF(), getDireccion(), getCorreo(), getFecha(), getTarifa());
    }

    public ClientePaticular getClientePaticular() {
        return new ClientePaticular(getNombre(), getApellido(), getNIF(), getDireccion(), getCorreo(), getFecha(), getTarifa());
    }

    public ClienteEmpresa getClienteEmpresa() {
        return new ClienteEmpresa(getNombre(), getNIF(), getDireccion(), getCorreo(), getFecha(), getTarifa());
    }
}
