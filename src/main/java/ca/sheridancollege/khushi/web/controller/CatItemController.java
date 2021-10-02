package ca.sheridancollege.khushi.web.controller;

import java.util.ArrayList;
import java.util.List;

import ca.sheridancollege.khushi.bean.CatItem;
import ca.sheridancollege.khushi.service.CatItemService;
import ca.sheridancollege.khushi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.khushi.bean.User;

@Controller
public class CatItemController {

    @Autowired
    private CatItemService catItemService;

    @Autowired
    private UserService userService;

    // SHOW CatItem
    @RequestMapping(value = {"/","/itemsList"})
    public String fetchCatItemList(Model model) {
        List<CatItem> catItems = catItemService.findAllCatItems();
        model.addAttribute("catItems", catItems);
        return "index";

    }

    // CREATE NEW CatItem PAGE
    @RequestMapping("/createCatItem")
    public String createCatItem() {
        return "addCatItem";
    }

    // CREATE THE ACTUAL CatItem POST
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search() {
        return "saerchCatItem";
    }

    // CREATE THE ACTUAL CatItem POST
    @RequestMapping(value = "/searchItem", method = RequestMethod.POST)
    public String searchItem(@ModelAttribute("maxPrice") double max, @ModelAttribute("minPrice") double min , Model model) {
        List<CatItem> catItems = catItemService.searchCatItem(min,max);
        model.addAttribute("catItems", catItems);
        return "redirect:/";
    }
    // CREATE THE ACTUAL CatItem POST
    @RequestMapping(value = "/addCatItem", method = RequestMethod.POST)
    public String addDog(@ModelAttribute("") CatItem catItem) {
        catItemService.addUpdateCatItem(catItem);
        return "redirect:/";
    }
    //Update CatItem info Request
    @RequestMapping(value = "/updateCatItem", method = RequestMethod.GET)
    public String updateCatItem(@RequestParam(value = "id") int id, Model model) {
        CatItem catItem = catItemService.findCatItemById(id);
        model.addAttribute("catItem", catItem);
        return "updateCatItem";
    }

    //Delete CatItem Record
    @RequestMapping(value = "/deleteCatItem", method = RequestMethod.GET)
    public String addCatItem(@RequestParam(value = "id") int id, Model model) {
        catItemService.deleteCatItemById(id);
        return "redirect:/";
    }

}
