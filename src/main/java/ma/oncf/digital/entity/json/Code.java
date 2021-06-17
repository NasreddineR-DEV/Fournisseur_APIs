package ma.oncf.digital.entity.json;

import java.io.Serializable;

public class Code implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2883996756847467471L;
	private String codeTierce;
	private String code;
	
	
	public Code() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Code(String codeTierce, String code) {
		super();
		this.codeTierce = codeTierce;
		this.code = code;
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
	
	
	

}
