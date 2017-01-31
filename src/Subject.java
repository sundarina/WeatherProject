public interface Subject {
    public void registerObserver(Observer о);
    public void removeObserver(Observer о);
    public void notifyObservers();
}
