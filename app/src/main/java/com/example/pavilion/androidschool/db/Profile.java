package com.example.pavilion.androidschool.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Pavilion on 07.06.2015.
 */
@DatabaseTable(tableName = "profile")
public class Profile {@DatabaseField(generatedId = true)
private long id;
    @DatabaseField(columnName = "name", canBeNull = false, index = true, indexName = "name_index")
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public Profile() {
        this("");
    }

    public void setId(long id) {this.id = id;}
    public long getId() {return this.id;}

    public void setName(String name) { this.name = name; }
    public String getName() {return this.name;}

}
