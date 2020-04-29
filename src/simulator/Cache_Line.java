package simulator;

public class Cache_Line {

    public int tag;
    public int last_access;

    public Cache_Line(int tag){
        this.tag = tag;
    }

    public void increase_LA(){
        last_access++;
    }
}
