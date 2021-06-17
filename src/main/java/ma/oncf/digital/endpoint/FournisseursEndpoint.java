package ma.oncf.digital.endpoint;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.TemplateException;
import ma.oncf.digital.entity.Connexion;
import ma.oncf.digital.entity.Fournisseur;
import ma.oncf.digital.entity.MailModel;
import ma.oncf.digital.entity.json.Code;
import ma.oncf.digital.entity.json.FournisseurJson;
import ma.oncf.digital.entity.json.Identifiant;
import ma.oncf.digital.repo.foo.ConnexionRepository;
import ma.oncf.digital.repo.foo.FournisseurRepository;
import ma.oncf.digital.service.SendingEmailService;
import ma.oncf.digital.utils.CodeGenerator;

@RestController
@RequestMapping("/fournisseurs")
@CrossOrigin(origins = "http://localhost:4200")
public class FournisseursEndpoint {

	@SuppressWarnings("unused")
	private final Logger log = Logger.getLogger(FournisseursEndpoint.class);

	@Autowired
	private FournisseurRepository fournisseurRepository;
	@Autowired
	ConnexionRepository connexionRepository;
	@Autowired
	private SendingEmailService sendingEmailService;

	// Fournisseur par id (sera remplacé par code tiers)
	@GetMapping("")
	public Fournisseur getFournisseur(@RequestParam("idf") int idf) {

		Fournisseur fournisseur = fournisseurRepository.findOne(idf);
		return fournisseur;

	}

	@PutMapping("")
	public Fournisseur updateFournisseur(@RequestParam("idf") int idf,
			@RequestBody FournisseurJson fournisseurDetails) {

		Fournisseur fournisseur = fournisseurRepository.findOne(idf);

		fournisseur.setEmail(fournisseurDetails.getEmail());
		fournisseur.setAdressePostale(fournisseurDetails.getAdressePostale());
		fournisseur.setRaisonSociale(fournisseurDetails.getRaisonSociale());
		fournisseur.setTelephone(fournisseurDetails.getTelephone());
		if (fournisseur.getStatusFournisseur() == 0)

			fournisseur.setStatusFournisseur(fournisseur.getStatusFournisseur() + 1);

		Fournisseur updatedFournisseur = fournisseurRepository.save(fournisseur);
		return updatedFournisseur;
	}

	@GetMapping("/all")
	public Page<Fournisseur> getAllFournisseurs(Pageable page) {
		return fournisseurRepository.findAll(page);
	}

	@PutMapping("/updateStatus")
	public Fournisseur updateStatusFournisseur(@RequestParam("idf") int idf,
			@RequestBody FournisseurJson fournisseurDetails) {

		Fournisseur fournisseur = fournisseurRepository.findOne(idf);

		fournisseur.setStatusFournisseur(fournisseurDetails.getStatusFournisseur());

		Connexion connexion = connexionRepository.findOne(fournisseur.getCodeTier());

		if (connexion != null && connexion.getFournisseur().getStatusFournisseur() > 1) {
			try {
				Code code = new Code();
				code.setCode(new CodeGenerator(6).nextString());
				code.setCodeTierce(fournisseur.getCodeTier());
				connexion.setCodeReset(code.getCode());
				MailModel msg = new MailModel();
				msg.setTo(connexion.getFournisseur().getEmail());
				msg.setTitle("Accès ONCF Fournisseurs");
				msg.setSubject("ONCF Fournisseurs Compte");
				msg.setName(connexion.getFournisseur().getRaisonSociale());
				msg.setContent(
						"Votre compte ONCF Fournisseurs est activé, vous pouvez maintenant réinitiliser votre mot de passe avec le code : "
								+ code.getCode());
				msg.setFirstLines(
						"Pour réinitialiser votre mot de passe, copiez ce code et collez dans l'application fournisseur");
				msg.setDetails(
						"Pour des raisons de sécurité, ce code expirera dans un délai 15 minutes après son envoi.");

				sendingEmailService.sendEmail(msg);

				connexion.setCodeReset(code.getCode());
				connexionRepository.save(connexion);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Fournisseur updatedStatusFournisseur = fournisseurRepository.save(fournisseur);
		return updatedStatusFournisseur;
	}

	// reste password
	@PostMapping("/resetpassword")
	public ResponseEntity<Code> resetpassword(@RequestBody Identifiant identifiant) {
		try {
			Connexion connexion = connexionRepository.findOne(identifiant.getCodetierce());

			if (connexion != null && connexion.getFournisseur().getStatusFournisseur() > 1) {

				Code code = new Code();
				code.setCode(new CodeGenerator(6).nextString());
				code.setCodeTierce(identifiant.getCodetierce());
				connexion.setCodeReset(code.getCode());
				MailModel msg = new MailModel();
				msg.setTo(connexion.getFournisseur().getEmail());
				msg.setTitle("RESET CODE");
				msg.setSubject("ONCF Fournisseurs code RESET");
				msg.setName(connexion.getFournisseur().getRaisonSociale());
				msg.setContent("Le code pour réinitiliser votre mot de passe est  : " + code.getCode());
				msg.setFirstLines(
						"Pour réinitialiser votre mot de passe, copiez ce code et collez dans l'application fournisseur");
				msg.setDetails(
						"Pour des raisons de sécurité, ce code expirera dans un délai 15 minutes après son envoi.");
				// sendingEmailService.sendEmail(msg);
				connexion.setCodeReset(code.getCode());
				connexionRepository.save(connexion);
				return ResponseEntity.ok(code);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		} catch (Exception e) {
			log.error("Erreur reinitialiation mot de passe", e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/countFournisseur")
	public int getCountFournisseur() {
		return fournisseurRepository.getNbAllFournisseur();
	}

}
