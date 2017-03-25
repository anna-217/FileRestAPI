package us.finra.assignment.utility;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import us.finra.assignment.entity.MetaData;
import us.finra.assignment.service.MetaDataService;

@Component
public class EmailScheduler {
	
	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	MetaDataService metaDataService;
	
	@Autowired
	Constants constants;
	
//	@Scheduled (fixedRate = 60*60*1000)  // send email in every 50 sec
	public void sendEmail () {
		StringBuilder stringBuilder = new StringBuilder();
		List<MetaData> list = metaDataService.getNewItemInLastHour();
		
		Iterator<MetaData> iterator = list.iterator();
		while (iterator.hasNext()) {
			stringBuilder.append(iterator.next().getName() + " is added.\n");
		}
						
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(constants.TO_EMAIL_ADD);
		message.setFrom(constants.FROM_EMAIL_ADD);
		message.setSubject("new items added");		
		message.setText(stringBuilder.toString());
		System.out.println(stringBuilder.toString());
		if (stringBuilder.length() > 0)
			mailsender.send(message);
	}
}
