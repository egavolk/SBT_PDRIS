package edu.phystech.spring.controller;

import edu.phystech.spring.Audits;
import edu.phystech.spring.serializer.Serializer;
import edu.phystech.spring.UsersDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class MainController {
    private final Audits audits;
    private final UsersDB usersDB;
    private final Serializer serializer;

    public MainController(Audits audits, UsersDB usersDB, Serializer serializer) {
        this.audits = audits;
        this.usersDB = usersDB;
        this.serializer = serializer;
    }

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
            return processAction(model, "sign_in", "Username and password should not be empty");
        }

        if (!usersDB.isUsernameExist(username)) {
            return processActionWithAudit(model, null, Audits.Status.UNKNOWN_USER, "sign_in",
                                          String.format("Unknown username %s. Please, sign up", username));
        }

        if (usersDB.getUserPassword(username).equals(password)) {
            return processActionWithAudit(model, username, Audits.Status.SUCCESS, "content",
                                          String.format("Hello, %s!", username));
        }
        else {
            return processActionWithAudit(model, username, Audits.Status.WRONG_PASSWORD, "sign_in",
                                          "Wrong password");
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
            return processAction(model, "sign_up", "Username and password should not be empty");
        }

        if ("admin".equals(username)) {
            return processAction(model, "sign_up", "Username admin is reserved");
        }

        if (usersDB.isUsernameExist(username)) {
            return processAction(model, "sign_up", String.format("Username %s is exist", username));
        }

        usersDB.addUser(username, password);

        return processAction(model, "sign_in", String.format("Username %s successfully signed up", username));
    }

    @GetMapping("/audit")
    public String auditGet(Model model) {
        model.addAttribute("audits", serializer.toString(audits));
        return "audits";
    }

    private String processAction(Model model, String page, String message) {
        model.addAttribute("message", message);
        return page;
    }

    private String processActionWithAudit(Model model, String username, Audits.Status status,
                                          String page, String message) {
        audits.logAudit(username, status);
        model.addAttribute("message", message);
        return page;
    }
}
