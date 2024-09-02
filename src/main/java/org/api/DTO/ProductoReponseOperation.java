package org.api.DTO;

import io.vertx.mutiny.core.eventbus.Message;
import lombok.Data;
import org.api.entity.catalogo.Producto;

import java.util.List;

@Data
public class ProductoReponseOperation {
    List<Producto> productos;
    String message;

    public ProductoReponseOperation(List<Producto> productos, String message){
        this.productos = productos;
        this.message = message;
    }
}
