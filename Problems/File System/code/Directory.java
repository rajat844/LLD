package code;

import java.util.HashMap;
import java.util.Map;

class Directory implements FileSystemComponent {
    private Map<String, FileSystemComponent> contents;
    private String name;

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
        this.contents = new HashMap<>();
    }

    @Override
    public int getSize() {
        int size = 0;
        for (FileSystemComponent fileSystemComponent : contents.values()) {
            size += fileSystemComponent.getSize();
        }

        return size;
    }

    public void add(FileSystemComponent content) {
        contents.put(content.getName(), content);
    }

    public void remove(FileSystemComponent content) {
        contents.remove(content.getName());
    }

    public FileSystemComponent getChild(String name) {
        return contents.get(name);
    }

}