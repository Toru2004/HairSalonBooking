<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="icon" href="images/logo.png"> <!-- Logo cho Admin -->
    <link rel="stylesheet" href="css/style.css"> <!-- Đường dẫn CSS cho phần quản trị -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Cloudfare CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <!-- Include Header -->

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

            <%-- Display dynamic content based on the page parameter --%>
            <%
                String currentPage = request.getParameter("currentPage") != null
                        ? request.getParameter("currentPage").trim()
                        : "dashboard";

                switch (currentPage) {
                    case "dashboard":
            %><jsp:include page="dashboard.jsp" /> <%
                        break;
                    case "users":
            %><jsp:include page="users.jsp" /> <%
                        break;
                    case "appointments":
            %><jsp:include page="appointments.jsp" /> <%
                        break;
                    case "orders":
            %><jsp:include page="orders.jsp" /> <%
                        break;
                    case "services":
            %><jsp:include page="services.jsp" /> <%
                        break;
                    case "employees":
            %><jsp:include page="employees.jsp" /> <%
                        break;
                    case "feedback":
            %><jsp:include page="feedback.jsp" /> <%
                        break;
                    case "settings":
            %><jsp:include page="settings.jsp" /> <%
                        break;
                    case "reports":
            %><jsp:include page="reports.jsp" /> <%
                        break;
                    default:
            %><jsp:include page="dashboard.jsp" /> <%
                        break;
                }
            %>
        </main>
    </div>

    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />
</body>
</html>

<script src="js/popup-manage.js"></script> <!-- JavaScript xử lý pop-up nếu cần -->
