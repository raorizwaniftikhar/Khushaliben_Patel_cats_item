package ca.sheridancollege.khushi.service;

import ca.sheridancollege.khushi.bean.CatItem;
import ca.sheridancollege.khushi.database.CatItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatItemServiceImpl implements CatItemService {

    private CatItemRepository catItemRepository;

    public CatItemServiceImpl(CatItemRepository catItemRepository) {
        this.catItemRepository = catItemRepository;
    }

    @Override
    public List<CatItem> findAllCatItems() {
        return catItemRepository.findAllCatItems();
    }

    @Override
    public void addUpdateCatItem(CatItem catItem) {
        catItemRepository.addUpdateCatItem(catItem);
    }

    @Override
    public void deleteCatItemById(long id) {
        catItemRepository.deleteCateItemById(id);
    }

    @Override
    public CatItem findCatItemById(long id) {
        return catItemRepository.findCatItemById(id);
    }

    @Override
    public List<CatItem> searchCatItem(double min, double max) {
        return catItemRepository.searchCatItem(min,max);
    }
}
