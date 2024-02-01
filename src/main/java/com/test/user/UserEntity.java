package com.test.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @Email
    private String email;

    //from dto to entity
    public static UserEntity fromDto(UserDto userDto) {
        return UserEntity.builder()
                .firstname(userDto.firstname())
                .lastname(userDto.lastname())
                .email(userDto.email())
                .build();
    }
}
