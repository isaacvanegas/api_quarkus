package org.api.builder;

import jakarta.enterprise.context.Dependent;
import org.api.DTO.ProductoDto;
import org.api.entity.catalogo.Producto;
import org.api.request.catalogo.ProductoReques;
import org.api.response.catalogo.ProductoResponse;

import java.util.List;

@Dependent
public class ProductoBuilder {

    public ProductoDto build(Producto producto){
        if(producto==null){
            return null;
        }
        return  new ProductoDto()
                .withId(producto.getId())
                .withNombre(producto.getNombre())
                .withPrecio(producto.getPrecio());
    }

    public List<ProductoDto> buildList(List<Producto> listProduct){
        if (listProduct==null) {
            return null;
        }
        return  listProduct
                .stream()
                .map(this::build)
                .toList();
    }

    public ProductoResponse buildResponse(List<Producto> listProduct){
        if (listProduct==null) {
            return null;
        }
        return new ProductoResponse().withProductos(this.buildList(listProduct)) ;
    }
}
