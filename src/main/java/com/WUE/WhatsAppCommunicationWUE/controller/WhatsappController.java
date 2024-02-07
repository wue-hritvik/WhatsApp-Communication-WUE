package com.WUE.WhatsAppCommunicationWUE.controller;

import com.WUE.WhatsAppCommunicationWUE.service.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whatsapp")
public class WhatsappController {

    @Autowired
    private WhatsappService whatsappService;

}
