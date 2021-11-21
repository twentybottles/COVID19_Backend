package com.example.demo.learning;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPractice {
	
	public static void main(String[] args) {
		
		// List<Integer>
		List<Integer> intList = Arrays.asList(1, 0, 200, 0, 4000, 1);
		long count = intList.stream().filter(x -> x > 1).count();
		
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> distinct = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList());
		System.out.println(distinct);
		
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println(stats);

		// List<String>
		List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
		count = strList.stream().filter(x -> x.isEmpty()).count();
		
		count = strList.stream().filter(x -> x.length() > 3).count();
		
		count = strList.stream().filter(x -> x.startsWith("abc")).count();
		System.out.println(count);
		
		List<String> filtered = strList.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
		
		filtered.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
		
		List<String> G7 = Arrays.asList("USA","Japan","France","Germany","Italy","U.K.", "Canada");
		String G7Countries = G7.parallelStream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
		
	}

}