package Utils.log;

import java.util.ArrayList;

public class Log {
    public ArrayList<String> logList;

    public Log() {
        logList = new ArrayList<>();
    }

    public void addLog(String log) {
        logList.add(log);
    }

    public void printLog() {
        for (String log : logList) {
            System.out.println(log);
        }
    }
}
