package ma.oncf.digital.entity;

/*
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
*/
import java.util.Map;

/*
@Data
@ToString
@AllArgsConstructor*/
public class MailModel {

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getModel() {
		return model;
	}

	public void setModel(Map<String, String> model) {
		this.model = model;
	}

	public String getFirstLines() {
		return firstLines;
	}

	public void setFirstLines(String firstLines) {
		this.firstLines = firstLines;
	}

	public String getDetails() {
		return Details;
	}

	public void setDetails(String details) {
		Details = details;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// private String from;
	private String to;
	private String name;
	private String subject;
	private String content;
	private String firstLines;
	private String Details;
	private String title;
	private Map<String, String> model;
}
