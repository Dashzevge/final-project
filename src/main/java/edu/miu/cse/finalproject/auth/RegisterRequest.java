package edu.miu.cse.finalproject.auth;

import edu.miu.cse.finalproject.util.Category;
import edu.miu.cse.finalproject.util.Role;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        String email,
        Role role,
        Category category
) {
}
