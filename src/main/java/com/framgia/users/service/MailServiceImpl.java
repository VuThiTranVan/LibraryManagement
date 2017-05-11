package com.framgia.users.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.framgia.users.bean.BorrowedInfo;
import com.framgia.users.bean.UserInfo;
import com.framgia.util.ConvertDataModelAndBean;

/**
 * ManagementUsersController.java
 * 
 * @version 06/05/2017
 * @author vu.thi.tran.van@framgia.com
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

	// log
	private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;

	@Autowired
	MessageSource messageSource;

	private String subject = "Your Borrowed book: have been change status ";

	@Override
	public void sendEmail(Object object) {

		BorrowedInfo borrowedInfo = (BorrowedInfo) object;

		MimeMessagePreparator preparator = getMessagePreparator(borrowedInfo);

		try {
			mailSender.send(preparator);
			logger.info("Message sending...");

		} catch (MailException ex) {
			ex.printStackTrace();
			logger.error("Error send mail: " + ex.getMessage());
		}
	}

	private MimeMessagePreparator getMessagePreparator(final BorrowedInfo borrowedInfo) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject(subject + borrowedInfo.getStatus());

				helper.setTo(borrowedInfo.getUserInfo().getEmail());

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("borrowedInfo", borrowedInfo);
				String text = "";

				if (borrowedInfo.getStatus().equals(ConvertDataModelAndBean.borr_status_approve)) {
					text = getContent_Borrowed_Approve(model);
				}

				if (borrowedInfo.getStatus().equals(ConvertDataModelAndBean.borr_status_cancel)) {
					text = getContent_Borrowed_CanCel(model);
				}

				logger.info("Body mail: " + text);
				helper.setText(text, true);
			}
		};

		return preparator;
	}

	@SuppressWarnings("deprecation")
	public String getContent_Borrowed_Approve(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
			        "/templateMail/MailBorrowed_Approve.vm", model));
			return content.toString();
		} catch (Exception e) {
			logger.error("Error processing velocity template: " + e.getMessage());
		}
		return "";
	}

	@SuppressWarnings("deprecation")
	public String getContent_Borrowed_CanCel(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
			        "/templateMail/MailBorrowed_Cancel.vm", model));
			return content.toString();
		} catch (Exception e) {
			logger.error("Error processing velocity template: " + e.getMessage());
		}
		return "";
	}

	// send mail of reset password
	@Override
	public void sendMailRestPass(String contextPath, Object object) {

		UserInfo user = (UserInfo) object;

		MimeMessagePreparator preparator = getMessagePreparator_ResetPass(contextPath, user);

		try {
			mailSender.send(preparator);
			logger.info("Message Sending...");

		} catch (MailException ex) {
			ex.printStackTrace();
			logger.error("Error send mail: " + ex.getMessage());
		}
	}

	private MimeMessagePreparator getMessagePreparator_ResetPass(final String contextPath, final UserInfo user) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				String url = contextPath + "?id=" + user.getUserId() + "&password=" + user.getPassWord();

				helper.setSubject(messageSource.getMessage("subject_reset_pass", null, Locale.getDefault()));

				helper.setTo(user.getEmail());

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("name", user.getName());
				model.put("url", url);
				String text = getContentMailResetPass(model);

				logger.info("Body mail: " + text);
				helper.setText(text, true);
			}
		};

		return preparator;
	}

	@SuppressWarnings("deprecation")
	public String getContentMailResetPass(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"/templateMail/MailResetPassword.vm", model));
			return content.toString();
		} catch (Exception e) {
			logger.error("Error processing velocity template: " + e.getMessage());
		}
		return "";
	}
}
