<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Era Hair Salon</title>
    <link rel="icon" href="../images/logo.png">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/popUp.css">
    <link rel="stylesheet" href="css/footer.css">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />


    <!-- CSS của AOS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.css">
    <!-- Các CSS khác của bạn -->


</head>
<body>
<!-- Pop-up Login Form -->
<div id="login-popup" class="popup-overlay d-none">
    <div class="popup-content rounded shadow">
        <button class="close-popup">&times;</button> <!-- Nút đóng -->
        <h2 class="text-center">Login</h2>
        <form>
            <input type="text" class="form-control mb-3" placeholder="Username" required>
            <input type="password" class="form-control mb-3" placeholder="Password" required>
            <button type="submit" class="btn btn-dark w-100">Login</button> <!-- Nút đen -->
        </form>
        <p class="text-center mt-3">Don't have an account? <a href="#" id="to-signup">Sign Up</a></p>
    </div>
</div>

<!-- Pop-up Sign Up Form -->
<div id="signup-popup" class="popup-overlay d-none">
    <div class="popup-content rounded shadow">
        <button class="close-popup">&times;</button> <!-- Nút đóng -->
        <h2 class="text-center">Sign Up</h2>
        <form>
            <input type="text" class="form-control mb-3" placeholder="Username" required>
            <input type="email" class="form-control mb-3" placeholder="Email" required>
            <input type="password" class="form-control mb-3" placeholder="Password" required>
            <button type="submit" class="btn btn-dark w-100">Sign Up</button> <!-- Nút đen -->
        </form>
        <p class="text-center mt-3">Already have an account? <a href="#" id="to-login">Login</a></p>
    </div>
</div>

<!-- Header với video background -->

<header>
    <!-- Video Background -->
    <video autoplay muted loop id="bgVideo">
        <source src="../videos/background-video.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>

    <!-- Nội dung hiển thị phía trên video -->
    <div class="content">
        <!-- Logo -->
        <div class="top-bar">
            <div class="logo">
                <img src="../images/logo.png" alt="Booksy Logo">
            </div>
            <div class="login-signup">
                <a href="#" id="open-login">Log In / Sign Up</a>
            </div>
        </div>

        <!-- Phần chính với tiêu đề và thanh tìm kiếm -->
        <div class="header-content">
            <h1><span id="typing-text"></span></h1>
            <p>Discover and book beauty & wellness professionals near you</p>
            <div class="search-bar">
                <input type="text" placeholder="Search services or businesses">
            </div>
        </div>

        <!-- Menu điều hướng -->
        <nav class="nav link">
            <ul id="main-menu">
                <li><a href="../index.jsp?currentPage=aboutUs">About Us</a></li>
                <li><a href="../index.jsp?currentPage=services">Services</a></li>
                <li><a href="../index.jsp?currentPage=stylists">Stylists</a></li>
                <li><a href="../index.jsp?currentPage=bookNow">Book Now</a></li>
                <li><a href="../index.jsp?currentPage=contact">Contact</a></li>
                <li><a href="../index.jsp?currentPage=handbook">Handbook</a></li>
            </ul>
        </nav>
    </div>
</header>

<!-- khung2 -->
<div class="container mt-5">
    <div class="location-container" data-aos="fade-up">
        <div class="location-info">
            <h2>Turn on location services</h2>
            <p>Get recommendations of great businesses! Turn on location services so we can show you nearby businesses.</p>
            <div class="d-flex">
                <button class="btn btn-primary mr-3">Search Near Me</button>
                <button class="btn btn-outline-primary"> Not Now</button>
            </div>
        </div>
        <div class="location-image">
            <img src="../images/anhggmap.jpg" alt="Location Icon">
        </div>
    </div>
</div>

<!-- 2 bõ tiếp theo -->
<div class="container section-container">
    <div class="row equal-height">
        <!-- Cột Bên Trái -->
        <div class="col-md-6 d-flex">
            <div class="box text-center flex-fill">
                <h6>Booksy App • iOS, Android</h6>
                <h2>Find & book an appointment</h2>
                <p>Cut the phone tag. Find your next appointment and book <strong>instantly</strong> anytime, anywhere.</p>

                <!-- Phần ô nhập tùy chỉnh -->
                <div class="input-group input-group-custom mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">🇺🇸 +1</span>
                    </div>
                    <input type="text" class="form-control" placeholder="Your phone number">
                    <div class="input-group-append">
                        <button class="btn download-btn">DOWNLOAD</button>
                    </div>
                </div>

                <img src="../images/anhboxleft.jpg" alt="Phone Image" class="phone-img">
            </div>
        </div>

        <!-- Cột Bên Phải -->
        <div class="col-md-6 d-flex">
            <div class="box-dark text-center flex-fill">
                <h6>BooksyBiz App • iOS, Android</h6>
                <h2>Booksy for your business</h2>
                <p>Get started with Booksy to run your business better. Calendar, Booking, Marketing, and Payments all in one.</p>
                <button class="btn business-btn">Grow My Business</button>
                <img src="../images/anhboxright.jpg" alt="Phone Image" class="phone-img">
            </div>
        </div>
    </div>
</div>

