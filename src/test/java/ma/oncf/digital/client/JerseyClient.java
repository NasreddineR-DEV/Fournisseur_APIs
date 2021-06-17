//package ma.oncf.digital.client;
//
//import java.util.List;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.GenericType;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import ma.oncf.digital.entity.Fournisseur;
//
//public class JerseyClient {
//	public void login() {
//		Client client = ClientBuilder.newClient();
//		WebTarget base = client.target("http://localhost:8080/spring-app/users");
//		WebTarget details = base.path("login");
//		List<Fournisseur> list = details.request(MediaType.APPLICATION_JSON)
//				.get(new GenericType<List<Fournisseur>>() {});
//
//	    list.stream().forEach(article ->
//	        System.out.println(article.getId()+", "+ article.getTitle()+", "+ article.getCategory()));
//
//	    client.close();
//	}
////	public void getArticleById(int articleId) {
////		Client client = ClientBuilder.newClient();
////		WebTarget base = client.target("http://localhost:8080/spring-app/article");
////		WebTarget articleById = base.path("{id}").resolveTemplate("id", articleId);
////		Fournisseur article = articleById.request(MediaType.APPLICATION_JSON)
////				.get(Fournisseur.class);
////
////        System.out.println(article.getId()+", "+ article.getTitle()+", "+ article.getCategory());
////
////	    client.close();
////	}
////	public void addArticle(Fournisseur article) {
////		Client client = ClientBuilder.newClient();
////		WebTarget base = client.target("http://localhost:8080/spring-app/article");
////		WebTarget add = base.path("add");
////		Response response = add.request(MediaType.APPLICATION_JSON)
////				.post(Entity.json(article));
////
////		System.out.println("Response Http Status: "+ response.getStatus());
////        System.out.println(response.getLocation());
////
////	    client.close();
////	}
////	public void updateArticle(Fournisseur article) {
////		Client client = ClientBuilder.newClient();
////		WebTarget base = client.target("http://localhost:8080/spring-app/article");
////		WebTarget update = base.path("update");
////		Response response = update.request(MediaType.APPLICATION_JSON)
////				.put(Entity.json(article));
////
////		System.out.println("Response Http Status: "+ response.getStatus());
////		Fournisseur resArticle = response.readEntity(Fournisseur.class);
////		System.out.println(resArticle.getId()+", "+ resArticle.getTitle()+", "+ resArticle.getCategory());
////
////	    client.close();
////	}
////	public void deleteArticle(int articleId) {
////		Client client = ClientBuilder.newClient();
////		WebTarget base = client.target("http://localhost:8080/spring-app/article");
////		WebTarget deleteById = base.path("{id}").resolveTemplate("id", articleId);
////		Response response = deleteById.request(MediaType.APPLICATION_JSON)
////				.delete();
////
////		System.out.println("Response Http Status: "+ response.getStatus());
////		if(response.getStatus() == 204) {
////			System.out.println("Data deleted successfully.");
////		}
////
////	    client.close();
////	}
//	public static void main(String[] args) {
//		JerseyClient jerseyClient = new JerseyClient();
//	    jerseyClient.login();
//
//	}
//}
