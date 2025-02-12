package empresa.com.ProjetoAutoPecas.HelloServices;

import org.springframework.stereotype.Service;

@Service
public class helloservices {
    public String helloworld(String name){
        return "Hello Mundo " + name;
    }
}
