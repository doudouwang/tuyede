package com.sesame.network;
import org.junit.Test;

import com.abc.task.lang.SerialNumberGenerator;


public class TestGenerator {
	@Test
	public void test() {
		System.out.println(SerialNumberGenerator.generate());
	}
}
