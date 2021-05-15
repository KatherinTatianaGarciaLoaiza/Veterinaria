package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.Identity;

public class ClienteId extends Identity {

    private ClienteId(String uid){
        super(uid);
    }

    public ClienteId(){

    }

    public static ClienteId of(String uid) {return new ClienteId(uid);}

}
