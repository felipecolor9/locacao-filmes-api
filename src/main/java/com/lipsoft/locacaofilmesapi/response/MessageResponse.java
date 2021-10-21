package com.lipsoft.locacaofilmesapi.response;

public class MessageResponse {

    private Long idObj;
    private String message;

    private MessageResponse(MessageResponseBuilder builder) {
        this.idObj = builder.idObj;
        this.message = builder.message;
    }

    public Long getIdObj() {
        return idObj;
    }

    public String getMessage() {
        return message;
    }

    public static class MessageResponseBuilder {
        private String message;
        private Long idObj;


        public MessageResponseBuilder() { }

        public MessageResponseBuilder addMessage(String message) {
            this.message = message;
            return this;
        }

        public MessageResponseBuilder addIdObj(Long idObj) {
            this.idObj = idObj;
            return this;
        }
        public MessageResponse build() {
            MessageResponse messageResponse = new MessageResponse(this);
            return messageResponse;
        }
    }
}
