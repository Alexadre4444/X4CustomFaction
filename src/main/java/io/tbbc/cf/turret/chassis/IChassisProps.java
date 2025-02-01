package io.tbbc.cf.turret.chassis;

import io.tbbc.cf.common.property.Property;
import io.tbbc.cf.common.property.PropertyName;

import java.util.List;
import java.util.Optional;

public interface IChassisProps {
    List<Property> getProperties();

    Optional<Property> property(PropertyName name);
}
