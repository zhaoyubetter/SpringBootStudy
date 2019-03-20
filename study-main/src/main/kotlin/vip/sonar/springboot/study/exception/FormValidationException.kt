package vip.sonar.springboot.study.exception

import org.springframework.validation.BindingResult
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


/**
 * @Description:
 * @author zhaoyu1
 * @date 2019/3/20 4:18 PM
 */

@ResponseStatus(HttpStatus.BAD_REQUEST) // 返回  400
class FormValidationException(val result: BindingResult?) : RuntimeException()