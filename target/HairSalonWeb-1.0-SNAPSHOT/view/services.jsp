<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .sidebar {
            width:170px;
            position: sticky;
            top: 0px;
            left: 0;
            height: 100vh;
            background-color: #2f2f2f;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
            overflow-y: auto;
        }

        .sidebar h3 {
            text-align: center;
            margin-bottom: 20px;
            color:#dbdbdb;
        }

        .sidebar a {
            display: block;
            padding: 15px 25px;
            margin: 20px 0;
            text-decoration: none;
            color: #ffffff;
            border-radius: 5px;
            font-size: 18px;
        }

        .sidebar a:hover {
            background-color: #ffffff;
            color: #000000;

        }

        .container-content{
            display:flex;
        }

        .container-content .content {
            flex: 1; /* Tự động chiếm phần còn lại của màn hình */
            padding: 30px;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateX(2000px); /* Xuất phát từ bên ngoài */
            }
            to {
                opacity: 1;
                transform: translateX(0); /* Vị trí cuối cùng là bên trong */
            }
        }

        .service-section {
            display: flex;
            align-items: center;
            margin-bottom: 40px;
            width: 100%;
            position: relative;
            opacity: 0; /* Bắt đầu ở trạng thái ẩn */
            animation: slideIn 1s ease forwards; /* Hiệu ứng trượt vào */
        }

        .service-section:nth-child(even) {
            flex-direction: row-reverse;
            animation: slideIn 1.5s ease forwards;
        }

        .service-section img {
            width: 100%;
            height: auto;
            max-width: 400px;
            object-fit: cover;
            margin-right: 20px;
            transition: transform 0.3s ease; /* Hiệu ứng chuyển ảnh */
        }

        .service-section:hover img {
            transform: scale(1.10); /* Phóng to ảnh khi hover */
            box-shadow: 0 8px 16px rgba(0, 0.5, 0.5, 0.5); /* Bóng cho hộp mô tả */
        }

        .service-section:nth-child(even) img {
            margin-right: 0;
            margin-left: 20px;
        }

        .service-description {
            flex: 1;
            padding: 20px;
            background-color: #dbdbdb;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Bóng cho hộp mô tả */
            transition: background-color 0.3s ease; /* Hiệu ứng chuyển màu nền */
        }

        .service-description:hover {
            background-color: #a6a5a5; /* Thay đổi màu nền khi hover */
            font-size: 17px;
        }

        .service-description h3 {
            margin-bottom: 15px;
        }

        .service-description p {
            line-height: 1.6;
        }


        .service-info {
            opacity: 0;
            transform: translateY(1500px);
            transition: all 0.7s ease-in-out;

        }

        .service-info.show {
            opacity: 1;
            transform: translateY(0);
            padding: 20px;
            border: 1px solid #ddd; /* Đường viền xung quanh phần chi tiết */
            border-radius: 8px; /* Bo góc */
            margin-top: 20px; /* Khoảng cách với các phần khác */
            background-color: #f9f9f9; /* Màu nền nhạt */


        }
        .service-info h4 {
            font-size: 24px; /* Kích thước chữ tiêu đề */
            margin-bottom: 15px; /* Khoảng cách dưới tiêu đề */
            color: #333; /* Màu chữ */
        }

        .additional-info {
            margin-top: 40px; /* Khoảng cách trên phần thông tin thêm */
            padding: 15px;
            background-color: #e7f3fe; /* Màu nền cho phần ưu đãi */
            border-left: 5px solid #2196F3; /* Đường viền bên trái màu xanh */
        }

        .additional-info h4 {
            font-size: 20px; /* Kích thước chữ tiêu đề cho phần thông tin thêm */
            color: #1a73e8; /* Màu chữ */
        }

        .additional-info ul {
            list-style-type: disc; /* Kiểu danh sách chấm */
            margin-left: 20px; /* Lề trái cho danh sách */
        }





        .price-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .price-table th,
        .price-table td {
            padding: 20px;
            text-align: center;
            border: 1px solid #ccc;
        }

        .price-table th {
            background-color: #333;
            color: #fff;
            font-size: 18px;
        }

        .price-table td {
            background-color: #f9f9f9;
        }

        .price-table tr:nth-child(even) {
            background-color: #eaeaea;
            font-size: 17px;
        }

        tr:hover td {
            background-color: #f0f0f0; /* Màu nền khi hover */
            transform: scale(1.05); /* Tăng kích thước nhẹ khi hover */
        }

        th:hover {
            background-color: #444444; /* Màu nền cho tiêu đề khi hover */
        }

        .service-image {
            text-align: center; /* Căn giữa hình ảnh */
            margin: 20px 0; /* Khoảng cách trên và dưới hình ảnh */
        }

        .service-image img {
            max-width: 100%; /* Đảm bảo hình ảnh không vượt quá độ rộng của phần chứa */
            height: auto; /* Tự động điều chỉnh chiều cao để giữ tỷ lệ */
            border-radius: 8px; /* Bo góc cho hình ảnh */
        }

        .box-wrapper{
            margin-top:40px;
        }

        .booking-button {
            text-align: center; /* Căn giữa nút */
            margin-top: 20px; /* Khoảng cách trên nút */
        }

        .btn-booking {
            display: inline-block; /* Hiển thị dạng inline-block để có thể tùy chỉnh kích thước */
            padding: 10px 20px; /* Khoảng cách trong nút */
            background-color: #000000; /* Màu nền của nút */
            color: #fff; /* Màu chữ */
            text-decoration: none; /* Bỏ gạch chân */
            border-radius: 5px; /* Bo góc cho nút */
            transition: background-color 0.3s; /* Hiệu ứng chuyển màu nền khi hover */
        }

        .btn-booking:hover {
            background-color: #595858; /* Màu nền khi hover */
            color:white;
            text-decoration: none; /* Bỏ gạch chân */
        }

        /* Responsive Design */
        @media screen and (max-width: 768px) {
            .header-container {
                flex-direction: column;
            }

            .search-container {
                margin-top: 10px;
            }

            .sidebar {
                width: 100%;
                position: relative;
                height: auto;
            }

            .content {
                margin-left: 0;
            }

            .service-section {
                flex-direction: column;
            }


            .service-section img {
                margin-bottom: 20px;
            }
        }


    </style>
