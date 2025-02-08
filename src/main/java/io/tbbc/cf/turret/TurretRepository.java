package io.tbbc.cf.turret;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import io.tbbc.cf.common.production.ProductionMethodInstances;
import io.tbbc.cf.common.production.ProductionMethodName;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TurretRepository implements ITurretRepository, PanacheRepository<Turret> {
    private static void updateCustomizers(Turret turret, Turret existingTurret) {
        // Add new customizers
        turret.getCustomizers().stream()
                .filter(customizer -> existingTurret.getCustomizers().stream()
                        .noneMatch(existingCustomizer -> existingCustomizer.getCategoryName()
                                .equals(customizer.getCategoryName())))
                .forEach(customizerValue -> existingTurret.getCustomizers().add(customizerValue));
        // Update existing
        existingTurret.getCustomizers().forEach(existingCustomizer -> turret.getCustomizers().stream()
                .filter(customizer -> customizer.getCategoryName().equals(existingCustomizer.getCategoryName()))
                .findFirst()
                .ifPresent(customizerValue -> {
                    existingCustomizer.setCustomizerName(customizerValue.getCustomizerName());
                }));
        // Remove deleted
        existingTurret.getCustomizers().removeIf(existingCustomizer -> turret.getCustomizers().stream()
                .noneMatch(customizer -> customizer.getCategoryName().equals(existingCustomizer.getCategoryName())));
    }

    @Override
    public List<Turret> getAll() {
        return listAll(Sort.ascending("label"))
                .stream().map(this::addDefaultMethods)
                .toList();
    }

    @Override
    public void insert(Turret turret) {
        persist(turret);
    }

    @Override
    public void update(Turret turret) {
        getById(turret.getId()).ifPresent(existingTurret -> {
            existingTurret.setLabel(turret.getLabel());
            existingTurret.setDescription(turret.getDescription());
            existingTurret.setChassisName(turret.getChassisName());
            existingTurret.setChassisSkinName(turret.getChassisSkinName());
            existingTurret.setBulletName(turret.getBulletName());
            existingTurret.setBulletSkinName(turret.getBulletSkinName());
            existingTurret.setState(turret.getState());
            updateCustomizers(turret, existingTurret);
            updateMethods(turret, existingTurret);
            existingTurret.setSize(turret.getSize());
        });
    }

    private void updateMethods(Turret turret, Turret existingTurret) {
        // Add new methods
        turret.getMethods().stream()
                .filter(method -> existingTurret.getMethods().stream()
                        .noneMatch(existingMethod -> existingMethod.getName().equals(method.getName())))
                .forEach(productionMethod -> existingTurret.getMethods().add(productionMethod));
        // Remove deleted
        existingTurret.getMethods().removeIf(existingMethod -> turret.getMethods().stream()
                .noneMatch(method -> method.getName().equals(existingMethod.getName())));
    }

    @Override
    public Optional<Turret> getById(long id) {
        return find("id", id).stream()
                .map(this::addDefaultMethods)
                .findFirst();
    }

    @Override
    public void delete(long id) {
        delete("id", id);
    }

    private Turret addDefaultMethods(Turret turret) {
        ProductionMethodName defaultMethod = new ProductionMethodName();
        defaultMethod.setName(ProductionMethodInstances.CW.name());
        turret.getMethods().add(defaultMethod);
        return turret;
    }
}
