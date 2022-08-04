package com.sparta.springjwt.security.provider;


import com.auth0.jwt.JWT;
import com.sparta.springjwt.model.User;
import com.sparta.springjwt.repository.UserRepository;
import com.sparta.springjwt.security.UserDetailsImpl;
import com.sparta.springjwt.security.jwt.JwtDecoder;
import com.sparta.springjwt.security.jwt.JwtPreProcessingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;

    private final UserRepository userRepository;

    @Override               // JwtAuthFilter 에서 넘어온, jwtToken 을 담고있는 authenticate
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String token = (String) authentication.getPrincipal();    // authenticate 에 있는 token 을 가지고
        String username = jwtDecoder.decodeUsername(token);       // username 복호화를 진행.
            // 유효한 token 인지 체크하는 로직이 decodeUsername 안에 있다. 유효하다면 Jwt 본문 페이로드에서 username을 리턴해 빼낸다.

//         TODO: API 사용시마다 매번 User DB 조회 필요
//          -> 해결을 위해서는 UserDetailsImpl 에 User 객체를 저장하지 않도록 수정
//          ex) UserDetailsImpl 에 userId, username, role 만 저장
//            -> JWT 에 userId, username, role 정보를 암호화/복호화하여 사용

        User user = userRepository.findByUsername(username)     // 위에서 빼낸 username 을 가지고 DB 에서 user 를 찾아서
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));;
        UserDetailsImpl userDetails = new UserDetailsImpl(user);        // UserDetailsImpl 를 만들어서
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }                       // 컨트롤러에서 인증된 사용자 정보를 가지고 UserDetailsImpl 를 사용하도록 된다.

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
