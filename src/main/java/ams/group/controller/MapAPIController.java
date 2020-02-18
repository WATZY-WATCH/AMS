package ams.group.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import ams.user.service.Kakaoapi;

@Controller
@RequestMapping("/group/**")
public class MapAPIController {
	@Inject Kakaoapi KakaoAPI;
	private static final Logger logger = LoggerFactory.getLogger(MapAPIController.class);
	
	@ResponseBody
	@RequestMapping(value="/locInfo", method=RequestMethod.POST)
	public JsonNode getLocationInfo(@RequestBody JsonNode location) {
		logger.info("get clicked location info......");
		JsonNode locInfo = KakaoAPI.getAddressInfo(location);
		return locInfo;
	}
}
