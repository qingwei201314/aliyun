package im.gsj.image.controller;

import java.util.List;

import im.gsj.dao.ProductDao;
import im.gsj.entity.Product;
import im.gsj.image.service.ImageService;
import im.gsj.util.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/image")
public class ImageController {
	@Resource
	private ImageService imageService;
	@Resource
	private ProductDao productDao;
	
	@RequestMapping(value="addImage.do", method=RequestMethod.GET)
	public String addImage(HttpSession session, ModelMap model){
		String phone = (String)session.getAttribute(Constant.phone);
		model = imageService.addImage(phone, model);
		return "/admin/image/addImage";
	}

	/**
	 * 取得某一类别的产品
	 * @return
	 */
	@RequestMapping(value="getProductList.do", method=RequestMethod.GET)
	public String getProductList(@RequestParam("categoryId") String categoryId, ModelMap model) {
		List<Product> productList = productDao.queryList("category_id", categoryId);
		model.addAttribute("productList", productList);
		return "/admin/image/getProductList";
	}
}
