package com.troublemaker.homework;


import com.troublemaker.stack.LinkStack;


/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.homework
 * @Author: troublemaker
 * @CreateTime: 2022-05-05  19:57
 * @Description: TODO
 * @Version: 1.0
 */
public class Calculator {
    //数栈
    private final LinkStack<Integer> numStack = new LinkStack<>();
    //符号栈
    private final LinkStack<String> operStack = new LinkStack<>();

    private StringBuffer num = new StringBuffer();


    //表达式入栈
    private void push(String str) {
        int count = 0;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {

            if (isNum(aChar)) {
                num.append(aChar);
                if (count == chars.length - 1) {
                    numStack.push(Integer.valueOf(num.toString()));
                }
            } else {
                numStack.push(Integer.valueOf(num.toString()));
                num.delete(0, num.length());
                if (operStack.isEmpty()) {
                    operStack.push(String.valueOf(aChar));
                } else {
                    while (!operStack.isEmpty()) {
                        //判断符号优先级
                        if (priority(String.valueOf(aChar), operStack.getElement().t)) {
                            operStack.push(String.valueOf(aChar));
                            break;
                        } else {
                            //先出栈
                            numStack.push(calculate(numStack.pop(), numStack.pop(), operStack.pop()));
                        }
                    }
                    if (operStack.isEmpty()) {
                        operStack.push(String.valueOf(aChar));
                    }
                }
            }
            count++;
        }
    }

    //表达式计算
    public int doCalculator(String str) {
        push(str);
        while (!operStack.isEmpty()) {
            String oper = operStack.pop();
            Integer n1 = numStack.pop();
            Integer n2 = numStack.pop();
            int num = calculate(n1, n2, oper);
            numStack.push(num);
        }
        return numStack.pop();
    }

    //分离表达式中的数字和字符
    private boolean isNum(char str) {
        return 47 < (int) str && (int) str < 58;
    }

    //判断优先级
    private boolean priority(String str1, String str2) {
        if (str1.equals("*") || str1.equals("/")) {
            return !str2.equals("*") && !str2.equals("/");
        } else if (str1.equals("+") || str1.equals("-")) {
            return !str2.equals("*") && !str2.equals("/");
        } else throw new RuntimeException("错误的操作符");
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

    public static void main(String[] args) {
        String str = "7*2*2-5+1-5+3";
        Calculator calculator = new Calculator();
        System.out.println(calculator.doCalculator(str));
    }
}


