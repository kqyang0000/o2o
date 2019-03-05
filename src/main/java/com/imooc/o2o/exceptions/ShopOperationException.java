package com.imooc.o2o.exceptions;

public class ShopOperationException extends RuntimeException {

    private static final long serialVersionUID = 3142323989063376323L;

    public ShopOperationException(String msg) {
        super(msg);
    }
}
