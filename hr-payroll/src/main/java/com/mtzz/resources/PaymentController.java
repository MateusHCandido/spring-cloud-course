package com.mtzz.resources;

import com.mtzz.domain.PaymentVerifier;
import com.mtzz.payments.service.PaymentCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController
{
    @Autowired
    private PaymentCalculator paymentCalculator;


    @GetMapping(path = "/{workerId}/worker-days/{workerDays}")
    public ResponseEntity<PaymentVerifier> checkWorkerPay(@PathVariable Long workerId, @PathVariable Integer workerDays)
    {
        PaymentVerifier workerPay = paymentCalculator.checkPaymentAmount(workerId, workerDays);
        return ResponseEntity.ok(workerPay);
    }
}
