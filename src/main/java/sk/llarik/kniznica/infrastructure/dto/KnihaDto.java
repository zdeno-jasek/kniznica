package sk.llarik.kniznica.infrastructure.dto;

import java.util.Collection;

public class KnihaDto {
    public String nazov;
    public String autor;
    public Collection<String> serioveCisla;

    @Override
    public String toString() {
        return "KnihaDto{" +
                "nazov='" + nazov + '\'' +
                ", autor='" + autor + '\'' +
                ", serioveCisla=" + serioveCisla +
                '}';
    }
}
