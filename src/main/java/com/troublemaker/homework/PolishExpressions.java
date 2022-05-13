package com.troublemaker.homework;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.homework
 * @Author: troublemaker
 * @CreateTime: 2022-05-06  17:07
 * @Description: TODO
 * @Version: 1.0
 */
public class PolishExpressions {
    //准备一个队列和栈
    //队列用来存放表达式，栈用来计算
    private final Stack<Integer> stack = new Stack<>();

    private final Queue<String> queue = new LinkedList<>();

    private void strToQueue(String str) {
        String[] strs = str.split(" ");
        queue.addAll(Arrays.asList(strs));
    }

    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    private int calculate(int n1, int n2, String oper) {
        switch (oper) {
            case "*":
                return n1 * n2;
            case "/":
                return n1 / n2;
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            default:
                throw new RuntimeException("运算符错误");
        }
    }

    public int doCalculate(String str) {
        strToQueue(str);
        while (!queue.isEmpty()) {
            String s = queue.remove();
            if (!isOperator(s)) {
                stack.push(Integer.valueOf(s));
            } else {
                Integer n1 = stack.pop();
                Integer n2 = stack.pop();
                stack.push(calculate(n2, n1, s));
            }
        }
        return stack.pop();
    }


}


