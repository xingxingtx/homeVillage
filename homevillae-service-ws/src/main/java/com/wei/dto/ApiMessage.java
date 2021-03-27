package com.wei.dto;

import com.wei.utils.annotation.Excel;

/**
 * @Describe ApiMessage
 * @Author a_pen
 * @Date 2020年09月09日
 */
public class ApiMessage {
    /**类名*/
    @Excel(name = "类名")
    private String className;
    /**api路径*/
    @Excel(name = "api路径")
    private String apiUrl;
    /**请求方法名*/
    @Excel(name = "请求方法名")
    private String method;
    /**请求方法类型：{GET,PUT,DELETE,POST..}*/
    @Excel(name = "请求方法类型", combo = {"GET","PUT","DELETE","POST","HEAD","PATCH","OPTIONS","TRACE"})
    private String methodType;
    @Excel(name = "描述")
    private String describe;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "ApiMessage{" +
                "className='" + className + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", method='" + method + '\'' +
                ", methodType='" + methodType + '\'' +
                '}';
    }
}
