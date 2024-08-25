package sk.llarik.kniznica.infrastructure.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.llarik.kniznica.domain.CitatelskyPreukaz;
import sk.llarik.kniznica.domain.CitatelskyPreukazRepository;
import sk.llarik.kniznica.infrastructure.dto.CitatelskyPreukazDto;
import sk.llarik.kniznica.infrastructure.dto.CitatelskyPreukazReadDto;

import java.util.Optional;
import java.util.function.Function;

@RestController
public class CitatelskyPreukazController {

    @Autowired
    private CitatelskyPreukazRepository citatelskyPreukazRepository;
    @Autowired
    private Function<CitatelskyPreukaz,CitatelskyPreukazReadDto> citatelskyPreukazAssembler;

    @Transactional
    @PostMapping("/readers-card")
    public void create(@RequestBody CitatelskyPreukazDto dto) {
        CitatelskyPreukaz citatelskyPreukaz = new CitatelskyPreukaz( dto.meno, dto.priezvisko, citatelskyPreukazRepository.getNextSequenceValue());
        citatelskyPreukazRepository.save(citatelskyPreukaz);
    }

    @GetMapping("/readers-card/{id}")
    public CitatelskyPreukazReadDto get(@PathVariable long id) {
        Optional<CitatelskyPreukaz> citatelskyPreukaz = citatelskyPreukazRepository.findById(id);
        return citatelskyPreukaz.map(citatelskyPreukazAssembler).orElse(null);
    }


}
