package withOptional.utils;


import withOptional.model.Product;

import java.util.Collection;

@FunctionalInterface
public interface ItemNotFoundHandler {
    double handle(Collection<Product> items);
}
