package com.vinsguru.sec02.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final Path PATH = Path.of("src/main/resources/sec02");

    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(() -> Files.readString(PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> this.writeFile(fileName, content));
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> this.deleteFile(fileName));
    }

    private void writeFile(String fileName, String content){
        try {
            Files.writeString(PATH.resolve(fileName), content);
            log.info("created {}", fileName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String fileName){
        try {
            Files.delete(PATH.resolve(fileName));
            log.info("deleted {}", fileName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
