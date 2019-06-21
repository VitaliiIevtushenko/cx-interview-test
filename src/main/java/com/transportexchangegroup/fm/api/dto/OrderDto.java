package com.transportexchangegroup.fm.api.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class OrderDto {
    private Integer orderId;
    private Instant date;
}