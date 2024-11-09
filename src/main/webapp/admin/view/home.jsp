<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="view/css/style.css"> <!-- Điều chỉnh đường dẫn CSS -->
</head>
<body>
    <!-- Include Header -->
    <jsp:include page="header.jsp" />

    <div class="container">
        <!-- Sidebar Menu -->
        <aside class="sidebar">
            <ul>
                <li><a href="#dashboard">Dashboard</a></li>
                <li><a href="#users">Users</a></li>
                <li><a href="#appointments">Appointments</a></li>
                <li><a href="#orders">Orders</a></li>
                <li><a href="#services">Services</a></li>
                <li><a href="#employees">Employees</a></li>
                <li><a href="#feedback">Feedback</a></li>
                <li><a href="#settings">Settings</a></li>
                <li><a href="#reports">Reports</a></li>
            </ul>
        </aside>

        <!-- Main Content Area -->
        <main class="content">
            <h2>Welcome to the Admin Dashboard</h2>
            <p>This is where you can manage the Hair Salon system.</p>
        </main>
    </div>

    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />
</body>
</html>
