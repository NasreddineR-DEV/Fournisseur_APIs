package ma.oncf.digital.endpoint;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.oncf.digital.entity.Facture;
import ma.oncf.digital.entity.json.FactureJson;
import ma.oncf.digital.repo.foo.FactureRepository;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class FacturesEndpoint {

	private final Logger log = Logger.getLogger(FacturesEndpoint.class);

	@Autowired
	private FactureRepository factureRepository;

	// @Secured("")

	@GetMapping("/factureByFournisseur")
	// ct : codetiers , l : limit
	public List<Facture> getFactureByNumero(@RequestParam("ct") String codetiers,
			@RequestParam("dateFact") Integer dateFacture, @RequestParam("l") Integer limit) {
		try {
			System.out.println("List factures ******************************");
			List<Facture> factures = factureRepository.getByCriteria(codetiers, dateFacture, limit);
			return factures;
		} catch (Exception e) {

			log.error("Erreur récuperation factures", e);
			return null;
		}
	}

	@GetMapping("/factureByMarche")
	public List<Facture> getFactureByNuemro(@RequestParam("idM") String codeMarche, @RequestParam("l") Integer limit) {
		try {
			System.out.println("List factures ******************************");

			List<Facture> factures = factureRepository.getByCriterial(codeMarche, limit);
			return factures;
		} catch (Exception e) {
			log.error("Erreur récuperation factures", e);
			return null;
		}
	}

	@GetMapping("/factureByFacture")
	public Facture getFacture(@RequestParam("idFact") int idFact) {
		Facture facture = factureRepository.findOne(idFact);
		System.out.println("returned fournisseur " + facture.getNumeroFacture() + "************************");
		return facture;

	}

	@GetMapping("/years")
	public List<Date> getDateByFacture(@RequestParam("idfour") Integer fournisseur) {
		try {
			System.out.println("List factures ******************************");
			List<Date> dates = factureRepository.getByCriterial(fournisseur);
			return dates;
		} catch (Exception e) {

			log.error("Erreur récuperation des dates", e);
			return null;
		}
	}

	@PutMapping("/updateStatus")
	public Facture updateStatusFacture(@RequestParam("idf") int idf, @RequestBody FactureJson factureDetails) {

		Facture facture = factureRepository.findByIdFacture(idf);

		facture.setStatusFacture(factureDetails.getStatusFacture());
		Facture updatedStatusFacture = factureRepository.save(facture);
		return updatedStatusFacture;
	}

	@GetMapping("/countFacture")
	public int getCountMarche() {
		return factureRepository.getNbAllFacture();
	}

	@GetMapping("/countFactureByMarche")
	public int getCountFactureByMarche(@RequestParam("idMarche") int idM) {
		return factureRepository.getNbFactureByMarche(idM);
	}

}
