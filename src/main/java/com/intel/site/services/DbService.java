package com.intel.site.services;


import com.intel.site.dto.Sertificate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DbService {
    private final NamedParameterJdbcTemplate jdbcTemplate;


    public DbService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNewId() {
        int i = 0;
        for (Sertificate sert : getAllSertificates()) {
            if (i <= sert.getId()) {
                i = sert.getId() + 1;
            }
        }
        return i;
    }

    public List<Sertificate> getAllSertificates() {
        return jdbcTemplate.query("SELECT id,name,phonenumber,email,done FROM sertificates",
                (rs, rowNum) -> new Sertificate(rs.getInt("id"),
                        rs.getString("name"), rs.getString("phonenumber"),
                        rs.getString("email"), rs.getBoolean("done")));
    }

    public void addSertificate(Sertificate sertificate) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", sertificate.getId());
        param.put("name", sertificate.getName());
        param.put("phone", sertificate.getPhone());
        param.put("email", sertificate.getEmail());
        param.put("done", sertificate.isDone());
        jdbcTemplate.update("INSERT INTO sertificates(id,name,phonenumber,email,done) VALUES (:id,:name,:phone,:email,:done)"
                , param);
    }

    public void changeDone(int id, boolean done){
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("done",done);
        jdbcTemplate.update("UPDATE sertificates SET done = :done WHERE id = :id",param);
    }


}
