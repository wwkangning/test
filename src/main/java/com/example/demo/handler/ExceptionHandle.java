package com.example.demo.handler;

import com.example.demo.constants.DemoException;
import com.example.demo.constants.ReturnCodeEnum;
import com.example.demo.entity.ResultEntity;
import com.example.demo.entity.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lij381
 * @date 2018/8/21 21:12
 * @description
 */
@RestControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(Exception.class)
    public ResultEntity handleException(Exception e){
        logger.error("系统异常：",e);
        return ResultFactory.getResultEntity(ReturnCodeEnum.SYS_ERROR);
    }

    @ExceptionHandler(DemoException.class)
    public ResultEntity handleCbpSysException(DemoException e){
        logger.error("系统错误：{}", e.getMessage());
        return ResultFactory.getResultEntity(e);
    }
    @ExceptionHandler(BindException.class)
    public ResultEntity handleBindException(BindException e){
        String defaultMessage = e.getAllErrors().get(0).getDefaultMessage();
        logger.info("BindException：{}", defaultMessage);
        String code = ReturnCodeEnum.PARAM_ERROR.getCode();
        String desc = String.format(ReturnCodeEnum.PARAM_ERROR.getDesc(), defaultMessage);
        return ResultFactory.getResultEntity(code, desc);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        logger.info("MethodArgumentNotValidException：{}", defaultMessage);
        String code = ReturnCodeEnum.PARAM_ERROR.getCode();
        String desc = String.format(ReturnCodeEnum.PARAM_ERROR.getDesc(), defaultMessage);
        return ResultFactory.getResultEntity(code, desc);
    }

}
