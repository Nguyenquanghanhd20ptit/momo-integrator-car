package com.example.momointerator.commons.data.constant.momo;

import java.util.HashMap;
import java.util.Map;

public class MomoConstant {
    //Result
    public static Integer RESULT_SUCCESS_CODE = 0;
    public static Integer  RESULT_CANCELED_CODE = 1006;
    public static Integer  RESULT_WAIT_PAYMENT_CODE = 1000;
    public static Integer  UNPROCESSED_CODE = -1;
    public static Integer  CHECKED_CODE = -2;

    public static String PAYMENT_SUCCESS_MESSAGE = "Giao dịch thành công.";
    public static String PAYMENT_FAIL_MESSAGE = "Giao dịch thất bại.";
    public static String  UNPROCESSED_MESSAGE = "Chưa có kết quả trả về";
    public static String  CHECKED_MESSAGE = "Đã kiểm tra nhưng thất bại";


    public static Map<Integer, String> PAYMENT_STATUS = new HashMap<>() {{
            put(0, "Giao dịch thành công.");
            put(9000, "Giao dịch đã được xác nhận thành công.");
            put(8000, "Giao dịch đang ở trạng thái cần được người dùng xác nhận thanh toán lại.");
            put(7000, "Giao dịch đang được xử lý.");
            put(1000, "Giao dịch đã được khởi tạo, chờ người dùng xác nhận thanh toán.");
            put(11, "Truy cập bị từ chối.");
            put(12, "Phiên bản API không được hỗ trợ cho yêu cầu này.");
            put(13, "Xác thực doanh nghiệp thất bại.");
            put(20, "Yêu cầu sai định dạng.");
            put(22, "Số tiền giao dịch không hợp lệ.");
            put(40, "RequestId bị trùng.");
            put(41, "OrderId bị trùng.");
            put(42, "OrderId không hợp lệ hoặc không được tìm thấy.");
            put(43, "Yêu cầu bị từ chối vì xung đột trong quá trình xử lý giao dịch.");
            put(1001, "Giao dịch thanh toán thất bại do tài khoản người dùng không đủ tiền.");
            put(1002, "Giao dịch bị từ chối do nhà phát hành tài khoản thanh toán.");
            put(1003, "Giao dịch bị đã bị hủy.");
            put(1004, "Giao dịch thất bại do số tiền thanh toán vượt quá hạn mức thanh toán của người dùng.");
            put(1005, "Giao dịch thất bại do url hoặc QR code đã hết hạn.");
            put(1006, "Giao dịch thất bại do người dùng đã từ chối xác nhận thanh toán.");
            put(1007, "Giao dịch bị từ chối vì tài khoản người dùng đang ở trạng thái tạm khóa.");
            put(1026, "Giao dịch bị hạn chế theo thể lệ chương trình khuyến mãi.");
            put(1080, "Giao dịch hoàn tiền bị từ chối.Giao dịch thanh toán ban đầu không được tìm thấy.");
            put(1081, "Giao dịch hoàn tiền bị từ chối.Giao dịch thanh toán ban đầu có thể đã được hoàn.");
            put(2001, "Giao dịch thất bại do sai thông tin liên kết.");
            put(2007, "Giao dịch thất bại do liên kết hiện đang bị tạm khóa.");
            put(3001, "Liên kết thất bại do người dùng từ chối xác nhận.");
            put(3002, "Liên kết bị từ chối do không thỏa quy tắc liên kết.");
            put(3003, "Hủy liên kết bị từ chối do đã vượt quá số lần hủy.");
            put(3004, "Liên kết này không thể hủy do có giao dịch đang chờ xử lý.");
            put(4001, "Giao dịch bị hạn chế do người dùng chưa hoàn tất xác thực tài khoản.");
            put(4010, "Quá trình xác minh OTP thất bại.");
            put(4011, "OTP chưa được gửi hoặc hết hạn.");
            put(4100, "Giao dịch thất bại do người dùng không đăng nhập thành công.");
            put(4015, "Quá trình xác minh 3DS thất bại.");
            put(10, "Hệ thống đang được bảo trì.");
            put(99, "Lỗi không xác định.");
        }};
}
