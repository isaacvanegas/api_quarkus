package org.api.operation;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.api.builder.ProductoBuilder;
import org.api.entity.ContextData;
import org.api.entity.catalogo.Producto;
import org.api.request.ObjectRQ;
import org.api.request.catalogo.ProductoReques;
import org.api.response.catalogo.ProductoResponse;
import org.api.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Dependent
public class ProductoOperations {

    private static final Logger LOG = LoggerFactory.getLogger(ProductoOperations.class);

    private final BaseOperation<ProductoReques, ProductoResponse> baseOperation;

    private final ProductoService productoService;

    private final ProductoBuilder productoBuilder;

    @Inject
    public ProductoOperations(BaseOperation<ProductoReques, ProductoResponse> baseOperation,
                              ProductoService productoService,
                              ProductoBuilder productoBuilder) {
        this.baseOperation = baseOperation;
        this.productoService = productoService;
        this.productoBuilder = productoBuilder;
    }

    public ProductoResponse execute(ObjectRQ<ProductoReques> rq) {
        return baseOperation.execute(rq, this::doExecute, ProductoResponse::new);
    }


    private ProductoResponse doExecute(ObjectRQ<ProductoReques> rq) {
        LOG.debug("App Version Verific");

        ProductoReques request = rq.data();
        ContextData context = rq.context();

        return productoBuilder.buildResponse(operation(request));
    }

    private List<Producto> operation(ProductoReques request) {
        List<Producto> lp = new ArrayList<>();
        Producto p = setProdcuto( request);

        if (request.getAcciones().getGuardar()) {
            lp.add(productoService.save(p));
        } else if (request.getAcciones().getActualizar()) {
           lp.add(productoService.update(p));
        } else if (request.getAcciones().getEliminar() && productoService.delete(p)){
            lp.add(p);
        }
        else {
            lp = productoService.getProductos(request.getProductoDto().getId());
        }

        return lp;
    }

    private Producto setProdcuto(ProductoReques request) {
        Producto p = new Producto();
        p.setId(request.getProductoDto().getId());
        p.setNombre(request.getProductoDto().getNombre());
        p.setPrecio(request.getProductoDto().getPrecio());
        return p;
    }

}
