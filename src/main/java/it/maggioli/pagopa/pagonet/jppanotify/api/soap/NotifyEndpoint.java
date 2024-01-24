package it.maggioli.pagopa.pagonet.jppanotify.api.soap;

import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;

import it.maggioli.informatica.schemi.ws.pagopa.notify.PagoPANotifyEndpoint;
import it.maggioli.informatica.schemi.ws.pagopa.notify.RichiestaStandard;
import it.maggioli.informatica.schemi.ws.pagopa.notify.RispostaStandard;
import it.maggioli.pagopa.pagonet.jppanotify.services.NotificaPagamentoDebitoService;

@Service
public class NotifyEndpoint implements PagoPANotifyEndpoint {

	private static LoggerWrapper log = CustomLoggerManager.get(NotifyEndpoint.class);

	@Autowired
	private NotificaPagamentoDebitoService notificaPagamentoDebitoService;
	
    @Override
    public RispostaStandard notificaStatoDebito(RichiestaStandard notificaStatoDebitoRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notificaStatoDebito'");
    }

    @Override
    public RispostaStandard notificaPagamentoDebito(RichiestaStandard notificaPagamentoDebitoRequest) {
        //insert RichiestaStandard in to database with hibernate
    	
    	URL resource = getClass().getClassLoader().getResource("log4j2-spring.xml");
    	if (resource != null) {
    	    System.out.println("File log4j2.xml trovato nel classpath: " + resource.getFile());
    	} else {
    	    System.out.println("File log4j2.xml NON trovato nel classpath.");
    	}
    	
    	log.debug("debug");
    	log.error("error");
    	log.info("info");
		try {
			// TODO
	    	notificaPagamentoDebitoService.saveNotificaPagamentoDebitoEntity(notificaPagamentoDebitoRequest);
   
			RispostaStandard risposta = new RispostaStandard();
	    	risposta.setCodiceIPA(notificaPagamentoDebitoRequest.getCodiceIPA());
	    	
	    	GregorianCalendar gc = new GregorianCalendar();
	    	gc.setTime(new Date());
	    	XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			
	    	risposta.setDataRisposta(date);
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
