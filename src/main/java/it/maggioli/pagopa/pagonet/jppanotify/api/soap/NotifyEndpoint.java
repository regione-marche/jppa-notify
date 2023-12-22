package it.maggioli.pagopa.pagonet.jppanotify.api.soap;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.maggioli.informatica.schemi.ws.pagopa.notify.PagoPANotifyEndpoint;
import it.maggioli.informatica.schemi.ws.pagopa.notify.RichiestaStandard;
import it.maggioli.informatica.schemi.ws.pagopa.notify.RispostaStandard;
import it.maggioli.pagopa.pagonet.jppanotify.services.NotificaPagamentoDebitoService;


@Service
public class NotifyEndpoint implements PagoPANotifyEndpoint {

	@Autowired
	private NotificaPagamentoDebitoService notificaPagamentoDebitoService;
	
    @Override
    public RispostaStandard notificaStatoDebito(RichiestaStandard notificaStatoDebitoRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notificaStatoDebito'");
    }

    @Override
    public RispostaStandard notificaPagamentoDebito(RichiestaStandard notificaPagamentoDebitoRequest) {
        // TODO Auto-generated method stub

        //insert RichiestaStandard in to database with hibernate
//        throw new UnsupportedOperationException("Unimplemented method 'notificaPagamentoDebito'");
    	
    	
		try {
			// TODO
	    	notificaPagamentoDebitoService.saveNotificaPagamentoDebitoEntity(notificaPagamentoDebitoRequest);

			RispostaStandard risposta = new RispostaStandard();
	    	risposta.setCodiceIPA(notificaPagamentoDebitoRequest.getCodiceIPA());
	    	
	    	GregorianCalendar c = new GregorianCalendar();
	    	c.setTime(new Date());
	    	XMLGregorianCalendar date2;
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			
	    	risposta.setDataRisposta(date2);
	    	risposta.setEsitoOperazione(null);
	    	risposta.setIDOperazione(null);
	    	risposta.setMessaggi(null);
	    	return risposta;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
  	   		
		return null;
    }

    


}
