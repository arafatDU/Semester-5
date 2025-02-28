package registry;

import product.Product;
import java.util.HashMap;
import java.util.Map;

public class PrototypeRegistry {
    private Map<String, Product> prototypes = new HashMap<>();

    // Register a prototype with a key
    public void addPrototype(String key, Product prototype) {
        prototypes.put(key, prototype);
    }

    // Retrieve and clone a prototype
    public Product getPrototype(String key) {
        Product prototype = prototypes.get(key);
        if (prototype != null) {
            return prototype.clone();
        }
        throw new IllegalArgumentException("Prototype not found for key: " + key);
    }
}