</head>
<body>



<div class="container-content">
    <!-- Left Sidebar (Service Menu) -->
    <div class="sidebar">
        <h3>Services</h3>
        <a href="#" onclick="showServiceInfo('haircut')">Hair Cut</a>
        <a href="#" onclick="showServiceInfo('makeup')">Makeup</a>
        <a href="#" onclick="showServiceInfo('massage')">Massage</a>
        <a href="#" onclick="showServiceInfo('headwashing')" style="padding:15px 0px 15px 0px;">Head Washing</a>
        <a href="#" onclick="showServiceInfo('hairdying')"style="padding:15px 15px;">Hair Dying</a>
        <a href="#" onclick="showServiceInfo('haircurling')"style="padding:15px 0px 15px 10px;">Hair Curling</a>
    </div>

    <!-- Main Content Area -->
    <div class="content">
        <!-- Initial Service Sections -->
        <div id="initial-content">
            <div class="service-section">
                <img src="images/haircut.jpg" alt="Hair Cut">
                <div class="service-description">
                    <h3>Hair Cut</h3>
                    <p>Our professional stylists offer a wide range of hairstyles tailored to complement different face shapes and personal styles. We stay updated with the latest trends to provide you with the best experience. Whether you're looking for a simple trim or a bold new look, our team ensures precision and style that will leave you feeling refreshed and confident.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/makeup.jpg" alt="Makeup">
                <div class="service-description">
                    <h3>Makeup</h3>
                    <p>Transform your look with our professional makeup service. Our makeup artists specialize in creating stunning looks for all occasions, from weddings to evening events. Using high-quality products and techniques, they will enhance your features, giving you a flawless, radiant look that stands out in any setting.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/massage.jpg" alt="Massage">
                <div class="service-description">
                    <h3>Massage</h3>
                    <p>Relax and unwind with our gentle and rejuvenating massage services, designed to relieve stress and tension. Our skilled therapists provide a range of massage techniques, from deep tissue to Swedish, ensuring a tailored experience that helps you recharge and restore balance after a long day.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/headwashing.jpg" alt="Head Washing">
                <div class="service-description">
                    <h3>Head Washing</h3>
                    <p>Experience a soothing head wash with premium hair care products and gentle scalp massage techniques. This service not only cleanses your scalp but also promotes blood circulation, leaving you feeling refreshed and invigorated. Perfect for a quick relaxation break that revitalizes both your hair and mind.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/hairdying.jpg" alt="Hair Dying">
                <div class="service-description">
                    <h3>Hair Dying</h3>
                    <p>Revamp your style with our professional hair dyeing service, using safe and high-quality products. We offer a variety of colors to choose from, whether you prefer subtle tones or bold, vibrant shades. Our stylists will work with you to select a color that enhances your features, making sure you walk out feeling confident and looking stunning.</p>
                </div>
            </div>

            <div class="service-section">
                <img src="images/haircurling.jpg" alt="Hair Curling">
                <div class="service-description">
                    <h3>Hair Curling</h3>
                    <p>Create beautiful, voluminous curls with our expert hair curling service. Using modern curling technology, we ensure that your curls are long-lasting and look natural. Whether you want soft waves or defined curls, our team will bring out the best in your hair, adding an elegant touch to your overall look.</p>
                </div>
            </div>
        </div>



        <!-- Service Details -->
        <div id="haircut-info" class="service-info">
            <h4>Chi tiết dịch vụ Cắt tóc</h4>
            <p>Các nhà tạo mẫu chuyên nghiệp của chúng tôi cung cấp nhiều kiểu tóc khác nhau, từ đơn giản đến phức tạp. Chúng tôi cam kết mang đến trải nghiệm tốt nhất cho khách hàng.</p>

            <!-- Hình ảnh minh họa -->
            <div class="service-image">
                <img src="../images/haircut-image.jpg" alt="Hình ảnh dịch vụ cắt tóc" />
            </div>

            <div class="additional-info">
                <h4>Các bước thực hiện dịch vụ</h4>
                <ul>
                    <li>Bước 1: Tư vấn kiểu tóc phù hợp với khuôn mặt.</li>
                    <li>Bước 2: Cắt và tạo kiểu theo yêu cầu của khách hàng.</li>
                    <li>Bước 3: Hoàn thiện và chăm sóc tóc sau khi cắt.</li>
                </ul>
            </div>

            <div class="box-wrapper">
                <h4>Hair Cut Service Price List</h4>
                <table class="price-table">
                    <tr>
                        <th>Service</th>
                        <th>Price (VND)</th>
                    </tr>
                    <tr>
                        <td>Men's Haircut</td>
                        <td>100,000</td>
                    </tr>
                    <tr>
                        <td>Women's Haircut</td>
                        <td>300,000</td>
                    </tr>
                    <tr>
                        <td>Children's Haircut</td>
                        <td>80,000</td>
                    </tr>
                </table>
            </div>

            <!-- Nút đặt lịch -->
            <div class="booking-button">
                <a href="view/bookNow.jsp" class="btn-booking">Bấm vào đây để đặt lịch</a>
            </div>
        </div>


        <!-- Makeup Section -->
        <div id="makeup-info" class="service-info">
            <h4>Chi tiết dịch vụ Trang điểm</h4>
            <p>Đội ngũ trang điểm chuyên nghiệp của chúng tôi sẽ biến bạn thành phiên bản xinh đẹp nhất của chính mình. Chúng tôi cung cấp nhiều phong cách trang điểm phù hợp với từng dịp, từ trang điểm hàng ngày đến trang điểm cô dâu.</p>

            <!-- Hình ảnh minh họa -->
            <div class="service-image">
                <img src="../images/makeup-image.jpg" alt="Hình ảnh dịch vụ trang điểm" />
            </div>

            <div class="additional-info">
                <h4>Các bước thực hiện dịch vụ</h4>
                <ul>
                    <li>Bước 1: Tư vấn phong cách trang điểm phù hợp với khách hàng.</li>
                    <li>Bước 2: Thực hiện trang điểm theo yêu cầu.</li>
                    <li>Bước 3: Hoàn thiện và kiểm tra kỹ lưỡng trước khi kết thúc.</li>
                </ul>
            </div>

            <div class="box-wrapper">
                <h4>Bảng giá dịch vụ Trang điểm</h4>
                <table class="price-table">
                    <tr>
                        <th>Dịch vụ</th>
                        <th>Giá (VND)</th>
                    </tr>
                    <tr>
                        <td>Trang điểm Cô dâu</td>
                        <td>800,000</td>
                    </tr>
                    <tr>
                        <td>Trang điểm Tiệc</td>
                        <td>600,000</td>
                    </tr>
                    <tr>
                        <td>Trang điểm Hàng ngày</td>
                        <td>400,000</td>
                    </tr>
                </table>
            </div>

            <!-- Nút đặt lịch -->
            <div class="booking-button">
                <a href="view/bookNow.jsp" class="btn-booking">Bấm vào đây để đặt lịch</a>
            </div>
        </div>


        <!-- Massage Section -->
        <div id="massage-info" class="service-info">
            <h4>Chi tiết dịch vụ Massage</h4>
            <p>Đội ngũ chuyên nghiệp của chúng tôi cung cấp các liệu pháp massage thư giãn giúp bạn giải tỏa căng thẳng và phục hồi sức khỏe. Với nhiều phương pháp khác nhau, chúng tôi cam kết mang đến trải nghiệm thư giãn tuyệt vời cho khách hàng.</p>

            <!-- Hình ảnh minh họa -->
            <div class="service-image">
                <img src="../images/massage-image.jpg" alt="Hình ảnh dịch vụ massage" />
            </div>

            <div class="additional-info">
                <h4>Các bước thực hiện dịch vụ</h4>
                <ul>
                    <li>Bước 1: Tư vấn và xác định nhu cầu của khách hàng.</li>
                    <li>Bước 2: Thực hiện liệu pháp massage theo yêu cầu.</li>
                    <li>Bước 3: Hoàn thiện và kiểm tra sự hài lòng của khách hàng.</li>
                </ul>
            </div>

            <div class="box-wrapper">
                <h4>Bảng giá dịch vụ Massage</h4>
                <table class="price-table">
                    <tr>
                        <th>Dịch vụ</th>
                        <th>Giá (VND)</th>
                    </tr>
                    <tr>
                        <td>Massage Thư giãn</td>
                        <td>300,000</td>
                    </tr>
                    <tr>
                        <td>Massage Toàn thân</td>
                        <td>600,000</td>
                    </tr>
                    <tr>
                        <td>Massage Chân</td>
                        <td>150,000</td>
                    </tr>
                </table>
            </div>

            <!-- Nút đặt lịch -->
            <div class="booking-button">
                <a href="view/bookNow.jsp" class="btn-booking">Bấm vào đây để đặt lịch</a>
            </div>
        </div>


        <!-- Head Washing Section -->
        <div id="headwashing-info" class="service-info">
            <h4>Chi tiết dịch vụ Gội đầu</h4>
            <p>Dịch vụ gội đầu của chúng tôi không chỉ giúp bạn thư giãn mà còn mang lại cảm giác tươi mới và sảng khoái. Với các kỹ thuật gội đầu chuyên nghiệp, chúng tôi cam kết đem đến trải nghiệm tuyệt vời nhất cho khách hàng.</p>

            <!-- Hình ảnh minh họa -->
            <div class="service-image">
                <img src="../images/headwashing-image.jpg" alt="Hình ảnh dịch vụ gội đầu" />
            </div>

            <div class="additional-info">
                <h4>Các bước thực hiện dịch vụ</h4>
                <ul>
                    <li>Bước 1: Tư vấn nhu cầu gội đầu của khách hàng.</li>
                    <li>Bước 2: Gội đầu và mát xa da đầu theo kỹ thuật chuyên nghiệp.</li>
                    <li>Bước 3: Sử dụng sản phẩm chăm sóc tóc phù hợp sau khi gội.</li>
                </ul>
            </div>

            <div class="box-wrapper">
                <h4>Bảng giá dịch vụ Gội đầu</h4>
                <table class="price-table">
                    <tr>
                        <th>Dịch vụ</th>
                        <th>Giá (VND)</th>
                    </tr>
                    <tr>
                        <td>Gội đầu thư giãn</td>
                        <td>100,000</td>
                    </tr>
                    <tr>
                        <td>Gội đầu & Mát xa da đầu</td>
                        <td>200,000</td>
                    </tr>
                </table>
            </div>

            <!-- Nút đặt lịch -->
            <div class="booking-button">
            <a href="index.jsp?currentPage=bookNow" class="btn-booking">Bấm vào đây để đặt lịch</a>
        </div>

        </div>




        <!-- Hair Dying Section -->
        <div id="hairdying-info" class="service-info">
            <h4>Chi tiết dịch vụ Nhuộm tóc</h4>
            <p>Chúng tôi cung cấp dịch vụ nhuộm tóc chuyên nghiệp với nhiều màu sắc và kỹ thuật khác nhau, từ nhuộm tiêu chuẩn đến các phong cách nổi bật như Ombre và Balayage. Đội ngũ chuyên gia của chúng tôi sẽ tư vấn để bạn có được màu tóc hoàn hảo nhất.</p>

            <!-- Hình ảnh minh họa -->
            <div class="service-image">
                <img src="../images/hairdying-image.jpg" alt="Hình ảnh dịch vụ nhuộm tóc" />
            </div>

            <div class="additional-info">
                <h4>Các bước thực hiện dịch vụ</h4>
                <ul>
                    <li>Bước 1: Tư vấn màu sắc và phong cách nhuộm phù hợp với bạn.</li>
                    <li>Bước 2: Thực hiện quá trình nhuộm theo kỹ thuật đã chọn.</li>
                    <li>Bước 3: Hoàn thiện và chăm sóc tóc sau khi nhuộm.</li>
                </ul>
            </div>

            <div class="box-wrapper">
                <h4>Bảng giá dịch vụ Nhuộm tóc</h4>
                <table class="price-table">
                    <tr>
                        <th>Dịch vụ</th>
                        <th>Giá (VND)</th>
                    </tr>
                    <tr>
                        <td>Nhuộm tóc tiêu chuẩn</td>
                        <td>600,000</td>
                    </tr>
                    <tr>
                        <td>Nhuộm Ombre</td>
                        <td>1,200,000</td>
                    </tr>
                    <tr>
                        <td>Nhuộm Balayage</td>
                        <td>1,500,000</td>
                    </tr>
                </table>
            </div>

            <!-- Nút đặt lịch -->
            <div class="booking-button">
                <a href="view/bookNow.jsp" class="btn-booking">Bấm vào đây để đặt lịch</a>
            </div>
        </div>


        <!-- Hair Curling Section -->
        <div id="haircurling-info" class="service-info">
            <h4>Chi tiết dịch vụ Uốn tóc</h4>
            <p>Chúng tôi cung cấp dịch vụ uốn tóc chuyên nghiệp với nhiều phong cách khác nhau, từ uốn phồng cho đến uốn sóng nước. Đội ngũ chuyên gia của chúng tôi sẽ đảm bảo bạn có được kiểu tóc hoàn hảo và phù hợp nhất với bạn.</p>

            <!-- Hình ảnh minh họa -->
            <div class="service-image">
                <img src="../images/haircurling-image.jpg" alt="Hình ảnh dịch vụ uốn tóc" />
            </div>

            <div class="additional-info">
                <h4>Các bước thực hiện dịch vụ</h4>
                <ul>
                    <li>Bước 1: Tư vấn kiểu uốn tóc phù hợp với khuôn mặt và nhu cầu của khách hàng.</li>
                    <li>Bước 2: Tiến hành uốn tóc theo kỹ thuật đã chọn.</li>
                    <li>Bước 3: Hoàn thiện và chăm sóc tóc sau khi uốn.</li>
                </ul>
            </div>

            <div class="box-wrapper">
                <h4>Bảng giá dịch vụ Uốn tóc</h4>
                <table class="price-table">
                    <tr>
                        <th>Dịch vụ</th>
                        <th>Giá (VND)</th>
                    </tr>
                    <tr>
                        <td>Uốn phồng</td>
                        <td>800,000</td>
                    </tr>
                    <tr>
                        <td>Uốn đuôi</td>
                        <td>600,000</td>
                    </tr>
                    <tr>
                        <td>Uốn sóng nước</td>
                        <td>1,000,000</td>
                    </tr>
                </table>
            </div>

            <!-- Nút đặt lịch -->
            <div class="booking-button">
                <a href="view/bookNow.jsp" class="btn-booking">Bấm vào đây để đặt lịch</a>
            </div>
        </div>


    </div>
    </div>
</div>


</body>
</html>

<script src="view/js/show-content-service.js"></script>
