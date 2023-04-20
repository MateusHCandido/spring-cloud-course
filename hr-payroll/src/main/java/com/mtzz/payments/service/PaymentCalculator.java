package com.mtzz.payments.service;

import com.mtzz.domain.PaymentVerifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentCalculator
{
    public PaymentVerifier checkPaymentAmount(long workerId, int workedDays)
    {
        //mock return
        return new PaymentVerifier("Mateus", 300d, workedDays);
    }
}
