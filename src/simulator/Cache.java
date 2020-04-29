package simulator;

import java.util.LinkedList;

public class Cache {

    private int sets,assoc,set_size,line_size;
    public int access,hit,miss;
    private int offset,tag,index;
    private LinkedList<Cache_Set> set_list;
    private LinkedList<Cache_Entry> entry_list;

    public Cache(int sets, int assoc, int set_size, int line_size){

        this.sets = sets;
        this.assoc = assoc;
        this.set_size = set_size;
        this.line_size = line_size;
        this.hit = 0;
        this.miss = 0;
        this.access = 0;
        this.set_list = new LinkedList<>();
        this.entry_list = new LinkedList<>();

        offset = (int) (Math.log(line_size)/(Math.log(2)));
        index = (int) (Math.log(sets)/Math.log(2));
        tag = 32 - assoc - index;
    }

    public void Cache_Entry(String type,int address,int tag, int index, int offset){
        if(type.equals("R")){
            type = "read";
        }
        if(type.equals("W")){
            type="write";
        }
        //System.out.println("Inside Cache Entry");
        access++;
        int do_we_have_Set = 0;

        //Loop through all sets in the Cache
        int num_Sets = set_list.size();
        int access = 0;
        for(int i = 0; i < num_Sets; i++) {
            Cache_Set temp_set = set_list.get(i);
            if (temp_set.index == index) {
                //We do have this set
                //System.out.println("We do have this in the cache");
                access = i;
                do_we_have_Set = 1;
            }
        }
        //Now let's see if the item is in the set
        if(do_we_have_Set == 1) {
            int return_val = set_list.get(access).find_Line(tag);
            set_list.get(access).increase_Last_Access();

            if (return_val == 1) {
                //System.out.println("We have this tag in Cache");
                //We had a cache hit
                //We need to add the cache entry
                int mem = 0;
                String hit_or_miss = "hit";
                //System.out.println("Adding Cache Entry on hit");
                Cache_Entry new_entry = new Cache_Entry(type,address, tag, index, offset, hit_or_miss, mem);
                entry_list.add(new_entry);
                set_list.get(access).set_LA_to_Zero(tag);
                hit++;
            }
            if (return_val == 0) {
                //System.out.println("We do not have this tag in Cache");
                //We had a cache miss
                //Let's add it to the set

                int mem = 1;
                String hit_or_miss = "miss";
                //System.out.println("Adding Cache Entry on miss");
                Cache_Entry new_entry = new Cache_Entry(type,address, tag, index, offset, hit_or_miss, mem);
                entry_list.add(new_entry);
                set_list.get(access).add_to_Cache(tag,set_size);
                miss++;
            }
        }
            if(do_we_have_Set == 0){
                miss++;
                //System.out.println("We do not have this set so we are adding it");
                //We do not have this set, so let's add it
                Cache_Set new_set = new Cache_Set(index);
                set_list.add(new_set);
                //Now we need to add the tag to the set
                int most_recent_set = (set_list.size()-1);
                set_list.get(most_recent_set).add_to_Cache(tag,set_size);
                //And add to entry
                int mem = 1;
                String hit_or_miss = "miss";
                //System.out.println("Adding Cache Entry on miss");
                Cache_Entry new_entry = new Cache_Entry(type,address, tag, index, offset, hit_or_miss, mem);
                entry_list.add(new_entry);
            }
        }

    public int get_Tag(int address){
        address >>>= (offset + index);
        int remainder = Integer.SIZE - tag;
        return (address << remainder) >>> (remainder);
    }

    public int get_Index(int address){
        address >>>= offset;
        int remainder = Integer.SIZE - index;
        return (address << remainder) >>> (remainder);
    }

    public int get_Offset(int address){
        int remainder = Integer.SIZE - offset;
        return (address << remainder) >>> (remainder);
    }
    public void full_cache_print()
    {
        System.out.println("Cache Configuration\n");
        System.out.print(sets + " ");
        System.out.println(assoc+ "-way associative entries of line size\n" + line_size + " bytes\n");

        System.out.println("Access   Address   Tag   Index   Offset   Status   Memrefs");
        System.out.println("-----------------------------------------------------------");

        StringBuilder cache_entries = new StringBuilder();
        for (Cache_Entry entry : entry_list)
        {
            cache_entries.append(entry.toString());
        }
        System.out.println(cache_entries);
        System.out.println("\nSimulation Summary Statistics");
        System.out.println("-----------------------------");
        System.out.println("Total hits:" + hit);
        System.out.println("Total misses:" + miss);
        System.out.println("Total accesses:" + access);
        System.out.println("Hit Ratio:" + (double)hit/access);
        System.out.println("Miss Ratio:" + (double)miss/access);
    }

}
