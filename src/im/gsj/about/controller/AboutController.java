package im.gsj.about.controller;

import im.gsj.about.service.AboutService;
import im.gsj.entity.About;
import im.gsj.util.Constant;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/about")
public class AboutController {
	@Resource
	private AboutService aboutService;

	@RequestMapping(value = "addAbout.do", method = RequestMethod.GET)
	public String addAbout(HttpSession session, ModelMap model) {
		String toView = "/admin/about/addAbout";
		String phone = (String) session.getAttribute(Constant.phone);
		About about = aboutService.getByPhone(phone);
		if (about != null) {
			model.addAttribute("about", about);
			toView = "/admin/about/viewAbout";
		}
		return toView;
	}

	@RequestMapping(value = "saveAbout", method = RequestMethod.POST)
	public String saveAbout(@ModelAttribute("about") About about,
			HttpSession session, ModelMap model) {
		String phone = (String) session.getAttribute(Constant.phone);
		about = aboutService.saveAbout(about, phone);
		model.addAttribute("about", about);
		return "/admin/about/viewAbout";
	}

	@RequestMapping(value = "editAbout.do", method = RequestMethod.GET)
	public String editAbout(HttpSession session, ModelMap model) {
		String phone = (String) session.getAttribute(Constant.phone);
		About about = aboutService.getByPhone(phone);
		model.addAttribute("about", about);
		return "/admin/about/addAbout";
	}
}
