package com.spark.lms.common;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String ROLE_ADMIN = "Admin";
	public static final String ROLE_LIBRARIAN = "Librarian";

	public static final String ROLE_USER = "User";

	public static final Long AC_USER = 1L;
	public static final Long DAC_USER = 0L;

	public static final String MEMBER_PARENT = "Parent";
	public static final String MEMBER_STUDENT = "Student";
	public static final String MEMBER_OTHER = "Other";



	public static final List<String> USERS_TYPES = new ArrayList<String>() {{
		add(ROLE_ADMIN);
		add(ROLE_USER);
	}};

	public static final List<Long> USERS_ACTIV = new ArrayList<Long>() {{
		add(AC_USER);
		add(DAC_USER);
	}};
	public static final List<String> MEMBER_TYPES = new ArrayList<String>() {{
	    add(MEMBER_PARENT);
	    add(MEMBER_STUDENT);
	    add(MEMBER_OTHER);
	}};
	
	public static final Integer BOOK_STATUS_AVAILABLE = 1;
	public static final Integer BOOK_STATUS_ISSUED = 2;
	
	public static final Integer BOOK_NOT_RETURNED = 0;
	public static final Integer BOOK_RETURNED = 1;
}
