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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.isd.game.controller.TeamController;
import com.isd.game.dto.TeamDTO;
import com.isd.game.mapper.TeamService;

// narrow down the tests to just the web layer, 
// using @WebMvcTest to test the TeamController
@WebMvcTest(TeamController.class) 
public class TeamControllerTests {
    @MockBean
    TeamService teamService;

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    @Value("${app.service.secret}")
    private String SECRET_SERVICE_KEY;

    @Test
	public void teamAllShouldReturnAListOfTeamsFromService() throws Exception {
		// TODO: cambiare, non usare il service ma il repository

		when(teamService.getAllData()).thenReturn(
            Arrays.asList(
                new TeamDTO(1, "Team 1"),
                new TeamDTO(2, "Team 2")
                )
            );

        // mock performs a GET request to the "/game/" endpoint
		// and check if the response status is 200 (OK)
		// and check if the response body contains the expected DTOs
		MvcResult result = this.mockMvc.perform(get("/game/").header("Secret-Key", SECRET_SERVICE_KEY)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Team 1")))
        .andExpect(content().string(containsString("Team 2")))
        .andReturn();

        // check if result is equal to the expected DTOs
        JsonMapper mapper = new JsonMapper();
        TeamDTO[] teamDtos = mapper.readValue(result.getResponse().getContentAsString(), TeamDTO[].class);

        // teamDtos[0].setId(99); // this will fail the test

        for (int i = 0; i < teamDtos.length; i++) {
            assertEquals(teamDtos[i], teamService.getAllData().get(i));
        }
	}

    @Test
    public void teamAllShouldReturnUnautorizedErrorWhenCalledWithoutSecretKey() throws Exception {	
        // mock performs a GET request to the /team/all endpoint without the secret key
		// and check if the response status is 401 (UNAUTHORIZED)
		this.mockMvc.perform(get("/game/")).andDo(print())
        .andExpect(status().isUnauthorized())
        .andReturn();
	}

    @Test
    public void teamAllShouldReturnUnautorizedErrorWhenCalledWithWrongSecretKey() throws Exception {
        this.mockMvc.perform(get("/game/").header("Secret-Key", "wrong-secret-key")).andDo(print())
        .andExpect(status().isUnauthorized())
        .andReturn();
    }
}
