package io.tbbc.cf.turret;

import java.util.List;
import java.util.Optional;

public interface ITurretRepository {
    List<Turret> getAll();

    void insert(Turret turret);

    void update(Turret turret);

    Optional<Turret> getById(long id);

    void delete(long id);
}
