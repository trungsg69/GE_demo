package vn.com.fpt.boot.beans.rests;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;










import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

import vn.com.fpt.boot.beans.Entities.Users;
import vn.com.fpt.boot.beans.Service.UserService;
import vn.com.fpt.boot.beans.forms.UserForm;
import vn.com.fpt.boot.beans.models.TokenResponse;
import vn.com.fpt.boot.commons.utils.JacksonUtils;
import vn.com.fpt.boot.structures.templates.RestTemplate;
@Component
public class UserRest extends RestTemplate<UserForm>{
	@Autowired
	UserService userService;
	@Override
	public String getDefinition(UserForm form,
			RequestEntity<UserForm> requestEntity, String functionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String putDefinition(UserForm form,
			RequestEntity<UserForm> requestEntity, String... functionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postDefinition(@RequestBody UserForm form,
			RequestEntity<UserForm> requestEntity, String url) {
		TokenResponse tokenResponse= new TokenResponse();
		if (StringUtils.isNotBlank(form.getUserName())){
				Users user= userService.getUserByUserName(form.getUserName());
				
				if (user.getPassWord().equals(form.getPassword())){
				try {
					byte[] bytesOfMessage = form.getUserName().getBytes("UTF-8");
					MessageDigest md = MessageDigest.getInstance("MD5");
					byte[] thedigest = md.digest(bytesOfMessage);
					
					tokenResponse.setStatusCode(200);
					tokenResponse.setSuccess(true);
					tokenResponse.setToken(new String(thedigest, StandardCharsets.UTF_8));
					
					return JacksonUtils.java2Json(tokenResponse);
				} catch (UnsupportedEncodingException e) {					
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {					
					e.printStackTrace();
				}
				
				}
				else {
					tokenResponse.setStatusCode(400);
					tokenResponse.setSuccess(false);
					tokenResponse.setErrorMessage("Wrong UserName or PassWord");
					return JacksonUtils.java2Json(tokenResponse);
				}
				
		}
		
		tokenResponse.setStatusCode(500);
		tokenResponse.setSuccess(false);
		tokenResponse.setErrorMessage("UserName or Password cannot be empty");
		return JacksonUtils.java2Json(tokenResponse);
	}

	@Override
	public String deleteDefinition(UserForm form,
			RequestEntity<UserForm> requestEntity, String... functionType) {
		// TODO Auto-generated method stub
		return null;
	}

}
