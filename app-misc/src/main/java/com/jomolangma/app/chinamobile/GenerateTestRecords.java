package com.jomolangma.app.chinamobile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class GenerateTestRecords {

	@Test
	public void generateSDKTestRecords() {
		String fileName = "a_SDK_17801"
				+ GenerateTestRecordsUtil.formatDate(new Date(),
						GenerateTestRecordsUtil.DATE_FORMAT_YYYYMMDDHHMM)
				+ ".dat";

		StringBuilder recordFormatBuilder = new StringBuilder();
		String recordFormat = recordFormatBuilder.append("{0}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{1}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{2}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{3}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{4}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{5}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{6}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{7}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{8}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{9}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{10}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{11}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{12}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{13}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{14}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{15}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{16}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{17}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{18}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{19}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{20}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{21}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{22}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{23}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{24}")
				.append(GenerateTestRecordsUtil.fieldSep).append("{25}")
				.append(GenerateTestRecordsUtil.recordSep).toString();

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < 20000; i++) {
			Date date = new Date();
			String time = GenerateTestRecordsUtil.formatDate(date,
					GenerateTestRecordsUtil.DATE_FORMAT_YYYYMMDDHHMM);
			String ip = GenerateTestRecordsUtil.ips[(int) (Math.random() * GenerateTestRecordsUtil.ips.length)];
			int pid = GenerateTestRecordsUtil.pids[(int) (Math.random() * GenerateTestRecordsUtil.pids.length)];
			String cid = GenerateTestRecordsUtil.getCidKey();

			String sid = "Y";
			String sdk_ver = GenerateTestRecordsUtil.sdk_vers[(int) (Math
					.random() * GenerateTestRecordsUtil.sdk_vers.length)];
			String app_key = GenerateTestRecordsUtil.app_keys[(int) (Math
					.random() * GenerateTestRecordsUtil.app_keys.length)];
			String pkg_nm = UUID.randomUUID().toString();
			String pkg_ver_nm = "1.0";
			String pkg_ver_code = "1000";
			String hd_access_time = GenerateTestRecordsUtil.formatDate(date,
					GenerateTestRecordsUtil.DATE_FORMAT_YYYYMMDDHHMM);
			String page_event_nm = "click";
			String page_nm = "about";
			String access_time = GenerateTestRecordsUtil.formatDate(date,
					GenerateTestRecordsUtil.DATE_FORMAT_YYYYMMDDHHMM);
			String page_stay_time = "10";
			String create_time = GenerateTestRecordsUtil.formatDate(date,
					GenerateTestRecordsUtil.DATE_FORMAT_YYYYMMDDHHMM);
			String insert_time = GenerateTestRecordsUtil.formatDate(date,
					GenerateTestRecordsUtil.DATE_FORMAT_YYYYMMDDHHMM);
			String device_id = UUID.randomUUID().toString();
			String channel_id = GenerateTestRecordsUtil.channel_ids[(int) (Math
					.random() * GenerateTestRecordsUtil.channel_ids.length)];
			String flow_consum_psnd = "";
			String flow_consum_prev = "";
			String from_page_name = "";
			String area_id = "01";
			String city_id = "0769";
			String area_name = "广东";
			String city_name = "东莞";

			String record = MessageFormat.format(recordFormat, time, ip, pid,
					cid, sid, sdk_ver, app_key, pkg_nm, pkg_ver_nm,
					pkg_ver_code, hd_access_time, page_event_nm, page_nm,
					access_time, page_stay_time, create_time, insert_time,
					device_id, channel_id, flow_consum_psnd, flow_consum_prev,
					from_page_name, area_id, city_id, area_name, city_name);
			builder.append(record);
		}

		try {
			File path = new File("/Users/zhanglijun/Documents");
			File dir = new File(path, fileName);
			if (!dir.exists())
				dir.createNewFile();

			FileWriter fw = new FileWriter(dir);
			BufferedWriter buffw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(buffw);
			pw.write(builder.toString());

			pw.close();
			buffw.close();
			fw.close();

		} catch (Exception e) {
			System.out.print("创建失败");
		}

		// String recordStr = builder.toString();
		// String records[] = recordStr.split(String.valueOf(recordSep));
		// String record1 = records[0];
		//
		// String fieldArray[] = record1.split(String.valueOf(fieldSep));
		// for (String field:fieldArray){
		// System.out.println(field);
		// }

	}
}
