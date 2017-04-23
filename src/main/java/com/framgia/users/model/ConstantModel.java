package com.framgia.users.model;

public interface ConstantModel {

	// Constant DEL_FLG {0: Enable}
	public static String DEL_FLG = "0";

	// Constant DEL_FLG_DEL {1: Disable}
	public static String DEL_FLG_DEL = "1";

	// tablel Borrowed
	// Constant Status {1: Request}
	public static String BOR_STATUS_REQUEST = "1";

	// Constant Status {2: Approve}
	public static String BOR_STATUS_APPROVE = "2";

	// Constant Status {3: Cancel}
	public static String BOR_STATUS_CANCEL = "3";

	// Constant Status {4: Borrowed}
	public static String BOR_STATUS_BORRWED = "4";

	// Constant Status {5: Finish}
	public static String BOR_STATUS_FINISH = "5";

	// table BorrowedDetail
	// Constant Status {0: Wait}
	public static String BOR_DET_STATUS_WAIT = "0";
	
	// Constant Status {1: Accept}
	public static String BOR_DET_STATUS_ACCEPT = "1";

	// Constant Status {2: Reject}
	public static String BOR_DET_STATUS_REJECT = "2";
	

	// table Book
	// Constant Status {1: Good}
	public static String BOO_STATUS_GOOD = "1";

	// Constant Status {2: Bad}
	public static String BOO_STATUS_BAD = "2";

}
