package nl.tijsgroenendaal.domainline.impl.order;

import nl.tijsgroenendaal.domainline.DomainMiddleware;
import nl.tijsgroenendaal.domainline.RequestHandler;
import nl.tijsgroenendaal.domainline.impl.order.commands.CreateProductCommand;

public class ProductDomainCommandValidator implements DomainMiddleware<ProductDomain> {

    @RequestHandler
    private void createProduct(CreateProductCommand command) {
        System.out.println("Validate CreateProduct");
    }

    @RequestHandler
    private void secondCreateProduct(CreateProductCommand command) {
        System.out.println("Second Validate CreateProduct");
    }

}
