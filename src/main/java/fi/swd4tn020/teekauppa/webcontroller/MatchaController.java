package fi.swd4tn020.teekauppa.webcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.swd4tn020.teekauppa.domain.Matcha;
import fi.swd4tn020.teekauppa.domain.MatchaRepository;
import fi.swd4tn020.teekauppa.domain.ProducerRepository;

@Controller
public class MatchaController {

	@Autowired
	private MatchaRepository mrepository;
	@Autowired
	private ProducerRepository prepository;
	

	
	@RequestMapping(value = {"/"})
	public String home() {
		return "home";
	}
	
	@RequestMapping("/matchalist")
	public String matchaList(Model model) {
		model.addAttribute("matchas", mrepository.findAll());
		return "matchalist";
	}
	
	
	
	//NOT USED
	
	@RequestMapping(value = "/matchas", method = RequestMethod.GET)
	public @ResponseBody List<Matcha> matchaListRest() {
		return (List<Matcha>) mrepository.findAll();
	}

	// RESTful service to get Matcha by id
	@RequestMapping(value = "/matchas/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Matcha> findMatchaRest(@PathVariable("id") Long matchaId) {
		return mrepository.findById(matchaId);
	}
	

	
	
	
	// Delete matcha
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteMatcha(@PathVariable("id") Long matchaId, Model model) {
		mrepository.deleteById(matchaId);
		return "redirect:/matchalist";
	}

	// Add matcha
	@RequestMapping(value = "/add")
	public String addMatcha(Model model) {
		model.addAttribute("matcha", new Matcha());
		model.addAttribute("producers", prepository.findAll());
		return "addmatcha";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Matcha matcha) {
		mrepository.save(matcha);
		return "redirect:matchalist";
	}

	// Edit matcha
	@RequestMapping(value = "/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String updateMatcha(@PathVariable("id") Long id, Model model) {
		Matcha matcha = mrepository.findById(id).get();
		model.addAttribute("matcha", matcha);
		model.addAttribute("producers", prepository.findAll());
		return "editmatcha";
	}

}
