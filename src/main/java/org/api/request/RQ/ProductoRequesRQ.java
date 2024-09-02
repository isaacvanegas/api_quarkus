package org.api.request.RQ;

import org.api.entity.ContextData;
import org.api.request.ObjectRQ;
import org.api.request.catalogo.ProductoReques;

public record ProductoRequesRQ(ContextData context, ProductoReques data)  implements ObjectRQ<ProductoReques> {
    public static ObjectRQ<ProductoReques> of(ContextData context, ProductoReques data) {
        return new ProductoRequesRQ(context, data);
    }
}
