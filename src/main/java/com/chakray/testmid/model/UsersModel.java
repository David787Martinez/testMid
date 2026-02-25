package com.chakray.testmid.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author luis-barrera
 */
@Entity
@Table(name = "users", schema = "public")
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Email
    @Column(name = "email", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    private String email;

    @Column(name = "name", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    private String name;

    @Pattern(
            regexp = "^(\\+\\d{1,3})?\\d{10}$",
            message = "Teléfono inválido"
    )
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "password", nullable = false, length = 100)
    @Size(min = 8, max = 100)
    private String password;

    @Pattern(
            regexp = "^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$",
            message = "Formato RFC inválido"
    )
    @Column(name = "tax_id", length = 13, unique = true)
    @Size(min = 12, max = 13)
    private String taxId;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public UsersModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
