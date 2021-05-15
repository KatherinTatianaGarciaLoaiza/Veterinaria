package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;

public class FormulaMedica implements ValueObject<String> {

    private final String formulamedica;

    public FormulaMedica(String formulamedica){
        this.formulamedica = formulamedica;
    }

    public String getFormulamedica() {
        return formulamedica;
    }

    @Override
    public String value() {
        return formulamedica;
    }
}
