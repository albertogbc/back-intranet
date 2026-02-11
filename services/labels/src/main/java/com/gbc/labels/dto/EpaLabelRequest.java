package com.gbc.labels.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EpaLabelRequest {
    private String description;
    private String upc;
    private String direntrega;
    private String tranid;
    private String quantity;
    private String custcol_pslad_inventory_details_json;
}
