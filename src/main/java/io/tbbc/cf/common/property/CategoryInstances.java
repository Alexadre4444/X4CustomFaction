package io.tbbc.cf.common.property;

public class CategoryInstances {
    public static final Category TURRET = new Category(new CategoryName("turret"), "Turret", false);
    public static final Category BULLET = new Category(new CategoryName("bullet"), "Bullet", false);
    public static final Category COST = new Category(new CategoryName("cost"), "Cost", true);
    public static final Category DAMAGE = new Category(new CategoryName("damage"), "Damage", false);

    private CategoryInstances() {
    }
}
