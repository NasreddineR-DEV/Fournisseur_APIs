package ma.oncf.digital.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ma.oncf.digital.entity.MailModel;
import ma.oncf.digital.service.SendingEmailService;

@Service
public class SendingEmailServiceImpl implements SendingEmailService {

	private static Logger log = LoggerFactory.getLogger(SendingEmailServiceImpl.class);

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	@Qualifier("emailConfigBean")
	private Configuration emailConfig;

	@Override
	public void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException {

		Map<String, String> model = new HashMap<String, String>();
		model.put("name", mailModel.getName());
		model.put("title", mailModel.getTitle());
		model.put("location", "Rabat Agdal");
		model.put("signature", "https://www.oncf.ma");
		model.put("content", mailModel.getContent());
		model.put("firstLine", mailModel.getFirstLines());
		model.put("details", mailModel.getDetails());

		mailModel.setModel(model);

		log.info("Sending Email to: " + mailModel.getTo());

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

		Template template = emailConfig.getTemplate("email.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());

		mimeMessageHelper.setTo(mailModel.getTo());
		mimeMessageHelper.setText(html, true);
		mimeMessageHelper.setSubject(mailModel.getSubject());

		emailSender.send(message);

	}
}
