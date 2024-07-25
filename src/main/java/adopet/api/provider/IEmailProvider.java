package adopet.api.provider;

public interface IEmailProvider {
    void send(String from, String to, String subject, String body);
}
