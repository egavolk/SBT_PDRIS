package edu.phystech.spring.serializer;

import edu.phystech.spring.Audits;
import org.json.JSONObject;

public class JSONSerializer implements Serializer {
    @Override
    public String toString(Audits audits) {
        JSONObject json = new JSONObject(audits.getAuditKnownUsers());
        json.put("< UNKNOWN >", audits.getAuditUnkownUsers());
        return json.toString();
    }
}
