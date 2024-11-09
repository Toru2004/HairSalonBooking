<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/style.css">
</head>
<body>
<header>
    <h2>Admin</h2>
</header>

<div class="container">
    <!-- Sidebar Menu -->
    <aside class="sidebar">
        <ul>
            <li><a href="?currentPage=dashboard">Dashboard</a></li>
            <li><a href="?currentPage=users">Users</a></li>
            <li><a href="?currentPage=appointments">Appointments</a></li>
            <li><a href="?currentPage=orders">Orders</a></li>
            <li><a href="?currentPage=services">Services</a></li>
            <li><a href="?currentPage=employees">Employees</a></li>
            <li><a href="?currentPage=feedback">Feedback</a></li>
            <li><a href="?currentPage=settings">Settings</a></li>
            <li><a href="?currentPage=reports">Reports</a></li>
        </ul>
    </aside>

    <!-- Main Content Area -->
    <main class="content">
        <%-- Dynamic Content Loading --%>
        <%
            String currentPage = request.getParameter("currentPage") != null
                    ? request.getParameter("currentPage").trim()
                    : "dashboard";

            String pagePath = "/view/" + currentPage + ".jsp"; // Updated path
            try {
                if (getServletContext().getResource(pagePath) != null) {
        %>
        <jsp:include page="<%= pagePath %>" />
        <%
                } else {
                    out.println("<p>Page not found.</p>");
                }
            } catch (Exception e) {
                out.println("<p>Error loading page.</p>");
            }
        %>
    </main>
</div>

<!-- Footer Content -->
<footer>
    <p>&copy; 2024 Hair Salon. All rights reserved.</p>
</footer>
</body>
</html>
