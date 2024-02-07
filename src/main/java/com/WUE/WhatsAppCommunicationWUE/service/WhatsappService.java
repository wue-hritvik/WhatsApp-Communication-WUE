package com.WUE.WhatsAppCommunicationWUE.service;

import com.WUE.WhatsAppCommunicationWUE.dto.SendMessageDto.WhatsappRequestDto;
import com.WUE.WhatsAppCommunicationWUE.dto.SendMessageDto.WhatsappRequestDtoForExternalApi;
import com.WUE.WhatsAppCommunicationWUE.dto.TemplateDto.TemplatesResponseDto;
import com.WUE.WhatsAppCommunicationWUE.model.WhatsappTemplateAnalytics;
import com.WUE.WhatsAppCommunicationWUE.repository.WhatsappTemplateAnalyticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WhatsappService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WhatsappTemplateAnalyticsRepository templateAnalyticsRepository;


    public ResponseEntity<?> sendMessage(WhatsappRequestDto payload, String user) {


        List<String> numbersList = new ArrayList<>(payload.getPhoneNumbersAndUsernames().keySet());
        System.out.println(numbersList);

        String usernamesString = String.join(",", payload.getPhoneNumbersAndUsernames().values());

        System.out.println(usernamesString);
        WhatsappRequestDtoForExternalApi payloadForExternalApi = WhatsappRequestDtoForExternalApi.builder()
                .recipients(numbersList)
                .template_name(payload.getTemplateName())
                .template_variables(WhatsappRequestDtoForExternalApi.TemplateVariables.builder()
                        .variable1(usernamesString)
                        .variable2(payload.getMessageBody())
                        .variable3(payload.getMessageFooter())
                        .build())
                .build();
        System.out.println(payloadForExternalApi);


        ResponseEntity<Object> response = restTemplate.exchange(
                "http://54.84.143.209:5000/send",
                HttpMethod.POST,
                new HttpEntity<>(payloadForExternalApi),
                Object.class);
        WhatsappTemplateAnalytics oldAnalytics = templateAnalyticsRepository.findByTemplateNameAndTemplateUsedByUser(payload.getTemplateName(), user);

        if (oldAnalytics != null) {
            oldAnalytics.setTotalCountOfMessagesSent(oldAnalytics.getTotalCountOfMessagesSent() + numbersList.size());
            oldAnalytics.setTotalCountOfTemplateUsage(oldAnalytics.getTotalCountOfTemplateUsage() + 1);
            templateAnalyticsRepository.save(oldAnalytics);
        } else {
            WhatsappTemplateAnalytics analytics = new WhatsappTemplateAnalytics();
            analytics.setTemplateName(payload.getTemplateName());
            analytics.setTemplateUsedByUser(user);
            analytics.setTotalCountOfTemplateUsage(1L);
            analytics.setTotalCountOfMessagesSent((long) numbersList.size());
            templateAnalyticsRepository.save(analytics);
        }

        return ResponseEntity.ok(response.getBody());
    }

    public ResponseEntity<?> getTemplateNames() {

        TemplatesResponseDto response = restTemplate.getForEntity(
                "http://100.26.244.18:5000/approved_templates",
                TemplatesResponseDto.class).getBody();

        return ResponseEntity.ok(Objects.requireNonNull(response).getData().stream()
                .map(TemplatesResponseDto.DataItem::getName)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<?> templateUsedCountAndDetails(String templateName, String user) {

//        templateName = templateName.toLowerCase();
        WhatsappTemplateAnalytics analytics = templateAnalyticsRepository
                .findByTemplateNameAndTemplateUsedByUser(templateName, user);
        return ResponseEntity.ok(analytics);

    }
}
