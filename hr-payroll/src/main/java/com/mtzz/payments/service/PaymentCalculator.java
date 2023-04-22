package com.mtzz.payments.service;

import com.mtzz.domain.PaymentVerifier;
import com.mtzz.domain.EmployeeWorker;
import com.mtzz.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PaymentCalculator
{
    @Autowired
    private WorkerFeignClient workerFeignClient;


    public PaymentVerifier checkPaymentDetails(long workerId, int workedDays)
    {
        EmployeeWorker workerInformation = generateWorkerFeignClientBy(workerId);

        return new PaymentVerifier(workerInformation.getNameOfWorker(), workerInformation.getDailyIncome(), workedDays);
    }

    public EmployeeWorker generateWorkerFeignClientBy(long workerId)
    {
        return workerFeignClient.findWorkerById(workerId).getBody();
    }
}
