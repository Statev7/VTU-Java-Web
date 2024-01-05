package com.example.MoviesSystem.features.users.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserFormModel {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
