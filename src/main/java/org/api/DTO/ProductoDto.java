package org.api.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Value;
import org.api.request.Acciones;
import org.api.request.catalogo.ProductoReques;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@Schema(description = "Informacion de catalogo.")
public class ProductoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Identificador", example = "1")
    Integer id ;
    @Schema(description = "Nombre del producto", example = "Totames")
    String nombre;
    @Schema(description = "Precio", example = "2.50")
    Double precio;

    public ProductoDto() {}

    public ProductoDto(ProductoDto productoDto) {
        this.id  = productoDto.id ;
        this.nombre = productoDto.nombre;
        this.precio = productoDto.precio;
    }

    public ProductoDto withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProductoDto withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoDto withPrecio(Double precio) {
        this.precio = precio;
        return this;
    }


}