<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .bookNow-section {
            padding: 20px;

            background-color: #ffffff;
        }

        .booking-container {
            display: flex;
            flex-direction: column;
            max-width: 1200px; /* Tăng chiều rộng tối đa */
            margin: 0 auto;
            padding: 40px; /* Thêm padding cho xung quanh */
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        .box {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .customer-info {
            flex: 1 1 55%; /* Tăng độ rộng cho phần thông tin khách hàng */
        }

        .service-info {
            flex: 1 1 40%; /* Điều chỉnh độ rộng cho phần thông tin dịch vụ */
        }

        h2 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
        }

        form {
            display: flex;
            flex-wrap: wrap;
            gap: 30px; /* Thêm khoảng cách giữa các phần tử */
        }

        label {
            display: block;
            font-weight: 600;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="email"],
        input[type="tel"],
        input[type="date"],
        input[type="time"],
        select {
            width: 100%;
            padding: 12px; /* Tăng padding của các ô nhập liệu */
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            padding: 14px;
            background-color: #333;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            font-size: 20px;
            width: 100%;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #555;
        }

        .service-info .booking-input {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            margin-top: 10px;
        }

        .service-checkbox label {
            flex: 1 1 45%;
            background-color: #f3f3f3;
            padding: 12px; /* Tăng padding cho nhãn dịch vụ */
            border-radius: 8px;
            border: 1px solid #ddd;
            display: flex;
            align-items: center;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .service-checkbox label:hover {
            background-color: #959595;

        }

        .service-checkbox input:checked + label {
            background-color: #070202;
            color:white;
        }

        .service-checkbox input {
            display: none;
        }

        .staff-profile {
            display: none; /* Ẩn profile nhân viên mặc định */
            align-items: center;
            margin-top: 15px;
            margin-bottom: 15px;
        }

        .staff-profile img {
            width: 100px;
            height: 100px;
            margin-right: 15px;

        }

        .staff-profile div {
            flex: 1;
        }

        .customer-info.active {
            border: 2px solid #000000; /* Đổi màu viền khi có dữ liệu */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Thêm bóng mờ */
            transition: border 0.3s ease, box-shadow 0.3s ease; /* Hiệu ứng chuyển tiếp */
        }

        .service-info.active {
            border: 2px solid #000000; /* Đổi màu viền khi có dữ liệu */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Thêm bóng mờ */
            transition: border 0.3s ease, box-shadow 0.3s ease; /* Hiệu ứng chuyển tiếp */
        }

    </style>
</head>
<body>
<section class="bookNow-section">
    <div class="booking-container">
        <form id="booking-form" onsubmit="return validateForm()">
            <!-- Customer Information -->
            <div class="box customer-info">
                <h2>Customer Information</h2>
                <label for="name">Customer Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="phone">Phone Number:</label>
                <input type="tel" id="phone" name="phone" required>

                <label for="date">Booking Date:</label>
                <input type="date" id="date" name="date" required>

                <label for="time">Time:</label>
                <input type="time" id="time" name="time" required>
            </div>

            <!-- Service Information -->
            <div class="box service-info">
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
                </div>


            </div>
            <button type="submit">Book Appointment</button>
        </form>
    </div>
</section>

<script>
    const staffData = {
        1: { name: "Luu Truong Van", age: 30, rating: 4.5, image: "images/staff1.jpg" },
        2: { name: "Le Nguyen Minh Phuc", age: 28, rating: 4.8, image: "images/staff4.jpg" },
        3: { name: "Tran Thanh Dong", age: 35, rating: 4.2, image: "images/staff2.jpg" },
        4: { name: "Do Tan Phat", age: 31, rating: 4.7, image: "images/staff3.jpg" },
        5: { name: "Nguyen Tri Khai", age: 29, rating: 4.6, image: "images/staff5.jpg" }
    };

    function validateForm() {
        const checkboxes = document.querySelectorAll('input[name="service"]:checked');
        if (checkboxes.length === 0) {
            alert("Please select at least one service before booking.");
            return false; // Ngăn chặn việc gửi form
        }
        return true; // Cho phép gửi form
    }

    function toggleStaffSelection() {
        const checkboxes = document.querySelectorAll('input[name="service"]:checked');
        const staffSelection = document.getElementById('staff-selection');

        // Hiện phần chọn nhân viên nếu có ít nhất một dịch vụ được chọn
        if (checkboxes.length > 0) {
            staffSelection.style.display = 'block';
        } else {
            staffSelection.style.display = 'none';
            hideStaffProfile(); // Ẩn profile nhân viên khi không có dịch vụ nào được chọn
        }
    }

    function displayStaffProfile() {
        const staffSelect = document.getElementById('staff');
        const selectedStaffId = staffSelect.value;
        const profileDiv = document.getElementById('staff-profile');
        const staffImage = document.getElementById('staff-image');
        const staffDetails = document.getElementById('staff-details');

        // Nếu không có nhân viên nào được chọn, ẩn profile
        if (selectedStaffId === "") {
            hideStaffProfile();
            return; // Thoát hàm
        }

        // Hiện profile nhân viên được chọn
        const staffInfo = staffData[selectedStaffId];
        staffImage.src = staffInfo.image;
        staffDetails.innerHTML = `
            <strong>Name:</strong> ${staffInfo.name}<br>
            <strong>Age:</strong> ${staffInfo.age}<br>
            <strong>Rating:</strong> ${staffInfo.rating} ★
        `;
        profileDiv.style.display = 'flex'; // Hiện profile
    }

    function hideStaffProfile() {
        document.getElementById('staff-profile').style.display = 'none';
    }

    // Thêm sự kiện cho các ô nhập liệu customer-info
    document.querySelectorAll('.customer-info input').forEach(input => {
        input.addEventListener('input', function() {
            const customerInfoBox = document.querySelector('.customer-info');
            if (this.value) {
                customerInfoBox.classList.add('active');
            } else {
                const allInputsFilled = Array.from(document.querySelectorAll('.customer-info input')).some(input => input.value);
                if (!allInputsFilled) {
                    customerInfoBox.classList.remove('active');
                }
            }
        });
    });

    // Thêm sự kiện cho các ô dịch vụ
    document.querySelectorAll('.service-info input').forEach(input => {
        input.addEventListener('change', function() {
            const serviceInfoBox = document.querySelector('.service-info');
            const checkboxes = document.querySelectorAll('.service-info input[name="service"]:checked');
            if (checkboxes.length > 0) {
                serviceInfoBox.classList.add('active');
            } else {
                serviceInfoBox.classList.remove('active');
            }
        });
    });
</script>
</body>
</html>
