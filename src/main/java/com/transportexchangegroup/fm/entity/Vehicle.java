package com.transportexchangegroup.fm.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehiclesSeq")
    @SequenceGenerator(name = "vehiclesSeq", sequenceName = "vehicles_vehicle_id_seq", allocationSize = 1)
    private Integer vehicleId;

    @NotNull
    @Size(max = 300)
    private String name;

    @NotNull
    private Boolean deleted;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Order> orders;
}