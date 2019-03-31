package com.hangzhou.sz.baser.utils;


import com.hangzhou.sz.baser.base.BaseRecyclerBean;
import com.hangzhou.sz.baser.bean.TypeBeanOne;

import java.util.Comparator;

public class PinyinComparator implements Comparator<BaseRecyclerBean> {

	@Override
	public int compare(BaseRecyclerBean o1, BaseRecyclerBean o2) {
		if (o1.getLetters().equals("@")
				|| o2.getLetters().equals("#")) {
			return 1;
		} else if (o1.getLetters().equals("#")
				|| o2.getLetters().equals("@")) {
			return -1;
		} else {
			return o1.getLetters().compareTo(o2.getLetters());
		}
	}

}
