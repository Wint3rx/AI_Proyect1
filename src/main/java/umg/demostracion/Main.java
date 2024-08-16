package umg.demostracion;

import org.example.BotTelegram.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            Bot bot = new Bot();
            botsApi.registerBot(bot);
            System.out.println("El bot está funcionando");
        } catch (Exception ex) {
            System.err.println("Error al iniciar el bot: " + ex.getMessage());
            ex.printStackTrace();  // Muestra el stack trace para una mejor depuración
        }
    }
}
