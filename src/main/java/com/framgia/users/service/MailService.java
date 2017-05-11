package com.framgia.users.service;

public interface MailService {

	public void sendEmail(final Object object);
	
	public void sendMailRestPass(final String contextPath, final Object object);
}
