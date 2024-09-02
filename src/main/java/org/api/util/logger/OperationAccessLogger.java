package org.api.util.logger;

import jakarta.enterprise.context.Dependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Dependent
public class OperationAccessLogger {
    private static final Logger LOG = LoggerFactory.getLogger("API_SERVICE_ACCESS");

    public void logRequest(String transactionId, String operation) {
        log(transactionId, operation, "REQUEST");
    }

    public void logResponse(String transactionId, String operation) {
        log(transactionId, operation, "RESPONSE");
    }

    private void log(String transactionId, String operation, String direction) {
        MDC.put("transactionId", transactionId);
        MDC.put("operation", operation);
        MDC.put("direction", direction);
        LOG.info("");
        MDC.remove("transactionId");
        MDC.remove("operation");
        MDC.remove("direction");
    }

}
