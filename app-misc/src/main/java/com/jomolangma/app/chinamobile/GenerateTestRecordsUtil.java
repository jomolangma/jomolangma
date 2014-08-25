package com.jomolangma.app.chinamobile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateTestRecordsUtil {

	public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";

	public static final char fieldSep = 0X80;
	public static final String recordSep = "\r";

	public static final String[] ips = { "192.168.10.210", "192.168.10.211",
			"192.168.10.212", "192.168.10.213", "192.168.10.214" };

	public static final String[] cids = { "a", "b", "c", "d", "e", "f", "g",
			"h" };

	public static final String[] sdk_vers = { "0.5.0", "0.5.00", "0.5.1",
			"0.5.2", "0.5.3", "0.5.4", "0.5.5", "0.5.6", "0.5.7", "0.5.8",
			"0.5.9", "0.50.686", "0.51.686", "0.53.686", "0.54.686",
			"0.55.686", "0.56.686", "0.56.687", "0.57.725", "0.58.730",
			"0.59.734", "0.6", "0.6.0", "0.6.1", "0.6.5", "0.6.8.4", "0.6.9.4",
			"0.6.9.6", "0.60.0611.09", "0.60.0618.09", "0.60.764" };

	public static final String[] app_keys = { "300000254276", "300001661918",
			"300000428315", "300001595270", "300002133228", "300000262105",
			"300000262292", "300000199902", "300001459931", "300001490298",
			"300001427665", "300002242922", "300001852617", "300001676766",
			"300002334955", "300002334956", "300002739538", "300002515442",
			"300002208061", "300001488567", "300001900293", "300001626068",
			"300001112243", "300001601124", "300000815109", "300001900332",
			"300002839586", "300001106532", "300001240861", "300001875186",
			"300001333528", "300000491305", "300000491339", "300001425921",
			"300002174276", "300002174277", "300002179081", "300000032307",
			"300000532461", "300002340417", "300000125048", "300000125049",
			"300000876262", "300000876263", "300000905379", "300000928850",
			"300001292673", "300001292677", "300001107372", "300001292679",
			"300001353158", "300001353162", "300001353195", "300001952041",
			"300000040512", "300000040514", "300000042785", "300000043755",
			"300000043821", "300001757015", "300001757087", "300000043824",
			"300001733530", "300001733534", "300002496309", "300002011919",
			"300000042049", "300000229708", "300002262627", "300001433187",
			"300001433208", "300000599658", "300001561048", "300002658324",
			"300002658356", "300002658357", "300002300804", "300000095575" };

	public static final String[] channel_ids = { "2000000031", "2000000075",
			"2000000077", "2000000083", "2000000093", "2000000165",
			"2000000174", "2000000183", "2000000192", "2000000201",
			"2000000219", "2000000228", "2000000237", "2000000363",
			"2000000375", "2000000453", "2000000467", "2000000481",
			"2000000495", "2000000509", "2000000721", "2000000740",
			"2000000759", "2000000778", "2000000797", "2000000816",
			"2000000835", "2000000854", "2000000873", "2000000892",
			"2000000911", "2000000930", "2000002766", "2000002786",
			"2000002806", "2000002826", "2000002846", "2000002866",
			"2000002886", "2000002906", "2000002926", "2000002946",
			"2000002966", "2000002986", "2000003006", "2000003026",
			"2000003046", "2000003066", "2000003086", "2000003106",
			"2000003126", "2000003146", "2000003166", "2000003186" };

	public static final int[] pids = { 1, 2, 3 };
	
	public static final String CID_APP_KEY = "china:mobile:cid:app_key";

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static String getCidKey() {
		StringBuilder keyBuilder = new StringBuilder();
		int cidsLen = GenerateTestRecordsUtil.cids.length;

		return keyBuilder
				.append(GenerateTestRecordsUtil.cids[(int) (Math.random() * cidsLen)])
				.append(GenerateTestRecordsUtil.cids[(int) (Math.random() * cidsLen)])
				.append(GenerateTestRecordsUtil.cids[(int) (Math.random() * cidsLen)])
				.append(GenerateTestRecordsUtil.cids[(int) (Math.random() * cidsLen)])
				.append(GenerateTestRecordsUtil.cids[(int) (Math.random() * cidsLen)])
				.append(GenerateTestRecordsUtil.cids[(int) (Math.random() * cidsLen)])
				.append(GenerateTestRecordsUtil.cids[(int) (Math.random() * cidsLen)])
				.append(GenerateTestRecordsUtil.cids[(int) (Math.random() * cidsLen)])
				.toString();
	}

	public static void main(String[] args) {
		System.out.println(String.valueOf((int) (Math.random() * 10)));
	}
}
