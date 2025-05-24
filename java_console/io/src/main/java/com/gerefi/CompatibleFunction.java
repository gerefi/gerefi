package com.gerefi;

/**
 * reducing Android level down from 24
 */
public interface CompatibleFunction<T, R> {
    R apply(T t);
}
