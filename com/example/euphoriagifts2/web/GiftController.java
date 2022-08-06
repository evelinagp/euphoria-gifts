package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.RoleEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;
import com.example.euphoriagifts2.model.service.GiftServiceModel;
import com.example.euphoriagifts2.model.view.GiftViewModel;
import com.example.euphoriagifts2.service.GiftService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/gifts")
public class GiftController {
    private final ModelMapper modelMapper;
    private final GiftService giftService;

    public GiftController(ModelMapper modelMapper, GiftService giftService) {
        this.modelMapper = modelMapper;
        this.giftService = giftService;
    }


    @GetMapping("/all-gifts")
    public String allGifts(Model model) {
        List<GiftViewModel> gifts = this.giftService.findAllGifts();

        model.addAttribute("gifts", gifts);

        return "all-gifts";

    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        GiftEntity giftById = giftService.findGiftById(id);

        model.addAttribute("gift", giftById);

       RoleEntity adminEntity = giftById.getUserEntity().getRoles().stream().filter(r -> r.getName().equals(RoleNameEnum.ADMIN)).findFirst().orElse(null);
        if (adminEntity != null){
              model.addAttribute("admin", adminEntity);

        }

        return "gift-details";

    }

    //    //SHOW ALL GIFTS BY CATEGORY
    @GetMapping("/categories/{category}")
    public String homeCategory(@PathVariable String category, Model model) {


        if ((CategoryNameEnum.HOME.name()).equals(category.toUpperCase(Locale.ROOT))) {
            List<GiftViewModel> home = giftService
                    .getAllGiftsByCategoryName(CategoryNameEnum.HOME);
            model.addAttribute("home", home);
            return "home-category";
        } else if ((CategoryNameEnum.KITCHEN.name()).equals(category.toUpperCase(Locale.ROOT))) {
            List<GiftViewModel> kitchen = giftService
                    .getAllGiftsByCategoryName(CategoryNameEnum.KITCHEN);
            model.addAttribute("kitchen", kitchen);
            return "kitchen-category";
        } else if ((CategoryNameEnum.WOMEN.name()).equals(category.toUpperCase(Locale.ROOT))) {
            List<GiftViewModel> women = giftService
                    .getAllGiftsByCategoryName(CategoryNameEnum.WOMEN);
            model.addAttribute("women", women);
            return "women-category";
        } else if ((CategoryNameEnum.MEN.name()).equals(category.toUpperCase(Locale.ROOT))) {
            List<GiftViewModel> men = giftService
                    .getAllGiftsByCategoryName(CategoryNameEnum.MEN);
            model.addAttribute("men", men);
            return "men-category";
        } else if ((CategoryNameEnum.OCCASIONS.name()).equals(category.toUpperCase(Locale.ROOT))) {
            List<GiftViewModel> occasions = giftService
                    .getAllGiftsByCategoryName(CategoryNameEnum.OCCASIONS);
            model.addAttribute("occasions", occasions);
            return "occasions-category";
        } else if ((CategoryNameEnum.OTHERS.name()).equals(category.toUpperCase(Locale.ROOT))) {
            List<GiftViewModel> others = giftService
                    .getAllGiftsByCategoryName(CategoryNameEnum.OTHERS);
            model.addAttribute("others", others);
            return "others-category";
        } else {
            return "redirect:/";
        }

    }
}
