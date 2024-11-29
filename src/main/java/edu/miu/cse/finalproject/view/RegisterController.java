package edu.miu.cse.finalproject.view;

import edu.miu.cse.finalproject.auth.AuthenticationResponse;
import edu.miu.cse.finalproject.auth.AuthenticationService;
import edu.miu.cse.finalproject.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public String register(){
        return "register_page";
    }
    @PostMapping
    public String register(@ModelAttribute RegisterRequest registerRequest) {
        // Create an AuthenticationRequest object from the parameters
        System.out.println("RegisterRequest: " + registerRequest);

        // Call the authenticate method
        AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);

        // Process the AuthenticationResponse as needed (e.g., redirecting or returning a view name)
        if (authenticationResponse.token() != null) {
            return "redirect:/login"; // Replace with your desired success path
        } else {
            return "redirect:/login?error=true"; // Replace with your error handling
        }
    }
}
