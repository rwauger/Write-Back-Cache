package simulator;

public class Cache_Entry {

    private int address,tag,index,offset,memory_reference;
    private String status, type;

    public Cache_Entry(String type,int address,int tag, int index, int offset, String status, int memory_reference){
        this.type = type;
        this.address = address;
        this.tag = tag;
        this.index = index;
        this.offset = offset;
        this.status = status;
        this.memory_reference = memory_reference;
    }

    public String toString() {
        String string_print = "";
        String address_S = Integer.toHexString(address);
        string_print = string_print + type + "\t   "+ address_S +"\t    "+ tag +"\t   "+ index +"\t   "+ offset + "\t   "+status +"\t      "+ memory_reference + "\n";
        return string_print;
    }

}
