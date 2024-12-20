package edu.miu.cse.finalproject.auth;

import edu.miu.cse.finalproject.config.JwtService;
import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Transactional
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        //Construct user object from registerRequest
        User user = new User(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.email(),
                registerRequest.role(),
                registerRequest.category()
        );
        //save the user
        User registeredUser = userRepository.save(user);
        //generate token
        String token = jwtService.generateToken(registeredUser);
        return new AuthenticationResponse(token);
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );

        User user = (User)authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
