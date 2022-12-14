package nl.tijsgroenendaal.domainline.impl.order.models;

import java.util.UUID;

public class ProductModel {
    public UUID id;
    public String name;
    public String description;

    public ProductModel(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductModel[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ']';
    }
}
