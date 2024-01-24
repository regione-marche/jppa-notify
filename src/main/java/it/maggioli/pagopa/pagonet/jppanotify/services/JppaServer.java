package it.maggioli.pagopa.pagonet.jppanotify.services;

import io.swagger.client.api.PagamentiApi;
import io.swagger.client.model.ChiaveDebitoDto;
import io.swagger.client.model.ChiaveDebitoDto.CodiceTipoDebitoEnum;
import io.swagger.client.model.RichiestaInfoPagamentoDovutoDto;
import io.swagger.client.model.RichiestaInfoPagamentoDovutoDto.CodiceServizioEnum;
import io.swagger.client.model.RispostaInfoPagamentoDovutoDto;

public class JppaServer extends JppaBaseServer {

	private final PagamentiApi pagamentiApiCaller = new PagamentiApi();

	public JppaServer() {
		super();
	}

	// Info Pagamento Dovuto
	public RispostaInfoPagamentoDovutoDto infoPagamentoDovuto(String idDeb, String idPos) {
		RispostaInfoPagamentoDovutoDto res = null;
		RichiestaInfoPagamentoDovutoDto richiestaInfoPagamentoDovuto = new RichiestaInfoPagamentoDovutoDto();
		
		try {
			setJwtTokenToRequest();
			ChiaveDebitoDto chiaveDebitoDto = new ChiaveDebitoDto();
			chiaveDebitoDto.setCodEnteCreditore("c_b240");
			chiaveDebitoDto.setCodiceTipoDebito(CodiceTipoDebitoEnum.MULTE);
			chiaveDebitoDto.setIDeb(idDeb);
			chiaveDebitoDto.setIPos(idPos);
			
			richiestaInfoPagamentoDovuto.setChiaveDebitoDto(chiaveDebitoDto);
			richiestaInfoPagamentoDovuto.setCodiceServizio(CodiceServizioEnum.PAGONET);
			richiestaInfoPagamentoDovuto.setCodIpaRichiedente("c_b240");
			res = pagamentiApiCaller.postInfoPerDovutoUsingPOST(richiestaInfoPagamentoDovuto);
			
			System.out.println("################### RESPONSE ###################");
			System.out.println(res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}

