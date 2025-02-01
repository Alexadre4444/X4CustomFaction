package io.tbbc.cf.bullet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BulletService implements IBulletService {
    @Inject
    private IBulletRepository bulletTemplateRepository;

    @Override
    public Optional<Bullet> getByName(String name) {
        return bulletTemplateRepository.getBulletTemplateByName(name);
    }

    @Override
    public List<Bullet> getAll() {
        return bulletTemplateRepository.getAll();
    }
}
