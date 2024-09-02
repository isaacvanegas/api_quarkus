package org.api.RS.catalogo;

import io.quarkus.vertx.http.Compressed;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.api.entity.ContextData;
import org.api.operation.OperationsManager;
import org.api.request.RQ.ProductoRequesRQ;
import org.api.request.catalogo.ProductoReques;
import org.api.response.ResponseErrors;
import org.api.response.catalogo.ProductoResponse;
import org.api.util.logger.OperationAccessLogger;
import org.api.util.logger.TimesLogger;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.function.Supplier;

@SecurityScheme(
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)
@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogoRS {
    private static final Logger LOG = LoggerFactory.getLogger(CatalogoRS.class);

    @HeaderParam("Token")
    private String token;
    @HeaderParam("TransactionId")
    private String transactionId;

    @Inject
    OperationAccessLogger operationAccessLogger;
    @Inject
    TimesLogger timesLogger;
    @Inject
    OperationsManager operationsManager;

    @POST
    @RolesAllowed("admin")
    @Compressed
    @Path("/productoOperation")
    @Operation(
            operationId = "productoOperation",
            summary = "operaciones de producto",
            description = "Realiza el crud basico para la tabla productos."
    )
    @APIResponse(
            responseCode = "200",
            description = "Operación realizada con éxito."
    )
    public ProductoResponse productoOperation(
            @RequestBody(
                    required = true,
                    description = "Request access data.",
                    content = @Content(schema = @Schema(implementation = ProductoReques.class))
            )
            ProductoReques request
    ) {
        LOG.info("productoOperation");
        return log("productoOperatios", () -> operationsManager
                .productoOperations()
                .execute(ProductoRequesRQ.of(buildContext(), request)));
    }

    private <Response extends ResponseErrors> Response log(String operation, Supplier<Response> supplier) {
        logAccessRequest(operation);
        long init = System.currentTimeMillis();
        Response rs = supplier.get();
        long end = System.currentTimeMillis();
        timesLogger.log(getTransactionId(transactionId), operation, end - init);
        logAccessResponse(operation);
        return rs;
    }

    private void logAccessRequest(String operation) {
        operationAccessLogger.logRequest(getTransactionId(transactionId), operation);
    }

    private void logAccessResponse(String operation) {
        operationAccessLogger.logResponse(getTransactionId(transactionId), operation);
    }

    private ContextData buildContext() {
        return ContextData.builder()
                .setToken(token)
                .setTransactionId(getTransactionId(transactionId))
                .build();
    }

    private String getTransactionId(String value) {
        final String localTransactionId;
        if (value == null || value.trim().isEmpty()) {
            localTransactionId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        } else {
            localTransactionId = value;
        }
        return localTransactionId;
    }



}
