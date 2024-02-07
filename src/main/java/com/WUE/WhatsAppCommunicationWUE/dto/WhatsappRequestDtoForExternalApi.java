package com.WUE.WhatsAppCommunicationWUE.dto;

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
}
