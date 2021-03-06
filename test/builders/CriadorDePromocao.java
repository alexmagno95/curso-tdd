package builders;

import java.util.Date;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;

public class CriadorDePromocao {

	private Promocao promocao;

	public CriadorDePromocao para(String nomeDaPromecao) {

		promocao = new Promocao(nomeDaPromecao);
		return this;
	}

	public CriadorDePromocao comLance(Cliente cliente, double valor) {

		promocao.registra(new Lance(cliente, valor));
		return this;
	}

	public Promocao cria() {

		return promocao;

	}
	
	public CriadorDePromocao naData(Date data){
		
		promocao.setData(data);
		return this;
	}

}
