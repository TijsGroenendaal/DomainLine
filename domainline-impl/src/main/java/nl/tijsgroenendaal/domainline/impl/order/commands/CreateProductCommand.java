package nl.tijsgroenendaal.domainline.impl.order.commands;

import nl.tijsgroenendaal.domainline.Request;
import nl.tijsgroenendaal.domainline.impl.order.ProductDomain;
import nl.tijsgroenendaal.domainline.impl.order.models.ProductModel;

public class CreateProductCommand extends Request<ProductModel, ProductDomain> {
    public String name;
    public String description;

    public CreateProductCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
