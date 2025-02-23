package com.alextechsolutions.serviceaheadversion5_0.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.alextechsolutions.serviceaheadversion5_0.Model.BookingDetail;

import jakarta.mail.MessagingException; 
import jakarta.mail.internet.MimeMessage;


@Service
public class SimpleEmailService {
     @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(BookingDetail bDetail) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");

            String client = bDetail.getEmail();
            
            // Set email details
            helper.setFrom("contact@alextechsolutions.com");
            helper.setTo(new String[]{"aryanbansalaryan@gmail.com", "contact@alextechsolutions.com", client});
            helper.setSubject("Thank you for booking with us");

                    // Create HTML content
                    String emailContent = "<html><body>"
                    + "<p>Here are the details of your booking:</p>"
                    + "<table border='1' cellpadding='5' cellspacing='0' style='border-collapse: collapse;'>"
                    + "<tr><th>Full Name</th><td>" + bDetail.getFullName() + "</td></tr>"
                    + "<tr><th>Email</th><td>" + bDetail.getEmail() + "</td></tr>"
                    + "<tr><th>Phone</th><td>" + bDetail.getPhone() + "</td></tr>"
                    + "<tr><th>Address</th><td>" + bDetail.getAddress() + "</td></tr>"
                    + "<tr><th>City</th><td>" + bDetail.getCity() + "</td></tr>"
                    + "<tr><th>Car Company</th><td>" + bDetail.getCarCompany() + "</td></tr>"
                    + "<tr><th>Slot</th><td>" + bDetail.getSlot() + "</td></tr>"
                    + "<tr><th>Fuel Type</th><td>" + bDetail.getFuelType() + "</td></tr>"
                    + "</table>"
                    + "<p>Thank you for choosing us!</p>"
                    + "</body></html>";

            // Set the email content to HTML
            helper.setText(emailContent, true);

            // Send the email
            mailSender.send(mimeMessage);
        }
    
        catch (MessagingException e) {
            // Log the exception and handle it, e.g., by sending an alert or a retry
            e.printStackTrace(); // or use a logger
        }
    }
}
