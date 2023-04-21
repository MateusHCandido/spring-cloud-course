package com.mtzz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerWorker
{
    private Long workerId;
    private String nameOfWorker;
    private Double dailyIncome;

}
