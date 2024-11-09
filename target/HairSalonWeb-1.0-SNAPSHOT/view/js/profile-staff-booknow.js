// Lấy tất cả các checkbox trong phần dịch vụ
const serviceCheckboxes = document.querySelectorAll('#service-info input[type="checkbox"]');

// Hàm kiểm tra nếu ít nhất một checkbox được chọn
function isAnyServiceSelected() {
    return Array.from(serviceCheckboxes).some(checkbox => checkbox.checked);
}

// Cập nhật hàm toggleActiveClass để kiểm tra checkbox
function toggleActiveClass() {
    const customerBox = document.getElementById('customer-info');
    const serviceBox = document.getElementById('service-info');

    const isCustomerFilled = Array.from(customerInputs).some(input => input.value.trim() !== '');
    const isServiceFilled = isAnyServiceSelected();

    if (isCustomerFilled) {
        customerBox.classList.add('active');
    } else {
        customerBox.classList.remove('active');
    }

    if (isServiceFilled) {
        serviceBox.classList.add('active');
    } else {
        serviceBox.classList.remove('active');
    }
}

// Gắn sự kiện click cho tất cả các checkbox dịch vụ
serviceCheckboxes.forEach(checkbox => {
    checkbox.addEventListener('change', toggleActiveClass);
});
