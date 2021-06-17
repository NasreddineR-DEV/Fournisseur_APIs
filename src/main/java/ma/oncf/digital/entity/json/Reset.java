package ma.oncf.digital.entity.json;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Reset implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6109645000792612172L;
	private String codeTierce;
	private String code;
	@NotNull(message="password could not be null or empty")
    @Size(min=8,message="Password must be more than 8 caracters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message=
    		"a digit must occur at least once\r\n" + 
    		"a lower case letter must occur at least once\r\n" + 
    		" an upper case letter must occur at least once\r\n" + 
    		"a special character must occur at least once\r\n" + 
    		" no whitespace allowed in the entire string\r\n")
	private String password;
	
	
	public Reset() {
		// TODO Auto-generated constructor stub
	}


	public String getCodeTierce() {
		return codeTierce;
	}


	public void setCodeTierce(String codeTierce) {
		this.codeTierce = codeTierce;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	
	
	
	

}
