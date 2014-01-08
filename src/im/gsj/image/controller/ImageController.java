package im.gsj.image.controller;

import im.gsj.image.service.ImageService;
import im.gsj.util.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/image")
public class ImageController {
	@Resource
	private ImageService imageService;

	
	@RequestMapping(value="addImage.do", method=RequestMethod.GET)
	public String addImage(HttpSession session, ModelMap model){
		String phone = (String)session.getAttribute(Constant.phone);
		model = imageService.addImage(phone, model);
		return "/admin/image/addImage";
	}
}
