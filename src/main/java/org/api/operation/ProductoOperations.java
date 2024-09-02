package org.api.operation;

import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.api.DTO.ProductoReponseOperation;
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

    private ProductoReponseOperation operation(ProductoReques request) {
        List<Producto> lp = new ArrayList<>();
        String message ="";
        Producto p = setProdcuto(request,request.getAcciones().getGuardar());

        if (request.getAcciones().getGuardar()) {
            lp.add(productoService.save(p));
            message="Guardado con éxito";
        } else if (request.getAcciones().getActualizar()) {
           lp.add(productoService.update(p));
            message="Actualizado con éxito";
        } else if (request.getAcciones().getEliminar() && productoService.delete(p)){
            message="Eliminado con éxito";
        }
        else {
            lp = productoService.getProductos(request.getProductoDto().getId());
        }

        return new ProductoReponseOperation(lp,message);
    }

    private Producto setProdcuto(ProductoReques request,Boolean guardar) {
        Producto p = new Producto();
        if(!guardar){
            p.setId(request.getProductoDto().getId());
        }
        p.setNombre(request.getProductoDto().getNombre());
        p.setPrecio(request.getProductoDto().getPrecio());
        return p;
    }

}
