package org.api.request.RQ;


import org.api.entity.ContextData;
import org.api.request.ObjectRQ;
import org.api.request.authentication.AuthenticationRequestDto;

public record AuthenticationRequestRQ(ContextData context, AuthenticationRequestDto data)  implements ObjectRQ<AuthenticationRequestDto> {
    public static ObjectRQ<AuthenticationRequestDto> of(ContextData context, AuthenticationRequestDto data) {
        return new AuthenticationRequestRQ(context, data);
    }
}
