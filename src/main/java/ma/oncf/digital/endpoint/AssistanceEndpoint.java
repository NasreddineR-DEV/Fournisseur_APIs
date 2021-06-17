package ma.oncf.digital.endpoint;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.oncf.digital.entity.Connexion;
import ma.oncf.digital.entity.Faq;
import ma.oncf.digital.entity.Fournisseur;
import ma.oncf.digital.entity.MailModel;
import ma.oncf.digital.entity.Reclamation;
import ma.oncf.digital.entity.TypeDemande;
import ma.oncf.digital.entity.json.Notification;
import ma.oncf.digital.entity.json.ReclamationJson;
import ma.oncf.digital.entity.json.Result;
import ma.oncf.digital.repo.foo.ConnexionRepository;
import ma.oncf.digital.repo.foo.FAQRepository;
import ma.oncf.digital.repo.foo.FournisseurRepository;
import ma.oncf.digital.repo.foo.ReclemationRepository;
import ma.oncf.digital.repo.foo.TypeDemandeRepository;
import ma.oncf.digital.service.SendingEmailService;

@RestController
@RequestMapping("/assistance/")
@CrossOrigin(origins="http://localhost:4200")
public class AssistanceEndpoint {

	private final Logger log = Logger.getLogger(AssistanceEndpoint.class);

	@Autowired
	private ReclemationRepository reclamationRepository;
	@Autowired
	private FournisseurRepository fournisseurRepository;
	@Autowired
	private TypeDemandeRepository typeDemandeRepository;
	@Autowired
	private ConnexionRepository connexionRepository;
	@Autowired
	private FAQRepository faqRepository;
	@Autowired
	private SendingEmailService sendingEmailService;

	// Api Reclamation
	@PostMapping("/new")
	public ResponseEntity<Result> saveReclamation(@RequestBody ReclamationJson reclamationJson) {
		try {

			/** Verification Champs mail **/
			if (reclamationJson != null) {
				Fournisseur fournisseur = fournisseurRepository.findByEmail(reclamationJson.getMail());

				/** Verification si le mail corresspond a un profile fornisseur **/
				if (reclamationJson.getMail() != null) {
					fournisseur = fournisseurRepository.findByEmail(reclamationJson.getMail());
				} // if
				if (fournisseur == null)
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				Reclamation reclamation = new Reclamation();
				reclamation.setContenu(reclamationJson.getContenu());
				reclamation.setObjet(reclamationJson.getObjet());
				reclamation.setFournisseur(fournisseur);
				reclamation.setReclamationDate(new Date());
				TypeDemande typeDemande = typeDemandeRepository.getOne(reclamationJson.getIdtype());
				reclamation.setTypeDemande(typeDemande);
				int idReclamation = reclamationRepository.save(reclamation).getIdReclamation();
				if (idReclamation > 0) {

					MailModel msg = new MailModel();
					msg.setTo(typeDemande.getMailDestintaire());
					msg.setTitle("RECLAMATION");
					msg.setSubject(typeDemande.getLibelleTypeDemande());
					msg.setName(typeDemande.getLibelleTypeDemande() + " from "
							+ reclamation.getFournisseur().getRaisonSociale());
					msg.setContent("Objet : " + reclamation.getObjet());
					msg.setFirstLines("Contenu : " + reclamation.getContenu());
					msg.setDetails(
							"Pour repondre ce fournisseur voici son mail : " + reclamation.getFournisseur().getEmail());
					sendingEmailService.sendEmail(msg);

					Result result = new Result();
					result.setType("ok");
					return ResponseEntity.ok(result);
				} else {
					Result result = new Result();
					result.setType("ko");
					result.setContenu("Erreur base de donnees");
					log.error("Erreur base de donnees" + reclamationJson.getMail());

					return ResponseEntity.ok(result);
				}
			} else {
				Result result = new Result();
				result.setType("ko");
				result.setContenu("Les Donn�es sont erron�s ou incomplets");
				return ResponseEntity.ok(result);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			log.error("erreur reclamation", exception);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	// @Secured("")
	@GetMapping("/getFaqs")
	public List<Faq> getFaqs() {
		return faqRepository.findByFlag(1);
	}

	// @Secured("")
	@GetMapping("/getTypes")
	public List<TypeDemande> getTypeDemandes() {
		return typeDemandeRepository.findAll();
	}

	@GetMapping("/getReclamation")
	public List<Reclamation> getReclamations() {
		return reclamationRepository.findAll();
	}

	// @Secured("")
	@PostMapping("/notification")
	public ResponseEntity<Result> ActiverDesactiverNotification(Notification notification) {
		try {
			if (notification != null) {
				Connexion connexion = connexionRepository.findOne(notification.getMail());
				if (connexion != null) {
					connexion.setNotification(notification.getStatut());
					connexionRepository.save(connexion);
					Result result = new Result();
					result.setType("ok");
					return ResponseEntity.ok(result);

				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}

			} else {
				Result result = new Result();
				result.setType("ko");
				result.setContenu("Les Données sont erronés ou incomplets");
				return ResponseEntity.ok(result);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Result resultat = new Result();
			resultat.setType("ko");
			resultat.setContenu(exception.getMessage());
			log.error("Erreur notifications", exception);
			return ResponseEntity.ok(resultat);
		}
	}
}
