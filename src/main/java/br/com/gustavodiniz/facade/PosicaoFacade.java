package br.com.gustavodiniz.facade;

import br.com.logicx.domain.HistoricoSensor;
import br.com.logicx.model.PosicaoSensorModel;
import br.com.logicx.service.PosicaoSensorService;
import br.com.logicx.to.FiltroObjetosTO;
import br.com.logicx.to.HistoricoPosicaoTO;
import br.com.logicx.to.PosicaoSensorTO;
import br.com.logicx.vo.FiltroObjetosVO;
import br.com.logicx.vo.PosicaoSensorVO;
import br.com.logicx.vo.PosicaoSensorVOList;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class PosicaoFacade {
	
	private static final String LIGADO = "Ligado";
	private static final String DESLIGADO = "Desligado";
	
	@Autowired
	private PosicaoSensorService posicaoSensorService;
	
	public FiltroObjetosTO validaFiltroObjetos(FiltroObjetosVO filtroObjetosVO) {
		FiltroObjetosTO to = new FiltroObjetosTO();
		to.setPage(filtroObjetosVO.getPage());
		to.setPageSize(filtroObjetosVO.getPageSize());
		
		if (LIGADO.equals(filtroObjetosVO.getFiltrosIgnicao())) {
			to.setFiltrosIgnicao(1);
		} else if (DESLIGADO.equals(filtroObjetosVO.getFiltrosIgnicao())) {
			to.setFiltrosIgnicao(0);
		} else {
			to.setFiltrosIgnicao(null);
		}
		return to;
	}
	
	public PosicaoSensorVOList buscaPosicaoSensor(FiltroObjetosTO to) {
		
		PosicaoSensorTO posicao = posicaoSensorService.buscaPosicaoSensor(to);
		
		List<PosicaoSensorVO> posicaoVO = new ArrayList<PosicaoSensorVO>();
		for (PosicaoSensorModel ps : posicao.getPosicaoSensor()) {
			posicaoVO.add(new PosicaoSensorVO(ps));
		}
		
		PosicaoSensorVOList vo = new PosicaoSensorVOList(posicaoVO);
		return vo;
	}
	
	public Long buscaQtdPosicaoSensor(FiltroObjetosTO to) {
		return posicaoSensorService.buscaQtdPosicaoSensor(to);
	}
	
	public PosicaoSensorVO consultaPosicaoAtualPorIdModulo(long idModulo) {
		final PosicaoSensorTO posicao = posicaoSensorService.buscaPosicaoSensorPorIdSensor(idModulo);
		final List<PosicaoSensorModel> posicaoSensorLista = posicao.getPosicaoSensor();
		
		if (posicaoSensorLista == null || posicaoSensorLista.isEmpty() || posicaoSensorLista.get(0) == null) {
			return null;
		}
		
		return new PosicaoSensorVO(posicaoSensorLista.get(0));
	}
	
	public HistoricoSensor validaHistoricoSensor(String historicoJson) throws ParseException {
		HistoricoSensor sensor = new Gson().fromJson(historicoJson, HistoricoSensor.class);
		
		if (sensor.getPage() == null || sensor.getPage() <= 0) {
			sensor.setPage(1);
		}
		
		if (sensor.getPageSize() == null || sensor.getPageSize() <= 0) {
			sensor.setPageSize(100);
		}
		
		sensor.setDataInicial(new SimpleDateFormat("dd/MM/yyyy").parse(sensor.getDtInicio()));
		sensor.setDataFinal(new SimpleDateFormat("dd/MM/yyyy").parse(sensor.getDtFim()));
		return sensor;
	}
	
	public HistoricoPosicaoTO buscaHistoricoPosicao(HistoricoSensor sensor) {
		HistoricoPosicaoTO historicoTO = posicaoSensorService.buscaHistoricoPosicao(sensor.getIdSensor(), sensor.getDataInicial(),
				sensor.getDataFinal(), sensor.getPage(), sensor.getPageSize());
		return historicoTO;
	}
	
	public HistoricoPosicaoTO buscaQtdHistoricoPosicao(HistoricoSensor sensor) {
		HistoricoPosicaoTO historicoTO = posicaoSensorService.buscaQtdHistoricoPosicao(sensor.getIdSensor(), sensor.getDataInicial(),
				sensor.getDataFinal(), sensor.getPage(), sensor.getPageSize());
		return historicoTO;
	}
	
}
