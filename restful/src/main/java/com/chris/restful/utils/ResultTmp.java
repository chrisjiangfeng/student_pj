package com.chris.restful.utils;

public class ResultTmp {
    private static final int SUCCESS=200;
    private static final String MOREN="无异常";

    public static<T> Result format(T t){
        return  new Result(t,SUCCESS,MOREN);
    }
}
