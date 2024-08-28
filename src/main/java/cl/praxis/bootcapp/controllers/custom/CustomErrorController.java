package cl.praxis.bootcapp.controllers.custom;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == 404) {
                model.addAttribute("statusCode", "4");
                model.addAttribute("errorMessage", "La página que estás buscando no existe");
                return "error404";
            }

            if (statusCode == 403) {
                model.addAttribute("statusCode", "3");
                model.addAttribute("errorMessage", "No tienes permisos para acceder a este recurso.");
                return "error404";
            }

            if (statusCode == 401) {
                model.addAttribute("statusCode", "1");
                model.addAttribute("errorMessage", "No estás autenticado. Por favor, inicia sesión.");
                return "error404";
            }
        }

        model.addAttribute("statusCode", "4");
        model.addAttribute("errorMessage", "La página que estás buscando no existe");
        return "error404";
    }
}
