package sk.javakurz.kniznica.api;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import sk.javakurz.kniznica.domain.Kniha2;
import sk.javakurz.kniznica.domain.Kniha2Repository;

@RestController
public class Kniznica2Controller {
	private static final Logger LOG = LoggerFactory.getLogger( Kniznica2Controller.class );
	
	@Autowired
	private Kniha2Repository knihaRepository;

    @PostMapping("/books2")
    @Transactional
    public void createKniha( @RequestBody Kniha2Dto knihaDto ) {
    	Kniha2 kniha = new Kniha2( knihaDto.nazov, knihaDto.autor );
    	kniha.setPocetKusov(knihaDto.pocetKusov);
    	knihaRepository.save(kniha);
    	LOG.debug( "Kniha {} od {} ulozena s id {}", kniha.getNazov(), kniha.getAutor(), kniha.getId() );
    }
    
    @GetMapping("/books2")
    public Collection<Kniha2Dto> getAllBooks() {
    	Collection<Kniha2> knihy = knihaRepository.findAll();
    	LOG.debug( "Bolo nájdených {} kníh v zozname", knihy.size() );
    	
    	return knihy.stream()
    			.map( kniha -> toDto( kniha ) )
    			.toList();
    }

    @GetMapping("/books2/{id}")
    public Kniha2Dto getBook( @PathVariable long id ) {
    	Optional<Kniha2> kniha = knihaRepository.findById( id );
    	return kniha.map( k -> toDto(k) ).orElse(null);
    }

	private Kniha2Dto toDto( Kniha2 kniha ) {
		Kniha2Dto result = new Kniha2Dto();
		result.id = kniha.getId();
		result.autor = kniha.getAutor();
		result.nazov = kniha.getNazov();
		result.pocetKusov = kniha.getPocetKusov();
		return result;
	}
    

}
