package com.mtzz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorker
{
    private Long workerId;
    private String nameOfWorker;
    private Double dailyIncome;

}
