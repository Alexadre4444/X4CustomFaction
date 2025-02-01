package io.tbbc.cf.bullet;

import java.util.List;
import java.util.Optional;

public interface IBulletRepository {
    Optional<Bullet> getBulletTemplateByName(String name);

    List<Bullet> getAll();
}
