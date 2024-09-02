package org.api.request.catalogo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.api.DTO.ProductoDto;
import org.api.request.Acciones;
import org.api.request.Request;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;


@Data
@Schema(description = "Request de producto.")
public class ProductoReques implements Request, Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Objeto producto",  example = "{\"id\": 1, \"nombre\": \"Tomate\", \"precio\": 1.99}")
    ProductoDto productoDto ;

    @Schema(description = "Acciones a realizar", example = "{\"guardar\": false, \"eliminar\": false,\"actualizar\": false}" )
    Acciones acciones;

    public ProductoReques() {}

    public ProductoReques(ProductoReques productoReques) {
        this.productoDto  = productoReques.productoDto ;
        this.acciones = productoReques.acciones;
    }

    public ProductoReques withProducto(ProductoDto productoDto) {
        this.productoDto = productoDto;
        return this;
    }

    public ProductoReques withAcciones(Acciones acciones) {
        this.acciones = acciones;
        return this;
    }

}
