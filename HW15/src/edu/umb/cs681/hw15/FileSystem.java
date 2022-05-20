package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystem implements Runnable {

	private FileSystem() {};
	private LinkedList<Directory> directoryroot;
    private static FileSystem instance = null;

    public static FileSystem getFileSystem() {
        if(instance==null)
            instance = new FileSystem ();
        return instance;
    }

    public LinkedList<Directory> getRootDirs() {
        return this.directoryroot;
    }

    void appendRootDir(Directory RootDirectory) {
        directoryroot = new LinkedList<Directory>();
        directoryroot.add(RootDirectory);
    }
    
    @Override
    public void run() {
        System.out.println("\nThread number "+Thread.currentThread().getId()+" is running");
        for(Directory directory: getRootDirs()) {
        	System.out.println("The Size of " + directory.getName() + " directory is: " + directory.getTotalSize());
    		for (Directory child : directory.getSubDirectory()) {
    			System.out.println("The Directory "+child.getName()+" is added to directory "+child.getParent().getName());
    			for (File element : child.getFiles()) {
                    System.out.println("The File "+element.getName()+" is added to the directory "+child.getName()+".");
                }
    			
    			for(Directory dir: child.getSubDirectory()) {
    				System.out.println("The Directory "+dir.getName()+" is added to directory "+dir.getParent().getName());
    				for (File element : dir.getFiles()) {
                        System.out.println("The File "+element.getName()+" is added to the directory "+dir.getName()+".");
                    }
    			}
    		}
        }
        
    }

    public static void main(String[] args) {

        LocalDateTime localTime = LocalDateTime.now();
        FileSystem fs = new FileSystem();
        Directory root = new Directory(null, "root", 0, localTime);
        fs.appendRootDir(root);
        Directory applications = new Directory(root, "applications", 0, localTime);
        new File(applications, "a", 40, localTime);
        new File(applications, "b", 100, localTime);

        Thread t1 = new Thread(fs);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        
        Directory home = new Directory(root, "home", 0, localTime);
        new File(home, "c", 200, localTime);
        new File(home, "d", 400, localTime);

        Directory code = new Directory(home, "code", 0, localTime);
        new File(code, "e", 300, localTime);
        new File(code, "f", 600, localTime);
     
        Thread t2 = new Thread(fs);
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
    }

}