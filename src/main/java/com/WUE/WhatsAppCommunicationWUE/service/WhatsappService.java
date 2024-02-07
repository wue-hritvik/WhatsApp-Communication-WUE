package com.WUE.WhatsAppCommunicationWUE.service;

import com.WUE.WhatsAppCommunicationWUE.dto.TemplateVariables;
import com.WUE.WhatsAppCommunicationWUE.dto.WhatsappRequestDto;
import com.WUE.WhatsAppCommunicationWUE.dto.WhatsappRequestDtoForExternalApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WhatsappService {

    @Autowired
    RestTemplate restTemplate;


    public ResponseEntity<?> sendMessage(WhatsappRequestDto payload) {


        List<String> numbersList = new ArrayList<>(payload.getPhoneNumbersAndUsernames().keySet());
        System.out.println(numbersList);

        String usernamesString = String.join(",", payload.getPhoneNumbersAndUsernames().values());

        System.out.println(usernamesString);
        WhatsappRequestDtoForExternalApi payloadForExternalApi = WhatsappRequestDtoForExternalApi.builder()
                .recipients(numbersList)
                .template_name(payload.getTemplateName())
                .template_variables(TemplateVariables.builder()
                        .variable1(usernamesString)
                        .variable2(payload.getMessageBody())
                        .variable3(payload.getMessageFooter())
                        .build())
                .build();
        System.out.println(payloadForExternalApi);
        HttpEntity<?> request = new HttpEntity<>(payloadForExternalApi);

        ResponseEntity<Object> response = restTemplate.exchange(
                "http://54.84.143.209:5000/send",
                HttpMethod.POST,
                request,
                Object.class);
        return ResponseEntity.ok(response.getBody());
    }
}
