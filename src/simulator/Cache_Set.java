package simulator;

import java.util.LinkedList;

public class Cache_Set {

    public int index;
    private LinkedList<Cache_Line> lines;

    public Cache_Set(int index){
        this.index = index;
        this.lines = new LinkedList<>();
    }

    public int find_Line(int tag) {
        int return_val = 0;

        for (int i = 0; i < lines.size(); i++) {
            int temp_tag = lines.get(i).tag;
            if (temp_tag == tag) {
                //System.out.println("The tags are equal");
                //Its in the cache yay
                return_val = 1;
            }
        }
        return return_val;
    }

    public void add_to_Cache(int tag, int set_size){

        if(lines.size() == set_size){
            //We need to replace a line because our set is full
            int tag_temp = lines.get(0).tag;
            int replace = 0;
            for(int i = 0; i < lines.size(); i++){
                //We need to figure out which line was least recently used
                //The line with highest last access is the least recently used
                if(lines.get(i).tag > tag_temp){
                    replace = i;
                }
            }
            Cache_Line new_line = new Cache_Line(tag);
            lines.set(replace,new_line);
        }
        else {
            //The set is not full so we can just add
            Cache_Line new_line = new Cache_Line(tag);
            lines.add(new_line);
        }
    }

    public void increase_Last_Access(){
        for(int i = 0; i < lines.size(); i++){
            lines.get(i).increase_LA();
        }
    }

    public void set_LA_to_Zero(int tag){
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).tag == tag){
                lines.get(i).last_access = 0;
            }
        }
    }

}
