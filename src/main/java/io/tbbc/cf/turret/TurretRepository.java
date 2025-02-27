package io.tbbc.cf.turret;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import io.tbbc.cf.production.ProductionMethodInstances;
import io.tbbc.cf.production.ProductionMethodName;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TurretRepository implements ITurretRepository, PanacheRepository<Turret> {
    private void updatePropertyCustomizerValue(Turret turret, Turret existingTurret) {
        // Add new values
        turret.getPropertyCustomizers().stream()
                .filter(value -> existingTurret.getPropertyCustomizers().stream()
                        .noneMatch(existingValue -> existingValue.getPropertyName().equals(value.getPropertyName())))
                .forEach(customizerValue -> existingTurret.getPropertyCustomizers().add(customizerValue));
        // Update existing
        existingTurret.getPropertyCustomizers().forEach(existingValue -> turret.getPropertyCustomizers().stream()
                .filter(value -> value.getPropertyName().equals(existingValue.getPropertyName()))
                .findFirst()
                .ifPresent(customizerValue -> {
                    existingValue.setPropertyModifier(customizerValue.getPropertyModifier());
                }));

        // Remove deleted
        existingTurret.getPropertyCustomizers().removeIf(existingValue -> turret.getPropertyCustomizers().stream()
                .noneMatch(value -> value.getPropertyName().equals(existingValue.getPropertyName())));
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
            updateMethods(turret, existingTurret);
            updatePropertyCustomizerValue(turret, existingTurret);
            existingTurret.setSize(turret.getSize());
        });
    }

    private void updateMethods(Turret turret, Turret existingTurret) {
        // Add new methods
        turret.getMethods().stream()
                .filter(method -> existingTurret.getMethods().stream()
                        .noneMatch(existingMethod -> existingMethod.getName().equals(method.getName())))
                .forEach(productionMethod -> {
                    existingTurret.getMethods().add(productionMethod);
                });
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
        if (turret.getMethods().isEmpty()) {
            ProductionMethodName defaultMethod = new ProductionMethodName();
            defaultMethod.setName(ProductionMethodInstances.CW.name());
            turret.getMethods().add(defaultMethod);
        }
        return turret;
    }
}
