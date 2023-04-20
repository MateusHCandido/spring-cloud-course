package com.mtzz.payments.service;

import com.mtzz.domain.PaymentVerifier;
import com.mtzz.domain.EmployerWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentCalculator
{
    @Value("${hr-worker.host}")
    private String workerModuleHost;

    @Autowired
    private RestTemplate restTemplate;


    public PaymentVerifier checkPaymentAmount(long workerId, int workedDays)
    {
        Map<String, String> workerIdStorage = mapWorkerId(workerId);
        EmployerWorker workerInformation = generateWorkerRestTemplate(workerIdStorage);

        return new PaymentVerifier(workerInformation.getNameOfWorker(), workerInformation.getDailyIncome(), workedDays);
    }

    public Map<String, String> mapWorkerId(long workerId)
    {
        Map<String,String> searchedId = new HashMap<>();
        searchedId.put("workerId", String.valueOf(workerId));
        return searchedId;
    }

    public EmployerWorker generateWorkerRestTemplate(Map<String, String> workerIds)
    {
        return restTemplate.getForObject(workerModuleHost + "/workers/{workerId}",
                EmployerWorker.class, workerIds);
    }
}
