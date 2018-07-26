package br.com.gustavodiniz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavodiniz.vo.Pessoa;

@RestController
public class HelloController {



	@RequestMapping(value = "/pessoa", method = RequestMethod.GET, headers = "Accept=application/json")
	public Pessoa consultaPessoa() {

		Pessoa p = new Pessoa();
		p.setNome("Gustavo Diniz");
		p.setIdade(32);
		p.setEmail("gdinsantos1@gmail.com");

		return p;
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.GET, headers = "Accept=application/json")
	public Pessoa cliente() {

		Pessoa p = new Pessoa();
		p.setNome("Gustavo Diniz");
		p.setIdade(32);
		p.setEmail("gdinsantos1@gmail.com");

		return p;
	}

	

}
