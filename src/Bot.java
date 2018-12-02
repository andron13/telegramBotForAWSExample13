import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private BotTokenReader br = new BotTokenReader();

    @Override
    public void onUpdateReceived(Update update) {

        Message userMessage = update.getMessage();
        long chat_id = update.getMessage().getChatId();

        if (update.hasMessage() && userMessage.hasText()) {
            String message_text = update.getMessage().getText();
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            SendMessage message = new SendMessage() // Create a message object object
                    //.setChatId(chat_id)
                    .setText("not found");
        }
    }

    @Override
    public String getBotUsername() {
        return br.getBotName();
    }

    @Override
    public String getBotToken() {
        return br.getBotToken();
    }
}
