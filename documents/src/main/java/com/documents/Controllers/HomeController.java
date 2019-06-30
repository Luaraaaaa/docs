package com.documents.Controllers;

import com.documents.Models.Documents;
import com.documents.Models.Documents_type;
import com.documents.Models.Users;
import com.documents.Repositories.DocumentsRepo;
import com.documents.Repositories.Documents_typeRepo;
import com.documents.Repositories.UserRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private UserRepo UserRepo;

    @Autowired
    private DocumentsRepo DocumentsRepo;

    @Autowired
    private Documents_typeRepo Doc_typeRepo;

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Users> users = UserRepo.findAll();
        //users.forEach(user -> {
          //  System.out.println(new Gson().toJson(user));
        //});
        model.put("users", users);
        model.put("type", Doc_typeRepo.findAll());
        return "main";
    }

    @PostMapping("add_user")
    public String save(@RequestParam String name, @RequestParam String surname, @RequestParam String patronymic,
                       @RequestParam String bith_date, @RequestParam boolean gender, @RequestParam String address) {
        Users user = new Users(surname, name, patronymic, bith_date, gender, address);
        UserRepo.save(user);
        return "redirect:/main";
    }

    @PostMapping("add_document")
    public String save_doc(@RequestParam long user, @RequestParam long number, @RequestParam String type,
                           @RequestParam String agency, @RequestParam String issue_date, @RequestParam String exp_date) {
        Users us = UserRepo.findById(user);
        Documents_type docs_type = Doc_typeRepo.findByType(type);
        if ((DocumentsRepo.findByDocumentsTypeAndUser(docs_type, us)) == null) {
            Documents doc = new Documents(us, number, docs_type, agency, issue_date, exp_date);
            DocumentsRepo.save(doc);
        }
        return "redirect:/main";
    }

    @GetMapping("/fio")
    public String findByFIO(@RequestParam String surname, @RequestParam String name, @RequestParam String patronymic,
                            Map<String, Object> model) {
        List<Users> users = UserRepo.findByNameAndSurnameAndPatronymic(name, surname, patronymic);
        model.put("users1", users);
        return "fio";
    }

    @GetMapping("/bith_date")
    public String findBybith_date(@RequestParam String bith_date,
                                  Map<String, Object> model) {
        List<Users> users = UserRepo.findByBirthdate(bith_date);
        model.put("users2", users);
        return "bith_date";
    }

    @GetMapping("/number")
    public String findBynumber(@RequestParam long number, Map<String, Object> model) {
        List<Documents> docs = DocumentsRepo.findByNumber(number);
        model.put("docs", docs);
        return "number";
    }

    @GetMapping("/usersbynumber/{id}")
    public String usersByNumber(@PathVariable long id, Map<String, Object> model) {
        Users user = UserRepo.findById(id);
        model.put("us", user);
        return "usersbynumber";
    }

    @GetMapping("/listUserDocs")
    public String list(Map<String, Object> model) {
        model.put("users", UserRepo.findAll());
        model.put("docs", DocumentsRepo.findAll());
        return "listUserDocs";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id){
        UserRepo.deleteById(id);
        return "redirect:/listUserDocs";
    }

    @GetMapping("/deleteDocs/{id}")
    public String deleteDocs(@PathVariable long id) {
        DocumentsRepo.deleteById(id);
        return "redirect:/listUserDocs";
    }

    @GetMapping("/editUser")
    public String editUserSendId(@RequestParam long id, Map<String, Object> model) {
        model.put("user", UserRepo.findById(id));
        return "editUser";
    }

    @PostMapping("/edit_User/{id}")
    public String editUser(@PathVariable long id, @RequestParam String name, @RequestParam String surname, @RequestParam String patronymic,
                           @RequestParam String bith_date, @RequestParam boolean gender, @RequestParam String address) {
       // Users user = UserRepo.findById(id);
        Users user = new Users();
        user.setId(id);
        user.setSurname(surname);
        user.setName(name);
        user.setPatronymic(patronymic);
        user.setBirthdate(bith_date);
        user.setGender(gender);
        user.setAddress(address);
        UserRepo.saveAndFlush(user);
        return "redirect:/listUserDocs";
    }

    @GetMapping("/editDocs")
    public String editDocsSendId(@RequestParam long id, Map<String, Object> model) {
        Documents doc = DocumentsRepo.findById(id);
        model.put("doc", doc);
        Users user = UserRepo.findById(doc.getUser().getId());
        model.put("user", user);
        model.put("type", doc.getDocumentsType());
        return "editDocs";
    }

    @PostMapping("/editDocs/{id}")
    public String editDocs(@PathVariable long id, @RequestParam long number,
                           @RequestParam String agency, @RequestParam String issue_date, @RequestParam String exp_date){
        Documents docs = DocumentsRepo.findById(id);
            docs.setId(id);
            docs.setNumber(number);
            docs.setAgency(agency);
            docs.setIssue_date(issue_date);
            docs.setExp_date(exp_date);
            DocumentsRepo.save(docs);
        return "redirect:/listUserDocs";
    }

}
