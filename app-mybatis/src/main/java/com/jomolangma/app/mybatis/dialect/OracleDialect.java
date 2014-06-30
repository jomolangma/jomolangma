package com.jomolangma.app.mybatis.dialect;

/**
 * Oracle 版本分页
 * 
 */
public class OracleDialect extends Dialect {

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {
		String forUpdate = " for update";
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(forUpdate)) {
			sql = sql.substring(0, sql.length() - forUpdate.length());
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			pagingSelect
					.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}
		else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			// int end = offset+limit;
			String endString = offsetPlaceholder + "+" + limitPlaceholder;
			pagingSelect.append(" ) row_ ) where rownum_ <= " + endString
					+ " and rownum_ > " + offsetPlaceholder);
		}
		else {
			pagingSelect.append(" ) where rownum <= " + limitPlaceholder);
		}

		if (isForUpdate) {
			pagingSelect.append(forUpdate);
		}

		return pagingSelect.toString();
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
