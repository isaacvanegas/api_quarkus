package org.api.operation;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class OperationsManagerCore implements OperationsManager {


    private final InspeccionesOperations inspeccionesOperations;


    @Inject
    public OperationsManagerCore(InspeccionesOperations inspeccionesOperations) {
        this.inspeccionesOperations = inspeccionesOperations;
    }


    @Override
    public InspeccionesOperations getAppVersion() {
        return inspeccionesOperations;
    }
}
