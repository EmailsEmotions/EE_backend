package pl.tul.emailsemotions.statsservice.clients.model;

public interface Emotionable {
    public Number getAngry();
    public Number getFear();
    public Number getHappy();
    public Number getSad();
    public Number getSurprise();
}
