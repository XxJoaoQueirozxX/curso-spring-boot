package com.cursospring.security.jwt;

import com.cursospring.domain.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {


    @Value("${security.jwt.expire-in}")
    private String expires_in;

    @Value("${security.jwt.secret-key}")
    private String secretKey;


    public String gerarToken(Usuario usuario){
        final long expiresIn = Long.parseLong(expires_in);
        LocalDateTime expiresTime = LocalDateTime.now().plusMinutes(expiresIn);
        Date data = Date.from(expiresTime.atZone(ZoneId.systemDefault()).toInstant());


//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("generatedDate", LocalDate.now().toString());

        return Jwts
                .builder()
//                .setClaims(claims) hash map com valores personalizados
                .setSubject(usuario.getUsername())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean tokenValido(String token) throws ExpiredJwtException{
        try{
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();

            LocalDateTime dtt = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return LocalDateTime.now().isBefore(dtt);
        }catch (ExpiredJwtException e){
            return false;
        }
    }


    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }

}
