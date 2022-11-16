package fi.swd4tn020.teekauppa.webcontroller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.swd4tn020.teekauppa.domain.MatchaRepository;
import fi.swd4tn020.teekauppa.domain.Matcha;

@Controller
public class ShoppingCartController {
	@Autowired
	private HttpSession session;

	@Autowired
	private MatchaRepository repository;

	// Show shopping cart
	@RequestMapping(value = "/shoppingcart")
	public String showCart(Model model) {
		model.addAttribute("cart", session.getAttribute("cart"));
		return "shoppingcart";
	}

	// Add to shopping cart
	@RequestMapping(value = "/addtocart/{id}", method = RequestMethod.GET)
	public String addtoCart(@PathVariable(value = "id") Long matchaId) {
		List<Matcha> cart = (List<Matcha>) session.getAttribute("cart");

		if (cart == null) { // ostoskoria ei ole
			cart = new ArrayList<Matcha>(); // luodaan tyhjä ostoskori
			session.setAttribute("cart", cart);
		}

		Matcha item = repository.findById(matchaId).get();
		cart.add(item);

		return "redirect:/shoppingcart";
	}

	// Delete from cart
	@RequestMapping(value = "/deletefromcart/{id}", method = RequestMethod.GET)
	public String deleteFromCart(@PathVariable(value = "id") Long matchaId) {
		List<Matcha> cart = (List<Matcha>) session.getAttribute("cart");

		Matcha item = repository.findById(matchaId).get();
		cart.remove(item);

		return "redirect:/shoppingcart";
	}
	

	// Reset shopping chart
	@RequestMapping(value = "/resetcart", method = RequestMethod.GET)
	public String resetCart() {
		
		List<Matcha> cart = (List<Matcha>) session.getAttribute("cart");

		if (cart != null) { // ostoskori on
			cart.clear(); // poistetaan ostoskoriin lisätyt asiat
		}

		return "redirect:/shoppingcart";
	}
}