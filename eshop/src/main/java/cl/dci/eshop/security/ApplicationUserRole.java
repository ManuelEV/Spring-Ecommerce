package cl.dci.eshop.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static cl.dci.eshop.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(PRODUCTO_READ, PRODUCTO_WRITE, PRODUCTO_UPDATE, PRODUCTO_DELETE, CARRITO_MANAGE, PERFIL_MANAGE,
            USUARIO_READ, USUARIO_WRITE, USUARIO_UPDATE, USUARIO_DELETE)),
    CUSTOMER(Sets.newHashSet(PRODUCTO_READ, CARRITO_MANAGE, PERFIL_MANAGE));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
