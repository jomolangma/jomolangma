package com.jomolangma.app.mybatis.dialect;

public class PostgreSQLDialect extends Dialect {
	
	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {

		if (offset > 0) {
			return sql + " limit " + limitPlaceholder + " offset " + offsetPlaceholder ;
		}
		else {
			return sql + " limit " + limitPlaceholder;
		}
	}
	
	@Override
	public String getConcatString(String[] ss, String delimiter) {
		String delimiter1 = getDelimiter(delimiter);
		String concatDelimiter = " || ";
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0;i < ss.length;i++) {
			strBuilder.append(ss[i]);
			if (i < ss.length - 1) {
				strBuilder.append(concatDelimiter);
				if (delimiter1.length() > 0) {
					strBuilder.append("'");
					strBuilder.append(delimiter1);
					strBuilder.append("'");
					strBuilder.append(concatDelimiter);
				}
			}
		}
		return strBuilder.toString();
	}

}
