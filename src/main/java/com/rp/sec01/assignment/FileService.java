package com.rp.sec01.assignment;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileService {

    private static final Path PATH = Paths.get("src/main/resources/assignment/sec01");

    public static Mono<String> read(String fileName){
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    public static Mono<Void> write(String fileName, String content){
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    public static Mono<Void> delete(String fileName){
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    private static String readFile(String fileName){
        try{
            return Files.readString(PATH.resolve(fileName));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static void writeFile(String fileName, String content){
        try{
            Files.writeString(PATH.resolve(fileName), content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static void deleteFile(String fileName){
        try{
            Files.delete(PATH.resolve(fileName));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
