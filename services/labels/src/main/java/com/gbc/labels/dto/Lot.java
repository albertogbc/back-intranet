package com.gbc.labels.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lot {

    private String pslad_lot_item;
    private String pslad_lot_number;
    private String pslad_lot_quantity;
    private String pslad_lot_expiration_date;
}
