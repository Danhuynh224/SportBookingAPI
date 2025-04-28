package com.example.apiproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final String VNP_RETURN_URL = "myapp://payment/return";  // Custom scheme URL
    private static final String VNP_TMN_CODE = "CYTH4PM8";
    private static final String VNP_HASH_SECRET = "2N1TQNUA2T8I9FYV8H6SL9CBYPINSJ55";
    private static final String VNP_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";


    @GetMapping("/create")
    public ResponseEntity<String> createPayment(@RequestParam("price") Long price) {
        try {
            String vnp_TxnRef = String.valueOf(System.currentTimeMillis());
            String vnp_OrderInfo = "Thanh toán đặt sân";
            String vnp_OrderType = "other";
            String vnp_Amount = String.valueOf(price * 100); // số tiền * 100 (ví dụ 100k VND)
            String vnp_Locale = "vn";
            String vnp_BankCode = "NCB"; // ngân hàng giả lập
            String vnp_IpAddr = "127.0.0.1";

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", "2.1.0");
            vnp_Params.put("vnp_Command", "pay");
            vnp_Params.put("vnp_TmnCode", VNP_TMN_CODE);
            vnp_Params.put("vnp_Amount", vnp_Amount);
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
            vnp_Params.put("vnp_OrderType", vnp_OrderType);
            vnp_Params.put("vnp_Locale", vnp_Locale);
            vnp_Params.put("vnp_ReturnUrl", VNP_RETURN_URL);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Date dt = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(dt);
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);

            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            for (String fieldName : fieldNames) {
                String value = vnp_Params.get(fieldName);
                if (value != null && !value.isEmpty()) {
                    hashData.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII.toString()));
                    query.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII.toString()));
                    if (!fieldName.equals(fieldNames.get(fieldNames.size() - 1))) {
                        hashData.append('&');
                        query.append('&');
                    }
                }
            }

            String secureHash = hmacSHA512(VNP_HASH_SECRET, hashData.toString());
            query.append("&vnp_SecureHash=").append(secureHash);
            String paymentUrl = VNP_URL + "?" + query.toString();

            return ResponseEntity.ok(paymentUrl);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi tạo thanh toán VNPay");
        }
    }

    private String hmacSHA512(String key, String data) throws Exception {
        Mac hmac512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmac512.init(secretKeySpec);
        byte[] bytes = hmac512.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }

    @RequestMapping("/return")
    public ResponseEntity<String> paymentReturn(HttpServletRequest request) {
        try {
            // Lấy các tham số từ URL trả về của VNPay
            Map<String, String[]> params = request.getParameterMap();
            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            String vnp_TxnRef = request.getParameter("vnp_TxnRef");
            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");

            // Tạo một string chứa dữ liệu yêu cầu để kiểm tra lại với HMAC SHA512
            StringBuilder hashData = new StringBuilder();
            params.forEach((key, values) -> {
                if (!key.equals("vnp_SecureHash")) {
                    for (String value : values) {
                        hashData.append(key).append('=').append(value).append('&');
                    }
                }
            });

            // Loại bỏ dấu & cuối cùng
            if (hashData.length() > 0) {
                hashData.deleteCharAt(hashData.length() - 1);
            }

            // Tính toán hash mới từ dữ liệu đã nhận và secret key của bạn
            String secureHash = hmacSHA512(VNP_HASH_SECRET, hashData.toString());

            // Kiểm tra nếu secureHash nhận được khớp với hash từ VNPay
            if (secureHash.equals(vnp_SecureHash)) {
                if ("00".equals(vnp_ResponseCode)) {
                    // Thanh toán thành công
                    return ResponseEntity.ok("Thanh toán thành công! Mã giao dịch: " + vnp_TxnRef);
                } else {
                    // Thanh toán không thành công
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thanh toán thất bại! Mã giao dịch: " + vnp_TxnRef);
                }
            } else {
                // Kiểm tra hash không khớp
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xác thực, bảo mật dữ liệu không khớp.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xử lý kết quả thanh toán.");
        }
    }


}
