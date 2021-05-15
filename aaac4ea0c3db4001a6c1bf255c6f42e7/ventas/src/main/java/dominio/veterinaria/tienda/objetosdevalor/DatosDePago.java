package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;
import java.util.HashMap;
import java.util.Map;

public class DatosDePago implements ValueObject<DatosDePago.Value> {

    private final String banco;
    private final Long numCuenta;
    private final MedioDePago medioDePago;
    private final int numCuotas;

    public DatosDePago(String banco, Long numCuenta, MedioDePago medioDePago, int numCuotas) {
        this.banco = banco;
        this.numCuenta = numCuenta;
        this.medioDePago = medioDePago(String.valueOf(medioDePago));
        this.numCuotas = (medioDePago == MedioDePago.EFECTIVO || medioDePago == MedioDePago.TARJETADEDEBITO) ?  1 : numCuotas;
    }

    public MedioDePago medioDePago(String medioDePago) {
        Map<String, MedioDePago> medioDePagoHashMap = new HashMap<String, MedioDePago>();
        medioDePagoHashMap.put("EFECTIVO", MedioDePago.EFECTIVO);
        medioDePagoHashMap.put("TARJETADECREDITO", MedioDePago.TARJETADECREDITO);
        medioDePagoHashMap.put("TARJETADEDEBITO", MedioDePago.TARJETADEDEBITO);
        return medioDePagoHashMap.get(medioDePago);
    }

    @Override
    public DatosDePago.Value value() {
        return new Value() {
            @Override
            public String banco() {
                return banco;
            }

            @Override
            public Long numCuenta() {
                return numCuenta;
            }

            @Override
            public MedioDePago medioDePago() {
                return medioDePago;
            }

            @Override
            public int numCuotas() {
                return numCuotas;
            }
        };
    }

    public interface Value {
        String banco();

        Long numCuenta();

        MedioDePago medioDePago();

        int numCuotas();
    }
}
