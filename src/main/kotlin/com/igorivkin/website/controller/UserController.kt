package com.igorivkin.website.controller

import com.igorivkin.website.converter.UserConverter
import com.igorivkin.website.model.User
import com.igorivkin.website.security.LocalUserDetails
import org.mapstruct.factory.Mappers
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/account")
@SessionAttributes(names = ["currentUser"])
class UserController {

    private val userConverter: UserConverter = Mappers.getMapper(UserConverter::class.java)

    @RequestMapping("/login")
    fun processLogin(model: Model, request: HttpServletRequest): String {
        return "account/login-main"
    }

    @RequestMapping("/loginFailed")
    fun loginError(model: Model): String {
        model.addAttribute("loginError", true)
        return "account/login-main"
    }

    @GetMapping(value = ["/logout"])
    fun logout(session: SessionStatus): String {
        SecurityContextHolder.getContext().authentication = null
        session.setComplete()
        return "redirect:/"
    }

    @PostMapping("/postLogin")
    fun postLogin(model: Model): String {
        val authentication: UsernamePasswordAuthenticationToken =
            SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
        validatePrincipal(authentication.principal)
        val loggedInUser: User = (authentication.principal as LocalUserDetails).getUserDetails()
        model.addAttribute("currentUser", userConverter.toDto(loggedInUser))
        return "redirect:/admin/"
    }

    private fun validatePrincipal(principal: Any) {
        if(principal !is LocalUserDetails) {
            throw IllegalArgumentException("Principal should be an instance of class LocalUserDetails!")
        }
    }

}