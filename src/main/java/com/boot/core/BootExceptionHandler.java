package com.boot.core;

import com.boot.bean.base.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;


/**
 * Created by ChenXiang on 2018/01/05.
 * @处理所有抛出的异常，系统中所有的异常，从dao到service最后到controller，最后都会抛出到controller层
 * @这里是spring自带的controller增强，配置后，所有异常根据配置的种类由这里统一处理
 * @所有BusiException异常，会返回结果为抛出时传入的errorcode和errormessage，最后经过spring的messageconvert转换成json格式返回
 * @所有的系统异常Exception，会打印报错信息后抛出resultcode为（1000，系统错误）的信息，成json格式返回
 */
@ControllerAdvice
public class BootExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BootExceptionHandler.class);

    /**
     * 业务异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(BusiException.class)
    @ResponseBody
    @ResponseStatus( HttpStatus.UNPROCESSABLE_ENTITY)
    public HttpResult busiExceptionCaught(BusiException e, final WebRequest req) {
        HttpResult result = new HttpResult();
        if (e.getErrCode() != null) {
            logger.error(e.getErrCode().getMessage(), e);
            result.setErrorCode(e.getErrCode().getCode());
            result.setErrorMessage(e.getErrCode().getMessage());
        }else {
            logger.error(e.getErrorMessage(), e);
            result.setErrorCode(e.getErrorCode());
            result.setErrorMessage(e.getErrorMessage());
        }

        return result;
    }

    /**
     * 系统异常处理
     * @param
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus( HttpStatus.UNPROCESSABLE_ENTITY )
    public HttpResult exceptionCaught(Exception e) {
        HttpResult result = new HttpResult();
        logger.error(e.getMessage(),e);
        result.setErrorCode(ResultCode.SYSTEM_ERROR.getCode());
        result.setErrorMessage(ResultCode.SYSTEM_ERROR.getMessage());
        return result;
    }
}
