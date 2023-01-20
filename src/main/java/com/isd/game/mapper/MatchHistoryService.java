package com.isd.game.mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.isd.game.converter.MatchHistoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isd.game.domain.MatchHistory;
import com.isd.game.dto.MatchDTO;
import com.isd.game.dto.MatchHistoryDTO;
import com.isd.game.repository.MatchHistoryRepository;
import com.isd.game.repository.TeamRepository;


/**
 * <p>a Mapper is a class that maps one object to another (e.g. a DTO to an Entity)
 * <b>Service</b> annotation is a specialization of Component annotation. 
 * Spring Service annotation can be applied only to classes. 
 * It is used to mark the class as a service provider.
 * This annotation is used with classes that provide some business functionalities.
 * Spring context will autodetect these classes when annotation-based configuration and
 * classpath scanning is used.</p>
 * <br>
 * <p>At a high level, Spring creates proxies for all the classes annotated with
 * Transactional – either on the class or on any of the methods. The proxy
 * allows the framework to inject transactional logic before and after the
 * running method – mainly for starting and committing the transaction</p>
 */
@Service
@Transactional
public class MatchHistoryService {
    private final MatchHistoryRepository matchHistoryRepository;
    
    @Autowired
    MatchHistoryConverter cnv;

    public MatchHistoryService(MatchHistoryRepository matchHistoryRepository) {
        this.matchHistoryRepository = matchHistoryRepository;
    }

    // get all the data from the database
    public List<MatchHistoryDTO> getAllData() {
        List<MatchHistoryDTO> dtos = new LinkedList<>();

        for (MatchHistory mh : matchHistoryRepository.findAll()) {
            dtos.add(cnv.convertToDto(mh));
        }

        return dtos;
    }

}