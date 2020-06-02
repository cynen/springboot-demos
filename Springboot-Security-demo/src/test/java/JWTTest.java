import com.cynen.ssd.util.JwtUtils;
import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.*;

public class JWTTest {


    @Test
    public void main() {

        Long exp = System.currentTimeMillis() + 1000* 10 * 100 ;
        JwtBuilder builder = Jwts.builder().setId("1")
                .setSubject("你好")
                .setIssuedAt(new Date())
                .setExpiration(new Date(exp))
                .signWith(SignatureAlgorithm.HS256,"cynen");
        System.out.println(builder.compact());
    }

    @Test
    public void test(){

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoi5L2g5aW9IiwiaWF0IjoxNTkxMDY3MzY5LCJleHAiOjE1OTEwNjgzNjl9.KPpHaODRvhrYxrQ2C3Mn2BZqYRABGCXgelGi8RaehaQ";

        Claims claims = Jwts.parser().setSigningKey("cynen0111111111111").parseClaimsJws(token).getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getSubject());

    }

    @Test
    public void test2(){
        JwtUtils utils = new JwtUtils();
        List<String> roles = new ArrayList<>();
        String jwt = utils.creatJWT("1", "Nih", roles);

        Claims claims = utils.parseJWT(jwt);
        System.out.println(claims.getSubject());

    }

}
