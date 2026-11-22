package code.repositories;

import code.ShortCode;

public interface ShortCodeRepository {
    ShortCode get(String shortCode);

    boolean isPresent(String shortCode);

    boolean remove(String shortCode);

    boolean add(ShortCode shortCode);
}
