package com.example.MoviesSystem.data.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
public class ApplicationUser extends BaseModel {
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "applicationrole_id", nullable = false)
    private ApplicationRole role;
}
