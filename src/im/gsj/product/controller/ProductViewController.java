package im.gsj.product.controller;

import im.gsj.dao.ImageDao;
import im.gsj.index.service.IndexService;
import im.gsj.product.service.ProductService;
import im.gsj.product.vo.ProductVo;
import im.gsj.util.Page;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductViewController {
	@Resource
	private ProductService productService;
	@Resource
	private ImageDao imageDao;
	@Resource
	private IndexService indexService;
	
	/**
	 * 跳转到产品详细页面
	 */
	@RequestMapping(value="viewProduct.do", method=RequestMethod.GET)
	public String viewProduct(@RequestParam("productId") String productId,@RequestParam("pageNo") int pageNo, ModelMap model) throws IllegalAccessException, InvocationTargetException{
		model = productService.viewProduct(productId, pageNo, model);
		return "/product/viewProduct";
	}
	
	/**
	 * 查看某一张图片
	 */
	@RequestMapping(value="viewImage.do", method=RequestMethod.GET)
	public String viewImage(@RequestParam("imageSrc") String imageSrc, @RequestParam("productName") String productName, ModelMap model){
		model.addAttribute("imageSrc", imageSrc);
		model.addAttribute("productName", productName);
		return "/product/viewImage";
	}
	
	/**
	 * 加载某一产品的更多图片
	 */
	@RequestMapping(value="moreImage.do", method = RequestMethod.GET)
	public String moreImage(@RequestParam("productId") String productId, int pageNo, ModelMap model) throws IllegalAccessException, InvocationTargetException{
		ProductVo productVo = productService.viewProductWithImage(productId, pageNo);
		model.addAttribute("productVo", productVo);
		return "/product/moreImage";
	}
	
	/**
	 * 头部搜索
	 */
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search(@RequestParam("shopId") String shopId, @RequestParam("q") String q, int pageNo, ModelMap model){
		Page<im.gsj.index.vo.ProductVo> page =productService.search(shopId, q, pageNo);
		model.addAttribute("page", page);
		
		//查出头部和尾部信息
		model = indexService.getHeadAndFooter(shopId, model);
		return "/product/searchResult";
	}
}
