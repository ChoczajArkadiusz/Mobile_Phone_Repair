package pl.choczaj.spring.mobilerepair.email;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}