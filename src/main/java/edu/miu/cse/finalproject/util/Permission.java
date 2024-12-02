package edu.miu.cse.finalproject.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    ADMIN_WRITE("admin:write"),
    ADMIN_READ("admin:read"),
    CLIENT_WRITE("client:write"),
    CLIENT_READ("client:read"),
    PROFESSIONAL_WRITE("professional:write"),
    PROFESSIONAL_READ("professional:read");

    private final String permission;
}
