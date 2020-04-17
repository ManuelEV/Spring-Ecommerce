package cl.dci.eshop.security;

public enum ApplicationUserPermission {
    PRODUCTO_READ("producto:read"),
    PRODUCTO_WRITE("producto:write"),
    PRODUCTO_UPDATE("producto:update"),
    PRODUCTO_DELETE("producto:delete"),
    CARRITO_MANAGE("carrito:manage"),
    PERFIL_MANAGE("perfil:manage"),
    USUARIO_READ("usuario:read"),
    USUARIO_WRITE("usuario:write"),
    USUARIO_UPDATE("usuario:update"),
    USUARIO_DELETE("usuario:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
