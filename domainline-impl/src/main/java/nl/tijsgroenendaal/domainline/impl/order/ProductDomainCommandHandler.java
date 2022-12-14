package nl.tijsgroenendaal.domainline.impl.order;

import nl.tijsgroenendaal.domainline.DomainHandler;
import nl.tijsgroenendaal.domainline.RequestHandler;
import nl.tijsgroenendaal.domainline.impl.order.commands.CreateProductCommand;
import nl.tijsgroenendaal.domainline.impl.order.models.ProductModel;

import java.util.UUID;

public class ProductDomainCommandHandler implements DomainHandler<ProductDomain> {

    @RequestHandler
    private ProductModel createProduct(CreateProductCommand command) {
        System.out.println("Create Product: " + command.name + " " + command.description);
        return new ProductModel(UUID.randomUUID(), command.name, command.description);
    }

}
