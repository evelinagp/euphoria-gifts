package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.binding.AddressBindingModel;
import com.example.euphoriagifts2.model.entity.AddressEntity;
import com.example.euphoriagifts2.model.service.AddressServiceModel;
import com.example.euphoriagifts2.service.AddressService;
import com.example.euphoriagifts2.util.cart.ShoppingCart;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.LinkedHashSet;

@Controller
//@RequestMapping("/addresses")
public class AddressesController {

    private final ModelMapper modelMapper;
    private final ShoppingCart shoppingCart;
    private final AddressService addressService;

    public AddressesController(ModelMapper modelMapper, ShoppingCart shoppingCart, AddressService addressService) {
        this.modelMapper = modelMapper;
        this.shoppingCart = shoppingCart;
        this.addressService = addressService;
    }


    @GetMapping("/address-details")
    public String deliveryAddress() {
        return "address-details";
    }


    @PostMapping("/address-details")
    public String deliveryAddressConfirm(@Valid AddressBindingModel addressBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addressBindingModel", addressBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addressBindingModel", bindingResult);
            return "redirect:address-details";
        }

        AddressServiceModel addressServiceModel = this.modelMapper.map(addressBindingModel, AddressServiceModel.class);
        addressServiceModel.setOrders(new LinkedHashSet<>());

        this.shoppingCart.setDeliveryAddress(this.addressService.saveAddress(addressServiceModel));

        return "address-details";
    }
    @ModelAttribute
    public AddressBindingModel addressBindingModel() {
        return new AddressBindingModel();
    }
}


