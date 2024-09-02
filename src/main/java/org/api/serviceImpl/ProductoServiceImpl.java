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
        if (id==null || id==0){
            return userRepository.listAll();
        } else{

            return  userRepository.list("id", id);
        }
    }

    public Producto save(Producto p){
        userRepository.persist(p);
        return p;
    }

    public Producto update(Producto p){
        Producto pr = this.getProductos(p.getId()).getFirst();
        if(pr!=null){
            pr.setNombre(p.getNombre());
            pr.setPrecio(p.getPrecio());
            userRepository.persist(pr);
            return pr;
        }

        return p;
    }

    public Boolean delete(Producto p){
        Producto pr = this.getProductos(p.getId()).getFirst();
        if(userRepository.isPersistent(pr)){
            userRepository.delete(pr);
            return true;
        }
        return false;
    }


}
