package com.revy.api_server.app.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by Revy on 2023.12.30
 */

@Getter
@NoArgsConstructor
public class CollateralAddReq {
    @JsonProperty("collateralCodes")
    @NotEmpty(message = "담보코드는 필수값입니다.")
    private Set<String> collateralCodes;

    public CollateralAddReq(Set<String> collateralCodes) {
        this.collateralCodes = collateralCodes;
    }
}
