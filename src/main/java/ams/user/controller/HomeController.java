package ams.user.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ams.group.domain.GroupScheduleVO;
import ams.user.domain.UserVO;
import ams.user.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject UserService service;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Principal principal, Model model) throws Exception {
		logger.info("Welcome home!");
		
		
		LocalDateTime today = LocalDateTime.now();
		int day = today.getDayOfWeek().getValue();
		LocalDateTime weekStart = today.minusDays(day);
		LocalDateTime weekEnd = today.plusDays(6-day);
		
		List<String> weeks = new ArrayList<>();
		for(LocalDateTime d = weekStart; d.isBefore(weekEnd.plusDays(1)); d=d.plusDays(1)) {
			weeks.add(d.format(format));
		}
		
		model.addAttribute("weeks", weeks);
		model.addAttribute("beginDate", weekStart.format(format));
		
		UserVO vo = new UserVO();
		vo.setUserId(principal.getName());
		vo.setWeekStart(weekStart.format(format));
		vo.setWeekEnd(weekEnd.format(format));
		List<GroupScheduleVO> schedule = service.showWeekSchedule(vo);
		
		for(GroupScheduleVO s : schedule) {
			System.out.println(s.getGroupName());
			System.out.println(s.getGroupId());
			System.out.println(s.getBeginTime());
			System.out.println(s.getGroupCategory());
		}
		
		model.addAttribute("schedules", schedule);
		
		String userName = service.findUser(principal.getName());
		model.addAttribute("userName", userName);
		return "user_home";
	}
	
}
