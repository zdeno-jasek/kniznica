package sk.llarik.kniznica.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sk.llarik.kniznica.domain.*;
import sk.llarik.kniznica.infrastructure.dto.KnihaSCislomDto;
import sk.llarik.kniznica.infrastructure.dto.PozickaDto;
import sk.llarik.kniznica.infrastructure.dto.PozickaReadDto;
import sk.llarik.kniznica.infrastructure.repository.KnihaRepository;

import java.util.Collection;
import java.util.function.Function;

@RestController
public class PozickaController {

    @Autowired
    private CitatelskyPreukazRepository citatelskyPreukazRepository;
    @Autowired
    private PozickaRepository pozickaRepository;
    @Autowired
    private KnihaRepository knihaRepository;
    @Autowired
    private Function<Pozicka,PozickaReadDto> pozickaAssembler;

    @PostMapping("/lend-books")
    void pozicajKnihu(@RequestBody PozickaDto dto) {
        CitatelskyPreukaz citatelskyPreukaz = citatelskyPreukazRepository.findById(dto.citatelskyPreukazId)
                .orElseThrow(() -> new RuntimeException("Citatelsky preukaz s id " + dto.citatelskyPreukazId + " neexistuje"));

        for (KnihaSCislomDto knihaSCislomDto : dto.serioveCisla) {
            Kniha kniha = knihaRepository.findById(knihaSCislomDto.knihaId)
                    .orElseThrow(() -> new RuntimeException("Kniha s id " + knihaSCislomDto.knihaId + " neexistuje"));
            Pozicka pozicka = new Pozicka(kniha, citatelskyPreukaz, knihaSCislomDto.serioveCislo);
            pozickaRepository.save(pozicka);

        }
    }

    @GetMapping("/lend-books")
    Collection<PozickaReadDto> findAll() {
        return pozickaRepository.findAll().stream()
                .map(pozickaAssembler)
                .toList();
    }
}
