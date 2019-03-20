package vip.sonar.springboot.study.controller

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.method.HandlerMethod
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.web.servlet.ModelAndView



/**
 * @Description: 记录方法执行时间
 * @author zhaoyu1
 * @date 2019/3/20 5:16 PM
 */
class PerformanceInteceptor : HandlerInterceptor {

    private val stopWatch = ThreadLocal<StopWatch>()
    private val log = LoggerFactory.getLogger(PerformanceInteceptor::class.java)

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?): Boolean {
        val sw = StopWatch()
        stopWatch.set(sw)
        sw.start()
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
        stopWatch.get().stop()
        stopWatch.get().start()
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any, ex: Exception?) {
        val sw = stopWatch.get()
        sw.stop()
        var method = handler.javaClass.simpleName
        if (handler is HandlerMethod) {
            val beanType = handler.beanType.name
            val methodName = handler.method.name
            method = "$beanType.$methodName"
        }
        log.info("{};{};{};{};{}ms;{}ms;{}ms", request!!.requestURI, method,
                response!!.status, if (ex == null) "-" else ex.javaClass.simpleName,
                sw.getTotalTimeMillis(), sw.getTotalTimeMillis() - sw.getLastTaskTimeMillis(),
                sw.getLastTaskTimeMillis())
        stopWatch.remove()
    }
}