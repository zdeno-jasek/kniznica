package sk.javakurz.kniznica.api;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import sk.javakurz.kniznica.domain.Osoba;
import sk.javakurz.kniznica.domain.OsobaRepository;

/*
 * Táto trieda umožňuje volať jednotlivé metódy pomocou HTTP protokolu.
 * Takto vypublikovaným metódam hovoríme "služby".
 * Používa sa aj pojem API = Application Programming Interface = aplikačné rozhranie.
 * Obvykle sa využíva GET na čítanie a POST na zápis.
 */
@RestController
public class KniznicaController {
	/* 
	 * Obdoba System.out.println. 
	 * Používa sa na zapisovanie do logov systému namiesto vypisovania na obrazovku.
	 * Jednoduchá implementácia triedy Logger robí aj výpisy na obrazovku kvôli ladeniu počas vývoja.
	 */
	private static final Logger LOG = LoggerFactory.getLogger( KniznicaController.class );
	
	@Autowired
	private OsobaRepository osobaRepository;

	/*
	 * Účelom tejto služby je iba overiť, či vieme služby volať, t.j. či nám odpovedá minimálna funkčnosť.
	 */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world";
    }
    
    @PostMapping("/persons")
    @Transactional
    public void createOsoba( @RequestBody OsobaDto osobaDto ) {
    	Optional<Osoba> existujuca = osobaRepository.findByRodneCislo( osobaDto.rodneCislo );
    	if ( existujuca.isPresent() ) {
    		LOG.warn( "Osoba s rodnym cislom {} uz existuje, vola sa {} {}", existujuca.get().getRodneCislo(), existujuca.get().getMeno(), existujuca.get().getPriezvisko() );
    	} else {
    		Osoba osoba = new Osoba( osobaDto.meno, osobaDto.priezvisko, osobaDto.rodneCislo );
    		osobaRepository.save( osoba );
    		LOG.debug( "Osoba s rodnym cislom {} ulozena s id {}", osoba.getRodneCislo(), osoba.getId() );
    	}
    }
    
    @GetMapping("/persons")
    public Collection<OsobaDto> getAllPersons() {
    	Collection<Osoba> persons = osobaRepository.findAll();
    	LOG.debug( "Bolo nájdených {} osôb v zozname", persons.size() );
    	
    	return persons.stream().map( osoba -> toDto( osoba ) ).toList();
    }

	private OsobaDto toDto( Osoba osoba ) {
		OsobaDto result = new OsobaDto();
		result.id = osoba.getId();
		result.meno = osoba.getMeno();
		result.priezvisko = osoba.getPriezvisko();
		result.rodneCislo = osoba.getRodneCislo();
		return result;
	}
    

}
