package com.jomolangma.app.mybatis.dialect;

public class UndefinedDialect extends Dialect {

	@Override
	public String getConcatString(String[] ss, String delimiter) {
		throw new UnsupportedOperationException(" string concat not supported");
	}

}
