package fi.swd4tn020.teekauppa.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.swd4tn020.teekauppa.domain.ProducerRepository;
import fi.swd4tn020.teekauppa.domain.Producer;


@Controller
public class ProducerController {
	
	@Autowired
	private ProducerRepository prepository;
	
	@GetMapping("/producerlist")
		public String producerList(Model model) {
			model.addAttribute("producers", prepository.findAll());
			return "producerlist";
		}
	// Add Producer
	@RequestMapping(value = "/addproducer")
	public String addProducer(Model model) {
		model.addAttribute("producer", new Producer());
		return "addproducer";
	}

	@RequestMapping(value = "/saveproducer", method = RequestMethod.POST)
	public String save(Producer producer) {
		prepository.save(producer);
		return "redirect:producerlist";
	}
	// Delete Producer
	@RequestMapping(value = "/deleteproducer/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProducer(@PathVariable("id") Long producerId, Model model) {
		prepository.deleteById(producerId);
		return "redirect:/producerlist";
	}
	
	// Edit Producer
	@RequestMapping(value = "/editproducer/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String updateProducer(@PathVariable("id") Long producerId, Model model) {
		Producer producer = prepository.findById(producerId).get();
		model.addAttribute("producer", producer);
		return "editproducer";
	}
}