package com.intel.site.services;


import com.intel.site.dto.Book;
import com.intel.site.dto.Record;
import com.intel.site.dto.Sertificate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void changeDone(int id, boolean done) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("done", done);
        jdbcTemplate.update("UPDATE sertificates SET done = :done WHERE id = :id", param);
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT id,name,phonenumber,email,datequest,quest, messageuser,done FROM booking",
                (rs, rowNum) -> new Book(rs.getInt("id"),
                        rs.getString("name"), rs.getString("phonenumber"),
                        rs.getString("email"), rs.getString("datequest"),rs.getString("quest"),
                        rs.getString("messageuser"), rs.getBoolean("done")));
    }

    public int getNewIdBook() {
        int i = 0;
        for (Book sert : getAllBooks()) {
            if (i <= sert.getId()) {
                i = sert.getId() + 1;
            }
        }
        return i;
    }

    public void addBook(Book book) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", book.getId());
        param.put("name", book.getName());
        param.put("phone", book.getPhone());
        param.put("email", book.getEmail());
        param.put("quest", book.getQuest());
        param.put("datequest", book.getDatequest());
        param.put("messageuser", book.getMessageuser());
        param.put("done", book.isDone());
        jdbcTemplate.update("INSERT INTO booking(id,name,phonenumber,email,datequest,quest,messageuser,done) " +
                        "VALUES (:id,:name,:phone,:email,:datequest,:quest,:messageuser,:done)"
                , param);
    }

    public void changeDonebook(int id, boolean done) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("done", done);
        jdbcTemplate.update("UPDATE booking SET done = :done WHERE id = :id", param);
    }

    public List<Record> getAllRecords(){
        return jdbcTemplate.query("SELECT id,crew,timeexit,quest FROM records",
                (rs, rowNum) -> new Record(rs.getInt("id"),
                        rs.getString("crew"), rs.getFloat("timeexit"),
                        rs.getString("quest")));
    }


    public List<Record> getClinicRecords(List<Record> records){
        List<Record> list = new ArrayList<>();
        for (Record record: records){
            if (record.getQuest().equals("Клініка")){
                list.add(record);
            }
        }
        return list;
    }


    public List<Record> getKushRecords(List<Record> records){
        List<Record> list = new ArrayList<>();
        for (Record record: records){
            if (record.getQuest().equals("Куш")){
                list.add(record);
            }
        }
        return list;
    }


}
