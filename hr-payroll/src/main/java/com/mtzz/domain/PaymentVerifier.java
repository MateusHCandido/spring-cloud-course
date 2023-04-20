package com.mtzz.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVerifier
{
    private String workerName;
    private Double dailyIncome;
    private Integer days;
}
