package com.jomolangma.app.mybatis.dialect;


public class MySQLDialect extends Dialect {

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {

		if (offset > 0) {
			return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
		}
		else {
			return sql + " limit " + limitPlaceholder;
		}
	}

	@Override
	public String getConcatString(String[] ss, String delimiter) {
		String delimiter1 = getDelimiter(delimiter);
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("CONCAT(");
		for (String s:ss) {
			strBuilder.append("'");
			strBuilder.append(s);
			strBuilder.append("',");
			if (delimiter1.length() > 0) {
				strBuilder.append("'");
				strBuilder.append(delimiter1);
				strBuilder.append("',");
			}
		}
		strBuilder.deleteCharAt(strBuilder.length() - 1);
		strBuilder.append(")");
		return strBuilder.toString();
	}		

}
