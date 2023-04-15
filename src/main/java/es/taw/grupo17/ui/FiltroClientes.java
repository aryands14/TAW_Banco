package es.taw.grupo17.ui;

import java.util.List;

public class FiltroClientes {

    private String texto;
    private List<String> estados;

    private List<Integer> tipos;

    public FiltroClientes() {

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Integer> getTipos() {
        return tipos;
    }

    public void setTipos(List<Integer> tipos) {
        this.tipos = tipos;
    }

    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }
}
