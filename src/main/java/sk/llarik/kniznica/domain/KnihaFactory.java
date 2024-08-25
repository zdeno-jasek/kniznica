package sk.llarik.kniznica.domain;

import org.slf4j.Logger;
import sk.llarik.kniznica.infrastructure.repository.KnihaRepository;

import java.util.Collection;
import java.util.Objects;

public class KnihaFactory {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(KnihaFactory.class);
    private final KnihaRepository knihaRepository;

    public KnihaFactory(KnihaRepository knihaRepository) {
        this.knihaRepository = Objects.requireNonNull(knihaRepository);
    }

    public Kniha create(String nazov, String autor, Collection<String> serioveCisla) {
        if (knihaRepository.findByVytlackySerioveCisloIn(serioveCisla).size() > 0) {
            LOG.error("Seriove cisla sa uz pouzivaju");
            throw new IllegalArgumentException("Seriove cisla sa uz pouzivaju");
        }
        LOG.debug("create(nazov={}, autor={}, serioveCisla={})", nazov, autor, serioveCisla);
        return new Kniha(nazov, autor, serioveCisla);
    }
}
