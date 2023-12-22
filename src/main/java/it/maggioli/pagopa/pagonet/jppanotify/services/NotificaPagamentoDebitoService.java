package it.maggioli.pagopa.pagonet.jppanotify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.maggioli.informatica.schemi.ws.pagopa.notify.RichiestaStandard;
import it.maggioli.pagopa.pagonet.jppanotify.entities.NotificaPagamentoDebitoEntity;
import it.maggioli.pagopa.pagonet.jppanotify.repositories.NotificaPagamentoDebitoRepository;

@Service
public class NotificaPagamentoDebitoService {

    @Autowired
    private NotificaPagamentoDebitoRepository notificaPagamentoDebitoRepository;

    public void saveNotificaPagamentoDebitoEntity(NotificaPagamentoDebitoEntity notificaPagamentoDebitoEntity) {
        notificaPagamentoDebitoRepository.save(notificaPagamentoDebitoEntity);
    }

	public void saveNotificaPagamentoDebitoEntity(RichiestaStandard notificaPagamentoDebitoRequest) {
    	NotificaPagamentoDebitoEntity notificaPagamentoDebitoEntity = new NotificaPagamentoDebitoEntity();
    	notificaPagamentoDebitoEntity.setStatus("ok");
    	saveNotificaPagamentoDebitoEntity(notificaPagamentoDebitoEntity);
	}
    
}
