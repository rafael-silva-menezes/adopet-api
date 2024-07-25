package adopet.api.provider;

public interface EmailProviderInterface {
   void send(String from, String to, String subject, String body);
}
