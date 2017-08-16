package com.demo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class FileMonitorTest {

    private WatchService watcher;
    private Path path;

    public FileMonitorTest(Path path) throws IOException {
        this.path = path;
        watcher = FileSystems.getDefault().newWatchService();
        this.path.register(watcher, OVERFLOW, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
    }

    public void handleEvents() throws InterruptedException {
        // start to process the data files
        while (true) {
            // start to handle the file change event
            final WatchKey key = watcher.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                // get event type
                final WatchEvent.Kind<?> kind = event.kind();

                // get file name
                @SuppressWarnings("unchecked") final WatchEvent<Path> pathWatchEvent = (WatchEvent<Path>) event;
                final Path fileName = pathWatchEvent.context();
                System.out.println("path ------------" + fileName);

                if (kind == ENTRY_CREATE) {
                    System.out.println("----create-------");
                    // 说明点1
                    // create a new thread to monitor the new file
                    new Thread(new Runnable() {
                        public void run() {
                            File file = new File(path.toFile().getAbsolutePath() + "/" + fileName);
                            boolean exist;
                            long size = 0;
                            long lastModified = 0;
                            int sameCount = 0;
                            while (exist = file.exists()) {
                                // if the 'size' and 'lastModified' attribute keep same for 3 times,
                                // then we think the file was transferred successfully
                                if (size == file.length() && lastModified == file.lastModified()) {
                                    if (++sameCount >= 3) {
                                        System.out.println("it is new");
                                        System.out.println(path);

                                        // String fname = fileName.toString().substring(0,fileName.toString().length()-4);
                                        // new CompactFile(new File(path.toString(), fname + ".zip")).zipFiles(new File(fileName.toString()));

                                        break;
                                    }
                                } else {
                                    size = file.length();
                                    lastModified = file.lastModified();
                                }
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    return;
                                }
                            }
                            // if the new file was cancelled or deleted
                            // if (!exist) {
                            //
                            // }
                            // update database ...
                        }
                    }).start();
                } else if (kind == ENTRY_DELETE) {
                    System.out.println("----delete-------");
                } else if (kind == ENTRY_MODIFY) {
                    System.out.println("----modify-------");
                } else if (kind == OVERFLOW) {
                    System.out.println("----overflow-------");
                }
            }

            // IMPORTANT: the key must be reset after processed
            if (!key.reset()) {
                return;
            }
        }
    }

}
