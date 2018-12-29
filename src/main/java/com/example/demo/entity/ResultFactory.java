package com.example.demo.entity;

import com.example.demo.constants.DemoException;
import com.example.demo.constants.ReturnCodeEnum;

/**
 * @author lij381
 * @date 2018/12/29 16:30
 * @description
 */
public class ResultFactory {

    public static ResultEntity getResultEntity(ReturnCodeEnum exc) {
        ResultEntity entity = new ResultEntity();
        entity.setCode(exc.getCode());
        entity.setDesc(exc.getDesc());
        return entity;
    }

    public static ResultEntity getResultEntity(String code, String msg) {
        ResultEntity entity = new ResultEntity();
        entity.setCode(code);
        entity.setDesc(msg);
        return entity;
    }

    public static ResultEntity getResultEntity(DemoException e) {
        ResultEntity entity = new ResultEntity();
        entity.setCode(e.getErrCode());
        entity.setDesc(e.getMessage());
        return entity;
    }

}
