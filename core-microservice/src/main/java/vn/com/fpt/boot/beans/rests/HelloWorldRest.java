package vn.com.fpt.boot.beans.rests;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import vn.com.fpt.boot.beans.Clound.UaaClient;
import vn.com.fpt.boot.beans.Entities.Users;
import vn.com.fpt.boot.beans.Service.UserService;



import vn.com.fpt.boot.beans.components.DeploymentRegistry;
import vn.com.fpt.boot.beans.forms.HelloWorldForm;
import vn.com.fpt.boot.beans.models.HelloWorldModel;
import vn.com.fpt.boot.beans.models.TokenResponse;
import vn.com.fpt.boot.commons.constants.CommonConstants;
import vn.com.fpt.boot.commons.utils.HttpMessageUtils;
import vn.com.fpt.boot.commons.utils.JacksonUtils;
import vn.com.fpt.boot.commons.utils.MessageUtils;
import vn.com.fpt.boot.structures.templates.RestTemplate;

/**
 * Created by VietLK on 2/20/2017.
 */

@Component
public class HelloWorldRest extends RestTemplate<HelloWorldForm> {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private DeploymentRegistry deploymentRegistry;
	@Autowired
	private UaaClient uaaClient;
	@Autowired
	private UserService userService;

	@Override
	public String getDefinition(HelloWorldForm form, RequestEntity<HelloWorldForm> requestEntity, String url) {
		if (url== "index" ){
		String lang = HttpMessageUtils.getRequestLanguage(requestEntity.getHeaders());
		
		if(StringUtils.isEmpty(lang)) {
			lang = deploymentRegistry.get("default.language");
		}
		
		String name = form.getName();
		
		HelloWorldModel model = new HelloWorldModel();
		model.setHello(MessageUtils.getMessage(messageSource, "helloworld.hello", null, lang) + 
				CommonConstants.SINGLE_SPACE + name);
		model.setWelcome(MessageUtils.getMessage(messageSource, "helloworld.welcome.world", null, lang));
		
		return JacksonUtils.java2Json(model);}
		else if (url=="he"){
			TokenResponse tokenResponse = new TokenResponse();
			tokenResponse.setStatusCode(200);
			tokenResponse.setSuccess(true);
			tokenResponse.setToken(uaaClient.getToken().getAccessToken());
			return JacksonUtils.java2Json(tokenResponse);
		}
		else if (url=="user"){
			List<Users> users = userService.getAll();
			return JacksonUtils.java2Json(users);
		}
		else return null;
	}

	@Override
	public String putDefinition(HelloWorldForm form, RequestEntity<HelloWorldForm> requestEntity, String... functionType) {
		return null;
	}

	@Override
	public String postDefinition(HelloWorldForm form, RequestEntity<HelloWorldForm> requestEntity, String url) {
		return null;
	}

	@Override
	public String deleteDefinition(HelloWorldForm form, RequestEntity<HelloWorldForm> requestEntity, String... functionType) {
		return null;
	}

	

	
}
