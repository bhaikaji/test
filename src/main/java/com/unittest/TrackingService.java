package com.unittest;
import java.util.*;

public class TrackingService {
    private int total;
    private int goal;
    private List<HistoryItem> history = new ArrayList<HistoryItem>();
    private int historyId = 0;
    private Notifier notifier;

    public TrackingService(Notifier notifier){
        this.notifier = notifier;
    }

    public void addProtein(int amount){
        total += amount;
        history.add(new HistoryItem(historyId++, amount, "add", total));
        if(total > goal) {
            boolean sendResult = notifier.send("goal met");
            String historyMessage = "sent:goal met";
            if(!sendResult)
                historyMessage = "send_error:goal met";
            history.add(new HistoryItem(historyId++,0, historyMessage, total));
        }
    }

    public void removeProtein(int amount){
        total -= amount;
        if(total < 0)
            total = 0;

        history.add(new HistoryItem(historyId++, amount, "subtract", total));
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getGoal(){
        return goal;
    }

    public void setGoal(int goal) throws InvalidGoalException {
        if(goal < 0)
            throw new InvalidGoalException("Goal was less than zero!");
        this.goal = goal;
    }

    public List<HistoryItem> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryItem> history) {
        this.history = history;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }


}
