package org.api.request;


import lombok.Data;
import org.api.request.catalogo.ProductoReques;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Schema(description = "Acciones a realizar, guardar, actualizar, eliminar.")
public class Acciones {

    @Schema(description = "Guardar", example = "false")
    Boolean guardar ;

    @Schema(description = "Eliminar", example = "false")
    Boolean eliminar ;

    @Schema(description = "Actualizar", example = "false")
    Boolean actualizar ;

    public Acciones() {}

    public Acciones(Acciones acciones) {
        this.guardar  = acciones.guardar ;
        this.eliminar = acciones.eliminar;
        this.actualizar = acciones.actualizar;
    }

    public Acciones withGuardar(Boolean guardar) {
        this.guardar = guardar;
        return this;
    }

    public Acciones withEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
        return this;
    }

    public Acciones withActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
        return this;
    }
}
