package it.maggioli.pagopa.pagonet.jppanotify.api.soap;

import org.springframework.stereotype.Service;

import it.maggioli.informatica.schemi.ws.pagopa.notify.PagoPANotifyEndpoint;
import it.maggioli.informatica.schemi.ws.pagopa.notify.RichiestaStandard;
import it.maggioli.informatica.schemi.ws.pagopa.notify.RispostaStandard;


@Service
public class NotifyEndpoint implements PagoPANotifyEndpoint {

    @Override
    public RispostaStandard notificaStatoDebito(RichiestaStandard notificaStatoDebitoRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notificaStatoDebito'");
    }

    @Override
    public RispostaStandard notificaPagamentoDebito(RichiestaStandard notificaPagamentoDebitoRequest) {
        // TODO Auto-generated method stub

        //insert RichiestaStandard in to database with hibernate
        


        throw new UnsupportedOperationException("Unimplemented method 'notificaPagamentoDebito'");
    }

    


}
