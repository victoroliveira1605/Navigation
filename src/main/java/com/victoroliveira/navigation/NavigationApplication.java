package com.victoroliveira.navigation;

import com.victoroliveira.navigation.domain.Cidade;
import com.victoroliveira.navigation.services.CidadeService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NavigationApplication implements CommandLineRunner {

    @Autowired
    private CidadeService service;

    public static void main(String[] args) {
        SpringApplication.run(NavigationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Cidade> cidadeList = new ArrayList<>();
        Reader in = new FileReader("C:/Users/Victor Oliveira/Desktop/Trabalho Java - Cidades.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord record : records) {
            String ibgeId = record.get("ibge_id");
            String uf = record.get("uf");
            String city = record.get("name");
            String capital = record.get("capital");
            String lon = record.get("lon");
            String lat = record.get("lat");
            String cityNoAccents = record.get("no_accents");
            String alternativeNames = record.get("alternative_names");
            String microregion = record.get("microregion");
            String mesoregion = record.get("mesoregion");

            Cidade obj = new Cidade();
            obj.setIbgeId(Integer.valueOf(ibgeId));
            obj.setUf(uf);
            obj.setCity(city);
            obj.setCapital(Boolean.valueOf(capital));
            obj.setLon(Double.valueOf(lon));
            obj.setLat(Double.valueOf(lat));
            obj.setCityNoAccents(cityNoAccents);
            obj.setAlternativeNames(alternativeNames);
            obj.setMicroregion(microregion);
            obj.setMesoregion(mesoregion);
            cidadeList.add(obj);
        }
        service.saveListCities(cidadeList);
    }
}
