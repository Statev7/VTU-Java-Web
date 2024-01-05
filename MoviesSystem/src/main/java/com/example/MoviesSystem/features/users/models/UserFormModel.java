package com.example.MoviesSystem.features.users.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserFormModel {
    @NotEmpty
    public String username;
    @NotEmpty
    public String password;
}
