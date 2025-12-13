package app.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;

@Service
public class MessageService {
    private final MessageSource ms;
    private Locale locale = new Locale("en");

    public MessageService(MessageSource ms) { this.ms = ms; }

    public void setLang(String lang) { this.locale = new Locale(lang); }
    public String getLang() { return locale.getLanguage(); }

    public String msg(String key, Object... args) {
        String pattern = ms.getMessage(key, null, locale);
        return MessageFormat.format(pattern, args);
    }
}
