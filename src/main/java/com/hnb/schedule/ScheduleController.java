package com.hnb.schedule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnb.movie.MovieController;
import com.hnb.ticket.TicketVO;



@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	@Autowired ScheduleServiceImpl scheduleService;
	@Autowired TicketVO ticket;
	@Autowired ScheduleVO schedule;
	
	List<?> movieListRate = new ArrayList<>();
	List<?> movieListAsc = new ArrayList<>();
	List<?> theaterList = new ArrayList<>();
	List<?> dateList = new ArrayList<>();
	List<?> timeList = new ArrayList<>();
	List<?> seatList = new ArrayList<>();
	int result;
	
	public String Ticket(){
		return "ticket/Ticket";
	}
	public String movieSelect(String movie,String theater,String date){
		if (theater==null && date!=null) {
			logger.info("극장널");
			theaterList = scheduleService.getTheaterListByMD(movie,date);
		} else if (theater!=null && date==null) {
			logger.info("날짜널");
			dateList = scheduleService.getShowDateListByMT(movie,theater);
		} else if (theater==null && date==null) {
			logger.info("다널");
			theaterList = scheduleService.getTheaterListByM(movie);
			dateList = scheduleService.getShowDateListByM(movie);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = scheduleService.getTimeList(movie, theater, date);
		}
		List movieSelectList = new ArrayList();
		logger.info("극장" +theaterList);
		logger.info("날짜" +dateList);
		movieSelectList.add(theaterList);
		movieSelectList.add(dateList);
		movieSelectList.add(timeList);
		return "ticket/movieSelect";
	}
	public String theaterSelect(String movie,String theater,String date){
		if (movie==null && date!=null) {
			movieListRate = scheduleService.getMovieRateByTD(theater,date);
			movieListAsc = scheduleService.getMovieAscByTD(theater,date);
		} else if (movie!=null && date==null) {
			dateList = scheduleService.getShowDateListByMT(movie, theater);
		} else if (movie==null && date==null) {
			movieListRate = scheduleService.getMovieRateByT(theater);
			movieListAsc = scheduleService.getMovieAscByT(theater);
			dateList = scheduleService.getShowDateListByT(theater);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = scheduleService.getTimeList(movie, theater, date);
		}
		List theaterSelectList = new ArrayList();
		theaterSelectList.add(movieListRate);
		theaterSelectList.add(movieListAsc);
		theaterSelectList.add(dateList);
		theaterSelectList.add(timeList);
		return "ticket/theaterSelect";
	}
	public String dateSelect(String movie,String theater,String date){
		if (movie==null && theater!=null) {
			movieListRate = scheduleService.getMovieRateByTD(theater,date);
			movieListAsc = scheduleService.getMovieAscByTD(theater,date);
		} else if (movie!=null && theater==null) {
			theaterList = scheduleService.getTheaterListByMD(movie,date);
		} else if (movie==null && theater==null) {
			movieListRate = scheduleService.getMovieRateByD(date);
			movieListAsc = scheduleService.getMovieAscByD(date);
			theaterList = scheduleService.getTheaterListByD(date);
		} else if (movie!=null&&theater!=null&&date!=null) {
			timeList = scheduleService.getTimeList(movie, theater, date);
		}
		List dateSelectList = new ArrayList();
		dateSelectList.add(movieListRate);
		dateSelectList.add(movieListAsc);
		dateSelectList.add(theaterList);
		dateSelectList.add(timeList);
		logger.info("결과 : {}", timeList);
		return "ticket/dateSelect";
	}
	 public Model choiceseat(String movie,String theater,String date,String time, Model model){
	      String filmNumber = scheduleService.getFilmNumberBy(movie);
	      System.out.println(filmNumber);
	      ticket.setFilmNumber(filmNumber);
	      ticket.setTheaterName(theater);
	      ticket.setDate(date);
	      ticket.setRoomName(time.split(" ")[0]);
	      ticket.setStartTime(time.split(" ")[1]);
	      logger.info(ticket.getFilmNumber());
	      logger.info(ticket.getTheaterName());
	      logger.info(ticket.getDate());
	      logger.info(ticket.getRoomName());
	      logger.info(ticket.getStartTime());
	      if(result == 1) {
	         model.addAttribute("result", "success");
	      } else {
	         model.addAttribute("result", "fail");
	      }
	      return model;
	   }
	 public String initList(){
	      movieListRate = scheduleService.getRateList();
	      movieListAsc = scheduleService.getAscList();
	      theaterList = scheduleService.getTheaterList();
	      dateList = scheduleService.getShowDateList();
	      List initList = new ArrayList();
	      initList.add(movieListAsc);
	      initList.add(movieListAsc);
	      initList.add(theaterList);
	      initList.add(dateList);
	      logger.info("리스트"+initList);
	      return "ticket/initList";
	   }

	   public String Seats(Model model, String movie,String date,String time){
	      model.addAttribute("movie", movie);
	      model.addAttribute("date", date);
	      model.addAttribute("time", time);
	      return "ticket/initList";
	   }
	   
	   public String infoSave(Model model, String adult,String old_man,String teenager,String price,String seat_number){
	      ticket.setAdult(Integer.parseInt(adult));
	      ticket.setOldMan(Integer.parseInt(old_man));
	      ticket.setTeenager(Integer.parseInt(teenager));
	      ticket.setPrice(Integer.parseInt(price));
	      ticket.setSeatNumber(seat_number);
	      logger.info(adult);
	      logger.info(old_man);
	      logger.info(teenager);
	      logger.info(price);
	      logger.info(seat_number);
	      if(result == 1) {
	         model.addAttribute("result", "success");
	      } else {
	         model.addAttribute("result", "fail");
	      }
	      return "ticket/infoSave";
	   }
	   public String Confirm(){
	      return "ticket/Confirm";
	   }
	   


	

}
