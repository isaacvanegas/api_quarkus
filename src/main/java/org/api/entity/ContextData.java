package org.api.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ContextData implements Serializable {

    private static final long serialVersionUID = 4302477463867466016L;
    private final String token;
    private final String username;
    private final String transactionId;

    ContextData() {
        this.token = null;
        this.username = null;
        this.transactionId = null;
    }

    private ContextData(Builder builder) {
        this.token = builder.token;
        this.username = builder.username;
        this.transactionId = builder.transactionId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String token;
        private String username;
        private String transactionId;

        private Builder() {
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }


        public Builder setTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public ContextData build() {
            return new ContextData(this);
        }
    }

}
