package com.sda.DTO;

import com.sda.model.Ad;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdDTO {
    private Ad ad;
    private boolean isObservedByCurrentUser;
}
