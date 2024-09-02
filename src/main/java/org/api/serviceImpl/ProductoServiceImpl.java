package org.api.serviceImpl;

import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;
import org.api.entity.catalogo.Producto;
import org.api.repository.ProductoRepository;
import org.api.service.ProductoService;


import java.util.List;

@Dependent
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository userRepository;

    public ProductoServiceImpl(ProductoRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<Producto> getProductos(Integer id){
        if (id==null){
            return  userRepository.list("id", id);
        } else{
            return userRepository.listAll();
        }
    }

    public Producto save(Producto p){
        userRepository.persist(p);
        return p;
    }

    public Producto update(Producto p){
        userRepository.persist(p);
        return p;
    }

    public Boolean delete(Producto p){

        if(userRepository.isPersistent(p)){
            userRepository.delete(p);
            return true;
        }
        return false;
    }


}
