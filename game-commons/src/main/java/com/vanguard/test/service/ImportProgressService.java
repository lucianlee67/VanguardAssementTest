package com.vanguard.test.service;

import com.vanguard.test.entities.ImportProgress;

public interface ImportProgressService {
    public int add(ImportProgress importProgress);
    public int delete(Integer id);
    public int update(ImportProgress importProgress);

}
