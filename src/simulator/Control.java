package simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Control {
    public static void main (String[] args) throws FileNotFoundException {
//        File infile = new File(args[0]);
        Scanner scanner = new Scanner(System.in);
        Pattern p = Pattern.compile(".*:");
        scanner.skip(p);
        Integer sets = scanner.nextInt();
        scanner.nextLine();
        scanner.skip(p);
        Integer set_size = scanner.nextInt();
        scanner.nextLine();
        scanner.skip(p);
        Integer line_size = scanner.nextInt();
        scanner.nextLine();

        Cache cache = new Cache(sets, set_size, sets * line_size * set_size,
                line_size);

        while(scanner.hasNextLine()){
            String[] str = scanner.nextLine().split(":");
            int mem_addr = Integer.decode("0x" + str[1]);

            cache.Cache_Entry(str[0],mem_addr, cache.get_Tag(mem_addr),
                    cache.get_Index(mem_addr),
                    cache.get_Offset(mem_addr));
        }
        cache.full_cache_print();
        }
    }

