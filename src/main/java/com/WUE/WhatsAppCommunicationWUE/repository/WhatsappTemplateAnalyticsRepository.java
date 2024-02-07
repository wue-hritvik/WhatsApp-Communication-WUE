package com.WUE.WhatsAppCommunicationWUE.repository;

import com.WUE.WhatsAppCommunicationWUE.model.WhatsappTemplateAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhatsappTemplateAnalyticsRepository extends JpaRepository<WhatsappTemplateAnalytics, Long> {
    WhatsappTemplateAnalytics findByTemplateNameAndTemplateUsedByUser(String templateName, String user);
}
