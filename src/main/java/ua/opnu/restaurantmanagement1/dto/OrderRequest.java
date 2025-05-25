package ua.opnu.restaurantmanagement1.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long customerId;
    private Long waiterId;
}

