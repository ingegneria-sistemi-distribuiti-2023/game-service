package com.isd.game;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.isd.game.commons.MatchStatus;
import com.isd.game.controller.MatchController;
import com.isd.game.dto.MatchDto;
import com.isd.game.mapper.MatchMapperService;

// narrow down the tests to just the web layer, 
// using @WebMvcTest to test the MatchController
@WebMvcTest(MatchController.class) 
public class MatchTests {
//    @MockBean
//	MatchMapperService matchMapperService;
//
//	@Autowired
//	private MockMvc mockMvc;

//    @Test
//	public void matchAllShouldReturnAListOfMatchFromService() throws Exception {
//		when(matchMapperService.getAllData()).thenReturn(
//            Arrays.asList(
//                new MatchDto(1, 1, 2, "Team 1", "Team 2", 2, 1, 1.3, 2d, 45, 3, new Date(), null, MatchStatus.PLAYING),
//                new MatchDto(5, 7, 3, "Team 7", "Team 3", 1, 1, 3d, 2d, 88, 0, new Date(), null, MatchStatus.PLAYING)
//                )
//            );
//
//		// mock performs a GET request to the /match/all endpoint
//		// and check if the response status is 200 (OK)
//		// and check if the response body contains the expected DTOs
//		MvcResult result = this.mockMvc.perform(get("/match/all")).andDo(print())
//			.andExpect(status().isOk())
//			.andExpect(content().string(containsString("Team 1")))
//			.andExpect(content().string(containsString("Team 7")))
//			.andReturn();
//
//		// check if result is equal to the expected DTOs
//		JsonMapper mapper = new JsonMapper();
//		MatchDto[] matchDtos = mapper.readValue(result.getResponse().getContentAsString(), MatchDto[].class);
//
//		// matchDtos[0].setAwayTeamId(99); // this will fail the test
//
//		for (int i = 0; i < matchDtos.length; i++) {
//			assertEquals(matchDtos[i], matchMapperService.getAllData().get(i));
//		}
//	}
//
	// TODO: once the payout logic is implemented, add more tests
}
