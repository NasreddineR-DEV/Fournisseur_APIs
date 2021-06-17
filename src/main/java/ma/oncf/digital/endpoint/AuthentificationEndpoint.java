package ma.oncf.digital.endpoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.oncf.digital.entity.Connexion;
import ma.oncf.digital.entity.MailModel;
import ma.oncf.digital.entity.json.Code;
import ma.oncf.digital.entity.json.Credentials;
import ma.oncf.digital.entity.json.Identifiant;
import ma.oncf.digital.entity.json.Reset;
import ma.oncf.digital.entity.json.Result;
import ma.oncf.digital.repo.foo.ConnexionRepository;
import ma.oncf.digital.security.CustomUserDetailsService;
import ma.oncf.digital.security.jwt.JwtTokenProvider;
import ma.oncf.digital.service.SendingEmailService;
import ma.oncf.digital.utils.CodeGenerator;

@RestController
@RequestMapping("/users/")
@CrossOrigin
public class AuthentificationEndpoint {
	private static final Logger log = LoggerFactory.getLogger(AuthentificationEndpoint.class);

	@Autowired
	ConnexionRepository connexionRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private SendingEmailService sendingEmailService;

	// authentification
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody Credentials credentials) throws Exception {
    	authenticate(credentials.getUsername(), credentials.getPassword());

    	final UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
		Connexion connexion = connexionRepository.findByLogin(credentials.getUsername());
		final String token = jwtTokenProvider.generateToken(userDetails);
		 Map<String, Object> authentification = new HashMap<>();
	        
		int nbFacture = connexionRepository.getCountFact(connexion.getFournisseur().getIdFournisseur());
		int nbFactEnCoursRegle = connexionRepository.getCountFactEnCourRegle(connexion.getFournisseur().getIdFournisseur());
		int nbFactureEnCoursTrait = connexionRepository.getCountFactEncourTrait(connexion.getFournisseur().getIdFournisseur());
		int nbFactureEnre = connexionRepository.getCountFactEnr(connexion.getFournisseur().getIdFournisseur());
		int nbFactureRegle = connexionRepository.getCountFactRegle(connexion.getFournisseur().getIdFournisseur());
		int nbMarche = connexionRepository.getCountMarche(connexion.getFournisseur().getIdFournisseur());
		
		//String token = authenticate(credentials.getUsername(), credentials.getPassword(),userDetails);
		
		
	

		authentification.put("token", token);
		authentification.put("counter", connexion.getCompteur());
		authentification.put("idFournisseur", connexion.getFournisseur().getIdFournisseur());
		authentification.put("codeTier", connexion.getFournisseur().getCodeTier());
		authentification.put("email", connexion.getFournisseur().getEmail());
		authentification.put("nbFacture", nbFacture);
		authentification.put("enCoursRegle", nbFactEnCoursRegle);
		authentification.put("EnCoursTraitement", nbFactureEnCoursTrait);
		authentification.put("enregistre", nbFactureEnre);
		authentification.put("regle", nbFactureRegle);
		authentification.put("nbMarche", nbMarche);
		authentification.put("status", connexion.getFournisseur().getStatusFournisseur());
		
		return ResponseEntity.ok(authentification);
		//return ResponseEntity.ok(new JwtResponse(token));
      /*  Connexion connexion = connexionRepository.findFirstByLoginAndPassword(credentials.getUsername(), passwordEncoder.encode(credentials.getPassword()));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), passwordEncoder.encode(credentials.getPassword())));
        String token = jwtTokenProvider.createToken(
                credentials.getUsername(), new ArrayList<>());
       */
    }
    private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	
	/*
	@PostMapping("/login")
	public ResponseEntity<Map> authenticateUser(@RequestBody Credentials credentials) throws Exception {
	
		final UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
		Connexion connexion = connexionRepository.findByLogin(credentials.getUsername());
		int nbFacture = connexionRepository.getCountFact(connexion.getFournisseur().getIdFournisseur());
		int nbFactEnCoursRegle = connexionRepository.getCountFactEnCourRegle(connexion.getFournisseur().getIdFournisseur());
		int nbFactureEnCoursTrait = connexionRepository.getCountFactEncourTrait(connexion.getFournisseur().getIdFournisseur());
		int nbFactureEnre = connexionRepository.getCountFactEnr(connexion.getFournisseur().getIdFournisseur());
		int nbFactureRegle = connexionRepository.getCountFactRegle(connexion.getFournisseur().getIdFournisseur());
		int nbMarche = connexionRepository.getCountMarche(connexion.getFournisseur().getIdFournisseur());
		
		String token = authenticate(credentials.getUsername(), credentials.getPassword(),userDetails);
		
		
		Map<String, Object> authentification = new HashMap<>();

		authentification.put("token", token);
		authentification.put("counter", connexion.getCompteur());
		authentification.put("idFournisseur", connexion.getFournisseur().getIdFournisseur());
		authentification.put("codeTier", connexion.getFournisseur().getCodeTier());
		authentification.put("email", connexion.getFournisseur().getEmail());
		authentification.put("nbFacture", nbFacture);
		authentification.put("enCoursRegle", nbFactEnCoursRegle);
		authentification.put("EnCoursTraitement", nbFactureEnCoursTrait);
		authentification.put("enregistre", nbFactureEnre);
		authentification.put("regle", nbFactureRegle);
		authentification.put("nbMarche", nbMarche);
		authentification.put("status", connexion.getFournisseur().getStatusFournisseur());
		
		return ResponseEntity.ok(authentification);

	}

	private String  authenticate (String username, String password,UserDetails user) throws Exception  {


			  String login = user.getUsername();
		      String motdepasse =user.getPassword();
		      String cryptedPassWord= passwordEncoder.encode(password);
		      
		      if (login.equals(username) && cryptedPassWord.equals(motdepasse)) {
		    	  return  jwtTokenProvider.generateToken(user);
		       } else {
		    	   
		    	   
		           return "cryptedPassWord :"+ password +"|"+ "bdd:" + motdepasse;
		       }
			

	}
*/
 // reste password
 	@PostMapping("/resetpassword")
 	public ResponseEntity<Code> resetpassword(@RequestBody Identifiant identifiant) {
 		try {
 			Connexion connexion = connexionRepository.findOne(identifiant.getCodetierce());

 			if (connexion != null && connexion.getFournisseur().getStatusFournisseur()>1) {

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
 				//sendingEmailService.sendEmail(msg);
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

    
	// verify code
	@PostMapping("/verifycode")
	public ResponseEntity<Result> vetifyCode(@RequestBody Code code) {
		try {
			Connexion connexion = connexionRepository.findOne(code.getCodeTierce());

			if (connexion != null) {

				if (connexion.getCodeReset() != null && connexion.getCodeReset().equals(code.getCode())) {

					Result resultat = new Result();
					resultat.setType("ok");
					return ResponseEntity.ok(resultat);
				} else {

					Result resultat = new Result();
					resultat.setType("ko");
					resultat.setContenu("Code invalide");
					return ResponseEntity.ok(resultat);
				}
			}

			else {
				Result resultat = new Result();
				resultat.setType("ko");
				resultat.setContenu("donnÃ©es invalide");
				return ResponseEntity.ok(resultat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erreur", e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	// change password
	@PostMapping("/changepassword")
	public ResponseEntity<Result> changePassword(@Valid @RequestBody Reset reset) {
		try {

			if (reset != null) {
				Connexion connexion = connexionRepository.findOne(reset.getCodeTierce());

				if (connexion != null && connexion.getCodeReset().equals(reset.getCode())) {
					int i = connexion.getCompteur() != null ? connexion.getCompteur() + 1 : 0;
					connexion.setCompteur(i);
					connexionRepository.save(connexion);
					connexion.setPassword(passwordEncoder.encode(reset.getPassword()));
					connexion.setCodeReset("");
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
				result.setContenu("DonnÃ©es invalides");
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("erreur modification mot de passe", e);
			Result resultat = new Result();
			resultat.setType("ko");
			resultat.setContenu(e.getMessage());
			return ResponseEntity.ok(resultat);
		}
	}
}
