package im.gsj.uploadify.controller;

import im.gsj.uploadify.service.Uploadify;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/uploadify")
public class UploadifyController {
	@Resource
	private Uploadify uploadify;
	
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String widthXheight = request.getParameter("widthXheight");
		String result = uploadify.uplodate(request,widthXheight);
		PrintWriter pw = response.getWriter();
		pw.append(result);
		return null;
	}
}
