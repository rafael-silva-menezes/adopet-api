package adopet.provider;

public interface IEmailProvider {
    void send(String from, String to, String subject, String body);
}
