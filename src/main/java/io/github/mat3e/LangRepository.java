package io.github.mat3e;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {

    private List<Lang> languages;

    public LangRepository() {
        languages = new ArrayList<>();
        languages.add(new Lang(1, "Hello", "en"));
        languages.add(new Lang(2, "Siemanko", "pl"));
    }

    Optional<Lang> findByID(Integer id) {
        return languages.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }
}
