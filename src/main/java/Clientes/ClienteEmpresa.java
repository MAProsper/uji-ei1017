package Clientes;

import Direcciones.Direccion;
import Tarifas.Tarifa;

import java.util.Date;

public class ClienteEmpresa extends Cliente {
    public ClienteEmpresa(String nombre, String NIF, Direccion direccion, String correo, Date fechaAlta, Tarifa tarifa) {
        super(nombre, NIF, direccion, correo, fechaAlta, tarifa);
    }
}
