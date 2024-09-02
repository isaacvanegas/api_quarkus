package org.api.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.api.entity.catalogo.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductoRepository implements PanacheRepository<Producto> {
    private static final Logger LOG = LoggerFactory.getLogger(ProductoRepository.class);


}
