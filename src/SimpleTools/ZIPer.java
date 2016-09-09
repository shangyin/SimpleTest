package SimpleTools;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by 41237 on 2016/4/12.
 */
public class ZIPer {

    private String currentPath;
    private Map<String, ZipEntry> entries;
    private Set<String> entriesName;
    private ZipFile input;

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void displayEntries(String path) {
        Map<String, Long> temp =
        entriesName.stream()
                .filter(name -> name.startsWith(path))
                .map(name -> name.substring(path.length()).split("/")[0])
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
        temp.keySet().stream().forEach(name -> System.out.println(name));
    }

    public void parseCommand(String cmd) {
        String[] tokens = cmd.split(" ");
        if (tokens[0].equals("ls")) {
            displayEntries(currentPath);
        } else if (tokens[0].equals("cd")) {
            if (!tokens[1].endsWith("/")) {
                tokens[1] = tokens[1] + "/";
            }
            if (tokens[1].equals("../") && !currentPath.equals("/")) {
                int index = currentPath.length() - 2;
                while (currentPath.charAt(index) != '/') index--;
                setCurrentPath(currentPath.substring(0, index+1));
            } else if (tokens[1].equals("../") || tokens[1].equals("./")) {
//                don nothing
            } else {
                setCurrentPath(currentPath + tokens[1]);
            }
        } else if (tokens[0].equals("pwd")) {
            System.out.println(getCurrentPath());
        }
    }

    public void openFile(String name) {

    }

    public ZIPer(String FilePath) throws IOException {

        entries = new HashMap<>();
        entriesName = new TreeSet<>();
        currentPath = "/";
        input = new ZipFile(FilePath);

        Enumeration e = input.entries();
        ZipEntry entry;
        while (e.hasMoreElements()) {
            entry = (ZipEntry) e.nextElement();
            entries.put("/" + entry.getName(), entry);
            entriesName.add("/" + entry.getName());
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        ZIPer java = new ZIPer("d:\\corejava9.zip");
        while (true) {
            System.out.print(java.getCurrentPath() + "$>");
            java.parseCommand(in.nextLine());
        }
    }
}
