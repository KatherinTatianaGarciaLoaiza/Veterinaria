package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.Identity;

public class VendedorId extends Identity {

    private VendedorId(String uid){
        super(uid);
    }

    public VendedorId(){

    }

    public static VendedorId of(String uid) {return new VendedorId(uid);}

}
