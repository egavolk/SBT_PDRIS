package edu.phystech.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Audits {
    public enum Status {SUCCESS, WRONG_PASSWORD, UNKNOWN_USER}
    final private Map<String, ArrayList<Status>> usersLog = new HashMap<String, ArrayList<Status>>();
    final private List<Status> unknownLog = new ArrayList<Status>(); //for actions of unknown users

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

    public List<Status> getAudit(String username) {
        if (username == null) {
            return unknownLog;
        }
        return usersLog.get(username);
    }

    public Map<String, ArrayList<Status>> getAuditKnownUsers() {
        return usersLog;
    }

    public List<Status> getAuditUnkownUsers() {
        return unknownLog;
    }
}
