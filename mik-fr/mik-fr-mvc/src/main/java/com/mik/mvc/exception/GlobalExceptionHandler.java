package com.mik.mvc.exception;

import com.mik.core.exception.ServiceException;
import com.mik.core.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ServiceException.class)
    public Object serviceExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("业务异常", ex);
        Result result = new Result();
        result.setCode(1);
        result.setMsg(ex.getMessage());
        return result;
    }


    @ExceptionHandler(Exception.class)
    public Object undeclaredThrowableExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("未处理异常", ex);
        Result result = new Result();
        result.setCode(666);
        result.setMsg(ex.getMessage());
        return result;
    }

}
