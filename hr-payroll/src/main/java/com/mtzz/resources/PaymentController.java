package com.mtzz.resources;

import com.mtzz.domain.PaymentVerifier;
import com.mtzz.payments.service.PaymentCalculator;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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


    @HystrixCommand(fallbackMethod = "getCheckWorkerPayAlternative")
    @GetMapping(path = "/{workerId}/worker-days/{workerDays}")
    public ResponseEntity<PaymentVerifier> checkWorkerPay(@PathVariable Long workerId, @PathVariable Integer workerDays)
    {
        PaymentVerifier workerPay = paymentCalculator.checkPaymentDetails(workerId, workerDays);
        return ResponseEntity.ok(workerPay);
    }

    public ResponseEntity<PaymentVerifier> getCheckWorkerPayAlternative(Long workerId, Integer workerDays)
    {
        PaymentVerifier workerPay = new PaymentVerifier("Time out", 0d, workerDays);
        return ResponseEntity.ok(workerPay);
    }
}
