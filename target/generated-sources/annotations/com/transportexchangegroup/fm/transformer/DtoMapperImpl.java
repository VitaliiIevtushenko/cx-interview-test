package com.transportexchangegroup.fm.transformer;

import com.transportexchangegroup.fm.dto.OrderDto;
import com.transportexchangegroup.fm.dto.VehicleDto;
import com.transportexchangegroup.fm.entity.Order;
import com.transportexchangegroup.fm.entity.Vehicle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-06-21T12:13:41+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
public class DtoMapperImpl implements DtoMapper {

    @Override
    public VehicleDto vehicleDto(Vehicle source) {
        if ( source == null ) {
            return null;
        }

        VehicleDto vehicleDto = new VehicleDto();

        vehicleDto.setVehicleId( source.getVehicleId() );
        vehicleDto.setName( source.getName() );
        vehicleDto.setOrders( orderListToOrderDtoList( source.getOrders() ) );

        return vehicleDto;
    }

    protected OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId( order.getOrderId() );
        orderDto.setDate( order.getDate() );

        return orderDto;
    }

    protected List<OrderDto> orderListToOrderDtoList(List<Order> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDto> list1 = new ArrayList<OrderDto>( list.size() );
        for ( Order order : list ) {
            list1.add( orderToOrderDto( order ) );
        }

        return list1;
    }
}
