package com.troublemaker.hash;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.hash
 * @Author: troublemaker
 * @CreateTime: 2022-05-10  17:44
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class HashTab {
    private EmployeeLinkList[] employeeLinkLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        this.employeeLinkLists = new EmployeeLinkList[size];
        for (int i = 0; i < size; i++) {
            employeeLinkLists[i] = new EmployeeLinkList();
        }
    }

    public int hashFun(Employee employee) {
        return employee.getNo() % size;
    }

    public int hashFun(int no) {
        return no % size;
    }

    public void add(Employee employee) {
        employeeLinkLists[hashFun(employee)].addByNO(employee);
    }

    public void deleteByNO(int no) {
        employeeLinkLists[hashFun(no)].deleteByNo(no);
    }

    public void modify(Employee employee) {
        employeeLinkLists[hashFun(employee)].modify(employee);
    }

    public List<Employee> searchByNo(int no) {
        if (employeeLinkLists[hashFun(no)].searchByNo(no) != null) {
            return employeeLinkLists[hashFun(no)].searchByNo(no);
        } else {
            throw new RuntimeException("没有这个人");
        }

    }

    public void list() {
        for (int i = 0; i < employeeLinkLists.length; i++) {
            EmployeeLinkList employeeLinkList = employeeLinkLists[i];
            if (!employeeLinkList.isEmpty()) {
                System.out.print("第" + i + "链表数据: ");
                employeeLinkList.employeeList();
            }
        }
    }

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        hashTab.add(new Employee(1100, "王海", "普工"));
//        hashTab.add(new Employee(1100,"李乾","普工"));
        hashTab.add(new Employee(1132, "王武", "普工"));
        hashTab.add(new Employee(1158, "段登封", "普工"));
        hashTab.add(new Employee(1165, "尹雪松", "普工"));
        hashTab.add(new Employee(1102, "王武", "普工"));
        hashTab.add(new Employee(1002, "李四", "组长"));
        hashTab.add(new Employee(1001, "张三", "经理"));
        System.out.println("----------------addByNo------------------");
        hashTab.list();
        hashTab.deleteByNO(1165);
        hashTab.deleteByNO(1132);
        System.out.println("----------------delete------------------");
        hashTab.list();
        hashTab.modify(new Employee(1158, "广西", "CEO"));
        System.out.println("----------------modify------------------");
        hashTab.list();
        List<Employee> employees = hashTab.searchByNo(1158);
        System.out.println("----------------searchByNo------------------");
        employees.forEach(System.out::println);

    }
}

