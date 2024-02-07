package com.WUE.WhatsAppCommunicationWUE.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class WhatsappTemplateAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;
    private String templateUsedByUser;
    private Long totalCountOfTemplateUsage;
    private Long totalCountOfMessagesSent;

    @CreationTimestamp
    private Date createdAtTs;
    @UpdateTimestamp
    private Date updatedAtTs;

}
