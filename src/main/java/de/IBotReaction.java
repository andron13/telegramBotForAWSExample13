import org.telegram.telegrambots.meta.api.objects.Message;

public interface IBotReaction {
    public void execute(Bot bot, Message message);
}
