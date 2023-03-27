package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.binding.PictureAddBindingModel;
import com.example.euphoriagifts2.model.entity.CategoryEntity;
import com.example.euphoriagifts2.model.entity.PictureEntity;
import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;
import com.example.euphoriagifts2.model.view.PictureViewModel;
import com.example.euphoriagifts2.repository.PictureRepository;
import com.example.euphoriagifts2.service.CloudinaryImage;
import com.example.euphoriagifts2.service.CloudinaryService;
import com.example.euphoriagifts2.service.GiftService;
import com.example.euphoriagifts2.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class PictureController {
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;
    private final PictureService pictureService;

    public PictureController(CloudinaryService cloudinaryService, PictureRepository pictureRepository, PictureService pictureService) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        this.pictureService = pictureService;
    }

    @GetMapping("/pictures/add-picture")
    public String addPicture() {
        return "add-picture";
    }


    @PostMapping("/pictures/add-picture")
    public String addPictureConfirm(PictureAddBindingModel pictureAddBindingModel) throws IOException {
        var picture = createPictureEntity(pictureAddBindingModel.getPicture()
                , pictureAddBindingModel.getTitle());

        pictureRepository.save(picture);
        return "redirect:/pictures/all-pictures";

    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);


        return new PictureEntity()
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setUrl(uploaded.getUrl());

    }

    @GetMapping("/pictures/all-pictures")
    public String allPictures(Model model) {
        List<PictureViewModel> pictures = this.pictureService.findAllPictures();

        model.addAttribute("pictures", pictures);

        return "all-pictures";

    }
    @Transactional
    @DeleteMapping("/pictures/delete")
    //value ="public_id", required = false
    public String delete(@RequestParam("public_id") String publicId) {
        if (cloudinaryService.delete(publicId)) {
            this.pictureService.deletePicture(publicId);
        }
        return "redirect:/pictures/all-pictures";
    }

}
