package com.jomolangma.app.spring.instrument;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class Register {
	public static void premain(String agentArgs, Instrumentation inst) {
	   ClassFileTransformer t = new Transformer();
       inst.addTransformer(t);
	}
}
