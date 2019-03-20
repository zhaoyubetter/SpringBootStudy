package vip.sonar.springboot.study.controller

import org.apache.commons.lang3.math.NumberUtils
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.bind.BindResult
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import vip.sonar.springboot.study.controller.request.NewCoffeeRequest
import vip.sonar.springboot.study.domain.Coffee
import vip.sonar.springboot.study.exception.FormValidationException
import vip.sonar.springboot.study.service.CoffeeService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Error
import javax.validation.Valid
import javax.validation.ValidationException

/**
 * @description: CoffeeController
 * @author better
 * @date 2019-03-16 14:31
 */
@Controller
@RequestMapping("/coffee")
class CoffeeController {

    @Autowired
    lateinit var coffeeService: CoffeeService

    @RequestMapping(path = ["/"], method = [RequestMethod.GET],
            params = ["!name"],  // TODO: !name 此个配置无效
            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ResponseBody
    fun getAll(@RequestHeader("Accept") accept: String): List<Coffee> {
        return coffeeService.getAll()
    }

    /**
     * 自己处理错误
     */
    @PostMapping(path = ["/"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun addCoffee(@Valid newCoffeeRequest: NewCoffeeRequest, result: BindingResult): Coffee? {
        if (result.hasErrors()) {
            throw FormValidationException(result)
        }
        return coffeeService.saveCoffee(newCoffeeRequest.name, newCoffeeRequest.price)
    }


    @PostMapping(path = ["/"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun addCoffee2(@Valid newCoffeeRequest: NewCoffeeRequest, result: BindingResult): Coffee? {
        if (result.hasErrors()) {
            throw ValidationException(result.toString())
        }
        return coffeeService.saveCoffee(newCoffeeRequest.name, newCoffeeRequest.price!!)
    }

    /**
     * 上传单个文件
     */
    @PostMapping("/", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun batchAddCoffee(@RequestParam("file") file: MultipartFile): List<Coffee> {
        val coffees = mutableListOf<Coffee>()
        if (file != null) {
            BufferedReader(InputStreamReader(file.inputStream)).apply {
                this.readLines().forEach { line ->
                    line.split(" ").let {
                        if (it.size == 2) {
                            coffees.add(coffeeService.saveCoffee(it[0],
                                    Money.of(CurrencyUnit.of("CNY"),
                                            NumberUtils.createBigDecimal(it[1]))))
                        }
                    }
                }
                this.close()
            }
        }

        return coffees
    }

    ///////  指定JSON格式 ////////
    @PostMapping(path = ["/"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun addJsonCoffeeWithoutBindingResult(@Valid @RequestBody
                                          newCoffeeRequest: NewCoffeeRequest): Coffee? {
        return coffeeService.saveCoffee(newCoffeeRequest.name, newCoffeeRequest.price!!)
    }

    @GetMapping(path = ["/{name}"])
    @ResponseBody
    fun getByName(@PathVariable("name") name: String): List<Coffee>? {
        return coffeeService.getCoffeeByName(name)
    }
}