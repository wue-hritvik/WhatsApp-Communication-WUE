package com.WUE.WhatsAppCommunicationWUE.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhatsappRequestDto {

    private Map<String,String> phoneNumbersAndUsernames;
    private String messageBody;
    private String templateName;
    private String messageFooter;

}
