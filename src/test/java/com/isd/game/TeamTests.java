package com.isd.game;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.isd.game.controller.TeamController;
import com.isd.game.dto.TeamDTO;
import com.isd.game.mapper.TeamMapperService;

// narrow down the tests to just the web layer, 
// using @WebMvcTest to test the TeamController
@WebMvcTest(TeamController.class) 
public class TeamTests {
    @MockBean
	TeamMapperService teamMapperService;

	@Autowired
	private MockMvc mockMvc;

    @Test
	public void teamAllShouldReturnAListOfTeamsFromService() throws Exception {
		when(teamMapperService.getAllData()).thenReturn(
            Arrays.asList(
                new TeamDTO(1, "Team 1"),
                new TeamDTO(2, "Team 2")
                )
            );

        // mock performs a GET request to the /team/all endpoint
		// and check if the response status is 200 (OK)
		// and check if the response body contains the expected DTOs
		MvcResult result = this.mockMvc.perform(get("/team/all")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Team 1")))
        .andExpect(content().string(containsString("Team 2")))
        .andReturn();

        // check if result is equal to the expected DTOs
        JsonMapper mapper = new JsonMapper();
        TeamDTO[] teamDtos = mapper.readValue(result.getResponse().getContentAsString(), TeamDTO[].class);

        // teamDtos[0].setId(99); // this will fail the test

        for (int i = 0; i < teamDtos.length; i++) {
            assertEquals(teamDtos[i], teamMapperService.getAllData().get(i));
        }
	}
}
