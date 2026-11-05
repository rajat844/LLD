package code;

public class FileSystem {
    private Directory root;

    public FileSystem(Directory root) {
        this.root = new Directory("/");
    }

    private Directory traverseToParent(String path) {
        String[] parts = path.split("/");
        Directory curr = root;

        for (int i = 1; i < parts.length - 1; i++) {
            FileSystemComponent next = curr.getChild(parts[i]);

            if (next == null) {
                throw new IllegalArgumentException("Path not found :  " + parts[i]);
            }

            if (next instanceof Directory) {
                curr = (Directory) next;
            } else {
                throw new IllegalArgumentException(parts[i] + " is a file, not a directory!");
            }
        }

        return curr;
    }

    public void mkdir(String path) {
        Directory parent = traverseToParent(path);
        String[] parts = path.split("/");
        String newDirName = parts[parts.length - 1];

        parent.add(new Directory(newDirName));
    }

    public void touch(String path, int size) {
        Directory parent = traverseToParent(path);
        String[] parts = path.split("/");
        String newFileName = parts[parts.length - 1];

        parent.add(new File(newFileName, size));
    }

    public Directory ls(String path) {
        return traverseToParent(path);
    }
}
