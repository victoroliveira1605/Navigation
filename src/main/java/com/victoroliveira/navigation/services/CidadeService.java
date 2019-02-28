package com.victoroliveira.navigation.services;

import com.victoroliveira.navigation.domain.Cidade;
import com.victoroliveira.navigation.domain.QuantidadeUF;
import com.victoroliveira.navigation.repositories.CidadeRepository;
import com.victoroliveira.navigation.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public void saveListCities(List<Cidade> listCities) {
        repository.saveAll(listCities);
    }

    public List<Cidade> procurarCapitais() {
        List<Cidade> capitals;
        capitals = repository.findAll().stream().filter(Cidade::isCapital).collect(Collectors.toList());
        capitals.sort(Comparator.comparing(Cidade::getCity));
        return capitals;
    }

    public List<Cidade> procurarUF(String uf) {
        List<Cidade> citiesUF;
        citiesUF = repository.findAll().stream().filter(city -> uf.equals(city.getUf())).collect(Collectors.toList());
        citiesUF.sort(Comparator.comparing(Cidade::getCity));
        return citiesUF;
    }

    public Cidade procurarCidade(Integer ibgeId) {
        Optional<Cidade> obj = repository.findById(ibgeId);
        return obj.orElse(null);
    }

    public void deletarCidade(Integer ibgeId) {
        repository.delete(procurarCidade(ibgeId));
    }

    public void criarCidade(Cidade obj) {
        repository.save(obj);
    }

    public int calcularRegistros() {
        return repository.findAll().size();
    }

    public List<QuantidadeUF> contarUFPorCidade() {
        List<QuantidadeUF> listSize = new ArrayList<>();
        for (String uf : Util.getUfs()) {
            QuantidadeUF QuantidadeUF = new QuantidadeUF();
            QuantidadeUF.setUF(uf);
            QuantidadeUF.setQtd(procurarUF(uf).size());
            listSize.add(QuantidadeUF);
        }
        listSize.sort(Comparator.comparing(QuantidadeUF::getUF));
        return listSize;
    }

    public List<Cidade> buscaTermo(String coluna, String termo) {
        List<Cidade> buscaTermo = new ArrayList<>();
        switch (coluna) {
            case "UF":
                buscaTermo = repository.findAll().stream().filter(cidade ->
                        cidade.getUf().toUpperCase().equals(termo.toUpperCase())).collect(Collectors.toList());
                break;
            case "CITY":
                buscaTermo = repository.findAll().stream().filter(cidade ->
                        cidade.getCity().toUpperCase().equals(termo.toUpperCase())).collect(Collectors.toList());
                break;
            case "CITYNOACCENTS":
                buscaTermo = repository.findAll().stream().filter(cidade ->
                        cidade.getCityNoAccents().toUpperCase().equals(termo.toUpperCase())).collect(Collectors.toList());
                break;
            case "ALTERNATIVENAMES":
                buscaTermo = repository.findAll().stream().filter(cidade ->
                        cidade.getAlternativeNames().toUpperCase().equals(termo.toUpperCase())).collect(Collectors.toList());
                break;
            case "MICROREGION":
                buscaTermo = repository.findAll().stream().filter(cidade ->
                        cidade.getMicroregion().toUpperCase().equals(termo.toUpperCase())).collect(Collectors.toList());
                break;
            case "MESOREGION":
                buscaTermo = repository.findAll().stream().filter(cidade ->
                        cidade.getMesoregion().toUpperCase().equals(termo.toUpperCase())).collect(Collectors.toList());
                break;
        }
        return buscaTermo;
    }
}
