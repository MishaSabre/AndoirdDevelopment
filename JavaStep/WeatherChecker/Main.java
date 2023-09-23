import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class Weather{
    String name;
    Main main;

    class Main{
        Float temp;
    }

    public Float getTemp(){
        return this.main.temp;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        //создаю массив с названиями городов
        String[] cities = {"Irkutsk", "Novosibirsk", "Moscow", "Tokyo", "Krasnoyarsk", "Ulaanbaatar", "Ulan-Ude",
                "Krasnoyarsk", "Tomsk", "Yuzhno-Sakhalinsk"};
        String buff = "";
        //список объектов городов
        List<Weather> citiesTemp = new ArrayList<>();

        Gson gson = new Gson();
        //заполняю список городов последовательно десериализуя их
        for (String str : cities){
            //передаю название города в переменную buff
            buff = str;
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + buff
                    + "&appid=0549342419cee64f67fa443ccad3c942&units=metric";
            URL weather_url = new URL(url);
            InputStream stream = (InputStream) weather_url.getContent();
            citiesTemp.add(gson.fromJson(new InputStreamReader(stream), Weather.class));
        }
        //сортирую города по возрастанию температуры
        Comparator<Weather> compareByMainTemp = Comparator.comparing(Weather::getTemp);
        citiesTemp.sort(compareByMainTemp);
        //переворачиваю список, чтобы города шли от теплых к холодным
        Collections.reverse(citiesTemp);
        //вывод
        for (Weather weather : citiesTemp){
            System.out.println(weather.name + " " + Math.round(weather.main.temp));
        }

    }
}
