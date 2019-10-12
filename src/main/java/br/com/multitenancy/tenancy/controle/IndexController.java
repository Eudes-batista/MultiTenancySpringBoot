package br.com.multitenancy.tenancy.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin
public class IndexController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "login";
    }
    
    @RequestMapping(value = "/usuario",method = RequestMethod.GET)
    public String usuario() {
        return "usuario";
    }
   
    @RequestMapping(value = "/principal",method = RequestMethod.GET)
    public String principal() {
        return "principal";
    }
   
    @RequestMapping(value = "/entrada",method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    
    @RequestMapping(value = "/produtos",method = RequestMethod.GET)
    public String produto() {
        return "produto";
    }
   
    @RequestMapping(value = "/produto/novo",method = RequestMethod.GET)
    public String produtoNovo() {
        return "cadproduto";
    }
   
    @RequestMapping(value = "/produto/edit/{id}",method = RequestMethod.GET)
    public String produtoEdit() {
        return "editproduto";
    }
    
    @RequestMapping(value = "/estoque",method = RequestMethod.GET)
    public String estoque() {
        return "movimentacaoEstoque";
    }
   
    

}
