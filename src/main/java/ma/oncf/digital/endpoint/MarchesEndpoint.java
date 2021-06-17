package ma.oncf.digital.endpoint;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.oncf.digital.entity.Fournisseur;
import ma.oncf.digital.entity.Marche;
import ma.oncf.digital.entity.json.MarcheJson;
import ma.oncf.digital.repo.foo.FournisseurRepository;
import ma.oncf.digital.repo.foo.MarcheRepository;

@RestController
@RequestMapping("/marches")
@CrossOrigin(origins = "http://localhost:4200")
public class MarchesEndpoint {

	private final Logger log = Logger.getLogger(MarchesEndpoint.class);

	@Autowired
	private FournisseurRepository fournisseurRepository;
	@Autowired
	private MarcheRepository marcheRepository;

	// liste des facture selon les criteres
	// @Secured("")
	@GetMapping
	public List<Marche> getMarcheByFournisseur(@RequestParam("idf") int idf) {
		try {

			Fournisseur fournisseur = fournisseurRepository.getOne(idf);
			if (fournisseur != null) {
				return marcheRepository.findByFournisseur(fournisseur);

			}

			return null;
		} catch (Exception e) {
			log.error("Erreur récuperation marchés", e);
			return null;
		}
	}

	@GetMapping("/fournisseurs")
	public List<Marche> getMarcheByFournisseur(@RequestParam("ct") String codetiers,
			@RequestParam("dateMarche") Integer dateMarche) {
		try {

			return marcheRepository.getByFournisseur(codetiers, dateMarche);

		} catch (Exception e) {
			log.error("Erreur récuperation marchés", e);
			return null;
		}
	}

	@GetMapping("/marche")
	public Marche getMarche(@RequestParam("idM") int idM) {

		Marche marche = marcheRepository.findOne(idM);
		System.out.println("returned fournisseur " + marche.getIdMarche() + "************************");
		return marche;

	}

	@GetMapping("/years")
	public List<Date> getDateMarche(@RequestParam("idfour") Integer fournisseur) {
		try {
			List<Date> dates = marcheRepository.getDateMarche(fournisseur);
			return dates;
		} catch (Exception e) {

			log.error("Erreur récuperation des dates", e);
			return null;
		}
	}

	@PutMapping("/updateStatus")
	public Marche updateStatusMarche(@RequestParam("idm") int idm, @RequestBody MarcheJson marcheDetails) {

		Marche marche = marcheRepository.getMarcheByIdMarche(idm);

		marche.setStatusMarche(marcheDetails.getStatusMarche());
		Marche updatedStatusMarche = marcheRepository.save(marche);
		return updatedStatusMarche;
	}

	@GetMapping("/nbFactures")
	public int getNbFactures(@RequestParam("idm") Integer marche) {
		try {
			System.out.println("List factures ******************************");
			int nbFacture = marcheRepository.getNbFactureInMarche(marche);
			return nbFacture;
		} catch (Exception e) {

			log.error("Erreur récuperation des dates", e);
			return 0;
		}
	}

	// Back-OFFICE
	@GetMapping("/pageFournisseurs")
	public Page<Marche> getMarcheByFournisseur1(@RequestParam("ct") String codetiers,
			@RequestParam("dateMarche") Integer dateMarche, Pageable page) {
		try {

			Page<Marche> marche = marcheRepository.getPageByFournisseur(codetiers, dateMarche, page);
			return marche;

		} catch (Exception e) {
			log.error("Erreur récuperation marchés", e);
			return null;
		}
	}

	@GetMapping("/countMarche")
	public int getCountMarche() {
		return marcheRepository.getNbAllMarche();
	}

	/*
	 * @GetMapping("/countMarcheById") public int
	 * getCountMarche(@RequestParam("idfou") int idFournisseur) { return
	 * marcheRepository.getNbAllMarcheById(idFournisseur); }
	 */

}
