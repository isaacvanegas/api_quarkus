package org.api.util.logger;

import jakarta.enterprise.context.Dependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Dependent
public class TimesLogger {

    private static final Logger LOG = LoggerFactory.getLogger("API_SERVICE_ACCESS");

    public void log(String transactionId, String operation, long millis) {
        MDC.put("transactionId", transactionId);
        MDC.put("operation", operation);
        LOG.info(String.valueOf(millis));
        MDC.remove("transactionId");
        MDC.remove("operation");
    }

}