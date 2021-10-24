package com.lipsoft.locacaofilmesapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Service
@Builder(builderClassName = "Builder", toBuilder = true)
public class MessageResponse {

    private String message;


    public MessageResponse createMessageResponse(String s, Long id2) {
        return MessageResponse
                .builder()
                .message(s + id2)
                .build();
        }
}
