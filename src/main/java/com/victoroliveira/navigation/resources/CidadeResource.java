package com.victoroliveira.navigation.resources;

import com.victoroliveira.navigation.domain.Cidade;
import com.victoroliveira.navigation.domain.TotalRegistros;
import com.victoroliveira.navigation.services.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Cidades")
@CrossOrigin(origins = "*")
public class CidadeResource {

    @Autowired
    private CidadeService service;

    @RequestMapping(value = "/capitais", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna uma lista de capitais")
    private ResponseEntity<?> procurarCapitais() {
        try {
            return ResponseEntity.ok().body(service.procurarCapitais());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar as informações");
        }
    }

    @RequestMapping(value = "/calcularRegistros", method = RequestMethod.GET)
    @ApiOperation(value = "Calcula a quantidade de registros")
    private ResponseEntity<?> calcularRegistros() {
        try {
            TotalRegistros totalRegistros = new TotalRegistros();
            totalRegistros.setMsg("Total de registros");
            totalRegistros.setQtd(service.calcularRegistros());
            return ResponseEntity.ok().body(totalRegistros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar as informações");
        }
    }

    @RequestMapping(value = "/{ibgeId}", method = RequestMethod.GET)
    @ApiOperation(value = "Busca cidade pelo ibgeID")
    private ResponseEntity<?> procurarCidade(@PathVariable Integer ibgeId) {
        try {
            Cidade obj = service.procurarCidade(ibgeId);
            return ResponseEntity.ok().body(obj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar as informações");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Retorna todas as cidades da UF informada")
    private ResponseEntity<?> procurarUF(@RequestParam(value = "uf") String uf) {
        try {
            List<Cidade> citiesUF = service.procurarUF(uf);
            return ResponseEntity.ok().body(citiesUF);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar as informações");
        }
    }

    @RequestMapping(value = "/{ibgeId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deleta cidade, informando ibgeID")
    private ResponseEntity<?> deletarCidade(@PathVariable Integer ibgeId) {
        try {
            service.deletarCidade(ibgeId);
            return ResponseEntity.ok().body("Deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar as informações");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Registra cidade informada")
    private ResponseEntity<?> criarCidade(@RequestBody Cidade obj) {
        try {
            service.criarCidade(obj);
            return ResponseEntity.ok().body("Cidade Registrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar as informações");
        }
    }

    @RequestMapping(value = "/contarUFPorCidade", method = RequestMethod.GET)
    @ApiOperation(value = "Contabiliza quantas cidades contém a cada UF")
    private ResponseEntity<?> contarUFPorCidade() {
        try {
            return ResponseEntity.ok().body(service.contarUFPorCidade());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar as informações");
        }
    }

    @RequestMapping(value = "buscarTermo",method = RequestMethod.GET)
    @ApiOperation(value = "Busca o termo na coluna informada (UF,CITY,CITYNOACCENTS,ALTERNATIVENAMES,MICROREGION,MESOREGION)")
    private ResponseEntity<?> procurarTermo(@RequestParam(value = "coluna") String coluna, @RequestParam(value = "termo") String termo) {
        try {
            return ResponseEntity.ok().body(service.buscaTermo(coluna, termo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar as informações");
        }
    }
}
