package org.api.response.catalogo;

import lombok.Data;
import org.api.DTO.ProductoDto;
import org.api.response.ErrorType;
import org.api.response.ResponseErrors;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Informacion de respuesta de operacion de productos.")
public class ProductoResponse implements ResponseErrors, Serializable {

    @Schema(description = "Lista de productos", example = "CÓRDOBAS")
    public List<ProductoDto> productos;

    @Schema(description = "List of errors.")
    private List<ErrorType> error;

    public ProductoResponse() {}

    public ProductoResponse(ProductoResponse productoResponse) {
        if(productoResponse.productos!=null && !productoResponse.productos.isEmpty()){
            this.productos = productoResponse.productos;
        }else {
            this.productos = new ArrayList<>();
        }
    }

    public ProductoResponse withProductos(List<ProductoDto> productos) {
        this.productos = productos;
        return this;
    }

    public ProductoResponse withError(List<ErrorType> error) {
        this.error = error;
        return this;
    }

    @Override
    public List<ErrorType> getError() {
        if (error == null) {
            error = new ArrayList<>();
        }
        return this.error;
    }
}
