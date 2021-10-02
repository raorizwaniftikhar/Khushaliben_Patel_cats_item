package ca.sheridancollege.khushi.service;

import ca.sheridancollege.khushi.bean.CatItem;

import java.util.List;

public interface CatItemService {

     List<CatItem> findAllCatItems();

     void addUpdateCatItem(CatItem catItem);

     void deleteCatItemById(long id);

     CatItem findCatItemById(long id);
     List<CatItem> searchCatItem(double min, double max);
}
