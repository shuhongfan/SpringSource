package com.abc.service;

import com.abc.bean.Depart;
import com.abc.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartRepository repository;

    @Override
    public Depart getDepartById(int id) {
        if(repository.existsById(id)) {
            return repository.getOne(id);
        }
        Depart depart = new Depart();
        depart.setName("no this depart");
        return depart;
    }

}
