package sk.llarik.kniznica.infrastructure.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.llarik.kniznica.domain.Kniha;
import sk.llarik.kniznica.domain.KnihaFactory;
import sk.llarik.kniznica.infrastructure.repository.KnihaRepository;
import sk.llarik.kniznica.infrastructure.dto.KnihaDto;
import sk.llarik.kniznica.infrastructure.dto.KnihaReadDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
public class KnihyController {
    private static Logger LOG = LoggerFactory.getLogger(KnihyController.class);

    @Autowired
    private KnihaRepository knihaRepository;
    @Autowired
    private Function<Kniha,KnihaReadDto> knihaAssembler;

    public KnihyController() {
        LOG.debug("KnihyController created");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

    @PostMapping("/books")
    @Transactional
    public void createBook(@RequestBody(required = true) KnihaDto knihaDto) {
        LOG.debug("createBook({})", knihaDto);
        KnihaFactory knihaFactory = new KnihaFactory(knihaRepository);
        Kniha kniha = knihaFactory.create(knihaDto.nazov, knihaDto.autor, knihaDto.serioveCisla);
        knihaRepository.save(kniha);
    }

    @PostMapping("/books/{id}/serial-numbers")
    @Transactional
    public void addSerialNumbers(@PathVariable long id, @RequestBody(required = true) Collection<String> serioveCisla) {
        LOG.debug("addSerialNumber( id={}, serialNumbers={} )", id, serioveCisla);
        Optional<Kniha> optionalKniha = knihaRepository.findById(id);
        if (optionalKniha.isEmpty()) {
            LOG.error("Kniha s id={} neexistuje", id);
            throw new IllegalArgumentException("Kniha s id=" + id + " neexistuje");
        }

        Kniha kniha = optionalKniha.get();
        kniha.addSerialNumbers(serioveCisla);
        knihaRepository.save(kniha);
    }


    @GetMapping("/books")
    public Collection<KnihaReadDto> getBooks(@RequestParam(required = true) String nazov,
                                             @RequestParam(required = true) String autor) {
        return knihaRepository.findByAutorAndNazovIgnoreCase(autor, nazov).stream()
                .map(knihaAssembler)
                .toList();
    }

    @GetMapping("/books/serial-numbers/{serialNumber}")
    public Collection<KnihaReadDto> getBooks(@PathVariable String serialNumber) {
        return knihaRepository.findByVytlackySerioveCisloIn(List.of(serialNumber)).stream()
                .map(knihaAssembler)
                .toList();
    }

}
