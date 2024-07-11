package com.tianlan.tlproject.demos.DTO;

import lombok.Data;

@Data
public class ResultDTO {
    private int ResultCode;
    private String message;
    private Object data;
}
