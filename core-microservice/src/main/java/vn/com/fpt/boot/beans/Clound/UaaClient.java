package vn.com.fpt.boot.beans.Clound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class UaaClient {

	static final Logger logger = LoggerFactory.getLogger(UaaClient.class);

	@Value("${security.oauth2.client.accessTokenUri}")
	String uaaUri;

	@Value("${security.oauth2.client.base64}")
	String base64UaaCredentials;
	
	@Autowired
	RestTemplate restTemplate;

	public TokenResponse getToken() {
		System.out.println( "uaaUri: "+ uaaUri);		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Basic "
				+ base64UaaCredentials);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.set("grant_type", "client_credentials");
		ResponseEntity<TokenResponse> response = restTemplate.exchange(uaaUri
				+ "/oauth/token", HttpMethod.POST, new HttpEntity<>(body,
				headers), TokenResponse.class);

		return response.getBody();
	}

}
