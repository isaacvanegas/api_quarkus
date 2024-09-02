package org.api.service;

import org.api.entity.catalogo.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> getProductos(Integer id);

    Producto save(Producto p);

    Producto update(Producto p);

    Boolean delete(Producto p);

}
