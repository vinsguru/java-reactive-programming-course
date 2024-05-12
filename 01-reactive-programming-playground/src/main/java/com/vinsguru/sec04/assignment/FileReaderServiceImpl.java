package com.vinsguru.sec04.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                () -> openFile(path),
                this::readFile,
                this::closeFile
        );
    }

    private BufferedReader openFile(Path path) throws IOException {
        log.info("opening file");
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink){
        try {
            var line = reader.readLine();
            log.info("reading line: {}", line);
            if(Objects.isNull(line)){
                sink.complete();
            }else{
                sink.next(line);
            }
        }catch (Exception e){
            sink.error(e);
        }
        return reader;
    }

    private void closeFile(BufferedReader reader){
        try {
            reader.close();
            log.info("closed file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
