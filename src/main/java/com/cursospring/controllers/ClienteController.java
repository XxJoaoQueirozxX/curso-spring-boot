package com.cursospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @GetMapping(value={"/hello/{nome}", "/ola/{nome}"})
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCliente){
        return "Hello " + nomeCliente;
    }

}
