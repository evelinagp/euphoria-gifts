package com.example.euphoriagifts2.web;


import com.example.euphoriagifts2.model.binding.GiftAddBindingModel;
import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.model.service.GiftServiceModel;
import com.example.euphoriagifts2.model.view.UserViewModel;
import com.example.euphoriagifts2.service.GiftService;
import com.example.euphoriagifts2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pages/admins")
public class AdminController {

    private final GiftService giftService;
    private final UserService userService;
    private final ModelMapper modelMapper;


    public AdminController(GiftService giftService, UserService userService, ModelMapper modelMapper) {
        this.giftService = giftService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    //EDIT GIFT
    @GetMapping("/edit-gift/{id}")
    public String edit(@PathVariable Long id, Model model) {
        GiftEntity giftById = this.giftService.findGiftById(id);
        GiftServiceModel gift = modelMapper.map(giftById, GiftServiceModel.class);
        model.addAttribute("giftServiceModel", gift);

        return "/edit-gift";
    }

    @PutMapping("/edit-gift/{id}")
    public String editGift(@Valid GiftServiceModel giftServiceModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @PathVariable Long id) throws IOException {
        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("giftServiceModel", giftServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.giftServiceModel", bindingResult);

            return "/edit-gift";
        }

        this.giftService.updateGift(giftServiceModel);

        return "redirect:/gifts/all-gifts";
    }

   
//    TODO
//     CAN NOT DELETE GIFT WHICH IS INCLUDED IN ORDER. FIX IT TO SHOW MESSAGE!

 //DELETE GIFT
    @Transactional
    @GetMapping("/delete-gift/{id}")
    public String deleteGift(@PathVariable Long id) throws Exception {
        this.giftService.deleteGiftById(id);

        return "redirect:/gifts/all-gifts";
    }

    //ADD GIFT
    @GetMapping("/add-gift")
    public String addGift() {

        return "/add-gift";
    }

    @PostMapping("/add-gift")
    public String addGiftConfirm(@Valid GiftAddBindingModel giftAddBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("giftAddBindingModel", giftAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.giftAddBindingModel", bindingResult);
            return "redirect:add-gift";
        }

        boolean giftExists = this.giftService.isGiftExists(giftAddBindingModel.getName());
        if (giftExists) {
            return "redirect:add-gift";
        }
        GiftServiceModel giftServiceModel = this.modelMapper.map(giftAddBindingModel, GiftServiceModel.class);

        this.giftService.addNewGift(giftServiceModel, principal);
        return "redirect:/gifts/all-gifts";

    }

    //MANAGE USERS PAGE
    @GetMapping("/manage-users")
    public String adminPanel(Model model) {
        List<UserViewModel> usersViewModels = this.userService.findAllUsers().stream()
                .map(userServiceModel -> this.modelMapper.map(userServiceModel, UserViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("allUsers", usersViewModels);
        model.addAttribute("admin", RoleNameEnum.ADMIN);
        return "manage-users";
    }

    
//    @Transactional
    //    TODO -> CAN NOT DELETE USER WHO HAS ORDER. FIX IT TO SHOW MESSAGE!

//REMOVE USER
    @GetMapping("/delete-role/{id}")
    public String deleteUser(@PathVariable Long id) throws Exception {
        boolean isUserExist = this.userService.existById(id);
        if (isUserExist) {
            this.userService.deleteUser(id);
        }
        return "redirect:/pages/admins/manage-users";
    }


    //CHANGE USER ROLE
    @GetMapping("/change-role/{id}")
    public String changeUserRole(@PathVariable Long id) throws Exception {
        boolean isUserExist = this.userService.existById(id);
        if (isUserExist) {
            this.userService.changeCurrentUserRole(id);
        }
        return "redirect:/pages/admins/manage-users";
    }


    @ModelAttribute
    private GiftAddBindingModel giftBindingModel() {
        return new GiftAddBindingModel();
    }

    @ModelAttribute
    private GiftServiceModel giftServiceModel() {
        return new GiftServiceModel();
    }

}

