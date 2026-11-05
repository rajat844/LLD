# File System

## Functional Requirements

1. System model a hierarchial file system starting from root directory, supporting both absolute and relative path navigation.
2. User can create new directories and recursively delete existing directories. When a directory is deleted, all the subdirectories and files contained within it must be deleted.
3. The system must calculate sized uniformly, querying the size of a file returns its raw byte size, while querying a directory recursively aggregates and returns the total size of all nested files and subdirectories.

## Non Functional Requirements

1. All the file and directory related operations will be thread safe.
