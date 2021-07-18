package io.github.rahulrajsonu.forkjoin;

import io.github.rahulrajsonu.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static io.github.rahulrajsonu.util.CommonUtil.delay;
import static io.github.rahulrajsonu.util.CommonUtil.stopWatch;
import static io.github.rahulrajsonu.util.LoggerUtil.log;

public class StringTransformExampleUsingForkJoin extends RecursiveTask<List<String>> {

    List<String> inputList;

    public StringTransformExampleUsingForkJoin(List<String> inputList) {
        this.inputList = inputList;
    }

    public static void main(String[] args) {

        stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        log("names : "+ names);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        StringTransformExampleUsingForkJoin steufj = new StringTransformExampleUsingForkJoin(names);
        resultList = forkJoinPool.invoke(steufj);
        stopWatch.stop();
        log("Final Result : "+ resultList);
        log("Total Time Taken : "+ stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

    @Override
    protected List<String> compute() {
        if(inputList.size()<=1){
            List<String> resultList = new ArrayList<>();
            inputList.forEach(name->resultList.add(addNameLengthTransform(name)));
            return resultList;
        }
        int mid = inputList.size()/2;
        ForkJoinTask<List<String>> leftForkJoinTask = new StringTransformExampleUsingForkJoin(inputList.subList(0, mid)).fork();
        inputList = inputList.subList(mid,inputList.size());
        List<String> rightResult = compute();
        final List<String> leftResult = leftForkJoinTask.join();
        leftResult.addAll(rightResult);
        return leftResult;
    }
}
