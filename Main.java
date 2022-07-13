class Main {
    public static void main(String[] args) throws InterruptedException {
        Controller kontrol = new Controller();
        kontrol.hentModell().startSpill();
        kontrol.klokke();
    }
}
