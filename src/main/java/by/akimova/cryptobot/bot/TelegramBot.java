package by.akimova.cryptobot.bot;

import by.akimova.cryptobot.CurrencyClient;
import by.akimova.cryptobot.data.model.PersonEntity;
import by.akimova.cryptobot.service.CurrencyService;
import by.akimova.cryptobot.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(TelegramBot.class);
    @Autowired
    private final PersonService personService;
    @Autowired
    private final CurrencyService currencyService;
    @Autowired
    private final CurrencyClient currencyClient;

    @Value("${bot.name}")
    private String BOT_NAME;

    private static final String START = "/start";
    private static final String CHOOSE_CURRENCY = "/choose";
    private static final String CHOOSE_PERCENTAGE = "/percentage";
    private static final String HELP = "/help";


    public TelegramBot(@Value("${bot.token}") String botToken,
                       PersonService personService, CurrencyService currencyService,
                       CurrencyClient currencyClient) {
        super(botToken);
        this.personService = personService;
        this.currencyService = currencyService;
        this.currencyClient = currencyClient;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        switch (message) {
            case START -> {
                String firstName = update.getMessage().getChat().getFirstName();
                String username = update.getMessage().getChat().getUserName();

                startCommand(chatId, firstName, username);
            }
            case CHOOSE_CURRENCY -> {

                try {
                    currencyClient.getCurrencyRates();
                    currencyService.getAll();
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            case HELP -> {
                helpCommand(chatId);
            }
            default -> unknownCommand(chatId);
        }
    }

    private void startCommand(Long chatId, String userName, String username) {
        try {
            personService.add(new PersonEntity(username));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        var text = """
                Добро пожаловать в бот, %s!
                                
                Здесь Вы сможете узнать официальные курсы валют на сегодня
                                
                Для этого воспользуйтесь командами:
                /choose - выбрать валюту для отслеживания
                                
                Дополнительные команды:
                /help - получение справки
                """;
        var formattedText = String.format(text, userName);
        sendMessage(chatId, formattedText);
    }

    private void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

    private void helpCommand(Long chatId) {
        var text = """
                Справочная информация по боту
                                
                Для выбора валют воспользуйтесь командами:
                /choose
                                
                """;
        sendMessage(chatId, text);
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }
}
