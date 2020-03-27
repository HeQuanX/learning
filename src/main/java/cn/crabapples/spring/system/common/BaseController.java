package cn.crabapples.spring.system.common;

import cn.crabapples.spring.system.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * TODO 基础Controller，其他controller请继承此类
 *
 * @author Mr.He
 * 2019/9/21 18:28
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public abstract class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private Validator validator;

    /**
     * 属性校验的方法
     * @param object 需要验证的对象
     */
    protected final void validator(Object object, Class<?>... groups){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object,groups);
        for (ConstraintViolation constraintViolation : constraintViolations ) {
           throw new ApplicationException(constraintViolation.getMessageTemplate());
        }
    }

    @ExceptionHandler
    @ResponseBody
    protected ResponseDTO exceptionHandler(Exception e){
        logger.error("出现异常:[{}]",e.getMessage());
        return ResponseDTO.returnError("操作失败",e.getMessage());
    }
}
