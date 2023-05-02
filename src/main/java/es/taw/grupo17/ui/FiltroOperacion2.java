package es.taw.grupo17.ui;

import java.sql.Date;

// Hecho al 100% por Francisco Javier Tejada Martín
public class FiltroOperacion2 {

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
