package com.troublemaker.hash;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.hash
 * @Author: troublemaker
 * @CreateTime: 2022-05-10  17:45
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class EmployeeLinkList {
    EmployeeNode head = new EmployeeNode();

    public Boolean isEmpty() {
        return head.next == null;
    }

    //是否可以添加
    //避免id重复
    public boolean isOnly(Employee employee) {
        if (searchByNo(employee.getNo()) == null) {
            return false;
        } else return searchByNo(employee.getNo()).size() != 0;
    }

    //增加员工节点
    //尾插法
    public void add(Employee employee) {
        if (isOnly(employee)) {
            throw new RuntimeException("no已存在");
        } else {
            EmployeeNode node = new EmployeeNode(employee);
            EmployeeNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    //按照id添加
    public void addByNO(Employee employee) {
        if (isOnly(employee)) {
            throw new RuntimeException("no已存在");
        } else {
            EmployeeNode node = new EmployeeNode(employee);
            EmployeeNode temp = head;
            if (!isEmpty()) {
                while (temp.next != null) {
                    if (temp.next.employee.getNo() > employee.getNo()) {
                        node.next = temp.next;
                        temp.next = node;
                        return;
                    } else {
                        temp = temp.next;
                    }
                }
            }
            temp.next = node;
        }
    }

    //删除员工节点
    public void deleteByNo(int no) {
        if (isEmpty()) {
            throw new RuntimeException("链表为空");
        } else {
            EmployeeNode temp = head;
            while (temp.next != null) {
                if (temp.next.employee.getNo() == no) {
                    temp.next = temp.next.next;
                    return;
                } else {
                    temp = temp.next;
                }
            }
            throw new RuntimeException("没有这个人");
        }
    }

    //修改员工节点
    public void modify(Employee employee) {
        if (isEmpty()) {
            throw new RuntimeException("链表为空");
        } else {
            EmployeeNode temp = head;
            while (temp.next != null) {
                if (!temp.next.employee.getNo().equals(employee.getNo())) {
                    temp = temp.next;
                } else {
                    temp.next.employee.setName(employee.getName());
                    temp.next.employee.setPosition(employee.getPosition());
                    return;
                }
            }
            throw new RuntimeException("没有这个人");
        }
    }

    //查找员工
    public List<Employee> searchByNo(int no) {
        ArrayList<Employee> list = new ArrayList<>();
        if (!isEmpty()) {
            EmployeeNode temp = head;
            while (temp.next != null) {
                if (temp.next.employee.getNo().equals(no)) {
                    list.add(temp.next.employee);
                }
                temp = temp.next;
            }
            if (list.size() > 0) {
                return list;
            } else
                return null;
        } else return null;
    }

    //展示员工
    public void employeeList() {
        EmployeeNode temp = head;
        if (isEmpty()) {
            throw new RuntimeException("链表为空");
        } else {
            while (temp.next != null) {
                System.out.print(temp.next.employee + "\t");
                temp = temp.next;
            }
            System.out.println();
        }
    }
}

