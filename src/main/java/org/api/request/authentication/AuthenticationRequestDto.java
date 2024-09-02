package org.api.request.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.api.request.Request;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Data
//@XmlRootElement
//@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Información necesesaria del request.")
public class AuthenticationRequestDto implements Request, Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Nobre del usuario.", example = "jvanegas")
    String userName ;
    @Schema(description = "Password", example = "e10adc3949ba59abbe56e057f20f883e")
    String password ;
    public AuthenticationRequestDto() {}

    public AuthenticationRequestDto(AuthenticationRequestDto other) {
        this.userName  = other.userName ;
        this.password = other.password ;
    }

    public AuthenticationRequestDto withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public AuthenticationRequestDto withPassword(String password) {
        this.password = password;
        return this;
    }
}
