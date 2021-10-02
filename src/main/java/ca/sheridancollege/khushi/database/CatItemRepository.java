package ca.sheridancollege.khushi.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.sheridancollege.khushi.bean.CatItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CatItemRepository {

    @Autowired
    private NamedParameterJdbcTemplate dataSource;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("rawtypes")
    public List<CatItem> findAllCatItems() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        ArrayList<CatItem> catItems = new ArrayList<>();
        String query = "SELECT * FROM cat_item";
        List<Map<String, Object>> rows = dataSource.queryForList(query, parameters);
        for (Map row : rows) {
            CatItem catItem = new CatItem();
            catItem.setId((long) row.get("id"));
            catItem.setName((String) row.get("name"));
            catItem.setPrice((double) row.get("price"));
            catItem.setQuantity((int) row.get("quantity"));
            catItems.add(catItem);
        }
        return catItems;

    }

    public void addUpdateCatItem(CatItem catItem) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = null;
        if (catItem.getId() > 0) {
            query = "UPDATE cat_item SET name= :name, price= :price, quantity= :quantity where id= :id ";
        } else {
            query = "INSERT INTO cat_item (name, price, quantity) VALUES ( :name, :price, :quantity)";
        }
        if (catItem.getId() > 0) {
            parameters.addValue("id", catItem.getId());
        }
        parameters.addValue("name", catItem.getName());
        parameters.addValue("price", catItem.getPrice());
        parameters.addValue("quantity", catItem.getQuantity());
        dataSource.update(query, parameters);

    }

    @SuppressWarnings("rawtypes")
    public CatItem findCatItemById(long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        List<Map<String, Object>> rows = dataSource.queryForList("SELECT * FROM cat_item WHERE id = :id ", parameters);
        CatItem catItem = new CatItem();
        for (Map row : rows) {
            catItem.setId((long) row.get("id"));
            catItem.setName((String) row.get("name"));
            catItem.setPrice((double) row.get("price"));
            catItem.setQuantity((int) row.get("quantity"));
             }
        return catItem;

    }


    public void deleteCateItemById(long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        dataSource.update("delete FROM cat_item WHERE id = :id ", parameters);
    }

    public List<CatItem> searchCatItem(double min,double max){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        ArrayList<CatItem> catItems = new ArrayList<>();
        String query = "SELECT * FROM cat_item where price >= :min and price <= :max";
        parameters.addValue("min", min);
        parameters.addValue("max", max);
        List<Map<String, Object>> rows = dataSource.queryForList(query, parameters);
        for (Map row : rows) {
            CatItem catItem = new CatItem();
            catItem.setId((long) row.get("id"));
            catItem.setName((String) row.get("name"));
            catItem.setPrice((double) row.get("price"));
            catItem.setQuantity((int) row.get("quantity"));
            catItems.add(catItem);
        }
        return catItems;
    }
}
