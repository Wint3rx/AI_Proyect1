package umg.principal.BotTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "@WinterSoldi3rBot";
    }

    @Override
    public String getBotToken() {
        return ("7407523638:AAEJ4Mhi4hUBhf2q9G9Qu7LAh7UskX-hN50");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText.split(" ")[0].toLowerCase()) {
                case "/info":
                    sendText(chatId, "Información personal:\nCarnet: 0905-23-4498\nNombre: Franklin Boanerges Lopez Chavarria\nSemestre: Cuarto Semestre");
                    break;
                case "/progra":
                    sendText(chatId, "La clase de programación es muy interesante y me gusta mucho.");
                    break;
                case "/hola":
                    sendGreeting(chatId, update);
                    break;
                case "/cambio":
                    handleCurrencyExchange(chatId, messageText);
                    break;
                case "/grupal":
                    handleGroupMessage(messageText);
                    break;
                default:
                    sendText(chatId, "Comando no reconocido.");
            }

            System.out.printf("User id: %d, Message: %s%n", chatId, messageText);
        }
    }

    private void sendGreeting(long chatId, Update update) {
        String nombre = update.getMessage().getFrom().getFirstName();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd 'de' MMMM", new Locale("es", "ES"));
        String fecha = sdf.format(new Date());

        String greeting = "Hola, " + nombre + ", hoy es " + fecha + ".";
        sendText(chatId, greeting);
    }

    private void handleCurrencyExchange(long chatId, String messageText) {
        String[] parts = messageText.split(" ");
        if (parts.length == 2) {
            try {
                double euros = Double.parseDouble(parts[1]);
                double rate = 8.49;
                double quetzales = euros * rate;
                sendText(chatId, "Son " + quetzales + " quetzales.");
            } catch (NumberFormatException e) {
                sendText(chatId, "Por favor, envíe un valor numérico válido.");
            }
        } else {
            sendText(chatId, "Formato incorrecto. Usa /cambio [monto]");
        }
    }

    private void handleGroupMessage(String messageText) {
        List<Long> chatIds = List.of(6709392176L, 6922425377L, 6710213754L, 6957944438L);
        String message = messageText.substring(messageText.indexOf(" ") + 1);

        for (Long id : chatIds) {
            sendText(id, message);
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
