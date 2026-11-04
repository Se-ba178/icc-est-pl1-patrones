package edu.ups.pap.models.prototype;

import java.util.TreeMap;

public class RegistroPrototipos {

    // Guarada lso procutos originales asociados auna clav
    private final TreeMap<String, ProductoBase> prototipos;

    public RegistroPrototipos() {
        prototipos = new TreeMap<>();

        prototipos.put("cafe", new Cafe("PROTO-CAFE"));
        prototipos.put("ensalada", new Ensalada("PROTO-ENSALADA"));
        prototipos.put("pizza", new Pizza("PROTO-PIZZA"));

    }

    // busca el prtotypo y devuelve una copia con otro id
    public ProductoBase clonar(String clave, String nuevoId) {
        ProductoBase original = prototipos.get(clave);

        // una clave q no existe prodcue unnerror controaldo
        if (original == null) {
            throw new IllegalArgumentException("noe xiste un prototipo con la clave " + clave);

        }

        if (nuevoId == null || nuevoId.isBlank()) {
            throw new IllegalArgumentException("Debes proporcionar un identificador");
        }

        if (nuevoId.equals(original.id())) {
            throw new IllegalArgumentException("La copia  debe tener id diferente a la del prototipo");
        }

        return original.copiar(nuevoId);// devolvemos una copia ,nunca se devuelve eloriginal
    }

    public void registrar(String clave, ProductoBase prototipo) {
        prototipos.put(clave, prototipo);
    }

}
