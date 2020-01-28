package com.hexhoc.springbootblog.common.util;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public class PostResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int resultCode;
    private String message;
    private T data;

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    public static PostResponse genSuccessResult() {
        PostResponse postResponse = new PostResponse();
        postResponse.setResultCode(RESULT_CODE_SUCCESS);
        postResponse.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return postResponse;
    }

    public static PostResponse genSuccessResult(String message) {
        PostResponse postResponse = new PostResponse();
        postResponse.setResultCode(RESULT_CODE_SUCCESS);
        postResponse.setMessage(message);
        return postResponse;
    }

    public static PostResponse genSuccessResult(Object data) {
        PostResponse postResponse = new PostResponse();
        postResponse.setResultCode(RESULT_CODE_SUCCESS);
        postResponse.setMessage(DEFAULT_SUCCESS_MESSAGE);
        postResponse.setData(data);
        return postResponse;
    }

    public static PostResponse genFailResult(String message) {
        PostResponse postResponse = new PostResponse();
        postResponse.setResultCode(RESULT_CODE_SERVER_ERROR);
        if (StringUtils.isEmpty(message)) {
            postResponse.setMessage(DEFAULT_FAIL_MESSAGE);
        } else {
            postResponse.setMessage(message);
        }
        return postResponse;
    }

    public static PostResponse genErrorResult(int code, String message) {
        PostResponse postResponse = new PostResponse();
        postResponse.setResultCode(code);
        postResponse.setMessage(message);
        return postResponse;
    }

    ////////////////////////////
    //GETTER AND SETTER
    ////////////////////////////


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
