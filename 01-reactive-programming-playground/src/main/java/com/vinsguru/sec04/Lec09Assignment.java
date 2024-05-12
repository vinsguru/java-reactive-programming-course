package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import com.vinsguru.sec04.assignment.FileReaderServiceImpl;

import java.nio.file.Path;

public class Lec09Assignment {

    public static void main(String[] args) {

        var path = Path.of("src/main/resources/sec04/file.txt");
        var fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(path)
                .takeUntil(s -> s.equalsIgnoreCase("line17"))
                .subscribe(Util.subscriber());


    }

}
