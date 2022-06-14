package tw.leonchen.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes(names = {"userName"})
public class HelloController2 {
	@RequestMapping(path = "/hello2.controller", method = RequestMethod.GET)
	public String processAction(@RequestParam("userName") String userName, Model m,SessionStatus status) {
		Map<String, String> errors = new HashMap<String, String>();
		m.addAttribute("errors", errors);

		if (userName == null || userName.length() == 0) {
			errors.put("name", "name is required");
		}

		if (errors != null && !errors.isEmpty()) {
			return "/form.jsp";
		}
		m.addAttribute("userName", userName);
		
		status.setComplete();
		
		return "/sucess.jsp";
	}
	
	@RequestMapping(path = "/hello2.controller", method = RequestMethod.POST)
	public String processAction2(@RequestParam("userName") String userName, Model m,SessionStatus status) {
		Map<String, String> errors = new HashMap<String, String>();
		m.addAttribute("errors", errors);

		if (userName == null || userName.length() == 0) {
			errors.put("name", "name is required");
		}

		if (errors != null && !errors.isEmpty()) {
			return "/form.jsp";
		}
		m.addAttribute("userName", userName);
		
		status.setComplete();
		
		return "/sucess.jsp";
	}
}
