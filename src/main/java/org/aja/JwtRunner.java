package org.aja;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtRunner {

    private final static String MESSAGE = "Knows his shit:)";
    private final static String MESSAGE_1 = "Knows his shit:)";
    private final static String MESSAGE_2 = "Knows his shit:(";



    public static void main(String...args) {
        new JwtRunner().init();
    }

    private void init() {

        Date now = new Date(System.currentTimeMillis());

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secret_key");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //claims = paylod = body
        JwtBuilder builder = Jwts.builder().setId("671030")
                .setIssuedAt(now)
                .setSubject("Jonas")
                .setIssuer("org.aja")
                .signWith(signatureAlgorithm, signingKey);

        String jwt = builder.compact();
        System.out.println("jwt: " + jwt);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("secret_key"))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());

    }
}
