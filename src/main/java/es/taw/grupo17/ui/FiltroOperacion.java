package es.taw.grupo17.ui;

import java.util.List;

public class FiltroOperacion {

    private List<Integer> tipos;

    private boolean cantidad;

    private boolean fecha;

    public boolean isCantidad() {
        return cantidad;
    }

    public void setCantidad(boolean cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isFecha() {
        return fecha;
    }

    public void setFecha(boolean fecha) {
        this.fecha = fecha;
    }

    public List<Integer> getTipos() {
        return tipos;
    }

    public void setTipos(List<Integer> tipos) {
        this.tipos = tipos;
    }

    public FiltroOperacion() {
        this.fecha = false;
        this.cantidad = false;
    }

}
