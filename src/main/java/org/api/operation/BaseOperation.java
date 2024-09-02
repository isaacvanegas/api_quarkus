package org.api.operation;



import jakarta.enterprise.context.Dependent;
import org.api.request.ObjectRQ;
import org.api.request.Request;
import org.api.response.ErrorType;
import org.api.response.ResponseErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

@Dependent
public class BaseOperation<Request extends org.api.request.Request, Response extends ResponseErrors> {
    
    private static final Logger LOG = LoggerFactory.getLogger(BaseOperation.class);

    Response execute(ObjectRQ<Request> request, Function<ObjectRQ<Request>, Response> execution, Supplier<Response> responseSupplier) {
        try {
            Response resp = execution.apply(request);
            if (resp.getError().isEmpty()) {
                resp.getError().add(ErrorType.ok());
            }
            return resp;
        } catch (Exception ex) {
            LOG.error("TrainsException", ex);
            return getErrorResponse(ex, responseSupplier);
        }
    }
    
    private Response getErrorResponse(Exception exception, Supplier<Response> responseSupplier) {
        Response response = responseSupplier.get();
        response.getError().add(ErrorType.important(exception.getMessage()));
        return response;
    }
}
