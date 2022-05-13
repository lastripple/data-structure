package com.troublemaker.hash;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.hash
 * @Author: troublemaker
 * @CreateTime: 2022-05-10  17:48
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class EmployeeNode {
    Employee employee;
    EmployeeNode next;

    public EmployeeNode(Employee employee) {
        this.employee = employee;
    }
}

