package cl.dci.eshop.security;

public enum ApplicationUserPermission {
    PRODUCTO_READ("producto:read"),
    PRODUCTO_WRITE("producto:write"),
    PRODUCTO_UPDATE("producto:update"),
    PRODUCTO_DELETE("producto:delete"),
    CARRITO_MANAGE("carrito:manage"),
    PERFIL_MANAGE("perfil:manage");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
