package org.api.response.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.api.response.ErrorType;
import org.api.response.ResponseErrors;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
//@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response autentication.")
public class AuthenticationResponse implements Serializable, ResponseErrors {

    @Schema(description = "Token jwt.")
    private String jwt;
    @Schema(description = "Verdadero si logra genera el token.")
    private boolean generateJwt;
    @Schema(description = "Lista de errores.")
    private List<ErrorType> error;

    public AuthenticationResponse(){}

    public AuthenticationResponse(AuthenticationResponse other) {
       this.jwt= other.jwt;
       this.generateJwt = other.generateJwt;
    }

    public AuthenticationResponse withJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public AuthenticationResponse withGenerateJwt(boolean generateJwt) {
        this.generateJwt = generateJwt;
        return this;
    }

    @Override
    public List<ErrorType> getError() {
        if (error == null) {
            error = new ArrayList<>();
        }
        return this.error;
    }
}
