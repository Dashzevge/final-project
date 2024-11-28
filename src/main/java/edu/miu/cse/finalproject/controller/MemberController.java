package edu.miu.cse.finalproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management")
public class MemberController {
    @GetMapping("/member")
    public String get() {
        return "member: second end point";
    }

    //@PreAuthorize("hasAnyAuthority('member:read')")
    @PreAuthorize("hasAnyAuthority('member:read','member:write')")
    @GetMapping("/member-only")
    public String memberOnly() {
        return "memberOnly: second end point";
    }
}
