function showContent(id) {
    // Ẩn tất cả các phần dịch vụ trước
    const allContent = document.querySelectorAll('.service-content');
    allContent.forEach(content => {
        content.style.display = 'none';
    });

    // Hiển thị phần dịch vụ được chọn
    const selectedContent = document.getElementById(id);
    if (selectedContent) {
        selectedContent.style.display = 'block';
    }
}