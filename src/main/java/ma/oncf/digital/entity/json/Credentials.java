package ma.oncf.digital.entity.json;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Credentials implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	@NotNull(message="password could not be null or empty")
	@Size(min=5,message="Login must be more than 3 caracters")
	private String username;
    @NotNull(message="password could not be null or empty")
    @Size(min=8,message="Password must be more than 8 caracters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message=
    		"a digit must occur at least once\r\n" + 
    		"a lower case letter must occur at least once\r\n" + 
    		" an upper case letter must occur at least once\r\n" + 
    		"a special character must occur at least once\r\n" + 
    		" no whitespace allowed in the entire string\r\n")
    private String password;

    public Credentials() {
		// TODO Auto-generated constructor stub
	}

	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}