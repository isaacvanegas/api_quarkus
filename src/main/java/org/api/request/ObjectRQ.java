package org.api.request;


import org.api.entity.ContextData;

public interface ObjectRQ<T extends Request> {
    public ContextData context();
    public T data();
}
    
