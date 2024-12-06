package edu.miu.cse.finalproject.view;

import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.mapper.UserMapper;
import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.service.UserService;
import edu.miu.cse.finalproject.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        userService.findUserByName(userDetails.getUsername())
                .ifPresentOrElse(
                        dto -> model.addAttribute("user", dto),
                        () -> {
                            throw new UsernameNotFoundException("User not found: " + userDetails.getUsername());
                        }
                );
        return "home_page";
    }
}
