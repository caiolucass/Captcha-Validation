package com.example.captchavalidation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;

    @Transient
    private String captcha;

    @Transient
    private String hiddenCaptcha;

    @Transient
    private String realCaptcha;
}
