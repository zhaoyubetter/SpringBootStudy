package vip.sonar.springboot.study.controller

import java.util.HashMap
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ValidationException


/**
 * @Description: 全局切面异常处理
 * @author zhaoyu1
 * @date 2019/3/20 4:16 PM
 */
@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationExceptionHandler(exception: ValidationException): Map<String, String> {
        val map = HashMap<String, String>()
        map["message"] = exception.message ?: ""
        return map
    }
}