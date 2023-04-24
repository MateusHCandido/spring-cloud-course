package com.mtzz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessPermission
{
    @EqualsAndHashCode.Include
    private Long roleId;
    private String permissionType;
}
