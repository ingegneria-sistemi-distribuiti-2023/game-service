package com.isd.game.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    // this DTO contains the id and the name of the team
    private Integer id;
    private String name;

}
