package pe.edu.upeu.gth.dao;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.gth.dto.ProductOrder;
import pe.edu.upeu.gth.interfaz.MailService;



@Service("mailService")

//@Transactional(readOnly = true)
//@Component
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;
//	private JavaMailSender mailSender;  
//	   
//    public void setMailSender(JavaMailSender mailSender) {  
//        this.mailSender = mailSender;  
//    }  

	public void sendEmail(Object object, String[] email, String text) {

		ProductOrder order = (ProductOrder) object;
//		String[] email = {"104granados@gmail.com","harolcotac@gmail.com"};

		
		int currentSize = email.length;
		int newSize = currentSize + 2;
		String[] tempArray = new String[ newSize ];
		for (int i=0; i < currentSize; i++)
		{
		    tempArray[i] = email [i];
		}
		tempArray[newSize- 1] = "104granados@gmail.com";
		tempArray[newSize- 2] = "harolcotac@gmail.com";
		email = tempArray;   
		 System.out.println("mail service emails" );
		for (String element:email ) {
		
		    System.out.println( element );
		}
		
		
		int count = 0;
		int nroemails = email.length;
		int cociente = nroemails/50;
		int residuo = nroemails%50;
		
		int totalarrays = cociente + 1;	
		
		
		 int pos_start=0;
		for (int i=0; i < totalarrays; i++)
			
		{
			
			 System.out.println("numero de array> "+ i);
			if ( i == totalarrays-1) {
				String[] lastarray = new String[ residuo ];
				for (int k=0; k < residuo; k++)
				{
					System.out.println("residuo> " + residuo);
					System.out.println(nroemails);
					System.out.println("var posicion last> " + pos_start);
					System.out.println("valor en el lastarray> " +email [pos_start]);
					lastarray[k] = email [pos_start];
					 pos_start++;
				}
				
				
				MimeMessagePreparator preparator = getMessagePreparator(order, lastarray, text);
				
				try {
					mailSender.send(preparator);
					
					System.out.println("Message Send...Hurrey");
				} catch (MailException ex) {
					System.err.println(ex.getMessage());
				}
				
				
				
			}else {
				String[] array = new String[ 50 ];
				for (int j=0; j < 50; j++)
				{
					
					System.out.println(nroemails);
					System.out.println("var posicion> " + pos_start);
					System.out.println("valor en el array> " +email [pos_start]);
					array[j] = email [pos_start];
					 pos_start++;
				}
				
				MimeMessagePreparator preparator = getMessagePreparator(order, array, text);
				
				try {
					mailSender.send(preparator);
					
					System.out.println("Message Send...Hurrey");
				} catch (MailException ex) {
					System.err.println(ex.getMessage());
				}
				
				
			}
			
//			pos_start += 1;
			
		}
		

		
	}

	
	
	private MimeMessagePreparator getMessagePreparator(final ProductOrder order, String[] to, String text) {

//		MimeMessagePreparator preparator = new MimeMessagePreparator() {
//
//			public void prepare(MimeMessage mimeMessage) throws Exception {
//				mimeMessage.setFrom("harolcotac@gmail.com");
//				mimeMessage.setRecipient(Message.RecipientType.TO,
//						new InternetAddress(order.getCustomerInfo().getEmail()));
//				mimeMessage.setText("Dear " + order.getCustomerInfo().getName()
//						+ ", thank ...............????????///////??????....................you for placing order. Your order id is " + order.getOrderId() + ".");
//				mimeMessage.setSubject("Your order on Demoapp Testing");
//			}
//		};
		
		MimeMessagePreparator preparator1 = new MimeMessagePreparator() {
			 @Override
			   public void prepare(MimeMessage mimeMessage) throws MessagingException {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setFrom("developeralpha@testing.com");
			     message.setTo(to);
			     message.setSubject("Flying aorund the world");
			     message.setText(text, true);
			     
//			     FileSystemResource file = new FileSystemResource("E:\\EL GRAN CONFLICTO.docx");
//			     message.addAttachment(file.getFilename(), file);
//			     message.addInline("myLogoUpeu", new ClassPathResource("img/logo_upeu.png"));
//			     message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
			   }
			 };
		
		
		return preparator1;
	}





}
