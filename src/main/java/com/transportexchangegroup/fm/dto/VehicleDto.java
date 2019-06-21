package com.transportexchangegroup.fm.dto;

import lombok.Data;

import java.util.List;

@Data
public class VehicleDto {
    private Integer vehicleId;
    private String name;
    private List<OrderDto> orders;
}