package com.mtzz.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVerifier
{
    private String nameOfWorker;
    private Double dailyIncome;
    private Integer workedDays;

    public double getTotalReceivable()
    {
        return dailyIncome * workedDays;
    }
}
