import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BotTokenReader {
    private String botName;
    private String botToken;

    public BotTokenReader(){
        readFromJsonFile();
    }

    public void readFromJsonFile() {

        String fileName = "botToken\\config.json";
        JSONParser parser = new JSONParser();

        try {
            Object objToken = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) objToken;

            String botName = (String) jsonObject.get("botName");
            String botToken = (String) jsonObject.get("botToken");

            this.setBotName(botName);
            this.setBotToken(botToken);

        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBotName() {
        return botName;
    }

    private void setBotName(String botName) {
        this.botName = botName;
    }

    public String getBotToken() {
        return botToken;
    }

    private void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}