package com.sda.DTO;

import com.sda.model.Ad;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdDTO {
    private Ad ad;
    private Boolean isObservedByCurrentUser;

    public AdDTO(Ad ad) {
        this.ad = ad;
    }

    public Boolean getObservedByCurrentUser() {
        return isObservedByCurrentUser;
    }

    public void setObservedByCurrentUser(Boolean observedByCurrentUser) {
        isObservedByCurrentUser = observedByCurrentUser;
    }
}
