package com.example.MoviesSystem.features.users.services.contracts;

import com.example.MoviesSystem.data.models.ApplicationUser;
import com.example.MoviesSystem.features.users.models.UserFormModel;

public interface UserService {
    void Create(UserFormModel model);

    boolean IsExistByUsername(String username);

    String getRoleNameByUsername(String username);
}
