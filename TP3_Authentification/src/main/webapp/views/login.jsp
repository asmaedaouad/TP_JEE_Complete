<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card shadow-sm border-0" style="width: 400px;">
    
    <div class="card-body p-4">

        <h3 class="text-center mb-2">Login</h3>
        <p class="text-center text-muted mb-4">
            Please enter your details
        </p>

        <form action="${pageContext.request.contextPath}/Login" method="post">
            
            <div class="mb-3">
                <input type="text" name="username" class="form-control" placeholder="Username" required>
            </div>

            <div class="mb-3">
                <input type="password" name="password" class="form-control" placeholder="Password" required>
            </div>

            <button type="submit" class="btn btn-primary w-100 rounded-pill">
                Login
            </button>
        </form>

        <p class="mt-3 text-center">
            Don't have an account?
            <a href="${pageContext.request.contextPath}/views/register.jsp">Register</a>
        </p>

        <%
            String error = (String) request.getAttribute("error");
            if(error != null && !error.isEmpty()) {
        %>
            <div class="alert alert-danger mt-3 text-center"><%= error %></div>
        <%
            }
        %>

    </div>
</div>

</body>
</html>