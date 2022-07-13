class Node {
    // ganske rett fram hvordan en node lages og tilhørende metoder
    private int minnestorrelse;
    private int prossesorantall;

    public Node(int minnestorrelse, int prossesorantall) {
        this.minnestorrelse = minnestorrelse;
        this.prossesorantall = prossesorantall;
    }

    public int antProsessorer() {
        return prossesorantall;
    }

    public boolean nokMinne(int paakrevdMinne) {
        return paakrevdMinne <= minnestorrelse;
    }

}