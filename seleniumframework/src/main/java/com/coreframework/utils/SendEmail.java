package com.coreframework.utils;
import java.io.IOException;
import org.apache.commons.mail.EmailException;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

public class SendEmail {
	public static void sendEmailThis(final String filePath) throws IOException, EmailException {
		sendEmailThis(filePath, "TestMail- Alternative message");

	}

	public static void sendEmailThis(final String filePath, final String subjectName)
			throws IOException, EmailException {
		File file = new File(filePath);
		String string = FileUtils.readFileToString(file, "UTF-8");
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("tsutiger129@gmail.com", "AccountFor@tsu"));
		email.setSSLOnConnect(true);
		email.setFrom("tsutiger129@gmail.com");
		email.setSubject(subjectName);
		email.setHtmlMsg(filePath);
		email.addTo("tsutiger129@gmail.com");
		email.addCc("sqatestingexpert@gmail.com");
		email.send();

	}
}


