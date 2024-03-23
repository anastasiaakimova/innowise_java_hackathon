package by.akimova.cryptobot;

import by.akimova.cryptobot.data.model.CurrencyEntity;
import by.akimova.cryptobot.service.CurrencyService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
public class CurrencyClient {
    @Value("${currency.rates.url}")
    private String currencyUrl;
    @Autowired
    private final CurrencyService currencyService;

    public CurrencyClient(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public void getCurrencyRates() throws IOException, SQLException {
        URL url = new URL(currencyUrl);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Gson gson = new Gson();
        List<CurrencyEntity> currencyList = Arrays.asList(gson.fromJson(reader, CurrencyEntity[].class));
        for (CurrencyEntity currency : currencyList) {
            saveCurrency(currency);
        }
        reader.close();
    }

    public void saveCurrency(CurrencyEntity currency) throws SQLException {
        currencyService.add(currency);
    }
}
