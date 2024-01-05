package com.example.MoviesSystem.features.users.services.contracts;

import com.example.MoviesSystem.features.users.models.UserFormModel;

public interface UserService {
    void create(UserFormModel model);

    boolean isExistByUsername(String username);

    String getRoleNameByUsername(String username);
}
