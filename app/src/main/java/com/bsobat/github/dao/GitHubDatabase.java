package com.bsobat.github.dao;


import com.bsobat.github.dto.GitHubDto;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GitHubDto.class}, version = 2)
public abstract class GitHubDatabase extends RoomDatabase {
    public abstract GitHubDao gitHubDao();
}
