package com.WUE.WhatsAppCommunicationWUE.controller;

import com.WUE.WhatsAppCommunicationWUE.dto.SendMessageDto.WhatsappRequestDto;
import com.WUE.WhatsAppCommunicationWUE.service.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/whatsapp")
public class WhatsappController {

    @Autowired
    private WhatsappService whatsappService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody WhatsappRequestDto payload, @RequestParam String user) {
       return whatsappService.sendMessage(payload,user);
    }

    @GetMapping("/allTemplateNames")
    public ResponseEntity<?> getTemplatesNames() {
        return whatsappService.getTemplateNames();
    }
    @GetMapping("/templateUsedCountAndDetails/{templateName}")
    public ResponseEntity<?> templateUsedCountAndDetails(@PathVariable String templateName, @RequestParam String user) {
        return whatsappService.templateUsedCountAndDetails(templateName,user);
    }

}
