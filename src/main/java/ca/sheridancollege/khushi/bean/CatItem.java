package ca.sheridancollege.khushi.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatItem {
	private long id;
	private String name;
	private double price;
	private int Quantity;
}
