package edu.phystech.spring.controller;

import edu.phystech.spring.Audits;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    @Qualifier("audits")
    Audits audits;

    @Autowired
    @Qualifier("usersDB")
    HashMap<String, String> usersDB;

    @GetMapping("/")
    public RedirectView homeRedirect() {
        return new RedirectView("sign_in");
    }

    @GetMapping("/sign_in")
    public String signInGet() {
        return "sign_in";
    }

    @PostMapping("/sign_in")
    public String signIn(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("message", "Username and password should not be empty");
            return "sign_in";
        }

        if (usersDB.get(username) == null) {
            audits.logAudit(null, Audits.Status.UNKNOWN_USER);
            model.addAttribute("message",
                    String.format("Unknown username %s. Please, sign up", username));
            return "sign_in";
        }

        if (usersDB.get(username).equals(password)) {
            audits.logAudit(username, Audits.Status.SUCCESS);
            model.addAttribute("message", String.format("Hello, %s!", username));
            return "content";
        }
        else {
            audits.logAudit(username, Audits.Status.WRONG_PASSWORD);
            model.addAttribute("message", String.format("Wrong password"));
            return "sign_in";
        }
    }

    @GetMapping("/sign_up")
    public String signUpGet() {
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String signUpPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("message", "Username and password should not be empty");
            return "sign_up";
        }

        if ("admin".equals(username)) {
            model.addAttribute("message", "Username admin is reserved");
            return "sign_up";
        }

        if (usersDB.get(username) != null) {
            model.addAttribute("message",
                    String.format("Username %s is exist", username));
            return "sign_up";
        }

        usersDB.put(username, password);

        model.addAttribute("message",
                String.format("Username %s successfully signed up", username));
        return "sign_in";
    }

    @GetMapping("/audit")
    public String auditGet(Model model) {
        JSONObject json = new JSONObject(audits.getAuditKnownUsers());
        json.put("< UNKNOWN >", audits.getAuditUnkownUsers());
        model.addAttribute("audits", json.toString());
        return "audits";
    }
}