<div class="container">
    <!-- Phần 1 -->
    <div class="row section-container align-items-center">
        <div class="col-md-6 section-text">
            <h2 class="section-title">Appointments done better</h2>
            <p>Looking for your next appointment with a local barber, hair stylist, massage therapist or nail artist? Need appointment booking for a beard trim, an eyebrow wax, or a deep tissue massage?</p>
            <p>Booksy is a free beauty scheduling app and makes appointments easy to find and book within seconds. No more phone tag. Book anytime, from anywhere, 24/7.</p>
            <p><strong>Discover top businesses in your area and book instantly with Booksy.</strong></p>
        </div>
        <div class="col-md-6 text-center">
            <img src="../images/anhphan3.jpg" alt="Illustration 1" class="illustration">
        </div>
    </div>

    <!-- Phần 2 -->
    <div class="row section-container align-items-center">
        <!-- Hình ảnh bên trái -->
        <div class="col-md-6 text-center">
            <img src="../images/anhphan3-2.jpg" alt="Illustration 2" class="illustration">
        </div>

        <!-- Văn bản bên phải -->
        <div class="col-md-6 section-text">
            <h2 class="section-title">Something come up? We’ve got you.</h2>
            <p>Download Booksy, a free online appointment booking app, and manage your appointments from anywhere. Reschedule or cancel without picking up the phone.</p>
            <p>And because we know life gets busy, we’ll send you reminders. You’ll never forget or miss out on another appointment.</p>
        </div>
    </div>

    <!-- Phần 3 -->
    <div class="row section-container align-items-center">
        <div class="col-md-6 section-text">
            <h2 class="section-title">Book with the best, near you</h2>
            <p>Take a scroll around the block to see top health and beauty businesses on Booksy’s marketplace.</p>
            <p>Check out their vibe from their business profile and hear what other people are saying with verified reviews. You can even look through their portfolio of work.</p>
            <p>Save time and leave the stress to someone else. With Booksy, setting up your next beauty appointment is free and easy.</p>
        </div>
        <div class="col-md-6 text-center">
            <img src="../images/anhphan3-3.jpg" alt="Illustration 3" class="illustration">
        </div>
    </div>
</div>
<!-- ảnh -->
<div class="recommended-section">
    <h2>Recommended for you</h2>
    <div class="recommended-cards" >
        <div class="card"  data-aos="fade-up">
            <img src="../images/Homeimg.png" alt="Best Mustache Styles">
            <p>Best Mustache Styles</p>
        </div>
        <div class="card"  data-aos="fade-up" data-aos-delay="200">
            <img src="../images/Homeimg4.png" alt="What does an esthetician do?">
            <p>What does an esthetician do?</p>
        </div>
        <div class="card"  data-aos="fade-up" data-aos-delay="400">
            <img src="../images/Homimg7.webp" alt="Our Favorite Short Nail Styles To Try">
            <p>Our Favorite Short Nail Styles To Try</p>
        </div>
    </div>
</div>
<h2 class="mb-4">Find your Booksy specialist by city</h2>
<div class="row">

    <!-- Cột 1 -->
    <div class="col-md-3">
        <ul class="city-list">
            <li><a href="#"><span class="city-icon">&gt;</span> Charlotte</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Columbus</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Philadelphia</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Tampa</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Chicago</a></li>
        </ul>
    </div>
    <!-- Cột 2 -->
    <div class="col-md-3">
        <ul class="city-list">
            <li><a href="#"><span class="city-icon">&gt;</span> San Francisco</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> San Antonio</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Los Angeles</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Dallas</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Orlando</a></li>
        </ul>
    </div>
    <!-- Cột 3 -->
    <div class="col-md-3">
        <ul class="city-list">
            <li><a href="#"><span class="city-icon">&gt;</span> New York City</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Washington</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Miami</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> San Diego</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Phoenix</a></li>
        </ul>
    </div>
    <!-- Cột 4 -->
    <div class="col-md-3">
        <ul class="city-list">
            <li><a href="#"><span class="city-icon">&gt;</span> Jacksonville</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Houston</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Austin</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> San Jose</a></li>
            <li><a href="#"><span class="city-icon">&gt;</span> Atlanta</a></li>
        </ul>
    </div>
</div>

<%--footer--%>
<jsp:include page="footer.jsp" />
<%--footer--%>

</body>
</html>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- JavaScript của AOS -->
<script src="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.js"></script>
<script>
    AOS.init({
        duration: 1200, // Thời gian hiệu ứng
        easing: 'ease-in-out', // Đường cong chuyển động
        once: true // Chỉ kích hoạt hiệu ứng một lần khi cuộn
    });
</script>

<script>
    // Danh sách các cụm từ cần hiển thị
    const textList = [
        "Be free",
        "Be colorful",
        "Be confident",
        "Be brave",
        "Be bold",
        "Be yourself"
    ];

    let currentTextIndex = 0; // Vị trí của chuỗi trong danh sách
    let currentCharIndex = 0; // Vị trí của ký tự trong chuỗi
    const typingTextElement = document.getElementById("typing-text");

    function typeText() {
        // Lấy chuỗi hiện tại từ danh sách
        const currentText = textList[currentTextIndex];

        // Hiển thị dần dần từng ký tự của chuỗi
        typingTextElement.textContent = currentText.slice(0, currentCharIndex + 1);

        // Tăng chỉ số ký tự lên
        currentCharIndex++;

        // Nếu đã gõ hết chuỗi hiện tại
        if (currentCharIndex === currentText.length) {
            // Đợi một lúc trước khi chuyển sang từ tiếp theo
            setTimeout(() => {
                deleteText();
            }, 2000); // Đợi 2 giây trước khi xóa
        } else {
            // Tiếp tục gõ
            setTimeout(typeText, 50); // Gõ từng ký tự với khoảng cách 100ms
        }
    }

    function deleteText() {
        // Xóa toàn bộ chuỗi hiện tại
        typingTextElement.textContent = "";

        // Chuyển sang chuỗi tiếp theo trong danh sách
        currentTextIndex = (currentTextIndex + 1) % textList.length;
        currentCharIndex = 0;

        typeText();
    }

    // Bắt đầu khi trang tải xong
    window.onload = function() {
        typeText();
    };
</script>

<script src="js/popup-manage.js"></script>