package br.com.marcosilva.gestao_de_vagas.modules.candidate.usecases;

import br.com.marcosilva.gestao_de_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.marcosilva.gestao_de_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) {
        var expiresIn = Instant.now().plus(Duration.ofMinutes(30));
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password in incorrect");
                });

        var passwordMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new ArithmeticException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

       var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .access_token(token)
               .expires_in(expiresIn.toEpochMilli())
                .build();

       return authCandidateResponse;
    }
}
