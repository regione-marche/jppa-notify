package it.maggioli.pagopa.pagonet.jppanotify.services;

import java.io.StringReader;
import java.util.function.Predicate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import it.maggioli.informatica.schemi.ws.pagopa.notify.RichiestaStandard;
import it.maggioli.pagopa.pagonet.jppanotify.entities.NotificaPagamentoDebitoEntity;
import it.maggioli.pagopa.pagonet.jppanotify.repositories.NotificaPagamentoDebitoRepository;

@Service
public class NotificaPagamentoDebitoService {

    @Autowired
    private NotificaPagamentoDebitoRepository notificaPagamentoDebitoRepository;

    Predicate<String> notNullNotEmpty = str -> str != null && !str.isEmpty();

    public void saveNotificaPagamentoDebitoEntity(NotificaPagamentoDebitoEntity notificaPagamentoDebitoEntity) {
        notificaPagamentoDebitoRepository.save(notificaPagamentoDebitoEntity);
    }

	public void saveNotificaPagamentoDebitoEntity(RichiestaStandard notificaPagamentoDebitoRequest) {
    	NotificaPagamentoDebitoEntity notificaPagamentoDebitoEntity = new NotificaPagamentoDebitoEntity();
    	notificaPagamentoDebitoEntity.setStatus("ok");
    	notificaPagamentoDebitoEntity.setCodiceIpa(notificaPagamentoDebitoRequest.getCodiceIPA());
    	
        String idPos = extractIDPos(notificaPagamentoDebitoRequest.getDatiDettaglioRichiesta(), "IDPos");
        String idDeb = extractIDPos(notificaPagamentoDebitoRequest.getDatiDettaglioRichiesta(), "IDDeb");
        
        // TODO salvare su DB 
//        String idDomino = idPos.split("_")[0];
//        String iuv = idPos.split("_")[1];
        
        if(notNullNotEmpty.test(idPos) && notNullNotEmpty.test(idDeb)) {
	        JppaServer server = new JppaServer();
	        server.infoPagamentoDovuto(idDeb, idPos);
        }

    	saveNotificaPagamentoDebitoEntity(notificaPagamentoDebitoEntity);
	}
	
    private static String extractIDPos(String xmlString, String tagName) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML string and return the Document object
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));

            // Find the value of IDPos
            NodeList nodeList = document.getElementsByTagName(tagName);

            if (nodeList.getLength() > 0) {
                Element element = (Element) nodeList.item(0);
                return element.getTextContent();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    
}
