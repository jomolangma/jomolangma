package com.jomolangma.app.mybatis.dialect;

public class SQLServerDialect extends Dialect {

	public boolean supportsLimit() {
		return true;
	}

	static int getAfterSelectPoint(String sql) {
		int selectIndex = sql.toLowerCase().indexOf("select");
		int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return (selectIndex + ((selectDistinctIndex == selectIndex) ? 15 : 6));
	}

	public String getLimitString(String sql, int offset, int limit ) {
		String lowerCaseSQL = sql.toLowerCase().replace("\n", " ").replace("\t", " ");
		int index = lowerCaseSQL.indexOf(" order ");
		if(index<0)
			return sql;
		String sqlWithoutOrderByClause =  sql.substring(0, index);
		String orderByClause = sql.substring(index);
		StringBuilder rowNumberColumnClause = new StringBuilder(" ROW_NUMBER() OVER ( ")
		.append(orderByClause).append(" ) AS row_number, ");
		StringBuilder innerSQL =  new StringBuilder(sqlWithoutOrderByClause)
				.insert(getAfterSelectPoint(sqlWithoutOrderByClause), rowNumberColumnClause);
		StringBuilder finalSQL = new StringBuilder(" select * from ( ").append(innerSQL)
				.append(" ) _table1  where row_number > ").append(offset).append(" and row_number <=").append( offset+limit );
		return finalSQL.toString();
	}
	
	@Override
	public String getConcatString(String[] ss, String delimiter) {
		String delimiter1 = getDelimiter(delimiter);
		String concatDelimiter = " + ";
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
