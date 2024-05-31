package user;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    public static User getRandomUser() {
        Faker faker = new Faker();
        return User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(6, 10))
                .name(faker.name().firstName())
                .build();
    }

    public static User getRandomUserWithShortPassword() {
        Faker faker = new Faker();
        return User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(3, 5))
                .name(faker.name().firstName())
                .build();
    }


}
