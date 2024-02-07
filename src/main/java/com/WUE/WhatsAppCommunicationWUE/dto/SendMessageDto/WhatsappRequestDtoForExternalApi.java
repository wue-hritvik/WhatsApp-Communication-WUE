package com.WUE.WhatsAppCommunicationWUE.dto.SendMessageDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhatsappRequestDtoForExternalApi {

    private List<String> recipients;
    private String template_name;
    private TemplateVariables template_variables;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TemplateVariables {
        private String variable1;
        private String variable2;
        private String variable3;
    }
}
