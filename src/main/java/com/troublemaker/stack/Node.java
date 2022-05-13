package com.troublemaker.stack;

import lombok.NoArgsConstructor;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.stack
 * @Author: troublemaker
 * @CreateTime: 2022-05-05  21:29
 * @Description: TODO
 * @Version: 1.0
 */
@NoArgsConstructor
public class Node<T> {
    public T t;
    public Node<T> next;

    public Node(T t) {
        this.t = t;
    }
}

