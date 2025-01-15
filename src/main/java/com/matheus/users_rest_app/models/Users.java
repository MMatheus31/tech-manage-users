package com.matheus.users_rest_app.models;

import com.matheus.users_rest_app.utils.EnumUserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table
public class Users {

    @Id
    @NotNull(message = "Id não pode ser nulo!")
    @NotBlank
    private Long id;

    @NotNull(message = "Nome não pode ser nulo!")
    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Email
    @NotNull(message = "Email não pode ser nulo!")
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @NotNull(message = "Telefone não pode ser nulo!")
    @Pattern(
            regexp = "^\\+[1-9][0-9]\\s\\d{2}\\s\\d{4,5}-\\d{4}$",
            message = "O telefone deve estar no formato internacional, exemplo: +55 11 99999-9999."
    )
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @NotBlank(message = "Data de nascimento não pode ser nula!")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "Função não pode ser nulo!")
    @Column(name = "user_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private EnumUserType userType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public EnumUserType getUserType() {
        return userType;
    }

    public void setUserType(EnumUserType userType) {
        this.userType = userType;
    }

    public Users(Long id, String fullName, String email, String phone, LocalDate birthDate, EnumUserType userType) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.userType = userType;
    }

    public Users() {}

    public Users(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
