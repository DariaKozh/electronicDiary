package com.sberbank.may.logincontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "home_pages/login";
    }

    @GetMapping("/login/success")
    public String loginSuccess(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role;
        if (auth != null) {
            role = auth.getAuthorities().iterator().next().getAuthority();
            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin";
            } else if (role.equals("ROLE_TEACHER")) {
                return "redirect:/teacher";
            } else if (role.equals("ROLE_PARENT")) {
                return "redirect:/parent";
            }
        }
        return "redirect:/login?error";
    }
}


