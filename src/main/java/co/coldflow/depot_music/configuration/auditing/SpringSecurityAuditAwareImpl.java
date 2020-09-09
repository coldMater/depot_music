package co.coldflow.depot_music.configuration.auditing;

import co.coldflow.depot_music.entity.Account;
import co.coldflow.depot_music.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class SpringSecurityAuditAwareImpl implements AuditorAware<String> {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            return Optional.empty();
        }

        if(!authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }

        User userPrincipal = (User)authentication.getPrincipal();
        String authorUsername = userPrincipal.getUsername();
        return Optional.ofNullable(authorUsername);
    }
}
