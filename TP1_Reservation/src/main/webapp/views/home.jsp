<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">

    <div class="card shadow-sm border-0 text-center" style="width: 400px;">
        
        <div class="card-body p-4">

            <%
                User user = (User) session.getAttribute("user");
                if(user == null) {
                    response.sendRedirect(request.getContextPath() + "/views/login.jsp");
                    return;
                }
            %>

            <h3 class="mb-3">Hello <%= user.getUsername() %> 👋</h3>
            
            <p class="text-muted mb-4">
                Welcome back to your Space
            </p>

            <form action="${pageContext.request.contextPath}/Logout" method="post">
                <button type="submit" class="btn btn-danger w-100 rounded-pill">
                    Logout
                </button>
            </form>

        </div>
    </div>

</div>

</body>
</html>