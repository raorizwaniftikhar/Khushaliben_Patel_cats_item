package ca.sheridancollege.khushi.web.controller;

import ca.sheridancollege.khushi.bean.CatItem;
import ca.sheridancollege.khushi.service.CatItemService;
import ca.sheridancollege.khushi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/item")
public class RestCatItemController {

    @Autowired
    private CatItemService catItemService;

    @GetMapping("/")
    public List<CatItem> fetchCatItemList() {
        List<CatItem> catItems = catItemService.findAllCatItems();
        return catItems;

    }

    @PostMapping(value = "/")
    public String addDog(@RequestBody CatItem catItem) {
        catItemService.addUpdateCatItem(catItem);
        return "new Item add";
    }
    //Update CatItem info Request
    @RequestMapping(value = "/updateCatItem", method = RequestMethod.PUT)
    public String updateCatItem(@RequestParam(value = "id") int id) {
        CatItem catItem = catItemService.findCatItemById(id);
        return "quanty  mins";
    }

    //Delete CatItem Record
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@RequestParam(value = "id") int id) {
        catItemService.deleteCatItemById(id);
        return "delete item";
    }
    //Delete CatItem Record
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CatItem findCatItem(@RequestParam(value = "id") int id) {
        CatItem catItem = catItemService.findCatItemById(id);
        return catItem;
    }

}
