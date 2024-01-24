package it.maggioli.pagopa.pagonet.jppanotify.services;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.AutenticazioneApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.JppaLoginRequest;
import io.swagger.client.model.JppaLoginResponse;

public class JppaBaseServer {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
		
	private final AutenticazioneApi apiAuthCaller = new AutenticazioneApi();
	
	private String basePath = null;  
	private String identificativoEnte = null;    
	private String username = null;
	private String password = null;
	private String token = null;
	
	public JppaBaseServer() {
		this.setAuthConfigs();
		this.setBasePath();
		this.login();
	}
	
	/*LOGIN*/
	private void login() {
		
		String idMessaggio = UUID.randomUUID().toString();
		try {
			JppaLoginRequest loginRequest = new JppaLoginRequest();
			
			loginRequest.setIdMessaggio(idMessaggio);
			loginRequest.setIdentificativoEnte(identificativoEnte);
			loginRequest.setUsername(username);
			loginRequest.setPassword(password);
			
			JppaLoginResponse response = apiAuthCaller.loginUsingPOST(loginRequest);
			
			if(response != null) {
				System.out.println("################### RESPONSE ###################");
				System.out.println("esito: " + response.getEsito());
				System.out.println("descrizioneErrore: " + response.getDescrizioneErrore());
				
				if(response.getEsito()!=null && response.getEsito().equals("OK")) {
					this.token = response.getToken();
				}else {
					throw new Exception(response.getDescrizioneErrore());
				}
							
			}else {
				throw new Exception("Exception when calling AutenticazioneApi#loginUsingPOST: response=null");
			}
		}catch(ApiException e) {
			System.err.println("Exception when calling AutenticazioneApi#loginUsingPOST");
			e.printStackTrace();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}	
	
	private void setAuthConfigs() {
		this.identificativoEnte = "c_b240";
	    this.username = "adminColl";
	    this.password = "AdminColl@1902357";
	    this.basePath = "https://pagopa-staging.maggioli.cloud/jcitygov-pagopa/api";
	}
	
	public void setJwtTokenToRequest() {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth jwtToken = (ApiKeyAuth) defaultClient.getAuthentication("jwtToken");
		jwtToken.setApiKey("Bearer " + this.token);
	}
	
	public void setBasePath() {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath(basePath);
	}
	
}
