package ca.sheridancollege.khushi.bean;

import lombok.Data;

@Data
public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String picture;
	private Role roleId;
}
