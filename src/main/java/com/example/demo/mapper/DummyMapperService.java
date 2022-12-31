package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Dummy2Dto;
import com.example.demo.entities.DummyData;
import com.example.demo.repository.DummyDataRepository;


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
public class DummyMapperService {
    // @Autowired annotation is used to inject the object dependency implicitly.
    @Autowired
    private DummyDataRepository dummyDataRepository;

    // get all the data from the database
    public List<Dummy2Dto> getAllData() {
        return dummyDataRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // convert the data from the database to a DTO
    public Dummy2Dto convertToDto(DummyData dummyData) {
        Dummy2Dto dummy2Dto = new Dummy2Dto();
        dummy2Dto.setUserId(dummyData.getId());
        dummy2Dto.setName(dummyData.getData1());
        dummy2Dto.setAnInteger(dummyData.getData2());
        return dummy2Dto;
    }

    // create a new record in the database
    public Dummy2Dto create(Dummy2Dto dummy2Dto) {
        DummyData dummyData = new DummyData();
        dummyData.setData1(dummy2Dto.getName());
        dummyData.setData2(dummy2Dto.getAnInteger());
        return convertToDto(dummyDataRepository.save(dummyData));
    }

    // update a record in the database
    public Dummy2Dto update(Dummy2Dto dummy2Dto) {
        DummyData dummyData = dummyDataRepository.findById(dummy2Dto.getAnInteger()).get();
        dummyData.setData1(dummy2Dto.getName());
        dummyData.setData2(dummy2Dto.getAnInteger());
        return convertToDto(dummyDataRepository.save(dummyData));
    }

    // delete a record from the database
    public void delete(Integer id) {
        // check if the record exists
        if (!dummyDataRepository.existsById(id)) {
            throw new RuntimeException("Record not found");
        }

        dummyDataRepository.deleteById(id); // TODO: this should be a soft delete
    }

    // find a record in the database
    public Dummy2Dto findById(Integer id) {
        // check if the record exists
        if (!dummyDataRepository.existsById(id)) {
            throw new RuntimeException("Record not found");
        }

        return convertToDto(dummyDataRepository.findById(id).get());
    }
}