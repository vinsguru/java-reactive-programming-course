package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.helper.NameGenerator;

public class Lec02StartWithUseCase {

    public static void main(String[] args) {


        var nameGenerator = new NameGenerator();

        nameGenerator.generateNames()
                     .take(2)
                     .subscribe(Util.subscriber("sam"));


        nameGenerator.generateNames()
                     .take(2)
                     .subscribe(Util.subscriber("mike"));

        nameGenerator.generateNames()
                     .take(3)
                     .subscribe(Util.subscriber("jake"));


    }

}
