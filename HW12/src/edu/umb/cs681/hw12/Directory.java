package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {

    private LinkedList<FSElement> child;
    LinkedList<Directory> subDirectory = new LinkedList<Directory>();
    LinkedList<File> files = new LinkedList<File>();
    
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if(parent != null)
            parent.appendChild(this);
    }

    public int countChild() {
        lock.lock();
        try {
            return this.child.size();
        } finally {
            lock.unlock();
        }
    }
    public LinkedList<FSElement> getChild() {
        lock.lock();
        try {
            return this.child;
        } finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        if(this.child == null) {
            this.child = new LinkedList<FSElement>();
        }
        this.child.add(child);
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            for (FSElement element : getChild()) {
                if (element instanceof File)
                    files.add((File) element);
            }
            return files;
        } finally {
            lock.unlock();
        }

    }
    public LinkedList<Directory> getSubDirectory() {
        lock.lock();
        try {
            for (FSElement element : getChild()) {
                if (element.isDirectory())
                	subDirectory.add((Directory) element);
            }
            return subDirectory;
        } finally {
            lock.unlock();
        }
    }

    public int getTotalSize() {
        lock.lock();
        try {
            int totalSize = 0;
            for(FSElement element : getChild()) {
                if(element instanceof Directory)
                	totalSize += ((Directory) element).getTotalSize();
                else
                	totalSize += element.getSize();
            }
            return totalSize;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public boolean isDirectory() {
        lock.lock();
        try {
            return true;
        } finally {
            lock.unlock();
        }

    }

}