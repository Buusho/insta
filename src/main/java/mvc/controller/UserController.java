package mvc.controller;

import lombok.RequiredArgsConstructor;
import mvc.entiity.User;
import mvc.service.Impl.UserServiceImpl;
import mvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
@GetMapping
public String getAllUsers(Model model){
model.addAttribute("allUsers",userService.userProfile());
return "userLogin";
}


@GetMapping("/newUser")
    private String newUser(Model model){
model.addAttribute("newUser",new User());
return"createNewUser";
}
    @PostMapping("/saveUser")
    public String signUpUser(@ModelAttribute("newUser") User user) {
        userService.signUpWithInfoAndFollowers(user);
        return "/userLogin";
    }

@GetMapping("/login")
    public String signIn(Model model){
    model.addAttribute("newUser", new User());
    return "/login";
}

   @PostMapping("/signIn")
    public String logIn(@ModelAttribute("newUser") User user, Model model){
        try {
            User currentUser = userService.signIn(user);
            if (currentUser != null){
                model.addAttribute("currentUser", currentUser);
                return "/profile";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/user";
  }
    @GetMapping("/profilePage")
    public String profile(Model model){
        model.addAttribute("newUser", userService.findUserById(UserServiceImpl.currentUser.getId()));
        return "/profile";
    }





}
