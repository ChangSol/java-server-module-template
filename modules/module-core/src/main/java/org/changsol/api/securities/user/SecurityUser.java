package org.changsol.api.securities.user;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.changsol.api.apps.users.domain.Users;
import org.changsol.api.apps.users.dto.PrincipalDto;
import org.changsol.api.apps.users.mapper.PrincipalMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {

    @Getter
    private final PrincipalDto.Response user;

    public SecurityUser(Users user) {
        super(user.getLoginId(), user.getPassword(), getAuthorities(user));
        this.user = PrincipalMapper.INSTANCE.toPrincipal(user);
    }

    /**
     * 사용자에 대한 ROLE 지정
     */
    private static Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        Set<String> authorities = Sets.newHashSet();
        authorities.add("ROLE_USER");
        authorities.add("ROLE_" + user.getType().name());
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
