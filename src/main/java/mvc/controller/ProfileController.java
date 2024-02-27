package mvc.controller;

import lombok.RequiredArgsConstructor;
import mvc.entiity.Post;
import mvc.entiity.User;
import mvc.entiity.UserInfo;
import mvc.service.Impl.UserServiceImpl;
import mvc.service.PostService;
import mvc.service.UserInfoService;
import mvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserInfoService userInfoService;
    private final UserService userService;
    private  final PostService postService;

    //    @GetMapping
//    public String getAllUserInfo(Model model){
//        model.addAttribute("allUserInfos", userInfoService);
//        return "/userInfos";
//    }
    @GetMapping
    public String Profile(Model model) {
        model.addAttribute("currentUser", userService.findUserById(UserServiceImpl.currentUser.getId()));
        return "/profile";
    }

    @GetMapping("/update/{userInfoId}")
    public String updateUserInfo(Model model, @PathVariable("userInfoId") Long id) {
        UserInfo userInfo = userService.findUserById(id).getUserInfo();
        model.addAttribute("infoID", userInfo.getId());
        model.addAttribute("userInfoUpdate", userInfo);
        return "updateProfile";
    }
    @PostMapping("/saveUpdated/{userInfoId}")
    public String updateUserInfo(@PathVariable("userInfoId") Long id, @ModelAttribute("userInfoUpdate") UserInfo userInfo) {
        userInfoService.updateUserInfoBYId(id, userInfo);
        return "redirect:/profile";
    }




//
}

