package co.coldflow.depot_music.service;

import co.coldflow.depot_music.dto.ChangePasswordRequestDto;
import co.coldflow.depot_music.dto.ChangePasswordResponseDto;
import co.coldflow.depot_music.dto.EResponseStatus;
import co.coldflow.depot_music.entity.Account;
import co.coldflow.depot_music.entity.EUserRole;
import co.coldflow.depot_music.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.extras.springsecurity5.util.SpringSecurityContextUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@Service
@Transactional
public class AccountService implements UserDetailsService {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account createAccount(String username, String password, EUserRole role){
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setRole(role);
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(account.getUsername(), account.getPassword(), getAuthorities(account));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Account account) {
        return Arrays.asList(new SimpleGrantedAuthority(account.getRole().toString()));
    }

    public ChangePasswordResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("접속한 사용자의 정보가 없습니다."));

        if(!changePasswordRequestDto.getNewPassword().equals(changePasswordRequestDto.getCheckPassword())) {
            return new ChangePasswordResponseDto(
                    EResponseStatus.ERROR,
                    "입력하신 비밀번호가 일치하지 않습니다."
            );
        }

        if(!passwordEncoder.matches(changePasswordRequestDto.getPrevPassword(), account.getPassword())){
            return new ChangePasswordResponseDto(
                    EResponseStatus.ERROR,
                    "이전 비밀번호가 맞지 않습니다."
            );
        }

        account.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
        return new ChangePasswordResponseDto(
                EResponseStatus.SUCCESS,
                "비밀번호가 변경되었습니다."
        );
    }
}
