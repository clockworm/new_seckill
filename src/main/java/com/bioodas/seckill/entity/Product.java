package com.bioodas.seckill.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {
    private String id;
    private String productName;
    private String productTitle;
    private String productImg;
    private BigDecimal productPrice;
    private Integer productStock;
    private Date insertTime;
    private Date lastUpdateTime;
    private String productDetail;
}