package com.revy.api_server.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Revy on 2023.12.30
 */

@Getter
@Setter
public class CommonRes {

    @JsonProperty("message")
    protected String message;

    @JsonProperty("success")
    protected boolean success = true;
}
