<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="view/css/booknow.css">
</head>
<body>
<section class="bookNow-section">
    <div class="booking-container">
        <!-- Combined Form -->
        <form id="booking-form">
            <!-- Customer Information -->
            <div class="box customer-info" id="customer-info">
                <h2>Customer Information</h2>
                <label for="name">Customer Name:</label>
                <input type="text" id="name" name="name" required class="booking-input">

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required class="booking-input">

                <label for="phone">Phone Number:</label>
                <input type="tel" id="phone" name="phone" required class="booking-input">

                <label for="date">Booking Date:</label>
                <input type="date" id="date" name="date" required class="booking-input">

                <label for="time">Time:</label>
                <input type="time" id="time" name="time" required class="booking-input">
            </div>

            <!-- Service Information -->
            <div class="box service-info" id="service-info">
                <h2>Service Information</h2>
                <label style="font-size:18px;">Select Services:</label>
                <div class="booking-input service-checkbox">
                    <input type="checkbox" id="service-haircut" name="service" value="haircut" onchange="toggleStaffSelection()">
                    <label  for="service-haircut" >Hair Cut</label>

                    <input type="checkbox" id="service-makeup" name="service" value="makeup" onchange="toggleStaffSelection()">
                    <label for="service-makeup">Makeup</label>

                    <input type="checkbox" id="service-massage" name="service" value="massage" onchange="toggleStaffSelection()">
                    <label for="service-massage">Massage</label>

                    <input type="checkbox" id="service-headwashing" name="service" value="headwashing" onchange="toggleStaffSelection()">
                    <label for="service-headwashing">Hair Washing</label>

                    <input type="checkbox" id="service-hairdying" name="service" value="hairdying" onchange="toggleStaffSelection()">
                    <label for="service-hairdying">Hair Dying</label>

                    <input type="checkbox" id="service-haircurling" name="service" value="haircurling" onchange="toggleStaffSelection()">
                    <label for="service-haircurling">Hair Curling</label>
                </div>
<div>Văn</div>
                <div id="staff-selection" style="display: none;">
                    <label for="staff" style="font-size:18px;">Select Staff:</label>
                    <select id="staff" name="staff" required onchange="displayStaffProfile()">
                        <option value="">Select Staff</option>
                        <option value="1">Luu Truong Van</option>
                        <option value="2">Le Nguyen Minh Phuc</option>
                        <option value="3">Tran Thanh Dong</option>
                        <option value="4">Do Tan Phat</option>
                        <option value="5">Nguyen Tri Khai</option>
                    </select>

                <div id="staff-profile" class="staff-profile">
                    <img id="staff-image" src="" alt="Profile Image">
                    <div id="staff-details"></div>
                </div>
                <button type="submit">Book Appointment</button>
            </div>
        </form>

    </div>
</section>




</body>
</html>
<script src="view/js/profile-staff-booknow.js"></script>


<script> // Lấy tất cả các input và select trong form
const customerInputs = document.querySelectorAll('#booking-form input, #booking-form select');
const serviceInputs = document.querySelectorAll('#service-info select');

// Hàm để thêm hoặc xóa class active
function toggleActiveClass() {
    const customerBox = document.getElementById('customer-info');
    const serviceBox = document.getElementById('service-info');

    // Kiểm tra nếu có bất kỳ input nào được điền trong phần thông tin khách hàng
    const isCustomerFilled = Array.from(customerInputs).some(input => input.value.trim() !== '');
    const isServiceFilled = Array.from(serviceInputs).some(input => input.value.trim() !== '');

    // Thêm hoặc xóa class active cho bảng thông tin khách hàng
    if (isCustomerFilled) {
        customerBox.classList.add('active');
    } else {
        customerBox.classList.remove('active');
    }

    // Thêm hoặc xóa class active cho bảng thông tin dịch vụ
    if (isServiceFilled) {
        serviceBox.classList.add('active');
    } else {
        serviceBox.classList.remove('active');
    }
}

// Gắn sự kiện input cho tất cả các input trong form khách hàng
customerInputs.forEach(input => {
    input.addEventListener('input', toggleActiveClass);
});

// Gắn sự kiện input cho tất cả các select trong dịch vụ
serviceInputs.forEach(input => {
    input.addEventListener('change', toggleActiveClass);
});
</script>

