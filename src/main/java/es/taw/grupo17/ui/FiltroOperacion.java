package es.taw.grupo17.ui;

import es.taw.grupo17.entity.TipooperacionEntity;

import java.sql.Date;


public class FiltroOperacion {

    private Date fecha;
    private Integer tipo;

    public Date getFecha()
    {
        return fecha;
    }

    public Integer getTipo()
    {
        return tipo;
    }

    public void setFecha(Date aux)
    {
        fecha = aux;
    }

    public void setTipo(Integer aux)
    {
        tipo = aux;
    }
}
