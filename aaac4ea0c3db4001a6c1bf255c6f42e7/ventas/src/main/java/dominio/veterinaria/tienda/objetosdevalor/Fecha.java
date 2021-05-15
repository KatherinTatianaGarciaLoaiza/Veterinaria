package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Fecha implements ValueObject<String> {

    private final String DataPrestamo;

    public Fecha() {
        this.DataPrestamo = Objects.requireNonNull(FechaYHora());
    }

    public String FechaYHora(){
        Date HoraActual = new Date( );
        SimpleDateFormat Formato = new SimpleDateFormat("YYYY/MM/DD '-' HH:mm:ss");
        return Formato.format(HoraActual);
    }

    public String value() {
        return DataPrestamo;
    }
}
