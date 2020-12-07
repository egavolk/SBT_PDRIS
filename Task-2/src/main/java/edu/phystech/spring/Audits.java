package edu.phystech.spring;

import java.util.ArrayList;
import java.util.HashMap;

public class Audits {
    public enum Status {SUCCESS, WRONG_PASSWORD, UNKNOWN_USER}
    private HashMap<String, ArrayList<Status>> usersLog = new HashMap<String, ArrayList<Status>>();
    private ArrayList<Status> unknownLog = new ArrayList<Status>(); //for actions of unknown users

    public void logAudit(String username, Status status) {
        if (username == null) {
            unknownLog.add(status);
        }
        else {
            if (usersLog.get(username) == null) {
                usersLog.put(username, new ArrayList<Status>());
            }
            usersLog.get(username).add(status);
        }
    }

    public ArrayList<Status> getAudit(String username) {
        if (username == null) {
            return unknownLog;
        }
        return usersLog.get(username);
    }

    public HashMap<String, ArrayList<Status>> getAuditKnownUsers() {
        return usersLog;
    }

    public ArrayList<Status> getAuditUnkownUsers() {
        return unknownLog;
    }
}
