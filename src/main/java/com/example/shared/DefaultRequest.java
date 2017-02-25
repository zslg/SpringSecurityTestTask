package com.example.shared;

import com.fasterxml.jackson.annotation.JsonUnwrapped;


public class DefaultRequest<E> {

    @JsonUnwrapped
    private E request;

    public DefaultRequest(){

    }

    public E getRequest() {
        return request;
    }

    public void setRequest(E request) {
        this.request = request;
    }
}
