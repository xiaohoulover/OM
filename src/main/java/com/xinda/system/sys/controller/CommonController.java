/*
 * Copyright LKK Health Products Group Limited
 */
package com.xinda.system.sys.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 通用的 Controller,处理请求路径.
 * 
 * @author coudy
 *
 *         2017年2月27日
 */
@RestController
public class CommonController {

	@RequestMapping(value = "/{folder1}/{name}.html")
	public ModelAndView renderFolder1View(@PathVariable String folder1, @PathVariable String name, Model model) {
		return new ModelAndView(new StringBuilder().append("/").append(folder1).append("/").append(name).toString());
	}

	@RequestMapping(value = "/{folder1}/{folder2}/{name}.html")
	public ModelAndView renderFolder2View(@PathVariable String folder1, @PathVariable String folder2,
			@PathVariable String name, Model model) {
		return new ModelAndView(new StringBuilder().append("/").append(folder1).append("/").append(folder2).append("/")
				.append(name).toString());
	}

	@RequestMapping(value = "/{name}.html")
	public ModelAndView renderView(@PathVariable String name, Model model) {
		return new ModelAndView(name);
	}

	@RequestMapping(value = "/")
	public ModelAndView renderView() {
		return new ModelAndView("menu");
	}

}
