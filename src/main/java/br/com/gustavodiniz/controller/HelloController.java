package br.com.gustavodiniz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavodiniz.facade.PosicaoFacade;
import br.com.gustavodiniz.vo.Pessoa;
import br.com.logicx.to.FiltroObjetosTO;
import br.com.logicx.vo.FiltroObjetosVO;
import br.com.logicx.vo.PosicaoSensorVOList;

@RestController
public class HelloController {

	@Autowired
	private PosicaoFacade posicaoFacade;

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

	@RequestMapping(value = "/atual", method = RequestMethod.GET, headers = "Accept=application/json")
	public PosicaoSensorVOList exemplo() {
		FiltroObjetosTO to = posicaoFacade.validaFiltroObjetos(new FiltroObjetosVO());
		PosicaoSensorVOList vo = posicaoFacade.buscaPosicaoSensor(to);
		return vo;
	}

}
