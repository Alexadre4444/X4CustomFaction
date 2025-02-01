package io.tbbc.cf.bullet;

import java.util.List;
import java.util.Optional;

public interface IBulletService {
    Optional<Bullet> getByName(String name);

    List<Bullet> getAll();
}
