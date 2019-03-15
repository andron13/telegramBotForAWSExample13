package de.telran;

import de.telran.weather.WeatherService;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

	@Value("${bot.token}")
	private String token;

	@Value("${bot.username}")
	private String username;

	private static final Logger logger = LoggerFactory.getLogger(Bot.class);

	@Autowired
	WeatherService weatherService;

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	@PostConstruct
	public void start() {
		logger.info("username: {}, token: {}", username, token);
	}

	private String getResponseByRequest(String input) {
		double weatherInCity = weatherService.getWeatherInCity(input);
		String text = "Weather in " + input + " is " + weatherInCity + " grad Celsius";
		return text;
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) {
			Message message = update.getMessage();
			SendMessage response = new SendMessage();
			Long chatId = message.getChatId();
			response.setChatId(chatId);
			String out = getResponseByRequest(message.getText());
			response.setText(out);
			try {
				execute(response);
				logger.info("Sent message \"{}\" to {}", out, chatId);
			} catch (TelegramApiException e) {
				logger.error("Failed to send message \"{}\" to {} due to error: {}", out, chatId, e.getMessage());
			}
		}
	}
}
