package com.DirectoryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {
    private Map<String, Directory>  rootDirectories;

    public FileSystem() {
        this.rootDirectories = new HashMap<>();
    }

    public void createDir(String path) {
        String[] parts = path.split("/");
        if (parts.length == 1) {
            //creating a root dir
            rootDirectories.put(parts[0], new Directory(parts[0]));
            return;
        }

        //creating or finding parent dir
        Directory current = findDirectory(parts, parts.length - 1);
        if (current != null) {
            Directory newDir = new Directory(parts[parts.length - 1]);
            current.addChild(newDir);
        }
    }

    public void move(String sourcePath, String destPath) {
        String[] sourceParts = sourcePath.split("/");
        String[] destParts = destPath.split("/");

        Directory sourceParent = findDirectory(sourceParts, sourceParts.length - 1);
        Directory destDir = findDirectory(destParts, destParts.length);

        if (sourceParent != null && destDir != null) {
            DirNode node = sourceParent.removeChild(sourceParts[sourceParts.length - 1]);
            if (node != null) {
                destDir.addChild(node);
            }
        }
    }

    public void delete(String path) throws Exception {
        String[] parts = path.split("/");
        Directory parent = findDirectory(parts, parts.length - 1);

        if (parent == null) {
            throw new Exception("fruit does not exist");
        }

        parent.removeChild(parts[parts.length - 1]);
    }

    public void list(StringBuilder sb) {
        List<Directory> sorted = new ArrayList<>(rootDirectories.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        for (Directory dir : sorted) {
            dir.print(sb, "");
        }
    }

    private Directory findDirectory(String[] parts, int upToIdx) {
        if (upToIdx == 0) {
            return rootDirectories.get(parts[0]);
        }

        Directory current = rootDirectories.get(parts[0]);
        for (int i = 1; i < upToIdx && current != null; i++) {
            DirNode next = current.getChild(parts[i]);
            if (!(next instanceof Directory)) {
                return null;
            }
            current = (Directory) next;
        }
        return current;
     }
}
