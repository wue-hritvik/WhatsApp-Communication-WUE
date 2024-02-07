package com.WUE.WhatsAppCommunicationWUE.dto.TemplateDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplatesResponseDto {
    private List<DataItem> data;
    private Paging paging;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataItem {
        private String id;
        private String name;
        private String status;

    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Paging {
        private Cursors cursors;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cursors {
        private String after;
        private String before;
    }



}

