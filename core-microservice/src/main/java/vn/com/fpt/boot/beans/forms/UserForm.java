package vn.com.fpt.boot.beans.forms;

import vn.com.fpt.boot.commons.objects.RestForm;

public class UserForm extends RestForm{
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
