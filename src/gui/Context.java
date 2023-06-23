/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

/**
 *
 * @author NUWAA
 */
interface NewsChannel {
    public abstract void addSubscriber(Subscriber s);
    public abstract void removeSubscriber(Subscriber s);
    public abstract void newArticle(String title);
    public abstract void notifySubscriber(String notification);
}

class JETNews implements NewsChannel {

    ArrayList<Subscriber> subscriber = new ArrayList<>();

    @Override
    public void addSubscriber(Subscriber s) {
        subscriber.add(s);

    }

    @Override
    public void removeSubscriber(Subscriber s) {
        subscriber.remove(s);
    }

    @Override
    public void newArticle(String title) {
        notifySubscriber("New Article has Published : " + title);
    }
    
    @Override
    public void notifySubscriber(String notification) {
        for (Subscriber s : subscriber) {
            s.post(notification);
        }
    }

}

interface Subscriber {
    public abstract void subscribe(NewsChannel nc);
    public abstract void unsubscribe();
    public abstract void post(String notification);
    public abstract void displayNotification();
}

class NewSubscriber implements Subscriber {

    NewsChannel newsChannel;
    String notificationMsg;

    @Override
    public void subscribe(NewsChannel nc) {
        newsChannel = nc;
        newsChannel.addSubscriber(this);
    }

    @Override
    public void unsubscribe() {
        newsChannel.removeSubscriber(this);
        newsChannel = null;
    }

    @Override
    public void post(String notification) {
        notificationMsg = notification;
        displayNotification();
    }

    @Override
    public void displayNotification() {
        NotificationPanel np = new NotificationPanel(notificationMsg);
        np.setVisible(true);
        System.out.println(notificationMsg);
    }

}

public class Context {

    JETNews j = new JETNews();

    NewSubscriber ns1 = new NewSubscriber();

    void callSubscribe() {
        ns1.subscribe(j);
    }

    void callUnsubscribe() {
        ns1.unsubscribe();
    }

}
