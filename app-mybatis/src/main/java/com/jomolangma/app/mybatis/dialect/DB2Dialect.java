package com.jomolangma.app.mybatis.dialect;


public class DB2Dialect extends Dialect {


	public boolean supportsLimit() {
		return true;
	}

	private static boolean hasDistinct(String sql) {
		return (sql.toLowerCase().indexOf("select distinct") >= 0);
	}

	private String getRowNumber(String sql) {
		StringBuffer rownumber = new StringBuffer(50)
				.append("rownumber() over(");

		int orderByIndex = sql.toLowerCase().indexOf("order by");

		if ((orderByIndex > 0) && (!(hasDistinct(sql)))) {
			rownumber.append(sql.substring(orderByIndex));
		}

		rownumber.append(") as rownumber_,");

		return rownumber.toString();
	}

	public String getLimitString(String sql, int offset,
			String offsetPlaceholder, int limit, String limitPlaceholder) {
		int startOfSelect = sql.toLowerCase().indexOf("select");

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100)
				.append(sql.substring(0, startOfSelect))
				.append("select * from ( select ").append(getRowNumber(sql));

		if (hasDistinct(sql)) {
			pagingSelect.append(" row_.* from ( ")
					.append(sql.substring(startOfSelect)).append(" ) as row_");
		} else {
			pagingSelect.append(sql.substring(startOfSelect + 6));
		}

		pagingSelect.append(" ) as temp_ where rownumber_ ");

		if (offset > 0) {
			pagingSelect.append("between ");
			pagingSelect.append(offset + 1);
			pagingSelect.append(" and ");
			pagingSelect.append(limit + 1);
		} else {
			pagingSelect.append("<= ");
			pagingSelect.append(limit);
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
