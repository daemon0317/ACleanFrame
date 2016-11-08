package com.framework.android.utils;

import android.support.annotation.NonNull;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {

	/**
	 * 根据指定的汉字字符串, 返回其对应的拼音
	 * @param string
	 * @return
	 */
	public static String getPinyin(@NonNull String string) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		
		char[] charArray = string.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			
			// 如果是空, 跳过当前循环
			if(Character.isWhitespace(c)){
				continue;
			}
			
			if(c >= -128 && c < 127){
				// 不可能是汉字, 直接拼接
				sb.append(c);
			}else {
				try {
					// 获取某个字符对应的拼音. 可以获取到多音字. ->DAN, SHAN
					String s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					sb.append(s);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return sb.toString();
	}

}
