package dev.jsonmusk.managers;

import io.javalin.http.ForbiddenResponse;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Base64;

public class TokenManagerImpl implements TokenManager{

    private final Key key;

    public TokenManagerImpl() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }


    public String issueTokenToUserId(int userId) {
        String userIdString = String.valueOf(userId);
        //  String userIdBase64String = encodeIntToBase64String(userId);
        String token = Jwts.builder().setSubject(userIdString).signWith(key).compact();
        return token;
    }


    public boolean authorize(String token, int userId) {
        try {
            String userIdString = String.valueOf(userId);
            //  String userIdBase64String = encodeIntToBase64String(userId);
            String subject = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
            return subject.equalsIgnoreCase(userIdString);
        } catch (Exception ex){
            throw new ForbiddenResponse();
        }
    }

//    public String encodeIntToBase64String(int num) {
//        byte[] bytes =  new byte[] {
//                (byte)((num >> 24) & 0xff),
//                (byte)((num >> 16) & 0xff),
//                (byte)((num >> 8) & 0xff),
//                (byte)((num >> 0) & 0xff),
//        };
//        Base64.Encoder encoder = Base64.getEncoder();
//        String stringValue = encoder.encodeToString(bytes);
//        return stringValue;
//    }
//
//    public int decodeBase64StringToInt(String string) {
//        Base64.Decoder decoder = Base64.getDecoder();
//        byte[] bytes = decoder.decode(string);
//
//        if (bytes == null || bytes.length != 4) return 0x0;
//
//        int num = (int)(
//                (0xff & bytes[0]) << 24  |
//                        (0xff & bytes[1]) << 16  |
//                        (0xff & bytes[2]) << 8   |
//                        (0xff & bytes[3]) << 0
//        );
//        return num;
//    }


}
