/**
 * 
 */
package com.shitx.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shitx.utils.JsonTools;

/**
 * @author shitx
 *
 */

@Controller
public class SearchCtrl {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchCtrl.class);
	
	@GetMapping("/greet")
	public void search_keyword(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.getWriter().write(JsonTools.toJson("hello spring石同享"));
	}
	
}
