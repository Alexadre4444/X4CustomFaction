package io.tbbc.cf.turret;

import io.tbbc.cf.bullet.Bullet;
import io.tbbc.cf.common.BadArgumentException;
import io.tbbc.cf.production.ProductionMethod;
import io.tbbc.cf.property.PropertyDefinition;
import io.tbbc.cf.turret.chassis.TurretChassis;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.tbbc.cf.common.State.DEPLOYED;

@ApplicationScoped
public class TurretValidator {

    private void validateLabel(TurretCreate turret) {
        if (turret.getLabel() == null || turret.getLabel().length() < 3) {
            throw new BadArgumentException("Turret label should have at least 3 characters.");
        }
        if (turret.getLabel().length() > 32) {
            throw new BadArgumentException("Turret label should have less than 33 characters.");
        }
    }

    public void validateCreateEmpty(TurretCreate turret) {
        validateLabel(turret);
        validateSize(turret);
    }

    private void validateSize(TurretCreate turret) {
        if (turret.getSize() == null) {
            throw new BadArgumentException("Turret size is required.");
        }
    }

    public void validateUpdate(Turret turret, Function<Long, Optional<Turret>> getTurretByIdFunction,
                               Function<String, Optional<TurretChassis>> getChassisByNameFunction,
                               Function<String, Optional<Bullet>> getBulletByNameFunction,
                               Function<String, Optional<ProductionMethod>> getProductionMethodByNameFunction,
                               Supplier<List<PropertyDefinition>> getPropertyDefinitions,
                               FunctionalComputeTurretPoints functionalComputeTurretPoints,
                               Supplier<Integer> getTurretPointsLimit) {
        validateLabel(turret);
        validateDescription(turret);
        validateUpdateSize(turret, getTurretByIdFunction);
        validateLabel(turret);
        validateChassis(turret, getChassisByNameFunction);
        validateBullet(turret, getBulletByNameFunction, getChassisByNameFunction);
        validateCustomizers(turret);
        validatePropertyCustomizers(turret, getPropertyDefinitions, getChassisByNameFunction, getBulletByNameFunction,
                functionalComputeTurretPoints, getTurretPointsLimit);
        validateProductionMethods(turret, getProductionMethodByNameFunction);
    }

    private void validatePropertyCustomizers(Turret turret, Supplier<List<PropertyDefinition>> getPropertyDefinitions,
                                             Function<String, Optional<TurretChassis>> getChassisByNameFunction,
                                             Function<String, Optional<Bullet>> getBulletByNameFunction,
                                             FunctionalComputeTurretPoints functionalComputeTurretPoints,
                                             Supplier<Integer> getTurretPointsLimit) {
        turret.getPropertyCustomizers()
                .forEach(propertyCustomizerValue -> getPropertyDefinitions.get().stream()
                        .filter(propertyDefinition -> propertyDefinition.name().name().equals(propertyCustomizerValue.getPropertyName()))
                        .findFirst()
                        .orElseThrow(() -> new BadArgumentException("Property with name %s does not exist.".formatted(propertyCustomizerValue.getPropertyName()))));
        Map<String, Integer> propertiesValue = turret.getPropertyCustomizers().stream()
                .collect(Collectors.toMap(PropertyCustomizerValue::getPropertyName, PropertyCustomizerValue::getPropertyModifier));
        int turretPoints = functionalComputeTurretPoints.computeCost(getChassisByNameFunction.apply(turret.getChassisName()).orElseThrow(),
                getBulletByNameFunction.apply(turret.getBulletName()).orElseThrow(),
                getPropertyDefinitions.get(), propertiesValue);
        if (turretPoints > getTurretPointsLimit.get()) {
            throw new BadArgumentException("Turret points %s exceed the limit %s.".formatted(turretPoints, getTurretPointsLimit.get()));
        }
    }

    private void validateProductionMethods(Turret turret, Function<String, Optional<ProductionMethod>> getProductionMethodByNameFunction) {
        if (turret.getMethods().isEmpty()) {
            throw new BadArgumentException("Turret production methods are required.");
        }
        turret.getMethods().forEach(method -> {
            getProductionMethodByNameFunction.apply(method.getName()).orElseThrow(() ->
                    new BadArgumentException("Production method with name %s does not exist.".formatted(method.getName()))
            );
        });
    }

