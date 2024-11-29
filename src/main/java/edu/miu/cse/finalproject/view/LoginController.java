package edu.miu.cse.finalproject.view;

import edu.miu.cse.finalproject.auth.AuthenticationRequest;
import edu.miu.cse.finalproject.auth.AuthenticationResponse;
import edu.miu.cse.finalproject.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public String login() {
        return "login_page";
    }
    @PostMapping
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Create an AuthenticationRequest object from the parameters
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);

        // Call the authenticate method
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

        // Process the AuthenticationResponse as needed (e.g., redirecting or returning a view name)
        if (authenticationResponse.token() != null) {
            return "redirect:/api/v1/home"; // Replace with your desired success path
        } else {
            return "redirect:/login?error=true"; // Replace with your error handling
        }
    }
}
