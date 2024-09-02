package org.api.operation;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class OperationsManagerCore implements OperationsManager {


    private final ProductoOperations productoOperations;


    @Inject
    public OperationsManagerCore(ProductoOperations productoOperations) {
        this.productoOperations = productoOperations;
    }


    @Override
    public ProductoOperations productoOperations() {
        return productoOperations;
    }
}
