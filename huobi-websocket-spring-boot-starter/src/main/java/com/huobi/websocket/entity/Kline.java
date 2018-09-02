package com.huobi.websocket.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kyle.zeng
 * @date 2017/12/28 15:21
 */
@Data
public class Kline {
    private int id;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal amount;
    private BigDecimal vol;
    private int count;
}
