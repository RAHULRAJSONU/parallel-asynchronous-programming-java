package io.github.rahulrajsonu.parallelstream;

import io.github.rahulrajsonu.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.rahulrajsonu.util.CommonUtil.delay;
import static io.github.rahulrajsonu.util.CommonUtil.stopWatch;
import static io.github.rahulrajsonu.util.LoggerUtil.log;

public class ParallelStreamExample {

    public List<String> stringTransform(List<String> nameList){
        return nameList
        .stream()
        .map(this::addNameLengthTransform)
        .collect(Collectors.toList());
    }

    public List<String> stringParallelStreamTransform(List<String> nameList){
        return nameList
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> nameList = DataSet.namesList();
        stopWatch.start();
        ParallelStreamExample pse = new ParallelStreamExample();
        List<String> res = pse.stringParallelStreamTransform(nameList);
        stopWatch.stop();
        res.forEach(System.out::println);
        log("Total Time Taken : "+ stopWatch.getTime());
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
