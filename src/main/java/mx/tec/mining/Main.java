package mx.tec.mining;

import org.deckfour.xes.model.*;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws Exception {

        XLog log = XLogReader.openLog("simulation_logs.mxml.gz");
        PrintStream out = new PrintStream("log.csv");
        out.println("CaseID,Activity,EventType,Resource,Timestamp");

        for (XTrace trace: log) {
            String caseId = trace.getAttributes().get("concept:name").toString();
            for (XEvent event: trace) {
                XAttributeMap attrs = event.getAttributes();
                try {
                    String str = caseId + ","
                            + attrs.get("concept:name").toString() + ","
                            + attrs.get("lifecycle:transition").toString() + ","
                            + attrs.get("org:resource").toString() + ","
                            + attrs.get("time:timestamp").toString();
                    out.println(str);
                } catch ( NullPointerException e ) {
                    e.printStackTrace();
                }
            }
        }
        out.close();
        System.out.println("Process Finished");
    }
}
