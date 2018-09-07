package com.intel.site.controller;

import com.intel.site.dto.Book;
import com.intel.site.dto.Emailsend;
import com.intel.site.dto.Record;
import com.intel.site.dto.Sertificate;
import com.intel.site.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    DbService dbService;

    @GetMapping(value = "rules")
    public String rules() {
        return "rules";
    }

    @GetMapping(value = "home")
    public String home() {
        return "home";
    }


    @GetMapping(value = "discount")
    public String discount() {
        return "discount";
    }

    @GetMapping(value = "contacts")
    public String contacts() {
        dbService.addSertificate(new Sertificate(dbService.getNewId(), "new", "phone", "email", true));
        return "contacts";
    }

    @RequestMapping(value = "sertificate", method = RequestMethod.GET)
    public String sertificate() {
        return "sertificate";
    }

    @RequestMapping(value = "sertificate", method = RequestMethod.POST)
    public String newSertificate(@RequestParam String name, @RequestParam String phone, @RequestParam String email) {
        dbService.addSertificate(new Sertificate(dbService.getNewId(), name, phone, email));
        return "sertificate";
    }

    @GetMapping(value = "main")
    public String main() {
        return "main";
    }

    @GetMapping(value = "admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "admin", method = RequestMethod.POST)
    public String admen(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer idquest) {
        if (id != null) {
            dbService.changeDone(id, true);
        }
        if (idquest != null) {
            dbService.changeDonebook(idquest, true);
        }
        return "admin";
    }

    @RequestMapping(value = "adminmenu", method = RequestMethod.POST)
    public String adminmenu(@RequestParam String admin, HttpServletRequest req) {
        if (admin.equals("vad")) {
            req.getSession().setAttribute("admin", admin);
            return "adminmenu";
        }
        return "admin";
    }


    @RequestMapping(value = "adminpage", method = RequestMethod.POST)
    public String adminPage(Model model, HttpServletRequest req, @RequestParam String action) {
        if (req.getSession().getAttribute("admin").equals("vad")) {
            switch (action) {
                case "sert":
                    List<Sertificate> list = dbService.getAllSertificates();
                    model.addAttribute("list", list);
                    return "adminpage";
                case "book":
                    model.addAttribute("books", dbService.getAllBooks());
                    return "adminbooking";
                case "record":
                    List<Record> kush = dbService.getKushRecords(dbService.getAllRecords());
                    List<Record> clinic = dbService.getClinicRecords(dbService.getAllRecords());
                    Collections.sort(kush);
                    Collections.sort(clinic);
                    model.addAttribute("kush", kush);
                    model.addAttribute("clinic", clinic);
                    return "recordsadmin";
                default:
                    return "adminmenu";

            }
        }
        return "admin";
    }

    @RequestMapping(value = "booking", method = RequestMethod.GET)
    public String booking() {
        return "booking";
    }

    @RequestMapping(value = "booking", method = RequestMethod.POST)
    public String endBooking(Model model, @RequestParam String name, @RequestParam String phone,
                             @RequestParam String email, @RequestParam String date, @RequestParam String time, @RequestParam String message) {
        dbService.addBook(new Book(dbService.getNewIdBook(), name, phone, email, date + "T" + time, "Куш", message));
        Emailsend em = new Emailsend(name, phone, email, date + "T" + time, message, "Куш");
        em.send();
        model.addAttribute("isGood", "Квет забронирован успешно");
        return "booking";
    }

    @RequestMapping(value = "bookingclin", method = RequestMethod.GET)
    public String bookingClin() {
        return "bookingclin";
    }

    @RequestMapping(value = "bookingclin", method = RequestMethod.POST)
    public String endBookingClin(Model model, @RequestParam String name, @RequestParam String phone,
                                 @RequestParam String email, @RequestParam String date, @RequestParam String time, @RequestParam String message) {
        model.addAttribute("isGood", "Квет забронирован успешно");
        Emailsend em = new Emailsend(name, phone, email, date + "T" + time, message, "Клініка");
        em.send();
        dbService.addBook(new Book(dbService.getNewIdBook(), name, phone, email, date + "T" + time, "Клініка", message));
        model.addAttribute("isGood", "Квет забронирован успешно");
        return "bookingclin";
    }

    @RequestMapping(value = "records")
    public String records(Model model) {
        List<Record> kush = dbService.getKushRecords(dbService.getAllRecords());
        List<Record> clinic = dbService.getClinicRecords(dbService.getAllRecords());
        Collections.sort(kush);
        Collections.sort(clinic);
        model.addAttribute("kush", kush);
        model.addAttribute("clinic", clinic);
        return "records";
    }

    @RequestMapping(value = "recordsadmin", method = RequestMethod.POST)
    public String recordsAdmin(Model model, @RequestParam String crew, @RequestParam float timeExit) {
        dbService.addRecord(new Record(dbService.getNewIdRecord(), crew, timeExit,"Куш"));
        List<Record> kush = dbService.getKushRecords(dbService.getAllRecords());
        List<Record> clinic = dbService.getClinicRecords(dbService.getAllRecords());
        Collections.sort(kush);
        Collections.sort(clinic);
        model.addAttribute("kush", kush);
        model.addAttribute("clinic", clinic);
        return "recordsadmin";
    }


    @RequestMapping(value = "recordsadminclin", method = RequestMethod.POST)
    public String recordsAdminClin(Model model, @RequestParam String crew, @RequestParam float timeExit) {
        dbService.addRecord(new Record(dbService.getNewIdRecord(), crew, timeExit,"Клініка"));
        List<Record> kush = dbService.getKushRecords(dbService.getAllRecords());
        List<Record> clinic = dbService.getClinicRecords(dbService.getAllRecords());
        Collections.sort(kush);
        Collections.sort(clinic);
        model.addAttribute("kush", kush);
        model.addAttribute("clinic", clinic);
        return "recordsadmin";
    }
}
