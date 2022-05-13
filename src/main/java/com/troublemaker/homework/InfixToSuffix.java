package com.troublemaker.homework;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.homework
 * @Author: troublemaker
 * @CreateTime: 2022-05-06  19:34
 * @Description: TODO
 * @Version: 1.0
 */
public class InfixToSuffix {

    private final Stack<String> stack = new Stack<>();
    private final Queue<String> resultQueue = new LinkedList<>();
    private final Queue<String> strQueue = new LinkedList<>();
    private final String str;

    public InfixToSuffix(String str) {
        this.str = str;
    }

    private void toStrQueue() {
        strQueue.addAll(Arrays.asList(str.split(" ")));
    }

    private int priority(String str) {
        switch (str) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            case "(":
                return -1;
            case ")":
                return -2;
            default:
                throw new RuntimeException("运算符错误");
        }
    }

    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("(") || str.equals(")");
    }

    public Queue<String> toSuffix() {
        toStrQueue();
        while (!strQueue.isEmpty()) {
            //出队
            String s = strQueue.remove();
            //非运算符直接入队
            if (!isOperator(s)) {
                resultQueue.add(s);
            } else {
                //"("
                if (priority(s) == -1) {
                    stack.push(s);
                }
                //")"
                else if (priority(s) == -2) {
                    //寻找"("
                    while (true) {
                        if (!stack.peek().equals("(")) {
                            resultQueue.add(stack.pop());
                        } else {
                            //"("出栈
                            stack.pop();
                            break;
                        }
                    }
                }
                //运算符
                else {
                    //栈中无字符
                    if (stack.isEmpty()) {
                        stack.add(s);
                    } else {
                        //判断和栈中元素的优先级
                        while (true) {
                            //字符大于栈中优先级
                            if (priority(s) > priority(stack.peek())) {
                                stack.push(s);
                                break;
                            } else {
                                resultQueue.add(stack.pop());
                                if (stack.isEmpty()) {
                                    stack.push(s);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            resultQueue.add(stack.pop());
        }
        return resultQueue;
    }

    public static void main(String[] args) {
        InfixToSuffix toSuffix = new InfixToSuffix("1 + ( ( 2 + 3 ) * 4 ) - 5");
        Queue<String> queue = toSuffix.toSuffix();
        while (!queue.isEmpty()) {
            String s = queue.remove();
            System.out.printf("%s\t", s);
        }
    }

}

