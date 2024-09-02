package org.api.request.RQ;

import org.api.entity.ContextData;
import org.api.request.ObjectRQ;
import org.api.request.catalogo.ProductoReques;

public record ProductoRequesRQ(ContextData contex, ProductoReques data) implements ObjectRQ<ProductoReques> {
    public static ObjectRQ<ProductoReques> of(ContextData contex,ProductoReques data){
        return  new ProductoRequesRQ(contex,data);
    }

    @Override
    public ContextData context() {
        return null;
    }
}
