package io.tbbc.cf.bullet;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static io.tbbc.cf.bullet.BulletInstances.*;

@ApplicationScoped
public class BulletRepository implements IBulletRepository {

    private final List<Bullet> bullets = Stream.of(
            M_PULSE, M_PULSE_ION, M_PLASMA, M_GATLING, M_SHOTGUN, M_BEAM, M_ELECTROMAGNETIC, M_FLAK, M_ARC, M_PHASE,
            L_PULSE, L_PLASMA, L_BEAM, L_GATLING, L_SEISMIC, L_PHASE, L_FLAK, L_DISRUPTOR
    ).sorted(Comparator.comparing(Bullet::label)).toList();

    @Override
    public Optional<Bullet> getBulletTemplateByName(String name) {
        return bullets.stream()
                .filter(bulletTemplate -> bulletTemplate.name().equals(name))
                .findFirst();
    }

    @Override
    public List<Bullet> getAll() {
        return bullets;
    }
}
