package empresa.com.ProjetoAutoPecas.HelloController;

import empresa.com.ProjetoAutoPecas.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/helloworld")
public class hellocontroller {
    @Autowired
    empresa.com.ProjetoAutoPecas.HelloServices.helloservices helloservices;

    @GetMapping
    public String hello() {
        return helloservices.helloworld("Yururi");
    }

    @PostMapping("/{id}")
    public String helloPost(@PathVariable("id") String id, @RequestParam(value ="filter", defaultValue = "nenhum ou vazio") String filter,@RequestBody User body) {
        return "Olá " + " " + body.getName()+ " " + id + " " + filter;
    }
}
