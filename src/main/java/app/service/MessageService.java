package app.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {
    private final MessageSource ms;
    private Locale locale = Locale.ENGLISH;

    public MessageService(MessageSource ms) {
        this.ms = ms;
    }

    public void setLang(String lang) {
        this.locale = Locale.forLanguageTag(lang); // "en", "ru"
    }

    public String getLang() {
        return locale.getLanguage();
    }

    public String msg(String key) {
        return ms.getMessage(key, null, locale);
    }

    public String msg(String key, Object... args) {
        return ms.getMessage(key, args, locale);
    }
}
