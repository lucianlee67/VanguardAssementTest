package com.vanguard.test.service.impl;

import com.vanguard.test.entities.ImportProgress;
import com.vanguard.test.mapper.ImportProgressMapper;
import com.vanguard.test.service.ImportProgressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ImportProgressServiceImpl implements ImportProgressService {
    @Resource
    ImportProgressMapper importProgressMapper;


    @Override
    public int add(ImportProgress importProgress) {
        return importProgressMapper.insertSelective(importProgress);
    }

    @Override
    public int delete(Integer id) {
        return importProgressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(ImportProgress importProgress) {
        return importProgressMapper.updateByPrimaryKey(importProgress);
    }
}