    private void validateDescription(Turret turret) {
        if (turret.getDescription() != null && turret.getDescription().length() > 255) {
            throw new BadArgumentException("Turret description should have less than 256 characters.");
        }
    }

    private void validateUpdateSize(Turret turret, Function<Long, Optional<Turret>> getTurretByIdFunction) {
        Turret existingTurret = getTurretByIdFunction.apply(turret.getId()).orElseThrow(() ->
                new BadArgumentException("Turret with id %s does not exist.".formatted(turret.getId()))
        );
        if (!existingTurret.getSize().equals(turret.getSize())) {
            throw new BadArgumentException("Turret size cannot be updated.");
        }
    }

    private void validateCustomizers(Turret turret) {
        if (!turret.getCustomizers().isEmpty()) {
            throw new BadArgumentException("Turret customizers cannot be updated.");
        }
    }

    private void validateBullet(Turret turret, Function<String, Optional<Bullet>> getBulletByNameFunction,
                                Function<String, Optional<TurretChassis>> getChassisByNameFunction) {
        if (turret.getBulletName() == null) {
            throw new BadArgumentException("Turret bullet is required.");
        }
        if (turret.getBulletSkinName() == null) {
            throw new BadArgumentException("Turret bullet skin is required.");
        }
        Bullet bullet = getBulletByNameFunction.apply(turret.getBulletName()).orElseThrow(() ->
                new BadArgumentException("Turret bullet with name %s does not exist.".formatted(turret.getBulletName()))
        );
        TurretChassis chassis = getChassisByNameFunction.apply(turret.getChassisName()).orElseThrow(() ->
                new BadArgumentException("Turret chassis with name %s does not exist.".formatted(turret.getChassisName()))
        );
        if (!turret.getSize().equals(bullet.size())) {
            throw new BadArgumentException("Turret size %s is not compatible with bullet size %s."
                    .formatted(turret.getSize(), bullet.size()));
        }
        bullet.availableSkins().stream()
                .filter(bulletSkin -> bulletSkin.name().equals(turret.getBulletSkinName()))
                .findAny()
                .orElseThrow(() -> new BadArgumentException("Turret bullet skin with name %s does not exist.".formatted(turret.getBulletSkinName())));
        bullet.compatibleChassis().stream()
                .filter(chassisType -> chassisType.equals(chassis.type()))
                .findAny()
                .orElseThrow(() -> new BadArgumentException("Turret bullet %s is not compatible with chassis %s."
                        .formatted(turret.getLabel(), turret.getLabel())));
    }

    private void validateChassis(Turret turret, Function<String, Optional<TurretChassis>> getChassisByNameFunction) {
        if (turret.getChassisName() == null) {
            throw new BadArgumentException("Turret chassis is required.");
        }
        if (turret.getChassisSkinName() == null) {
            throw new BadArgumentException("Turret chassis skin is required.");
        }
        TurretChassis chassis = getChassisByNameFunction.apply(turret.getChassisName()).orElseThrow(() ->
                new BadArgumentException("Turret chassis with name %s does not exist.".formatted(turret.getChassisName()))
        );
        if (!turret.getSize().equals(chassis.size())) {
            throw new BadArgumentException("Turret size %s is not compatible with chassis %s."
                    .formatted(turret.getSize(), chassis.size()));
        }
        chassis.availableSkins().stream()
                .filter(chassisSkin -> chassisSkin.name().equals(turret.getChassisSkinName()))
                .findAny()
                .orElseThrow(() -> new BadArgumentException("Turret chassis skin with name %s does not exist.".formatted(turret.getChassisSkinName())));
    }

    public void validateDelete(Turret turret) {
        if (turret.getState().equals(DEPLOYED)) {
            throw new BadArgumentException("Turret %s is deployed and cannot be deleted.".formatted(turret.getLabel()));
        }
    }
}
