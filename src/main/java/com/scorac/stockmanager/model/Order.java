package com.scorac.stockmanager.model;

import lombok.Data;

import java.util.List;
@Data
    public class Order {
        private int orderId;
        private List<OrderLine> orderLines;

        public Order(int orderId, List<OrderLine> orderLines) {
            this.orderId = orderId;
            this.orderLines = orderLines;
        }



    }
