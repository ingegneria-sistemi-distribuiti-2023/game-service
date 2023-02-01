package com.isd.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.isd.game.commons.MatchStatus;
import com.isd.game.controller.MatchController;
import com.isd.game.dto.MatchDTO;
import com.isd.game.mapper.MatchService;
import com.isd.game.repository.MatchRepository;

// narrow down the tests to just the web layer, 
// using @WebMvcTest to test the MatchController
@WebMvcTest(MatchController.class)
public class MatchControllerTests {
	@Autowired
    @Value("${app.service.secret}")
    private String SECRET_SERVICE_KEY;

	@MockBean
	MatchService matchService;

	@MockBean
	MatchRepository matchRepository;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test the /game/match endpoint without the secret key
	 * @throws Exception
	 */
	@Test
    public void matchAllShouldReturnUnautorizedErrorWhenCalledWithoutSecretKey() throws Exception {
		
        // mock performs a GET request to the /team/all endpoint without the secret key
		// and check if the response status is 401 (UNAUTHORIZED)
		this.mockMvc.perform(get("/game/match")).andDo(print())
        .andExpect(status().isUnauthorized())
        .andReturn();
	}

	/**
	 * Test the /game/match endpoint with the wrong secret key
	 * @throws Exception
	 */
	@Test
	public void matchAllShouldReturnUnautorizedErrorWhenCalledWithWrongSecretKey() throws Exception {
		
		// mock performs a GET request to the /team/all endpoint with the wrong secret key
		// and check if the response status is 401 (UNAUTHORIZED)
		this.mockMvc.perform(get("/game/match").header("Secret-Key", "wrong secret key")).andDo(print())
		.andExpect(status().isUnauthorized())
		.andReturn();
	}

	/**
	 * Test the /game/match endpoint with the correct secret key
	 * @throws Exception
	 */
	@Test
	public void matchAllShouldReturnAListOfMatchFromService() throws Exception {

		// mock the service to return a list of DTOs
		List <MatchDTO> dummyMatchDTOs = Arrays.asList(
			new MatchDTO(1, 0, 1, "Inter", "Milan", 0, 2, 3.0, 1.1, 2.2, 32, new Date(), null, MatchStatus.PLAYING),
			new MatchDTO(2, 2, 3, "Catania", "Palermo", 4, 0, 1.1, 5.0, 3.5, 45, new Date(), null, MatchStatus.PLAYING)
			);
		when(matchService.getAllData()).thenReturn(dummyMatchDTOs);
		
		// mock performs a GET request to the /match/all endpoint
		// and check if the response status is 200 (OK)
		// and check if the response body contains the expected DTOs
		MvcResult result = this.mockMvc.perform(get("/game/match/").header("Secret-Key", SECRET_SERVICE_KEY)).andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Milan")))
			.andExpect(content().string(containsString("Catania")))
			.andReturn();

		// check if result is equal to the expected DTOs
		JsonMapper mapper = new JsonMapper();
		MatchDTO[] matchDtos = mapper.readValue(result.getResponse().getContentAsString(), MatchDTO[].class);

		// matchDtos[0].setAwayTeamId(99); // this will fail the test

		for (int i = 0; i < matchDtos.length; i++) {
			assertEquals(matchDtos[i], matchService.getAllData().get(i));
		}
	}

	/**
	 * Test the /game/match/{id} endpoint, it should return a single team from the service by id
	 * @throws Exception
	 */
    @Test
    public void matchByIdShouldReturnASingleTeamFromService() throws Exception {
	
		Integer matchId = 1;
        when(matchService.findMatch(matchId)).thenReturn(
            new MatchDTO(1, 0, 1, "Inter", "Milan", 0, 2, 3.0, 1.1, 2.2, 32, new Date(), null, MatchStatus.PLAYING)
        );

        // mock performs a GET request to the /game/match/{id} endpoint
        // and check if the response status is 200 (OK)
        // and check if the response body contains the expected DTO
        MvcResult result = this.mockMvc.perform(get("/game/match/"+matchId.toString()).header("Secret-Key", SECRET_SERVICE_KEY)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Milan")))
        .andReturn();

        // check if result is equal to the expected DTO
        JsonMapper mapper = new JsonMapper();
        MatchDTO matchDTO = mapper.readValue(result.getResponse().getContentAsString(), MatchDTO.class);

        // matchDTO.setHomeTeamId(99); // this will fail the test

        assertEquals(matchDTO, matchService.findMatch(matchId));
    }
}
